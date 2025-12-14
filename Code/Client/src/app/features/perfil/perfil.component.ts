/**
 * Componente de Perfil de Usuario
 * Muestra y permite editar la información del usuario autenticado
 */

import { Component, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="perfil-container">
      <div class="perfil-header">
        <div class="avatar-grande">
          {{ obtenerIniciales() }}
        </div>
        <div class="usuario-info">
          <h1>{{ auth.nombreCompleto() }}</h1>
          <span class="badge badge-primario">{{ formatearRol(auth.rol()) }}</span>
        </div>
      </div>

      <div class="perfil-content">
        <!-- Información Personal -->
        <div class="card">
          <div class="card-header">
            <h2>Información Personal</h2>
            <button 
              class="btn btn-secundario btn-sm"
              *ngIf="!modoEdicion()"
              (click)="modoEdicion.set(true)"
            >
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
              </svg>
              Editar
            </button>
          </div>

          <div class="card-body" *ngIf="usuario()">
            <form (ngSubmit)="guardarCambios()" *ngIf="modoEdicion(); else vistaLectura">
              <div class="form-row">
                <div class="form-grupo">
                  <label for="nombre" class="form-label">Nombre</label>
                  <input 
                    type="text" 
                    id="nombre"
                    [(ngModel)]="nombre"
                    name="nombre"
                    class="form-input"
                    required
                  >
                </div>
                <div class="form-grupo">
                  <label for="apellido" class="form-label">Apellido</label>
                  <input 
                    type="text" 
                    id="apellido"
                    [(ngModel)]="apellido"
                    name="apellido"
                    class="form-input"
                    required
                  >
                </div>
              </div>

              <div class="form-grupo">
                <label for="email" class="form-label">Correo Electrónico</label>
                <input 
                  type="email" 
                  id="email"
                  [(ngModel)]="email"
                  name="email"
                  class="form-input"
                  disabled
                >
                <p class="form-hint">El correo no se puede modificar</p>
              </div>

              <!-- Información específica por rol -->
              <div *ngIf="usuario()?.rol === 'ESTUDIANTE'">
                <div class="form-grupo">
                  <label for="codigoEstudiante" class="form-label">Código de Estudiante</label>
                  <input 
                    type="text" 
                    id="codigoEstudiante"
                    [value]="getEstudiante()?.codigoEstudiante || ''"
                    class="form-input"
                    disabled
                  >
                </div>
                <div class="form-grupo">
                  <label for="carrera" class="form-label">Carrera</label>
                  <input 
                    type="text" 
                    id="carrera"
                    [value]="getEstudiante()?.carrera?.nombre || ''"
                    class="form-input"
                    disabled
                  >
                </div>
                <div class="form-grupo">
                  <label for="semestre" class="form-label">Semestre</label>
                  <input 
                    type="text" 
                    id="semestre"
                    [value]="(getEstudiante()?.semestre || 0) + '° Semestre'"
                    class="form-input"
                    disabled
                  >
                </div>
              </div>

              <div *ngIf="usuario()?.rol === 'DOCENTE'">
                <div class="form-grupo">
                  <label for="codigoDocente" class="form-label">Código de Docente</label>
                  <input 
                    type="text" 
                    id="codigoDocente"
                    [value]="getDocente()?.codigoDocente || ''"
                    class="form-input"
                    disabled
                  >
                </div>
                <div class="form-row">
                  <div class="form-grupo">
                    <label for="departamento" class="form-label">Departamento</label>
                    <input 
                      type="text" 
                      id="departamento"
                      [value]="getDocente()?.departamento || ''"
                      class="form-input"
                      disabled
                    >
                  </div>
                  <div class="form-grupo">
                    <label for="especialidad" class="form-label">Especialidad</label>
                    <input 
                      type="text" 
                      id="especialidad"
                      [value]="getDocente()?.especialidad || ''"
                      class="form-input"
                      disabled
                    >
                  </div>
                </div>
              </div>

              <div class="form-actions">
                <button type="button" class="btn btn-secundario" (click)="cancelarEdicion()">
                  Cancelar
                </button>
                <button type="submit" class="btn btn-primario">
                  Guardar Cambios
                </button>
              </div>
            </form>

            <ng-template #vistaLectura>
              <div class="info-grid">
                <div class="info-item">
                  <label>Nombre</label>
                  <p>{{ usuario()?.nombre }}</p>
                </div>
                <div class="info-item">
                  <label>Apellido</label>
                  <p>{{ usuario()?.apellido }}</p>
                </div>
                <div class="info-item">
                  <label>Correo Electrónico</label>
                  <p>{{ usuario()?.email }}</p>
                </div>
                <div class="info-item">
                  <label>Rol</label>
                  <p>{{ formatearRol(usuario()?.rol) }}</p>
                </div>

                <!-- Info específica estudiante -->
                <ng-container *ngIf="usuario()?.rol === 'ESTUDIANTE'">
                  <div class="info-item">
                    <label>Código de Estudiante</label>
                    <p>{{ getEstudiante()?.codigoEstudiante }}</p>
                  </div>
                  <div class="info-item">
                    <label>Carrera</label>
                    <p>{{ getEstudiante()?.carrera?.nombre }}</p>
                  </div>
                  <div class="info-item">
                    <label>Semestre</label>
                    <p>{{ getEstudiante()?.semestre }}° Semestre</p>
                  </div>
                  <div class="info-item">
                    <label>Fecha de Ingreso</label>
                    <p>{{ getEstudiante()?.fechaIngreso | date: 'dd/MM/yyyy' }}</p>
                  </div>
                </ng-container>

                <!-- Info específica docente -->
                <ng-container *ngIf="usuario()?.rol === 'DOCENTE'">
                  <div class="info-item">
                    <label>Código de Docente</label>
                    <p>{{ getDocente()?.codigoDocente }}</p>
                  </div>
                  <div class="info-item">
                    <label>Departamento</label>
                    <p>{{ getDocente()?.departamento }}</p>
                  </div>
                  <div class="info-item">
                    <label>Especialidad</label>
                    <p>{{ getDocente()?.especialidad }}</p>
                  </div>
                </ng-container>
              </div>
            </ng-template>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .perfil-container {
      max-width: 900px;
      margin: 0 auto;
      padding: var(--espaciado-lg);
    }

    .perfil-header {
      display: flex;
      align-items: center;
      gap: var(--espaciado-xl);
      margin-bottom: var(--espaciado-2xl);
      padding: var(--espaciado-2xl);
      background: linear-gradient(135deg, var(--color-primario), #7c3aed);
      border-radius: var(--radio-lg);
      color: white;
    }

    .avatar-grande {
      width: 120px;
      height: 120px;
      border-radius: 50%;
      background-color: rgba(255, 255, 255, 0.2);
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 3rem;
      font-weight: 700;
      border: 4px solid rgba(255, 255, 255, 0.3);
    }

    .usuario-info h1 {
      font-size: var(--texto-3xl);
      margin-bottom: var(--espaciado-sm);
    }

    .perfil-content {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-xl);
    }

    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: var(--espaciado-lg);
    }

    .card-header h2 {
      font-size: var(--texto-xl);
      font-weight: 600;
      margin: 0;
    }

    .info-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: var(--espaciado-xl);
    }

    .info-item {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-xs);
    }

    .info-item label {
      font-size: var(--texto-sm);
      font-weight: 600;
      color: var(--color-texto-secundario);
      text-transform: uppercase;
      letter-spacing: 0.5px;
    }

    .info-item p {
      font-size: var(--texto-base);
      color: var(--color-texto);
      margin: 0;
    }

    .form-row {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: var(--espaciado-md);
    }

    .form-actions {
      display: flex;
      justify-content: flex-end;
      gap: var(--espaciado-md);
      margin-top: var(--espaciado-lg);
      padding-top: var(--espaciado-lg);
      border-top: 1px solid var(--color-borde);
    }

    .text-secundario {
      color: var(--color-texto-secundario);
      margin-bottom: var(--espaciado-md);
    }

    @media (max-width: 768px) {
      .perfil-header {
        flex-direction: column;
        text-align: center;
      }

      .form-row {
        grid-template-columns: 1fr;
      }

      .info-grid {
        grid-template-columns: 1fr;
      }
    }
  `]
})
export class PerfilComponent {
  usuario = computed(() => this.auth.usuario());
  modoEdicion = signal(false);

  // Campos editables
  nombre = '';
  apellido = '';
  email = '';

  constructor(public auth: AuthService) {
    const user = this.usuario();
    if (user) {
      this.nombre = user.nombre;
      this.apellido = user.apellido;
      this.email = user.email;
    }
  }

  obtenerIniciales(): string {
    const user = this.usuario();
    if (!user) return '?';
    return `${user.nombre[0]}${user.apellido[0]}`.toUpperCase();
  }

  formatearRol(rol: string | null | undefined): string {
    if (!rol) return '';
    const roles: Record<string, string> = {
      'ESTUDIANTE': 'Estudiante',
      'DOCENTE': 'Docente',
      'DIRECTOR': 'Director de Carrera'
    };
    return roles[rol] || rol;
  }

  getEstudiante(): any {
    const user = this.usuario();
    if (user && user.rol === 'ESTUDIANTE') {
      return user;
    }
    return null;
  }

  getDocente(): any {
    const user = this.usuario();
    if (user && user.rol === 'DOCENTE') {
      return user;
    }
    return null;
  }

  esEstudiante(usuario: any): boolean {
    return usuario && usuario.rol === 'ESTUDIANTE' && 'codigoEstudiante' in usuario;
  }

  esDocente(usuario: any): boolean {
    return usuario && usuario.rol === 'DOCENTE' && 'codigoDocente' in usuario;
  }

  guardarCambios(): void {
    // TODO: Conectar con Spring Boot PATCH /api/usuarios/{id}
    const user = this.usuario();
    if (user) {
      const actualizado = { ...user, nombre: this.nombre, apellido: this.apellido };
      
      // Actualizar en localStorage
      localStorage.setItem('usuario', JSON.stringify(actualizado));
      
      // Actualizar usuarios registrados si existe
      const usuariosRegistrados = JSON.parse(localStorage.getItem('usuarios_registrados') || '[]');
      const index = usuariosRegistrados.findIndex((u: any) => u.id === user.id);
      if (index !== -1) {
        usuariosRegistrados[index] = actualizado;
        localStorage.setItem('usuarios_registrados', JSON.stringify(usuariosRegistrados));
      }
      
      // Recargar página para actualizar el estado
      window.location.reload();
    }
  }

  cancelarEdicion(): void {
    const user = this.usuario();
    if (user) {
      this.nombre = user.nombre;
      this.apellido = user.apellido;
      this.email = user.email;
    }
    this.modoEdicion.set(false);
  }
}
