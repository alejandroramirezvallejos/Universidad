/**
 * Servicio de Datos Mock
 * Centraliza todos los datos de prueba para mantener coherencia
 * Este servicio simula el backend SpringBoot
 */

import { Injectable, signal } from '@angular/core';
import { 
  Estudiante, Docente, Usuario, Carrera 
} from '../../models/usuario.model';
import { 
  Materia, Grupo, Aula, Gestion, Horario, DiaSemana 
} from '../../models/academico.model';
import { 
  Matricula, Calificacion, EstadoMatricula, TipoEvaluacion 
} from '../../models/matricula.model';

@Injectable({
  providedIn: 'root'
})
export class DatosMockService {
  // ============ CARRERAS ============
  readonly carreras: Carrera[] = [
    { id: 1, nombre: 'Ingeniería de Sistemas', codigo: 'SIS', duracionSemestres: 10, facultad: 'Facultad de Ingeniería' },
    { id: 2, nombre: 'Ingeniería Industrial', codigo: 'IND', duracionSemestres: 10, facultad: 'Facultad de Ingeniería' }
  ];

  // ============ USUARIOS ============
  readonly estudiantes: Estudiante[] = [
    {
      id: 1, nombre: 'Juan', apellido: 'Pérez', email: 'juan.perez@ucb.edu.bo',
      rol: 'ESTUDIANTE', activo: true, codigoEstudiante: '2023-001',
      carrera: this.carreras[0], semestre: 3, fechaIngreso: new Date('2023-02-01')
    },
    {
      id: 5, nombre: 'Ana', apellido: 'García', email: 'ana.garcia@ucb.edu.bo',
      rol: 'ESTUDIANTE', activo: true, codigoEstudiante: '2023-002',
      carrera: this.carreras[0], semestre: 3, fechaIngreso: new Date('2023-02-01')
    },
    {
      id: 6, nombre: 'Carlos', apellido: 'Mendoza', email: 'carlos.mendoza@ucb.edu.bo',
      rol: 'ESTUDIANTE', activo: true, codigoEstudiante: '2023-003',
      carrera: this.carreras[0], semestre: 3, fechaIngreso: new Date('2023-02-01')
    },
    {
      id: 7, nombre: 'Diana', apellido: 'Torres', email: 'diana.torres@ucb.edu.bo',
      rol: 'ESTUDIANTE', activo: true, codigoEstudiante: '2023-004',
      carrera: this.carreras[0], semestre: 3, fechaIngreso: new Date('2023-02-01')
    },
    {
      id: 8, nombre: 'Eduardo', apellido: 'Paz', email: 'eduardo.paz@ucb.edu.bo',
      rol: 'ESTUDIANTE', activo: true, codigoEstudiante: '2023-005',
      carrera: this.carreras[0], semestre: 3, fechaIngreso: new Date('2023-02-01')
    },
    {
      id: 9, nombre: 'Fernanda', apellido: 'Ruiz', email: 'fernanda.ruiz@ucb.edu.bo',
      rol: 'ESTUDIANTE', activo: true, codigoEstudiante: '2023-006',
      carrera: this.carreras[0], semestre: 3, fechaIngreso: new Date('2023-02-01')
    }
  ];

  readonly docentes: Docente[] = [
    {
      id: 2, nombre: 'María', apellido: 'González', email: 'maria.gonzalez@ucb.edu.bo',
      rol: 'DOCENTE', activo: true, codigoDocente: 'DOC-001',
      departamento: 'Ciencias Exactas', especialidad: 'Matemáticas'
    },
    {
      id: 10, nombre: 'Pedro', apellido: 'Sánchez', email: 'pedro.sanchez@ucb.edu.bo',
      rol: 'DOCENTE', activo: true, codigoDocente: 'DOC-002',
      departamento: 'Informática', especialidad: 'Programación'
    },
    {
      id: 11, nombre: 'Laura', apellido: 'Díaz', email: 'laura.diaz@ucb.edu.bo',
      rol: 'DOCENTE', activo: true, codigoDocente: 'DOC-003',
      departamento: 'Informática', especialidad: 'Base de Datos'
    }
  ];

