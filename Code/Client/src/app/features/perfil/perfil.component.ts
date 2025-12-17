/**
 * Componente de Perfil de Usuario
 * Muestra y permite editar la información del usuario autenticado
 *
 * Heurística Nielsen #7: Flexibilidad y eficiencia de uso
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
    <div class="perfil-page">
      <!-- Header con gradiente -->
      <div class="perfil-banner">
        <div class="banner-content">
          <div class="avatar-container">
            <div class="avatar">
              {{ obtenerIniciales() }}
            </div>
            <div class="estado-activo"></div>
          </div>
          <div class="info-principal">
            <h1>{{ auth.nombreCompleto() }}</h1>
            <p class="email">{{ usuario()?.email }}</p>
            <div class="badges">
              <span class="badge" [class]="'badge-' + usuario()?.rol?.toLowerCase()">
                {{ formatearRol(auth.rol()) }}
              </span>
              <span class="badge badge-activo">Activo</span>
            </div>
          </div>
        </div>
      </div>

      <!-- Contenido principal -->
      <div class="perfil-content">
        <!-- Tarjetas de estadísticas -->
        <div class="stats-grid">
          <div class="stat-card">
            <div class="stat-icon estudiante">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M22 10v6M2 10l10-5 10 5-10 5z"></path>
                <path d="M6 12v5c0 2 2 3 6 3s6-1 6-3v-5"></path>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Rol</span>
              <span class="stat-value">{{ formatearRol(auth.rol()) }}</span>
            </div>
          </div>

          <div class="stat-card" *ngIf="usuario()?.rol === 'ESTUDIANTE'">
            <div class="stat-icon primario">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                <line x1="16" y1="2" x2="16" y2="6"></line>
                <line x1="8" y1="2" x2="8" y2="6"></line>
                <line x1="3" y1="10" x2="21" y2="10"></line>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Semestre</span>
              <span class="stat-value">{{ getEstudiante()?.semestre || 1 }}°</span>
            </div>
          </div>

          <div class="stat-card" *ngIf="usuario()?.rol === 'ESTUDIANTE'">
            <div class="stat-icon exito">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="3" width="7" height="7"></rect>
                <rect x="14" y="3" width="7" height="7"></rect>
                <rect x="14" y="14" width="7" height="7"></rect>
                <rect x="3" y="14" width="7" height="7"></rect>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Carrera</span>
              <span class="stat-value">{{ getEstudiante()?.carrera?.nombre || 'N/A' }}</span>
            </div>
          </div>

          <div class="stat-card" *ngIf="usuario()?.rol === 'DOCENTE'">
            <div class="stat-icon docente">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"></circle>
                <path d="M12 16v-4"></path>
                <path d="M12 8h.01"></path>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Especialidad</span>
              <span class="stat-value">{{ getDocente()?.especialidad || 'N/A' }}</span>
            </div>
          </div>

          <div class="stat-card" *ngIf="usuario()?.rol === 'DIRECTOR'">
            <div class="stat-icon director">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                <circle cx="8.5" cy="7" r="4"></circle>
                <polyline points="17 11 19 13 23 9"></polyline>
              </svg>
            </div>
            <div class="stat-info">
              <span class="stat-label">Departamento</span>
              <span class="stat-value">{{ getDirector()?.departamento || 'Dirección' }}</span>
            </div>
          </div>
        </div>

        <!-- Información detallada -->
        <div class="cards-grid">
          <!-- Información Personal -->
          <div class="card">
            <div class="card-header">
              <div class="card-title">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                  <circle cx="12" cy="7" r="4"></circle>
                </svg>
                <h2>Información Personal</h2>
              </div>
              <button
                class="btn-editar"
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
                <div class="form-grid">
                  <div class="form-grupo">
                    <label for="nombre">Nombre</label>
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
                    <label for="apellido">Apellido</label>
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
                  <label for="email">Correo Electrónico</label>
                  <input
                    type="email"
                    id="email"
                    [value]="email"
                    class="form-input disabled"
                    disabled
                  >
                  <span class="form-hint">El correo no se puede modificar</span>
                </div>

                <div class="form-acciones">
                  <button type="button" class="btn btn-cancelar" (click)="cancelarEdicion()">
                    Cancelar
                  </button>
                  <button type="submit" class="btn btn-guardar">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <polyline points="20 6 9 17 4 12"></polyline>
                    </svg>
                    Guardar Cambios
                  </button>
                </div>
              </form>

              <ng-template #vistaLectura>
                <div class="info-lista">
                  <div class="info-fila">
                    <span class="info-label">Nombre completo</span>
                    <span class="info-valor">{{ usuario()?.nombre }} {{ usuario()?.apellido }}</span>
                  </div>
                  <div class="info-fila">
                    <span class="info-label">Correo electrónico</span>
                    <span class="info-valor">{{ usuario()?.email }}</span>
                  </div>
                  <div class="info-fila">
                    <span class="info-label">Estado</span>
                    <span class="info-valor estado-activo-texto">
                      <span class="punto-verde"></span> Activo
                    </span>
                  </div>
                </div>
              </ng-template>
            </div>
          </div>

          <!-- Información Académica -->
          <div class="card">
            <div class="card-header">
              <div class="card-title">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M22 10v6M2 10l10-5 10 5-10 5z"></path>
                  <path d="M6 12v5c0 2 2 3 6 3s6-1 6-3v-5"></path>
                </svg>
                <h2>Información Académica</h2>
              </div>
            </div>

            <div class="card-body">
              <div class="info-lista">
                <!-- Estudiante -->
                <ng-container *ngIf="usuario()?.rol === 'ESTUDIANTE'">
                  <div class="info-fila">
                    <span class="info-label">Código de estudiante</span>
                    <span class="info-valor codigo">{{ getEstudiante()?.codigoEstudiante }}</span>
                  </div>
                  <div class="info-fila">
                    <span class="info-label">Carrera</span>
                    <span class="info-valor">{{ getEstudiante()?.carrera?.nombre }}</span>
                  </div>
                  <div class="info-fila">
                    <span class="info-label">Semestre actual</span>
                    <span class="info-valor">{{ getEstudiante()?.semestre }}° Semestre</span>
                  </div>
                  <div class="info-fila">
                    <span class="info-label">Fecha de ingreso</span>
                    <span class="info-valor">{{ formatearFecha(getEstudiante()?.fechaIngreso) }}</span>
                  </div>
                </ng-container>

                <!-- Docente -->
                <ng-container *ngIf="usuario()?.rol === 'DOCENTE'">
                  <div class="info-fila">
                    <span class="info-label">Código de docente</span>
                    <span class="info-valor codigo">{{ getDocente()?.codigoDocente }}</span>
                  </div>
                  <div class="info-fila">
                    <span class="info-label">Departamento</span>
                    <span class="info-valor">{{ getDocente()?.departamento }}</span>
                  </div>
                  <div class="info-fila">
                    <span class="info-label">Especialidad</span>
                    <span class="info-valor">{{ getDocente()?.especialidad }}</span>
                  </div>
                </ng-container>

                <!-- Director -->
                <ng-container *ngIf="usuario()?.rol === 'DIRECTOR'">
                  <div class="info-fila">
                    <span class="info-label">Código de director</span>
                    <span class="info-valor codigo">{{ getDirector()?.codigoDirector }}</span>
                  </div>
                  <div class="info-fila">
                    <span class="info-label">Departamento</span>
                    <span class="info-valor">{{ getDirector()?.departamento }}</span>
                  </div>
                </ng-container>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .perfil-page {
      min-height: 100%;
      background-color: var(--color-fondo);
    }

    /* Banner superior */
    .perfil-banner {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      padding: 2rem 2rem 3rem;
      position: relative;
    }

    .banner-content {
      max-width: 900px;
      margin: 0 auto;
      display: flex;
      align-items: center;
      gap: 2rem;
    }

    .avatar-container {
      position: relative;
    }

    .avatar {
      width: 120px;
      height: 120px;
      border-radius: 50%;
      background: rgba(255, 255, 255, 0.2);
      backdrop-filter: blur(10px);
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 2.5rem;
      font-weight: 700;
      color: white;
      border: 4px solid rgba(255, 255, 255, 0.3);
      box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
    }

    .estado-activo {
      position: absolute;
      bottom: 8px;
      right: 8px;
      width: 24px;
      height: 24px;
      background: #22c55e;
      border-radius: 50%;
      border: 4px solid white;
    }

    .info-principal h1 {
      color: white;
      font-size: 2rem;
      font-weight: 700;
      margin: 0 0 0.5rem 0;
    }

    .info-principal .email {
      color: rgba(255, 255, 255, 0.8);
      font-size: 1rem;
      margin: 0 0 1rem 0;
    }

    .badges {
      display: flex;
      gap: 0.5rem;
    }

    .badge {
      padding: 0.35rem 0.75rem;
      border-radius: 9999px;
      font-size: 0.75rem;
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 0.5px;
    }

    .badge-estudiante {
      background: rgba(59, 130, 246, 0.2);
      color: #93c5fd;
      border: 1px solid rgba(59, 130, 246, 0.3);
    }

    .badge-docente {
      background: rgba(16, 185, 129, 0.2);
      color: #6ee7b7;
      border: 1px solid rgba(16, 185, 129, 0.3);
    }

    .badge-director {
      background: rgba(245, 158, 11, 0.2);
      color: #fcd34d;
      border: 1px solid rgba(245, 158, 11, 0.3);
    }

    .badge-activo {
      background: rgba(34, 197, 94, 0.2);
      color: #86efac;
      border: 1px solid rgba(34, 197, 94, 0.3);
    }

    /* Contenido principal */
    .perfil-content {
      max-width: 900px;
      margin: -2rem auto 2rem;
      padding: 0 1rem;
      position: relative;
    }

    /* Grid de estadísticas */
    .stats-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
      gap: 1rem;
      margin-bottom: 1.5rem;
    }

    .stat-card {
      background: var(--color-fondo-card);
      border-radius: 12px;
      padding: 1.25rem;
      display: flex;
      align-items: center;
      gap: 1rem;
      box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
      border: 1px solid var(--color-borde);
    }

    .stat-icon {
      width: 48px;
      height: 48px;
      border-radius: 12px;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .stat-icon.estudiante {
      background: rgba(59, 130, 246, 0.1);
      color: #3b82f6;
    }

    .stat-icon.docente {
      background: rgba(16, 185, 129, 0.1);
      color: #10b981;
    }

    .stat-icon.director {
      background: rgba(245, 158, 11, 0.1);
      color: #f59e0b;
    }

    .stat-icon.primario {
      background: rgba(102, 126, 234, 0.1);
      color: #667eea;
    }

    .stat-icon.exito {
      background: rgba(16, 185, 129, 0.1);
      color: #10b981;
    }

    .stat-info {
      display: flex;
      flex-direction: column;
    }

    .stat-label {
      font-size: 0.75rem;
      color: var(--color-texto-secundario);
      text-transform: uppercase;
      letter-spacing: 0.5px;
    }

    .stat-value {
      font-size: 1rem;
      font-weight: 600;
      color: var(--color-texto);
    }

    /* Grid de tarjetas */
    .cards-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
      gap: 1.5rem;
    }

    .card {
      background: var(--color-fondo-card);
      border-radius: 16px;
      box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
      border: 1px solid var(--color-borde);
      overflow: hidden;
    }

    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 1.25rem 1.5rem;
      border-bottom: 1px solid var(--color-borde);
      background: var(--color-fondo);
    }

    .card-title {
      display: flex;
      align-items: center;
      gap: 0.75rem;
      color: var(--color-texto);
    }

    .card-title h2 {
      font-size: 1rem;
      font-weight: 600;
      margin: 0;
    }

    .btn-editar {
      display: flex;
      align-items: center;
      gap: 0.5rem;
      padding: 0.5rem 1rem;
      background: transparent;
      border: 1px solid var(--color-borde);
      border-radius: 8px;
      color: var(--color-texto-secundario);
      font-size: 0.875rem;
      cursor: pointer;
      transition: all 0.2s;
    }

    .btn-editar:hover {
      background: var(--color-primario);
      color: white;
      border-color: var(--color-primario);
    }

    .card-body {
      padding: 1.5rem;
    }

    /* Lista de información */
    .info-lista {
      display: flex;
      flex-direction: column;
    }

    .info-fila {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 1rem 0;
      border-bottom: 1px solid var(--color-borde);
    }

    .info-fila:last-child {
      border-bottom: none;
    }

    .info-label {
      font-size: 0.875rem;
      color: var(--color-texto-secundario);
    }

    .info-valor {
      font-size: 0.875rem;
      font-weight: 500;
      color: var(--color-texto);
    }

    .info-valor.codigo {
      font-family: monospace;
      background: var(--color-fondo);
      padding: 0.25rem 0.5rem;
      border-radius: 4px;
      font-size: 0.8rem;
    }

    .estado-activo-texto {
      display: flex;
      align-items: center;
      gap: 0.5rem;
      color: #22c55e;
    }

    .punto-verde {
      width: 8px;
      height: 8px;
      background: #22c55e;
      border-radius: 50%;
    }

    /* Formulario */
    .form-grid {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 1rem;
      margin-bottom: 1rem;
    }

    .form-grupo {
      display: flex;
      flex-direction: column;
      gap: 0.5rem;
      margin-bottom: 1rem;
    }

    .form-grupo label {
      font-size: 0.875rem;
      font-weight: 500;
      color: var(--color-texto);
    }

    .form-input {
      padding: 0.75rem 1rem;
      border: 1px solid var(--color-borde);
      border-radius: 8px;
      font-size: 0.875rem;
      transition: border-color 0.2s;
      background: var(--color-fondo-card);
      color: var(--color-texto);
    }

    .form-input:focus {
      outline: none;
      border-color: var(--color-primario);
      box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
    }

    .form-input.disabled {
      background: var(--color-fondo);
      color: var(--color-texto-secundario);
      cursor: not-allowed;
    }

    .form-hint {
      font-size: 0.75rem;
      color: var(--color-texto-claro);
    }

    .form-acciones {
      display: flex;
      justify-content: flex-end;
      gap: 0.75rem;
      margin-top: 1.5rem;
      padding-top: 1.5rem;
      border-top: 1px solid var(--color-borde);
    }

    .btn {
      display: flex;
      align-items: center;
      gap: 0.5rem;
      padding: 0.75rem 1.5rem;
      border-radius: 8px;
      font-size: 0.875rem;
      font-weight: 500;
      cursor: pointer;
      transition: all 0.2s;
    }

    .btn-cancelar {
      background: transparent;
      border: 1px solid var(--color-borde);
      color: var(--color-texto-secundario);
    }

    .btn-cancelar:hover {
      background: var(--color-fondo);
    }

    .btn-guardar {
      background: var(--color-primario);
      border: none;
      color: white;
    }

    .btn-guardar:hover {
      background: #5a67d8;
    }

    /* Responsive */
    @media (max-width: 768px) {
      .banner-content {
        flex-direction: column;
        text-align: center;
      }

      .badges {
        justify-content: center;
      }

      .cards-grid {
        grid-template-columns: 1fr;
      }

      .form-grid {
        grid-template-columns: 1fr;
      }

      .info-fila {
        flex-direction: column;
        align-items: flex-start;
        gap: 0.25rem;
      }
    }
  `]
})
export class PerfilComponent {
  usuario = computed(() => this.auth.usuario());
  modoEdicion = signal(false);

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
    return `${user.nombre[0] || ''}${user.apellido[0] || ''}`.toUpperCase();
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

  formatearFecha(fecha: any): string {
    if (!fecha) return 'No disponible';
    try {
      return new Date(fecha).toLocaleDateString('es-ES', {
        day: 'numeric',
        month: 'long',
        year: 'numeric'
      });
    } catch {
      return 'No disponible';
    }
  }

  getEstudiante(): any {
    const user = this.usuario();
    return user?.rol === 'ESTUDIANTE' ? user : null;
  }

  getDocente(): any {
    const user = this.usuario();
    return user?.rol === 'DOCENTE' ? user : null;
  }

  getDirector(): any {
    const user = this.usuario();
    return user?.rol === 'DIRECTOR' ? user : null;
  }

  guardarCambios(): void {
    const user = this.usuario();
    if (user) {
      const actualizado = { ...user, nombre: this.nombre, apellido: this.apellido };
      localStorage.setItem('usuario_actual', JSON.stringify(actualizado));
      window.location.reload();
    }
  }

  cancelarEdicion(): void {
    const user = this.usuario();
    if (user) {
      this.nombre = user.nombre;
      this.apellido = user.apellido;
    }
    this.modoEdicion.set(false);
  }
}
