/**
 * Archivo barrel para exportar todos los servicios core
 * Sistema de Gestión Universitaria
 */

// Servicios principales
export * from './auth.service';
export * from './api.service';
export * from './mappers.service';
export * from './datos-mock.service';
export * from './notificacion.service';

// Servicios de datos maestros
export * from './carreras.service';
export * from './estudiantes.service';
export * from './docentes.service';
export * from './aulas.service';
export * from './paralelos.service';
export * from './gestiones.service';
export * from './materias.service';

// Servicios de operaciones académicas
export * from './oferta-academica.service';
export * from './matricula.service';
export * from './calificaciones.service';
export * from './historial.service';

// Nuevos servicios - Integración completa con backend
export * from './evaluaciones.service';
export * from './actas.service';
export * from './reportes.service';
export * from './dashboard.service';
export * from './usuarios.service';
export * from './directores.service';
export * from './loader.service';
export * from './backend-notificacion.service';

