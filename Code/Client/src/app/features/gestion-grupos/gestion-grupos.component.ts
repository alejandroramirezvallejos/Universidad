/**
 * Componente de Gestión de Grupos
 * Exclusivo para Director de Carrera
 * Permite crear grupos y asignar docentes
 * 
 * Heurística Nielsen #5: Prevención de errores
 * - Validaciones antes de crear grupos
 * 
 * Heurística Nielsen #6: Reconocimiento antes que recuerdo
 * - Información clara de materias y docentes disponibles
 */

import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { NotificacionService } from '../../core/services/notificacion.service';
import { OfertaAcademicaService } from '../../core/services/oferta-academica.service';
import { DocentesService } from '../../core/services/docentes.service';
import { AulasService } from '../../core/services/aulas.service';
import { ParalelosService } from '../../core/services/paralelos.service';
import { MateriasService } from '../../core/services/materias.service';
import { Materia, Usuario, Grupo, Horario, DiaSemana, Aula } from '../../models';

@Component({
  selector: 'app-gestion-grupos',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="gestion-grupos-page">
      <header class="pagina-header">
        <h1>Gestión de Grupos</h1>
        <p>Crea y administra grupos para la gestión académica</p>
      </header>

      <!-- Botón crear nuevo grupo -->
      <section class="acciones-principales">
        <button class="btn btn-primario" (click)="mostrarFormulario = !mostrarFormulario" *ngIf="!modoEdicion">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="12" y1="5" x2="12" y2="19"></line>
            <line x1="5" y1="12" x2="19" y2="12"></line>
          </svg>
          Crear Nuevo Grupo
        </button>
        <div class="alerta alerta-info" *ngIf="modoEdicion">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="10"></circle>
            <line x1="12" y1="8" x2="12" y2="16"></line>
            <line x1="12" y1="20" x2="12.01" y2="20"></line>
          </svg>
          <span>Estás editando un grupo existente</span>
        </div>
      </section>

      <!-- Formulario de creación -->
      <section class="formulario-grupo card" *ngIf="mostrarFormulario">
        <div class="card-header-with-badge">
          <h2 class="card-titulo">{{ modoEdicion ? 'Editar Grupo' : 'Nuevo Grupo' }}</h2>
          <span class="badge badge-info" *ngIf="modoEdicion">Editando</span>
        </div>
        
        <form (ngSubmit)="modoEdicion ? guardarEdicion() : crearGrupo()" class="form-grid">
          <!-- Selección de materia -->
          <div class="form-group">
            <label class="form-label">Materia *</label>
            <select 
              class="form-select" 
              [(ngModel)]="nuevoGrupo.materiaId" 
              name="materia"
              required
              (change)="onMateriaChange()"
            >
              <option value="">Seleccione una materia</option>
              <option *ngFor="let materia of materias" [value]="materia.id">
                {{ materia.codigo }} - {{ materia.nombre }}
              </option>
            </select>
          </div>

          <!-- Código del grupo -->
          <div class="form-group">
            <label class="form-label">Código del Grupo *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="nuevoGrupo.codigo" 
              name="codigo"
              placeholder="Ej: A, B, 01, 02"
              required
            >
          </div>

          <!-- Selección de docente -->
          <div class="form-group">
            <label class="form-label">Docente *</label>
            <select 
              class="form-select" 
              [(ngModel)]="nuevoGrupo.docenteId" 
              name="docente"
              required
            >
              <option value="">Seleccione un docente</option>
              <option *ngFor="let docente of docentes" [value]="docente.id">
                {{ docente.nombre }} {{ docente.apellido }}
              </option>
            </select>
          </div>

          <!-- Aula -->
          <div class="form-group">
            <label class="form-label">Aula *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="nuevoGrupo.aula" 
              name="aula"
              placeholder="Ej: Lab-101, Aula-205"
              required
            >
          </div>

          <!-- Cupo máximo -->
          <div class="form-group">
            <label class="form-label">Cupo Máximo *</label>
            <input 
              type="number" 
              class="form-input" 
              [(ngModel)]="nuevoGrupo.cupoMaximo" 
              name="cupo"
              min="1"
              max="50"
              required
            >
          </div>

          <!-- Horarios -->
          <div class="form-group form-group-full">
            <label class="form-label">Horarios *</label>
            <div class="horarios-container">
              <div class="horario-item" *ngFor="let horario of nuevoGrupo.horarios; let i = index">
                <select class="form-select" [(ngModel)]="horario.dia" [name]="'dia'+i">
                  <option value="">Día</option>
                  <option value="LUNES">Lunes</option>
                  <option value="MARTES">Martes</option>
                  <option value="MIERCOLES">Miércoles</option>
                  <option value="JUEVES">Jueves</option>
                  <option value="VIERNES">Viernes</option>
                  <option value="SABADO">Sábado</option>
                </select>

                <input 
                  type="time" 
                  class="form-input" 
                  [(ngModel)]="horario.horaInicio" 
                  [name]="'inicio'+i"
                >

                <input 
                  type="time" 
                  class="form-input" 
                  [(ngModel)]="horario.horaFin" 
                  [name]="'fin'+i"
                >

                <button 
                  type="button" 
                  class="btn btn-error btn-sm" 
                  (click)="eliminarHorario(i)"
                  *ngIf="nuevoGrupo.horarios.length > 1"
                >
                  ✕
                </button>
              </div>

              <button type="button" class="btn btn-ghost btn-sm" (click)="agregarHorario()">
                + Agregar horario
              </button>
            </div>
          </div>

          <!-- Botones -->
          <div class="form-actions form-group-full">
            <button type="submit" class="btn btn-primario" [disabled]="!formularioValido()">
              {{ modoEdicion ? 'Guardar Cambios' : 'Crear Grupo' }}
            </button>
            <button type="button" class="btn btn-ghost" (click)="cancelar()">
              Cancelar
            </button>
          </div>
        </form>
      </section>

      <!-- Lista de grupos existentes -->
      <section class="grupos-lista">
        <h2>Grupos Activos ({{ gruposExistentes().length }})</h2>
        
        <div class="grupos-grid">
          <div class="grupo-card card" *ngFor="let grupo of gruposExistentes()">
            <div class="grupo-header">
              <div>
                <h3>{{ grupo.materia.codigo }} - Grupo {{ grupo.codigo }}</h3>
                <p class="grupo-materia">{{ grupo.materia.nombre }}</p>
              </div>
              <span class="badge" [class.badge-exito]="grupo.cupoDisponible > 5" 
                                   [class.badge-advertencia]="grupo.cupoDisponible <= 5 && grupo.cupoDisponible > 0"
                                   [class.badge-error]="grupo.cupoDisponible === 0">
                {{ grupo.cupoDisponible }}/{{ grupo.cupoMaximo }} disponibles
              </span>
            </div>

            <div class="grupo-info">
              <div class="info-item">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                  <circle cx="12" cy="7" r="4"></circle>
                </svg>
                <span>{{ grupo.docente.nombre }} {{ grupo.docente.apellido }}</span>
              </div>

              <div class="info-item">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                </svg>
                <span>{{ grupo.aula.codigo }} - {{ grupo.aula.nombre }}</span>
              </div>

              <div class="info-item horarios-info">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="12" cy="12" r="10"></circle>
                  <polyline points="12 6 12 12 16 14"></polyline>
                </svg>
                <div class="horarios-lista">
                  <span *ngFor="let h of grupo.horarios" class="horario-tag">
                    {{ formatearDia(h.dia) }} {{ h.horaInicio }}-{{ h.horaFin }}
                  </span>
                </div>
              </div>
            </div>

            <div class="grupo-acciones">
              <button class="btn btn-ghost btn-sm" (click)="editarGrupo(grupo)">
                Editar
              </button>
              <button class="btn btn-error btn-sm" (click)="eliminarGrupo(grupo.id)">
                Eliminar
              </button>
            </div>
          </div>
        </div>

        <div class="estado-vacio" *ngIf="gruposExistentes().length === 0">
          <div class="estado-vacio-icono"></div>
          <p>No hay grupos creados aún</p>
          <p class="texto-secundario">Crea el primer grupo para comenzar</p>
        </div>
      </section>
    </div>
  `,
  styles: [`
    .gestion-grupos-page {
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

    .acciones-principales {
      margin-bottom: var(--espaciado-xl);
    }

    .card-header-with-badge {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: var(--espaciado-lg);
    }

    .card-header-with-badge .card-titulo {
      margin: 0;
    }

    .alerta {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      padding: var(--espaciado-md);
      border-radius: var(--radio-md);
      margin-top: var(--espaciado-sm);
    }

    .alerta-info {
      background-color: #e3f2fd;
      color: #1976d2;
      border: 1px solid #90caf9;
    }

    .alerta svg {
      flex-shrink: 0;
    }

    .formulario-grupo {
      margin-bottom: var(--espaciado-xl);
      padding: var(--espaciado-xl);
    }

    .card-titulo {
      font-size: var(--texto-lg);
      font-weight: 600;
      margin-bottom: var(--espaciado-lg);
    }

    .form-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: var(--espaciado-lg);
    }

    .form-group {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-xs);
    }

    .form-group-full {
      grid-column: 1 / -1;
    }

    .horarios-container {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-sm);
    }

    .horario-item {
      display: grid;
      grid-template-columns: 150px 1fr 1fr auto;
      gap: var(--espaciado-sm);
      align-items: center;
    }

    .form-actions {
      display: flex;
      gap: var(--espaciado-md);
      justify-content: flex-end;
      margin-top: var(--espaciado-md);
    }

    .grupos-lista h2 {
      font-size: var(--texto-lg);
      font-weight: 600;
      margin-bottom: var(--espaciado-md);
    }

    .grupos-grid {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
      gap: var(--espaciado-lg);
    }

    .grupo-card {
      padding: var(--espaciado-lg);
    }

    .grupo-header {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      margin-bottom: var(--espaciado-md);
      padding-bottom: var(--espaciado-md);
      border-bottom: 1px solid var(--color-borde);
    }

    .grupo-header h3 {
      font-size: var(--texto-base);
      font-weight: 600;
      margin-bottom: var(--espaciado-xs);
    }

    .grupo-materia {
      color: var(--color-texto-secundario);
      font-size: var(--texto-sm);
      margin: 0;
    }

    .grupo-info {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-sm);
      margin-bottom: var(--espaciado-md);
    }

    .info-item {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      color: var(--color-texto-secundario);
      font-size: var(--texto-sm);
    }

    .info-item svg {
      flex-shrink: 0;
    }

    .horarios-info {
      align-items: flex-start;
    }

    .horarios-lista {
      display: flex;
      flex-wrap: wrap;
      gap: var(--espaciado-xs);
    }

    .horario-tag {
      background-color: var(--color-primario-light);
      color: var(--color-primario);
      padding: 2px 8px;
      border-radius: var(--radio-sm);
      font-size: var(--texto-xs);
      font-weight: 500;
    }

    .grupo-acciones {
      display: flex;
      gap: var(--espaciado-sm);
      padding-top: var(--espaciado-md);
      border-top: 1px solid var(--color-borde);
    }

    .estado-vacio {
      text-align: center;
      padding: var(--espaciado-2xl);
      color: var(--color-texto-secundario);
    }

    .estado-vacio-icono {
      font-size: 64px;
      margin-bottom: var(--espaciado-md);
    }

    @media (max-width: 768px) {
      .grupos-grid {
        grid-template-columns: 1fr;
      }

      .horario-item {
        grid-template-columns: 1fr;
      }
    }
  `]
})
export class GestionGruposComponent implements OnInit {
  mostrarFormulario = false;
  modoEdicion = false;
  grupoEnEdicion: Grupo | null = null;
  
  materias: Materia[] = [];
  docentes: Usuario[] = [];
  
  nuevoGrupo = {
    materiaId: '',
    codigo: '',
    docenteId: '',
    aula: '',
    cupoMaximo: 30,
    horarios: [{ dia: '' as DiaSemana | '', horaInicio: '', horaFin: '' }]
  };

  gruposExistentes = signal<Grupo[]>([]);

  constructor(
    public authService: AuthService,
    private notificacion: NotificacionService,
    private ofertaService: OfertaAcademicaService,
    private docentesService: DocentesService,
    private aulasService: AulasService,
    private paralelosService: ParalelosService,
    private materiasService: MateriasService
  ) {}

  async ngOnInit(): Promise<void> {
    // Verificar que sea director
    if (this.authService.rol() !== 'DIRECTOR') {
      this.notificacion.error('Acceso denegado');
      return;
    }

    await this.cargarDatos();
  }

  async cargarDatos(): Promise<void> {
    try {
      // USANDO BACKEND: Cargar datos desde los servicios
      await Promise.all([
        this.materiasService.obtenerTodasLasMaterias(),
        this.docentesService.obtenerDocentes(),
        this.aulasService.obtenerAulas(),
        this.paralelosService.obtenerParalelos()
      ]);

      // Mapear datos del backend al formato del componente
      const materiasBackend = await this.materiasService.obtenerTodasLasMaterias();
      this.materias = materiasBackend.map((m: any) => ({
        id: parseInt(m.codigo.replace(/[^0-9]/g, '')),
        codigo: m.codigo,
        nombre: m.nombre,
        creditos: m.creditos,
        semestre: m.nivel || 1,
        horasTeoricas: 0,
        horasPracticas: 0,
        prerrequisitos: [],
        activa: m.activa !== false
      }));

      this.docentes = this.docentesService.docentes().map(docente => ({
        id: docente.id!,
        codigo: docente.codigo,
        nombre: docente.nombre,
        apellido: docente.apellido,
        email: docente.email,
        rol: 'DOCENTE' as const,
        activo: true
      }));
      
      // USANDO BACKEND: Cargar grupos (paralelos)
      const paralellosBackend = await this.paralelosService.obtenerParalelos();
      this.gruposExistentes.set(paralellosBackend);
    } catch (error) {
      console.error('Error cargando datos desde backend:', error);
      this.notificacion.error('Error al cargar datos del servidor.');
    }
  }

  onMateriaChange(): void {
    // Aquí podrías pre-llenar información basada en la materia seleccionada
  }

  agregarHorario(): void {
    this.nuevoGrupo.horarios.push({ dia: '' as DiaSemana | '', horaInicio: '', horaFin: '' });
  }

  eliminarHorario(index: number): void {
    this.nuevoGrupo.horarios.splice(index, 1);
  }

  formularioValido(): boolean {
    return !!(
      this.nuevoGrupo.materiaId &&
      this.nuevoGrupo.codigo &&
      this.nuevoGrupo.docenteId &&
      this.nuevoGrupo.aula &&
      this.nuevoGrupo.cupoMaximo > 0 &&
      this.nuevoGrupo.horarios.length > 0 &&
      this.nuevoGrupo.horarios.every(h => h.dia && h.horaInicio && h.horaFin)
    );
  }

  async crearGrupo(): Promise<void> {
    if (!this.formularioValido()) {
      this.notificacion.error('Completa todos los campos obligatorios');
      return;
    }

    const materia = this.materias.find(m => m.id === parseInt(this.nuevoGrupo.materiaId));
    const docente = this.docentes.find(d => d.id === parseInt(this.nuevoGrupo.docenteId));

    if (!materia || !docente) {
      this.notificacion.error('Materia o docente no encontrado');
      return;
    }

    try {
      // USANDO BACKEND: Crear paralelo
      // Backend espera ParaleloMateria con: codigo, materia, docente (codigo, nombre, apellido, email, especialidad), aula, cupoMaximo, horarios
      const docenteConCodigo = docente as any;
      const nuevoParalelo = {
        codigo: this.nuevoGrupo.codigo,
        materia: {
          codigo: materia.codigo,
          nombre: materia.nombre,
          creditos: materia.creditos,
          semestre: materia.semestre
        },
        docente: {
          codigo: docenteConCodigo.codigoDocente || docenteConCodigo.codigo || 'DOC-001',
          nombre: docente.nombre,
          apellido: docenteConCodigo.apellido || '',
          email: docenteConCodigo.email || '',
          especialidad: docenteConCodigo.especialidad || 'General',
          departamento: docenteConCodigo.departamento || 'General',
          activo: true
        },
        aula: {
          codigo: this.nuevoGrupo.aula,
          edificio: 'Edificio Principal',
          capacidad: this.nuevoGrupo.cupoMaximo,
          disponible: true
        },
        cupoMaximo: this.nuevoGrupo.cupoMaximo,
        horarios: this.nuevoGrupo.horarios.map(h => ({
          diaSemana: h.dia,
          horaInicio: h.horaInicio,
          horaFin: h.horaFin
        }))
      };

      await this.paralelosService.crearParalelo(nuevoParalelo);
      await this.cargarDatos();
      this.cancelar();
      this.notificacion.exito('Grupo creado exitosamente en el backend');
    } catch (error) {
      console.error('Error creando grupo:', error);
      this.notificacion.error('Error al crear el grupo. Verifica los datos.');
    }
  }

  editarGrupo(grupo: Grupo): void {
    this.modoEdicion = true;
    this.grupoEnEdicion = grupo;
    this.mostrarFormulario = true;
    
    // Pre-llenar el formulario con los datos del grupo
    this.nuevoGrupo = {
      materiaId: grupo.materia.id.toString(),
      codigo: grupo.codigo,
      docenteId: grupo.docente.id.toString(),
      aula: grupo.aula.codigo,
      cupoMaximo: grupo.cupoMaximo,
      horarios: grupo.horarios.map(h => ({
        dia: h.dia,
        horaInicio: h.horaInicio,
        horaFin: h.horaFin
      }))
    };
    
    // Scroll al formulario
    setTimeout(() => {
      document.querySelector('.formulario-grupo')?.scrollIntoView({ behavior: 'smooth' });
    }, 100);
  }

  async guardarEdicion(): Promise<void> {
    if (!this.formularioValido() || !this.grupoEnEdicion) {
      this.notificacion.error('Completa todos los campos obligatorios');
      return;
    }

    const materia = this.materias.find(m => m.id === parseInt(this.nuevoGrupo.materiaId));
    const docente = this.docentes.find(d => d.id === parseInt(this.nuevoGrupo.docenteId));

    if (!materia || !docente) {
      this.notificacion.error('Materia o docente no encontrado');
      return;
    }

    try {
      // USANDO BACKEND: Actualizar paralelo con TODOS los campos requeridos
      const docenteConCodigo = docente as any;
      const paraleloActualizado = {
        codigo: this.nuevoGrupo.codigo,
        materia: {
          codigo: materia.codigo,
          nombre: materia.nombre,
          creditos: materia.creditos,
          semestre: materia.semestre
        },
        docente: {
          codigo: docenteConCodigo.codigoDocente || docenteConCodigo.codigo || 'DOC-001',
          nombre: docente.nombre,
          apellido: docenteConCodigo.apellido || '',
          email: docenteConCodigo.email || '',
          especialidad: docenteConCodigo.especialidad || 'General',
          departamento: docenteConCodigo.departamento || 'General',
          activo: true
        },
        aula: {
          codigo: this.nuevoGrupo.aula,
          edificio: 'Edificio Principal',
          capacidad: this.nuevoGrupo.cupoMaximo,
          disponible: true
        },
        cupoMaximo: this.nuevoGrupo.cupoMaximo,
        horarios: this.nuevoGrupo.horarios.map(h => ({
          diaSemana: h.dia,
          horaInicio: h.horaInicio,
          horaFin: h.horaFin
        }))
      };

      console.log('[DEBUG] Actualizando paralelo:', this.grupoEnEdicion.codigo, 'con datos:', paraleloActualizado);
      
      const resultado = await this.paralelosService.actualizarParalelo(this.grupoEnEdicion.codigo, paraleloActualizado);
      
      if (resultado) {
        await this.cargarDatos();
        this.cancelar();
        this.notificacion.exito('Grupo actualizado exitosamente');
      } else {
        this.notificacion.error('Error al actualizar el grupo en el servidor');
      }
    } catch (error) {
      console.error('Error actualizando grupo:', error);
      this.notificacion.error('Error al actualizar el grupo.');
    }
  }

  async eliminarGrupo(grupoId: number): Promise<void> {
    try {
      // USANDO BACKEND: Eliminar paralelo
      const grupo = this.gruposExistentes().find(g => g.id === grupoId);
      if (!grupo) {
        this.notificacion.error('Grupo no encontrado');
        return;
      }

      await this.paralelosService.eliminarParalelo(grupo.codigo);
      await this.cargarDatos();
      this.notificacion.exito('Grupo eliminado exitosamente del backend');
    } catch (error) {
      console.error('Error eliminando grupo:', error);
      this.notificacion.error('Error al eliminar el grupo.');
    }
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.modoEdicion = false;
    this.grupoEnEdicion = null;
    this.nuevoGrupo = {
      materiaId: '',
      codigo: '',
      docenteId: '',
      aula: '',
      cupoMaximo: 30,
      horarios: [{ dia: '' as DiaSemana | '', horaInicio: '', horaFin: '' }]
    };
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
}