  readonly usuarios: Usuario[] = [
    ...this.estudiantes,
    ...this.docentes,
    { id: 3, nombre: 'Carlos', apellido: 'Rodríguez', email: 'carlos.rodriguez@ucb.edu.bo', rol: 'DIRECTOR', activo: true }
  ];

  // ============ GESTIONES ============
  readonly gestiones: Gestion[] = [
    {
      id: 1, nombre: 'II-2025', anio: 2025, periodo: 2,
      fechaInicio: new Date('2025-08-01'), fechaFin: new Date('2025-12-15'),
      fechaInicioMatricula: new Date('2025-07-15'), fechaFinMatricula: new Date('2025-07-30'),
      estado: 'EN_CURSO'
    },
    {
      id: 2, nombre: 'I-2025', anio: 2025, periodo: 1,
      fechaInicio: new Date('2025-02-01'), fechaFin: new Date('2025-06-30'),
      fechaInicioMatricula: new Date('2025-01-15'), fechaFinMatricula: new Date('2025-01-30'),
      estado: 'CERRADA'
    },
    {
      id: 3, nombre: 'II-2024', anio: 2024, periodo: 2,
      fechaInicio: new Date('2024-08-01'), fechaFin: new Date('2024-12-15'),
      fechaInicioMatricula: new Date('2024-07-15'), fechaFinMatricula: new Date('2024-07-30'),
      estado: 'CERRADA'
    }
  ];

  // ============ AULAS ============
  readonly aulas: Aula[] = [
    { id: 1, codigo: 'A-101', nombre: 'Aula 101', capacidad: 40, edificio: 'A', piso: 1, tipoAula: 'TEORIA' },
    { id: 2, codigo: 'A-102', nombre: 'Aula 102', capacidad: 35, edificio: 'A', piso: 1, tipoAula: 'TEORIA' },
    { id: 3, codigo: 'LAB-01', nombre: 'Laboratorio 01', capacidad: 30, edificio: 'B', piso: 2, tipoAula: 'LABORATORIO' },
    { id: 4, codigo: 'A-201', nombre: 'Aula 201', capacidad: 45, edificio: 'A', piso: 2, tipoAula: 'TEORIA' },
    { id: 5, codigo: 'AUD-01', nombre: 'Auditorio Principal', capacidad: 200, edificio: 'C', piso: 1, tipoAula: 'AUDITORIO' }
  ];

  // ============ MATERIAS ============
  private _materias: Materia[] = [];
  
