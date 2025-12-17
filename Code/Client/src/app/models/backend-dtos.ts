/**
 * Backend DTOs - Interfaces que coinciden EXACTAMENTE con los modelos del backend Spring Boot
 *
 * IMPORTANTE:
 * - Estos DTOs reflejan la estructura JSON que devuelve el backend
 * - El backend usa Lombok con @Data que genera getters/setters
 * - Jackson serializa los campos con sus nombres originales en camelCase
 */

// ============================================================================
// CARRERA - Coincide con Carrera.java
// ============================================================================
export interface DtoCarrera {
  codigo: string;
  nombre: string;
  estudiantes?: DtoEstudiante[];
  director?: DtoDirectorCarrera;
  materias?: DtoMateria[];
}

// ============================================================================
// ESTUDIANTE - Coincide con Estudiante.java (extiende AUsuario)
// Backend: codigo, nombre, apellido, email, contrasenna, rol, semestre, carrera
// ============================================================================
export interface DtoEstudiante {
  codigo: string;
  nombre: string;
  apellido: string;
  email: string;
  contrasenna?: string;
  rol?: string;
  semestre?: number;
  carrera?: DtoCarrera;
  materiasInscritas?: DtoMateria[];
  materiasAprobadas?: DtoMateria[];
}

// ============================================================================
// DOCENTE - Coincide con Docente.java (extiende AUsuario)
// Backend: codigo, nombre, apellido, email, contrasenna, rol, departamento, especialidad, activo
// ============================================================================
export interface DtoDocente {
  codigo: string;
  nombre: string;
  apellido: string;
  email: string;
  contrasenna?: string;
  rol?: string;
  departamento?: string;
  especialidad?: string;
  activo?: boolean;
  paraleloMaterias?: DtoParaleloMateria[];
}

// ============================================================================
// DIRECTOR DE CARRERA - Coincide con DirectorCarrera.java
// ============================================================================
export interface DtoDirectorCarrera {
  codigo: string;
  nombre: string;
  apellido: string;
  email: string;
  contrasenna?: string;
  rol?: string;
  carrera?: DtoCarrera;
}

// ============================================================================
// MATERIA - Coincide con Materia.java
// ============================================================================
export interface DtoMateria {
  codigo: string;
  nombre: string;
  semestre?: number;
  creditos?: number;
  activa?: boolean;
  materiasCorrelativas?: DtoMateria[];
  paraleloMaterias?: DtoParaleloMateria[];
  carrera?: DtoCarrera;
}

// ============================================================================
// AULA - Coincide con Aula.java
// Orden en backend: disponible, capacidad, edificio, codigo
// ============================================================================
export interface DtoAula {
  codigo: string;
  edificio: string;
  capacidad: number;
  disponible: boolean;
}

// ============================================================================
// HORARIO - Coincide con Horario.java
// Backend usa: diaSemana, horaInicio (LocalTime), horaFin (LocalTime)
// ============================================================================
export interface DtoHorario {
  diaSemana: string;  // "LUNES", "MARTES", etc.
  horaInicio: string; // LocalTime se serializa como "HH:mm:ss"
  horaFin: string;
}

// ============================================================================
// GESTION - Coincide con Gestion.java
// ============================================================================
export interface DtoGestion {
  codigo: string;
  nombre: string;
  anio: number;
  periodo: number;
  fechaInicio: string;  // Formato "yyyy-MM-dd"
  fechaFin: string;
  fechaInicioMatricula: string;
  fechaFinMatricula: string;
  estado: string;  // "EN_CURSO", "CERRADA", "PLANIFICADA"
  materias?: DtoMateria[];
}

// ============================================================================
// PARALELO/GRUPO - Coincide con ParaleloMateria.java
// Backend: codigo, materia, docente, aula, gestion, cupoMaximo, estudiantes, horarios
// ============================================================================
export interface DtoParaleloMateria {
  codigo: string;
  materia: DtoMateria;
  docente: DtoDocente;
  aula: DtoAula;
  gestion?: DtoGestion;
  cupoMaximo: number;
  estudiantes?: DtoEstudiante[];
  horarios?: DtoHorario[];
}

// ============================================================================
// MATRICULA - Coincide con Matricula.java
// Backend: estado, paraleloMateria, estudiante
// ============================================================================
export interface DtoMatricula {
  estado: string;  // "PENDIENTE", "ACEPTADA", "RECHAZADA"
  paraleloMateria: DtoParaleloMateria;
  estudiante: DtoEstudiante;
}

// Alias: El controlador de inscripciones retorna Matricula
export type DtoInscripcion = DtoMatricula;

// Estados de inscripción/matrícula
export type EstadoInscripcion = 'PENDIENTE' | 'ACEPTADA' | 'RECHAZADA';

// ============================================================================
// EVALUACIÓN - Coincide con Evaluacion.java
// Backend usa: codigo (UUID autogenerado), nombre, porcentaje, paraleloMateria
// ============================================================================

