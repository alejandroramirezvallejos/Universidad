/**
 * Componente de Gestión de Estudiantes
 * Exclusivo para Director de Carrera
 * Permite crear, editar y gestionar estudiantes
 */

import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { NotificacionService } from '../../core/services/notificacion.service';
import { EstudiantesService } from '../../core/services/estudiantes.service';
import { CarrerasService } from '../../core/services/carreras.service';
import { DtoEstudiante, DtoCarrera } from '../../models/backend-dtos';

@Component({
  selector: 'app-gestion-estudiantes',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="gestion-estudiantes-page">
      <header class="pagina-header">
        <h1>Gestión de Estudiantes</h1>
        <p>Administra el registro de estudiantes de la carrera</p>
      </header>

      <!-- Botón crear nuevo estudiante -->
      <section class="acciones-principales">
        <button class="btn btn-primario" (click)="mostrarFormulario = !mostrarFormulario">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="12" y1="5" x2="12" y2="19"></line>
            <line x1="5" y1="12" x2="19" y2="12"></line>
          </svg>
          {{ modoEdicion ? 'Cancelar Edición' : 'Crear Nuevo Estudiante' }}
        </button>
        <input 
          type="text" 
          class="form-input buscar-input" 
          placeholder="Buscar por nombre..."
          [(ngModel)]="filtroBusqueda"
          (ngModelChange)="aplicarFiltros()"
        >
      </section>

      <!-- Formulario de creación/edición -->
      <section class="formulario-estudiante card" *ngIf="mostrarFormulario">
        <h2 class="card-titulo">{{ modoEdicion ? 'Editar Estudiante' : 'Nuevo Estudiante' }}</h2>
        
        <form (ngSubmit)="guardarEstudiante()" class="form-grid">
          <!-- Código -->
          <div class="form-group">
            <label class="form-label">Código de Estudiante *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="estudianteForm.codigo" 
              name="codigo"
              placeholder="Ej: EST-001"
              required
              [disabled]="modoEdicion"
            >
          </div>

          <!-- Nombre -->
          <div class="form-group">
            <label class="form-label">Nombre *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="estudianteForm.nombre" 
              name="nombre"
              placeholder="Nombre"
              required
            >
          </div>

          <!-- Apellido -->
          <div class="form-group">
            <label class="form-label">Apellido *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="estudianteForm.apellido" 
              name="apellido"
              placeholder="Apellido"
              required
            >
          </div>

          <!-- Email -->
          <div class="form-group">
            <label class="form-label">Email *</label>
            <input 
              type="email" 
              class="form-input" 
              [(ngModel)]="estudianteForm.email" 
              name="email"
              placeholder="correo@ucb.edu.bo"
              required
            >
          </div>

          <!-- Contraseña -->
          <div class="form-group" *ngIf="!modoEdicion">
            <label class="form-label">Contraseña *</label>
            <input 
              type="password" 
              class="form-input" 
              [(ngModel)]="estudianteForm.contrasenna" 
              name="contrasenna"
              placeholder="Contraseña"
              required
            >
          </div>

          <!-- Carrera -->
          <div class="form-group">
            <label class="form-label">Carrera *</label>
            <select 
              class="form-input" 
              [(ngModel)]="estudianteForm.carreraCodigo" 
              name="carrera"
              required
            >
              <option value="">Seleccione una carrera</option>
              <option *ngFor="let carrera of carreras()" [value]="carrera.codigo">
                {{ carrera.nombre }}
              </option>
            </select>
          </div>

          <!-- Semestre -->
          <div class="form-group">
            <label class="form-label">Semestre</label>
            <input 
              type="number" 
              class="form-input" 
              [(ngModel)]="estudianteForm.semestre" 
              name="semestre"
              placeholder="1"
              min="1"
              max="10"
            >
          </div>

          <!-- Acciones -->
          <div class="form-actions form-group-full">
            <button type="button" class="btn btn-secundario" (click)="cancelar()">
              Cancelar
            </button>
            <button type="submit" class="btn btn-primario" [disabled]="!formularioValido()">
              {{ modoEdicion ? 'Actualizar' : 'Crear' }} Estudiante
            </button>
          </div>
        </form>
      </section>

      <!-- Estadísticas -->
      <section class="estadisticas-grid">
        <div class="card estadistica">
          <div class="estadistica-icono"></div>
          <div class="estadistica-info">
            <span class="estadistica-valor">{{ estudiantes().length }}</span>
            <span class="estadistica-label">Total Estudiantes</span>
          </div>
        </div>
        <div class="card estadistica">
          <div class="estadistica-icono"></div>
          <div class="estadistica-info">
            <span class="estadistica-valor">{{ estudiantesFiltrados().length }}</span>
            <span class="estadistica-label">Resultados Filtrados</span>
          </div>
        </div>
      </section>

      <!-- Lista de estudiantes -->
      <section class="lista-estudiantes card">
        <h2 class="card-titulo">Estudiantes Registrados</h2>
        
        <div class="tabla-contenedor">
          <table class="tabla">
            <thead>
              <tr>
                <th>Código</th>
                <th>Nombre Completo</th>
                <th>Email</th>
                <th class="acciones-col">Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let estudiante of estudiantesFiltrados()">
                <td><strong>{{ estudiante.codigo }}</strong></td>
                <td>{{ estudiante.nombre }}</td>
                <td>{{ estudiante.email }}</td>
                <td class="acciones-col">
                  <!-- ⚠️ EDICIÓN DESHABILITADA: El backend no tiene endpoint PUT /api/estudiantes/{codigo}
                  <button 
                    class="btn btn-ghost btn-sm" 
                    (click)="editarEstudiante(estudiante)"
                    title="Editar"
                  >
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                      <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                    </svg>
                  </button>
                  -->
                  <button 
                    class="btn btn-error btn-sm" 
                    (click)="eliminarEstudiante(estudiante)"
                    title="Eliminar"
                  >
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <polyline points="3 6 5 6 21 6"></polyline>
                      <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                    </svg>
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <div class="estado-vacio" *ngIf="estudiantesFiltrados().length === 0">
          <div class="estado-vacio-icono"></div>
          <p>No se encontraron estudiantes</p>
        </div>
      </section>
    </div>
  `,
  styles: [`
    .gestion-estudiantes-page {
      max-width: var(--ancho-maximo);
      margin: 0 auto;
    }

    .pagina-header {
      margin-bottom: var(--espaciado-xl);
    }

    .acciones-principales {
      display: flex;
      gap: var(--espaciado-md);
      margin-bottom: var(--espaciado-xl);
      align-items: center;
    }

    .buscar-input {
      max-width: 300px;
      margin-left: auto;
    }

    .formulario-estudiante {
      margin-bottom: var(--espaciado-xl);
    }

    .form-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: var(--espaciado-lg);
    }

    .form-group-full {
      grid-column: 1 / -1;
    }

    .form-actions {
      display: flex;
      gap: var(--espaciado-md);
      justify-content: flex-end;
    }

    .estadisticas-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
      gap: var(--espaciado-md);
      margin-bottom: var(--espaciado-xl);
    }

    .estadistica {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
    }

    .estadistica-icono {
      font-size: 2rem;
    }

    .estadistica-info {
      display: flex;
      flex-direction: column;
    }

    .estadistica-valor {
      font-size: var(--texto-xl);
      font-weight: 700;
    }

    .estadistica-label {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .tabla-contenedor {
      overflow-x: auto;
    }

    .tabla {
      width: 100%;
      border-collapse: collapse;
    }

    .tabla th {
      text-align: left;
      padding: var(--espaciado-md);
      border-bottom: 2px solid var(--color-borde);
      font-weight: 600;
    }

    .tabla td {
      padding: var(--espaciado-md);
      border-bottom: 1px solid var(--color-borde);
    }

    .tabla tbody tr:hover {
      background-color: var(--color-fondo);
    }

    .acciones-col {
      width: 120px;
      text-align: right;
    }

    .acciones-col button {
      margin-left: var(--espaciado-xs);
    }

    .estado-vacio {
      text-align: center;
      padding: var(--espaciado-2xl);
      color: var(--color-texto-secundario);
    }

    .estado-vacio-icono {
      font-size: 3rem;
      margin-bottom: var(--espaciado-md);
    }
  `]
})
export class GestionEstudiantesComponent implements OnInit {
  mostrarFormulario = false;
  modoEdicion = false;
  estudianteEditando: DtoEstudiante | null = null;
  filtroBusqueda = '';
  
  estudiantesFiltrados = signal<DtoEstudiante[]>([]);

  estudianteForm = {
    codigo: '',
    nombre: '',
    apellido: '',
    email: '',
    contrasenna: '',
    carreraCodigo: '',
    semestre: 1
  };

  constructor(
    public authService: AuthService,
    private notificacion: NotificacionService,
    private estudiantesService: EstudiantesService,
    private carrerasService: CarrerasService
  ) {}
  
  // Accessors para los datos de los servicios
  get estudiantes() { return this.estudiantesService.estudiantes; }
  get carreras() { return this.carrerasService.carreras; }

  async ngOnInit(): Promise<void> {
    if (this.authService.rol() !== 'DIRECTOR') {
      this.notificacion.error('Acceso denegado');
      return;
    }

    await this.cargarDatos();
  }

  async cargarDatos(): Promise<void> {
    try {
      await Promise.all([
        this.estudiantesService.obtenerEstudiantes(),
        this.carrerasService.obtenerCarreras()
      ]);
      this.aplicarFiltros();
    } catch (error) {
      console.error('Error cargando datos:', error);
      this.notificacion.error('Error al cargar estudiantes');
    }
  }

  aplicarFiltros(): void {
    let filtrados = this.estudiantes();

    if (this.filtroBusqueda) {
      const busqueda = this.filtroBusqueda.toLowerCase();
      filtrados = filtrados.filter(e => 
        e.nombre.toLowerCase().includes(busqueda) ||
        e.codigo.toLowerCase().includes(busqueda) ||
        e.email.toLowerCase().includes(busqueda)
      );
    }

    this.estudiantesFiltrados.set(filtrados);
  }

  formularioValido(): boolean {
    return !!(
      this.estudianteForm.codigo &&
      this.estudianteForm.nombre &&
      this.estudianteForm.apellido &&
      this.estudianteForm.email &&
      this.estudianteForm.contrasenna &&
      this.estudianteForm.carreraCodigo &&
      this.estudianteForm.semestre
    );
  }

  async guardarEstudiante(): Promise<void> {
    if (!this.formularioValido()) {
      this.notificacion.error('Completa todos los campos obligatorios');
      return;
    }

    try {
      if (this.modoEdicion && this.estudianteEditando) {
        // Actualizar (por ahora no soportado por backend)
        this.notificacion.advertencia('Actualización no implementada en backend');
      } else {
        // Buscar la carrera seleccionada
        const carreraSeleccionada = this.carreras().find(c => c.codigo === this.estudianteForm.carreraCodigo);
        
        // Crear nuevo estudiante
        const nuevoEstudiante: DtoEstudiante = {
          codigo: this.estudianteForm.codigo,
          nombre: this.estudianteForm.nombre,
          apellido: this.estudianteForm.apellido,
          email: this.estudianteForm.email,
          contrasenna: this.estudianteForm.contrasenna,
          semestre: this.estudianteForm.semestre,
          carrera: carreraSeleccionada
        };

        await this.estudiantesService.crearEstudiante(nuevoEstudiante);
        this.notificacion.exito('Estudiante creado exitosamente');
      }

      await this.cargarDatos();
      this.cancelar();
    } catch (error) {
      console.error('Error guardando estudiante:', error);
      this.notificacion.error('Error al guardar el estudiante');
    }
  }

  /* ⚠️ MÉTODO DESHABILITADO - El backend no tiene endpoint PUT /api/estudiantes/{codigo}
   * Este método está comentado porque el backend solo soporta:
   * - POST /api/estudiantes (crear)
   * - GET /api/estudiantes (listar)
   * - DELETE /api/estudiantes/{codigo} (eliminar)
   * Descomentar cuando el backend agregue el endpoint PUT
   */
  /*
  editarEstudiante(estudiante: DtoEstudiante): void {
    this.modoEdicion = true;
    this.estudianteEditando = estudiante;
    this.estudianteForm = {
      codigo: estudiante.codigo,
      nombre: estudiante.nombre,
      apellido: estudiante.apellido || '',
      email: estudiante.email,
      contrasenna: estudiante.contrasenna || '',
      carreraCodigo: estudiante.carrera?.codigo || '',
      semestre: estudiante.semestre || 1
    };
    this.mostrarFormulario = true;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
  */

  async eliminarEstudiante(estudiante: DtoEstudiante): Promise<void> {
    const confirmar = window.confirm(
      `¿Estás seguro de eliminar al estudiante ${estudiante.nombre}?`
    );

    if (!confirmar) return;

    try {
      // Pasar el objeto completo del estudiante según lo que espera el backend
      await this.estudiantesService.eliminarEstudiante(estudiante);
      this.notificacion.exito('Estudiante eliminado exitosamente');
      await this.cargarDatos();
    } catch (error) {
      console.error('Error eliminando estudiante:', error);
      this.notificacion.error('Error al eliminar el estudiante');
    }
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.modoEdicion = false;
    this.estudianteEditando = null;
    this.estudianteForm = {
      codigo: '',
      nombre: '',
      apellido: '',
      email: '',
      contrasenna: '',
      carreraCodigo: '',
      semestre: 1
    };
  }
}
