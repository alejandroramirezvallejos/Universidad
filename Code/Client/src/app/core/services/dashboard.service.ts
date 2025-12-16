/**
 * Servicio de Dashboard
 * Conecta con ControladorDashboard del backend
 * Endpoints: /api/dashboard
 */

import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, catchError, of } from 'rxjs';

export interface DashboardEstudiante {
  nombreCompleto: string;
  carrera: string;
  semestre: number;
  materiasInscritas: number;
  creditosActuales: number;
  promedioGeneral: number;
  proximasClases: ProximaClase[];
  notificacionesPendientes: number;
  materiasEnCurso: MateriaEnCurso[];
}

export interface DashboardDocente {
  nombreCompleto: string;
  especialidad: string;
  paralelosAsignados: number;
  totalEstudiantes: number;
  proximasClases: ProximaClase[];
  paralelosActivos: ParaleloActivo[];
  evaluacionesPendientes: number;
}

export interface ProximaClase {
  materia: string;
  aula: string;
  horaInicio: string;
  horaFin: string;
  dia: string;
}

export interface MateriaEnCurso {
  codigo: string;
  nombre: string;
  docente: string;
  progreso: number;
  notaActual: number;
}

export interface ParaleloActivo {
  codigo: string;
  materia: string;
  estudiantes: number;
  horario: string;
}

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/dashboard';

  private _dashboardEstudiante = signal<DashboardEstudiante | null>(null);
  private _dashboardDocente = signal<DashboardDocente | null>(null);

  readonly dashboardEstudiante = this._dashboardEstudiante.asReadonly();
  readonly dashboardDocente = this._dashboardDocente.asReadonly();

  /**
   * Obtener dashboard para estudiante
   */
  getDashboardEstudiante(codigo: string): Observable<DashboardEstudiante | null> {
    return this.http.get<DashboardEstudiante>(`${this.apiUrl}/estudiante/${codigo}`).pipe(
      tap(dashboard => this._dashboardEstudiante.set(dashboard)),
      catchError(error => {
        console.error('Error al obtener dashboard del estudiante:', error);
        // Retornar datos mock si falla
        return of(this.getDashboardEstudianteMock());
      })
    );
  }

  /**
   * Obtener dashboard para docente
   */
  getDashboardDocente(codigo: string): Observable<DashboardDocente | null> {
    return this.http.get<DashboardDocente>(`${this.apiUrl}/docente/${codigo}`).pipe(
      tap(dashboard => this._dashboardDocente.set(dashboard)),
      catchError(error => {
        console.error('Error al obtener dashboard del docente:', error);
        // Retornar datos mock si falla
        return of(this.getDashboardDocenteMock());
      })
    );
  }

  /**
   * Dashboard mock para estudiante cuando el backend no responde
   */
  private getDashboardEstudianteMock(): DashboardEstudiante {
    return {
      nombreCompleto: 'Estudiante',
      carrera: 'Ingeniería de Sistemas',
      semestre: 4,
      materiasInscritas: 5,
      creditosActuales: 20,
      promedioGeneral: 78.5,
      proximasClases: [
        { materia: 'Programación I', aula: 'A-201', horaInicio: '08:00', horaFin: '10:00', dia: 'LUNES' },
        { materia: 'Base de Datos I', aula: 'B-101', horaInicio: '14:00', horaFin: '16:00', dia: 'LUNES' }
      ],
      notificacionesPendientes: 3,
      materiasEnCurso: [
        { codigo: 'SIS-101', nombre: 'Programación I', docente: 'María González', progreso: 60, notaActual: 85 },
        { codigo: 'SIS-203', nombre: 'Base de Datos I', docente: 'Pedro López', progreso: 45, notaActual: 72 }
      ]
    };
  }

  /**
   * Dashboard mock para docente cuando el backend no responde
   */
  private getDashboardDocenteMock(): DashboardDocente {
    return {
      nombreCompleto: 'Docente',
      especialidad: 'Ingeniería de Software',
      paralelosAsignados: 3,
      totalEstudiantes: 85,
      proximasClases: [
        { materia: 'Programación I', aula: 'A-201', horaInicio: '08:00', horaFin: '10:00', dia: 'LUNES' },
        { materia: 'Ingeniería de Software', aula: 'C-301', horaInicio: '08:00', horaFin: '10:00', dia: 'MARTES' }
      ],
      paralelosActivos: [
        { codigo: 'SIS-101-A', materia: 'Programación I', estudiantes: 30, horario: 'Lun/Mie 08:00-10:00' },
        { codigo: 'SIS-303-A', materia: 'Ingeniería de Software', estudiantes: 45, horario: 'Mar/Jue 08:00-10:00' }
      ],
      evaluacionesPendientes: 2
    };
  }

  /**
   * Limpiar datos del dashboard
   */
  limpiar(): void {
    this._dashboardEstudiante.set(null);
    this._dashboardDocente.set(null);
  }
}

