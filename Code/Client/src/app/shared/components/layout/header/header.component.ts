/**
 * Componente Header - Barra de navegación superior
 *
 * Heurística Nielsen #1: Visibilidad del estado del sistema
 * - Muestra el usuario actual, rol y notificaciones
 *
 * Heurística Nielsen #3: Control y libertad del usuario
 * - Permite cerrar sesión fácilmente
 */

import { Component, EventEmitter, Output, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';
import { ActasService } from '../../../../core/services/actas.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <header class="header">
      <div class="header-izquierda">
        <button class="btn-menu" (click)="toggleSidebar.emit()" aria-label="Abrir menú">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <line x1="3" y1="12" x2="21" y2="12"></line>
            <line x1="3" y1="6" x2="21" y2="6"></line>
            <line x1="3" y1="18" x2="21" y2="18"></line>
          </svg>
        </button>

        <a routerLink="/" class="logo">
          <svg class="logo-icono" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
            <polyline points="9 22 9 12 15 12 15 22"></polyline>
          </svg>
          <span class="logo-texto">Universidad</span>
        </a>
      </div>

      <div class="header-centro">
        <div class="busqueda">
          <svg class="busqueda-icono" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <circle cx="11" cy="11" r="8"></circle>
            <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
          </svg>
          <input
            type="search"
            class="busqueda-input"
            placeholder="Buscar materias, docentes..."
            aria-label="Buscar"
          >
        </div>
      </div>

      <div class="header-derecha">
        <!-- Notificaciones -->
        <a routerLink="/notificaciones" class="btn-icono" aria-label="Notificaciones">
          <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
            <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
          </svg>
          <span class="notificacion-badge" *ngIf="tieneNotificaciones">{{ cantidadNotificaciones }}</span>
        </a>

        <!-- Perfil de usuario -->
        <div class="usuario-menu" *ngIf="authService.estaAutenticado()">
          <button class="usuario-btn" (click)="toggleMenuUsuario()">
            <div class="usuario-avatar">
              {{ obtenerIniciales() }}
            </div>
            <div class="usuario-info">
              <span class="usuario-nombre">{{ authService.nombreCompleto() }}</span>
              <span class="usuario-rol">{{ formatearRol(authService.rol()) }}</span>
            </div>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <polyline points="6 9 12 15 18 9"></polyline>
            </svg>
          </button>

          <div class="usuario-dropdown" *ngIf="menuAbierto">
            <a routerLink="/perfil" class="dropdown-item">
              <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                <circle cx="12" cy="7" r="4"></circle>
              </svg>
              Mi Perfil
            </a>
            <div class="dropdown-divider"></div>
            <button class="dropdown-item dropdown-item-danger" (click)="cerrarSesion()">
              <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
                <polyline points="16 17 21 12 16 7"></polyline>
                <line x1="21" y1="12" x2="9" y2="12"></line>
              </svg>
              Cerrar Sesión
            </button>
          </div>
        </div>
      </div>
    </header>
  `,
  styles: [`
    .header {
      display: flex;
      align-items: center;
      justify-content: space-between;
      gap: var(--espaciado-lg);
      height: var(--altura-header);
      padding: 0 var(--espaciado-lg);
      background-color: var(--color-fondo-card);
      border-bottom: 1px solid var(--color-borde);
      position: fixed;
      top: 0;
      left: 0;
      right: 0;
      z-index: 100;
    }

    .header-izquierda {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
    }

    .btn-menu {
      display: none;
      padding: var(--espaciado-sm);
      background: transparent;
      border: none;
      cursor: pointer;
      color: var(--color-texto-secundario);
      border-radius: var(--radio-md);
      transition: all var(--transicion-rapida);
    }

    .btn-menu:hover {
      background-color: var(--color-fondo);
      color: var(--color-texto);
    }

    .logo {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      text-decoration: none;
    }

    .logo-icono {
      width: 28px;
      height: 28px;
      color: var(--color-primario);
      flex-shrink: 0;
    }

    .logo-texto {
      font-size: var(--texto-xl);
      font-weight: 700;
      color: var(--color-texto);
    }

    .header-centro {
      flex: 1;
      max-width: 500px;
    }

    .busqueda {
      position: relative;
      width: 100%;
    }

    .busqueda-icono {
      position: absolute;
      left: var(--espaciado-md);
      top: 50%;
      transform: translateY(-50%);
      color: var(--color-texto-claro);
      pointer-events: none;
    }

    .busqueda-input {
      width: 100%;
      padding: var(--espaciado-sm) var(--espaciado-md);
      padding-left: 2.75rem;
      background-color: var(--color-fondo);
      border: 1px solid transparent;
      border-radius: var(--radio-full);
      font-size: var(--texto-sm);
      transition: all var(--transicion-rapida);
    }

    .busqueda-input:focus {
      outline: none;
      background-color: var(--color-fondo-card);
      border-color: var(--color-primario);
      box-shadow: 0 0 0 3px var(--color-primario-light);
    }

    .header-derecha {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
    }

    .btn-icono {
      position: relative;
      display: flex;
      align-items: center;
      justify-content: center;
      width: 40px;
      height: 40px;
      background: transparent;
      border: none;
      border-radius: var(--radio-md);
      cursor: pointer;
      color: var(--color-texto-secundario);
      transition: all var(--transicion-rapida);
      text-decoration: none;
    }

    .btn-icono:hover {
      background-color: var(--color-fondo);
      color: var(--color-texto);
    }

    .notificacion-badge {
      position: absolute;
      top: 4px;
      right: 4px;
      min-width: 18px;
      height: 18px;
      padding: 0 5px;
      background-color: var(--color-error);
      color: white;
      font-size: 0.65rem;
      font-weight: 600;
      border-radius: var(--radio-full);
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .usuario-menu {
      position: relative;
    }

    .usuario-btn {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      padding: var(--espaciado-xs) var(--espaciado-sm);
      background: transparent;
      border: none;
      border-radius: var(--radio-md);
      cursor: pointer;
      transition: all var(--transicion-rapida);
    }

    .usuario-btn:hover {
      background-color: var(--color-fondo);
    }

    .usuario-avatar {
      width: 36px;
      height: 36px;
      background-color: var(--color-primario);
      color: white;
      border-radius: var(--radio-full);
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: var(--texto-sm);
      font-weight: 600;
    }

    .usuario-info {
      display: flex;
      flex-direction: column;
      align-items: flex-start;
      text-align: left;
    }

    .usuario-nombre {
      font-size: var(--texto-sm);
      font-weight: 500;
      color: var(--color-texto);
    }

    .usuario-rol {
      font-size: var(--texto-xs);
      color: var(--color-texto-secundario);
    }

    .usuario-dropdown {
      position: absolute;
      top: calc(100% + var(--espaciado-sm));
      right: 0;
      min-width: 200px;
      background-color: var(--color-fondo-card);
      border: 1px solid var(--color-borde);
      border-radius: var(--radio-lg);
      box-shadow: var(--sombra-lg);
      overflow: hidden;
      animation: slideUp var(--transicion-normal);
    }

    .dropdown-item {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      width: 100%;
      padding: var(--espaciado-sm) var(--espaciado-md);
      background: transparent;
      border: none;
      color: var(--color-texto);
      font-size: var(--texto-sm);
      text-decoration: none;
      cursor: pointer;
      transition: background-color var(--transicion-rapida);
    }

    .dropdown-item:hover {
      background-color: var(--color-fondo);
    }

    .dropdown-item-danger {
      color: var(--color-error);
    }

    .dropdown-item-danger:hover {
      background-color: var(--color-error-light);
    }

    .dropdown-divider {
      height: 1px;
      background-color: var(--color-borde);
      margin: var(--espaciado-xs) 0;
    }

    @media (max-width: 768px) {
      .btn-menu {
        display: flex;
      }

      .header-centro {
        display: none;
      }

      .usuario-info {
        display: none;
      }

      .logo-texto {
        display: none;
      }
    }

    @keyframes slideUp {
      from {
        opacity: 0;
        transform: translateY(-10px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }
  `]
})
export class HeaderComponent {
  @Output() toggleSidebar = new EventEmitter<void>();

  menuAbierto = false;

  private actasService = inject(ActasService);

  constructor(public authService: AuthService) {}

  get cantidadNotificaciones(): number {
    return this.actasService.getCantidadNoLeidas();
  }

  get tieneNotificaciones(): boolean {
    return this.cantidadNotificaciones > 0;
  }

  obtenerIniciales(): string {
    const nombre = this.authService.nombreCompleto();
    if (!nombre) return '?';

    const partes = nombre.split(' ');
    return partes.map((p: string) => p[0]).join('').substring(0, 2).toUpperCase();
  }

  formatearRol(rol: string | null): string {
    if (!rol) return '';
    const roles: Record<string, string> = {
      'ESTUDIANTE': 'Estudiante',
      'DOCENTE': 'Docente',
      'DIRECTOR': 'Director de Carrera'
    };
    return roles[rol] ?? rol;
  }

  toggleMenuUsuario(): void {
    this.menuAbierto = !this.menuAbierto;
  }

  cerrarSesion(): void {
    this.menuAbierto = false;
    this.authService.logout();
  }
}
