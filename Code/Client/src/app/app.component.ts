/**
 * Componente Principal de la Aplicación
 * Sistema de Gestión Universitaria
 * 
 * Heurística Nielsen #1: Visibilidad del estado del sistema
 * - Notificaciones globales siempre visibles
 */

import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NotificacionService } from './core/services/notificacion.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  template: `
    <router-outlet></router-outlet>
    
    <!-- Sistema de notificaciones global -->
    <div class="notificaciones-container">
      <div 
        *ngFor="let notificacion of notificacionService.notificaciones()"
        class="notificacion"
        [ngClass]="'notificacion-' + notificacion.tipo"
      >
        <div class="notificacion-contenido">
          <svg *ngIf="notificacion.tipo === 'exito'" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path>
            <polyline points="22 4 12 14.01 9 11.01"></polyline>
          </svg>
          <svg *ngIf="notificacion.tipo === 'error'" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="10"></circle>
            <line x1="15" y1="9" x2="9" y2="15"></line>
            <line x1="9" y1="9" x2="15" y2="15"></line>
          </svg>
          <svg *ngIf="notificacion.tipo === 'advertencia'" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M10.29 3.86L1.82 18a2 2 0 0 0 1.71 3h16.94a2 2 0 0 0 1.71-3L13.71 3.86a2 2 0 0 0-3.42 0z"></path>
            <line x1="12" y1="9" x2="12" y2="13"></line>
            <line x1="12" y1="17" x2="12.01" y2="17"></line>
          </svg>
          <svg *ngIf="notificacion.tipo === 'info'" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="10"></circle>
            <line x1="12" y1="16" x2="12" y2="12"></line>
            <line x1="12" y1="8" x2="12.01" y2="8"></line>
          </svg>
          <span>{{ notificacion.mensaje }}</span>
        </div>
        <button class="notificacion-cerrar" (click)="notificacionService.remover(notificacion.id)">
          ×
        </button>
      </div>
    </div>
  `,
  styles: [`
    .notificaciones-container {
      position: fixed;
      top: 20px;
      right: 20px;
      z-index: 9999;
      display: flex;
      flex-direction: column;
      gap: 10px;
      max-width: 380px;
    }

    .notificacion {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 12px 16px;
      border-radius: 8px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
      animation: slideIn 0.3s ease-out;
    }

    @keyframes slideIn {
      from {
        transform: translateX(100%);
        opacity: 0;
      }
      to {
        transform: translateX(0);
        opacity: 1;
      }
    }

    .notificacion-contenido {
      display: flex;
      align-items: center;
      gap: 12px;
    }

    .notificacion-cerrar {
      background: none;
      border: none;
      font-size: 20px;
      cursor: pointer;
      opacity: 0.7;
      padding: 0 0 0 12px;
    }

    .notificacion-cerrar:hover {
      opacity: 1;
    }

    .notificacion-exito {
      background-color: #ecfdf5;
      border-left: 4px solid #10b981;
      color: #065f46;
    }

    .notificacion-error {
      background-color: #fef2f2;
      border-left: 4px solid #ef4444;
      color: #991b1b;
    }

    .notificacion-advertencia {
      background-color: #fffbeb;
      border-left: 4px solid #f59e0b;
      color: #92400e;
    }

    .notificacion-info {
      background-color: #eff6ff;
      border-left: 4px solid #3b82f6;
      color: #1e40af;
    }
  `]
})
export class AppComponent {
  title = 'Sistema Universitario';

  constructor(public notificacionService: NotificacionService) {}
}
