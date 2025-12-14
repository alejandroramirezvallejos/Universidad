/**
 * Modelos relacionados con la gestión académica
 * Sistema de Gestión Universitaria
 */

export interface Materia {
  id: number;
  codigo: string;
  nombre: string;
  creditos: number;
  horasTeoricas: number;
  horasPracticas: number;
  semestre: number;
  prerrequisitos: Materia[];
  descripcion?: string;
  activa: boolean;
}

export interface Aula {
  id: number;
  codigo: string;
  nombre: string;
  capacidad: number;
  edificio: string;
  piso: number;
  tipoAula: 'TEORIA' | 'LABORATORIO' | 'AUDITORIO';
  equipamiento?: string[];
}

export interface Horario {
  id: number;
  dia: DiaSemana;
  horaInicio: string; // Formato HH:mm
  horaFin: string;
}

export type DiaSemana = 'LUNES' | 'MARTES' | 'MIERCOLES' | 'JUEVES' | 'VIERNES' | 'SABADO';

export interface Grupo {
  id: number;
  codigo: string;
  materia: Materia;
  docente: DocenteResumen;
  aula: Aula;
  horarios: Horario[];
  cupoMaximo: number;
  cupoDisponible: number;
  gestion: Gestion;
  activo: boolean;
}

export interface DocenteResumen {
  id: number;
  nombre: string;
  apellido: string;
  email: string;
}

export interface Gestion {
  id: number;
  nombre: string; // Ej: "I-2025", "II-2025"
  anio: number;
  periodo: 1 | 2; // Semestre 1 o 2
  fechaInicio: Date;
  fechaFin: Date;
  fechaInicioMatricula: Date;
  fechaFinMatricula: Date;
  estado: EstadoGestion;
}

export type EstadoGestion = 'PLANIFICACION' | 'MATRICULA' | 'EN_CURSO' | 'CIERRE' | 'CERRADA';

export interface OfertaAcademica {
  gestion: Gestion;
  grupos: Grupo[];
}
