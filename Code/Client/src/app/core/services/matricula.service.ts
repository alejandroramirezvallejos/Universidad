import { Injectable, signal, computed } from '@angular/core';
import { Matricula, Grupo } from '../../models';
import { AuthService } from './auth.service';
import { DatosMockService } from './datos-mock.service';
import { ApiService } from './api.service';
import { MappersService } from './mappers.service';
import { DtoInscripcion, DtoInscripcionRequest, DtoMatricula, EstadoInscripcion } from '../../models/backend-dtos';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MatriculaService {
  private matriculasEnProceso = signal<Grupo[]>([]);

  readonly matriculasEnProceso$ = this.matriculasEnProceso.asReadonly();
  readonly cantidadEnProceso = computed(() => this.matriculasEnProceso().length);
  readonly creditosTotales = computed(() => 
    this.matriculasEnProceso().reduce((sum, g) => sum + g.materia.creditos, 0)
  );

  constructor(
    private authService: AuthService,
    private datosMock: DatosMockService,
    private api: ApiService,
    private mappers: MappersService
  ) {}

  /**
   * Obtiene las matrículas del estudiante desde el backend
   * USANDO: GET /api/inscripciones/estudiante/{codigo}
   */
  async obtenerMisMatriculas(): Promise<Matricula[]> {
    const userId = this.authService.usuarioId();
    const usuario = this.authService.usuario();
    const userCodigo = (usuario as any)?.codigoEstudiante || userId.toString();
    
    try {
      // Usar endpoint específico por estudiante
      const dtoInscripciones = await firstValueFrom(
        this.api.get<DtoInscripcion[]>(`/inscripciones/estudiante/${userCodigo}`)
      );
      
      // Convertir DTOs a modelos frontend
      console.log(`${dtoInscripciones.length} inscripciones obtenidas del backend`);
      return dtoInscripciones.map(dto => this.mappers.dtoToMatricula(dto));
    } catch (error) {
      console.error('Error al obtener matrículas desde backend:', error);
      // Fallback a datos mock
      console.warn('Usando datos mock como fallback');
      return this.datosMock.obtenerMatriculasUsuario(userId);
    }
  }

  /**
   * Obtiene inscripciones de un paralelo específico
   * USANDO: GET /api/inscripciones/paralelo/{codigo}
   */
  async obtenerInscripcionesParalelo(codigoParalelo: string): Promise<Matricula[]> {
    try {
      const dtoInscripciones = await firstValueFrom(
        this.api.get<DtoInscripcion[]>(`/inscripciones/paralelo/${codigoParalelo}`)
      );
      
      console.log(`${dtoInscripciones.length} inscripciones obtenidas para paralelo ${codigoParalelo}`);
      return dtoInscripciones.map(dto => this.mappers.dtoToMatricula(dto));
    } catch (error) {
      console.error(`Error al obtener inscripciones del paralelo ${codigoParalelo}:`, error);
      return [];
    }
  }

  /**
   * Cancela una inscripción específica
   * USANDO: DELETE /api/inscripciones/{estudianteCodigo}/{paraleloCodigo}
   */
  async cancelarInscripcion(estudianteCodigo: string, paraleloCodigo: string): Promise<boolean> {
    try {
      await firstValueFrom(
        this.api.delete(`/inscripciones/${estudianteCodigo}/${paraleloCodigo}`)
      );
      
      console.log(`Inscripción cancelada: Estudiante ${estudianteCodigo} - Paralelo ${paraleloCodigo}`);
      return true;
    } catch (error) {
      console.error('Error al cancelar inscripción:', error);
      return false;
    }
  }

  /**
   * Obtiene solo las matrículas activas (ACEPTADAS)
   */
  async obtenerMatriculasActivas(): Promise<Matricula[]> {
    const matriculas = await this.obtenerMisMatriculas();
    return matriculas.filter(m => m.estado === 'INSCRITO');
  }

  agregarAlCarrito(grupo: Grupo): { 
    valido: boolean; 
    errores: any[]; 
    advertencias?: string[] 
  } {
    const validacion = this.validarInscripcion(grupo);
    
    if (validacion.valido) {
      this.matriculasEnProceso.update(list => [...list, grupo]);
    }
    
    return validacion;
  }

  removerDelCarrito(grupoId: number): void {
    this.matriculasEnProceso.update(list => list.filter(g => g.id !== grupoId));
  }

  limpiarCarrito(): void {
    this.matriculasEnProceso.set([]);
  }

  obtenerMateriasInscritas(): number[] {
    return this.matriculasEnProceso().map(g => g.materia.id);
  }

  async confirmarMatricula(): Promise<{ exito: boolean; mensaje: string; matriculas?: Matricula[] }> {
    const gruposEnProceso = this.matriculasEnProceso();
    const usuario = this.authService.usuario();
    
    if (!usuario) {
      return { exito: false, mensaje: 'Debe iniciar sesión' };
    }

    try {
      // USANDO ENDPOINT BATCH: POST /api/inscripciones/batch
      // Preparar todas las inscripciones en un solo payload
      const inscripciones = gruposEnProceso.map(grupo => ({
        estudiante: this.mappers.estudianteToDto(usuario as any),
        paraleloMateria: {
          codigo: grupo.id.toString(),
          nroParalelo: parseInt(grupo.codigo),
          cupoMaximo: grupo.cupoMaximo,
          materia: {
            codigo: grupo.materia.codigo,
            nombre: grupo.materia.nombre,
            creditos: grupo.materia.creditos,
            carrera: this.mappers.carreraToDto((usuario as any).carrera),
            nivel: grupo.materia.semestre,
            materiasCorrelativas: []
          },
          docente: {
            codigo: grupo.docente.id.toString(),
            nombre: `${grupo.docente.nombre} ${grupo.docente.apellido}`,
            especialidad: 'General'
          },
          aula: {
            codigo: grupo.aula.codigo,
            nombre: grupo.aula.nombre,
            capacidad: grupo.aula.capacidad,
            tipo: grupo.aula.tipoAula
          },
          horarios: [],
          turno: 'MANANA'
        }
      }));

      // Enviar batch de inscripciones en una sola petición
      const dtoInscripcionesBatch = { inscripciones };
      const dtoMatriculas = await firstValueFrom(
        this.api.post<DtoInscripcion[]>('/inscripciones/batch', dtoInscripcionesBatch)
      );
      
      // Convertir respuestas a modelos frontend
      const matriculasCreadas = dtoMatriculas.map(dto => this.mappers.dtoToMatricula(dto));
      
      // Limpiar carrito después de confirmar
      this.limpiarCarrito();
      
      console.log(`${matriculasCreadas.length} matrícula(s) confirmada(s) mediante endpoint batch`);
      
      return { 
        exito: true, 
        mensaje: `${matriculasCreadas.length} matrícula(s) confirmada(s) exitosamente`, 
        matriculas: matriculasCreadas 
      };
    } catch (error: any) {
      console.error('Error al confirmar matrícula con backend:', error);
      
      // Fallback: guardar en mock
      const nuevasMatriculas: Matricula[] = gruposEnProceso.map((grupo, index) => ({
        id: Date.now() + index,
        estudiante: usuario as any,
        grupo,
        gestion: grupo.gestion,
        fechaMatricula: new Date(),
        estado: 'INSCRITO' as any
      }));

      this.datosMock.agregarMatriculas(nuevasMatriculas);
      this.limpiarCarrito();

      console.warn('Usando datos mock como fallback');
      
      return { 
        exito: true, 
        mensaje: 'Matrícula confirmada (mock - backend no disponible)', 
        matriculas: nuevasMatriculas 
      };
    }
  }

  validarInscripcion(grupo: Grupo): { 
    valido: boolean; 
    errores: any[]; 
    advertencias?: string[] 
  } {
    const errores: any[] = [];
    const advertencias: string[] = [];

    if (grupo.cupoDisponible <= 0) {
      errores.push({ tipo: 'CUPO', mensaje: 'No hay cupos disponibles' });
    }

    const materiasInscritas = this.obtenerMateriasInscritas();
    if (materiasInscritas.includes(grupo.materia.id)) {
      errores.push({ tipo: 'DUPLICADO', mensaje: 'Ya inscrito en esta materia' });
    }

    return { 
      valido: errores.length === 0, 
      errores,
      advertencias 
    };
  }

  /**
   * Acepta una inscripción pendiente (Director de Carrera)
   * Endpoint: PUT /api/inscripciones/aceptar
   */
  async aceptarInscripcion(matricula: Matricula): Promise<void> {
    try {
      // Construir el DTO simplificado que espera el backend
      const dtoMatricula: DtoMatricula = {
        estado: 'ACEPTADA',
        estudiante: {
          codigo: matricula.estudiante.codigoEstudiante,
          nombre: `${matricula.estudiante.nombre} ${matricula.estudiante.apellido}`,
          email: matricula.estudiante.email
        },
        paraleloMateria: {
          codigo: matricula.grupo.codigo.toString(),
          nroParalelo: parseInt(matricula.grupo.codigo),
          turno: 'MANANA' as any,
          materia: {
            codigo: matricula.grupo.materia.codigo,
            nombre: matricula.grupo.materia.nombre,
            creditos: matricula.grupo.materia.creditos,
            semestre: matricula.grupo.materia.semestre
          },
          docente: {
            codigo: (matricula.grupo.docente as any).codigoDocente || matricula.grupo.docente.id.toString(),
            nombre: `${matricula.grupo.docente.nombre} ${matricula.grupo.docente.apellido}`,
            especialidad: (matricula.grupo.docente as any).especialidad || 'General'
          },
          aula: {
            codigo: matricula.grupo.aula.codigo,
            edificio: matricula.grupo.aula.edificio,
            capacidad: matricula.grupo.aula.capacidad,
            disponible: true
          },
          cupoMaximo: matricula.grupo.cupoMaximo,
          horarios: matricula.grupo.horarios.map(h => ({
            dia: h.dia as any,
            horaInicio: h.horaInicio,
            horaFin: h.horaFin
          }))
        }
      };

      await firstValueFrom(
        this.api.put('/inscripciones/aceptar', dtoMatricula)
      );

      console.log('Inscripción aceptada exitosamente');
    } catch (error) {
      console.error('Error al aceptar inscripción:', error);
      throw error;
    }
  }

  /**
   * Obtiene todas las inscripciones pendientes (Director de Carrera)
   */
  async obtenerInscripcionesPendientes(): Promise<Matricula[]> {
    try {
      const dtoInscripciones = await firstValueFrom(
        this.api.get<DtoInscripcion[]>('/inscripciones')
      );
      
      // Filtrar solo las pendientes
      const inscripcionesPendientes = dtoInscripciones.filter(
        dto => dto.estado === EstadoInscripcion.PENDIENTE
      );
      
      return inscripcionesPendientes.map(dto => this.mappers.dtoToMatricula(dto));
    } catch (error) {
      console.error('Error al obtener inscripciones pendientes:', error);
      return [];
    }
  }
}
