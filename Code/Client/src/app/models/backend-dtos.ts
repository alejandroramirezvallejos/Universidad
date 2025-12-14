/**
 * Backend DTOs - Interfaces que coinciden EXACTAMENTE con los DTOs del backend Spring Boot
 * 
 * ⚠️ IMPORTANTE:
 * - Los IDs son STRING (no number) - el backend usa String para todos los códigos
 * - Los nombres de propiedades deben coincidir exactamente con los del backend
 * - Estas interfaces representan los datos tal como vienen/van al backend
 * - Para usar en el frontend, hay que mapearlos a los modelos locales
 */

// ============================================================================
// ENUMS
// ============================================================================

export enum EstadoInscripcion {
  PENDIENTE = 'PENDIENTE',
  ACEPTADA = 'ACEPTADA',
  RECHAZADA = 'RECHAZADA'
}

export enum Turno {
  MANANA = 'MANANA',
  TARDE = 'TARDE',
  NOCHE = 'NOCHE'
}

export enum DiasSemana {
  LUNES = 'LUNES',
  MARTES = 'MARTES',
  MIERCOLES = 'MIERCOLES',
  JUEVES = 'JUEVES',
  VIERNES = 'VIERNES',
  SABADO = 'SABADO'
}

// ============================================================================
// CARRERA
// ============================================================================

export interface DtoCarrera {
  codigo: string;  // ⚠️ String en backend
  nombre: string;
  // ⚠️ Backend NO incluye duracion en DtoCarrera
}

// ============================================================================
// ESTUDIANTE
// ============================================================================

export interface DtoEstudiante {
  codigo: string;  // ⚠️ String en backend
  nombre: string;
  email: string;
  // ⚠️ Backend NO incluye carrera en DtoEstudiante
}

// ============================================================================
// DOCENTE
// ============================================================================

export interface DtoDocente {
  codigo: string;  // ⚠️ String en backend
  nombre: string;
  especialidad: string;
}

// ============================================================================
// MATERIA
// ============================================================================

export interface DtoMateria {
  codigo: string;  // ⚠️ String en backend
  nombre: string;
  creditos: number;
  semestre: number;  // ⚠️ Backend usa "semestre" no "nivel"
  // ⚠️ Backend NO incluye carrera ni materiasCorrelativas en DtoMateria simple
}

// ============================================================================
// HORARIO
// ============================================================================

export interface DtoHorario {
  dia: DiasSemana;
  horaInicio: string;  // Formato: "HH:mm:ss"
  horaFin: string;     // Formato: "HH:mm:ss"
}

// ============================================================================
// AULA
// ============================================================================

export interface DtoAula {
  codigo: string;  // ⚠️ String en backend
  edificio: string;
  capacidad: number;
  disponible: boolean;
}

// ============================================================================
// PARALELO/GRUPO
// ============================================================================

export interface DtoParaleloMateria {
  codigo: string;  // ⚠️ String en backend
  nroParalelo: number;
  cupoMaximo: number;
  materia: DtoMateria;
  docente: DtoDocente;
  aula: DtoAula;
  horarios: DtoHorario[];
  turno: Turno;
}

// ============================================================================
// INSCRIPCIÓN (Matrícula)
// ============================================================================

export interface DtoInscripcion {
  codigo: string;  // ⚠️ String en backend
  estudiante: DtoEstudiante;
  paralelo: DtoParaleloMateria;
  estado: EstadoInscripcion;  // ⚠️ PENDIENTE | ACEPTADA | RECHAZADA
  fechaInscripcion: string;  // ISO 8601: "2024-01-15T10:30:00"
}

// ============================================================================
// MATRÍCULA (para PUT /inscripciones/aceptar)
// ============================================================================

export interface DtoMatricula {
  estado: string;  // "PENDIENTE" | "ACEPTADA" | "RECHAZADA"
  paraleloMateria: DtoParaleloMateria;
  estudiante: DtoEstudiante;
}

// ============================================================================
// EVALUACIÓN
// ============================================================================

export interface DtoEvaluacion {
  codigo: string;  // ⚠️ String en backend
  tipo: string;  // "PARCIAL", "FINAL", "PROYECTO"
  descripcion: string;
  fecha: string;  // ISO 8601: "2024-03-15"
  porcentaje: number;  // 0-100
  paralelo: DtoParaleloMateria;
}

