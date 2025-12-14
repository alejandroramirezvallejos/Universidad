/**
 * Modelos relacionados con matrícula y calificaciones
 * Sistema de Gestión Universitaria
 */

import { Grupo, Gestion, Materia } from './academico.model';
import { Estudiante } from './usuario.model';

export interface Matricula {
  id: number;
  estudiante: Estudiante;
  grupo: Grupo;
  gestion: Gestion;
  fechaMatricula: Date;
  estado: EstadoMatricula;
}

export type EstadoMatricula = 'INSCRITO' | 'RETIRADO' | 'APROBADO' | 'REPROBADO';

export interface SolicitudMatricula {
  estudianteId: number;
  grupoIds: number[];
  gestionId: number;
}

export interface ValidacionMatricula {
  valido: boolean;
  errores: ErrorMatricula[];
  advertencias: string[];
}

export interface ErrorMatricula {
  tipo: TipoErrorMatricula;
  mensaje: string;
  grupoId?: number;
  materiaId?: number;
}

export type TipoErrorMatricula = 
  | 'PRERREQUISITO_FALTANTE'
  | 'CHOQUE_HORARIO'
  | 'CUPO_LLENO'
  | 'YA_INSCRITO'
  | 'MATERIA_APROBADA'
  | 'FUERA_DE_PERIODO';

export interface Calificacion {
  id: number;
  matricula: Matricula;
  tipoEvaluacion: TipoEvaluacion;
  nombre: string;
  nota: number;
  porcentaje: number;
  fecha: Date;
  observaciones?: string;
}

export type TipoEvaluacion = 'PARCIAL' | 'FINAL' | 'PRACTICO' | 'TAREA' | 'PROYECTO' | 'OTRO';

export interface ResumenNotas {
  matriculaId: number;
  materia: Materia;
  calificaciones: Calificacion[];
  notaFinal: number;
  estado: EstadoMatricula;
}

export interface HistorialAcademico {
  estudiante: Estudiante;
  gestiones: HistorialGestion[];
  promedioGeneral: number;
  creditosAprobados: number;
  creditosTotales: number;
}

export interface HistorialGestion {
  gestion: Gestion;
  materias: ResumenNotas[];
  promedioGestion: number;
  creditosGestion: number;
}

export interface Acta {
  id: number;
  grupo: Grupo;
  gestion: Gestion;
  fechaGeneracion: Date;
  docente: string;
  estudiantes: ActaEstudiante[];
  estado: 'BORRADOR' | 'FINALIZADA';
}

export interface ActaEstudiante {
  estudiante: Estudiante;
  notaFinal: number;
  estado: EstadoMatricula;
  observaciones?: string;
}

export interface AlertaReprobacion {
  id: number;
  estudiante: Estudiante;
  materia: Materia;
  gestion: Gestion;
  notaActual: number;
  fechaAlerta: Date;
  leida: boolean;
}
