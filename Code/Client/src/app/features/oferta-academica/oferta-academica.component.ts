/**
 * Página de Oferta Académica
 * Muestra materias y grupos disponibles para inscripción
 * 
 * Heurística Nielsen #3: Control y libertad del usuario
 * - Filtros para encontrar materias fácilmente
 * 
 * Heurística Nielsen #6: Reconocimiento antes que recuerdo
 * - Información clara de cada materia y grupo
 */

import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OfertaAcademicaService } from '../../core/services/oferta-academica.service';
import { MatriculaService } from '../../core/services/matricula.service';
import { NotificacionService } from '../../core/services/notificacion.service';
import { CalificacionesService } from '../../core/services/calificaciones.service';
import { Materia, Grupo } from '../../models';

@Component({
  selector: 'app-oferta-academica',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="oferta-academica">
      <!-- Encabezado -->
      <header class="pagina-header">
        <div class="header-info">
          <h1>Oferta Académica</h1>
          <p>Gestión II-2025 • Período de matrícula abierto</p>
        </div>
        <div class="header-acciones">
          <span class="badge badge-primario" *ngIf="matriculaService.cantidadEnProceso() > 0">
            {{ matriculaService.cantidadEnProceso() }} materias seleccionadas
          </span>
        </div>
      </header>

      <!-- Filtros -->
      <section class="filtros card">
        <div class="filtros-contenido">
          <div class="filtro-busqueda">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="11" cy="11" r="8"></circle>
              <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
            </svg>
            <input 
              type="search" 
              class="form-input"
              placeholder="Buscar materia por nombre o código..."
              [(ngModel)]="terminoBusqueda"
              (input)="filtrar()"
            >
          </div>
          
          <div class="filtros-selectores">
            <select class="form-select" [(ngModel)]="filtroSemestre" (change)="filtrar()">
              <option value="">Todos los semestres</option>
              <option *ngFor="let s of semestres" [value]="s">Semestre {{ s }}</option>
            </select>
            
            <select class="form-select" [(ngModel)]="filtroDisponibilidad" (change)="filtrar()">
              <option value="">Todas</option>
              <option value="disponible">Con cupos</option>
              <option value="lleno">Sin cupos</option>
            </select>
          </div>
        </div>
      </section>

      <!-- Lista de materias -->
      <section class="materias-lista">
        <div *ngFor="let materia of materiasFiltradas()" class="materia-card card">
          <div class="materia-header" (click)="toggleMateria(materia.id)">
            <div class="materia-info">
              <div class="materia-codigo">{{ materia.codigo }}</div>
              <div class="materia-detalles">
                <h3>{{ materia.nombre }}</h3>
                <div class="materia-meta">
                  <span class="meta-item">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <circle cx="12" cy="12" r="10"></circle>
                      <polyline points="12 6 12 12 16 14"></polyline>
                    </svg>
                    {{ materia.creditos }} créditos
                  </span>
                  <span class="meta-item">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                      <line x1="16" y1="2" x2="16" y2="6"></line>
                      <line x1="8" y1="2" x2="8" y2="6"></line>
                      <line x1="3" y1="10" x2="21" y2="10"></line>
                    </svg>
                    Semestre {{ materia.semestre }}
                  </span>
                  <span class="meta-item" *ngIf="materia.prerrequisitos.length > 0">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M10 13a5 5 0 0 0 7.54.54l3-3a5 5 0 0 0-7.07-7.07l-1.72 1.71"></path>
                      <path d="M14 11a5 5 0 0 0-7.54-.54l-3 3a5 5 0 0 0 7.07 7.07l1.71-1.71"></path>
                    </svg>
                    {{ materia.prerrequisitos.length }} prerrequisito(s)
                  </span>
                </div>
              </div>
            </div>
            <div class="materia-acciones">
              <span class="grupos-count">{{ obtenerGrupos(materia.id).length }} grupos</span>
              <svg 
                class="icono-expandir"
                [class.rotado]="materiaExpandida() === materia.id"
                xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
              >
                <polyline points="6 9 12 15 18 9"></polyline>
              </svg>
            </div>
          </div>

          <!-- Prerrequisitos -->
          <div class="materia-prerrequisitos" *ngIf="materia.prerrequisitos.length > 0 && materiaExpandida() === materia.id">
            <span class="prerrequisitos-label">Prerrequisitos:</span>
            <span class="prerrequisito-item" *ngFor="let prereq of materia.prerrequisitos">
              {{ prereq.nombre }}
            </span>
          </div>

          <!-- Grupos disponibles -->
          <div class="grupos-lista" *ngIf="materiaExpandida() === materia.id">
            <div 
              *ngFor="let grupo of obtenerGrupos(materia.id)" 
              class="grupo-item"
              [class.grupo-seleccionado]="estaSeleccionado(grupo.id)"
              [class.grupo-sin-cupo]="grupo.cupoDisponible === 0"
            >
              <div class="grupo-info">
                <div class="grupo-header">
                  <span class="grupo-codigo">Grupo {{ grupo.codigo }}</span>
                  <span 
                    class="cupo-badge"
                    [class.cupo-bajo]="grupo.cupoDisponible <= 5 && grupo.cupoDisponible > 0"
                    [class.cupo-lleno]="grupo.cupoDisponible === 0"
                  >
                    {{ grupo.cupoDisponible }}/{{ grupo.cupoMaximo }} cupos
                  </span>
                </div>
                
                <div class="grupo-detalles">
                  <div class="detalle-item">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                      <circle cx="12" cy="7" r="4"></circle>
                    </svg>
                    {{ grupo.docente.nombre }} {{ grupo.docente.apellido }}
                  </div>
                  <div class="detalle-item">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"></path>
                      <circle cx="12" cy="10" r="3"></circle>
                    </svg>
                    {{ grupo.aula.nombre }}
                  </div>
                </div>

                <div class="grupo-horarios">
                  <span class="horario-item" *ngFor="let horario of grupo.horarios">
                    {{ formatearDia(horario.dia) }} {{ horario.horaInicio }}-{{ horario.horaFin }}
                  </span>
                </div>
              </div>

              <div class="grupo-accion">
                <button 
                  *ngIf="!estaSeleccionado(grupo.id)"
                  class="btn btn-primario btn-sm"
                  [disabled]="grupo.cupoDisponible === 0 || !puedaAgregarMateria(grupo.materia.id)"
                  [title]="!puedaAgregarMateria(grupo.materia.id) ? obtenerMotivoBloqueo(grupo.materia.id) : 
                          (grupo.cupoDisponible === 0 ? 'Sin cupos disponibles' : 'Seleccionar materia')"
                  (click)="agregarMateria(grupo)"
                >
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <line x1="12" y1="5" x2="12" y2="19"></line>
                    <line x1="5" y1="12" x2="19" y2="12"></line>
                  </svg>
                  <span *ngIf="puedaAgregarMateria(grupo.materia.id)">Seleccionar</span>
                  <span *ngIf="!puedaAgregarMateria(grupo.materia.id)" class="texto-xs">
                    {{ materiasAprobadas.includes(grupo.materia.id) ? 'Aprobada' : 'Inscrita' }}
                  </span>
                </button>
                <button 
                  *ngIf="estaSeleccionado(grupo.id)"
                  class="btn btn-error btn-sm"
                  (click)="removerMateria(grupo.id)"
                >
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <line x1="18" y1="6" x2="6" y2="18"></line>
                    <line x1="6" y1="6" x2="18" y2="18"></line>
                  </svg>
                  Quitar
                </button>
              </div>
            </div>
          </div>
        </div>

        <!-- Estado vacío -->
        <div class="estado-vacio" *ngIf="materiasFiltradas().length === 0">
          <div class="estado-vacio-icono"></div>
          <h3>No se encontraron materias</h3>
          <p>Intenta con otros filtros de búsqueda</p>
        </div>
      </section>

      <!-- Panel de selección flotante -->
      <aside class="carrito-flotante" *ngIf="matriculaService.cantidadEnProceso() > 0">
        <div class="carrito-contenido">
          <div class="carrito-info">
            <span class="carrito-cantidad">{{ matriculaService.cantidadEnProceso() }} materias seleccionadas</span>
            <span class="carrito-creditos">{{ matriculaService.creditosTotales() }} créditos</span>
          </div>
          <a routerLink="/matricula" class="btn btn-primario">
            Confirmar selección
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="9 18 15 12 9 6"></polyline>
            </svg>
          </a>
        </div>
      </aside>
    </div>
  `,
  styles: [`
    .oferta-academica {
      max-width: var(--ancho-maximo);
      margin: 0 auto;
      padding-bottom: 100px;
    }

    .pagina-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: var(--espaciado-lg);
      flex-wrap: wrap;
      gap: var(--espaciado-md);
    }

    .header-info h1 {
      font-size: var(--texto-2xl);
      margin-bottom: var(--espaciado-xs);
    }

    .header-info p {
      color: var(--color-texto-secundario);
      margin: 0;
    }

    /* Filtros */
    .filtros {
      margin-bottom: var(--espaciado-lg);
    }

    .filtros-contenido {
      display: flex;
      gap: var(--espaciado-md);
      flex-wrap: wrap;
      padding: var(--espaciado-md);
    }

    .filtro-busqueda {
      flex: 1;
      min-width: 280px;
      position: relative;
    }

    .filtro-busqueda svg {
      position: absolute;
      left: var(--espaciado-md);
      top: 50%;
      transform: translateY(-50%);
      color: var(--color-texto-claro);
    }

    .filtro-busqueda .form-input {
      padding-left: 2.75rem;
    }

    .filtros-selectores {
      display: flex;
      gap: var(--espaciado-sm);
    }

    .filtros-selectores .form-select {
      min-width: 160px;
    }

    /* Materias */
    .materias-lista {
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

    .materia-info {
      display: flex;
      gap: var(--espaciado-md);
      align-items: flex-start;
    }

    .materia-codigo {
      padding: var(--espaciado-xs) var(--espaciado-sm);
      background-color: var(--color-primario-light);
      color: var(--color-primario);
      font-weight: 600;
      font-size: var(--texto-sm);
      border-radius: var(--radio-sm);
      white-space: nowrap;
    }

    .materia-detalles h3 {
      font-size: var(--texto-lg);
      margin-bottom: var(--espaciado-xs);
    }

    .materia-meta {
      display: flex;
      gap: var(--espaciado-md);
      flex-wrap: wrap;
    }

    .meta-item {
      display: flex;
      align-items: center;
      gap: 4px;
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .materia-acciones {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
    }

    .grupos-count {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .icono-expandir {
      transition: transform var(--transicion-rapida);
      color: var(--color-texto-secundario);
    }

    .icono-expandir.rotado {
      transform: rotate(180deg);
    }

    /* Prerrequisitos */
    .materia-prerrequisitos {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      padding: var(--espaciado-sm) var(--espaciado-lg);
      background-color: var(--color-advertencia-light);
      flex-wrap: wrap;
    }

    .prerrequisitos-label {
      font-size: var(--texto-sm);
      font-weight: 500;
      color: var(--color-advertencia);
    }

    .prerrequisito-item {
      font-size: var(--texto-sm);
      padding: 2px var(--espaciado-sm);
      background-color: white;
      border-radius: var(--radio-sm);
      color: var(--color-texto);
    }

    /* Grupos */
    .grupos-lista {
      border-top: 1px solid var(--color-borde);
    }

    .grupo-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: var(--espaciado-md) var(--espaciado-lg);
      border-bottom: 1px solid var(--color-borde);
      gap: var(--espaciado-md);
    }

    .grupo-item:last-child {
      border-bottom: none;
    }

    .grupo-item:hover {
      background-color: var(--color-fondo);
    }

    .grupo-seleccionado {
      background-color: var(--color-primario-light) !important;
    }

    .grupo-sin-cupo {
      opacity: 0.6;
    }

    .grupo-info {
      flex: 1;
    }

    .grupo-header {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
      margin-bottom: var(--espaciado-xs);
    }

    .grupo-codigo {
      font-weight: 600;
    }

    .cupo-badge {
      font-size: var(--texto-xs);
      padding: 2px var(--espaciado-sm);
      background-color: var(--color-exito-light);
      color: var(--color-exito);
      border-radius: var(--radio-full);
    }

    .cupo-bajo {
      background-color: var(--color-advertencia-light);
      color: var(--color-advertencia);
    }

    .cupo-lleno {
      background-color: var(--color-error-light);
      color: var(--color-error);
    }

    .grupo-detalles {
      display: flex;
      gap: var(--espaciado-lg);
      margin-bottom: var(--espaciado-xs);
    }

    .detalle-item {
      display: flex;
      align-items: center;
      gap: 4px;
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .grupo-horarios {
      display: flex;
      gap: var(--espaciado-sm);
      flex-wrap: wrap;
    }

    .horario-item {
      font-size: var(--texto-xs);
      padding: 2px var(--espaciado-sm);
      background-color: var(--color-fondo);
      border-radius: var(--radio-sm);
      color: var(--color-texto-secundario);
    }

    /* Carrito flotante */
    .carrito-flotante {
      position: fixed;
      bottom: var(--espaciado-lg);
      left: 50%;
      transform: translateX(-50%);
      background-color: var(--color-fondo-card);
      border-radius: var(--radio-xl);
      box-shadow: var(--sombra-xl);
      padding: var(--espaciado-md) var(--espaciado-lg);
      z-index: 50;
      border: 1px solid var(--color-borde);
      animation: slideUp var(--transicion-normal);
    }

    .carrito-contenido {
      display: flex;
      align-items: center;
      gap: var(--espaciado-lg);
    }

    .carrito-info {
      display: flex;
      flex-direction: column;
    }

    .carrito-cantidad {
      font-weight: 600;
      color: var(--color-texto);
    }

    .carrito-creditos {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    @media (max-width: 768px) {
      .filtros-selectores {
        width: 100%;
      }

      .filtros-selectores .form-select {
        flex: 1;
      }

      .grupo-item {
        flex-direction: column;
        align-items: flex-start;
      }

      .grupo-accion {
        width: 100%;
        margin-top: var(--espaciado-sm);
      }

      .grupo-accion .btn {
        width: 100%;
      }
    }
  `]
})
export class OfertaAcademicaComponent implements OnInit {
  terminoBusqueda = '';
  filtroSemestre = '';
  filtroDisponibilidad = '';
  
  materiaExpandida = signal<number | null>(null);
  semestres = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  
  // Listas de materias que no se pueden agregar
  materiasAprobadas: number[] = [];
  materiasInscritas: number[] = [];

  constructor(
    public ofertaService: OfertaAcademicaService,
    public matriculaService: MatriculaService,
    private notificacion: NotificacionService,
    private calificacionesService: CalificacionesService
  ) {}

  materiasFiltradas = computed(() => {
    let materias = this.ofertaService.materias$();

    if (this.terminoBusqueda) {
      const termino = this.terminoBusqueda.toLowerCase();
      materias = materias.filter(m => 
        m.nombre.toLowerCase().includes(termino) ||
        m.codigo.toLowerCase().includes(termino)
      );
    }

    if (this.filtroSemestre) {
      materias = materias.filter(m => m.semestre === parseInt(this.filtroSemestre));
    }

    if (this.filtroDisponibilidad) {
      materias = materias.filter(m => {
        const grupos = this.obtenerGrupos(m.id);
        const tieneDisponibilidad = grupos.some(g => g.cupoDisponible > 0);
        return this.filtroDisponibilidad === 'disponible' ? tieneDisponibilidad : !tieneDisponibilidad;
      });
    }

    return materias;
  });

  ngOnInit(): void {
    // Cargar materias que ya están aprobadas o inscritas
    this.materiasAprobadas = this.calificacionesService.obtenerMateriasAprobadas();
    this.materiasInscritas = this.matriculaService.obtenerMateriasInscritas();
  }

  toggleMateria(materiaId: number): void {
    if (this.materiaExpandida() === materiaId) {
      this.materiaExpandida.set(null);
    } else {
      this.materiaExpandida.set(materiaId);
    }
  }

  obtenerGrupos(materiaId: number): Grupo[] {
    return this.ofertaService.obtenerGruposPorMateria(materiaId);
  }

  estaSeleccionado(grupoId: number): boolean {
    return this.matriculaService.matriculasEnProceso$().some(g => g.id === grupoId);
  }

  agregarMateria(grupo: Grupo): void {
    // Validar primero si puede agregar la materia
    if (!this.puedaAgregarMateria(grupo.materia.id)) {
      const motivo = this.obtenerMotivoBloqueo(grupo.materia.id);
      this.notificacion.error(`No se puede seleccionar: ${motivo}`);
      return;
    }

    const resultado = this.matriculaService.agregarAlCarrito(grupo);
    
    if (resultado.valido) {
      this.notificacion.exito(`${grupo.materia.nombre} seleccionada correctamente`);
      // Actualizar lista de materias inscritas
      this.materiasInscritas = this.matriculaService.obtenerMateriasInscritas();
    } else {
      resultado.errores.forEach((error: any) => {
        this.notificacion.error(error.mensaje);
      });
    }

    if (resultado.advertencias && resultado.advertencias.length > 0) {
      resultado.advertencias.forEach((adv: string) => {
        this.notificacion.advertencia(adv);
      });
    }
  }

  removerMateria(grupoId: number): void {
    this.matriculaService.removerDelCarrito(grupoId);
    this.notificacion.info('Materia removida de tu selección');
    // Actualizar lista de materias inscritas
    this.materiasInscritas = this.matriculaService.obtenerMateriasInscritas();
  }

  /**
   * Verifica si una materia puede ser agregada
   * No se puede agregar si ya fue aprobada o está inscrita actualmente
   */
  puedaAgregarMateria(materiaId: number): boolean {
    return !this.materiasAprobadas.includes(materiaId) && 
           !this.materiasInscritas.includes(materiaId);
  }

  /**
   * Obtiene el motivo por el cual no se puede agregar una materia
   */
  obtenerMotivoBloqueo(materiaId: number): string {
    if (this.materiasAprobadas.includes(materiaId)) {
      return 'Ya aprobaste esta materia';
    }
    if (this.materiasInscritas.includes(materiaId)) {
      return 'Ya estás inscrito en esta materia';
    }
    return '';
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

  filtrar(): void {
    // El computed se actualiza automáticamente
  }
}