  get materias(): Materia[] {
    if (this._materias.length === 0) {
      this._materias = [
        // Semestre 1
        { id: 1, codigo: 'MAT-101', nombre: 'Cálculo I', creditos: 5, horasTeoricas: 4, horasPracticas: 2, semestre: 1, prerrequisitos: [], descripcion: 'Fundamentos de cálculo diferencial', activa: true },
        { id: 3, codigo: 'INF-101', nombre: 'Programación I', creditos: 4, horasTeoricas: 2, horasPracticas: 4, semestre: 1, prerrequisitos: [], descripcion: 'Introducción a la programación', activa: true },
        { id: 7, codigo: 'FIS-101', nombre: 'Física I', creditos: 4, horasTeoricas: 3, horasPracticas: 2, semestre: 1, prerrequisitos: [], descripcion: 'Mecánica y termodinámica', activa: true },
        { id: 9, codigo: 'QMC-101', nombre: 'Química General', creditos: 4, horasTeoricas: 3, horasPracticas: 2, semestre: 1, prerrequisitos: [], descripcion: 'Fundamentos de química', activa: true },
        { id: 10, codigo: 'LEN-101', nombre: 'Lenguaje y Comunicación', creditos: 3, horasTeoricas: 3, horasPracticas: 0, semestre: 1, prerrequisitos: [], descripcion: 'Comunicación oral y escrita', activa: true },
        
        // Semestre 2
        { id: 2, codigo: 'MAT-201', nombre: 'Cálculo II', creditos: 5, horasTeoricas: 4, horasPracticas: 2, semestre: 2, prerrequisitos: [], descripcion: 'Cálculo integral y aplicaciones', activa: true },
        { id: 4, codigo: 'INF-201', nombre: 'Programación II', creditos: 4, horasTeoricas: 2, horasPracticas: 4, semestre: 2, prerrequisitos: [], descripcion: 'Programación orientada a objetos', activa: true },
        { id: 8, codigo: 'MAT-102', nombre: 'Álgebra Lineal', creditos: 4, horasTeoricas: 4, horasPracticas: 2, semestre: 2, prerrequisitos: [], descripcion: 'Vectores, matrices y sistemas lineales', activa: true },
        { id: 11, codigo: 'FIS-201', nombre: 'Física II', creditos: 4, horasTeoricas: 3, horasPracticas: 2, semestre: 2, prerrequisitos: [], descripcion: 'Electromagnetismo y óptica', activa: true },
        { id: 12, codigo: 'EST-101', nombre: 'Estadística I', creditos: 4, horasTeoricas: 3, horasPracticas: 2, semestre: 2, prerrequisitos: [], descripcion: 'Estadística descriptiva e inferencial', activa: true },
        
        // Semestre 3
        { id: 5, codigo: 'INF-301', nombre: 'Estructuras de Datos', creditos: 4, horasTeoricas: 3, horasPracticas: 3, semestre: 3, prerrequisitos: [], descripcion: 'Estructuras de datos y algoritmos', activa: true },
        { id: 13, codigo: 'MAT-301', nombre: 'Matemática Discreta', creditos: 4, horasTeoricas: 4, horasPracticas: 2, semestre: 3, prerrequisitos: [], descripcion: 'Lógica, conjuntos y grafos', activa: true },
        { id: 14, codigo: 'INF-302', nombre: 'Arquitectura de Computadoras', creditos: 4, horasTeoricas: 3, horasPracticas: 2, semestre: 3, prerrequisitos: [], descripcion: 'Organización y arquitectura del computador', activa: true },
        { id: 15, codigo: 'EST-201', nombre: 'Probabilidades', creditos: 4, horasTeoricas: 3, horasPracticas: 2, semestre: 3, prerrequisitos: [], descripcion: 'Teoría de probabilidades', activa: true },
        
        // Semestre 4
        { id: 6, codigo: 'INF-401', nombre: 'Base de Datos', creditos: 4, horasTeoricas: 3, horasPracticas: 3, semestre: 4, prerrequisitos: [], descripcion: 'Diseño y gestión de bases de datos', activa: true },
        { id: 16, codigo: 'INF-402', nombre: 'Análisis y Diseño de Sistemas', creditos: 4, horasTeoricas: 3, horasPracticas: 3, semestre: 4, prerrequisitos: [], descripcion: 'Metodologías de desarrollo de software', activa: true },
        { id: 17, codigo: 'INF-403', nombre: 'Sistemas Operativos', creditos: 4, horasTeoricas: 3, horasPracticas: 2, semestre: 4, prerrequisitos: [], descripcion: 'Gestión de procesos y recursos', activa: true },
        { id: 18, codigo: 'MAT-401', nombre: 'Investigación Operativa', creditos: 4, horasTeoricas: 3, horasPracticas: 2, semestre: 4, prerrequisitos: [], descripcion: 'Optimización y modelos matemáticos', activa: true },
        
        // Semestre 5
        { id: 19, codigo: 'INF-501', nombre: 'Ingeniería de Software', creditos: 4, horasTeoricas: 3, horasPracticas: 3, semestre: 5, prerrequisitos: [], descripcion: 'Procesos y metodologías de desarrollo', activa: true },
        { id: 20, codigo: 'INF-502', nombre: 'Redes de Computadoras', creditos: 4, horasTeoricas: 3, horasPracticas: 2, semestre: 5, prerrequisitos: [], descripcion: 'Protocolos y arquitecturas de redes', activa: true },
        { id: 21, codigo: 'INF-503', nombre: 'Compiladores', creditos: 4, horasTeoricas: 3, horasPracticas: 3, semestre: 5, prerrequisitos: [], descripcion: 'Diseño y construcción de compiladores', activa: true },
        { id: 22, codigo: 'INF-504', nombre: 'Inteligencia Artificial', creditos: 4, horasTeoricas: 3, horasPracticas: 2, semestre: 5, prerrequisitos: [], descripcion: 'Algoritmos de IA y aprendizaje automático', activa: true }
      ];
      
      // Establecer prerrequisitos (cadenas lógicas de aprendizaje)
      
      // Cadena de Cálculo
      this._materias.find(m => m.id === 2)!.prerrequisitos = [this._materias.find(m => m.id === 1)!]; // Cálculo II requiere Cálculo I
      
      // Cadena de Programación
      this._materias.find(m => m.id === 4)!.prerrequisitos = [this._materias.find(m => m.id === 3)!]; // Prog II requiere Prog I
      this._materias.find(m => m.id === 5)!.prerrequisitos = [this._materias.find(m => m.id === 4)!]; // Estructuras de Datos requiere Prog II
      this._materias.find(m => m.id === 6)!.prerrequisitos = [this._materias.find(m => m.id === 5)!]; // BD requiere Estructuras de Datos
      
      // Cadena de Física
      this._materias.find(m => m.id === 11)!.prerrequisitos = [
        this._materias.find(m => m.id === 7)!,  // Física II requiere Física I
        this._materias.find(m => m.id === 1)!   // y Cálculo I
      ];
      
      // Cadena de Estadística
      this._materias.find(m => m.id === 12)!.prerrequisitos = [this._materias.find(m => m.id === 1)!]; // Estadística I requiere Cálculo I
      this._materias.find(m => m.id === 15)!.prerrequisitos = [this._materias.find(m => m.id === 12)!]; // Probabilidades requiere Estadística I
      
      // Matemática Discreta
      this._materias.find(m => m.id === 13)!.prerrequisitos = [this._materias.find(m => m.id === 3)!]; // Mat Discreta requiere Prog I
      
      // Arquitectura de Computadoras
      this._materias.find(m => m.id === 14)!.prerrequisitos = [this._materias.find(m => m.id === 3)!]; // Arq Comp requiere Prog I
      
      // Análisis y Diseño
      this._materias.find(m => m.id === 16)!.prerrequisitos = [
        this._materias.find(m => m.id === 4)!,  // requiere Prog II
        this._materias.find(m => m.id === 6)!   // y Base de Datos
      ];
      
      // Sistemas Operativos
      this._materias.find(m => m.id === 17)!.prerrequisitos = [
        this._materias.find(m => m.id === 5)!,  // requiere Estructuras de Datos
        this._materias.find(m => m.id === 14)!  // y Arquitectura de Computadoras
      ];
      
      // Investigación Operativa
      this._materias.find(m => m.id === 18)!.prerrequisitos = [
        this._materias.find(m => m.id === 2)!,  // requiere Cálculo II
        this._materias.find(m => m.id === 8)!   // y Álgebra Lineal
      ];
      
      // Ingeniería de Software
      this._materias.find(m => m.id === 19)!.prerrequisitos = [
        this._materias.find(m => m.id === 16)!  // requiere Análisis y Diseño
      ];
      
      // Redes de Computadoras
      this._materias.find(m => m.id === 20)!.prerrequisitos = [
        this._materias.find(m => m.id === 17)!  // requiere Sistemas Operativos
      ];
      
      // Compiladores
      this._materias.find(m => m.id === 21)!.prerrequisitos = [
        this._materias.find(m => m.id === 5)!,  // requiere Estructuras de Datos
        this._materias.find(m => m.id === 13)!  // y Matemática Discreta
      ];
      
      // Inteligencia Artificial
      this._materias.find(m => m.id === 22)!.prerrequisitos = [
        this._materias.find(m => m.id === 5)!,  // requiere Estructuras de Datos
        this._materias.find(m => m.id === 15)!  // y Probabilidades
      ];
    }
    return this._materias;
  }

