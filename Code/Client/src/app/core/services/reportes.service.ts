/**
 * Servicio de Reportes
 * Conecta con ControladorReporte del backend
 * Endpoints: /api/reportes
 */

import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, catchError, of } from 'rxjs';

export interface ReporteEstudiantesPorCarrera {
  carrera: any;
  estudiantes: any[];
  totalEstudiantes: number;
  fechaGeneracion: string;
  solicitante: string;
}

export interface ReporteInscripciones {
  gestion: any;
  inscripciones: any[];
  totalInscripciones: number;
  fechaGeneracion: string;
}

export interface ReporteRendimiento {
  paralelo: any;
  estudiantes: any[];
  promedioGeneral: number;
  aprobados: number;
  reprobados: number;
  fechaGeneracion: string;
  solicitante: string;
}

@Injectable({
  providedIn: 'root'
})
export class ReportesService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/reportes';

  private _reportesDisponibles = signal<string[]>([]);
  readonly reportesDisponibles = this._reportesDisponibles.asReadonly();

  /**
   * Listar tipos de reportes disponibles
   */
  listarReportes(): Observable<string[]> {
    return this.http.get<string[]>(this.apiUrl).pipe(
      tap(reportes => this._reportesDisponibles.set(reportes)),
      catchError(error => {
        console.error('Error al listar reportes:', error);
        return of([]);
      })
    );
  }

  /**
   * Obtener reporte de estudiantes por carrera
   */
  getReporteEstudiantesPorCarrera(
    codigoCarrera: string,
    solicitante: string = 'Sistema'
  ): Observable<ReporteEstudiantesPorCarrera | null> {
    return this.http.get<ReporteEstudiantesPorCarrera>(
      `${this.apiUrl}/estudiantes-por-carrera/${codigoCarrera}`,
      { params: { solicitante } }
    ).pipe(
      catchError(error => {
        console.error('Error al obtener reporte de estudiantes por carrera:', error);
        return of(null);
      })
    );
  }

  /**
   * Obtener reporte de inscripciones por gestión
   */
  getReporteInscripciones(codigoGestion: string): Observable<ReporteInscripciones | null> {
    return this.http.get<ReporteInscripciones>(
      `${this.apiUrl}/inscripciones/${codigoGestion}`
    ).pipe(
      catchError(error => {
        console.error('Error al obtener reporte de inscripciones:', error);
        return of(null);
      })
    );
  }

  /**
   * Obtener reporte de rendimiento por paralelo
   */
  getReporteRendimiento(
    codigoParalelo: string,
    solicitante: string = 'Sistema'
  ): Observable<ReporteRendimiento | null> {
    return this.http.get<ReporteRendimiento>(
      `${this.apiUrl}/rendimiento/${codigoParalelo}`,
      { params: { solicitante } }
    ).pipe(
      catchError(error => {
        console.error('Error al obtener reporte de rendimiento:', error);
        return of(null);
      })
    );
  }

  /**
   * Descargar reporte en formato PDF (simulado)
   */
  descargarReportePDF(tipo: string, datos: any): void {
    // Crear blob con los datos del reporte
    const contenido = JSON.stringify(datos, null, 2);
    const blob = new Blob([contenido], { type: 'application/json' });
    const url = window.URL.createObjectURL(blob);

    const link = document.createElement('a');
    link.href = url;
    link.download = `reporte_${tipo}_${new Date().toISOString().split('T')[0]}.json`;
    link.click();

    window.URL.revokeObjectURL(url);
  }
}

