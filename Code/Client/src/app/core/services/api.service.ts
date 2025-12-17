/**
 * API Service - Servicio centralizado para comunicación HTTP
 * Maneja todas las peticiones al backend Spring Boot
 * 
 * Base URL: http://localhost:8080/api
 * CORS: Habilitado en backend (@CrossOrigin(origins = "*"))
 */

import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  // IMPORTANTE: Asegurate que el backend este corriendo en puerto 8080
  private readonly API_URL = 'http://localhost:8080/api';
  
  // Headers por defecto
  private readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
    console.log('ApiService inicializado. Backend URL:', this.API_URL);
  }

  /**
   * GET - Obtener recursos
   */
  get<T>(endpoint: string, showLog: boolean = true): Observable<T> {
    const url = `${this.API_URL}${endpoint}`;
    
    if (showLog) {
      console.log(`GET ${url}`);
    }
    
    return this.http.get<T>(url, this.httpOptions).pipe(
      tap(response => {
        if (showLog) {
          console.log(`GET ${endpoint} - Respuesta:`, response);
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * POST - Crear recursos
   */
  post<T>(endpoint: string, body: any, showLog: boolean = true): Observable<T> {
    const url = `${this.API_URL}${endpoint}`;
    
    if (showLog) {
      console.log(`POST ${url}`, body);
    }
    
    return this.http.post<T>(url, body, this.httpOptions).pipe(
      tap(response => {
        if (showLog) {
          console.log(`POST ${endpoint} - Respuesta:`, response);
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * PUT - Actualizar recursos
   */
  put<T>(endpoint: string, body: any, showLog: boolean = true): Observable<T> {
    const url = `${this.API_URL}${endpoint}`;
    
    if (showLog) {
      console.log(`PUT ${url}`, body);
    }
    
    return this.http.put<T>(url, body, this.httpOptions).pipe(
      tap(response => {
        if (showLog) {
          console.log(`PUT ${endpoint} - Respuesta:`, response);
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * PATCH - Actualizar parcialmente recursos
   */
  patch<T>(endpoint: string, body: any, showLog: boolean = true): Observable<T> {
    const url = `${this.API_URL}${endpoint}`;
    
    if (showLog) {
      console.log(`PATCH ${url}`, body);
    }
    
    return this.http.patch<T>(url, body, this.httpOptions).pipe(
      tap(response => {
        if (showLog) {
          console.log(`PATCH ${endpoint} - Respuesta:`, response);
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * DELETE - Eliminar recursos
   */
  delete<T>(endpoint: string, body?: any, showLog: boolean = true): Observable<T> {
    const url = `${this.API_URL}${endpoint}`;
    
    if (showLog) {
      console.log(`DELETE ${url}`, body);
    }
    
    const options = body ? { ...this.httpOptions, body } : this.httpOptions;
    
    return this.http.delete<T>(url, options).pipe(
      tap(response => {
        if (showLog) {
          console.log(`DELETE ${endpoint} - Respuesta:`, response);
        }
      }),
      catchError(this.handleError)
    );
  }

  /**
   * Manejo centralizado de errores HTTP
   */
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Error desconocido';
    
    if (error.error instanceof ErrorEvent) {
      // Error del cliente o de red
      errorMessage = `Error del cliente: ${error.error.message}`;
      console.error('Error del cliente:', error.error.message);
    } else {
      // Error del backend
      errorMessage = `Error del servidor: ${error.status} - ${error.message}`;
      console.error(`Error del backend:`, {
        status: error.status,
        statusText: error.statusText,
        message: error.message,
        body: error.error
      });
      
      // Mensajes específicos según status code
      switch (error.status) {
        case 0:
          errorMessage = 'No se pudo conectar con el servidor. ¿Está el backend corriendo en puerto 8080?';
          break;
        case 400:
          errorMessage = 'Solicitud inválida. Verifica los datos enviados.';
          break;
        case 404:
          errorMessage = 'Recurso no encontrado.';
          break;
        case 500:
          errorMessage = 'Error interno del servidor. Revisa los logs del backend.';
          break;
      }
    }
    
    return throwError(() => new Error(errorMessage));
  }

  /**
   * Verifica si el backend está disponible
   */
  async verificarConexion(): Promise<boolean> {
    try {
      // Intentar obtener materias como test
      await this.get('/materias', false).toPromise();
      console.log('Conexion con backend exitosa');
      return true;
    } catch (error) {
      console.error('No se pudo conectar con el backend:', error);
      return false;
    }
  }
}
