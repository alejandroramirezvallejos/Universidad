/**
 * Componente de Gestión de Docentes
 * Exclusivo para Director de Carrera
 * Permite crear, editar y gestionar docentes
 */

import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { NotificacionService } from '../../core/services/notificacion.service';
import { DocentesService } from '../../core/services/docentes.service';
import { DtoDocente } from '../../models/backend-dtos';

@Component({
  selector: 'app-gestion-docentes',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="gestion-docentes-page">
      <header class="pagina-header">
        <h1>Gestión de Docentes</h1>
        <p>Administra el registro de docentes de la carrera</p>
      </header>

      <!-- Botón crear nuevo docente -->
      <section class="acciones-principales">
        <button class="btn btn-primario" (click)="mostrarFormulario = !mostrarFormulario">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="12" y1="5" x2="12" y2="19"></line>
            <line x1="5" y1="12" x2="19" y2="12"></line>
          </svg>
          {{ modoEdicion ? 'Cancelar Edición' : 'Crear Nuevo Docente' }}
        </button>
        <input 
          type="text" 
          class="form-input buscar-input" 
          placeholder="Buscar por nombre o especialidad..."
          [(ngModel)]="filtroBusqueda"
          (ngModelChange)="aplicarFiltros()"
        >
      </section>

      <!-- Formulario de creación/edición -->
      <section class="formulario-docente card" *ngIf="mostrarFormulario">
        <h2 class="card-titulo">{{ modoEdicion ? 'Editar Docente' : 'Nuevo Docente' }}</h2>
        
        <form (ngSubmit)="guardarDocente()" class="form-grid">
          <!-- Código -->
          <div class="form-group">
            <label class="form-label">Código de Docente *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="docenteForm.codigo" 
              name="codigo"
              placeholder="Ej: DOC-001"
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
              [(ngModel)]="docenteForm.nombre" 
              name="nombre"
              placeholder="Nombre del docente"
              required
            >
          </div>

          <!-- Apellido -->
          <div class="form-group">
            <label class="form-label">Apellido *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="docenteForm.apellido" 
              name="apellido"
              placeholder="Apellido del docente"
              required
            >
          </div>

          <!-- Email -->
          <div class="form-group">
            <label class="form-label">Email *</label>
            <input 
              type="email" 
              class="form-input" 
              [(ngModel)]="docenteForm.email" 
              name="email"
              placeholder="correo@universidad.edu"
              required
            >
          </div>

          <!-- Contraseña -->
          <div class="form-group">
            <label class="form-label">Contraseña *</label>
            <input 
              type="password" 
              class="form-input" 
              [(ngModel)]="docenteForm.contrasenna" 
              name="contrasenna"
              placeholder="Contraseña"
              required
            >
          </div>

          <!-- Departamento -->
          <div class="form-group">
            <label class="form-label">Departamento *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="docenteForm.departamento" 
              name="departamento"
              placeholder="Ej: Ingeniería, Ciencias"
              required
            >
          </div>

          <!-- Especialidad -->
          <div class="form-group">
            <label class="form-label">Especialidad *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="docenteForm.especialidad" 
              name="especialidad"
              placeholder="Ej: Matemáticas, Física, Programación"
              required
            >
          </div>

          <!-- Acciones -->
          <div class="form-actions form-group-full">
            <button type="button" class="btn btn-secundario" (click)="cancelar()">
              Cancelar
            </button>
            <button type="submit" class="btn btn-primario" [disabled]="!formularioValido()">
              {{ modoEdicion ? 'Actualizar' : 'Crear' }} Docente
            </button>
          </div>
        </form>
      </section>

      <!-- Estadísticas -->
      <section class="estadisticas-grid">
        <div class="card estadistica">
          <div class="estadistica-icono"></div>
          <div class="estadistica-info">
            <span class="estadistica-valor">{{ docentes().length }}</span>
            <span class="estadistica-label">Total Docentes</span>
          </div>
        </div>
        <div class="card estadistica">
          <div class="estadistica-icono"></div>
          <div class="estadistica-info">
            <span class="estadistica-valor">{{ docentesFiltrados().length }}</span>
            <span class="estadistica-label">Resultados Filtrados</span>
          </div>
        </div>
        <div class="card estadistica">
          <div class="estadistica-icono"></div>
          <div class="estadistica-info">
            <span class="estadistica-valor">{{ especialidadesUnicas() }}</span>
            <span class="estadistica-label">Especialidades</span>
          </div>
        </div>
      </section>

      <!-- Lista de docentes -->
      <section class="lista-docentes card">
        <h2 class="card-titulo">Docentes Registrados</h2>
        
        <div class="tabla-contenedor">
          <table class="tabla">
            <thead>
              <tr>
                <th>Código</th>
                <th>Nombre Completo</th>
                <th>Especialidad</th>
                <th class="acciones-col">Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let docente of docentesFiltrados()">
                <td><strong>{{ docente.codigo }}</strong></td>
                <td>{{ docente.nombre }}</td>
                <td>
                  <span class="badge badge-info">{{ docente.especialidad }}</span>
                </td>
                <td class="acciones-col">
                  <!-- ⚠️ EDICIÓN DESHABILITADA: El backend no tiene endpoint PUT /api/docentes/{codigo} -->
                  <!-- Para modificar un docente: eliminarlo y crear uno nuevo -->
                  <!--
                  <button 
                    class="btn btn-ghost btn-sm" 
                    (click)="editarDocente(docente)"
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
                    (click)="eliminarDocente(docente)"
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

        <div class="estado-vacio" *ngIf="docentesFiltrados().length === 0">
          <div class="estado-vacio-icono"></div>
          <p>No se encontraron docentes</p>
        </div>
      </section>
    </div>
  `,
  styles: [`
    .gestion-docentes-page {
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

    .formulario-docente {
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
export class GestionDocentesComponent implements OnInit {
  mostrarFormulario = false;
  modoEdicion = false;
  docenteEditando: DtoDocente | null = null;
  filtroBusqueda = '';

  docenteForm = {
    codigo: '',
    nombre: '',
    apellido: '',
    email: '',
    contrasenna: '',
    departamento: '',
    especialidad: ''
  };

  constructor(
    public authService: AuthService,
    private notificacion: NotificacionService,
    private docentesService: DocentesService
  ) {}
  
  // Accessors para los datos de los servicios
  get docentes() { return this.docentesService.docentes; }
  docentesFiltrados = signal<DtoDocente[]>([]);

  async ngOnInit(): Promise<void> {
    if (this.authService.rol() !== 'DIRECTOR') {
      this.notificacion.error('Acceso denegado');
      return;
    }

    await this.cargarDatos();
  }

  async cargarDatos(): Promise<void> {
    try {
      await this.docentesService.obtenerDocentes();
      this.aplicarFiltros();
    } catch (error) {
      console.error('Error cargando datos:', error);
      this.notificacion.error('Error al cargar docentes');
    }
  }

  aplicarFiltros(): void {
    let filtrados = this.docentes();

    if (this.filtroBusqueda) {
      const busqueda = this.filtroBusqueda.toLowerCase();
      filtrados = filtrados.filter(d => 
        d.nombre.toLowerCase().includes(busqueda) ||
        d.codigo.toLowerCase().includes(busqueda) ||
        d.especialidad.toLowerCase().includes(busqueda)
      );
    }

    this.docentesFiltrados.set(filtrados);
  }

  especialidadesUnicas(): number {
    const especialidades = new Set(this.docentes().map(d => d.especialidad));
    return especialidades.size;
  }

  formularioValido(): boolean {
    return !!(
      this.docenteForm.codigo &&
      this.docenteForm.nombre &&
      this.docenteForm.apellido &&
      this.docenteForm.email &&
      this.docenteForm.contrasenna &&
      this.docenteForm.departamento &&
      this.docenteForm.especialidad
    );
  }

  async guardarDocente(): Promise<void> {
    if (!this.formularioValido()) {
      this.notificacion.error('Completa todos los campos obligatorios');
      return;
    }

    try {
      if (this.modoEdicion && this.docenteEditando) {
        // Actualizar (por ahora no soportado por backend)
        this.notificacion.advertencia('Actualización no implementada en backend');
      } else {
        // Crear nuevo docente
        const nuevoDocente: DtoDocente = {
          codigo: this.docenteForm.codigo,
          nombre: this.docenteForm.nombre,
          apellido: this.docenteForm.apellido,
          email: this.docenteForm.email,
          contrasenna: this.docenteForm.contrasenna,
          departamento: this.docenteForm.departamento,
          especialidad: this.docenteForm.especialidad,
          activo: true
        };

        await this.docentesService.crearDocente(nuevoDocente);
        this.notificacion.exito('Docente creado exitosamente');
      }

      await this.cargarDatos();
      this.cancelar();
    } catch (error) {
      console.error('Error guardando docente:', error);
      this.notificacion.error('Error al guardar el docente');
    }
  }

  /*
   * ⚠️ MÉTODO DESHABILITADO
   * El backend no tiene endpoint PUT /api/docentes/{codigo}
   * Para modificar un docente: eliminarlo y crear uno nuevo
   */
  /*
  editarDocente(docente: DtoDocente): void {
    this.modoEdicion = true;
    this.docenteEditando = docente;
    this.docenteForm = {
      codigo: docente.codigo,
      nombre: docente.nombre,
      apellido: docente.apellido || '',
      email: docente.email,
      contrasenna: docente.contrasenna || '',
      departamento: docente.departamento || '',
      especialidad: docente.especialidad || ''
    };
    this.mostrarFormulario = true;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
  */

  async eliminarDocente(docente: DtoDocente): Promise<void> {
    const confirmar = window.confirm(
      `¿Estás seguro de eliminar al docente ${docente.nombre}?`
    );

    if (!confirmar) return;

    try {
      // Pasar el objeto completo según lo que espera el backend
      await this.docentesService.eliminarDocente({
        codigoDocente: docente.codigo,
        nombre: docente.nombre,
        especialidad: docente.especialidad
      });
      this.notificacion.exito('Docente eliminado exitosamente');
      await this.cargarDatos();
    } catch (error) {
      console.error('Error eliminando docente:', error);
      this.notificacion.error('Error al eliminar el docente');
    }
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.modoEdicion = false;
    this.docenteEditando = null;
    this.docenteForm = {
      codigo: '',
      nombre: '',
      apellido: '',
      email: '',
      contrasenna: '',
      departamento: '',
      especialidad: ''
    };
  }
}