export interface DtoEvaluacion {
  codigo?: string;  // UUID autogenerado por backend
  nombre: string;
  porcentaje: number;  // Double en backend (0-100)
  paraleloMateria: DtoParaleloMateria;
  calificaciones?: DtoCalificacionSimple[];  // Lista opcional
}

// Calificación simple para usar dentro de Evaluación
export interface DtoCalificacionSimple {
  valor: number;
  observaciones: string;
  estudiante: DtoEstudiante;
}

// ============================================================================
// CALIFICACIÓN - Coincide con Calificacion.java
// Backend usa: valor, observaciones, estudiante, evaluacion (NO tiene codigo)
// ============================================================================

export interface DtoCalificacion {
  valor: number;  // Double en backend
  observaciones: string;
  estudiante: DtoEstudiante;
  evaluacion: DtoEvaluacion;
}

// ============================================================================
// ACTA DE ESTUDIANTE
// ============================================================================

export interface DtoActaEstudiante {
  codigo: string;  // String en backend
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
  codigo: string;  // String en backend
  estudiante: DtoEstudiante;
  materia: DtoMateria;
  gestion: string;  // Backend no tiene modelo Gestion, usa String (ej: "2024-1")
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
  inscripcion: DtoMatricula;
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
  // Backend usa modelos completos, no DTOs anidados
}

// ============================================================================
// NOTIFICACIÓN (Observer Pattern)
// ============================================================================

export interface DtoNotificacion {
  estudiante: DtoEstudiante;
  materia: DtoMateria;
  notaFinal: number;
}

// ============================================================================
// USUARIO (para ControladorUsuario)
// ============================================================================

export interface DtoUsuario {
  codigo: string;
  nombre: string;
  apellido: string;
  email: string;
  contrasenna?: string;
  rol: 'ESTUDIANTE' | 'DOCENTE' | 'DIRECTOR';
}

export interface DtoActualizarUsuario {
  nombre?: string;
  apellido?: string;
  email?: string;
  contrasenna?: string;
  especialidad?: string;
  departamento?: string;
}

// ============================================================================
// DASHBOARD (para ControladorDashboard)
// ============================================================================

export interface DtoDashboardEstudiante {
  nombreCompleto: string;
  carrera: string;
  semestre: number;
  materiasInscritas: number;
  creditosActuales: number;
  promedioGeneral: number;
  proximasClases: DtoProximaClase[];
  notificacionesPendientes: number;
  materiasEnCurso: DtoMateriaEnCurso[];
}

export interface DtoDashboardDocente {
  nombreCompleto: string;
  especialidad: string;
  paralelosAsignados: number;
  totalEstudiantes: number;
  proximasClases: DtoProximaClase[];
  paralelosActivos: DtoParaleloActivo[];
  evaluacionesPendientes: number;
}

export interface DtoProximaClase {
  materia: string;
  aula: string;
  horaInicio: string;
  horaFin: string;
  dia: string;
}

export interface DtoMateriaEnCurso {
  codigo: string;
  nombre: string;
  docente: string;
  progreso: number;
  notaActual: number;
}

export interface DtoParaleloActivo {
  codigo: string;
  materia: string;
  estudiantes: number;
  horario: string;
}

// ============================================================================
// REPORTES (para ControladorReporte)
// ============================================================================

export interface DtoReporteEstudiantesPorCarrera {
  carrera: DtoCarrera;
  estudiantes: DtoEstudiante[];
  totalEstudiantes: number;
  fechaGeneracion: string;
  solicitante: string;
}

export interface DtoReporteInscripciones {
  gestion: string;
  inscripciones: DtoMatricula[];
  totalInscripciones: number;
  fechaGeneracion: string;
}

export interface DtoReporteRendimiento {
  paralelo: DtoParaleloMateria;
  estudiantes: DtoEstudiante[];
  promedioGeneral: number;
  aprobados: number;
  reprobados: number;
  fechaGeneracion: string;
  solicitante: string;
}

// ============================================================================
// EVALUACIÓN Y CALIFICACIÓN (para ControladorEvaluacion y ControladorCalificacion)
// ============================================================================

export interface DtoEvaluacionCompleta {
  codigo: string;
  nombre: string;
  porcentaje: number;
  paraleloMateria: DtoParaleloMateria;
  calificaciones?: DtoCalificacionSimple[];
}

export interface DtoCalificacionSimple {
  valor: number;
  observaciones: string;
  estudiante: DtoEstudiante;
  evaluacion?: DtoEvaluacion;
}

// ============================================================================
// ACTA ESTUDIANTE COMPLETA (para ControladorActaEstudiante)
// ============================================================================

export interface DtoActaEstudianteCompleta {
  estudiante: DtoEstudiante;
  paraleloMateria: DtoParaleloMateria;
  calificaciones: DtoCalificacionSimple[];
  calificacionFinal: number;
  aprobado: boolean;
}

