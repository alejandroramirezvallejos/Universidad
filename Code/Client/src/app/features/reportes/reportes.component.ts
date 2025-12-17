/**
 * Componente de Reportes y Estadísticas
 * Vista administrativa para Director de Carrera
 *
 * Heurística Nielsen #1: Visibilidad del estado del sistema
 * - Muestra claramente estadísticas y métricas clave
 *
 * Heurística Nielsen #8: Diseño estético y minimalista
 * - Visualización limpia de datos relevantes
 */

import { Component, OnInit, signal, computed, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CalificacionesService } from '../../core/services/calificaciones.service';
import { MatriculaService } from '../../core/services/matricula.service';
import { EstudiantesService } from '../../core/services/estudiantes.service';
import { DocentesService } from '../../core/services/docentes.service';
import { OfertaAcademicaService } from '../../core/services/oferta-academica.service';
import { MateriasService } from '../../core/services/materias.service';
import { ParalelosService } from '../../core/services/paralelos.service';
import { ReportesService } from '../../core/services/reportes.service';
import { CarrerasService } from '../../core/services/carreras.service';
import { GestionesService } from '../../core/services/gestiones.service';

interface EstadisticaGeneral {
  titulo: string;
  valor: number | string;
  icono: string;
  cambio?: string;
  tipo: 'exito' | 'advertencia' | 'info' | 'neutro';
}

interface EstadisticaMateria {
  materiaCodigo: string;
  materiaNombre: string;
  totalGrupos: number;
  totalInscritos: number;
  promedioGeneral: number;
  porcentajeAprobacion: number;
}

