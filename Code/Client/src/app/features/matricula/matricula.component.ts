/**
 * Página de Matrícula (Carrito)
 * Muestra las materias seleccionadas para inscripción
 * 
 * Heurística Nielsen #3: Control y libertad del usuario
 * - Permite modificar la selección antes de confirmar
 * 
 * Heurística Nielsen #5: Prevención de errores
 * - Muestra validaciones antes de confirmar
 */

import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { MatriculaService } from '../../core/services/matricula.service';
import { NotificacionService } from '../../core/services/notificacion.service';
import { Grupo } from '../../models';

@Component({
  selector: 'app-matricula',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="matricula-page">
      <!-- Encabezado -->
      <header class="pagina-header">
        <a routerLink="/oferta-academica" class="btn-volver">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="15 18 9 12 15 6"></polyline>
          </svg>
          Volver a oferta
        </a>
        <h1>Mi Matrícula</h1>
        <p>Revisa y confirma tu inscripción para la Gestión II-2025</p>
      </header>

      <div class="matricula-contenido" *ngIf="matriculaService.cantidadEnProceso() > 0; else estadoVacio">
        <div class="matricula-grid">
          <!-- Lista de materias -->
          <section class="materias-seleccionadas">
            <h2>Materias Seleccionadas ({{ matriculaService.cantidadEnProceso() }})</h2>
            
            <div class="materia-item card" *ngFor="let grupo of matriculaService.matriculasEnProceso$()">
              <div class="materia-info">
                <div class="materia-header">
                  <span class="materia-codigo">{{ grupo.materia.codigo }}</span>
                  <span class="grupo-codigo">Grupo {{ grupo.codigo }}</span>
                </div>
                <h3>{{ grupo.materia.nombre }}</h3>
                
                <div class="materia-detalles">
                  <span class="detalle">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                      <circle cx="12" cy="7" r="4"></circle>
                    </svg>
                    {{ grupo.docente.nombre }} {{ grupo.docente.apellido }}
                  </span>
                  <span class="detalle">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <circle cx="12" cy="12" r="10"></circle>
                      <polyline points="12 6 12 12 16 14"></polyline>
                    </svg>
                    {{ grupo.materia.creditos }} créditos
                  </span>
                </div>

                <div class="horarios">
                  <span class="horario" *ngFor="let h of grupo.horarios">
                    {{ formatearDia(h.dia) }} {{ h.horaInicio }}-{{ h.horaFin }}
                  </span>
                </div>
              </div>

              <button class="btn-remover" (click)="remover(grupo.id)" aria-label="Remover materia">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <polyline points="3 6 5 6 21 6"></polyline>
                  <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                </svg>
              </button>
            </div>
          </section>

          <!-- Resumen -->
          <aside class="resumen-matricula">
            <div class="resumen-card card">
              <div class="card-header">
                <h3 class="card-titulo">Resumen de Matrícula</h3>
              </div>
              <div class="card-body">
                <div class="resumen-linea">
                  <span>Materias</span>
                  <span>{{ matriculaService.cantidadEnProceso() }}</span>
                </div>
                <div class="resumen-linea">
                  <span>Créditos totales</span>
                  <span>{{ matriculaService.creditosTotales() }}</span>
                </div>
                <div class="resumen-linea resumen-gestion">
                  <span>Gestión</span>
                  <span>II-2025</span>
                </div>
                
                <div class="resumen-divider"></div>

                <div class="resumen-horario">
                  <h4>Vista previa de horario</h4>
                  <div class="horario-preview">
                    <div class="dia-column" *ngFor="let dia of diasSemana">
                      <span class="dia-nombre">{{ dia.corto }}</span>
                      <div 
                        class="horario-bloque" 
                        *ngFor="let bloque of obtenerBloquesDia(dia.largo)"
                        [style.background-color]="bloque.color"
                      >
                        <span class="bloque-hora">{{ bloque.horaInicio }}</span>
                        <span class="bloque-materia">{{ bloque.codigo }}</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="card-footer">
                <button 
                  class="btn btn-primario btn-lg w-full"
                  [disabled]="procesando()"
                  (click)="confirmarMatricula()"
                >
                  <span class="spinner spinner-sm" *ngIf="procesando()"></span>
                  {{ procesando() ? 'Procesando...' : 'Confirmar Matrícula' }}
                </button>
                <button 
                  class="btn btn-ghost w-full mt-sm"
                  (click)="limpiarTodo()"
                >
                  Limpiar selección
                </button>
              </div>
            </div>

            <!-- Información adicional -->
            <div class="info-card card">
              <div class="card-body">
                <div class="info-item">
                  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <circle cx="12" cy="12" r="10"></circle>
                    <line x1="12" y1="16" x2="12" y2="12"></line>
                    <line x1="12" y1="8" x2="12.01" y2="8"></line>
                  </svg>
                  <p>Podrás modificar tu matrícula hasta el <strong>30 de julio</strong></p>
                </div>
                <div class="info-item">
                  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path>
                  </svg>
                  <p>Tu información está protegida y segura</p>
                </div>
              </div>
            </div>
          </aside>
        </div>
      </div>

      <!-- Estado vacío -->
      <ng-template #estadoVacio>
        <div class="estado-vacio-container">
          <div class="estado-vacio">
            <div class="estado-vacio-icono">📋</div>
            <h2>No tienes materias seleccionadas</h2>
            <p>Explora la oferta académica y selecciona las materias que deseas cursar</p>
            <a routerLink="/oferta-academica" class="btn btn-primario btn-lg">
              Ver Oferta Académica
            </a>
          </div>
        </div>
      </ng-template>

      <!-- Modal de confirmación -->
      <div class="modal-overlay" *ngIf="mostrarConfirmacion()">
        <div class="modal">
          <div class="modal-header">
            <h3 class="modal-titulo">¡Matrícula Exitosa!</h3>
          </div>
          <div class="modal-body texto-centro">
            <div class="exito-icono">✅</div>
            <p>Te has inscrito exitosamente en {{ matriculasConfirmadas }} materia(s)</p>
            <p class="texto-secundario">Puedes ver tu horario y materias inscritas en el dashboard</p>
          </div>
          <div class="modal-footer">
            <button class="btn btn-primario w-full" (click)="irAlDashboard()">
              Ir al Dashboard
            </button>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .matricula-page {
      max-width: var(--ancho-maximo);
      margin: 0 auto;
    }

    .pagina-header {
      margin-bottom: var(--espaciado-xl);
    }

    .btn-volver {
      display: inline-flex;
      align-items: center;
      gap: var(--espaciado-xs);
      color: var(--color-texto-secundario);
      margin-bottom: var(--espaciado-md);
      font-size: var(--texto-sm);
    }

    .btn-volver:hover {
      color: var(--color-primario);
    }

    .pagina-header h1 {
      font-size: var(--texto-2xl);
      margin-bottom: var(--espaciado-xs);
    }

    .pagina-header p {
      color: var(--color-texto-secundario);
      margin: 0;
    }

    .matricula-grid {
      display: grid;
      grid-template-columns: 1fr 380px;
      gap: var(--espaciado-xl);
      align-items: start;
    }

    /* Materias seleccionadas */
    .materias-seleccionadas h2 {
      font-size: var(--texto-lg);
      margin-bottom: var(--espaciado-md);
    }

    .materia-item {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      padding: var(--espaciado-lg);
      margin-bottom: var(--espaciado-md);
    }

    .materia-header {
      display: flex;
      gap: var(--espaciado-sm);
      margin-bottom: var(--espaciado-xs);
    }

    .materia-codigo {
      font-size: var(--texto-sm);
      font-weight: 600;
      color: var(--color-primario);
    }

    .grupo-codigo {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .materia-info h3 {
      font-size: var(--texto-base);
      margin-bottom: var(--espaciado-sm);
    }

    .materia-detalles {
      display: flex;
      gap: var(--espaciado-lg);
      margin-bottom: var(--espaciado-sm);
    }

    .detalle {
      display: flex;
      align-items: center;
      gap: 4px;
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .horarios {
      display: flex;
      gap: var(--espaciado-sm);
      flex-wrap: wrap;
    }

    .horario {
      font-size: var(--texto-xs);
      padding: 2px var(--espaciado-sm);
      background-color: var(--color-fondo);
      border-radius: var(--radio-sm);
    }

    .btn-remover {
      background: transparent;
      border: none;
      color: var(--color-texto-claro);
      cursor: pointer;
      padding: var(--espaciado-sm);
      border-radius: var(--radio-md);
      transition: all var(--transicion-rapida);
    }

    .btn-remover:hover {
      background-color: var(--color-error-light);
      color: var(--color-error);
    }

    /* Resumen */
    .resumen-matricula {
      position: sticky;
      top: calc(var(--altura-header) + var(--espaciado-lg));
    }

    .resumen-card {
      margin-bottom: var(--espaciado-md);
    }

    .resumen-linea {
      display: flex;
      justify-content: space-between;
      padding: var(--espaciado-sm) 0;
      font-size: var(--texto-sm);
    }

    .resumen-gestion {
      font-weight: 600;
    }

    .resumen-divider {
      height: 1px;
      background-color: var(--color-borde);
      margin: var(--espaciado-md) 0;
    }

    .resumen-horario h4 {
      font-size: var(--texto-sm);
      margin-bottom: var(--espaciado-sm);
    }

    .horario-preview {
      display: flex;
      gap: 2px;
      background-color: var(--color-fondo);
      border-radius: var(--radio-md);
      padding: var(--espaciado-sm);
    }

    .dia-column {
      flex: 1;
      display: flex;
      flex-direction: column;
      gap: 2px;
      min-width: 0;
    }

    .dia-nombre {
      font-size: var(--texto-xs);
      font-weight: 600;
      text-align: center;
      color: var(--color-texto-secundario);
      padding: 4px;
    }

    .horario-bloque {
      padding: 4px;
      border-radius: var(--radio-sm);
      text-align: center;
    }

    .bloque-hora {
      display: block;
      font-size: 0.65rem;
      opacity: 0.8;
    }

    .bloque-materia {
      display: block;
      font-size: var(--texto-xs);
      font-weight: 600;
      color: white;
    }

    .info-card {
      background-color: var(--color-info-light);
      border-color: var(--color-info);
    }

    .info-item {
      display: flex;
      gap: var(--espaciado-sm);
      margin-bottom: var(--espaciado-sm);
      color: #1e40af;
    }

    .info-item:last-child {
      margin-bottom: 0;
    }

    .info-item p {
      font-size: var(--texto-sm);
      margin: 0;
      color: inherit;
    }

    /* Estado vacío */
    .estado-vacio-container {
      display: flex;
      justify-content: center;
      padding: var(--espaciado-2xl);
    }

    .estado-vacio {
      max-width: 400px;
    }

    .estado-vacio h2 {
      font-size: var(--texto-xl);
      margin-bottom: var(--espaciado-sm);
    }

    /* Modal */
    .exito-icono {
      font-size: 4rem;
      margin-bottom: var(--espaciado-md);
    }

    @media (max-width: 1024px) {
      .matricula-grid {
        grid-template-columns: 1fr;
      }

      .resumen-matricula {
        position: static;
      }
    }
  `]
})
export class MatriculaComponent {
  procesando = signal(false);
  mostrarConfirmacion = signal(false);
  matriculasConfirmadas = 0;

  diasSemana = [
    { corto: 'Lun', largo: 'LUNES' },
    { corto: 'Mar', largo: 'MARTES' },
    { corto: 'Mié', largo: 'MIERCOLES' },
    { corto: 'Jue', largo: 'JUEVES' },
    { corto: 'Vie', largo: 'VIERNES' }
  ];

  colores = ['#3b82f6', '#10b981', '#f59e0b', '#ef4444', '#8b5cf6', '#ec4899'];

  constructor(
    public matriculaService: MatriculaService,
    private notificacion: NotificacionService,
    private router: Router
  ) {}

  remover(grupoId: number): void {
    this.matriculaService.removerDelCarrito(grupoId);
    this.notificacion.info('Materia removida');
  }

  limpiarTodo(): void {
    this.matriculaService.limpiarCarrito();
    this.notificacion.info('Selección limpiada');
  }

  async confirmarMatricula(): Promise<void> {
    this.procesando.set(true);

    try {
      const resultado = await this.matriculaService.confirmarMatricula();

      if (resultado.exito) {
        this.matriculasConfirmadas = resultado.matriculas?.length ?? 0;
        this.mostrarConfirmacion.set(true);
      } else {
        this.notificacion.error(resultado.mensaje);
      }
    } catch (error) {
      this.notificacion.error('Error al procesar la matrícula');
    } finally {
      this.procesando.set(false);
    }
  }

  irAlDashboard(): void {
    this.mostrarConfirmacion.set(false);
    this.router.navigate(['/dashboard']);
  }

  formatearDia(dia: string): string {
    const dias: Record<string, string> = {
      'LUNES': 'Lun',
      'MARTES': 'Mar',
      'MIERCOLES': 'Mié',
      'JUEVES': 'Jue',
      'VIERNES': 'Vie',
      'SABADO': 'Sáb'
    };
    return dias[dia] || dia;
  }

  obtenerBloquesDia(dia: string): { horaInicio: string; codigo: string; color: string }[] {
    const bloques: { horaInicio: string; codigo: string; color: string }[] = [];
    
    this.matriculaService.matriculasEnProceso$().forEach((grupo, index) => {
      grupo.horarios.forEach(horario => {
        if (horario.dia === dia) {
          bloques.push({
            horaInicio: horario.horaInicio,
            codigo: grupo.materia.codigo.split('-')[0],
            color: this.colores[index % this.colores.length]
          });
        }
      });
    });

    return bloques.sort((a, b) => a.horaInicio.localeCompare(b.horaInicio));
  }
}
