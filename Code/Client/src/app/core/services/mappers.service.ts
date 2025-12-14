import { Injectable } from '@angular/core';
import { Estudiante, Carrera, Matricula, Calificacion } from '../../models';
import { 
  DtoEstudiante, 
  DtoCarrera, 
  DtoInscripcion, 
  DtoCalificacion, 
  DtoHistorialAcademico,
  DtoMateria,
  DtoAula,
  DtoDocente,
  DtoActaEstudiante,
  EstadoInscripcion 
} from '../../models/backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class MappersService {
  dtoToCarrera(dto: DtoCarrera): Carrera {
    return {
      id: parseInt(dto.codigo) || 0,
      codigo: dto.codigo,
      nombre: dto.nombre,
      duracionSemestres: 10, // ⚠️ Backend no incluye duración, usamos valor por defecto
      facultad: 'General'
    };
  }

  carreraToDto(carrera: Carrera): DtoCarrera {
    return {
      codigo: carrera.codigo,
      nombre: carrera.nombre
      // ⚠️ Backend no acepta duracion en DtoCarrera
    };
  }

  dtoToEstudiante(dto: DtoEstudiante): Estudiante {
    const nombres = dto.nombre.split(' ');
    return {
      id: parseInt(dto.codigo) || 0,
      nombre: nombres[0] || dto.nombre,
      apellido: nombres.slice(1).join(' ') || '',
      email: dto.email,
      rol: 'ESTUDIANTE',
      activo: true,
      codigoEstudiante: dto.codigo,
      carrera: {
        id: 0,
        codigo: 'ING-SIS',
        nombre: 'Ingeniería de Sistemas',
        duracionSemestres: 10,
        facultad: 'General'
      }, // ⚠️ Backend no incluye carrera en DtoEstudiante
      semestre: 1,
      fechaIngreso: new Date()
    };
  }

  estudianteToDto(estudiante: Estudiante): DtoEstudiante {
    return {
      codigo: estudiante.codigoEstudiante,
      nombre: estudiante.nombre + ' ' + estudiante.apellido,
      email: estudiante.email
      // ⚠️ Backend no incluye carrera en DtoEstudiante
    };
  }

  dtoToMateria(dto: DtoMateria): any {
    return {
      id: parseInt(dto.codigo) || 0,
      codigo: dto.codigo,
      nombre: dto.nombre,
      creditos: dto.creditos,
      horasTeoricas: 4, // ⚠️ Backend no incluye horas, usamos valor por defecto
      horasPracticas: 2,
      semestre: dto.semestre || 1,
      prerrequisitos: [],
      activa: true
    };
  }

  materiaToDto(materia: any): DtoMateria {
    return {
      codigo: materia.codigo,
      nombre: materia.nombre,
      creditos: materia.creditos,
      semestre: materia.semestre
    };
  }

  dtoToAula(dto: DtoAula): any {
    return {
      id: parseInt(dto.codigo) || 0,
      codigo: dto.codigo,
      nombre: dto.edificio, // ⚠️ Backend usa "edificio" no "nombre"
      capacidad: dto.capacidad,
      edificio: dto.edificio,
      piso: 1,
      tipoAula: 'TEORIA', // ⚠️ Backend no incluye tipo
      disponible: dto.disponible
    };
  }

  aulaToDto(aula: any): DtoAula {
    return {
      codigo: aula.codigo,
      edificio: aula.edificio,
      capacidad: aula.capacidad,
      disponible: aula.disponible !== undefined ? aula.disponible : true
    };
  }

  dtoToDocente(dto: DtoDocente): any {
    const nombres = dto.nombre.split(' ');
    return {
      id: parseInt(dto.codigo) || 0,
      nombre: nombres[0] || dto.nombre,
      apellido: nombres.slice(1).join(' ') || '',
      email: dto.codigo + '@universidad.edu',
      rol: 'DOCENTE',
      activo: true,
      codigoDocente: dto.codigo,
      especialidad: dto.especialidad,
      departamento: 'General'
    };
  }

  docenteToDto(docente: any): DtoDocente {
    return {
      codigo: docente.codigoDocente || docente.codigo,
      nombre: docente.nombre + ' ' + docente.apellido,
      especialidad: docente.especialidad || 'General'
    };
  }

  dtoToGrupo(dto: any, gestion: any): any {
    return {
      id: parseInt(dto.codigo),
      codigo: dto.nroParalelo?.toString() || '1',
      materia: this.dtoToMateria(dto.materia),
      docente: {
        id: parseInt(dto.docente.codigo),
        nombre: dto.docente.nombre.split(' ')[0],
        apellido: dto.docente.nombre.split(' ').slice(1).join(' '),
        email: dto.docente.codigo + '@universidad.edu'
      },
      aula: this.dtoToAula(dto.aula),
      horarios: [],
      cupoMaximo: dto.cupoMaximo,
      cupoDisponible: dto.cupoMaximo,
      gestion: gestion,
      activo: true
    };
  }

  /**
   * Convierte DtoInscripcion (backend) a Matricula (frontend)
   */
  dtoToMatricula(dto: DtoInscripcion, gestion?: any): Matricula {
    // Crear una gestión por defecto si no se proporciona
    const gestionActual = gestion || {
      id: 1,
      nombre: 'II-2025',
      anio: 2025,
      periodo: 2,
      fechaInicio: new Date('2025-08-01'),
      fechaFin: new Date('2025-12-15'),
      fechaInicioMatricula: new Date('2025-07-15'),
      fechaFinMatricula: new Date('2025-07-30'),
      estado: 'EN_CURSO' as any
    };

    return {
      id: parseInt(dto.codigo),
      estudiante: this.dtoToEstudiante(dto.estudiante),
      grupo: this.dtoToGrupo(dto.paralelo, gestionActual),
      gestion: gestionActual,
      fechaMatricula: new Date(dto.fechaInscripcion),
      estado: this.mapEstadoInscripcion(dto.estado)
    };
  }

  /**
   * Mapea estado de inscripción del backend al frontend
   */
  private mapEstadoInscripcion(estado: EstadoInscripcion): any {
    switch (estado) {
      case EstadoInscripcion.ACEPTADA:
        return 'INSCRITO';
      case EstadoInscripcion.PENDIENTE:
        return 'PENDIENTE';
      case EstadoInscripcion.RECHAZADA:
        return 'RECHAZADA';
      default:
        return 'PENDIENTE';
    }
  }

  /**
   * Convierte DtoCalificacion (backend) a Calificacion (frontend)
   */
  dtoToCalificacion(dto: DtoCalificacion): any {
    // Crear matrícula mock para la calificación
    const matriculaMock: any = {
      id: parseInt(dto.codigo),
      estudiante: this.dtoToEstudiante(dto.estudiante),
      grupo: this.dtoToGrupo(dto.evaluacion.paralelo, null)
    };

    return {
      id: parseInt(dto.codigo),
      matricula: matriculaMock,
      tipoEvaluacion: dto.evaluacion.tipo as any,
      nombre: dto.evaluacion.descripcion,
      nota: dto.nota,
      porcentaje: dto.evaluacion.porcentaje,
      fecha: new Date(dto.evaluacion.fecha)
    };
  }

  /**
   * Convierte DtoHistorialAcademico (backend) a formato frontend
   */
  dtoToHistorial(registros: DtoHistorialAcademico[]): any {
    // Agrupar por gestión
    const porGestion = new Map<string, any[]>();
    
    registros.forEach(reg => {
      if (!porGestion.has(reg.gestion)) {
        porGestion.set(reg.gestion, []);
      }
      porGestion.get(reg.gestion)!.push({
        materia: this.dtoToMateria(reg.materia),
        nota: reg.notaFinal,
        estado: reg.estado
      });
    });

    // Convertir a array de gestiones
    const gestiones = Array.from(porGestion.entries()).map(([gestionNombre, materias]) => {
      const promedio = materias.reduce((sum, m) => sum + m.nota, 0) / materias.length;
      const creditos = materias.reduce((sum, m) => sum + m.materia.creditos, 0);
      
      return {
        gestion: { nombre: gestionNombre },
        materias,
        promedio,
        creditos
      };
    });

    // Calcular totales
    const creditosAprobados = registros
      .filter(r => r.estado === 'APROBADO')
      .reduce((sum, r) => sum + r.materia.creditos, 0);
    
    const promedioGeneral = registros.length > 0
      ? registros.reduce((sum, r) => sum + r.notaFinal, 0) / registros.length
      : 0;

    return {
      gestiones,
      creditosAprobados,
      creditosTotales: 240, // Valor fijo estándar
      promedioGeneral
    };
  }
}