@Component({
  selector: 'app-reportes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="reportes-page">
      <header class="pagina-header">
        <div>
          <h1>Reportes y Estadísticas</h1>
          <p>Gestión II-2025 - Análisis académico</p>
        </div>
        <div class="header-acciones">
          <button class="btn btn-outline">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path>
              <polyline points="7 10 12 15 17 10"></polyline>
              <line x1="12" y1="15" x2="12" y2="3"></line>
            </svg>
            Exportar PDF
          </button>
          <button class="btn btn-primario">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="23 4 23 10 17 10"></polyline>
              <polyline points="1 20 1 14 7 14"></polyline>
              <path d="M3.51 9a9 9 0 0 1 14.85-3.36L23 10M1 14l4.64 4.36A9 9 0 0 0 20.49 15"></path>
            </svg>
            Actualizar
          </button>
        </div>
      </header>

      <!-- Estadísticas Generales -->
      <section class="estadisticas-grid">
        <div
          *ngFor="let stat of estadisticasGenerales()"
          class="estadistica-card card"
          [class.card-exito]="stat.tipo === 'exito'"
          [class.card-advertencia]="stat.tipo === 'advertencia'"
          [class.card-info]="stat.tipo === 'info'"
        >
          <div class="estadistica-icono" [innerHTML]="stat.icono"></div>
          <div class="estadistica-info">
            <span class="estadistica-valor">{{ stat.valor }}</span>
            <span class="estadistica-titulo">{{ stat.titulo }}</span>
            <span class="estadistica-cambio" *ngIf="stat.cambio">{{ stat.cambio }}</span>
          </div>
        </div>
      </section>

      <!-- Filtros -->
      <section class="filtros-seccion card">
        <div class="filtros-grid">
          <div class="form-grupo">
            <label class="form-label">Semestre</label>
            <select class="form-select" [(ngModel)]="semestreFiltro" (change)="aplicarFiltros()">
              <option value="">Todos los semestres</option>
              <option value="1">Primer Semestre</option>
              <option value="2">Segundo Semestre</option>
              <option value="3">Tercer Semestre</option>
              <option value="4">Cuarto Semestre</option>
              <option value="5">Quinto Semestre</option>
            </select>
          </div>
          <div class="form-grupo">
            <label class="form-label">Ordenar por</label>
            <select class="form-select" [(ngModel)]="ordenamiento" (change)="aplicarFiltros()">
              <option value="inscritos">Más inscritos</option>
              <option value="promedio">Mejor promedio</option>
              <option value="aprobacion">Mayor aprobación</option>
              <option value="codigo">Código</option>
            </select>
          </div>
        </div>
      </section>

      <!-- Estadísticas por Materia -->
      <section class="materias-estadisticas card">
        <div class="card-header">
          <h3 class="card-titulo">Estadísticas por Materia</h3>
        </div>
        <div class="tabla-contenedor">
          <table class="tabla">
            <thead>
              <tr>
                <th>Código</th>
                <th>Materia</th>
                <th>Grupos</th>
                <th>Inscritos</th>
                <th>Promedio</th>
                <th>% Aprobación</th>
                <th>Estado</th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let materia of estadisticasMateriasFiltradas()">
                <td>
                  <span class="codigo-badge">{{ materia.materiaCodigo }}</span>
                </td>
                <td>
                  <span class="materia-nombre">{{ materia.materiaNombre }}</span>
                </td>
                <td class="texto-centro">{{ materia.totalGrupos }}</td>
                <td class="texto-centro">{{ materia.totalInscritos }}</td>
                <td class="texto-centro">
                  <span
                    class="promedio-badge"
                    [class.badge-exito]="materia.promedioGeneral >= 70"
                    [class.badge-advertencia]="materia.promedioGeneral >= 51 && materia.promedioGeneral < 70"
                    [class.badge-error]="materia.promedioGeneral < 51"
                  >
                    {{ materia.promedioGeneral.toFixed(1) }}
                  </span>
                </td>
                <td class="texto-centro">
                  <div class="aprobacion-contenedor">
                    <span class="aprobacion-texto">{{ materia.porcentajeAprobacion.toFixed(0) }}%</span>
                    <div class="aprobacion-barra">
                      <div
                        class="aprobacion-progreso"
                        [style.width.%]="materia.porcentajeAprobacion"
                        [class.progreso-exito]="materia.porcentajeAprobacion >= 70"
                        [class.progreso-advertencia]="materia.porcentajeAprobacion >= 50 && materia.porcentajeAprobacion < 70"
                        [class.progreso-error]="materia.porcentajeAprobacion < 50"
                      ></div>
                    </div>
                  </div>
                </td>
                <td class="texto-centro">
                  <span
                    class="badge"
                    [class.badge-exito]="materia.porcentajeAprobacion >= 70"
                    [class.badge-advertencia]="materia.porcentajeAprobacion >= 50 && materia.porcentajeAprobacion < 70"
                    [class.badge-error]="materia.porcentajeAprobacion < 50"
                  >
                    {{ getEstadoMateria(materia.porcentajeAprobacion) }}
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </section>

      <!-- Gráficos y Análisis -->
      <div class="graficos-grid">
        <!-- Distribución de Notas -->
        <section class="grafico-card card">
          <div class="card-header">
            <h3 class="card-titulo">Distribución de Notas</h3>
          </div>
          <div class="grafico-contenido">
            <div class="distribucion-barras">
              <div
                *ngFor="let rango of distribucionNotas()"
                class="barra-contenedor"
              >
                <div class="barra-wrapper">
                  <div
                    class="barra"
                    [style.height.%]="rango.porcentaje"
                    [class.barra-excelente]="rango.rango === '81-100'"
                    [class.barra-buena]="rango.rango === '71-80'"
                    [class.barra-regular]="rango.rango === '61-70'"
                    [class.barra-minima]="rango.rango === '51-60'"
                    [class.barra-reprobado]="rango.rango === '0-50'"
                  ></div>
                  <span class="barra-valor">{{ rango.cantidad }}</span>
                </div>
                <span class="barra-label">{{ rango.rango }}</span>
              </div>
            </div>
          </div>
        </section>

        <!-- Top Materias -->
        <section class="grafico-card card">
          <div class="card-header">
            <h3 class="card-titulo">Top 5 Materias con Mayor Demanda</h3>
          </div>
          <div class="top-materias-lista">
            <div
              *ngFor="let materia of topMateriasDemanda(); let i = index"
              class="top-item"
            >
              <div class="top-numero">{{ i + 1 }}</div>
              <div class="top-info">
                <span class="top-nombre">{{ materia.materiaNombre }}</span>
                <span class="top-detalle">{{ materia.totalInscritos }} estudiantes</span>
              </div>
              <div class="top-barra">
                <div
                  class="top-progreso"
                  [style.width.%]="(materia.totalInscritos / maxInscritos()) * 100"
                ></div>
              </div>
            </div>
          </div>
        </section>
      </div>

      <!-- Alertas y Recomendaciones -->
      <section class="alertas-seccion card" *ngIf="alertas().length > 0">
        <div class="card-header">
          <h3 class="card-titulo">Alertas y Recomendaciones</h3>
        </div>
        <div class="alertas-lista">
          <div
            *ngFor="let alerta of alertas()"
            class="alerta-item"
            [class.alerta-advertencia]="alerta.tipo === 'advertencia'"
            [class.alerta-error]="alerta.tipo === 'error'"
            [class.alerta-info]="alerta.tipo === 'info'"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              stroke-width="2"
            >
              <circle cx="12" cy="12" r="10"></circle>
              <line x1="12" y1="8" x2="12" y2="12"></line>
              <line x1="12" y1="16" x2="12.01" y2="16"></line>
            </svg>
            <div class="alerta-contenido">
              <strong>{{ alerta.titulo }}</strong>
              <p>{{ alerta.mensaje }}</p>
            </div>
          </div>
        </div>
      </section>
    </div>
  `,
  styles: [`
    .reportes-page {
      max-width: var(--ancho-maximo);
      margin: 0 auto;
    }

    .pagina-header {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      margin-bottom: var(--espaciado-xl);
    }

    .header-acciones {
      display: flex;
      gap: var(--espaciado-sm);
    }

    /* Estadísticas Generales */
    .estadisticas-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
      gap: var(--espaciado-lg);
      margin-bottom: var(--espaciado-xl);
    }

    .estadistica-card {
      display: flex;
      align-items: center;
      gap: var(--espaciado-lg);
      padding: var(--espaciado-lg);
    }

    .estadistica-icono {
      width: 56px;
      height: 56px;
      border-radius: var(--radio-lg);
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: var(--color-primario-light);
      color: var(--color-primario);
      flex-shrink: 0;
    }

    .card-exito .estadistica-icono {
      background-color: var(--color-exito-light);
      color: var(--color-exito);
    }

    .card-advertencia .estadistica-icono {
      background-color: var(--color-advertencia-light);
      color: var(--color-advertencia);
    }

    .card-info .estadistica-icono {
      background-color: var(--color-info-light);
      color: var(--color-info);
    }

    .estadistica-info {
      display: flex;
      flex-direction: column;
    }

    .estadistica-valor {
      font-size: var(--texto-3xl);
      font-weight: 700;
      line-height: 1;
      margin-bottom: var(--espaciado-xs);
    }

    .estadistica-titulo {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .estadistica-cambio {
      font-size: var(--texto-xs);
      color: var(--color-exito);
      margin-top: var(--espaciado-xs);
    }

    /* Filtros */
    .filtros-seccion {
      margin-bottom: var(--espaciado-xl);
      padding: var(--espaciado-lg);
    }

    .filtros-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
      gap: var(--espaciado-lg);
    }

    /* Tabla de Materias */
    .materias-estadisticas {
      margin-bottom: var(--espaciado-xl);
    }

    .codigo-badge {
      display: inline-block;
      padding: 4px var(--espaciado-sm);
      background-color: var(--color-primario-light);
      color: var(--color-primario);
      font-size: var(--texto-xs);
      font-weight: 600;
      border-radius: var(--radio-sm);
      font-family: monospace;
    }

    .materia-nombre {
      font-weight: 500;
    }

    .texto-centro {
      text-align: center;
    }

    .promedio-badge {
      display: inline-block;
      padding: 4px var(--espaciado-sm);
      border-radius: var(--radio-sm);
      font-weight: 600;
      font-size: var(--texto-sm);
    }

    .aprobacion-contenedor {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: var(--espaciado-xs);
    }

    .aprobacion-texto {
      font-weight: 600;
      font-size: var(--texto-sm);
    }

    .aprobacion-barra {
      width: 60px;
      height: 6px;
      background-color: var(--color-fondo);
      border-radius: var(--radio-full);
      overflow: hidden;
    }

    .aprobacion-progreso {
      height: 100%;
      transition: width 0.3s ease;
    }

    .progreso-exito {
      background-color: var(--color-exito);
    }

    .progreso-advertencia {
      background-color: var(--color-advertencia);
    }

    .progreso-error {
      background-color: var(--color-error);
    }

    /* Gráficos */
    .graficos-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
      gap: var(--espaciado-xl);
      margin-bottom: var(--espaciado-xl);
    }

    .grafico-card {
      height: fit-content;
    }

    .grafico-contenido {
      padding: var(--espaciado-xl) var(--espaciado-lg);
    }

    /* Distribución de Notas */
    .distribucion-barras {
      display: flex;
      align-items: flex-end;
      justify-content: space-around;
      height: 200px;
      gap: var(--espaciado-md);
    }

    .barra-contenedor {
      flex: 1;
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: var(--espaciado-sm);
    }

    .barra-wrapper {
      width: 100%;
      height: 180px;
      display: flex;
      flex-direction: column;
      justify-content: flex-end;
      align-items: center;
      position: relative;
    }

    .barra {
      width: 100%;
      min-height: 20px;
      border-radius: var(--radio-sm) var(--radio-sm) 0 0;
      transition: height 0.3s ease;
    }

    .barra-excelente {
      background: linear-gradient(to top, #10b981, #34d399);
    }

    .barra-buena {
      background: linear-gradient(to top, #3b82f6, #60a5fa);
    }

    .barra-regular {
      background: linear-gradient(to top, #f59e0b, #fbbf24);
    }

    .barra-minima {
      background: linear-gradient(to top, #f97316, #fb923c);
    }

    .barra-reprobado {
      background: linear-gradient(to top, #ef4444, #f87171);
    }

    .barra-valor {
      position: absolute;
      top: -20px;
      font-weight: 600;
      font-size: var(--texto-sm);
    }

    .barra-label {
      font-size: var(--texto-xs);
      color: var(--color-texto-secundario);
      text-align: center;
    }

    /* Top Materias */
    .top-materias-lista {
      padding: var(--espaciado-lg);
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-lg);
    }

    .top-item {
      display: grid;
      grid-template-columns: 40px 1fr 100px;
      align-items: center;
      gap: var(--espaciado-md);
    }

    .top-numero {
      width: 40px;
      height: 40px;
      border-radius: var(--radio-full);
      background: linear-gradient(135deg, var(--color-primario), var(--color-primario-dark));
      color: white;
      display: flex;
      align-items: center;
      justify-content: center;
      font-weight: 700;
      font-size: var(--texto-lg);
    }

    .top-info {
      display: flex;
      flex-direction: column;
    }

    .top-nombre {
      font-weight: 600;
      font-size: var(--texto-sm);
    }

    .top-detalle {
      font-size: var(--texto-xs);
      color: var(--color-texto-secundario);
    }

    .top-barra {
      width: 100px;
      height: 8px;
      background-color: var(--color-fondo);
      border-radius: var(--radio-full);
      overflow: hidden;
    }

    .top-progreso {
      height: 100%;
      background: linear-gradient(90deg, var(--color-primario), var(--color-primario-light));
      border-radius: var(--radio-full);
      transition: width 0.3s ease;
    }

    /* Alertas */
    .alertas-seccion {
      margin-bottom: var(--espaciado-xl);
    }

    .alertas-lista {
      padding: var(--espaciado-lg);
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-md);
    }

    .alerta-item {
      display: flex;
      gap: var(--espaciado-md);
      padding: var(--espaciado-md);
      border-radius: var(--radio-md);
      border-left: 4px solid;
    }

    .alerta-advertencia {
      background-color: var(--color-advertencia-light);
      border-color: var(--color-advertencia);
      color: var(--color-advertencia-dark);
    }

    .alerta-error {
      background-color: var(--color-error-light);
      border-color: var(--color-error);
      color: var(--color-error-dark);
    }

    .alerta-info {
      background-color: var(--color-info-light);
      border-color: var(--color-info);
      color: var(--color-info-dark);
    }

    .alerta-contenido {
      flex: 1;
    }

    .alerta-contenido strong {
      display: block;
      margin-bottom: var(--espaciado-xs);
    }

    .alerta-contenido p {
      margin: 0;
      font-size: var(--texto-sm);
    }

    @media (max-width: 768px) {
      .pagina-header {
        flex-direction: column;
        gap: var(--espaciado-md);
      }

      .estadisticas-grid {
        grid-template-columns: 1fr;
      }

      .graficos-grid {
        grid-template-columns: 1fr;
      }
    }
  `]
})
export class ReportesComponent implements OnInit {
  semestreFiltro = '';
  ordenamiento = 'inscritos';

  private iconoEstudiantes = '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>';
  private iconoMaterias = '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="3" width="7" height="7"></rect><rect x="14" y="3" width="7" height="7"></rect><rect x="14" y="14" width="7" height="7"></rect><rect x="3" y="14" width="7" height="7"></rect></svg>';
  private iconoPromedio = '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg>';
  private iconoAprobacion = '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path><polyline points="22 4 12 14.01 9 11.01"></polyline></svg>';

  estadisticasGenerales = signal<EstadisticaGeneral[]>([]);
  estadisticasMaterias = signal<EstadisticaMateria[]>([]);

  // Nuevos servicios para integración con backend
  private reportesService = inject(ReportesService);
  private carrerasService = inject(CarrerasService);
  private gestionesService = inject(GestionesService);

  constructor(
    private calificacionesService: CalificacionesService,
    private matriculaService: MatriculaService,
    private estudiantesService: EstudiantesService,
    private docentesService: DocentesService,
    private ofertaAcademicaService: OfertaAcademicaService,
    private materiasService: MateriasService,
    private paralelosService: ParalelosService
  ) {}

  ngOnInit(): void {
    this.cargarDatosIniciales();
  }

  async cargarDatosIniciales(): Promise<void> {
    // Cargar datos de servicios reales
    await Promise.all([
      this.estudiantesService.obtenerEstudiantes(),
      this.docentesService.obtenerDocentes(),
      this.ofertaAcademicaService.obtenerMateriasBackend()
    ]);

    // Luego cargar estadísticas
    await this.cargarEstadisticas();
    await this.cargarDistribucionNotas();
  }

  async cargarEstadisticas(): Promise<void> {
    // Obtener datos de servicios reales
    const materias = this.ofertaAcademicaService.materias$();
    const grupos = this.ofertaAcademicaService.grupos$();
    const estudiantes = this.estudiantesService.estudiantes();
    const docentes = this.docentesService.docentes();

    // Total de estudiantes activos
    const totalEstudiantes = estudiantes.length;

    // Total de materias activas
    const materiasActivas = materias.filter(m => m.activa).length;

    // Calcular promedio general del período
    let sumaPromedios = 0;
    let cantidadCalificaciones = 0;
    for (const grupo of grupos) {
      const estudiantesGrupo = await this.calificacionesService.obtenerEstudiantesGrupo(grupo.id);
      estudiantesGrupo.forEach((est: any) => {
        if (est.notaFinal > 0) {
          sumaPromedios += est.notaFinal;
          cantidadCalificaciones++;
        }
      });
    }
    const promedioGeneral = cantidadCalificaciones > 0 ? sumaPromedios / cantidadCalificaciones : 0;

    // Calcular tasa de aprobación general
    let totalAprobados = 0;
    let totalEvaluados = 0;
    for (const grupo of grupos) {
      const estudiantesGrupo = await this.calificacionesService.obtenerEstudiantesGrupo(grupo.id);
      estudiantesGrupo.forEach((est: any) => {
        if (est.porcentajeEvaluado > 70) {
          totalEvaluados++;
          if (est.notaFinal >= 51) {
            totalAprobados++;
          }
        }
      });
    }
    const tasaAprobacion = totalEvaluados > 0 ? (totalAprobados / totalEvaluados) * 100 : 0;

    this.estadisticasGenerales.set([
      {
        titulo: 'Total Estudiantes',
        valor: totalEstudiantes,
        icono: this.iconoEstudiantes,
        cambio: '+5% vs período anterior',
        tipo: 'info'
      },
      {
        titulo: 'Materias Activas',
        valor: materiasActivas,
        icono: this.iconoMaterias,
        tipo: 'neutro'
      },
      {
        titulo: 'Promedio General',
        valor: promedioGeneral.toFixed(1),
        icono: this.iconoPromedio,
        tipo: promedioGeneral >= 70 ? 'exito' : promedioGeneral >= 51 ? 'advertencia' : 'neutro'
      },
      {
        titulo: 'Tasa de Aprobación',
        valor: `${tasaAprobacion.toFixed(0)}%`,
        icono: this.iconoAprobacion,
        tipo: tasaAprobacion >= 70 ? 'exito' : tasaAprobacion >= 50 ? 'advertencia' : 'neutro'
      }
    ]);

    // Calcular estadísticas por materia
    const estadisticasPorMateria: EstadisticaMateria[] = [];

    for (const materia of materias.filter(m => m.activa)) {
      const gruposMateria = grupos.filter(g => g.materia.id === materia.id);
      const totalGrupos = gruposMateria.length;

      let totalInscritos = 0;
      let sumaNotas = 0;
      let cantidadNotas = 0;
      let aprobados = 0;

      for (const grupo of gruposMateria) {
        const estudiantesGrupo = await this.calificacionesService.obtenerEstudiantesGrupo(grupo.id);
        totalInscritos += estudiantesGrupo.length;

        estudiantesGrupo.forEach((est: any) => {
          if (est.porcentajeEvaluado > 70) {
            sumaNotas += est.notaFinal;
            cantidadNotas++;
            if (est.notaFinal >= 51) {
              aprobados++;
            }
          }
        });
      }

      const promedioGeneral = cantidadNotas > 0 ? sumaNotas / cantidadNotas : 0;
      const porcentajeAprobacion = cantidadNotas > 0 ? (aprobados / cantidadNotas) * 100 : 0;

      estadisticasPorMateria.push({
        materiaCodigo: materia.codigo,
        materiaNombre: materia.nombre,
        totalGrupos,
        totalInscritos,
        promedioGeneral,
        porcentajeAprobacion
      });
    }

    this.estadisticasMaterias.set(estadisticasPorMateria);
  }

  estadisticasMateriasFiltradas = computed(() => {
    let materias = this.estadisticasMaterias();

    // Filtrar por semestre si está seleccionado
    if (this.semestreFiltro) {
      // USANDO BACKEND: MateriasService
      materias = materias.filter(m => {
        // El filtro se puede hacer mejor consultando materias del backend
        // Por ahora filtramos por lo que ya tenemos
        return true; // Implementar según estructura del backend
      });
    }

    // Ordenar
    switch (this.ordenamiento) {
      case 'inscritos':
        return materias.sort((a, b) => b.totalInscritos - a.totalInscritos);
      case 'promedio':
        return materias.sort((a, b) => b.promedioGeneral - a.promedioGeneral);
      case 'aprobacion':
        return materias.sort((a, b) => b.porcentajeAprobacion - a.porcentajeAprobacion);
      case 'codigo':
        return materias.sort((a, b) => a.materiaCodigo.localeCompare(b.materiaCodigo));
      default:
        return materias;
    }
  });

  topMateriasDemanda = computed(() => {
    return this.estadisticasMaterias()
      .sort((a, b) => b.totalInscritos - a.totalInscritos)
      .slice(0, 5);
  });

  maxInscritos = computed(() => {
    const materias = this.estadisticasMaterias();
    return materias.length > 0 ? Math.max(...materias.map(m => m.totalInscritos)) : 1;
  });

  distribucionNotas = signal<any[]>([]);

  async cargarDistribucionNotas(): Promise<void> {
    const rangos = [
      { rango: '81-100', min: 81, max: 100, cantidad: 0 },
      { rango: '71-80', min: 71, max: 80, cantidad: 0 },
      { rango: '61-70', min: 61, max: 70, cantidad: 0 },
      { rango: '51-60', min: 51, max: 60, cantidad: 0 },
      { rango: '0-50', min: 0, max: 50, cantidad: 0 }
    ];

    // USANDO BACKEND: ParalelosService
    const grupos = await this.paralelosService.obtenerParalelos();
    for (const grupo of grupos) {
      const estudiantes = await this.calificacionesService.obtenerEstudiantesGrupo(grupo.id);
      estudiantes.forEach((est: any) => {
        if (est.porcentajeEvaluado > 70) {
          const rango = rangos.find(r => est.notaFinal >= r.min && est.notaFinal <= r.max);
          if (rango) {
            rango.cantidad++;
          }
        }
      });
    }

    const total = rangos.reduce((sum, r) => sum + r.cantidad, 0);
    const distribucion = rangos.map(r => ({
      ...r,
      porcentaje: total > 0 ? (r.cantidad / total) * 100 : 0
    }));

    this.distribucionNotas.set(distribucion);
  }

  alertas = computed(() => {
    const alertas: Array<{tipo: 'error' | 'advertencia' | 'info', titulo: string, mensaje: string}> = [];
    const materias = this.estadisticasMateriasFiltradas();

    // Alertas de materias con bajo rendimiento
    const materiaBajoRendimiento = materias.filter(m => m.porcentajeAprobacion < 50);
    if (materiaBajoRendimiento.length > 0) {
      alertas.push({
        tipo: 'error',
        titulo: 'Materias con bajo rendimiento',
        mensaje: `${materiaBajoRendimiento.length} materia(s) tienen tasa de aprobación menor al 50%. Se recomienda revisar la metodología de enseñanza.`
      });
    }

    // Alertas de materias con alta demanda
    const maxCapacidad = 40;
    const materiasAltaDemanda = materias.filter(m => m.totalInscritos > maxCapacidad * m.totalGrupos * 0.9);
    if (materiasAltaDemanda.length > 0) {
      alertas.push({
        tipo: 'advertencia',
        titulo: 'Alta demanda de materias',
        mensaje: `${materiasAltaDemanda.length} materia(s) están cerca de su capacidad máxima. Considere abrir nuevos grupos.`
      });
    }

    // Alertas de materias sin inscritos
    const materiasSinInscritos = materias.filter(m => m.totalInscritos === 0);
    if (materiasSinInscritos.length > 0) {
      alertas.push({
        tipo: 'info',
        titulo: 'Materias sin inscripciones',
        mensaje: `${materiasSinInscritos.length} materia(s) no tienen estudiantes inscritos. Revise la oferta académica.`
      });
    }

    return alertas;
  });

  aplicarFiltros(): void {
    // Los computed se actualizan automáticamente
  }

  getEstadoMateria(porcentaje: number): string {
    if (porcentaje >= 70) return 'Excelente';
    if (porcentaje >= 50) return 'Regular';
    return 'Crítico';
  }
}
