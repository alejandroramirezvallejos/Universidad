/**
 * Página de Historial Académico
 * Muestra el historial completo del estudiante
 * 
 * Heurística Nielsen #6: Reconocimiento antes que recuerdo
 * - Toda la información académica en un solo lugar
 * 
 * Heurística Nielsen #8: Diseño estético y minimalista
 * - Información organizada de forma clara
 */

import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import { CalificacionesService } from '../../core/services/calificaciones.service';
import { NotificacionService } from '../../core/services/notificacion.service';
import { OfertaAcademicaService } from '../../core/services/oferta-academica.service';

interface GestionHistorial {
  id: number;
  nombre: string;
  estado: 'CERRADA' | 'EN_CURSO';
  materias: MateriaHistorial[];
  promedio: number;
  creditos: number;
}

interface MateriaHistorial {
  codigo: string;
  nombre: string;
  creditos: number;
  nota: number;
  estado: 'APROBADO' | 'REPROBADO' | 'EN_CURSO';
}

@Component({
  selector: 'app-historial',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="historial-page">
      <header class="pagina-header">
        <h1>Historial Académico</h1>
        <p>{{ authService.nombreCompleto() }}</p>
      </header>

      <!-- Resumen general -->
      <section class="resumen-general card">
        <div class="resumen-grid">
          <div class="resumen-item">
            <span class="resumen-valor">{{ promedioGeneral() }}</span>
            <span class="resumen-label">Promedio General</span>
          </div>
          <div class="resumen-item">
            <span class="resumen-valor">{{ creditosAprobados() }}/{{ creditosTotales }}</span>
            <span class="resumen-label">Créditos Aprobados</span>
          </div>
          <div class="resumen-item">
            <span class="resumen-valor">{{ materiasAprobadas() }}</span>
            <span class="resumen-label">Materias Aprobadas</span>
          </div>
          <div class="resumen-item">
            <span class="resumen-valor">{{ avanceCarrera() }}%</span>
            <span class="resumen-label">Avance de Carrera</span>
          </div>
        </div>
        
        <!-- Barra de progreso de carrera -->
        <div class="progreso-carrera">
          <div class="progreso-label">
            <span>Progreso de la carrera</span>
            <span>{{ avanceCarrera() }}% completado</span>
          </div>
          <div class="progreso-barra">
            <div class="progreso-relleno" [style.width.%]="avanceCarrera()"></div>
          </div>
        </div>
      </section>

      <!-- Historial por gestión -->
      <section class="historial-gestiones">
        <div *ngFor="let gestion of gestiones()" class="gestion-card card">
          <div class="gestion-header" (click)="toggleGestion(gestion.id)">
            <div class="gestion-info">
              <h3>
                {{ gestion.nombre }}
                <span class="gestion-estado" [class.estado-cerrada]="gestion.estado === 'CERRADA'" [class.estado-curso]="gestion.estado === 'EN_CURSO'">
                  {{ gestion.estado === 'CERRADA' ? 'Finalizada' : 'En Curso' }}
                </span>
              </h3>
              <div class="gestion-stats">
                <span>{{ gestion.materias.length }} materias</span>
                <span>•</span>
                <span>{{ gestion.creditos }} créditos</span>
                <span>•</span>
                <span>Promedio: {{ gestion.promedio.toFixed(1) }}</span>
              </div>
            </div>
            <svg 
              class="icono-expandir"
              [class.rotado]="gestionExpandida() === gestion.id"
              xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
            >
              <polyline points="6 9 12 15 18 9"></polyline>
            </svg>
          </div>

          <div class="gestion-materias" *ngIf="gestionExpandida() === gestion.id">
            <table class="tabla">
              <thead>
                <tr>
                  <th>Código</th>
                  <th>Materia</th>
                  <th>Créditos</th>
                  <th>Nota</th>
                  <th>Estado</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let materia of gestion.materias">
                  <td>{{ materia.codigo }}</td>
                  <td>{{ materia.nombre }}</td>
                  <td>{{ materia.creditos }}</td>
                  <td>
                    <span 
                      class="nota"
                      [class.nota-aprobada]="materia.nota >= 51"
                      [class.nota-reprobada]="materia.nota < 51 && materia.nota > 0"
                    >
                      {{ materia.estado === 'EN_CURSO' ? '-' : materia.nota }}
                    </span>
                  </td>
                  <td>
                    <span 
                      class="badge"
                      [class.badge-exito]="materia.estado === 'APROBADO'"
                      [class.badge-error]="materia.estado === 'REPROBADO'"
                      [class.badge-info]="materia.estado === 'EN_CURSO'"
                    >
                      {{ formatearEstado(materia.estado) }}
                    </span>
                  </td>
                </tr>
              </tbody>
            </table>

            <div class="gestion-resumen">
              <span>Promedio del período: <strong>{{ gestion.promedio.toFixed(1) }}</strong></span>
              <span>Créditos aprobados: <strong>{{ calcularCreditosAprobados(gestion) }}</strong></span>
            </div>
          </div>
        </div>
      </section>

      <!-- Mensaje si no hay historial -->
      <section class="sin-datos card" *ngIf="gestiones().length === 0">
        <div class="sin-datos-contenido">
          <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="10"></circle>
            <polyline points="12 6 12 12 16 14"></polyline>
          </svg>
          <p>No tienes historial académico registrado</p>
        </div>
      </section>

      <!-- Acciones -->
      <section class="acciones-historial" *ngIf="gestiones().length > 0">
        <button class="btn btn-secundario" (click)="descargarPDF()">
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path>
            <polyline points="7 10 12 15 17 10"></polyline>
            <line x1="12" y1="15" x2="12" y2="3"></line>
          </svg>
          Descargar PDF
        </button>
        <button class="btn btn-secundario" (click)="solicitarCertificado()">
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect>
            <path d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path>
          </svg>
          Solicitar Certificado
        </button>
      </section>
    </div>
  `,
  styles: [`
    .historial-page {
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

    /* Resumen general */
    .resumen-general {
      padding: var(--espaciado-xl);
      margin-bottom: var(--espaciado-xl);
    }

    .resumen-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
      gap: var(--espaciado-lg);
      margin-bottom: var(--espaciado-xl);
    }

    .resumen-item {
      text-align: center;
    }

    .resumen-valor {
      display: block;
      font-size: var(--texto-3xl);
      font-weight: 700;
      color: var(--color-primario);
      margin-bottom: var(--espaciado-xs);
    }

    .resumen-label {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    /* Progreso de carrera */
    .progreso-carrera {
      padding-top: var(--espaciado-lg);
      border-top: 1px solid var(--color-borde);
    }

    .progreso-label {
      display: flex;
      justify-content: space-between;
      margin-bottom: var(--espaciado-sm);
      font-size: var(--texto-sm);
    }

    .progreso-barra {
      height: 12px;
      background-color: var(--color-fondo);
      border-radius: var(--radio-full);
      overflow: hidden;
    }

    .progreso-relleno {
      height: 100%;
      background: linear-gradient(90deg, var(--color-primario), var(--color-exito));
      border-radius: var(--radio-full);
      transition: width var(--transicion-lenta);
    }

    /* Gestiones */
    .historial-gestiones {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-md);
      margin-bottom: var(--espaciado-xl);
    }

    .gestion-card {
      overflow: hidden;
    }

    .gestion-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: var(--espaciado-lg);
      cursor: pointer;
      transition: background-color var(--transicion-rapida);
    }

    .gestion-header:hover {
      background-color: var(--color-fondo);
    }

    .gestion-info h3 {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      margin-bottom: var(--espaciado-xs);
    }

    .gestion-estado {
      font-size: var(--texto-xs);
      font-weight: 500;
      padding: 2px var(--espaciado-sm);
      border-radius: var(--radio-sm);
    }

    .gestion-estado.estado-cerrada {
      background-color: var(--color-exito-light);
      color: var(--color-exito);
    }

    .gestion-estado.estado-curso {
      background-color: var(--color-info-light);
      color: var(--color-info);
    }

    .gestion-stats {
      display: flex;
      gap: var(--espaciado-sm);
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .icono-expandir {
      transition: transform var(--transicion-rapida);
    }

    .icono-expandir.rotado {
      transform: rotate(180deg);
    }

    .gestion-materias {
      padding: var(--espaciado-lg);
      background-color: var(--color-fondo);
      border-top: 1px solid var(--color-borde);
      animation: slideDown var(--transicion-normal);
    }

    @keyframes slideDown {
      from { opacity: 0; transform: translateY(-10px); }
      to { opacity: 1; transform: translateY(0); }
    }

    .nota {
      font-weight: 600;
    }

    .nota-aprobada {
      color: var(--color-exito);
    }

    .nota-reprobada {
      color: var(--color-error);
    }

    .gestion-resumen {
      display: flex;
      justify-content: space-between;
      margin-top: var(--espaciado-md);
      padding-top: var(--espaciado-md);
      border-top: 1px solid var(--color-borde);
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

    /* Acciones */
    .acciones-historial {
      display: flex;
      gap: var(--espaciado-md);
      justify-content: center;
    }

    @media (max-width: 768px) {
      .resumen-grid {
        grid-template-columns: repeat(2, 1fr);
      }

      .gestion-stats {
        flex-wrap: wrap;
      }

      .acciones-historial {
        flex-direction: column;
      }

      .acciones-historial .btn {
        width: 100%;
      }
    }
  `]
})
export class HistorialComponent implements OnInit {
  gestionExpandida = signal<number | null>(1);
  private _gestiones = signal<GestionHistorial[]>([]);
  readonly creditosTotales = 240; // Total de créditos de la carrera

  constructor(
    public authService: AuthService,
    private calificacionesService: CalificacionesService,
    private notificacion: NotificacionService,
    private ofertaService: OfertaAcademicaService
  ) {}

  async ngOnInit(): Promise<void> {
    await this.cargarHistorial();
  }

  async cargarHistorial(): Promise<void> {
    try {
      // NUEVA INTEGRACIÓN: Obtener historial académico desde backend
      const historialBackend = await this.calificacionesService.obtenerHistorialAcademicoBackend();
      
      if (historialBackend.length > 0) {
        // Usar datos del backend
        const gestionesMap = new Map<number, GestionHistorial>();
        
        historialBackend.forEach(item => {
          if (!gestionesMap.has(item.gestionId)) {
            gestionesMap.set(item.gestionId, {
              id: item.gestionId,
              nombre: item.gestionNombre,
              estado: item.gestionEstado as 'CERRADA' | 'EN_CURSO',
              materias: [],
              promedio: 0,
              creditos: 0
            });
          }
          
          const gestion = gestionesMap.get(item.gestionId)!;
          gestion.materias.push({
            codigo: item.materiaCodigo,
            nombre: item.materiaNombre,
            creditos: item.creditos,
            nota: item.notaFinal,
            estado: item.estado as 'APROBADO' | 'REPROBADO' | 'EN_CURSO'
          });
          gestion.creditos += item.creditos;
        });

        // Calcular promedio por gestión
        gestionesMap.forEach(gestion => {
          const materiasConNota = gestion.materias.filter(m => m.estado !== 'EN_CURSO');
          if (materiasConNota.length > 0) {
            gestion.promedio = materiasConNota.reduce((sum, m) => sum + m.nota, 0) / materiasConNota.length;
          }
        });

        const gestionesArray = Array.from(gestionesMap.values()).sort((a, b) => b.id - a.id);
        this._gestiones.set(gestionesArray);
      } else {
        // Fallback a datos mock
        this.cargarHistorialMock();
      }
    } catch (error) {
      console.error('Error cargando historial backend:', error);
      // Fallback a datos mock
      this.cargarHistorialMock();
    }
  }

  cargarHistorialMock(): void {
    // Obtener historial del estudiante actual (mock)
    const historial = this.calificacionesService.obtenerHistorialAcademico();
    
    // Agrupar por gestión
    const gestionesMap = new Map<number, GestionHistorial>();
    
    historial.forEach(item => {
      if (!gestionesMap.has(item.gestionId)) {
        gestionesMap.set(item.gestionId, {
          id: item.gestionId,
          nombre: item.gestionNombre,
          estado: item.gestionEstado as 'CERRADA' | 'EN_CURSO',
          materias: [],
          promedio: 0,
          creditos: 0
        });
      }
      
      const gestion = gestionesMap.get(item.gestionId)!;
      gestion.materias.push({
        codigo: item.materiaCodigo,
        nombre: item.materiaNombre,
        creditos: item.creditos,
        nota: item.notaFinal,
        estado: item.estado as 'APROBADO' | 'REPROBADO' | 'EN_CURSO'
      });
      gestion.creditos += item.creditos;
    });

    // Calcular promedio por gestión
    gestionesMap.forEach(gestion => {
      const materiasConNota = gestion.materias.filter(m => m.estado !== 'EN_CURSO');
      if (materiasConNota.length > 0) {
        gestion.promedio = materiasConNota.reduce((sum, m) => sum + m.nota, 0) / materiasConNota.length;
      }
    });

    // Ordenar por id descendente (más reciente primero)
    const gestionesArray = Array.from(gestionesMap.values()).sort((a, b) => b.id - a.id);
    this._gestiones.set(gestionesArray);
  }

  gestiones = computed(() => this._gestiones());

  promedioGeneral = computed(() => {
    const gestionesCerradas = this._gestiones().filter(g => g.estado === 'CERRADA');
    if (gestionesCerradas.length === 0) return '-';
    const promedio = gestionesCerradas.reduce((sum, g) => sum + g.promedio, 0) / gestionesCerradas.length;
    return promedio.toFixed(1);
  });

  creditosAprobados = computed(() => {
    return this._gestiones()
      .flatMap(g => g.materias)
      .filter(m => m.estado === 'APROBADO')
      .reduce((sum, m) => sum + m.creditos, 0);
  });

  materiasAprobadas = computed(() => {
    return this._gestiones()
      .flatMap(g => g.materias)
      .filter(m => m.estado === 'APROBADO')
      .length;
  });

  avanceCarrera = computed(() => {
    return Math.round((this.creditosAprobados() / this.creditosTotales) * 100);
  });

  toggleGestion(gestionId: number): void {
    if (this.gestionExpandida() === gestionId) {
      this.gestionExpandida.set(null);
    } else {
      this.gestionExpandida.set(gestionId);
    }
  }

  formatearEstado(estado: string): string {
    const estados: Record<string, string> = {
      'APROBADO': 'Aprobado',
      'REPROBADO': 'Reprobado',
      'EN_CURSO': 'En Curso'
    };
    return estados[estado] || estado;
  }

  calcularCreditosAprobados(gestion: GestionHistorial): number {
    return gestion.materias
      .filter(m => m.estado === 'APROBADO')
      .reduce((sum, m) => sum + m.creditos, 0);
  }

  descargarPDF(): void {
    // Generar contenido del historial
    const nombreEstudiante = this.authService.nombreCompleto();
    let contenido = `
HISTORIAL ACADÉMICO
==================

Estudiante: ${nombreEstudiante}
Fecha de emisión: ${new Date().toLocaleDateString('es-BO')}

RESUMEN
-------
Promedio General: ${this.promedioGeneral()}
Créditos Aprobados: ${this.creditosAprobados()}/${this.creditosTotales}
Materias Aprobadas: ${this.materiasAprobadas()}
Avance de Carrera: ${this.avanceCarrera()}%

DETALLE POR GESTIÓN
-------------------`;

    this._gestiones().forEach(gestion => {
      contenido += `

${gestion.nombre} (${gestion.estado === 'CERRADA' ? 'Finalizada' : 'En Curso'})
${'='.repeat(gestion.nombre.length + 15)}
`;
      gestion.materias.forEach(materia => {
        const notaStr = materia.estado === 'EN_CURSO' ? '-' : materia.nota.toString();
        contenido += `  ${materia.codigo} - ${materia.nombre}
    Créditos: ${materia.creditos} | Nota: ${notaStr} | ${this.formatearEstado(materia.estado)}
`;
      });
      contenido += `  Promedio del período: ${gestion.promedio.toFixed(1)}
`;
    });

    contenido += `

==================
Documento generado automáticamente
Sistema Universitario - Gestión II-2025
`;

    // Crear y descargar archivo
    const blob = new Blob([contenido], { type: 'text/plain' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = `Historial_Academico_${nombreEstudiante.replace(/\s+/g, '_')}.txt`;
    link.click();
    window.URL.revokeObjectURL(url);

    this.notificacion.exito('Historial académico descargado');
  }

  solicitarCertificado(): void {
    this.notificacion.info('Solicitud de certificado enviada. Recibirás una notificación cuando esté listo.');
  }
}
