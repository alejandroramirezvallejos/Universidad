/**
 * Servicio Loader - Datos Iniciales de Prueba
 * Conversión de Server/configuracion/Loader.java a TypeScript
 *
 * Este servicio proporciona datos mock para desarrollo y pruebas
 * cuando el backend no está disponible.
 */

import { Injectable, inject } from '@angular/core';
import { GestionesService } from './gestiones.service';
import { CarrerasService } from './carreras.service';
import { EstudiantesService } from './estudiantes.service';
import { DocentesService } from './docentes.service';
import { DirectoresService } from './directores.service';
import { AulasService } from './aulas.service';
import { MateriasService } from './materias.service';
import { ParalelosService } from './paralelos.service';

export interface DatosIniciales {
  gestiones: GestionMock[];
  carreras: CarreraMock[];
  estudiantes: EstudianteMock[];
  docentes: DocenteMock[];
  directores: DirectorMock[];
  aulas: AulaMock[];
  materias: MateriaMock[];
  paralelos: ParaleloMock[];
}

export interface GestionMock {
  codigo: string;
  nombre: string;
  anio: number;
  periodo: number;
  fechaInicio: string;
  fechaFin: string;
  fechaInicioMatricula: string;
  fechaFinMatricula: string;
  estado: string;
}

export interface CarreraMock {
  codigo: string;
  nombre: string;
}

export interface EstudianteMock {
  codigo: string;
  nombre: string;
  apellido: string;
  email: string;
  carreraCodigo: string;
}

export interface DocenteMock {
  codigo: string;
  nombre: string;
  apellido: string;
  email: string;
  especialidad: string;
}

export interface DirectorMock {
  codigo: string;
  nombre: string;
  apellido: string;
  email: string;
  carreraCodigo: string;
}

export interface AulaMock {
  codigo: string;
  edificio: string;
  capacidad: number;
  disponible: boolean;
}

export interface MateriaMock {
  codigo: string;
  nombre: string;
  semestre: number;
  creditos: number;
  correlativas?: string[];
}

export interface HorarioMock {
  dia: string;
  horaInicio: string;
  horaFin: string;
}

export interface ParaleloMock {
  codigo: string;
  materiaCodigo: string;
  docenteCodigo: string;
  aulaCodigo: string;
  cupoMaximo: number;
  horarios: HorarioMock[];
}

@Injectable({
  providedIn: 'root'
})
export class LoaderService {
  private gestionesService = inject(GestionesService);
  private carrerasService = inject(CarrerasService);
  private estudiantesService = inject(EstudiantesService);
  private docentesService = inject(DocentesService);
  private directoresService = inject(DirectoresService);
  private aulasService = inject(AulasService);
  private materiasService = inject(MateriasService);
  private paralelosService = inject(ParalelosService);

