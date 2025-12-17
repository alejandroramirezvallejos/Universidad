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
      </header>

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

    @media (max-width: 768px) {
      .pagina-header {
        flex-direction: column;
        gap: var(--espaciado-md);
      }
    }
  `]
})
export class ReportesComponent implements OnInit {
  semestreFiltro = '';
  ordenamiento = 'inscritos';

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
      const estudiantesGrupo = await this.calificacionesService.obtenerEstudiantesGrupo(grupo.codigo);
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
      const estudiantesGrupo = await this.calificacionesService.obtenerEstudiantesGrupo(grupo.codigo);
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
        const estudiantesGrupo = await this.calificacionesService.obtenerEstudiantesGrupo(grupo.codigo);
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

  aplicarFiltros(): void {
    // Los computed se actualizan automáticamente
  }

  getEstadoMateria(porcentaje: number): string {
    if (porcentaje >= 70) return 'Excelente';
    if (porcentaje >= 50) return 'Regular';
    return 'Crítico';
  }
}
