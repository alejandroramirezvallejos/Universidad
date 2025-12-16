/**
 * Servicio de Directores de Carrera
 * Conecta con ControladorDirectorCarrera del backend
 * Endpoints: /api/directores
 */

import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, catchError, of } from 'rxjs';

export interface DirectorCarrera {
  codigo: string;
  nombre: string;
  apellido: string;
  email: string;
  departamento?: string;
  carrera?: {
    codigo: string;
    nombre: string;
  };
}

@Injectable({
  providedIn: 'root'
})
export class DirectoresService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/directores';

  private _directores = signal<DirectorCarrera[]>([]);
  readonly directores = this._directores.asReadonly();

  /**
   * Crear nuevo director
   */
  crear(director: DirectorCarrera): Observable<DirectorCarrera> {
    return this.http.post<DirectorCarrera>(this.apiUrl, director).pipe(
      tap(nuevo => {
        this._directores.update(list => [...list, nuevo]);
      }),
      catchError(error => {
        console.error('Error al crear director:', error);
        throw error;
      })
    );
  }

  /**
   * Obtener todos los directores
   */
  getDirectores(): Observable<DirectorCarrera[]> {
    return this.http.get<DirectorCarrera[]>(this.apiUrl).pipe(
      tap(directores => this._directores.set(directores)),
      catchError(error => {
        console.error('Error al obtener directores:', error);
        return of([]);
      })
    );
  }

  /**
   * Eliminar director
   */
  eliminar(director: DirectorCarrera): Observable<void> {
    return this.http.delete<void>(this.apiUrl, { body: director }).pipe(
      tap(() => {
        this._directores.update(list =>
          list.filter(d => d.codigo !== director.codigo)
        );
      }),
      catchError(error => {
        console.error('Error al eliminar director:', error);
        throw error;
      })
    );
  }

  /**
   * Buscar director por código
   */
  buscarPorCodigo(codigo: string): DirectorCarrera | undefined {
    return this._directores().find(d => d.codigo === codigo);
  }

  /**
   * Buscar director por carrera
   */
  buscarPorCarrera(codigoCarrera: string): DirectorCarrera | undefined {
    return this._directores().find(d => d.carrera?.codigo === codigoCarrera);
  }
}

