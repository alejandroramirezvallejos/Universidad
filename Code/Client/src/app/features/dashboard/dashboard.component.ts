/**
 * Dashboard Principal
 *
 * Heurística Nielsen #1: Visibilidad del estado del sistema
 * - Muestra resumen de información relevante para el usuario
 *
 * Heurística Nielsen #7: Flexibilidad y eficiencia de uso
 * - Accesos rápidos a las funciones más usadas
 */

import { Component, OnInit, signal, computed, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { MatriculaService } from '../../core/services/matricula.service';
import { CalificacionesService } from '../../core/services/calificaciones.service';
import { OfertaAcademicaService } from '../../core/services/oferta-academica.service';
import { EstudiantesService } from '../../core/services/estudiantes.service';
import { DocentesService } from '../../core/services/docentes.service';
import { DashboardService } from '../../core/services/dashboard.service';
import { RolUsuario } from '../../models';

interface TarjetaResumen {
  titulo: string;
  valor: string | number;
  icono: string;
  color: 'primario' | 'exito' | 'advertencia' | 'info';
  cambio?: string;
}

interface AccesoRapido {
  titulo: string;
  descripcion: string;
  icono: string;
  ruta: string;
  roles: RolUsuario[];
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="dashboard">
      <!-- Encabezado de bienvenida -->
      <header class="dashboard-header">
        <div class="bienvenida">
          <h1>¡Hola, {{ authService.usuario()?.nombre }}!</h1>
          <p>{{ obtenerSaludo() }}. Aquí está el resumen de tu actividad.</p>
        </div>
        <div class="fecha-actual">
          <span class="fecha-dia">{{ fechaActual | date:'EEEE' }}</span>
          <span class="fecha-completa">{{ fechaActual | date:'d MMMM, yyyy' }}</span>
        </div>
      </header>

      <!-- Tarjetas de resumen -->
      <section class="resumen-grid">
        <article
          *ngFor="let tarjeta of tarjetasResumen()"
          class="tarjeta-resumen"
          [class]="'tarjeta-' + tarjeta.color"
        >
          <div class="tarjeta-icono" [innerHTML]="tarjeta.icono"></div>
          <div class="tarjeta-contenido">
            <span class="tarjeta-titulo">{{ tarjeta.titulo }}</span>
            <span class="tarjeta-valor">{{ tarjeta.valor }}</span>
            <span class="tarjeta-cambio" *ngIf="tarjeta.cambio">{{ tarjeta.cambio }}</span>
          </div>
        </article>
      </section>

      <!-- Accesos rápidos -->
      <section class="accesos-rapidos">
        <h2>Accesos Rápidos</h2>
        <div class="accesos-grid">
          <a
            *ngFor="let acceso of accesosRapidos()"
            [routerLink]="acceso.ruta"
            class="acceso-card"
          >
            <div class="acceso-icono" [innerHTML]="acceso.icono"></div>
            <div class="acceso-info">
              <h3>{{ acceso.titulo }}</h3>
              <p>{{ acceso.descripcion }}</p>
            </div>
            <svg class="acceso-flecha" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="9 18 15 12 9 6"></polyline>
            </svg>
          </a>
        </div>
      </section>

      <!-- Alertas y notificaciones -->
    </div>
  `,
  styles: [`
    .dashboard {
      max-width: var(--ancho-maximo);
      margin: 0 auto;
    }

    .dashboard-header {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      margin-bottom: var(--espaciado-xl);
      flex-wrap: wrap;
      gap: var(--espaciado-md);
    }

    .bienvenida h1 {
      font-size: var(--texto-3xl);
      margin-bottom: var(--espaciado-xs);
    }

    .bienvenida p {
      color: var(--color-texto-secundario);
      margin: 0;
    }

    .fecha-actual {
      display: flex;
      flex-direction: column;
      align-items: flex-end;
      text-align: right;
    }

    .fecha-dia {
      font-size: var(--texto-lg);
      font-weight: 600;
      color: var(--color-primario);
      text-transform: capitalize;
    }

    .fecha-completa {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    /* Tarjetas de resumen */
    .resumen-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
      gap: var(--espaciado-lg);
      margin-bottom: var(--espaciado-xl);
    }

    .tarjeta-resumen {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
      padding: var(--espaciado-lg);
      background-color: var(--color-fondo-card);
      border-radius: var(--radio-lg);
      border: 1px solid var(--color-borde);
      transition: transform var(--transicion-rapida), box-shadow var(--transicion-rapida);
    }

    .tarjeta-resumen:hover {
      transform: translateY(-2px);
      box-shadow: var(--sombra-md);
    }

    .tarjeta-icono {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 48px;
      height: 48px;
      border-radius: var(--radio-md);
      flex-shrink: 0;
    }

    .tarjeta-primario .tarjeta-icono {
      background-color: var(--color-primario-light);
      color: var(--color-primario);
    }

    .tarjeta-exito .tarjeta-icono {
      background-color: var(--color-exito-light);
      color: var(--color-exito);
    }

    .tarjeta-advertencia .tarjeta-icono {
      background-color: var(--color-advertencia-light);
      color: var(--color-advertencia);
    }

    .tarjeta-info .tarjeta-icono {
      background-color: var(--color-info-light);
      color: var(--color-info);
    }

    .tarjeta-contenido {
      display: flex;
      flex-direction: column;
    }

    .tarjeta-titulo {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .tarjeta-valor {
      font-size: var(--texto-2xl);
      font-weight: 700;
      color: var(--color-texto);
    }

    .tarjeta-cambio {
      font-size: var(--texto-xs);
      color: var(--color-exito);
    }

    /* Accesos rápidos */
    .accesos-rapidos {
      margin-bottom: var(--espaciado-xl);
    }

    .accesos-rapidos h2 {
      font-size: var(--texto-xl);
      margin-bottom: var(--espaciado-md);
    }

    .accesos-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
      gap: var(--espaciado-md);
    }

    .acceso-card {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
      padding: var(--espaciado-md) var(--espaciado-lg);
      background-color: var(--color-fondo-card);
      border: 1px solid var(--color-borde);
      border-radius: var(--radio-lg);
      text-decoration: none;
      transition: all var(--transicion-rapida);
    }

    .acceso-card:hover {
      border-color: var(--color-primario);
      box-shadow: var(--sombra-sm);
    }

    .acceso-icono {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 40px;
      height: 40px;
      background-color: var(--color-primario-light);
      color: var(--color-primario);
      border-radius: var(--radio-md);
      flex-shrink: 0;
    }

    .acceso-info {
      flex: 1;
    }

    .acceso-info h3 {
      font-size: var(--texto-base);
      margin-bottom: 2px;
      color: var(--color-texto);
    }

    .acceso-info p {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
      margin: 0;
    }

    .acceso-flecha {
      color: var(--color-texto-claro);
      transition: transform var(--transicion-rapida);
    }

    .acceso-card:hover .acceso-flecha {
      transform: translateX(4px);
      color: var(--color-primario);
    }

    @media (max-width: 768px) {
      .dashboard-header {
        flex-direction: column;
      }

      .fecha-actual {
        align-items: flex-start;
        text-align: left;
      }
    }
  `]
})
export class DashboardComponent implements OnInit {
  fechaActual = new Date();

  private iconos = {
    materias: '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="3" width="7" height="7"></rect><rect x="14" y="3" width="7" height="7"></rect><rect x="14" y="14" width="7" height="7"></rect><rect x="3" y="14" width="7" height="7"></rect></svg>',
    creditos: '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"></circle><polyline points="12 6 12 12 16 14"></polyline></svg>',
    promedio: '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg>',
    pendientes: '<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect><line x1="16" y1="2" x2="16" y2="6"></line><line x1="8" y1="2" x2="8" y2="6"></line><line x1="3" y1="10" x2="21" y2="10"></line></svg>',
    matricula: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg>',
    horario: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect><line x1="16" y1="2" x2="16" y2="6"></line><line x1="8" y1="2" x2="8" y2="6"></line><line x1="3" y1="10" x2="21" y2="10"></line></svg>',
    notas: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline></svg>',
    historial: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"></circle><polyline points="12 6 12 12 16 14"></polyline></svg>'
  };

  // Servicio de Dashboard para integración con backend
  private dashboardService = inject(DashboardService);

  constructor(
    public authService: AuthService,
    private matriculaService: MatriculaService,
    private calificacionesService: CalificacionesService,
    private ofertaService: OfertaAcademicaService,
    private estudiantesService: EstudiantesService,
    private docentesService: DocentesService
  ) {}

  async ngOnInit(): Promise<void> {
    await this.cargarDatos();
  }

  async cargarDatos(): Promise<void> {
    const rol = this.authService.rol();
    try {
      // Cargar datos según el rol
      if (rol === 'ESTUDIANTE') {
        await Promise.all([
          this.matriculaService.obtenerMisMatriculas(),
          this.calificacionesService.obtenerMisNotas()
        ]);
      } else if (rol === 'DOCENTE') {
        await Promise.all([
          this.calificacionesService.obtenerMisGrupos(),
          this.ofertaService.obtenerMateriasBackend()
        ]);
      } else if (rol === 'DIRECTOR') {
        await Promise.all([
          this.estudiantesService.obtenerEstudiantes(),
          this.docentesService.obtenerDocentes(),
          this.ofertaService.obtenerMateriasBackend()
        ]);
      }
    } catch (error) {
      console.error('Error cargando datos del dashboard:', error);
    }
  }

  alertas = computed(() => {
    const rol = this.authService.rol();
    const iconoAdvertencia = '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="12"></line><line x1="12" y1="16" x2="12.01" y2="16"></line></svg>';
    const iconoInfo = '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="16"></line><line x1="12" y1="20" x2="12.01" y2="20"></line></svg>';

    if (rol === 'ESTUDIANTE') {
      return [
        {
          id: 1,
          tipo: 'advertencia',
          titulo: 'Período de matrícula abierto',
          mensaje: 'Tienes hasta el 30 de julio para completar tu inscripción.',
          icono: iconoAdvertencia
        },
        {
          id: 2,
          tipo: 'info',
          titulo: 'Nueva calificación disponible',
          mensaje: 'Se publicó la nota final de Programación I.',
          icono: iconoInfo
        }
      ];
    }

    if (rol === 'DOCENTE') {
      return [
        {
          id: 1,
          tipo: 'advertencia',
          titulo: 'Fecha límite para registro de notas',
          mensaje: 'Tienes hasta el 8 de diciembre para registrar todas las calificaciones.',
          icono: iconoAdvertencia
        },
        {
          id: 2,
          tipo: 'info',
          titulo: 'Reunión de evaluación',
          mensaje: 'Reunión departamental el 5 de diciembre a las 10:00 AM.',
          icono: iconoInfo
        }
      ];
    }

    if (rol === 'DIRECTOR') {
      return [
        {
          id: 1,
          tipo: 'advertencia',
          titulo: 'Gestión II-2025 en período de exámenes',
          mensaje: 'El período de exámenes finalizará el 31 de diciembre automáticamente.',
          icono: iconoAdvertencia
        },
        {
          id: 2,
          tipo: 'info',
          titulo: 'Reporte de inscripciones',
          mensaje: '156 nuevas inscripciones completadas en las últimas 24 horas.',
          icono: iconoInfo
        }
      ];
    }

    return [];
  });

  tarjetasResumen = computed<TarjetaResumen[]>(() => {
    const rol = this.authService.rol();

    if (rol === 'ESTUDIANTE') {
      // Tarjetas deshabilitadas para estudiante - funcionalidad en desarrollo
      return [];
      // const materiasEnProceso = this.matriculaService.cantidadEnProceso();
      // const creditos = this.matriculaService.creditosTotales();
      // return [
      //   { titulo: 'Materias en Proceso', valor: materiasEnProceso, icono: this.iconos.materias, color: 'primario' },
      //   { titulo: 'Créditos Seleccionados', valor: creditos, icono: this.iconos.creditos, color: 'info' }
      // ];
    }

    if (rol === 'DOCENTE') {
      // Datos básicos para docente
      return [
        { titulo: 'Mis Grupos', valor: 3, icono: this.iconos.materias, color: 'primario' },
        { titulo: 'Estudiantes', valor: 87, icono: this.iconos.creditos, color: 'info' },
        { titulo: 'Notas Pendientes', valor: 12, icono: this.iconos.pendientes, color: 'advertencia' },
        { titulo: 'Promedio General', valor: '72.3', icono: this.iconos.promedio, color: 'exito' }
      ];
    }

    // Admin/Director - Datos reales usando signals
    const estudiantes = this.estudiantesService.estudiantes();
    const docentes = this.docentesService.docentes();
    const materias = this.ofertaService.materias$();

    return [
      { titulo: 'Estudiantes Activos', valor: estudiantes.length, icono: this.iconos.materias, color: 'primario' },
      { titulo: 'Docentes', valor: docentes.length, icono: this.iconos.creditos, color: 'info' },
      { titulo: 'Materias', valor: materias.length, icono: this.iconos.pendientes, color: 'advertencia' }
    ];
  });

  accesosRapidos = computed<AccesoRapido[]>(() => {
    const rol = this.authService.rol();
    const accesos: AccesoRapido[] = [];

    if (rol === 'ESTUDIANTE') {
      accesos.push(
        { titulo: 'Inscribir Materias', descripcion: 'Gestión II-2025', icono: this.iconos.matricula, ruta: '/matricula', roles: ['ESTUDIANTE'] },
        { titulo: 'Mi Horario', descripcion: 'Ver horario semanal', icono: this.iconos.horario, ruta: '/horario', roles: ['ESTUDIANTE'] }
        // Calificaciones e Historial deshabilitados - funcionalidad en desarrollo
        // { titulo: 'Consultar Notas', descripcion: 'Calificaciones actuales', icono: this.iconos.notas, ruta: '/calificaciones', roles: ['ESTUDIANTE'] },
        // { titulo: 'Historial', descripcion: 'Historial académico', icono: this.iconos.historial, ruta: '/historial', roles: ['ESTUDIANTE'] }
      );
    }

    if (rol === 'DOCENTE') {
      accesos.push(
        { titulo: 'Oferta Académica', descripcion: 'Ver oferta', icono: this.iconos.matricula, ruta: '/oferta-academica', roles: ['DOCENTE'] },
        { titulo: 'Registrar Notas', descripcion: 'Ingresar calificaciones', icono: this.iconos.notas, ruta: '/calificaciones', roles: ['DOCENTE'] }
      );
    }

    if (rol === 'DIRECTOR') {
      accesos.push(
        { titulo: 'Gestión de Materias', descripcion: 'Catálogo de materias', icono: this.iconos.notas, ruta: '/gestion-materias', roles: ['DIRECTOR'] },
        { titulo: 'Gestión de Grupos', descripcion: 'Grupos y docentes', icono: this.iconos.horario, ruta: '/gestion-grupos', roles: ['DIRECTOR'] },
        // ⚠️ DESHABILITADO: El backend no tiene gestión de períodos académicos
        // { titulo: 'Gestión de Períodos', descripcion: 'Períodos académicos', icono: this.iconos.historial, ruta: '/gestiones', roles: ['DIRECTOR'] },
        // Reportes deshabilitado - funcionalidad en desarrollo
        // { titulo: 'Reportes', descripcion: 'Estadísticas y reportes', icono: this.iconos.matricula, ruta: '/reportes', roles: ['DIRECTOR'] }
      );
    }

    return accesos;
  });

  obtenerSaludo(): string {
    const hora = new Date().getHours();
    if (hora < 12) return 'Buenos días';
    if (hora < 18) return 'Buenas tardes';
    return 'Buenas noches';
  }
}
