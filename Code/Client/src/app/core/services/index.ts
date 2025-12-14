/**
 * Archivo barrel para exportar todos los servicios core
 */

export * from './auth.service';
export * from './oferta-academica.service';
export * from './matricula.service';
export * from './calificaciones.service';
export * from './notificacion.service';
export * from './datos-mock.service';
export * from './api.service';
export * from './mappers.service';

// Servicios de datos maestros
export * from './carreras.service';
export * from './estudiantes.service';
export * from './docentes.service';
export * from './aulas.service';
export * from './paralelos.service';
export * from './gestiones.service';

// Nuevos servicios con endpoints backend
export * from './materias.service';
export * from './historial.service';
