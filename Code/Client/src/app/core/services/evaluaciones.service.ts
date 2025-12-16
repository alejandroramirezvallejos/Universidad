/**
 * Servicio de Evaluaciones
 * Conecta con ControladorEvaluacion del backend
 * Endpoints: /api/evaluaciones
 */

import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, catchError, of } from 'rxjs';

export interface Evaluacion {
  codigo: string;
  nombre: string;
  porcentaje: number;
  paraleloMateria: any;
  calificaciones?: Calificacion[];
}

export interface Calificacion {
  valor: number;
  observaciones: string;
  estudiante: any;
  evaluacion: any;
}

@Injectable({
  providedIn: 'root'
})
export class EvaluacionesService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/evaluaciones';

  private _evaluaciones = signal<Evaluacion[]>([]);
  readonly evaluaciones = this._evaluaciones.asReadonly();

  /**
   * Crear nueva evaluación
   */
  crear(evaluacion: Evaluacion): Observable<Evaluacion> {
    return this.http.post<Evaluacion>(this.apiUrl, evaluacion).pipe(
      tap(nueva => {
        this._evaluaciones.update(list => [...list, nueva]);
      }),
      catchError(error => {
        console.error('Error al crear evaluación:', error);
        throw error;
      })
    );
  }

  /**
   * Obtener todas las evaluaciones
   */
  getEvaluaciones(): Observable<Evaluacion[]> {
    return this.http.get<Evaluacion[]>(this.apiUrl).pipe(
      tap(evaluaciones => this._evaluaciones.set(evaluaciones)),
      catchError(error => {
        console.error('Error al obtener evaluaciones:', error);
        return of([]);
      })
    );
  }

  /**
   * Eliminar evaluación
   */
  eliminar(evaluacion: Evaluacion): Observable<void> {
    return this.http.delete<void>(this.apiUrl, { body: evaluacion }).pipe(
      tap(() => {
        this._evaluaciones.update(list =>
          list.filter(e => e.codigo !== evaluacion.codigo)
        );
      }),
      catchError(error => {
        console.error('Error al eliminar evaluación:', error);
        throw error;
      })
    );
  }

  /**
   * Obtener evaluaciones por paralelo
   */
  getEvaluacionesPorParalelo(paraleloCodigo: string): Observable<Evaluacion[]> {
    return this.http.get<Evaluacion[]>(`${this.apiUrl}/paralelo/${paraleloCodigo}`).pipe(
      catchError(error => {
        console.error('Error al obtener evaluaciones del paralelo:', error);
        return of([]);
      })
    );
  }

  /**
   * Obtener calificaciones de un estudiante
   */
  getCalificacionesPorEstudiante(estudianteCodigo: string): Observable<Calificacion[]> {
    return this.http.get<Calificacion[]>(`${this.apiUrl}/estudiante/${estudianteCodigo}`).pipe(
      catchError(error => {
        console.error('Error al obtener calificaciones del estudiante:', error);
        return of([]);
      })
    );
  }

  /**
   * Agregar calificación a evaluación
   */
  agregarCalificacion(calificacion: Calificacion): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/calificaciones`, calificacion).pipe(
      catchError(error => {
        console.error('Error al agregar calificación:', error);
        throw error;
      })
    );
  }

  /**
   * Registrar calificación
   */
  registrarCalificacion(calificacion: Calificacion): Observable<Calificacion> {
    return this.http.post<Calificacion>(`${this.apiUrl}/calificacion`, calificacion).pipe(
      catchError(error => {
        console.error('Error al registrar calificación:', error);
        throw error;
      })
    );
  }
}