  // ============ GRUPOS ============
  private _grupos: Grupo[] = [];

  get grupos(): Grupo[] {
    if (this._grupos.length === 0) {
      const gestion = this.gestiones[0];
      this._grupos = [
        // Cálculo I - 2 grupos
        this.crearGrupo(1, 'A', this.materias[0], this.docentes[0], this.aulas[0], 
          [{ id: 1, dia: 'LUNES', horaInicio: '08:00', horaFin: '10:00' }, { id: 2, dia: 'MIERCOLES', horaInicio: '08:00', horaFin: '10:00' }],
          40, 15, gestion),
        this.crearGrupo(2, 'B', this.materias[0], this.docentes[0], this.aulas[1],
          [{ id: 3, dia: 'MARTES', horaInicio: '10:00', horaFin: '12:00' }, { id: 4, dia: 'JUEVES', horaInicio: '10:00', horaFin: '12:00' }],
          35, 8, gestion),
        // Cálculo II - 1 grupo
        this.crearGrupo(3, 'A', this.materias[1], this.docentes[0], this.aulas[0],
          [{ id: 5, dia: 'LUNES', horaInicio: '10:00', horaFin: '12:00' }, { id: 6, dia: 'MIERCOLES', horaInicio: '10:00', horaFin: '12:00' }],
          40, 20, gestion),
        // Programación I - 1 grupo
        this.crearGrupo(4, 'A', this.materias[2], this.docentes[1], this.aulas[2],
          [{ id: 7, dia: 'LUNES', horaInicio: '14:00', horaFin: '16:00' }, { id: 8, dia: 'MIERCOLES', horaInicio: '14:00', horaFin: '16:00' }, { id: 9, dia: 'VIERNES', horaInicio: '14:00', horaFin: '16:00' }],
          30, 5, gestion),
        // Programación II - 1 grupo
        this.crearGrupo(5, 'A', this.materias[3], this.docentes[1], this.aulas[2],
          [{ id: 10, dia: 'MARTES', horaInicio: '08:00', horaFin: '10:00' }, { id: 11, dia: 'JUEVES', horaInicio: '08:00', horaFin: '10:00' }],
          30, 12, gestion),
        // Estructuras de Datos - 1 grupo
        this.crearGrupo(6, 'A', this.materias[4], this.docentes[2], this.aulas[3],
          [{ id: 12, dia: 'MARTES', horaInicio: '14:00', horaFin: '16:00' }, { id: 13, dia: 'JUEVES', horaInicio: '14:00', horaFin: '16:00' }],
          45, 20, gestion),
        // Base de Datos - 1 grupo
        this.crearGrupo(7, 'A', this.materias[5], this.docentes[2], this.aulas[2],
          [{ id: 14, dia: 'LUNES', horaInicio: '16:00', horaFin: '18:00' }, { id: 15, dia: 'MIERCOLES', horaInicio: '16:00', horaFin: '18:00' }],
          30, 18, gestion),
        // Física I - 1 grupo
        this.crearGrupo(8, 'A', this.materias[6], this.docentes[0], this.aulas[3],
          [{ id: 16, dia: 'MARTES', horaInicio: '16:00', horaFin: '18:00' }, { id: 17, dia: 'JUEVES', horaInicio: '16:00', horaFin: '18:00' }],
          45, 25, gestion),
        // Álgebra Lineal - 1 grupo
        this.crearGrupo(9, 'A', this.materias[7], this.docentes[0], this.aulas[1],
          [{ id: 18, dia: 'VIERNES', horaInicio: '08:00', horaFin: '12:00' }],
          35, 10, gestion)
      ];
    }
    return this._grupos;
  }