// ============================================================================
// CALIFICACIÓN
// ============================================================================

export interface DtoCalificacion {
  codigo: string;  // ⚠️ String en backend
  evaluacion: DtoEvaluacion;
  estudiante: DtoEstudiante;
  nota: number;  // 0-100
  observaciones: string;
}

// ============================================================================
// ACTA DE ESTUDIANTE
// ============================================================================

export interface DtoActaEstudiante {
  codigo: string;  // ⚠️ String en backend
  estudiante: DtoEstudiante;
  paralelo: DtoParaleloMateria;
  notaFinal: number;  // 0-100
  aprobado: boolean;
  fechaRegistro: string;  // ISO 8601
}

// ============================================================================
// HISTORIAL ACADÉMICO
// ============================================================================

export interface DtoHistorialAcademico {
  codigo: string;  // ⚠️ String en backend
  estudiante: DtoEstudiante;
  materia: DtoMateria;
  gestion: string;  // ⚠️ Backend no tiene modelo Gestion, usa String (ej: "2024-1")
  notaFinal: number;  // 0-100
  estado: string;  // "APROBADO" | "REPROBADO"
}

/**
 * Respuesta completa del historial académico agrupado
 */
export interface DtoHistorialCompletoResponse {
  estudiante: DtoEstudiante;
  registros: DtoHistorialAcademico[];
  creditosAprobados: number;
  creditosTotales: number;
  promedioGeneral: number;
}

// ============================================================================
// DIRECTOR DE CARRERA
// ============================================================================

export interface DtoDirectorCarrera {
  codigo: string;  // Código del director
  nombre: string;  // Nombre completo (nombre + apellido)
  email: string;   // Email institucional
}

// ============================================================================
// REQUESTS ESPECÍFICOS (Para POST/PUT)
// ============================================================================

/**
 * Request para crear una inscripción
 * Backend espera objetos completos, no solo IDs
 */
export interface DtoInscripcionRequest {
  estudiante: DtoEstudiante;
  paralelo: DtoParaleloMateria;
}

/**
 * Request para registrar una calificación
 */
export interface DtoCalificacionRequest {
  evaluacion: DtoEvaluacion;
  estudiante: DtoEstudiante;
  nota: number;
  observaciones: string;
}

/**
 * Request para aprobar/rechazar inscripción
 */
export interface DtoInscripcionAprobacionRequest {
  inscripcion: DtoInscripcion;
  aprobar: boolean;
}

// ============================================================================
// NOTAS DE INTEGRACIÓN
// ============================================================================

/**
 * DIFERENCIAS CLAVE ENTRE BACKEND Y FRONTEND:
 * 
 * 1. IDs: Backend usa String, Frontend usa number
 *    - Mapper debe convertir: String.parse() y toString()
 * 
 * 2. Estados de Inscripción:
 *    - Backend: PENDIENTE → ACEPTADA (flujo de dos pasos)
 *    - Frontend: INSCRITO (concepto unificado)
 *    - Mapper debe manejar: ACEPTADA → INSCRITO, otros → NO_INSCRITO
 * 
 * 3. Gestiones:
 *    - Backend: No tiene modelo Gestion, usa String
 *    - Frontend: Tiene interfaz Gestion completa
 *    - Solución: Frontend debe gestionar sus propias Gestiones localmente
 * 
 * 4. Campos faltantes en backend:
 *    - Estudiante.apellido: Backend solo tiene "nombre" completo
 *    - Materia.horasTeoricas/horasPracticas: Backend no las tiene
 *    - Solución: Frontend debe inferir o usar valores por defecto
 * 
 * 5. Fechas:
 *    - Backend: ISO 8601 Strings
 *    - Frontend: Date objects
 *    - Mapper debe convertir con new Date() y toISOString()
 * 
 * 6. Relaciones:
 *    - Backend: Objetos anidados completos
 *    - Frontend: A veces solo IDs
 *    - Al enviar al backend: SIEMPRE objetos completos
 */

// ============================================================================
// ACTA ESTUDIANTE
// ============================================================================

export interface DtoActaEstudiante {
  estudiante: DtoEstudiante;
  paraleloMateria: DtoParaleloMateria;
  // ⚠️ Backend usa modelos completos, no DTOs anidados
}
