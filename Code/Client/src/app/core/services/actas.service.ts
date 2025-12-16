/**
 * Servicio de Actas de Estudiante
 * Conecta con ControladorActaEstudiante del backend
 * Endpoints: /api/actas
 */

import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, catchError, of } from 'rxjs';

export interface ActaEstudiante {
  estudiante: any;
  paraleloMateria: any;
  calificaciones: any[];
  calificacionFinal: number;
  aprobado: boolean;
}

export interface NotificacionAcademica {
  id?: number;
  estudiante: any;
  materia: any;
  notaFinal: number;
  fecha?: Date;
  leida?: boolean;
  tipo?: 'CALIFICACION' | 'INSCRIPCION' | 'SISTEMA';
  mensaje?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ActasService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/actas';

  private _actas = signal<ActaEstudiante[]>([]);
  private _notificaciones = signal<NotificacionAcademica[]>([]);

  readonly actas = this._actas.asReadonly();
  readonly notificaciones = this._notificaciones.asReadonly();

  /**
   * Crear nueva acta
   */
  crear(acta: ActaEstudiante): Observable<ActaEstudiante> {
    return this.http.post<ActaEstudiante>(this.apiUrl, acta).pipe(
      tap(nueva => {
        this._actas.update(list => [...list, nueva]);
      }),
      catchError(error => {
        console.error('Error al crear acta:', error);
        throw error;
      })
    );
  }

  /**
   * Obtener todas las actas
   */
  getActas(): Observable<ActaEstudiante[]> {
    return this.http.get<ActaEstudiante[]>(this.apiUrl).pipe(
      tap(actas => this._actas.set(actas)),
      catchError(error => {
        console.error('Error al obtener actas:', error);
        return of([]);
      })
    );
  }

  /**
   * Obtener actas aprobadas
   */
  getActasAprobadas(): Observable<ActaEstudiante[]> {
    return this.http.get<ActaEstudiante[]>(`${this.apiUrl}/aprobadas`).pipe(
      catchError(error => {
        console.error('Error al obtener actas aprobadas:', error);
        return of([]);
      })
    );
  }

  /**
   * Obtener actas reprobadas
   */
  getActasReprobadas(): Observable<ActaEstudiante[]> {
    return this.http.get<ActaEstudiante[]>(`${this.apiUrl}/reprobadas`).pipe(
      catchError(error => {
        console.error('Error al obtener actas reprobadas:', error);
        return of([]);
      })
    );
  }

  /**
   * Eliminar acta
   */
  eliminar(acta: ActaEstudiante): Observable<void> {
    return this.http.delete<void>(this.apiUrl, { body: acta }).pipe(
      tap(() => {
        this._actas.update(list =>
          list.filter(a => a.estudiante?.codigo !== acta.estudiante?.codigo ||
                          a.paraleloMateria?.codigo !== acta.paraleloMateria?.codigo)
        );
      }),
      catchError(error => {
        console.error('Error al eliminar acta:', error);
        throw error;
      })
    );
  }

  /**
   * Notificar al estudiante sobre calificación
   * Este es el endpoint que usa el patrón Observer
   */
  notificar(acta: ActaEstudiante): Observable<void> {
    const dto = {
      estudiante: {
        codigo: acta.estudiante?.codigo || '',
        nombre: acta.estudiante?.nombre || '',
        email: acta.estudiante?.email || ''
      },
      materia: {
        codigo: acta.paraleloMateria?.materia?.codigo || '',
        nombre: acta.paraleloMateria?.materia?.nombre || '',
        creditos: acta.paraleloMateria?.materia?.creditos || 0,
        semestre: acta.paraleloMateria?.materia?.semestre || 1
      },
      notaFinal: acta.calificacionFinal
    };
    return this.http.post<void>('http://localhost:8080/api/notificaciones', dto).pipe(
      tap(() => {
        const notificacion: NotificacionAcademica = {
          id: Date.now(),
          estudiante: acta.estudiante,
          materia: acta.paraleloMateria?.materia,
          notaFinal: acta.calificacionFinal,
          fecha: new Date(),
          leida: false,
          tipo: 'CALIFICACION',
          mensaje: `Nota registrada: ${acta.calificacionFinal} en ${acta.paraleloMateria?.materia?.nombre}`
        };
        this._notificaciones.update(list => [notificacion, ...list]);
      }),
      catchError(error => {
        console.error('Error al notificar:', error);
        throw error;
      })
    );
  }

  /**
   * Obtener notificaciones académicas del estudiante actual
   */
  getNotificacionesEstudiante(estudianteCodigo: string): NotificacionAcademica[] {
    return this._notificaciones().filter(
      n => n.estudiante?.codigo === estudianteCodigo
    );
  }

  /**
   * Marcar notificación como leída
   */
  marcarComoLeida(id: number): void {
    this._notificaciones.update(list =>
      list.map(n => n.id === id ? { ...n, leida: true } : n)
    );
  }

  /**
   * Marcar todas como leídas
   */
  marcarTodasComoLeidas(): void {
    this._notificaciones.update(list =>
      list.map(n => ({ ...n, leida: true }))
    );
  }

  /**
   * Obtener cantidad de notificaciones no leídas
   */
  getCantidadNoLeidas(): number {
    return this._notificaciones().filter(n => !n.leida).length;
  }

  /**
   * Agregar notificación de sistema
   */
  agregarNotificacionSistema(mensaje: string): void {
    const notificacion: NotificacionAcademica = {
      id: Date.now(),
      estudiante: null,
      materia: null,
      notaFinal: 0,
      fecha: new Date(),
      leida: false,
      tipo: 'SISTEMA',
      mensaje
    };
    this._notificaciones.update(list => [notificacion, ...list]);
  }
}