  /**
   * Datos iniciales de prueba - Conversión directa de Loader.java
   */
  readonly datosIniciales: DatosIniciales = {
    gestiones: [
      {
        codigo: 'II-2025',
        nombre: 'Segundo Semestre 2025',
        anio: 2025,
        periodo: 2,
        fechaInicio: '2025-08-01',
        fechaFin: '2025-12-15',
        fechaInicioMatricula: '2025-07-15',
        fechaFinMatricula: '2025-07-30',
        estado: 'EN_CURSO'
      },
      {
        codigo: 'I-2025',
        nombre: 'Primer Semestre 2025',
        anio: 2025,
        periodo: 1,
        fechaInicio: '2025-02-01',
        fechaFin: '2025-06-30',
        fechaInicioMatricula: '2025-01-15',
        fechaFinMatricula: '2025-01-30',
        estado: 'CERRADA'
      },
      {
        codigo: 'II-2024',
        nombre: 'Segundo Semestre 2024',
        anio: 2024,
        periodo: 2,
        fechaInicio: '2024-08-01',
        fechaFin: '2024-12-15',
        fechaInicioMatricula: '2024-07-15',
        fechaFinMatricula: '2024-07-30',
        estado: 'CERRADA'
      }
    ],

    carreras: [
      { codigo: 'ING-SIS', nombre: 'Ingeniería de Sistemas' },
      { codigo: 'ING-IND', nombre: 'Ingeniería Industrial' },
      { codigo: 'ADM-EMP', nombre: 'Administración de Empresas' }
    ],

    estudiantes: [
      {
        codigo: 'EST001',
        nombre: 'Juan',
        apellido: 'Pérez',
        email: 'juan.perez@ucb.edu.bo',
        carreraCodigo: 'ING-SIS'
      },
      {
        codigo: 'EST002',
        nombre: 'Ana',
        apellido: 'Martínez',
        email: 'ana.martinez@ucb.edu.bo',
        carreraCodigo: 'ING-IND'
      }
    ],

    docentes: [
      {
        codigo: 'DOC001',
        nombre: 'María',
        apellido: 'González',
        email: 'maria.gonzalez@ucb.edu.bo',
        especialidad: 'Ingeniería de Software'
      },
      {
        codigo: 'DOC002',
        nombre: 'Pedro',
        apellido: 'López',
        email: 'pedro.lopez@ucb.edu.bo',
        especialidad: 'Base de Datos'
      }
    ],

    directores: [
      {
        codigo: 'DIR001',
        nombre: 'Carlos',
        apellido: 'Rodríguez',
        email: 'carlos.rodriguez@ucb.edu.bo',
        carreraCodigo: 'ING-SIS'
      },
      {
        codigo: 'DIR002',
        nombre: 'Laura',
        apellido: 'Fernández',
        email: 'laura.fernandez@ucb.edu.bo',
        carreraCodigo: 'ING-IND'
      }
    ],

    aulas: [
      { codigo: 'A-201', edificio: 'Edificio A', capacidad: 30, disponible: true },
      { codigo: 'A-202', edificio: 'Edificio A', capacidad: 35, disponible: true },
      { codigo: 'B-101', edificio: 'Edificio B', capacidad: 40, disponible: true },
      { codigo: 'B-102', edificio: 'Edificio B', capacidad: 25, disponible: true },
      { codigo: 'C-301', edificio: 'Edificio C', capacidad: 45, disponible: true }
    ],

    materias: [
      { codigo: 'SIS-101', nombre: 'Programación I', semestre: 1, creditos: 4 },
      { codigo: 'MAT-101', nombre: 'Matemáticas I', semestre: 1, creditos: 4 },
      { codigo: 'FIS-101', nombre: 'Física I', semestre: 1, creditos: 3 },
      { codigo: 'SIS-201', nombre: 'Programación II', semestre: 2, creditos: 4, correlativas: ['SIS-101'] },
      { codigo: 'SIS-202', nombre: 'Estructuras de Datos', semestre: 2, creditos: 4, correlativas: ['SIS-101'] },
      { codigo: 'SIS-203', nombre: 'Base de Datos I', semestre: 2, creditos: 3 },
      { codigo: 'SIS-301', nombre: 'Algoritmos Avanzados', semestre: 3, creditos: 4, correlativas: ['SIS-202'] },
      { codigo: 'SIS-302', nombre: 'Base de Datos II', semestre: 3, creditos: 4, correlativas: ['SIS-203'] },
      { codigo: 'SIS-303', nombre: 'Ingeniería de Software', semestre: 3, creditos: 4, correlativas: ['SIS-201'] },
      { codigo: 'SIS-401', nombre: 'Arquitectura de Software', semestre: 4, creditos: 4, correlativas: ['SIS-303'] },
      { codigo: 'SIS-402', nombre: 'Desarrollo Web', semestre: 4, creditos: 4, correlativas: ['SIS-203'] }
    ],

    paralelos: [
      {
        codigo: 'SIS-101-A',
        materiaCodigo: 'SIS-101',
        docenteCodigo: 'DOC001',
        aulaCodigo: 'A-201',
        cupoMaximo: 30,
        horarios: [
          { dia: 'LUNES', horaInicio: '08:00', horaFin: '10:00' },
          { dia: 'MIERCOLES', horaInicio: '08:00', horaFin: '10:00' }
        ]
      },
      {
        codigo: 'SIS-101-B',
        materiaCodigo: 'SIS-101',
        docenteCodigo: 'DOC002',
        aulaCodigo: 'A-202',
        cupoMaximo: 35,
        horarios: [
          { dia: 'MARTES', horaInicio: '10:00', horaFin: '12:00' },
          { dia: 'JUEVES', horaInicio: '10:00', horaFin: '12:00' }
        ]
      },
      {
        codigo: 'SIS-203-A',
        materiaCodigo: 'SIS-203',
        docenteCodigo: 'DOC002',
        aulaCodigo: 'B-101',
        cupoMaximo: 40,
        horarios: [
          { dia: 'LUNES', horaInicio: '14:00', horaFin: '16:00' },
          { dia: 'MIERCOLES', horaInicio: '14:00', horaFin: '16:00' }
        ]
      },
      {
        codigo: 'SIS-303-A',
        materiaCodigo: 'SIS-303',
        docenteCodigo: 'DOC001',
        aulaCodigo: 'C-301',
        cupoMaximo: 45,
        horarios: [
          { dia: 'MARTES', horaInicio: '08:00', horaFin: '10:00' },
          { dia: 'JUEVES', horaInicio: '08:00', horaFin: '10:00' }
        ]
      },
      {
        codigo: 'SIS-402-A',
        materiaCodigo: 'SIS-402',
        docenteCodigo: 'DOC001',
        aulaCodigo: 'B-102',
        cupoMaximo: 25,
        horarios: [
          { dia: 'VIERNES', horaInicio: '10:00', horaFin: '13:00' }
        ]
      }
    ]
  };

  /**
   * Usuarios de prueba para login rápido
   */
  readonly usuariosPrueba = {
    estudiante: {
      email: 'juan.perez@ucb.edu.bo',
      password: 'password123',
      rol: 'ESTUDIANTE',
      nombre: 'Juan Pérez',
      codigo: 'EST001'
    },
    docente: {
      email: 'maria.gonzalez@ucb.edu.bo',
      password: 'password123',
      rol: 'DOCENTE',
      nombre: 'María González',
      codigo: 'DOC001'
    },
    director: {
      email: 'carlos.rodriguez@ucb.edu.bo',
      password: 'password123',
      rol: 'DIRECTOR',
      nombre: 'Carlos Rodríguez',
      codigo: 'DIR001'
    }
  };

