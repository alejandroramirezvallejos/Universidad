/**
 * Servicio de Notificaciones
 * Muestra mensajes de feedback al usuario
 * 
 * Heurística Nielsen #1: Visibilidad del estado del sistema
 * - Proporciona feedback inmediato de las acciones
 * 
 * Heurística Nielsen #9: Ayudar a reconocer, diagnosticar y recuperarse de errores
 * - Mensajes claros y específicos
 */

import { Injectable, signal } from '@angular/core';

export type TipoNotificacion = 'exito' | 'error' | 'advertencia' | 'info';

export interface Notificacion {
  id: number;
  tipo: TipoNotificacion;
  titulo: string;
  mensaje: string;
  duracion: number;
  visible: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class NotificacionService {
  private _notificaciones = signal<Notificacion[]>([]);
  
  // Señal pública de solo lectura
  readonly notificaciones = this._notificaciones.asReadonly();

  private readonly DURACION_DEFAULT = 5000;

  /**
   * Muestra una notificación de éxito
   */
  exito(mensaje: string, titulo: string = '¡Éxito!'): void {
    this.mostrar('exito', titulo, mensaje);
  }

  /**
   * Muestra una notificación de error
   */
  error(mensaje: string, titulo: string = 'Error'): void {
    this.mostrar('error', titulo, mensaje, 8000);
  }

  /**
   * Muestra una notificación de advertencia
   */
  advertencia(mensaje: string, titulo: string = 'Advertencia'): void {
    this.mostrar('advertencia', titulo, mensaje);
  }

  /**
   * Muestra una notificación informativa
   */
  info(mensaje: string, titulo: string = 'Información'): void {
    this.mostrar('info', titulo, mensaje);
  }

  /**
   * Cierra una notificación específica
   */
  cerrar(id: number): void {
    this._notificaciones.update(list => 
      list.filter(n => n.id !== id)
    );
  }

  /**
   * Alias para cerrar (compatibilidad)
   */
  remover(id: number): void {
    this.cerrar(id);
  }

  /**
   * Cierra todas las notificaciones
   */
  cerrarTodas(): void {
    this._notificaciones.set([]);
  }

  /**
   * Muestra una notificación genérica
   */
  private mostrar(
    tipo: TipoNotificacion, 
    titulo: string, 
    mensaje: string, 
    duracion: number = this.DURACION_DEFAULT
  ): void {
    const notificacion: Notificacion = {
      id: Date.now(),
      tipo,
      titulo,
      mensaje,
      duracion,
      visible: true
    };

    this._notificaciones.update(list => [...list, notificacion]);

    // Auto-cerrar después de la duración
    if (duracion > 0) {
      setTimeout(() => this.cerrar(notificacion.id), duracion);
    }
  }
}