  private crearGrupo(
    id: number, codigo: string, materia: Materia, docente: Docente, aula: Aula,
    horarios: Horario[], cupoMaximo: number, cupoDisponible: number, gestion: Gestion
  ): Grupo {
    return {
      id, codigo, materia,
      docente: { id: docente.id, nombre: docente.nombre, apellido: docente.apellido, email: docente.email },
      aula, horarios, cupoMaximo, cupoDisponible, gestion, activo: true
    };
  }

  // ============ MATRÍCULAS (Estado mutable - SEPARADO POR USUARIO) ============
  // 🔒 CORRECCIÓN CRÍTICA: Cada usuario tiene sus propias matrículas
  // En vez de un array global, usamos Map con userId como clave
  private _matriculasPorUsuario = new Map<number, Matricula[]>();
  
  // Método para inicializar matrículas de un usuario
  private inicializarMatriculasUsuario(userId: number): Matricula[] {
    if (!this._matriculasPorUsuario.has(userId)) {
      this._matriculasPorUsuario.set(userId, []);
    }
    return this._matriculasPorUsuario.get(userId)!;
  }

  // Obtener matrículas de un usuario específico
  obtenerMatriculasUsuario(userId: number): Matricula[] {
    return this._matriculasPorUsuario.get(userId) ?? [];
  }

