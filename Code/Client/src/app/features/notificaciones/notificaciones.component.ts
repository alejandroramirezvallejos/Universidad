/**
 * Componente de Notificaciones
 * Muestra las notificaciones académicas del usuario
 *
 * Heurística Nielsen #1: Visibilidad del estado del sistema
 * - Muestra todas las notificaciones académicas
 *
 * Heurística Nielsen #7: Flexibilidad y eficiencia
 * - Permite marcar como leídas individual o masivamente
 */

import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ActasService, NotificacionAcademica } from '../../core/services/actas.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-notificaciones',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="notificaciones-container">
      <header class="notificaciones-header">
        <div class="header-titulo">
          <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
            <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
          </svg>
          <h1>Notificaciones</h1>
        </div>
        <div class="header-acciones">
          <span class="contador-no-leidas" *ngIf="cantidadNoLeidas > 0">
            {{ cantidadNoLeidas }} sin leer
          </span>
          <button
            class="btn btn-secundario"
            (click)="marcarTodasComoLeidas()"
            *ngIf="cantidadNoLeidas > 0">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <polyline points="9 11 12 14 22 4"></polyline>
              <path d="M21 12v7a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11"></path>
            </svg>
            Marcar todas como leídas
          </button>
        </div>
      </header>

      <!-- Lista de notificaciones -->
      <div class="notificaciones-lista" *ngIf="notificaciones.length > 0">
        <div
          class="notificacion-item"
          [class.no-leida]="!notificacion.leida"
          *ngFor="let notificacion of notificaciones"
          (click)="marcarComoLeida(notificacion)">

          <div class="notificacion-icono" [ngClass]="getIconoClase(notificacion.tipo)">
            <ng-container [ngSwitch]="notificacion.tipo">
              <!-- Icono calificación -->
              <svg *ngSwitchCase="'CALIFICACION'" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
                <polyline points="14 2 14 8 20 8"></polyline>
                <line x1="16" y1="13" x2="8" y2="13"></line>
                <line x1="16" y1="17" x2="8" y2="17"></line>
              </svg>
              <!-- Icono inscripción -->
              <svg *ngSwitchCase="'INSCRIPCION'" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                <circle cx="8.5" cy="7" r="4"></circle>
                <line x1="20" y1="8" x2="20" y2="14"></line>
                <line x1="23" y1="11" x2="17" y2="11"></line>
              </svg>
              <!-- Icono sistema -->
              <svg *ngSwitchDefault xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <circle cx="12" cy="12" r="10"></circle>
                <line x1="12" y1="16" x2="12" y2="12"></line>
                <line x1="12" y1="8" x2="12.01" y2="8"></line>
              </svg>
            </ng-container>
          </div>

          <div class="notificacion-contenido">
            <h3 class="notificacion-titulo">
              {{ getTitulo(notificacion) }}
            </h3>
            <p class="notificacion-mensaje">
              {{ notificacion.mensaje || getMensajeDefault(notificacion) }}
            </p>
            <div class="notificacion-meta">
              <span class="notificacion-fecha">
                {{ formatearFecha(notificacion.fecha) }}
              </span>
              <span class="notificacion-estado" *ngIf="!notificacion.leida">
                Nueva
              </span>
            </div>
          </div>

          <button
            class="btn-eliminar"
            (click)="eliminarNotificacion(notificacion, $event)"
            title="Eliminar notificación">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <line x1="18" y1="6" x2="6" y2="18"></line>
              <line x1="6" y1="6" x2="18" y2="18"></line>
            </svg>
          </button>
        </div>
      </div>

      <!-- Estado vacío -->
      <div class="estado-vacio" *ngIf="notificaciones.length === 0">
        <div class="estado-vacio-icono">
          <svg xmlns="http://www.w3.org/2000/svg" width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round">
            <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
            <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
          </svg>
        </div>
        <h2>No tienes notificaciones</h2>
        <p>Cuando recibas notificaciones académicas, aparecerán aquí.</p>
        <a routerLink="/dashboard" class="btn btn-primario">
          Ir al Dashboard
        </a>
      </div>
    </div>
  `,
  styles: [`
    .notificaciones-container {
      max-width: 800px;
      margin: 0 auto;
      padding: var(--espaciado-lg);
    }

    .notificaciones-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: var(--espaciado-xl);
      flex-wrap: wrap;
      gap: var(--espaciado-md);
    }

    .header-titulo {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      color: var(--color-texto);
    }

    .header-titulo h1 {
      margin: 0;
      font-size: var(--texto-2xl);
      font-weight: 600;
    }

    .header-titulo svg {
      color: var(--color-primario);
    }

    .header-acciones {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
    }

    .contador-no-leidas {
      background-color: var(--color-error);
      color: white;
      padding: var(--espaciado-xs) var(--espaciado-sm);
      border-radius: var(--radio-full);
      font-size: var(--texto-sm);
      font-weight: 500;
    }

    .btn {
      display: inline-flex;
      align-items: center;
      gap: var(--espaciado-xs);
      padding: var(--espaciado-sm) var(--espaciado-md);
      border: none;
      border-radius: var(--radio-md);
      font-size: var(--texto-sm);
      font-weight: 500;
      cursor: pointer;
      transition: all var(--transicion-rapida);
      text-decoration: none;
    }

    .btn-primario {
      background-color: var(--color-primario);
      color: white;
    }

    .btn-primario:hover {
      background-color: var(--color-primario-dark);
    }

    .btn-secundario {
      background-color: var(--color-fondo);
      color: var(--color-texto);
      border: 1px solid var(--color-borde);
    }

    .btn-secundario:hover {
      background-color: var(--color-borde);
    }

    .notificaciones-lista {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-md);
    }

    .notificacion-item {
      display: flex;
      align-items: flex-start;
      gap: var(--espaciado-md);
      padding: var(--espaciado-md);
      background-color: var(--color-fondo-card);
      border: 1px solid var(--color-borde);
      border-radius: var(--radio-lg);
      cursor: pointer;
      transition: all var(--transicion-rapida);
    }

    .notificacion-item:hover {
      box-shadow: var(--sombra-md);
      border-color: var(--color-primario-light);
    }

    .notificacion-item.no-leida {
      background-color: var(--color-primario-light);
      border-left: 4px solid var(--color-primario);
    }

    .notificacion-icono {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 48px;
      height: 48px;
      border-radius: var(--radio-full);
      flex-shrink: 0;
    }

    .notificacion-icono.calificacion {
      background-color: #dbeafe;
      color: #2563eb;
    }

    .notificacion-icono.inscripcion {
      background-color: #d1fae5;
      color: #059669;
    }

    .notificacion-icono.sistema {
      background-color: #fef3c7;
      color: #d97706;
    }

    .notificacion-contenido {
      flex: 1;
      min-width: 0;
    }

    .notificacion-titulo {
      margin: 0 0 var(--espaciado-xs);
      font-size: var(--texto-md);
      font-weight: 600;
      color: var(--color-texto);
    }

    .notificacion-mensaje {
      margin: 0 0 var(--espaciado-sm);
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
      line-height: 1.5;
    }

    .notificacion-meta {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
    }

    .notificacion-fecha {
      font-size: var(--texto-xs);
      color: var(--color-texto-claro);
    }

    .notificacion-estado {
      background-color: var(--color-primario);
      color: white;
      padding: 2px 8px;
      border-radius: var(--radio-full);
      font-size: var(--texto-xs);
      font-weight: 500;
    }

    .btn-eliminar {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 32px;
      height: 32px;
      background: transparent;
      border: none;
      border-radius: var(--radio-md);
      color: var(--color-texto-claro);
      cursor: pointer;
      transition: all var(--transicion-rapida);
      flex-shrink: 0;
    }

    .btn-eliminar:hover {
      background-color: var(--color-error-light);
      color: var(--color-error);
    }

    .estado-vacio {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      padding: var(--espaciado-2xl);
      text-align: center;
    }

    .estado-vacio-icono {
      color: var(--color-texto-claro);
      margin-bottom: var(--espaciado-lg);
      opacity: 0.5;
    }

    .estado-vacio h2 {
      margin: 0 0 var(--espaciado-sm);
      font-size: var(--texto-xl);
      font-weight: 600;
      color: var(--color-texto);
    }

    .estado-vacio p {
      margin: 0 0 var(--espaciado-lg);
      color: var(--color-texto-secundario);
    }

    @media (max-width: 640px) {
      .notificaciones-header {
        flex-direction: column;
        align-items: flex-start;
      }

      .notificacion-item {
        flex-direction: column;
      }

      .notificacion-icono {
        align-self: flex-start;
      }

      .btn-eliminar {
        align-self: flex-end;
        margin-top: calc(-1 * var(--espaciado-lg));
      }
    }
  `]
})
export class NotificacionesComponent implements OnInit {
  private actasService = inject(ActasService);
  private authService = inject(AuthService);

  notificaciones: NotificacionAcademica[] = [];
  cantidadNoLeidas = 0;

  ngOnInit(): void {
    this.cargarNotificaciones();
    // Agregar algunas notificaciones de demostración
    this.agregarNotificacionesDemostracion();
  }

  cargarNotificaciones(): void {
    this.notificaciones = this.actasService.notificaciones();
    this.actualizarContador();
  }

  private agregarNotificacionesDemostracion(): void {
    // Si no hay notificaciones, agregar algunas de demostración
    if (this.notificaciones.length === 0) {
      this.actasService.agregarNotificacionSistema('Bienvenido al sistema de notificaciones académicas');

      // Simular notificación de calificación
      const notificacionCalificacion: NotificacionAcademica = {
        id: Date.now() + 1,
        estudiante: { codigo: 'EST001', nombre: 'Juan Pérez' },
        materia: { codigo: 'SIS-101', nombre: 'Programación I' },
        notaFinal: 85,
        fecha: new Date(),
        leida: false,
        tipo: 'CALIFICACION',
        mensaje: 'Se ha registrado tu nota final: 85 en Programación I'
      };

      const notificacionInscripcion: NotificacionAcademica = {
        id: Date.now() + 2,
        estudiante: null,
        materia: null,
        notaFinal: 0,
        fecha: new Date(Date.now() - 86400000), // Ayer
        leida: true,
        tipo: 'INSCRIPCION',
        mensaje: 'Tu inscripción a Base de Datos I ha sido confirmada'
      };

      // Agregar al servicio
      this.notificaciones = [notificacionCalificacion, notificacionInscripcion, ...this.actasService.notificaciones()];
      this.actualizarContador();
    }
  }

  private actualizarContador(): void {
    this.cantidadNoLeidas = this.notificaciones.filter(n => !n.leida).length;
  }

  marcarComoLeida(notificacion: NotificacionAcademica): void {
    if (!notificacion.leida && notificacion.id) {
      this.actasService.marcarComoLeida(notificacion.id);
      notificacion.leida = true;
      this.actualizarContador();
    }
  }

  marcarTodasComoLeidas(): void {
    this.actasService.marcarTodasComoLeidas();
    this.notificaciones.forEach(n => n.leida = true);
    this.actualizarContador();
  }

  eliminarNotificacion(notificacion: NotificacionAcademica, event: Event): void {
    event.stopPropagation();
    this.notificaciones = this.notificaciones.filter(n => n.id !== notificacion.id);
    this.actualizarContador();
  }

  getTitulo(notificacion: NotificacionAcademica): string {
    switch (notificacion.tipo) {
      case 'CALIFICACION':
        return `Calificación registrada - ${notificacion.materia?.nombre || 'Materia'}`;
      case 'INSCRIPCION':
        return 'Inscripción confirmada';
      case 'SISTEMA':
      default:
        return 'Notificación del sistema';
    }
  }

  getMensajeDefault(notificacion: NotificacionAcademica): string {
    if (notificacion.tipo === 'CALIFICACION' && notificacion.notaFinal) {
      return `Has obtenido una calificación de ${notificacion.notaFinal} puntos.`;
    }
    return 'Tienes una nueva notificación.';
  }

  getIconoClase(tipo?: string): string {
    return tipo?.toLowerCase() || 'sistema';
  }

  formatearFecha(fecha?: Date): string {
    if (!fecha) return '';

    const ahora = new Date();
    const diff = ahora.getTime() - new Date(fecha).getTime();
    const minutos = Math.floor(diff / 60000);
    const horas = Math.floor(diff / 3600000);
    const dias = Math.floor(diff / 86400000);

    if (minutos < 1) return 'Ahora mismo';
    if (minutos < 60) return `Hace ${minutos} min`;
    if (horas < 24) return `Hace ${horas} hora${horas > 1 ? 's' : ''}`;
    if (dias < 7) return `Hace ${dias} día${dias > 1 ? 's' : ''}`;

    return new Date(fecha).toLocaleDateString('es-ES', {
      day: 'numeric',
      month: 'short',
      year: 'numeric'
    });
  }
}

