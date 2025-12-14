/**
 * Página de Gestión de Períodos Académicos
 * Solo para Directores de Carrera
 * 
 * Heurística Nielsen #3: Control y libertad del usuario
 * - Acciones reversibles y confirmaciones
 * 
 * Heurística Nielsen #5: Prevención de errores
 * - Validaciones antes de cerrar períodos
 */

import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NotificacionService } from '../../core/services/notificacion.service';

interface Gestion {
  id: number;
  nombre: string;
  fechaInicio: Date;
  fechaFin: Date;
  fechaInicioExamenes: Date; // Se calcula automáticamente: 1 mes antes del fin
  estado: 'PLANIFICACION' | 'INSCRIPCION' | 'EN_CURSO' | 'EXAMENES' | 'CERRADA';
  estudiantesInscritos: number;
  materiasActivas: number;
  tipo: 'I' | 'II'; // Gestión I (Feb-Jun) o II (Ago-Dic)
}

@Component({
  selector: 'app-gestiones',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="gestiones-page">
      <header class="pagina-header">
        <div>
          <h1>Gestión de Períodos Académicos</h1>
          <p>Administración de gestiones y calendario académico</p>
        </div>
        <button class="btn btn-primario" (click)="abrirModalNueva()">
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="12" y1="5" x2="12" y2="19"></line>
            <line x1="5" y1="12" x2="19" y2="12"></line>
          </svg>
          Nueva Gestión
        </button>
      </header>

      <!-- Período actual destacado -->
      <section class="periodo-actual card" *ngIf="gestionActual">
        <div class="periodo-header">
          <div class="periodo-badge">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
              <line x1="16" y1="2" x2="16" y2="6"></line>
              <line x1="8" y1="2" x2="8" y2="6"></line>
              <line x1="3" y1="10" x2="21" y2="10"></line>
            </svg>
            Período Actual
          </div>
          <span class="estado-badge" [ngClass]="'estado-' + gestionActual.estado.toLowerCase()">
            {{ formatearEstado(gestionActual.estado) }}
          </span>
        </div>

        <div class="periodo-info">
          <h2>{{ gestionActual.nombre }}</h2>
          <div class="periodo-fechas">
            <span>{{ gestionActual.fechaInicio | date:'dd MMM yyyy' }}</span>
            <span class="separador">—</span>
            <span>{{ gestionActual.fechaFin | date:'dd MMM yyyy' }}</span>
          </div>
        </div>

        <div class="periodo-stats">
          <div class="stat">
            <span class="stat-valor">{{ gestionActual.estudiantesInscritos }}</span>
            <span class="stat-label">Estudiantes</span>
          </div>
          <div class="stat">
            <span class="stat-valor">{{ gestionActual.materiasActivas }}</span>
            <span class="stat-label">Materias</span>
          </div>
          <div class="stat">
            <span class="stat-valor">{{ diasRestantes }}</span>
            <span class="stat-label">Días Restantes</span>
          </div>
        </div>

        <div class="periodo-acciones">
          <button 
            class="btn btn-primario" 
            (click)="confirmarCierre(gestionActual)"
            *ngIf="gestionActual.estado === 'EXAMENES'"
          >
            Cerrar Gestión
          </button>
          <div class="info-automatica" *ngIf="gestionActual.estado === 'EN_CURSO'">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="12" cy="12" r="10"></circle>
              <line x1="12" y1="16" x2="12" y2="12"></line>
              <line x1="12" y1="8" x2="12.01" y2="8"></line>
            </svg>
            <span>El período de exámenes iniciará automáticamente el {{ gestionActual.fechaInicioExamenes | date:'dd/MM/yyyy' }}</span>
          </div>
        </div>
      </section>

      <!-- Línea de tiempo de estados -->
      <section class="timeline-estados card" *ngIf="gestionActual">
        <h3 class="card-titulo">Fases del Período Académico</h3>
        <div class="timeline">
          <div 
            *ngFor="let fase of fases" 
            class="timeline-fase"
            [class.fase-completada]="esFaseCompletada(fase.estado)"
            [class.fase-actual]="gestionActual.estado === fase.estado"
          >
            <div class="fase-icono">
              <svg *ngIf="esFaseCompletada(fase.estado)" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
                <polyline points="20 6 9 17 4 12"></polyline>
              </svg>
              <span *ngIf="!esFaseCompletada(fase.estado)">{{ fase.numero }}</span>
            </div>
            <span class="fase-nombre">{{ fase.nombre }}</span>
          </div>
        </div>
      </section>

      <!-- Historial de gestiones -->
      <section class="historial-gestiones">
        <h2>Historial de Gestiones</h2>
        <div class="tabla-contenedor card">
          <table class="tabla">
            <thead>
              <tr>
                <th>Gestión</th>
                <th>Período</th>
                <th>Estado</th>
                <th>Estudiantes</th>
                <th>Materias</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let gestion of gestiones">
                <td><strong>{{ gestion.nombre }}</strong></td>
                <td>
                  {{ gestion.fechaInicio | date:'dd/MM/yy' }} - {{ gestion.fechaFin | date:'dd/MM/yy' }}
                </td>
                <td>
                  <span class="estado-badge" [ngClass]="'estado-' + gestion.estado.toLowerCase()">
                    {{ formatearEstado(gestion.estado) }}
                  </span>
                </td>
                <td>{{ gestion.estudiantesInscritos }}</td>
                <td>{{ gestion.materiasActivas }}</td>
                <td>
                  <div class="acciones-celda">
                    <button class="btn-icono" title="Ver detalles" (click)="verDetalles(gestion)">
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path>
                        <circle cx="12" cy="12" r="3"></circle>
                      </svg>
                    </button>
                    <button class="btn-icono" title="Editar" (click)="editarGestion(gestion)" *ngIf="gestion.estado !== 'CERRADA'">
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                        <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                      </svg>
                    </button>
                    <button class="btn-icono" title="Generar Actas" (click)="generarActas(gestion)" *ngIf="gestion.estado === 'CERRADA'">
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
                        <polyline points="14 2 14 8 20 8"></polyline>
                        <line x1="16" y1="13" x2="8" y2="13"></line>
                        <line x1="16" y1="17" x2="8" y2="17"></line>
                        <polyline points="10 9 9 9 8 9"></polyline>
                      </svg>
                    </button>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </section>

      <!-- Modal Nueva Gestión -->
      <div class="modal-overlay" *ngIf="modalNuevaAbierto()" (click)="cerrarModal()">
        <div class="modal" (click)="$event.stopPropagation()">
          <div class="modal-header">
            <h3>Nueva Gestión Académica</h3>
            <button class="btn-cerrar" (click)="cerrarModal()">×</button>
          </div>
          <div class="modal-body">
            <!-- Información del período actual -->
            <div class="info-periodo-actual">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"></circle>
                <line x1="12" y1="16" x2="12" y2="12"></line>
                <line x1="12" y1="8" x2="12.01" y2="8"></line>
              </svg>
              <div>
                <p><strong>Estás en: {{ mesActualNombre }} {{ anioActual }}</strong></p>
                <p class="texto-secundario">{{ mesActualTexto }}</p>
              </div>
            </div>

            <div class="form-grupo">
              <label class="form-label">Tipo de Gestión *</label>
              <select class="form-select" [(ngModel)]="nuevaGestion.tipo" (change)="actualizarNombreAutomatico()">
                <option value="">Seleccionar tipo de gestión...</option>
                <option value="I" [disabled]="!puedeCrearGestionI()">
                  Gestión I (Febrero - Junio) {{ !puedeCrearGestionI() ? '- No disponible en este período' : '' }}
                </option>
                <option value="II" [disabled]="!puedeCrearGestionII()">
                  Gestión II (Agosto - Diciembre) {{ !puedeCrearGestionII() ? '- No disponible en este período' : '' }}
                </option>
              </select>
            </div>

            <!-- Vista previa de la gestión -->
            <div class="vista-previa-gestion" *ngIf="nuevaGestion.tipo">
              <div class="vista-previa-header">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path>
                  <polyline points="22 4 12 14.01 9 11.01"></polyline>
                </svg>
                <h4>{{ obtenerNombreAutomatico() }}</h4>
              </div>
              <div class="vista-previa-detalles">
                <div class="detalle-item">
                  <span class="detalle-label">Año:</span>
                  <span class="detalle-valor">{{ anioActual }}</span>
                </div>
                <div class="detalle-item">
                  <span class="detalle-label">Período:</span>
                  <span class="detalle-valor">{{ nuevaGestion.tipo === 'I' ? 'Febrero - Junio' : 'Agosto - Diciembre' }}</span>
                </div>
                <div class="detalle-item">
                  <span class="detalle-label">Fecha Inicio:</span>
                  <span class="detalle-valor">{{ obtenerFechaInicio() | date:'dd MMMM yyyy' }}</span>
                </div>
                <div class="detalle-item">
                  <span class="detalle-label">Fecha Fin:</span>
                  <span class="detalle-valor">{{ obtenerFechaFin() | date:'dd MMMM yyyy' }}</span>
                </div>
                <div class="detalle-item">
                  <span class="detalle-label">Inicio Exámenes:</span>
                  <span class="detalle-valor">{{ obtenerFechaInicioExamenes() | date:'dd MMMM yyyy' }}</span>
                </div>
                <div class="detalle-item">
                  <span class="detalle-label">Duración:</span>
                  <span class="detalle-valor">5 meses</span>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button class="btn btn-fantasma" (click)="cerrarModal()">Cancelar</button>
            <button class="btn btn-primario" (click)="crearGestion()">Crear Gestión</button>
          </div>
        </div>
      </div>

      <!-- Modal Confirmación Cierre -->
      <div class="modal-overlay" *ngIf="modalCierreAbierto()" (click)="cerrarModalCierre()">
        <div class="modal modal-confirmacion" (click)="$event.stopPropagation()">
          <div class="modal-header modal-header-advertencia">
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M10.29 3.86L1.82 18a2 2 0 0 0 1.71 3h16.94a2 2 0 0 0 1.71-3L13.71 3.86a2 2 0 0 0-3.42 0z"></path>
              <line x1="12" y1="9" x2="12" y2="13"></line>
              <line x1="12" y1="17" x2="12.01" y2="17"></line>
            </svg>
          </div>
          <div class="modal-body text-center">
            <h3>¿Cerrar la gestión {{ gestionACerrar?.nombre }}?</h3>
            <p class="texto-secundario">
              Esta acción no se puede deshacer. Al cerrar la gestión:
            </p>
            <ul class="lista-consecuencias">
              <li>Se generarán las actas finales</li>
              <li>Se calcularán los promedios finales</li>
              <li>Se actualizará el historial académico de los estudiantes</li>
              <li>Se enviarán alertas de reprobación (si corresponde)</li>
            </ul>
          </div>
          <div class="modal-footer">
            <button class="btn btn-fantasma" (click)="cerrarModalCierre()">Cancelar</button>
            <button class="btn btn-error" (click)="ejecutarCierre()">
              Sí, Cerrar Gestión
            </button>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .gestiones-page {
      max-width: var(--ancho-maximo);
      margin: 0 auto;
    }

    .pagina-header {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
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

    /* Período actual */
    .periodo-actual {
      padding: var(--espaciado-xl);
      margin-bottom: var(--espaciado-lg);
      background: linear-gradient(135deg, var(--color-primario) 0%, #4338ca 100%);
      color: white;
    }

    .periodo-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: var(--espaciado-lg);
    }

    .periodo-badge {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      font-size: var(--texto-sm);
      font-weight: 500;
      opacity: 0.9;
    }

    .estado-badge {
      padding: var(--espaciado-xs) var(--espaciado-sm);
      border-radius: var(--radio-full);
      font-size: var(--texto-xs);
      font-weight: 600;
      text-transform: uppercase;
    }

    .periodo-actual .estado-badge {
      background-color: rgba(255, 255, 255, 0.2);
      color: white;
    }

    .estado-planificacion { background-color: var(--color-info-light); color: var(--color-info); }
    .estado-inscripcion { background-color: var(--color-advertencia-light); color: var(--color-advertencia); }
    .estado-en_curso { background-color: var(--color-exito-light); color: var(--color-exito); }
    .estado-examenes { background-color: var(--color-error-light); color: var(--color-error); }
    .estado-cerrada { background-color: var(--color-fondo); color: var(--color-texto-secundario); }

    .periodo-info {
      margin-bottom: var(--espaciado-lg);
    }

    .periodo-info h2 {
      font-size: var(--texto-3xl);
      margin-bottom: var(--espaciado-xs);
    }

    .periodo-fechas {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      opacity: 0.9;
    }

    .separador {
      opacity: 0.5;
    }

    .periodo-stats {
      display: flex;
      gap: var(--espaciado-xl);
      margin-bottom: var(--espaciado-lg);
      padding: var(--espaciado-lg);
      background-color: rgba(255, 255, 255, 0.1);
      border-radius: var(--radio-md);
    }

    .stat {
      text-align: center;
    }

    .stat-valor {
      display: block;
      font-size: var(--texto-2xl);
      font-weight: 700;
    }

    .stat-label {
      font-size: var(--texto-sm);
      opacity: 0.9;
    }

    .periodo-acciones {
      display: flex;
      gap: var(--espaciado-md);
    }

    .info-automatica {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      padding: var(--espaciado-md);
      background-color: rgba(255, 255, 255, 0.15);
      border-radius: var(--radio-md);
      font-size: var(--texto-sm);
    }

    .info-fechas {
      display: flex;
      gap: var(--espaciado-md);
      padding: var(--espaciado-md);
      background-color: var(--color-info-light);
      border-radius: var(--radio-md);
      border: 1px solid var(--color-info);
    }

    .info-fechas p {
      margin: var(--espaciado-xs) 0;
      font-size: var(--texto-sm);
    }

    .info-fechas strong {
      color: var(--color-info);
    }

    /* Información del período actual */
    .info-periodo-actual {
      display: flex;
      gap: var(--espaciado-md);
      padding: var(--espaciado-md);
      background-color: var(--color-info-light);
      border-radius: var(--radio-md);
      border-left: 4px solid var(--color-info);
      margin-bottom: var(--espaciado-lg);
    }

    .info-periodo-actual svg {
      flex-shrink: 0;
      color: var(--color-info);
    }

    .info-periodo-actual p {
      margin: var(--espaciado-xs) 0;
      font-size: var(--texto-sm);
    }

    /* Vista previa de la gestión */
    .vista-previa-gestion {
      margin-top: var(--espaciado-lg);
      padding: var(--espaciado-lg);
      background: linear-gradient(135deg, var(--color-primario-light), var(--color-info-light));
      border-radius: var(--radio-lg);
      border: 2px solid var(--color-primario);
    }

    .vista-previa-header {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
      margin-bottom: var(--espaciado-md);
      padding-bottom: var(--espaciado-md);
      border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    }

    .vista-previa-header svg {
      color: var(--color-exito);
    }

    .vista-previa-header h4 {
      margin: 0;
      font-size: var(--texto-xl);
      color: var(--color-primario);
    }

    .vista-previa-detalles {
      display: grid;
      gap: var(--espaciado-sm);
    }

    .detalle-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: var(--espaciado-sm);
      background-color: white;
      border-radius: var(--radio-sm);
    }

    .detalle-label {
      font-weight: 600;
      color: var(--color-texto-secundario);
      font-size: var(--texto-sm);
    }

    .detalle-valor {
      font-weight: 500;
      color: var(--color-texto);
      font-size: var(--texto-sm);
    }

    .periodo-actual .btn {
      border-color: white;
    }

    .periodo-actual .btn-secundario {
      background-color: rgba(255, 255, 255, 0.1);
      color: white;
    }

    .periodo-actual .btn-secundario:hover {
      background-color: rgba(255, 255, 255, 0.2);
    }

    .periodo-actual .btn-primario {
      background-color: white;
      color: var(--color-primario);
    }

    /* Timeline */
    .timeline-estados {
      padding: var(--espaciado-lg);
      margin-bottom: var(--espaciado-xl);
    }

    .timeline {
      display: flex;
      justify-content: space-between;
      position: relative;
      margin-top: var(--espaciado-lg);
    }

    .timeline::before {
      content: '';
      position: absolute;
      top: 16px;
      left: 32px;
      right: 32px;
      height: 2px;
      background-color: var(--color-borde);
    }

    .timeline-fase {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: var(--espaciado-sm);
      position: relative;
      z-index: 1;
    }

    .fase-icono {
      width: 32px;
      height: 32px;
      border-radius: var(--radio-full);
      background-color: var(--color-fondo);
      border: 2px solid var(--color-borde);
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: var(--texto-sm);
      font-weight: 600;
      color: var(--color-texto-secundario);
    }

    .fase-completada .fase-icono {
      background-color: var(--color-exito);
      border-color: var(--color-exito);
      color: white;
    }

    .fase-actual .fase-icono {
      background-color: var(--color-primario);
      border-color: var(--color-primario);
      color: white;
    }

    .fase-nombre {
      font-size: var(--texto-xs);
      color: var(--color-texto-secundario);
      text-align: center;
      white-space: nowrap;
    }

    .fase-actual .fase-nombre {
      color: var(--color-primario);
      font-weight: 600;
    }

    /* Historial */
    .historial-gestiones h2 {
      font-size: var(--texto-xl);
      margin-bottom: var(--espaciado-md);
    }

    .acciones-celda {
      display: flex;
      gap: var(--espaciado-xs);
    }

    .btn-icono {
      padding: var(--espaciado-xs);
      border: none;
      background: none;
      color: var(--color-texto-secundario);
      cursor: pointer;
      border-radius: var(--radio-sm);
      transition: all var(--transicion-rapida);
    }

    .btn-icono:hover {
      background-color: var(--color-fondo);
      color: var(--color-primario);
    }

    /* Modal */
    .form-row {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: var(--espaciado-md);
    }

    .modal-confirmacion {
      max-width: 420px;
    }

    .modal-header-advertencia {
      justify-content: center;
      padding: var(--espaciado-xl);
      background-color: var(--color-advertencia-light);
      color: var(--color-advertencia);
      border-bottom: none;
    }

    .modal-confirmacion .modal-body {
      text-align: center;
    }

    .modal-confirmacion .modal-body h3 {
      margin-bottom: var(--espaciado-sm);
    }

    .lista-consecuencias {
      text-align: left;
      margin: var(--espaciado-md) 0;
      padding-left: var(--espaciado-lg);
    }

    .lista-consecuencias li {
      margin-bottom: var(--espaciado-xs);
      color: var(--color-texto-secundario);
    }

    .form-ayuda {
      display: block;
      margin-top: var(--espaciado-xs);
      font-size: var(--texto-xs);
      color: var(--color-primario);
      font-weight: 500;
    }

    @media (max-width: 768px) {
      .pagina-header {
        flex-direction: column;
        gap: var(--espaciado-md);
      }

      .periodo-stats {
        flex-direction: column;
        gap: var(--espaciado-md);
      }

      .timeline {
        flex-direction: column;
        gap: var(--espaciado-lg);
      }

      .timeline::before {
        top: 16px;
        left: 15px;
        width: 2px;
        height: calc(100% - 32px);
        right: auto;
      }

      .timeline-fase {
        flex-direction: row;
        gap: var(--espaciado-md);
      }

      .form-row {
        grid-template-columns: 1fr;
      }
    }
  `]
})
export class GestionesComponent {
  modalNuevaAbierto = signal(false);
  modalCierreAbierto = signal(false);
  gestionACerrar: Gestion | null = null;

  // Solo necesitamos el tipo, nombre y año se generan automáticamente
  nuevaGestion = {
    tipo: '' as 'I' | 'II' | ''
  };

  // Propiedades para mostrar información del período actual
  anioActual = new Date().getFullYear();
  mesActual = new Date().getMonth() + 1; // 1-12
  
  get mesActualNombre(): string {
    const meses = [
      'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
      'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
    ];
    return meses[this.mesActual - 1];
  }

  fases = [
    { numero: 1, estado: 'PLANIFICACION', nombre: 'Planificación' },
    { numero: 2, estado: 'INSCRIPCION', nombre: 'Inscripciones' },
    { numero: 3, estado: 'EN_CURSO', nombre: 'En Curso' },
    { numero: 4, estado: 'EXAMENES', nombre: 'Exámenes' },
    { numero: 5, estado: 'CERRADA', nombre: 'Cerrada' }
  ];

  // Obtener mes actual en texto para mostrar al usuario
  get mesActualTexto(): string {
    const mesActual = new Date().getMonth();
    const mesesNombres = [
      'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
      'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
    ];
    
    const mesNombre = mesesNombres[mesActual];
    
    if (mesActual >= 0 && mesActual <= 6) {
      return `Estamos en ${mesNombre}. Puedes crear la Gestión I (Febrero-Junio).`;
    } else {
      return `Estamos en ${mesNombre}. Puedes crear la Gestión II (Agosto-Diciembre).`;
    }
  }

  gestiones: Gestion[] = [
    {
      id: 1,
      nombre: 'II-2025',
      fechaInicio: new Date('2025-08-01'),
      fechaFin: new Date('2025-12-31'),
      fechaInicioExamenes: new Date('2025-12-01'), // 1 mes antes del fin
      estado: 'EN_CURSO',
      estudiantesInscritos: 1250,
      materiasActivas: 85,
      tipo: 'II'
    },
    {
      id: 2,
      nombre: 'I-2025',
      fechaInicio: new Date('2025-02-01'),
      fechaFin: new Date('2025-06-30'),
      fechaInicioExamenes: new Date('2025-06-01'), // 1 mes antes del fin
      estado: 'CERRADA',
      estudiantesInscritos: 1180,
      materiasActivas: 82,
      tipo: 'I'
    },
    {
      id: 3,
      nombre: 'II-2024',
      fechaInicio: new Date('2024-08-01'),
      fechaFin: new Date('2024-12-31'),
      fechaInicioExamenes: new Date('2024-12-01'),
      estado: 'CERRADA',
      estudiantesInscritos: 1150,
      materiasActivas: 80,
      tipo: 'II'
    }
  ];

  constructor(private notificacion: NotificacionService) {
    // Verificar automáticamente el estado de las gestiones (sin notificaciones al inicio)
    this.verificarEstadoAutomatico(false);
    
    // Verificar cada hora (con notificaciones)
    setInterval(() => this.verificarEstadoAutomatico(true), 3600000);
  }

  /**
   * Verifica y actualiza automáticamente el estado de las gestiones según la fecha actual
   * @param mostrarNotificaciones - Si es true, muestra notificaciones al cambiar el estado
   */
  verificarEstadoAutomatico(mostrarNotificaciones: boolean = false): void {
    const hoy = new Date();
    hoy.setHours(0, 0, 0, 0); // Normalizar a medianoche
    
    this.gestiones.forEach(gestion => {
      // Skip si ya está cerrada
      if (gestion.estado === 'CERRADA') return;
      
      // Guardamos el estado anterior para detectar cambios
      const estadoAnterior = gestion.estado;
      
      const fechaInicio = new Date(gestion.fechaInicio);
      const fechaFin = new Date(gestion.fechaFin);
      const fechaInicioExamenes = new Date(gestion.fechaInicioExamenes);
      
      fechaInicio.setHours(0, 0, 0, 0);
      fechaFin.setHours(0, 0, 0, 0);
      fechaInicioExamenes.setHours(0, 0, 0, 0);
      
      // Si ya pasó la fecha de fin, cerrar automáticamente
      if (hoy > fechaFin) {
        gestion.estado = 'CERRADA';
        // Solo notificar si realmente cambió el estado
        if (mostrarNotificaciones) {
          this.notificacion.info(`La gestión ${gestion.nombre} se cerró automáticamente`);
        }
      }
      // Si estamos en período de exámenes (1 mes antes del fin)
      else if (hoy >= fechaInicioExamenes && hoy <= fechaFin) {
        if (gestion.estado === 'EN_CURSO') {
          gestion.estado = 'EXAMENES';
          // Solo notificar si realmente cambió el estado
          if (mostrarNotificaciones) {
            this.notificacion.advertencia(`Período de exámenes iniciado para ${gestion.nombre}`);
          }
        }
      }
      // Si estamos en el período normal
      else if (hoy >= fechaInicio && hoy < fechaInicioExamenes) {
        if (gestion.estado !== 'EN_CURSO' && gestion.estado !== 'EXAMENES') {
          gestion.estado = 'EN_CURSO';
          // Solo notificar si realmente cambió el estado
          if (mostrarNotificaciones) {
            this.notificacion.info(`Período académico iniciado para ${gestion.nombre}`);
          }
        }
      }
    });
  }

  get gestionActual(): Gestion | undefined {
    return this.gestiones.find(g => g.estado !== 'CERRADA');
  }

  get diasRestantes(): number {
    if (!this.gestionActual) return 0;
    const hoy = new Date();
    const fin = new Date(this.gestionActual.fechaFin);
    const diff = fin.getTime() - hoy.getTime();
    return Math.max(0, Math.ceil(diff / (1000 * 60 * 60 * 24)));
  }

  /**
   * Calcula la fecha de inicio según el tipo de gestión
   */
  obtenerFechaInicio(): Date | null {
    if (!this.nuevaGestion.tipo) return null;
    
    if (this.nuevaGestion.tipo === 'I') {
      // Gestión I: 1 de febrero
      return new Date(this.anioActual, 1, 1); // Mes 1 = febrero (0-indexed)
    } else {
      // Gestión II: 1 de agosto
      return new Date(this.anioActual, 7, 1); // Mes 7 = agosto
    }
  }

  /**
   * Calcula la fecha de fin según el tipo de gestión
   */
  obtenerFechaFin(): Date | null {
    if (!this.nuevaGestion.tipo) return null;
    
    if (this.nuevaGestion.tipo === 'I') {
      // Gestión I: 30 de junio
      return new Date(this.anioActual, 5, 30); // Mes 5 = junio
    } else {
      // Gestión II: 31 de diciembre
      return new Date(this.anioActual, 11, 31); // Mes 11 = diciembre
    }
  }

  /**
   * Calcula la fecha de inicio de exámenes (1 mes antes del fin)
   */
  obtenerFechaInicioExamenes(): Date | null {
    if (!this.nuevaGestion.tipo) return null;
    
    if (this.nuevaGestion.tipo === 'I') {
      // Gestión I: exámenes inician el 1 de junio (30 días antes del 30 de junio)
      return new Date(this.anioActual, 5, 1);
    } else {
      // Gestión II: exámenes inician el 1 de diciembre (31 días antes del 31 de diciembre)
      return new Date(this.anioActual, 11, 1);
    }
  }

  /**
   * Genera el nombre automático de la gestión basado en el tipo y año actual
   */
  obtenerNombreAutomatico(): string {
    if (!this.nuevaGestion.tipo) return '';
    return `${this.nuevaGestion.tipo}-${this.anioActual}`;
  }

  /**
   * Actualiza el nombre automático cuando cambia el tipo
   */
  actualizarNombreAutomatico(): void {
    // No hace falta hacer nada, el nombre se calcula automáticamente con obtenerNombreAutomatico()
  }

  formatearEstado(estado: string): string {
    const estados: Record<string, string> = {
      'PLANIFICACION': 'Planificación',
      'INSCRIPCION': 'Inscripciones',
      'EN_CURSO': 'En Curso',
      'EXAMENES': 'Exámenes',
      'CERRADA': 'Cerrada'
    };
    return estados[estado] || estado;
  }

  esFaseCompletada(estado: string): boolean {
    if (!this.gestionActual) return false;
    const ordenFases = ['PLANIFICACION', 'INSCRIPCION', 'EN_CURSO', 'EXAMENES', 'CERRADA'];
    const indiceActual = ordenFases.indexOf(this.gestionActual.estado);
    const indiceFase = ordenFases.indexOf(estado);
    return indiceFase < indiceActual;
  }

  abrirModalNueva(): void {
    // Validar que no haya una gestión en curso
    const gestionEnCurso = this.gestiones.find(g => 
      g.estado === 'EN_CURSO' || 
      g.estado === 'EXAMENES' || 
      g.estado === 'INSCRIPCION' ||
      g.estado === 'PLANIFICACION'
    );

    if (gestionEnCurso) {
      this.notificacion.error(
        `No se puede crear una nueva gestión. La gestión ${gestionEnCurso.nombre} está ${this.formatearEstado(gestionEnCurso.estado).toLowerCase()}`
      );
      return;
    }

    this.modalNuevaAbierto.set(true);
  }

  cerrarModal(): void {
    this.modalNuevaAbierto.set(false);
    this.nuevaGestion = {
      tipo: ''
    };
  }

  crearGestion(): void {
    // Validar que se haya seleccionado un tipo
    if (!this.nuevaGestion.tipo) {
      this.notificacion.error('Selecciona el tipo de gestión');
      return;
    }

    // Validar que no haya una gestión activa
    if (this.tieneGestionActiva()) {
      this.notificacion.error('No puedes crear una nueva gestión mientras haya una gestión activa (EN_CURSO o EXAMENES)');
      return;
    }

    // Validar que se esté creando la gestión correcta según el mes actual
    const mesActual = new Date().getMonth(); // 0 = Enero, 1 = Febrero, etc.
    const anioActual = new Date().getFullYear();

    // Gestión I es válida de Enero a Julio (meses 0-6)
    // Gestión II es válida de Julio a Enero del siguiente año (meses 6-12 y 0)
    if (this.nuevaGestion.tipo === 'I') {
      // Gestión I solo se puede crear entre Enero (0) y Julio (6)
      if (mesActual < 0 || mesActual > 6) {
        this.notificacion.error(
          'No puedes crear una Gestión I (Febrero-Junio) en este período. ' +
          'La Gestión I solo puede crearse entre Enero y Julio.'
        );
        return;
      }
    } else if (this.nuevaGestion.tipo === 'II') {
      // Gestión II solo se puede crear entre Julio (6) y Diciembre (11)
      if (mesActual < 6) {
        this.notificacion.error(
          'No puedes crear una Gestión II (Agosto-Diciembre) en este período. ' +
          'La Gestión II solo puede crearse entre Julio y Diciembre.'
        );
        return;
      }
    }

    // Generar nombre automáticamente
    const nombreGestion = this.obtenerNombreAutomatico();
    
    const fechaInicio = this.obtenerFechaInicio();
    const fechaFin = this.obtenerFechaFin();
    const fechaInicioExamenes = this.obtenerFechaInicioExamenes();

    if (!fechaInicio || !fechaFin || !fechaInicioExamenes) {
      this.notificacion.error('Error al calcular las fechas');
      return;
    }

    // Verificar que no exista ya una gestión con el mismo nombre
    if (this.gestiones.some(g => g.nombre === nombreGestion)) {
      this.notificacion.error(`Ya existe la gestión ${nombreGestion}`);
      return;
    }

    const nuevaGestion: Gestion = {
      id: this.gestiones.length + 1,
      nombre: nombreGestion,
      fechaInicio,
      fechaFin,
      fechaInicioExamenes,
      estado: 'PLANIFICACION',
      estudiantesInscritos: 0,
      materiasActivas: 0,
      tipo: this.nuevaGestion.tipo
    };

    this.gestiones.unshift(nuevaGestion);
    this.notificacion.exito(`Gestión ${nuevaGestion.nombre} creada correctamente`);
    this.cerrarModal();
  }

  confirmarCierre(gestion: Gestion): void {
    this.gestionACerrar = gestion;
    this.modalCierreAbierto.set(true);
  }

  cerrarModalCierre(): void {
    this.modalCierreAbierto.set(false);
    this.gestionACerrar = null;
  }

  ejecutarCierre(): void {
    if (this.gestionACerrar) {
      this.gestionACerrar.estado = 'CERRADA';
      this.notificacion.exito('Gestión cerrada correctamente. Actas generadas.');
    }
    this.cerrarModalCierre();
  }

  verDetalles(gestion: Gestion): void {
    this.notificacion.info(`Ver detalles de ${gestion.nombre}`);
  }

  editarGestion(gestion: Gestion): void {
    this.notificacion.info(`Editar ${gestion.nombre}`);
  }

  generarActas(gestion: Gestion): void {
    this.notificacion.exito(`Generando actas de ${gestion.nombre}...`);
  }

  /**
   * Verifica si hay alguna gestión en curso o en exámenes (no cerrada)
   */
  tieneGestionActiva(): boolean {
    return this.gestiones.some(g => g.estado !== 'CERRADA');
  }

  /**
   * Verifica si se puede crear una Gestión I según el mes actual
   * Gestión I solo se puede crear entre Enero (0) y Julio (6)
   */
  puedeCrearGestionI(): boolean {
    const mesActual = new Date().getMonth();
    return mesActual >= 0 && mesActual <= 6;
  }

  /**
   * Verifica si se puede crear una Gestión II según el mes actual
   * Gestión II solo se puede crear entre Julio (6) y Diciembre (11)
   */
  puedeCrearGestionII(): boolean {
    const mesActual = new Date().getMonth();
    return mesActual >= 6 && mesActual <= 11;
  }
}
