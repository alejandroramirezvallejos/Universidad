import { Injectable } from '@angular/core';
import { Estudiante, Carrera, Matricula, Docente } from '../../models';
import { 
  DtoEstudiante, 
  DtoCarrera, 
  DtoMatricula, 
  DtoCalificacion, 
  DtoHistorialAcademico,
  DtoMateria,
  DtoAula,
  DtoDocente,
  DtoParaleloMateria,
  DtoGestion,
  DtoHorario
} from '../../models/backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class MappersService {
  
  // ============================================================================
  // CARRERA
  // ============================================================================
  
  dtoToCarrera(dto: DtoCarrera): Carrera {
    return {
      id: this.codigoToId(dto.codigo),
      codigo: dto.codigo,
      nombre: dto.nombre,
      duracionSemestres: 10,
      facultad: 'General'
    };
  }

  carreraToDto(carrera: Carrera): DtoCarrera {
    return {
      codigo: carrera.codigo,
      nombre: carrera.nombre
    };
  }

  // ============================================================================
  // ESTUDIANTE - Backend envía: codigo, nombre, apellido, email, semestre, carrera
  // ============================================================================
  
  dtoToEstudiante(dto: DtoEstudiante): Estudiante {
    return {
      id: this.codigoToId(dto.codigo),
      nombre: dto.nombre,
      apellido: dto.apellido || '',
      email: dto.email,
      rol: 'ESTUDIANTE',
      activo: true,
      codigoEstudiante: dto.codigo,
      carrera: dto.carrera ? this.dtoToCarrera(dto.carrera) : this.carreraPorDefecto(),
      semestre: dto.semestre || 1,
      fechaIngreso: new Date()
    };
  }

  estudianteToDto(estudiante: any): DtoEstudiante {
    return {
      codigo: estudiante.codigoEstudiante || estudiante.codigo,
      nombre: estudiante.nombre,
      apellido: estudiante.apellido || '',
      email: estudiante.email,
      contrasenna: estudiante.contrasenna || estudiante.password,
      semestre: estudiante.semestre,
      carrera: estudiante.carrera ? this.carreraToDto(estudiante.carrera) : undefined
    };
  }

  // ============================================================================
  // DOCENTE - Backend envía: codigo, nombre, apellido, email, especialidad, departamento, activo
  // ============================================================================
  
  dtoToDocente(dto: DtoDocente): Docente {
    return {
      id: this.codigoToId(dto.codigo),
      nombre: dto.nombre,
      apellido: dto.apellido || '',
      email: dto.email,
      rol: 'DOCENTE',
      activo: dto.activo !== false,
      codigoDocente: dto.codigo,
      especialidad: dto.especialidad || 'General',
      departamento: dto.departamento || 'General'
    };
  }

  docenteToDto(docente: any): DtoDocente {
    return {
      codigo: docente.codigoDocente || docente.codigo,
      nombre: docente.nombre,
      apellido: docente.apellido || '',
      email: docente.email,
      contrasenna: docente.contrasenna || docente.password,
      especialidad: docente.especialidad || 'General',
      departamento: docente.departamento || 'General',
      activo: docente.activo !== false
    };
  }

  // ============================================================================
  // MATERIA
  // ============================================================================
  
  dtoToMateria(dto: DtoMateria): any {
    return {
      id: this.codigoToId(dto.codigo),
      codigo: dto.codigo,
      nombre: dto.nombre,
      creditos: dto.creditos || 0,
      horasTeoricas: 4,
      horasPracticas: 2,
      semestre: dto.semestre || 1,
      prerrequisitos: dto.materiasCorrelativas?.map(m => this.dtoToMateria(m)) || [],
      activa: dto.activa !== false
    };
  }

  materiaToDto(materia: any): DtoMateria {
    return {
      codigo: materia.codigo,
      nombre: materia.nombre,
      creditos: materia.creditos,
      semestre: materia.semestre,
      activa: materia.activa !== false
    };
  }

  // ============================================================================
  // AULA
  // ============================================================================
  
  dtoToAula(dto: DtoAula): any {
    return {
      id: this.codigoToId(dto.codigo),
      codigo: dto.codigo,
      nombre: `${dto.edificio} - ${dto.codigo}`,
      capacidad: dto.capacidad,
      edificio: dto.edificio,
      piso: 1,
      tipoAula: 'TEORIA',
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

  // ============================================================================
  // GESTION
  // ============================================================================
  
  dtoToGestion(dto: DtoGestion): any {
    return {
      id: this.codigoToId(dto.codigo),
      codigo: dto.codigo,
      nombre: dto.nombre,
      anio: dto.anio,
      periodo: dto.periodo,
      fechaInicio: new Date(dto.fechaInicio),
      fechaFin: new Date(dto.fechaFin),
      fechaInicioMatricula: new Date(dto.fechaInicioMatricula),
      fechaFinMatricula: new Date(dto.fechaFinMatricula),
      estado: dto.estado
    };
  }

  // ============================================================================
  // HORARIO - Backend usa: diaSemana, horaInicio, horaFin
  // ============================================================================
  
  dtoToHorario(dto: DtoHorario): any {
    return {
      id: Math.random(),
      dia: dto.diaSemana,
      horaInicio: dto.horaInicio,
      horaFin: dto.horaFin
    };
  }

  horarioToDto(horario: any): DtoHorario {
    return {
      diaSemana: horario.dia || horario.diaSemana,
      horaInicio: horario.horaInicio,
      horaFin: horario.horaFin
    };
  }

  // ============================================================================
  // PARALELO/GRUPO - Backend envía: codigo, materia, docente, aula, gestion, cupoMaximo, estudiantes, horarios
  // ============================================================================
  
  dtoToGrupo(dto: DtoParaleloMateria, gestion?: any): any {
    const gestionMapeada = dto.gestion ? this.dtoToGestion(dto.gestion) : (gestion || this.gestionPorDefecto());
    
    return {
      id: this.codigoToId(dto.codigo),
      codigo: dto.codigo,
      materia: dto.materia ? this.dtoToMateria(dto.materia) : null,
      docente: dto.docente ? {
        id: this.codigoToId(dto.docente.codigo),
        nombre: dto.docente.nombre,
        apellido: dto.docente.apellido || '',
        email: dto.docente.email
      } : null,
      aula: dto.aula ? this.dtoToAula(dto.aula) : null,
      horarios: dto.horarios?.map(h => this.dtoToHorario(h)) || [],
      cupoMaximo: dto.cupoMaximo || 30,
      cupoDisponible: dto.cupoMaximo - (dto.estudiantes?.length || 0),
      gestion: gestionMapeada,
      activo: true
    };
  }

  grupoToDto(grupo: any): DtoParaleloMateria {
    return {
      codigo: grupo.codigo,
      materia: this.materiaToDto(grupo.materia),
      docente: this.docenteToDto(grupo.docente),
      aula: this.aulaToDto(grupo.aula),
      cupoMaximo: grupo.cupoMaximo,
      horarios: grupo.horarios?.map((h: any) => this.horarioToDto(h)) || []
    };
  }

  // ============================================================================
  // MATRICULA - Backend envía: estado, paraleloMateria, estudiante
  // ============================================================================
  
  dtoToMatricula(dto: DtoMatricula, gestion?: any): Matricula {
    const gestionActual = gestion || this.gestionPorDefecto();
    
    return {
      id: Math.random() * 10000,
      estudiante: this.dtoToEstudiante(dto.estudiante),
      grupo: this.dtoToGrupo(dto.paraleloMateria, gestionActual),
      gestion: gestionActual,
      fechaMatricula: new Date(),
      estado: this.mapEstadoMatricula(dto.estado)
    };
  }

  matriculaToDto(matricula: any): DtoMatricula {
    return {
      estado: matricula.estado === 'INSCRITO' ? 'ACEPTADA' : matricula.estado,
      paraleloMateria: this.grupoToDto(matricula.grupo),
      estudiante: this.estudianteToDto(matricula.estudiante)
    };
  }

  // ============================================================================
  // CALIFICACIÓN - Backend usa: valor, observaciones, estudiante, evaluacion
  // ============================================================================
  
  dtoToCalificacion(dto: DtoCalificacion): any {
    return {
      id: Math.random(),  // Backend no tiene codigo en Calificacion
      nota: dto.valor,    // Backend usa 'valor', frontend usa 'nota'
      observaciones: dto.observaciones,
      estudiante: this.dtoToEstudiante(dto.estudiante),
      evaluacion: dto.evaluacion
    };
  }

  // ============================================================================
  // HISTORIAL ACADÉMICO
  // ============================================================================
  
  dtoToHistorial(registros: DtoHistorialAcademico[]): any {
    const porGestion = new Map<string, any[]>();
    
    registros.forEach(reg => {
      const gestionKey = reg.gestion || 'Sin Gestión';
      if (!porGestion.has(gestionKey)) {
        porGestion.set(gestionKey, []);
      }
      porGestion.get(gestionKey)!.push({
        materia: this.dtoToMateria(reg.materia),
        nota: reg.notaFinal,
        estado: reg.estado
      });
    });

    const gestiones = Array.from(porGestion.entries()).map(([gestionNombre, materias]) => {
      const promedio = materias.reduce((sum, m) => sum + m.nota, 0) / materias.length;
      const creditos = materias.reduce((sum, m) => sum + (m.materia.creditos || 0), 0);
      
      return {
        gestion: { nombre: gestionNombre },
        materias,
        promedio,
        creditos
      };
    });

    const creditosAprobados = registros
      .filter(r => r.estado === 'APROBADO')
      .reduce((sum, r) => sum + (r.materia.creditos || 0), 0);
    
    const promedioGeneral = registros.length > 0
      ? registros.reduce((sum, r) => sum + r.notaFinal, 0) / registros.length
      : 0;

    return {
      gestiones,
      creditosAprobados,
      creditosTotales: 240,
      promedioGeneral
    };
  }

  // ============================================================================
  // HELPERS PRIVADOS
  // ============================================================================
  
  private codigoToId(codigo: string): number {
    if (!codigo) return 0;
    const num = parseInt(codigo.replace(/\D/g, ''));
    return isNaN(num) ? Math.abs(codigo.split('').reduce((a, b) => a + b.charCodeAt(0), 0)) : num;
  }

  private mapEstadoMatricula(estado: string): any {
    switch (estado?.toUpperCase()) {
      case 'ACEPTADA':
        return 'INSCRITO';
      case 'PENDIENTE':
        return 'PENDIENTE';
      case 'RECHAZADA':
        return 'RECHAZADA';
      default:
        return 'PENDIENTE';
    }
  }

  private carreraPorDefecto(): Carrera {
    return {
      id: 1,
      codigo: 'ING-SIS',
      nombre: 'Ingeniería de Sistemas',
      duracionSemestres: 10,
      facultad: 'General'
    };
  }

  private gestionPorDefecto(): any {
    return {
      id: 1,
      codigo: 'II-2025',
      nombre: 'Segundo Semestre 2025',
      anio: 2025,
      periodo: 2,
      fechaInicio: new Date('2025-08-01'),
      fechaFin: new Date('2025-12-15'),
      fechaInicioMatricula: new Date('2025-07-15'),
      fechaFinMatricula: new Date('2025-07-30'),
      estado: 'EN_CURSO'
    };
  }
}