  /**
   * Cargar todos los datos iniciales en los servicios
   * Útil cuando el backend no está disponible
   */
  cargarDatosIniciales(): void {
    console.log('════════════════════════════════════════════════════════════');
    console.log('🔧 CARGANDO DATOS DE PRUEBA (Frontend)');
    console.log('════════════════════════════════════════════════════════════');

    this.cargarGestiones();
    this.cargarCarreras();
    this.cargarAulas();
    this.cargarMaterias();

    this.imprimirResumen();
  }

  private cargarGestiones(): void {
    console.log('📅 Cargando Gestiones Académicas...');
    // Los datos se cargan en los signals de cada servicio cuando se llaman los endpoints
    this.datosIniciales.gestiones.forEach(g => {
      console.log(`   ✓ ${g.codigo} (${g.estado})`);
    });
  }

  private cargarCarreras(): void {
    console.log('📚 Cargando Carreras...');
    this.datosIniciales.carreras.forEach(c => {
      console.log(`   ✓ ${c.nombre} (${c.codigo})`);
    });
  }

  private cargarAulas(): void {
    console.log('🏛️  Cargando Aulas...');
    this.datosIniciales.aulas.forEach(a => {
      console.log(`   ✓ Aula ${a.codigo} (Capacidad: ${a.capacidad})`);
    });
  }

  private cargarMaterias(): void {
    console.log('📖 Cargando Materias...');
    this.datosIniciales.materias.forEach(m => {
      console.log(`   ✓ ${m.nombre} (${m.codigo}) - Semestre ${m.semestre}`);
    });
  }

  private imprimirResumen(): void {
    console.log('\n════════════════════════════════════════════════════════════');
    console.log('✅ DATOS MOCK DISPONIBLES');
    console.log('════════════════════════════════════════════════════════════');
    console.log('\n📋 RESUMEN:');
    console.log(`   • ${this.datosIniciales.gestiones.length} Gestiones académicas`);
    console.log(`   • ${this.datosIniciales.carreras.length} Carreras`);
    console.log(`   • ${this.datosIniciales.estudiantes.length} Estudiantes`);
    console.log(`   • ${this.datosIniciales.docentes.length} Docentes`);
    console.log(`   • ${this.datosIniciales.directores.length} Directores`);
    console.log(`   • ${this.datosIniciales.aulas.length} Aulas`);
    console.log(`   • ${this.datosIniciales.materias.length} Materias`);
    console.log(`   • ${this.datosIniciales.paralelos.length} Paralelos`);
    console.log('\n📋 USUARIOS DE PRUEBA PARA LOGIN:');
    console.log('┌─────────────────────────────────────────────────────────┐');
    console.log('│ ESTUDIANTE: juan.perez@ucb.edu.bo                       │');
    console.log('│ DOCENTE: maria.gonzalez@ucb.edu.bo                      │');
    console.log('│ DIRECTOR: carlos.rodriguez@ucb.edu.bo                   │');
    console.log('│ Password: password123                                   │');
    console.log('└─────────────────────────────────────────────────────────┘');
    console.log('════════════════════════════════════════════════════════════\n');
  }

  /**
   * Obtener datos de una gestión específica
   */
  getGestion(codigo: string): GestionMock | undefined {
    return this.datosIniciales.gestiones.find(g => g.codigo === codigo);
  }

  /**
   * Obtener datos de una carrera específica
   */
  getCarrera(codigo: string): CarreraMock | undefined {
    return this.datosIniciales.carreras.find(c => c.codigo === codigo);
  }

  /**
   * Obtener datos de un estudiante específico
   */
  getEstudiante(codigo: string): EstudianteMock | undefined {
    return this.datosIniciales.estudiantes.find(e => e.codigo === codigo);
  }

  /**
   * Obtener datos de un docente específico
   */
  getDocente(codigo: string): DocenteMock | undefined {
    return this.datosIniciales.docentes.find(d => d.codigo === codigo);
  }

  /**
   * Obtener datos de una materia específica
   */
  getMateria(codigo: string): MateriaMock | undefined {
    return this.datosIniciales.materias.find(m => m.codigo === codigo);
  }

  /**
   * Obtener paralelos de una materia
   */
  getParalelosPorMateria(materiaCodigo: string): ParaleloMock[] {
    return this.datosIniciales.paralelos.filter(p => p.materiaCodigo === materiaCodigo);
  }

  /**
   * Obtener paralelos de un docente
   */
  getParalelosPorDocente(docenteCodigo: string): ParaleloMock[] {
    return this.datosIniciales.paralelos.filter(p => p.docenteCodigo === docenteCodigo);
  }

  /**
   * Obtener gestión activa (EN_CURSO)
   */
  getGestionActiva(): GestionMock | undefined {
    return this.datosIniciales.gestiones.find(g => g.estado === 'EN_CURSO');
  }
}

