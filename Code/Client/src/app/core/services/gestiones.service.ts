import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';

/**
 * Interfaz para representar una Gestión Académica (período/semestre)
 */
export interface Gestion {
  codigo: string;
  nombre: string;
  anio: number;
  periodo: number;
  fechaInicio: string;
  fechaFin: string;
  fechaInicioMatricula: string;
  fechaFinMatricula: string;
  estado: 'EN_CURSO' | 'CERRADA' | 'PLANIFICADA';
}

/**
 * Servicio para gestionar Gestiones Académicas
 * Conectado con el backend Spring Boot
 */
@Injectable({
  providedIn: 'root'
})
export class GestionesService {
  private readonly apiUrl = 'http://localhost:8080/api/gestiones';

  constructor(private http: HttpClient) {}

  /**
   * Obtiene todas las gestiones académicas
   * @returns Observable con array de gestiones
   */
  getGestiones(): Observable<Gestion[]> {
    return this.http.get<Gestion[]>(this.apiUrl).pipe(
      catchError(error => {
        console.error('Error al obtener gestiones:', error);
        return of([]);
      })
    );
  }

  /**
   * Obtiene la gestión académica actual (estado EN_CURSO)
   * @returns Observable con la gestión actual
   */
  getGestionActual(): Observable<Gestion | null> {
    return this.http.get<Gestion>(`${this.apiUrl}/actual`).pipe(
      catchError(error => {
        console.error('Error al obtener gestión actual:', error);
        return of(null);
      })
    );
  }

  /**
   * Obtiene una gestión por su código
   * @param codigo Código de la gestión (ej: "II-2025")
   * @returns Observable con la gestión encontrada
   */
  getGestionPorCodigo(codigo: string): Observable<Gestion | null> {
    return this.http.get<Gestion>(`${this.apiUrl}/${codigo}`).pipe(
      catchError(error => {
        console.error(`Error al obtener gestión ${codigo}:`, error);
        return of(null);
      })
    );
  }

  /**
   * Crea una nueva gestión académica
   * @param gestion Datos de la gestión a crear
   * @returns Observable con la gestión creada
   */
  crearGestion(gestion: Partial<Gestion>): Observable<Gestion | null> {
    return this.http.post<Gestion>(this.apiUrl, gestion).pipe(
      catchError(error => {
        console.error('Error al crear gestión:', error);
        return of(null);
      })
    );
  }

  /**
   * Elimina una gestión académica
   * @param codigo Código de la gestión a eliminar
   * @returns Observable con resultado de la operación
   */
  eliminarGestion(codigo: string): Observable<boolean> {
    return this.http.delete<void>(`${this.apiUrl}`, { 
      body: { codigo } 
    }).pipe(
      catchError(error => {
        console.error(`Error al eliminar gestión ${codigo}:`, error);
        return of(false);
      })
    ) as Observable<boolean>;
  }
}
