/**
 * Modelos relacionados con usuarios del sistema
 * Sistema de Gestión Universitaria
 */

export type RolUsuario = 'ESTUDIANTE' | 'DOCENTE' | 'DIRECTOR';

export interface Usuario {
  id: number;
  nombre: string;
  apellido: string;
  email: string;
  password?: string; // Opcional porque no se envía al frontend por seguridad
  rol: RolUsuario;
  activo: boolean;
  fechaCreacion?: Date;
  ultimoAcceso?: Date;
}

export interface Estudiante extends Usuario {
  rol: 'ESTUDIANTE';
  codigoEstudiante: string;
  carrera: Carrera;
  semestre: number;
  fechaIngreso: Date;
}

export interface Docente extends Usuario {
  rol: 'DOCENTE';
  codigoDocente: string;
  departamento: string;
  especialidad: string;
}

export interface Director extends Usuario {
  rol: 'DIRECTOR';
  codigoDirector: string;
  departamento: string;
  carrera?: Carrera;  // Director está asociado a una carrera
}

export interface Carrera {
  id: number;
  nombre: string;
  codigo: string;
  duracionSemestres: number;
  facultad: string;
}

export interface CredencialesLogin {
  email: string;
  password: string;
}

export interface RespuestaLogin {
  usuario: Usuario;
  mensaje: string;
}

// ===== NUEVAS INTERFACES PARA REGISTRO =====

export interface RegistroUsuario {
  nombre: string;
  apellido: string;
  email: string;
  password: string;
  rol: 'ESTUDIANTE' | 'DOCENTE';
}

export interface RegistroEstudiante extends RegistroUsuario {
  rol: 'ESTUDIANTE';
  codigoEstudiante: string;
  carreraId: number;
  semestre: number;
}

export interface RegistroDocente extends RegistroUsuario {
  rol: 'DOCENTE';
  codigoDocente: string;
  departamento: string;
  especialidad: string;
}

export interface RespuestaRegistro {
  exito: boolean;
  mensaje: string;
  usuario?: Usuario;
}
