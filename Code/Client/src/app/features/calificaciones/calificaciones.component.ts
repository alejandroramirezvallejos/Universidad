/**
 * Página de Calificaciones
 * Vista diferenciada para estudiantes y docentes
 * 
 * Heurística Nielsen #1: Visibilidad del estado del sistema
 * - Muestra claramente las notas y su estado
 * 
 * Heurística Nielsen #7: Flexibilidad y eficiencia de uso
 * - Vista adaptada al rol del usuario
 */

import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';
import { CalificacionesService, NotaMateria, EstudianteNotas, GrupoDocente } from '../../core/services/calificaciones.service';
import { NotificacionService } from '../../core/services/notificacion.service';

@Component({
  selector: 'app-calificaciones',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="calificaciones-page">
      <header class="pagina-header">
        <h1>{{ esDocente ? 'Registro de Calificaciones' : 'Mis Calificaciones' }}</h1>
        <p>Gestión II-2025</p>
      </header>

      <!-- Vista Estudiante -->
      <div *ngIf="!esDocente" class="vista-estudiante">
        <!-- Resumen -->
        <section class="resumen-grid">
          <div class="resumen-card card">
            <div class="resumen-icono promedio">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <line x1="18" y1="20" x2="18" y2="10"></line>
                <line x1="12" y1="20" x2="12" y2="4"></line>
                <line x1="6" y1="20" x2="6" y2="14"></line>
              </svg>
            </div>
            <div class="resumen-info">
              <span class="resumen-valor">{{ promedioActual() }}</span>
              <span class="resumen-label">Promedio Actual</span>
            </div>
          </div>

          <div class="resumen-card card">
            <div class="resumen-icono materias">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="3" width="7" height="7"></rect>
                <rect x="14" y="3" width="7" height="7"></rect>
                <rect x="14" y="14" width="7" height="7"></rect>
                <rect x="3" y="14" width="7" height="7"></rect>
              </svg>
            </div>
            <div class="resumen-info">
              <span class="resumen-valor">{{ materiasInscritas().length }}</span>
              <span class="resumen-label">Materias Inscritas</span>
            </div>
          </div>

          <div class="resumen-card card">
            <div class="resumen-icono aprobadas">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path>
                <polyline points="22 4 12 14.01 9 11.01"></polyline>
              </svg>
            </div>
            <div class="resumen-info">
              <span class="resumen-valor">{{ materiasAprobando() }}</span>
              <span class="resumen-label">Aprobando</span>
            </div>
          </div>

          <div class="resumen-card card">
            <div class="resumen-icono riesgo">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M10.29 3.86L1.82 18a2 2 0 0 0 1.71 3h16.94a2 2 0 0 0 1.71-3L13.71 3.86a2 2 0 0 0-3.42 0z"></path>
                <line x1="12" y1="9" x2="12" y2="13"></line>
                <line x1="12" y1="17" x2="12.01" y2="17"></line>
              </svg>
            </div>
            <div class="resumen-info">
              <span class="resumen-valor">{{ materiasEnRiesgo() }}</span>
              <span class="resumen-label">En Riesgo</span>
            </div>
          </div>
        </section>

        <!-- Alerta de reprobación -->
        <section class="alerta-reprobacion card" *ngIf="materiasEnRiesgo() > 0">
          <div class="alerta-contenido">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="12" cy="12" r="10"></circle>
              <line x1="12" y1="8" x2="12" y2="12"></line>
              <line x1="12" y1="16" x2="12.01" y2="16"></line>
            </svg>
            <div>
              <strong>Atención:</strong> Tienes {{ materiasEnRiesgo() }} materia(s) en riesgo de reprobación.
              Considera asistir a tutorías o hablar con tu docente.
            </div>
          </div>
        </section>

        <!-- Lista de materias -->
        <section class="materias-notas">
          <div *ngFor="let materia of materiasInscritas()" class="materia-card card">
            <div class="materia-header" (click)="toggleMateria(materia.materiaId)">
              <div class="materia-info">
                <span class="materia-codigo">{{ materia.materiaCodigo }}</span>
                <h3>{{ materia.materiaNombre }}</h3>
                <span class="materia-docente">{{ materia.docente }}</span>
              </div>
              <div class="materia-nota">
                <div 
                  class="nota-circulo"
                  [class.nota-aprobando]="materia.notaFinal >= 51"
                  [class.nota-reprobando]="materia.notaFinal < 51 && materia.porcentajeEvaluado > 0"
                  [class.nota-pendiente]="materia.porcentajeEvaluado === 0"
                >
                  {{ materia.porcentajeEvaluado > 0 ? materia.notaFinal.toFixed(0) : '-' }}
                </div>
                <span class="avance-texto">{{ materia.porcentajeEvaluado }}% evaluado</span>
              </div>
            </div>

            <!-- Barra de progreso -->
            <div class="progreso-barra">
              <div 
                class="progreso-relleno"
                [style.width.%]="materia.porcentajeEvaluado"
                [class.progreso-aprobando]="materia.notaFinal >= 51"
                [class.progreso-reprobando]="materia.notaFinal < 51"
              ></div>
            </div>

            <!-- Detalle de evaluaciones -->
            <div class="evaluaciones-lista" *ngIf="materiaExpandida() === materia.materiaId">
              <table class="tabla">
                <thead>
                  <tr>
                    <th>Evaluación</th>
                    <th>Tipo</th>
                    <th>Porcentaje</th>
                    <th>Nota</th>
                    <th>Aporte</th>
                  </tr>
                </thead>
                <tbody>
                  <tr *ngFor="let eval of materia.evaluaciones">
                    <td>{{ eval.nombre }}</td>
                    <td><span class="badge badge-info">{{ eval.tipo }}</span></td>
                    <td>{{ eval.porcentaje }}%</td>
                    <td>
                      <span *ngIf="eval.nota !== null" [class.texto-exito]="eval.nota >= 51" [class.texto-error]="eval.nota < 51">
                        {{ eval.nota }}
                      </span>
                      <span *ngIf="eval.nota === null" class="texto-secundario">Pendiente</span>
                    </td>
                    <td>
                      <span *ngIf="eval.nota !== null">{{ (eval.nota * eval.porcentaje / 100).toFixed(1) }}</span>
                      <span *ngIf="eval.nota === null" class="texto-secundario">-</span>
                    </td>
                  </tr>
                </tbody>
                <tfoot>
                  <tr>
                    <td colspan="3"><strong>Total</strong></td>
                    <td colspan="2">
                      <strong [class.texto-exito]="materia.notaFinal >= 51" [class.texto-error]="materia.notaFinal < 51 && materia.porcentajeEvaluado > 0">
                        {{ materia.notaFinal.toFixed(1) }}
                      </strong>
                    </td>
                  </tr>
                </tfoot>
              </table>
            </div>
          </div>
        </section>

        <!-- Mensaje si no hay materias -->
        <section class="sin-datos card" *ngIf="materiasInscritas().length === 0">
          <div class="sin-datos-contenido">
            <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <rect x="3" y="3" width="7" height="7"></rect>
              <rect x="14" y="3" width="7" height="7"></rect>
              <rect x="14" y="14" width="7" height="7"></rect>
              <rect x="3" y="14" width="7" height="7"></rect>
            </svg>
            <p>No tienes materias inscritas en esta gestión</p>
            <a routerLink="/oferta" class="btn btn-primario">Ver Oferta Académica</a>
          </div>
        </section>
      </div>

      <!-- Vista Docente -->
      <div *ngIf="esDocente" class="vista-docente">
        <!-- Selector de grupo -->
        <section class="selector-grupo card">
          <div class="card-body">
            <div class="form-grupo">
              <label class="form-label">Seleccionar Grupo</label>
              <select class="form-select" [(ngModel)]="grupoSeleccionadoId" (change)="cargarEstudiantes()">
                <option value="">-- Selecciona un grupo --</option>
                <option *ngFor="let grupo of gruposDocente()" [value]="grupo.grupoId">
                  {{ grupo.materiaNombre }} - Grupo {{ grupo.grupoCodigo }} ({{ grupo.cantidadEstudiantes }} estudiantes)
                </option>
              </select>
            </div>
          </div>
        </section>

        <!-- Tabla de notas del grupo -->
        <section class="tabla-notas card" *ngIf="grupoSeleccionadoId && estudiantesGrupo().length > 0">
          <div class="card-header">
            <h3 class="card-titulo">Lista de Estudiantes - {{ grupoActualNombre() }}</h3>
            <div class="card-acciones">
              <button class="btn btn-outline btn-sm" (click)="generarActaPDF()">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
                  <polyline points="14 2 14 8 20 8"></polyline>
                  <line x1="16" y1="13" x2="8" y2="13"></line>
                  <line x1="16" y1="17" x2="8" y2="17"></line>
                </svg>
                Generar Acta PDF
              </button>
            </div>
          </div>
          <div class="tabla-contenedor">
            <table class="tabla">
              <thead>
                <tr>
                  <th>Estudiante</th>
                  <th>Parcial 1 (25%)</th>
                  <th>Parcial 2 (25%)</th>
                  <th>Proyecto (30%)</th>
                  <th>Final (20%)</th>
                  <th>Nota Final</th>
                  <th>Estado</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let est of estudiantesGrupo()">
                  <td>
                    <div class="estudiante-info">
                      <span class="estudiante-nombre">{{ est.estudianteNombre }}</span>
                      <span class="estudiante-codigo">{{ est.estudianteCodigo }}</span>
                    </div>
                  </td>
                  <td>
                    <input 
                      type="number" 
                      class="nota-input"
                      [value]="est.notas['Parcial 1'] ?? ''"
                      [placeholder]="est.notas['Parcial 1'] === null ? '0-100' : ''"
                      min="0" 
                      max="100"
                      (blur)="guardarNota(est.estudianteId, 'Parcial 1', $event)"
                    >
                  </td>
                  <td>
                    <input 
                      type="number" 
                      class="nota-input"
                      [value]="est.notas['Parcial 2'] ?? ''"
                      [placeholder]="est.notas['Parcial 2'] === null ? '0-100' : ''"
                      min="0" 
                      max="100"
                      (blur)="guardarNota(est.estudianteId, 'Parcial 2', $event)"
                    >
                  </td>
                  <td>
                    <input 
                      type="number" 
                      class="nota-input"
                      [value]="est.notas['Proyecto'] ?? ''"
                      [placeholder]="est.notas['Proyecto'] === null ? '0-100' : ''"
                      min="0" 
                      max="100"
                      (blur)="guardarNota(est.estudianteId, 'Proyecto', $event)"
                    >
                  </td>
                  <td>
                    <input 
                      type="number" 
                      class="nota-input"
                      [value]="est.notas['Examen Final'] ?? ''"
                      [placeholder]="est.notas['Examen Final'] === null ? '0-100' : ''"
                      min="0" 
                      max="100"
                      (blur)="guardarNota(est.estudianteId, 'Examen Final', $event)"
                    >
                  </td>
                  <td>
                    <span 
                      class="nota-final"
                      [class.texto-exito]="est.notaFinal >= 51"
                      [class.texto-error]="est.notaFinal < 51"
                    >
                      {{ est.notaFinal.toFixed(1) }}
                    </span>
                  </td>
                  <td>
                    <span 
                      class="badge"
                      [class.badge-exito]="est.notaFinal >= 51"
                      [class.badge-error]="est.notaFinal < 51 && est.porcentajeEvaluado > 50"
                      [class.badge-advertencia]="est.notaFinal < 51 && est.porcentajeEvaluado <= 50"
                    >
                      {{ getEstadoEstudiante(est) }}
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="card-footer">
            <div class="estadisticas-grupo">
              <span>Aprobados: {{ estadisticasGrupo().aprobados }}</span>
              <span>Reprobados: {{ estadisticasGrupo().reprobados }}</span>
              <span>Promedio: {{ estadisticasGrupo().promedio.toFixed(1) }}</span>
            </div>
            <button class="btn btn-primario" (click)="guardarTodo()">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"></path>
                <polyline points="17 21 17 13 7 13 7 21"></polyline>
                <polyline points="7 3 7 8 15 8"></polyline>
              </svg>
              Guardar Cambios
            </button>
          </div>
        </section>

        <!-- Mensaje si no hay grupo seleccionado -->
        <section class="sin-datos card" *ngIf="!grupoSeleccionadoId">
          <div class="sin-datos-contenido">
            <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
              <circle cx="9" cy="7" r="4"></circle>
              <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
              <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
            </svg>
            <p>Selecciona un grupo para ver y registrar calificaciones</p>
          </div>
        </section>
      </div>
    </div>
  `,
  styles: [`
    .calificaciones-page {
      max-width: var(--ancho-maximo);
      margin: 0 auto;
    }

    .pagina-header {
      margin-bottom: var(--espaciado-xl);
    }

    .pagina-header h1 {
      font-size: var(--texto-2xl);
      margin-bottom: var(--espaciado-xs);
    }

    .pagina-header p {
      color: var(--color-texto-secundario);
      margin: 0;
    }

    /* Resumen */
    .resumen-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
      gap: var(--espaciado-md);
      margin-bottom: var(--espaciado-xl);
    }

    .resumen-card {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
      padding: var(--espaciado-lg);
    }

    .resumen-icono {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 48px;
      height: 48px;
      border-radius: var(--radio-md);
    }

    .resumen-icono.promedio {
      background-color: var(--color-primario-light);
      color: var(--color-primario);
    }

    .resumen-icono.materias {
      background-color: var(--color-info-light);
      color: var(--color-info);
    }

    .resumen-icono.aprobadas {
      background-color: var(--color-exito-light);
      color: var(--color-exito);
    }

    .resumen-icono.riesgo {
      background-color: var(--color-advertencia-light);
      color: var(--color-advertencia);
    }

    .resumen-info {
      display: flex;
      flex-direction: column;
    }

    .resumen-valor {
      font-size: var(--texto-2xl);
      font-weight: 700;
    }

    .resumen-label {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    /* Alerta de reprobación */
    .alerta-reprobacion {
      background-color: var(--color-advertencia-light);
      border: 1px solid var(--color-advertencia);
      margin-bottom: var(--espaciado-xl);
    }

    .alerta-contenido {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
      padding: var(--espaciado-md);
      color: var(--color-advertencia);
    }

    /* Materias notas */
    .materias-notas {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-md);
    }

    .materia-card {
      overflow: hidden;
    }

    .materia-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: var(--espaciado-lg);
      cursor: pointer;
      transition: background-color var(--transicion-rapida);
    }

    .materia-header:hover {
      background-color: var(--color-fondo);
    }

    .materia-codigo {
      display: inline-block;
      padding: 2px var(--espaciado-sm);
      background-color: var(--color-primario-light);
      color: var(--color-primario);
      font-size: var(--texto-xs);
      font-weight: 600;
      border-radius: var(--radio-sm);
      margin-bottom: var(--espaciado-xs);
    }

    .materia-info h3 {
      font-size: var(--texto-base);
      margin-bottom: 2px;
    }

    .materia-docente {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .materia-nota {
      text-align: center;
    }

    .nota-circulo {
      width: 56px;
      height: 56px;
      border-radius: var(--radio-full);
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: var(--texto-xl);
      font-weight: 700;
      background-color: var(--color-fondo);
    }

    .nota-circulo.nota-aprobando {
      background-color: var(--color-exito-light);
      color: var(--color-exito);
    }

    .nota-circulo.nota-reprobando {
      background-color: var(--color-error-light);
      color: var(--color-error);
    }

    .nota-circulo.nota-pendiente {
      background-color: var(--color-fondo);
      color: var(--color-texto-secundario);
    }

    .avance-texto {
      font-size: var(--texto-xs);
      color: var(--color-texto-secundario);
      display: block;
      margin-top: var(--espaciado-xs);
    }

    .progreso-barra {
      height: 4px;
      background-color: var(--color-fondo);
    }

    .progreso-relleno {
      height: 100%;
      background-color: var(--color-primario);
      transition: width 0.3s ease;
    }

    .progreso-relleno.progreso-aprobando {
      background-color: var(--color-exito);
    }

    .progreso-relleno.progreso-reprobando {
      background-color: var(--color-error);
    }

    .evaluaciones-lista {
      padding: var(--espaciado-md) var(--espaciado-lg);
      background-color: var(--color-fondo);
      animation: slideDown var(--transicion-normal);
    }

    @keyframes slideDown {
      from { opacity: 0; transform: translateY(-10px); }
      to { opacity: 1; transform: translateY(0); }
    }

    /* Vista Docente */
    .selector-grupo {
      margin-bottom: var(--espaciado-xl);
    }

    .form-grupo {
      max-width: 400px;
    }

    .tabla-contenedor {
      overflow-x: auto;
    }

    .nota-input {
      width: 60px;
      padding: var(--espaciado-xs);
      border: 1px solid var(--color-borde);
      border-radius: var(--radio-sm);
      text-align: center;
      font-size: var(--texto-sm);
    }

    .nota-input:focus {
      outline: none;
      border-color: var(--color-primario);
      box-shadow: 0 0 0 2px var(--color-primario-light);
    }

    .estudiante-info {
      display: flex;
      flex-direction: column;
    }

    .estudiante-nombre {
      font-weight: 500;
    }

    .estudiante-codigo {
      font-size: var(--texto-xs);
      color: var(--color-texto-secundario);
    }

    .nota-final {
      font-size: var(--texto-lg);
      font-weight: 700;
    }

    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: var(--espaciado-md) var(--espaciado-lg);
      border-bottom: 1px solid var(--color-borde);
    }

    .card-titulo {
      font-size: var(--texto-lg);
      font-weight: 600;
      margin: 0;
    }

    .card-acciones {
      display: flex;
      gap: var(--espaciado-sm);
    }

    .card-footer {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: var(--espaciado-md) var(--espaciado-lg);
      border-top: 1px solid var(--color-borde);
      background-color: var(--color-fondo);
    }

    .estadisticas-grupo {
      display: flex;
      gap: var(--espaciado-lg);
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    /* Sin datos */
    .sin-datos {
      padding: var(--espaciado-2xl);
    }

    .sin-datos-contenido {
      display: flex;
      flex-direction: column;
      align-items: center;
      text-align: center;
      gap: var(--espaciado-md);
      color: var(--color-texto-secundario);
    }

    .sin-datos-contenido svg {
      opacity: 0.5;
    }

    /* Responsivo */
    @media (max-width: 768px) {
      .resumen-grid {
        grid-template-columns: repeat(2, 1fr);
      }

      .materia-header {
        flex-direction: column;
        align-items: flex-start;
        gap: var(--espaciado-md);
      }

      .materia-nota {
        align-self: flex-end;
      }

      .card-footer {
        flex-direction: column;
        gap: var(--espaciado-md);
      }

      .estadisticas-grupo {
        flex-wrap: wrap;
        justify-content: center;
      }
    }
  `]
})
export class CalificacionesComponent implements OnInit {
  materiaExpandida = signal<number | null>(null);
  grupoSeleccionadoId = '';
  private _estudiantesGrupo = signal<EstudianteNotas[]>([]);

  constructor(
    private authService: AuthService,
    private calificacionesService: CalificacionesService,
    private notificacion: NotificacionService
  ) { }

  ngOnInit(): void {
    // Cargar datos según el rol
    if (this.esDocente) {
      this.cargarGruposDocente();
    } else {
      this.cargarMateriasEstudiante();
    }
  }

  get esDocente(): boolean {
    const rol = this.authService.rol();
    return rol === 'DOCENTE';
  }

  // ====== VISTA ESTUDIANTE ======

  private _materiasInscritas = signal<NotaMateria[]>([]);
  materiasInscritas = computed(() => this._materiasInscritas());

  private _promedioActual = signal<number>(0);
  promedioActual = computed(() => {
    const promedio = this._promedioActual();
    return promedio > 0 ? promedio.toFixed(1) : '-';
  });

  materiasAprobando = computed(() => {
    return this._materiasInscritas().filter((m: NotaMateria) => m.notaFinal >= 51).length;
  });

  materiasEnRiesgo = computed(() => {
    return this._materiasInscritas().filter((m: NotaMateria) =>
      m.notaFinal < 51 && m.porcentajeEvaluado >= 50
    ).length;
  });

  async cargarMateriasEstudiante() {
    if (this.esDocente) return;
    try {
      const materias = await this.calificacionesService.obtenerMisNotas();
      this._materiasInscritas.set(materias);

      const promedio = await this.calificacionesService.calcularPromedioActual();
      this._promedioActual.set(promedio);
    } catch (error) {
      console.error('Error al cargar materias del estudiante:', error);
      this.notificacion.error('Error al cargar las calificaciones');
    }
  }

  toggleMateria(materiaId: number): void {
    if (this.materiaExpandida() === materiaId) {
      this.materiaExpandida.set(null);
    } else {
      this.materiaExpandida.set(materiaId);
    }
  }

  // ====== VISTA DOCENTE ======

  private _gruposDocente = signal<GrupoDocente[]>([]);
  gruposDocente = computed(() => this._gruposDocente());

  estudiantesGrupo = computed(() => this._estudiantesGrupo());

  grupoActualNombre = computed(() => {
    const grupo = this._gruposDocente().find((g: GrupoDocente) => g.grupoId === this.grupoSeleccionadoId);
    return grupo ? `${grupo.materiaNombre} - Grupo ${grupo.grupoCodigo}` : '';
  });

  async cargarGruposDocente() {
    console.log('=== cargarGruposDocente ===');
    console.log('esDocente:', this.esDocente);
    
    if (!this.esDocente) return;
    try {
      const grupos = await this.calificacionesService.obtenerMisGrupos();
      console.log('Grupos obtenidos:', grupos);
      this._gruposDocente.set(grupos);
    } catch (error) {
      console.error('Error al cargar grupos del docente:', error);
      this.notificacion.error('Error al cargar los grupos');
    }
  }

  async cargarEstudiantes(): Promise<void> {
    console.log('=== cargarEstudiantes ===');
    console.log('grupoSeleccionadoId:', this.grupoSeleccionadoId);
    
    if (!this.grupoSeleccionadoId) {
      this._estudiantesGrupo.set([]);
      return;
    }

    try {
      console.log('Llamando a obtenerEstudiantesGrupo con:', this.grupoSeleccionadoId);
      const estudiantes = await this.calificacionesService.obtenerEstudiantesGrupo(
        this.grupoSeleccionadoId
      );
      console.log('Estudiantes recibidos:', estudiantes);
      this._estudiantesGrupo.set(estudiantes);
    } catch (error) {
      console.error('Error al cargar estudiantes:', error);
      this.notificacion.error('Error al cargar los estudiantes del grupo');
    }
  }

  estadisticasGrupo = computed(() => {
    const estudiantes = this._estudiantesGrupo();
    if (estudiantes.length === 0) {
      return { aprobados: 0, reprobados: 0, promedio: 0 };
    }

    const aprobados = estudiantes.filter((e: EstudianteNotas) => e.notaFinal >= 51).length;
    const reprobados = estudiantes.filter((e: EstudianteNotas) => e.notaFinal < 51 && e.porcentajeEvaluado >= 50).length;
    const promedio = estudiantes.reduce((sum: number, e: EstudianteNotas) => sum + e.notaFinal, 0) / estudiantes.length;

    return { aprobados, reprobados, promedio };
  });

  getEstadoEstudiante(est: EstudianteNotas): string {
    if (est.porcentajeEvaluado < 50) return 'En curso';
    return est.notaFinal >= 51 ? 'Aprobado' : 'Reprobado';
  }

  async guardarNota(estudianteId: string, evaluacion: string, event: Event): Promise<void> {
    const input = event.target as HTMLInputElement;
    const nota = input.value ? parseFloat(input.value) : null;

    if (nota !== null && (nota < 0 || nota > 100)) {
      this.notificacion.error('La nota debe estar entre 0 y 100');
      return;
    }

    const grupoId = this.grupoSeleccionadoId;
    const resultado = await this.calificacionesService.guardarNota(grupoId, estudianteId, evaluacion, nota);

    if (resultado.exito) {
      this.notificacion.exito('Nota guardada');

      if (resultado.alerta) {
        this.notificacion.advertencia(resultado.alerta);
      }

      this.cargarEstudiantes();
    } else {
      this.notificacion.error(resultado.mensaje || 'Error al guardar nota');
    }
  }

  guardarTodo(): void {
    this.notificacion.exito('Todas las notas guardadas correctamente');
  }

  generarActaPDF(): void {
    // Generar PDF del acta de notas
    const grupo = this._gruposDocente().find((g: GrupoDocente) => g.grupoId === this.grupoSeleccionadoId);
    if (!grupo) return;

    // Crear contenido del acta
    const estudiantes = this.estudiantesGrupo();
    let contenido = `
      ACTA DE CALIFICACIONES
      =======================
      
      Materia: ${grupo.materiaNombre}
      Grupo: ${grupo.grupoCodigo}
      Gestión: II-2025
      
      ESTUDIANTES:
    `;

    estudiantes.forEach((est, index) => {
      contenido += `
      ${index + 1}. ${est.estudianteNombre} (${est.estudianteCodigo})
         Nota Final: ${est.notaFinal.toFixed(1)} - ${est.notaFinal >= 51 ? 'APROBADO' : 'REPROBADO'}`;
    });

    contenido += `
      
      =======================
      Total Aprobados: ${this.estadisticasGrupo().aprobados}
      Total Reprobados: ${this.estadisticasGrupo().reprobados}
      Promedio Grupo: ${this.estadisticasGrupo().promedio.toFixed(1)}
    `;

    // Crear y descargar archivo
    const blob = new Blob([contenido], { type: 'text/plain' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = `Acta_${grupo.materiaNombre}_Grupo${grupo.grupoCodigo}.txt`;
    link.click();
    window.URL.revokeObjectURL(url);

    this.notificacion.exito('Acta generada y descargada');
  }
}
