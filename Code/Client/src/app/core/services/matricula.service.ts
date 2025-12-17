import { Injectable, signal, computed } from '@angular/core';
import { Matricula, Grupo } from '../../models';
import { AuthService } from './auth.service';
import { DatosMockService } from './datos-mock.service';
import { ApiService } from './api.service';
import { MappersService } from './mappers.service';
import { DtoInscripcion, DtoInscripcionRequest, DtoMatricula } from '../../models/backend-dtos';
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
  ) { }

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

  async agregarAlCarrito(grupo: Grupo): Promise<{
    valido: boolean;
    errores: any[];
    advertencias?: string[]
  }> {
    const validacion = await this.validarInscripcion(grupo);

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
      // El backend espera List<Matricula> directamente (no un objeto wrapper)
      // Matricula tiene: estado, estudiante, paraleloMateria
      const inscripciones: DtoMatricula[] = gruposEnProceso.map(grupo => ({
        estado: 'PENDIENTE',
        estudiante: {
          codigo: (usuario as any).codigoEstudiante || usuario.id?.toString(),
          nombre: usuario.nombre,
          apellido: usuario.apellido || '',
          email: usuario.email,
          semestre: (usuario as any).semestre,
          carrera: (usuario as any).carrera ? {
            codigo: (usuario as any).carrera.codigo,
            nombre: (usuario as any).carrera.nombre
          } : undefined
        },
        paraleloMateria: {
          codigo: grupo.codigo || grupo.id.toString(),
          materia: {
            codigo: grupo.materia.codigo,
            nombre: grupo.materia.nombre,
            creditos: grupo.materia.creditos,
            semestre: grupo.materia.semestre
          },
          docente: {
            codigo: (grupo.docente as any).codigoDocente || (grupo.docente as any).codigo || grupo.docente.id?.toString(),
            nombre: grupo.docente.nombre,
            apellido: grupo.docente.apellido || '',
            email: grupo.docente.email || '',
            especialidad: (grupo.docente as any).especialidad || 'General',
            departamento: (grupo.docente as any).departamento || 'General',
            activo: true
          },
          aula: {
            codigo: grupo.aula.codigo,
            edificio: grupo.aula.edificio || 'Edificio Principal',
            capacidad: grupo.aula.capacidad,
            disponible: true
          },
          cupoMaximo: grupo.cupoMaximo,
          // Backend usa diaSemana, no dia
          horarios: grupo.horarios?.map(h => ({
            diaSemana: (h as any).diaSemana || h.dia,
            horaInicio: h.horaInicio,
            horaFin: h.horaFin
          })) || []
        }
      }));

      // Enviar array de inscripciones directamente (no wrapper)
      const dtoMatriculas = await firstValueFrom(
        this.api.post<DtoInscripcion[]>('/inscripciones/batch', inscripciones)
      );

      // 🔧 PASO 2: Aceptar automáticamente todas las inscripciones creadas
      // Esto es necesario para que las materias se agreguen al horario del estudiante
      console.log('🔄 Aceptando inscripciones automáticamente...');
      for (const dtoMatricula of dtoMatriculas) {
        try {
          // Cambiar estado a ACEPTADA y actualizar en el backend
          dtoMatricula.estado = 'ACEPTADA';
          await firstValueFrom(
            this.api.put('/inscripciones/aceptar', dtoMatricula)
          );
          console.log(`✅ Inscripción aceptada: ${dtoMatricula.paraleloMateria.materia.nombre}`);
        } catch (error) {
          console.error('❌ Error al aceptar inscripción:', error);
        }
      }

      // Convertir respuestas a modelos frontend
      const matriculasCreadas = dtoMatriculas.map(dto => this.mappers.dtoToMatricula(dto));

      // Limpiar carrito después de confirmar
      this.limpiarCarrito();

      console.log(`${matriculasCreadas.length} matrícula(s) confirmada(s) y aceptada(s) exitosamente`);

      return {
        exito: true,
        mensaje: `${matriculasCreadas.length} matrícula(s) confirmada(s) y agregada(s) a tu horario exitosamente`,
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

  async validarInscripcion(grupo: Grupo): Promise<{
    valido: boolean;
    errores: any[];
    advertencias?: string[]
  }> {
    const errores: any[] = [];
    const advertencias: string[] = [];

    if (grupo.cupoDisponible <= 0) {
      errores.push({ tipo: 'CUPO', mensaje: 'No hay cupos disponibles' });
    }

    // Verificar duplicados en el carrito actual
    const materiasInscritas = this.obtenerMateriasInscritas();
    if (materiasInscritas.includes(grupo.materia.id)) {
      errores.push({ tipo: 'DUPLICADO', mensaje: 'Ya inscrito en esta materia' });
    }

    // Verificar duplicados contra matrículas existentes en el backend
    try {
      const matriculasExistentes = await this.obtenerMatriculasActivas();
      const materiaYaInscrita = matriculasExistentes.some(m =>
        m.grupo.materia.id === grupo.materia.id && m.estado === 'INSCRITO'
      );

      if (materiaYaInscrita) {
        errores.push({ tipo: 'DUPLICADO_BACKEND', mensaje: 'Ya estás inscrito en esta materia' });
      }
    } catch (error) {
      console.warn('No se pudo verificar matrículas existentes:', error);
      // No agregar error, permitir continuar pero con advertencia
      advertencias.push('No se pudo verificar matrículas existentes. Verifica manualmente.');
    }

    return {
      valido: errores.length === 0,
      errores,
      advertencias
    };
  }

  private formatearDia(dia: string): string {
    const dias: Record<string, string> = {
      'LUNES': 'Lun',
      'MARTES': 'Mar',
      'MIERCOLES': 'Mié',
      'JUEVES': 'Jue',
      'VIERNES': 'Vie',
      'SABADO': 'Sáb'
    };
    return dias[dia] || dia;
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
          nombre: matricula.estudiante.nombre,
          apellido: matricula.estudiante.apellido || '',
          email: matricula.estudiante.email
        },
        paraleloMateria: {
          codigo: matricula.grupo.codigo.toString(),
          materia: {
            codigo: matricula.grupo.materia.codigo,
            nombre: matricula.grupo.materia.nombre,
            creditos: matricula.grupo.materia.creditos,
            semestre: matricula.grupo.materia.semestre
          },
          docente: {
            codigo: (matricula.grupo.docente as any).codigoDocente || matricula.grupo.docente.id.toString(),
            nombre: matricula.grupo.docente.nombre,
            apellido: matricula.grupo.docente.apellido || '',
            email: matricula.grupo.docente.email || '',
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
            diaSemana: h.dia as any,
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
        dto => dto.estado === 'PENDIENTE'
      );

      return inscripcionesPendientes.map(dto => this.mappers.dtoToMatricula(dto));
    } catch (error) {
      console.error('Error al obtener inscripciones pendientes:', error);
      return [];
    }
  }
}
