/**
 * Componente Layout Principal
 * Envuelve toda la aplicación con header, sidebar y contenido
 * 
 * Heurística Nielsen #4: Consistencia y estándares
 * - Layout consistente en toda la aplicación
 */

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';

@Component({
  selector: 'app-main-layout',
  standalone: true,
  imports: [CommonModule, RouterModule, HeaderComponent, SidebarComponent],
  template: `
    <app-header (toggleSidebar)="toggleSidebar()"></app-header>
    <app-sidebar [abierto]="sidebarAbierto"></app-sidebar>
    
    <main class="contenido-principal">
      <router-outlet></router-outlet>
    </main>
  `,
  styles: [`
    :host {
      display: block;
      min-height: 100vh;
    }

    .contenido-principal {
      margin-left: var(--ancho-sidebar);
      margin-top: var(--altura-header);
      min-height: calc(100vh - var(--altura-header));
      padding: var(--espaciado-lg);
      background-color: var(--color-fondo);
    }

    @media (max-width: 768px) {
      .contenido-principal {
        margin-left: 0;
        padding: var(--espaciado-md);
      }
    }
  `]
})
export class MainLayoutComponent {
  sidebarAbierto = false;

  toggleSidebar(): void {
    this.sidebarAbierto = !this.sidebarAbierto;
  }
}