  // Compatibilidad: mantener getter para código existente pero con warning
  get matriculas(): Matricula[] {
    console.warn('⚠️ USAR obtenerMatriculasUsuario(userId) en vez de matriculas global');
    // Retornar TODAS las matrículas de TODOS los usuarios (solo para admin/director)
    const todasLasMatriculas: Matricula[] = [];
    this._matriculasPorUsuario.forEach(matriculas => {
      todasLasMatriculas.push(...matriculas);
    });
    return todasLasMatriculas;
  }

  agregarMatriculas(nuevas: Matricula[]): void {
    if (nuevas.length === 0) return;
    
    // Agrupar por estudiante
    const userId = nuevas[0].estudiante.id;
    const matriculasActuales = this.inicializarMatriculasUsuario(userId);
    this._matriculasPorUsuario.set(userId, [...matriculasActuales, ...nuevas]);
  }

  actualizarMatricula(matriculaId: number, cambios: Partial<Matricula>): void {
    // Buscar en qué usuario está esta matrícula
    this._matriculasPorUsuario.forEach((matriculas, userId) => {
      const index = matriculas.findIndex(m => m.id === matriculaId);
      if (index !== -1) {
        const matriculasActualizadas = [...matriculas];
        matriculasActualizadas[index] = { ...matriculasActualizadas[index], ...cambios };
        this._matriculasPorUsuario.set(userId, matriculasActualizadas);
      }
    });
  }

  // ============ CALIFICACIONES (Estado mutable - SEPARADO POR USUARIO) ============
  // 🔒 CORRECCIÓN CRÍTICA: Cada usuario tiene sus propias calificaciones
  private _calificacionesPorUsuario = new Map<number, Calificacion[]>();

  // Inicializar calificaciones de un usuario
  private inicializarCalificacionesUsuario(userId: number): Calificacion[] {
    if (!this._calificacionesPorUsuario.has(userId)) {
      this._calificacionesPorUsuario.set(userId, []);
    }
    return this._calificacionesPorUsuario.get(userId)!;
  }

  // Obtener calificaciones de un usuario específico
  obtenerCalificacionesUsuario(userId: number): Calificacion[] {
    return this._calificacionesPorUsuario.get(userId) ?? [];
  }

