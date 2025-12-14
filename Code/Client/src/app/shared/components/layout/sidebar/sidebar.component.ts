/**
 * Componente Sidebar - Navegación lateral
 * 
 * Heurística Nielsen #2: Coincidencia entre el sistema y el mundo real
 * - Iconos y etiquetas claras que reflejan terminología universitaria
 * 
 * Heurística Nielsen #6: Reconocimiento antes que recuerdo
 * - Menú siempre visible con opciones claras
 */

import { Component, Input, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';
import { RolUsuario } from '../../../../models';

interface MenuItem {
  label: string;
  icono: string;
  ruta: string;
  roles: RolUsuario[];
  badge?: number;
}

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <aside class="sidebar" [class.sidebar-abierto]="abierto">
      <nav class="sidebar-nav">
        <!-- Sección Principal -->
        <div class="nav-seccion">
          <span class="nav-seccion-titulo">Principal</span>
          
          <a 
            *ngFor="let item of menuPrincipal()" 
            [routerLink]="item.ruta"
            routerLinkActive="nav-item-activo"
            class="nav-item"
          >
            <span class="nav-item-icono" [innerHTML]="item.icono"></span>
            <span class="nav-item-label">{{ item.label }}</span>
            <span class="nav-item-badge" *ngIf="item.badge">{{ item.badge }}</span>
          </a>
        </div>

        <!-- Sección Académica -->
        <div class="nav-seccion">
          <span class="nav-seccion-titulo">Académico</span>
          
          <a 
            *ngFor="let item of menuAcademico()" 
            [routerLink]="item.ruta"
            routerLinkActive="nav-item-activo"
            class="nav-item"
          >
            <span class="nav-item-icono" [innerHTML]="item.icono"></span>
            <span class="nav-item-label">{{ item.label }}</span>
            <span class="nav-item-badge" *ngIf="item.badge">{{ item.badge }}</span>
          </a>
        </div>

        <!-- Sección Administración (solo admin/director) -->
        <div class="nav-seccion" *ngIf="menuAdministracion().length > 0">
          <span class="nav-seccion-titulo">Administración</span>
          
          <a 
            *ngFor="let item of menuAdministracion()" 
            [routerLink]="item.ruta"
            routerLinkActive="nav-item-activo"
            class="nav-item"
          >
            <span class="nav-item-icono" [innerHTML]="item.icono"></span>
            <span class="nav-item-label">{{ item.label }}</span>
          </a>
        </div>
      </nav>

      <!-- Footer del Sidebar -->
      <div class="sidebar-footer">
        <div class="gestion-actual">
          <span class="gestion-label">Gestión actual</span>
          <span class="gestion-valor">II-2025</span>
        </div>
      </div>
    </aside>

    <!-- Overlay para móvil -->
    <div 
      class="sidebar-overlay" 
      [class.sidebar-overlay-visible]="abierto"
      (click)="cerrar()"
    ></div>
  `,
  styles: [`
    .sidebar {
      position: fixed;
      top: var(--altura-header);
      left: 0;
      bottom: 0;
      width: var(--ancho-sidebar);
      background-color: var(--color-fondo-card);
      border-right: 1px solid var(--color-borde);
      display: flex;
      flex-direction: column;
      z-index: 90;
      transition: transform var(--transicion-normal);
    }

    .sidebar-nav {
      flex: 1;
      padding: var(--espaciado-md);
      overflow-y: auto;
    }

    .nav-seccion {
      margin-bottom: var(--espaciado-lg);
    }

    .nav-seccion-titulo {
      display: block;
      padding: var(--espaciado-sm) var(--espaciado-md);
      font-size: var(--texto-xs);
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 0.05em;
      color: var(--color-texto-claro);
    }

    .nav-item {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
      padding: var(--espaciado-sm) var(--espaciado-md);
      color: var(--color-texto-secundario);
      text-decoration: none;
      border-radius: var(--radio-md);
      transition: all var(--transicion-rapida);
      margin-bottom: 2px;
    }

    .nav-item:hover {
      background-color: var(--color-fondo);
      color: var(--color-texto);
    }

    .nav-item-activo {
      background-color: var(--color-primario-light);
      color: var(--color-primario);
      font-weight: 500;
    }

    .nav-item-icono {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 20px;
      height: 20px;
    }

    .nav-item-icono :deep(svg) {
      width: 20px;
      height: 20px;
    }

    .nav-item-label {
      flex: 1;
      font-size: var(--texto-sm);
    }

    .nav-item-badge {
      padding: 2px 8px;
      background-color: var(--color-primario);
      color: white;
      font-size: var(--texto-xs);
      font-weight: 600;
      border-radius: var(--radio-full);
    }

    .sidebar-footer {
      padding: var(--espaciado-md);
      border-top: 1px solid var(--color-borde);
    }

    .gestion-actual {
      display: flex;
      flex-direction: column;
      padding: var(--espaciado-md);
      background-color: var(--color-fondo);
      border-radius: var(--radio-md);
    }

    .gestion-label {
      font-size: var(--texto-xs);
      color: var(--color-texto-claro);
    }

    .gestion-valor {
      font-size: var(--texto-sm);
      font-weight: 600;
      color: var(--color-primario);
    }

    .sidebar-overlay {
      display: none;
      position: fixed;
      inset: 0;
      background-color: rgba(0, 0, 0, 0.5);
      z-index: 80;
    }

    @media (max-width: 768px) {
      .sidebar {
        transform: translateX(-100%);
      }

      .sidebar-abierto {
        transform: translateX(0);
      }

      .sidebar-overlay-visible {
        display: block;
      }
    }
  `]
})
export class SidebarComponent {
  @Input() abierto = false;

  constructor(private authService: AuthService) {}

  // Iconos SVG inline
  private iconos = {
    inicio: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="m3 9 9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline></svg>',
    matricula: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg>',
    horario: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect><line x1="16" y1="2" x2="16" y2="6"></line><line x1="8" y1="2" x2="8" y2="6"></line><line x1="3" y1="10" x2="21" y2="10"></line></svg>',
    notas: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline><line x1="16" y1="13" x2="8" y2="13"></line><line x1="16" y1="17" x2="8" y2="17"></line><polyline points="10 9 9 9 8 9"></polyline></svg>',
    historial: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"></circle><polyline points="12 6 12 12 16 14"></polyline></svg>',
    oferta: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path><path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path></svg>',
    grupos: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>',
    actas: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline><line x1="12" y1="18" x2="12" y2="12"></line><line x1="9" y1="15" x2="15" y2="15"></line></svg>',
    usuarios: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>',
    estudiantes: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M22 10v6M2 10l10-5 10 5-10 5z"></path><path d="M6 12v5c3 3 9 3 12 0v-5"></path></svg>',
    docentes: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="8" r="5"></circle><path d="M3 21v-2a7 7 0 0 1 7-7"></path><path d="M16 11l2 2 4-4"></path></svg>',
    aulas: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="2" y="7" width="20" height="14" rx="2" ry="2"></rect><path d="M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16"></path></svg>',
    gestiones: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="3" width="18" height="18" rx="2" ry="2"></rect><line x1="3" y1="9" x2="21" y2="9"></line><line x1="9" y1="21" x2="9" y2="9"></line></svg>',
    reportes: '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg>'
  };

  menuPrincipal = computed<MenuItem[]>(() => {
    const rol = this.authService.rol();
    const items: MenuItem[] = [
      { label: 'Inicio', icono: this.iconos.inicio, ruta: '/dashboard', roles: ['ESTUDIANTE', 'DOCENTE', 'DIRECTOR'] }
    ];

    return items.filter(item => !rol || item.roles.includes(rol as RolUsuario));
  });

  menuAcademico = computed<MenuItem[]>(() => {
    const rol = this.authService.rol();
    const items: MenuItem[] = [
      { label: 'Oferta Académica', icono: this.iconos.oferta, ruta: '/oferta-academica', roles: ['ESTUDIANTE'] },
      { label: 'Mi Matrícula', icono: this.iconos.matricula, ruta: '/matricula', roles: ['ESTUDIANTE'] },
      { label: 'Mi Horario', icono: this.iconos.horario, ruta: '/horario', roles: ['ESTUDIANTE'] },
      { label: 'Mis Calificaciones', icono: this.iconos.notas, ruta: '/calificaciones', roles: ['ESTUDIANTE'] },
      { label: 'Historial Académico', icono: this.iconos.historial, ruta: '/historial', roles: ['ESTUDIANTE'] },
      { label: 'Registro de Notas', icono: this.iconos.notas, ruta: '/calificaciones', roles: ['DOCENTE'] }
    ];

    return items.filter(item => !rol || item.roles.includes(rol as RolUsuario));
  });

  menuAdministracion = computed<MenuItem[]>(() => {
    const rol = this.authService.rol();
    const items: MenuItem[] = [
      { label: 'Gestión de Estudiantes', icono: this.iconos.estudiantes, ruta: '/gestion-estudiantes', roles: ['DIRECTOR'] },
      { label: 'Gestión de Docentes', icono: this.iconos.docentes, ruta: '/gestion-docentes', roles: ['DIRECTOR'] },
      { label: 'Gestión de Aulas', icono: this.iconos.aulas, ruta: '/gestion-aulas', roles: ['DIRECTOR'] },
      { label: 'Gestión de Materias', icono: this.iconos.oferta, ruta: '/gestion-materias', roles: ['DIRECTOR'] },
      { label: 'Gestión de Grupos', icono: this.iconos.horario, ruta: '/gestion-grupos', roles: ['DIRECTOR'] },
      { label: 'Gestión de Períodos', icono: this.iconos.gestiones, ruta: '/gestiones', roles: ['DIRECTOR'] },
      { label: 'Reportes', icono: this.iconos.reportes, ruta: '/reportes', roles: ['DIRECTOR'] }
    ];

    return items.filter(item => !rol || item.roles.includes(rol as RolUsuario));
  });

  cerrar(): void {
    this.abierto = false;
  }
}