  // Compatibilidad: getter para código existente
  get calificaciones(): Calificacion[] {
    console.warn('⚠️ USAR obtenerCalificacionesUsuario(userId) en vez de calificaciones global');
    const todasLasCalificaciones: Calificacion[] = [];
    this._calificacionesPorUsuario.forEach(calificaciones => {
      todasLasCalificaciones.push(...calificaciones);
    });
    return todasLasCalificaciones;
  }

  agregarCalificacion(calificacion: Calificacion): void {
    const userId = calificacion.matricula.estudiante.id;
    const calificacionesActuales = this.inicializarCalificacionesUsuario(userId);
    this._calificacionesPorUsuario.set(userId, [...calificacionesActuales, calificacion]);
  }

  actualizarCalificacion(calificacionId: number, nota: number): void {
    // Buscar en qué usuario está esta calificación
    this._calificacionesPorUsuario.forEach((calificaciones, userId) => {
      const index = calificaciones.findIndex(c => c.id === calificacionId);
      if (index !== -1) {
        const calificacionesActualizadas = [...calificaciones];
        calificacionesActualizadas[index] = { ...calificacionesActualizadas[index], nota };
        this._calificacionesPorUsuario.set(userId, calificacionesActualizadas);
      }
    });
  }

  // ============ MATERIAS APROBADAS POR ESTUDIANTE ============
  private _materiasAprobadas: Map<number, number[]> = new Map([
    [1, [1, 3, 7]], // Juan Pérez aprobó: Cálculo I, Prog I, Física I
    [5, [1, 3]], // Ana García aprobó: Cálculo I, Prog I
    [6, [1, 2, 3, 4]], // Carlos Mendoza aprobó más materias
  ]);

  obtenerMateriasAprobadas(estudianteId: number): number[] {
    return this._materiasAprobadas.get(estudianteId) ?? [];
  }

  aprobarMateria(estudianteId: number, materiaId: number): void {
    const actuales = this._materiasAprobadas.get(estudianteId) ?? [];
    if (!actuales.includes(materiaId)) {
      this._materiasAprobadas.set(estudianteId, [...actuales, materiaId]);
    }
  }

  // ============ HISTORIAL ACADÉMICO ============
  obtenerHistorialEstudiante(estudianteId: number): any {
    // Simula historial de gestiones pasadas
    return {
      gestiones: [
        {
          gestion: this.gestiones[1], // I-2025
          materias: [
            { materia: this.materias[0], nota: 78, estado: 'APROBADO' }, // Cálculo I
            { materia: this.materias[2], nota: 85, estado: 'APROBADO' }, // Prog I
            { materia: this.materias[7], nota: 72, estado: 'APROBADO' }, // Álgebra
            { materia: this.materias[6], nota: 68, estado: 'APROBADO' }  // Física I
          ],
          promedio: 75.75,
          creditos: 17
        },
        {
          gestion: this.gestiones[2], // II-2024
          materias: [
            { materia: { codigo: 'MAT-100', nombre: 'Matemáticas Básicas', creditos: 4 }, nota: 82, estado: 'APROBADO' },
            { materia: { codigo: 'INF-100', nombre: 'Intro. Computación', creditos: 3 }, nota: 90, estado: 'APROBADO' },
            { materia: { codigo: 'LEN-101', nombre: 'Comunicación', creditos: 2 }, nota: 88, estado: 'APROBADO' }
          ],
          promedio: 86.67,
          creditos: 9
        }
      ],
      creditosAprobados: 26,
      creditosTotales: 240,
      promedioGeneral: 80.43
    };
  }

  // ============ GRUPOS DEL DOCENTE ============
  obtenerGruposDocente(docenteId: number): Grupo[] {
    return this.grupos.filter(g => g.docente.id === docenteId);
  }

  // ============ ESTUDIANTES DE UN GRUPO ============
  obtenerEstudiantesGrupo(grupoId: number): Estudiante[] {
    // Retorna estudiantes inscritos en el grupo
    const matriculasGrupo = this.matriculas.filter(m => m.grupo.id === grupoId && m.estado === 'INSCRITO');
    return matriculasGrupo.map(m => m.estudiante);
  }

  // Inicializar con algunas matrículas de ejemplo para el docente
  constructor() {
    this.inicializarDatosDocente();
  }

  private inicializarDatosDocente(): void {
    // El estudiante Juan Pérez ya está inscrito en algunas materias (para pruebas)
    const estudiante = this.estudiantes[0];
    const gestion = this.gestiones[0];
    
    // Inscrito en Cálculo II y Programación II
    const matriculasIniciales: Matricula[] = [
      {
        id: 1,
        estudiante,
        grupo: this.grupos[2], // Cálculo II
        gestion,
        fechaMatricula: new Date('2025-07-20'),
        estado: 'INSCRITO'
      },
      {
        id: 2,
        estudiante,
        grupo: this.grupos[4], // Programación II
        gestion,
        fechaMatricula: new Date('2025-07-20'),
        estado: 'INSCRITO'
      }
    ];

    // Inscribir otros estudiantes en grupos del docente María González
    const otrosEstudiantes = this.estudiantes.slice(1, 6);
    let idCounter = 10;
    
    otrosEstudiantes.forEach(est => {
      matriculasIniciales.push({
        id: idCounter++,
        estudiante: est,
        grupo: this.grupos[0], // Cálculo I grupo A
        gestion,
        fechaMatricula: new Date('2025-07-18'),
        estado: 'INSCRITO'
      });
    });

    // 🔒 INICIALIZAR MATRÍCULAS POR USUARIO
    // Agrupar matrículas por estudiante
    const matriculasPorEstudiante = new Map<number, Matricula[]>();
    matriculasIniciales.forEach(m => {
      const userId = m.estudiante.id;
      const matriculasActuales = matriculasPorEstudiante.get(userId) ?? [];
      matriculasPorEstudiante.set(userId, [...matriculasActuales, m]);
    });
    
    // Guardar en el Map por usuario
    matriculasPorEstudiante.forEach((matriculas, userId) => {
      this._matriculasPorUsuario.set(userId, matriculas);
    });

    // Crear algunas calificaciones de ejemplo
    const califsIniciales: Calificacion[] = [];
    let califId = 1;
    
    // Calificaciones para estudiantes en Cálculo I
    otrosEstudiantes.forEach((est, idx) => {
      const matricula = matriculasIniciales.find(m => m.estudiante.id === est.id && m.grupo.id === 1);
      if (matricula) {
        califsIniciales.push({
          id: califId++,
          matricula,
          tipoEvaluacion: 'PARCIAL',
          nombre: 'Primer Parcial',
          nota: 60 + (idx * 8) % 35,
          porcentaje: 25,
          fecha: new Date('2025-09-15')
        });
        califsIniciales.push({
          id: califId++,
          matricula,
          tipoEvaluacion: 'PARCIAL',
          nombre: 'Segundo Parcial',
          nota: 55 + (idx * 10) % 40,
          porcentaje: 25,
          fecha: new Date('2025-10-20')
        });
      }
    });

    // 🔒 INICIALIZAR CALIFICACIONES POR USUARIO
    // Agrupar calificaciones por estudiante
    const calificacionesPorEstudiante = new Map<number, Calificacion[]>();
    califsIniciales.forEach(c => {
      const userId = c.matricula.estudiante.id;
      const calificacionesActuales = calificacionesPorEstudiante.get(userId) ?? [];
      calificacionesPorEstudiante.set(userId, [...calificacionesActuales, c]);
    });
    
    // Guardar en el Map por usuario
    calificacionesPorEstudiante.forEach((calificaciones, userId) => {
      this._calificacionesPorUsuario.set(userId, calificaciones);
    });
  }
}
