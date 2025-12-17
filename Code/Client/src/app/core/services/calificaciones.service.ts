/**
 * Servicio de Calificaciones
 * Gestiona notas, historial académico, alertas de reprobación, registro de notas (docentes)
 * 
 * Heurística Nielsen #1: Visibilidad del estado del sistema
 * - Muestra claramente el estado de las notas y promedios
 */

import { Injectable, signal, computed } from '@angular/core';
import { Calificacion, TipoEvaluacion, Matricula, AlertaReprobacion } from '../../models';
import { AuthService } from './auth.service';
import { DatosMockService } from './datos-mock.service';
import { ApiService } from './api.service';
import { MappersService } from './mappers.service';
import { DtoCalificacion, DtoInscripcion } from '../../models/backend-dtos';
import { firstValueFrom } from 'rxjs';

export interface NotaMateria {
  materiaId: number;
  matriculaId: number;
  materiaCodigo: string;
  materiaNombre: string;
  docente: string;
  evaluaciones: EvaluacionNota[];
  notaFinal: number;
  porcentajeEvaluado: number;
}

export interface EvaluacionNota {
  id: number;
  nombre: string;
  tipo: string;
  nota: number | null;
  porcentaje: number;
  fecha: Date | null;
}

export interface EstudianteNotas {
  estudianteId: number;
  estudianteNombre: string;
  estudianteCodigo: string;
  notas: { [key: string]: number | null };
  notaFinal: number;
  porcentajeEvaluado: number;
}

export interface GrupoDocente {
  grupoId: number;
  materiaNombre: string;
  grupoCodigo: string;
  cantidadEstudiantes: number;
}

@Injectable({
  providedIn: 'root'
})
export class CalificacionesService {
  private alertas = signal<AlertaReprobacion[]>([]);
  private usarBackend = signal<boolean>(true);

  readonly alertas$ = this.alertas.asReadonly();
  readonly alertasNoLeidas = computed(() => 
    this.alertas().filter(a => !a.leida).length
  );

  constructor(
    private authService: AuthService,
    private datosMock: DatosMockService,
    private api: ApiService,
    private mappers: MappersService
  ) {}

  // ============ MÉTODOS PARA ESTUDIANTES ============

  /**
   * Obtiene las notas del estudiante actual organizadas por materia
   * USANDO: GET /api/evaluaciones/estudiante/{codigo}
   */
  async obtenerMisNotas(): Promise<NotaMateria[]> {
    const userId = this.authService.usuarioId();
    const usuario = this.authService.usuario();
    const userCodigo = (usuario as any)?.codigoEstudiante || userId.toString();
    
    try {
      // Usar endpoint específico para obtener calificaciones del estudiante
      const dtoCalificaciones = await firstValueFrom(
        this.api.get<any[]>(`/evaluaciones/estudiante/${userCodigo}`)
      );
      
      console.log(`${dtoCalificaciones.length} calificaciones obtenidas del backend`);
      
      // Agrupar calificaciones por paralelo/materia
      const calificacionesPorParalelo = new Map<string, any[]>();
      dtoCalificaciones.forEach(calif => {
        const paraleloId = calif.evaluacion?.paralelo?.codigo || 'unknown';
        if (!calificacionesPorParalelo.has(paraleloId)) {
          calificacionesPorParalelo.set(paraleloId, []);
        }
        calificacionesPorParalelo.get(paraleloId)!.push(calif);
      });
      
      // Convertir a formato NotaMateria
      const notasMaterias: NotaMateria[] = [];
      calificacionesPorParalelo.forEach((califs, paraleloId) => {
        if (califs.length === 0) return;
        
        const primeraCalif = califs[0];
        const paralelo = primeraCalif.evaluacion?.paralelo;
        const materia = paralelo?.materia;
        
        if (!materia) return;
        
        // Calcular nota final y porcentaje evaluado
        let notaFinal = 0;
        let porcentajeEvaluado = 0;
        
        const evaluaciones: EvaluacionNota[] = califs.map(calif => {
          const evaluacion = calif.evaluacion;
          const nota = calif.nota;
          const porcentaje = evaluacion?.porcentaje || 0;
          
          if (nota !== null && nota !== undefined) {
            notaFinal += (nota * porcentaje / 100);
            porcentajeEvaluado += porcentaje;
          }
          
          return {
            id: parseInt(calif.codigo),
            nombre: evaluacion?.descripcion || 'Evaluación',
            tipo: evaluacion?.tipo || 'PARCIAL',
            nota,
            porcentaje,
            fecha: evaluacion?.fecha ? new Date(evaluacion.fecha) : null
          };
        });
        
        notasMaterias.push({
          materiaId: parseInt(materia.codigo),
          matriculaId: parseInt(paraleloId),
          materiaCodigo: materia.codigo,
          materiaNombre: materia.nombre,
          docente: paralelo?.docente?.nombre || 'Docente',
          evaluaciones,
          notaFinal: Math.round(notaFinal * 10) / 10,
          porcentajeEvaluado
        });
      });
      
      return notasMaterias;
      
    } catch (error) {
      console.error('Error al obtener calificaciones desde backend:', error);
      // Fallback a datos mock
      console.warn('Usando datos mock como fallback');
      return this.obtenerMisNotasMock();
    }
  }
  
  /**
   * Método fallback con datos mock (mantener por compatibilidad)
   */
  private obtenerMisNotasMock(): NotaMateria[] {
    const userId = this.authService.usuarioId();
    
    const matriculas = this.datosMock.obtenerMatriculasUsuario(userId)
      .filter(m => m.estado === 'INSCRITO');

    return matriculas.map(matricula => {
      const misCalificaciones = this.datosMock.obtenerCalificacionesUsuario(userId);
      const calificaciones = misCalificaciones.filter(
        c => c.matricula.id === matricula.id
      );

      const evaluaciones: EvaluacionNota[] = [
        { id: 1, nombre: 'Primer Parcial', tipo: 'PARCIAL', nota: null, porcentaje: 25, fecha: null },
        { id: 2, nombre: 'Segundo Parcial', tipo: 'PARCIAL', nota: null, porcentaje: 25, fecha: null },
        { id: 3, nombre: 'Proyecto', tipo: 'PROYECTO', nota: null, porcentaje: 30, fecha: null },
        { id: 4, nombre: 'Examen Final', tipo: 'FINAL', nota: null, porcentaje: 20, fecha: null }
      ];

      calificaciones.forEach(calif => {
        const evalIdx = evaluaciones.findIndex(e => 
          e.nombre.toLowerCase().includes(calif.nombre.toLowerCase().split(' ')[0])
        );
        if (evalIdx >= 0) {
          evaluaciones[evalIdx].nota = calif.nota;
          evaluaciones[evalIdx].fecha = calif.fecha;
        }
      });

      let notaFinal = 0;
      let porcentajeEvaluado = 0;
      evaluaciones.forEach(e => {
        if (e.nota !== null) {
          notaFinal += (e.nota * e.porcentaje / 100);
          porcentajeEvaluado += e.porcentaje;
        }
      });

      return {
        materiaId: matricula.grupo.materia.id,
        matriculaId: matricula.id,
        materiaCodigo: matricula.grupo.materia.codigo,
        materiaNombre: matricula.grupo.materia.nombre,
        docente: matricula.grupo.docente.nombre + ' ' + matricula.grupo.docente.apellido,
        evaluaciones,
        notaFinal: Math.round(notaFinal * 10) / 10,
        porcentajeEvaluado
      };
    });
  }

  /**
   * Calcula el promedio actual del estudiante
   */
  async calcularPromedioActual(): Promise<number> {
    const notas = await this.obtenerMisNotas();
    const notasConAvance = notas.filter(n => n.porcentajeEvaluado > 0);
    if (notasConAvance.length === 0) return 0;
    
    const suma = notasConAvance.reduce((acc, n) => acc + n.notaFinal, 0);
    return Math.round((suma / notasConAvance.length) * 10) / 10;
  }

  // ============ MÉTODOS PARA DOCENTES ============

  /**
   * Obtiene los grupos que el docente tiene asignados
   * Integrado con backend: GET /api/paralelos
   */
  async obtenerMisGrupos(): Promise<GrupoDocente[]> {
    const userId = this.authService.usuarioId();
    const usuario = this.authService.usuario();
    const docenteCodigo = (usuario as any)?.codigoDocente || userId.toString();
    
    try {
      // Obtener todos los paralelos desde el backend
      const dtoParalelos = await firstValueFrom(
        this.api.get<any[]>('/paralelos')
      );
      
      // Filtrar solo los paralelos del docente actual
      const misParalelos = dtoParalelos.filter(
        dto => dto.docente?.codigo === docenteCodigo
      );
      
      // Obtener inscripciones para contar estudiantes
      const dtoInscripciones = await firstValueFrom(
        this.api.get<any[]>('/inscripciones')
      );
      
      // Convertir a formato GrupoDocente
      return misParalelos.map(dto => {
        // Contar estudiantes inscritos en este paralelo
        const estudiantesInscritos = dtoInscripciones.filter(
          insc => insc.paralelo?.codigo === dto.codigo &&
                  insc.estado === 'ACEPTADA'
        ).length;
        
        return {
          grupoId: parseInt(dto.codigo),
          materiaNombre: dto.materia?.nombre || 'Materia',
          grupoCodigo: dto.nroParalelo?.toString() || '1',
          cantidadEstudiantes: estudiantesInscritos
        };
      });
      
    } catch (error) {
      console.error('Error al obtener grupos del docente desde backend:', error);
      // Fallback a datos mock
      return this.obtenerMisGruposMock();
    }
  }
  
  /**
   * Método fallback con datos mock
   */
  private obtenerMisGruposMock(): GrupoDocente[] {
    const userId = this.authService.usuarioId();
    return this.datosMock.grupos
      .filter(g => g.docente.id === userId)
      .map(grupo => ({
        grupoId: grupo.id,
        materiaNombre: grupo.materia.nombre,
        grupoCodigo: grupo.codigo,
        cantidadEstudiantes: this.datosMock.matriculas.filter(
          m => m.grupo.id === grupo.id && m.estado === 'INSCRITO'
        ).length
      }));
  }

  /**
   * Obtiene los estudiantes de un grupo con sus notas
   * Integrado con backend: GET /api/inscripciones y GET /api/evaluaciones
   */
  async obtenerEstudiantesGrupo(grupoId: number): Promise<EstudianteNotas[]> {
    try {
      // Obtener todas las inscripciones
      const dtoInscripciones = await firstValueFrom(
        this.api.get<any[]>('/inscripciones')
      );
      
      // Filtrar inscripciones de este grupo
      const inscripcionesGrupo = dtoInscripciones.filter(
        insc => parseInt(insc.paralelo?.codigo) === grupoId &&
                insc.estado === 'ACEPTADA'
      );
      
      // Obtener todas las calificaciones
      const dtoCalificaciones = await firstValueFrom(
        this.api.get<any[]>('/evaluaciones')
      );
      
      // Procesar cada estudiante
      return inscripcionesGrupo.map(inscripcion => {
        const estudiante = inscripcion.estudiante;
        
        // Filtrar calificaciones de este estudiante en este grupo
        const calificacionesEstudiante = dtoCalificaciones.filter(
          calif => calif.estudiante?.codigo === estudiante.codigo &&
                   calif.evaluacion?.paralelo?.codigo === grupoId.toString()
        );
        
        // Organizar notas por tipo
        const notas: { [key: string]: number | null } = {
          'Parcial 1': null,
          'Parcial 2': null,
          'Proyecto': null,
          'Examen Final': null
        };
        
        let notaFinal = 0;
        let porcentajeEvaluado = 0;
        
        calificacionesEstudiante.forEach(calif => {
          const tipo = calif.evaluacion?.tipo || '';
          const descripcion = calif.evaluacion?.descripcion?.toLowerCase() || '';
          
          if (descripcion.includes('primer') || tipo === 'PARCIAL_1') {
            notas['Parcial 1'] = calif.nota;
          } else if (descripcion.includes('segundo') || tipo === 'PARCIAL_2') {
            notas['Parcial 2'] = calif.nota;
          } else if (descripcion.includes('proyecto') || tipo === 'PROYECTO') {
            notas['Proyecto'] = calif.nota;
          } else if (descripcion.includes('final') || tipo === 'FINAL') {
            notas['Examen Final'] = calif.nota;
          }
          
          // Calcular nota ponderada
          const porcentaje = calif.evaluacion?.porcentaje || 0;
          if (calif.nota !== null && calif.nota !== undefined) {
            notaFinal += (calif.nota * porcentaje / 100);
            porcentajeEvaluado += porcentaje;
          }
        });
        
        return {
          estudianteId: parseInt(estudiante.codigo),
          estudianteNombre: estudiante.nombre,
          estudianteCodigo: estudiante.codigo,
          notas,
          notaFinal: Math.round(notaFinal * 10) / 10,
          porcentajeEvaluado
        };
      });
      
    } catch (error) {
      console.error('Error al obtener estudiantes del grupo desde backend:', error);
      // Fallback a datos mock
      return this.obtenerEstudiantesGrupoMock(grupoId);
    }
  }
  
  /**
   * Método fallback con datos mock
   */
  private obtenerEstudiantesGrupoMock(grupoId: number): EstudianteNotas[] {
    const matriculasGrupo = this.datosMock.matriculas.filter(
      m => m.grupo.id === grupoId && m.estado === 'INSCRITO'
    );

    return matriculasGrupo.map(matricula => {
      const calificaciones = this.datosMock.calificaciones.filter(
        c => c.matricula.id === matricula.id
      );

      const notas: { [key: string]: number | null } = {
        'Parcial 1': null,
        'Parcial 2': null,
        'Proyecto': null,
        'Examen Final': null
      };

      calificaciones.forEach(calif => {
        if (calif.nombre.toLowerCase().includes('primer')) notas['Parcial 1'] = calif.nota;
        else if (calif.nombre.toLowerCase().includes('segundo')) notas['Parcial 2'] = calif.nota;
        else if (calif.nombre.toLowerCase().includes('proyecto')) notas['Proyecto'] = calif.nota;
        else if (calif.nombre.toLowerCase().includes('final')) notas['Examen Final'] = calif.nota;
      });

      let notaFinal = 0;
      let porcentajeEvaluado = 0;
      if (notas['Parcial 1'] !== null) { notaFinal += notas['Parcial 1'] * 0.25; porcentajeEvaluado += 25; }
      if (notas['Parcial 2'] !== null) { notaFinal += notas['Parcial 2'] * 0.25; porcentajeEvaluado += 25; }
      if (notas['Proyecto'] !== null) { notaFinal += notas['Proyecto'] * 0.30; porcentajeEvaluado += 30; }
      if (notas['Examen Final'] !== null) { notaFinal += notas['Examen Final'] * 0.20; porcentajeEvaluado += 20; }

      return {
        estudianteId: matricula.estudiante.id,
        estudianteNombre: matricula.estudiante.nombre + ' ' + matricula.estudiante.apellido,
        estudianteCodigo: (matricula.estudiante as any).codigoEstudiante || 'EST-' + matricula.estudiante.id,
        notas,
        notaFinal: Math.round(notaFinal * 10) / 10,
        porcentajeEvaluado
      };
    });
  }

  /**
   * Guarda una calificación para un estudiante
   */
  async guardarNota(
    grupoId: number,
    estudianteId: number,
    tipoNota: string,
    nota: number | null
  ): Promise<{ exito: boolean; mensaje?: string; alerta?: string }> {
    if (nota !== null && (nota < 0 || nota > 100)) {
      return { exito: false, mensaje: 'La nota debe estar entre 0 y 100' };
    }

    if (this.usarBackend()) {
      return await this.guardarNotaBackend(grupoId, estudianteId, tipoNota, nota);
    } else {
      return this.guardarNotaMock(grupoId, estudianteId, tipoNota, nota);
    }
  }

  private async guardarNotaBackend(
    grupoId: number,
    estudianteId: number,
    tipoNota: string,
    nota: number | null
  ): Promise<{ exito: boolean; mensaje?: string; alerta?: string }> {
    try {
      const usuario = this.authService.usuario();
      if (!usuario) {
        return { exito: false, mensaje: 'Debe iniciar sesión' };
      }

      const dtoInscripciones = await firstValueFrom(
        this.api.get<DtoInscripcion[]>(`/inscripciones`, false)
      );

      const inscripcion = dtoInscripciones.find(i => 
        i.estudiante.codigo === estudianteId.toString() &&
        i.paralelo.codigo === grupoId.toString()
      );

      if (!inscripcion) {
        console.log('Fallback a mock: inscripción no encontrada');
        this.usarBackend.set(false);
        return this.guardarNotaMock(grupoId, estudianteId, tipoNota, nota);
      }

      let tipoEval = tipoNota;
      let porcentaje = 25;
      
      if (tipoNota.includes('Parcial')) {
        tipoEval = 'PARCIAL';
        porcentaje = 25;
      } else if (tipoNota.includes('Proyecto')) {
        tipoEval = 'PROYECTO';
        porcentaje = 30;
      } else if (tipoNota.includes('Final')) {
        tipoEval = 'FINAL';
        porcentaje = 20;
      }

      const dtoCalificacion: Partial<DtoCalificacion> = {
        estudiante: inscripcion.estudiante,
        evaluacion: {
          codigo: Date.now().toString(),
          tipo: tipoEval,
          descripcion: tipoNota,
          fecha: new Date().toISOString().split('T')[0],
          porcentaje: porcentaje,
          paralelo: inscripcion.paralelo
        },
        nota: nota || 0,
        observaciones: ''
      };

      await firstValueFrom(
        this.api.post<DtoCalificacion>('/calificaciones', dtoCalificacion)
      );

      return { exito: true, mensaje: 'Nota guardada correctamente en backend' };
    } catch (error) {
      console.error('Error guardando nota en backend:', error);
      console.log('Fallback a mock');
      this.usarBackend.set(false);
      return this.guardarNotaMock(grupoId, estudianteId, tipoNota, nota);
    }
  }

  private guardarNotaMock(
    grupoId: number,
    estudianteId: number,
    tipoNota: string,
    nota: number | null
  ): { exito: boolean; mensaje?: string; alerta?: string } {
    const matriculasEstudiante = this.datosMock.obtenerMatriculasUsuario(estudianteId);
    const matricula = matriculasEstudiante.find(
      m => m.grupo.id === grupoId
    );

    if (!matricula) {
      return { exito: false, mensaje: 'Estudiante no encontrado en este grupo' };
    }

    // Determinar nombre y tipo de evaluación
    let nombreEval = tipoNota; // Ya viene formateado como 'Parcial 1', 'Proyecto', etc.
    let tipoEval: TipoEvaluacion = 'PARCIAL';
    let porcentaje = 25;
    
    if (tipoNota.includes('Parcial')) {
      tipoEval = 'PARCIAL';
      porcentaje = 25;
    } else if (tipoNota.includes('Proyecto')) {
      tipoEval = 'PROYECTO';
      porcentaje = 30;
    } else if (tipoNota.includes('Final')) {
      tipoEval = 'FINAL';
      porcentaje = 20;
    }

    if (nota === null) {
      // Eliminar calificación si existe
      return { exito: true };
    }

    // Buscar si ya existe la calificación
    // [LOCK] Buscar en las calificaciones del estudiante específico
    // En Spring Boot: GET /api/estudiantes/{estudianteId}/calificaciones
    const calificacionesEstudiante = this.datosMock.obtenerCalificacionesUsuario(estudianteId);
    const califExistente = calificacionesEstudiante.find(
      c => c.matricula.id === matricula.id && c.nombre.toLowerCase().includes(nombreEval.toLowerCase().split(' ')[0])
    );

    if (califExistente) {
      this.datosMock.actualizarCalificacion(califExistente.id, nota);
    } else {
      const nuevaCalif: Calificacion = {
        id: Date.now(),
        matricula,
        tipoEvaluacion: tipoEval,
        nombre: nombreEval,
        nota,
        porcentaje,
        fecha: new Date()
      };
      this.datosMock.agregarCalificacion(nuevaCalif);
    }

    // Verificar si el estudiante está reprobando
    const alertaTexto = this.verificarAlertaReprobacion(matricula);

    return { exito: true, mensaje: 'Nota guardada correctamente', alerta: alertaTexto };
  }

  /**
   * Calcula la nota final de un estudiante
   */
  calcularNotaFinal(notas: { [key: string]: number | null }): number {
    let total = 0;
    let porcentaje = 0;

    if (notas['parcial1'] != null) { total += notas['parcial1'] * 0.25; porcentaje += 25; }
    if (notas['parcial2'] != null) { total += notas['parcial2'] * 0.25; porcentaje += 25; }
    if (notas['proyecto'] != null) { total += notas['proyecto'] * 0.30; porcentaje += 30; }
    if (notas['final'] != null) { total += notas['final'] * 0.20; porcentaje += 20; }

    return porcentaje > 0 ? Math.round((total / porcentaje) * 1000) / 10 : 0;
  }

  // ============ ALERTAS DE REPROBACIÓN ============

  private verificarAlertaReprobacion(matricula: Matricula): string | undefined {
    // [LOCK] Obtener solo las calificaciones del estudiante de esta matrícula
    // En Spring Boot: GET /api/estudiantes/{estudianteId}/calificaciones
    const calificacionesEstudiante = this.datosMock.obtenerCalificacionesUsuario(matricula.estudiante.id);
    const calificaciones = calificacionesEstudiante.filter(
      c => c.matricula.id === matricula.id
    );

    if (calificaciones.length === 0) return undefined;

    // Calcular nota actual
    let notaActual = 0;
    let porcentaje = 0;
    calificaciones.forEach(c => {
      notaActual += (c.nota * c.porcentaje / 100);
      porcentaje += c.porcentaje;
    });
    
    const notaProyectada = porcentaje > 0 ? (notaActual / porcentaje) * 100 : 0;

    // Si está reprobando (nota < 51) y tiene al menos 50% evaluado
    if (notaProyectada < 51 && porcentaje >= 50) {
      const yaExisteAlerta = this.alertas().some(
        a => a.estudiante.id === matricula.estudiante.id && 
             a.materia.id === matricula.grupo.materia.id
      );

      if (!yaExisteAlerta) {
        const nuevaAlerta: AlertaReprobacion = {
          id: Date.now(),
          estudiante: matricula.estudiante,
          materia: matricula.grupo.materia,
          gestion: matricula.gestion,
          notaActual: Math.round(notaProyectada * 10) / 10,
          fechaAlerta: new Date(),
          leida: false
        };
        this.alertas.update(list => [...list, nuevaAlerta]);
        
        return `Alerta: ${matricula.estudiante.nombre} está en riesgo de reprobar ${matricula.grupo.materia.nombre} (nota proyectada: ${Math.round(notaProyectada)})`;
      }
    }
    
    return undefined;
  }

  /**
   * Marca una alerta como leída
   */
  marcarAlertaLeida(alertaId: number): void {
    this.alertas.update(list =>
      list.map(a => a.id === alertaId ? { ...a, leida: true } : a)
    );
  }

  // ============ HISTORIAL ACADÉMICO ============

  /**
   * Obtiene el historial académico del estudiante actual
   * Formateado para el componente de historial
   */
  obtenerHistorialAcademico(): {
    gestionId: number;
    gestionNombre: string;
    gestionEstado: string;
    materiaId: number;
    materiaCodigo: string;
    materiaNombre: string;
    creditos: number;
    notaFinal: number;
    estado: string;
  }[] {
    const userId = this.authService.usuarioId();
    const historialRaw = this.datosMock.obtenerHistorialEstudiante(userId);
    
    const resultado: any[] = [];
    
    // Historial de gestiones pasadas
    if (historialRaw.gestiones) {
      historialRaw.gestiones.forEach((g: any) => {
        g.materias.forEach((m: any) => {
          resultado.push({
            gestionId: g.gestion.id,
            gestionNombre: g.gestion.nombre,
            gestionEstado: g.gestion.estado,
            materiaId: m.materia.id || 0,
            materiaCodigo: m.materia.codigo,
            materiaNombre: m.materia.nombre,
            creditos: m.materia.creditos,
            notaFinal: m.nota,
            estado: m.estado
          });
        });
      });
    }

    // Agregar matrículas actuales (en curso)
    // [LOCK] Obtener solo MIS matrículas (no todas)
    // En Spring Boot: GET /api/estudiantes/{userId}/matriculas
    const matriculasActuales = this.datosMock.obtenerMatriculasUsuario(userId)
      .filter(m => m.estado === 'INSCRITO');

    const gestionActual = this.datosMock.gestiones.find(g => g.estado === 'EN_CURSO');
    
    if (gestionActual) {
      // [LOCK] Obtener solo MIS calificaciones (no todas)
      // En Spring Boot: GET /api/estudiantes/{userId}/calificaciones
      const misCalificaciones = this.datosMock.obtenerCalificacionesUsuario(userId);
      
      matriculasActuales.forEach(m => {
        // Calcular nota actual
        const califs = misCalificaciones.filter(c => c.matricula.id === m.id);
        let notaActual = 0;
        califs.forEach(c => {
          notaActual += (c.nota * c.porcentaje / 100);
        });

        resultado.push({
          gestionId: gestionActual.id,
          gestionNombre: gestionActual.nombre,
          gestionEstado: 'EN_CURSO',
          materiaId: m.grupo.materia.id,
          materiaCodigo: m.grupo.materia.codigo,
          materiaNombre: m.grupo.materia.nombre,
          creditos: m.grupo.materia.creditos,
          notaFinal: Math.round(notaActual * 10) / 10,
          estado: 'EN_CURSO'
        });
      });
    }

    return resultado;
  }

  /**
   * Obtiene los IDs de las materias que el estudiante ya completó (aprobó)
   * Útil para validar que no se pueda inscribir en materias ya aprobadas
   */
  obtenerMateriasAprobadas(): number[] {
    const userId = this.authService.usuarioId();
    const historialRaw = this.datosMock.obtenerHistorialEstudiante(userId);
    
    const materiasAprobadas: number[] = [];
    
    if (historialRaw.gestiones) {
      historialRaw.gestiones.forEach((g: any) => {
        g.materias.forEach((m: any) => {
          // Considerar aprobada si la nota es >= 51
          if (m.nota >= 51 && m.materia.id) {
            materiasAprobadas.push(m.materia.id);
          }
        });
      });
    }

    return materiasAprobadas;
  }

  // ============================================================================
  // INTEGRACIÓN CON BACKEND - ACTAS E HISTORIAL
  // ============================================================================

  /**
   * Obtiene todas las actas de un estudiante desde el backend
   */
  async obtenerActasEstudiante(codigoEstudiante?: string): Promise<any[]> {
    try {
      const usuario = this.authService.usuario();
      const codigo = codigoEstudiante || (usuario as any)?.codigoEstudiante;
      
      if (!codigo) {
        console.warn('No se pudo obtener código de estudiante');
        return [];
      }

      const dtoActas = await firstValueFrom(
        this.api.get<any[]>('/actas')
      );
      
      // Filtrar por estudiante (client-side ya que backend no tiene query params)
      const misActas = dtoActas.filter(acta => 
        acta.estudiante?.codigo === codigo
      );
      
      console.log(`${misActas.length} actas obtenidas para estudiante ${codigo}`);
      return misActas;
    } catch (error) {
      console.error('Error al obtener actas del backend:', error);
      return [];
    }
  }

  /**
   * Obtiene las actas aprobadas de un estudiante desde el backend
   */
  async obtenerActasAprobadas(codigoEstudiante?: string): Promise<any[]> {
    try {
      const usuario = this.authService.usuario();
      const codigo = codigoEstudiante || (usuario as any)?.codigoEstudiante;
      
      if (!codigo) {
        console.warn('No se pudo obtener código de estudiante');
        return [];
      }

      const dtoActasAprobadas = await firstValueFrom(
        this.api.get<any[]>('/actas/aprobadas')
      );
      
      // Filtrar por estudiante
      const misActasAprobadas = dtoActasAprobadas.filter(acta => 
        acta.estudiante?.codigo === codigo
      );
      
      console.log(`${misActasAprobadas.length} actas aprobadas para estudiante ${codigo}`);
      return misActasAprobadas;
    } catch (error) {
      console.error('Error al obtener actas aprobadas del backend:', error);
      return [];
    }
  }

  /**
   * Obtiene las actas reprobadas de un estudiante desde el backend
   */
  async obtenerActasReprobadas(codigoEstudiante?: string): Promise<any[]> {
    try {
      const usuario = this.authService.usuario();
      const codigo = codigoEstudiante || (usuario as any)?.codigoEstudiante;
      
      if (!codigo) {
        console.warn('No se pudo obtener código de estudiante');
        return [];
      }

      const dtoActasReprobadas = await firstValueFrom(
        this.api.get<any[]>('/actas/reprobadas')
      );
      
      // Filtrar por estudiante
      const misActasReprobadas = dtoActasReprobadas.filter(acta => 
        acta.estudiante?.codigo === codigo
      );
      
      console.log(`${misActasReprobadas.length} actas reprobadas para estudiante ${codigo}`);
      return misActasReprobadas;
    } catch (error) {
      console.error('Error al obtener actas reprobadas del backend:', error);
      return [];
    }
  }

  /**
   * Obtiene el historial académico completo desde el backend
   */
  async obtenerHistorialAcademicoBackend(codigoEstudiante?: string): Promise<any[]> {
    try {
      const usuario = this.authService.usuario();
      const codigo = codigoEstudiante || (usuario as any)?.codigoEstudiante;
      
      if (!codigo) {
        console.warn('No se pudo obtener código de estudiante');
        return this.obtenerHistorialAcademicoMock();
      }

      const dtoHistorial = await firstValueFrom(
        this.api.get<any[]>('/historial')
      );
      
      // Filtrar por estudiante
      const miHistorial = dtoHistorial.filter(h => 
        h.codigoEstudiante === codigo
      );
      
      // Usar mapper para convertir a formato frontend
      const historialFormateado = this.mappers.dtoToHistorial(miHistorial);
      
      console.log(`Historial académico obtenido para estudiante ${codigo}`);
      return historialFormateado;
    } catch (error) {
      console.error('Error al obtener historial del backend:', error);
      return this.obtenerHistorialAcademicoMock();
    }
  }

  /**
   * Obtiene historial académico mock como fallback
   */
  private obtenerHistorialAcademicoMock(): any[] {
    const userId = this.authService.usuarioId();
    const historialRaw = this.datosMock.obtenerHistorialEstudiante(userId);
    
    if (!historialRaw.gestiones) return [];
    
    return historialRaw.gestiones.map((g: any) => ({
      gestion: g.gestion,
      materias: g.materias.map((m: any) => ({
        materia: m.materia,
        nota: m.nota,
        estado: m.estado,
        creditos: m.materia.creditos
      })),
      promedioGestion: g.promedio || 0,
      creditosAprobados: g.materias.filter((m: any) => m.nota >= 51)
        .reduce((sum: number, m: any) => sum + m.materia.creditos, 0)
    }));
  }

  /**
   * Crea un acta de estudiante en el backend
   */
  async crearActa(estudianteCodigo: string, paraleloId: string): Promise<void> {
    try {
      const dtoActa = {
        estudiante: { codigo: estudianteCodigo },
        paraleloMateria: { codigo: paraleloId }
      };
      
      await firstValueFrom(
        this.api.post('/actas', dtoActa)
      );
      
      console.log('Acta creada exitosamente');
    } catch (error) {
      console.error('Error al crear acta:', error);
      throw error;
    }
  }

  /**
   * Crea una nueva evaluación para un paralelo
   */
  async crearEvaluacion(paraleloId: number, nombre: string, porcentaje: number): Promise<void> {
    try {
      const dtoEvaluacion = {
        paraleloMateria: { codigo: paraleloId.toString() },
        nombre: nombre,
        porcentaje: porcentaje
      };
      
      await firstValueFrom(
        this.api.post('/evaluaciones', dtoEvaluacion)
      );
      
      console.log(`Evaluación "${nombre}" creada (${porcentaje}%)`);
    } catch (error) {
      console.error('Error al crear evaluación:', error);
      throw error;
    }
  }

  /**
   * Elimina una evaluación de un paralelo
   */
  async eliminarEvaluacion(paraleloId: number): Promise<void> {
    try {
      await firstValueFrom(
        this.api.delete('/evaluaciones', { 
          paraleloMateria: { codigo: paraleloId.toString() } 
        })
      );
      
      console.log('Evaluación eliminada');
    } catch (error) {
      console.error('Error al eliminar evaluación:', error);
      throw error;
    }
  }

  // ============ NUEVOS MÉTODOS CON ENDPOINTS ESPECÍFICOS ============

  /**
   * Obtiene las evaluaciones de un paralelo específico
   * USANDO: GET /api/evaluaciones/paralelo/{codigo}
   */
  async obtenerEvaluacionesParalelo(codigoParalelo: string): Promise<any[]> {
    try {
      const dtoEvaluaciones = await firstValueFrom(
        this.api.get<any[]>(`/evaluaciones/paralelo/${codigoParalelo}`)
      );
      
      console.log(`${dtoEvaluaciones.length} evaluaciones obtenidas para paralelo ${codigoParalelo}`);
      return dtoEvaluaciones;
    } catch (error) {
      console.error(`Error al obtener evaluaciones del paralelo ${codigoParalelo}:`, error);
      return [];
    }
  }

  /**
   * Registra una calificación para una evaluación
   * USANDO: POST /api/evaluaciones/calificacion
   */
  async registrarCalificacion(
    codigoEvaluacion: string,
    codigoEstudiante: string,
    nota: number,
    evaluacionCompleta: any
  ): Promise<boolean> {
    try {
      const dtoCalificacion = {
        codigo: `${codigoEvaluacion}-${codigoEstudiante}`,
        nota: nota,
        estudiante: {
          codigo: codigoEstudiante
        },
        evaluacion: evaluacionCompleta
      };
      
      await firstValueFrom(
        this.api.post('/evaluaciones/calificacion', dtoCalificacion)
      );
      
      console.log(`Calificación registrada: Estudiante ${codigoEstudiante} - Nota: ${nota}`);
      return true;
    } catch (error) {
      console.error('Error al registrar calificación:', error);
      return false;
    }
  }

  /**
   * Obtiene todas las calificaciones de un estudiante
   * USANDO: GET /api/evaluaciones/estudiante/{codigo}
   * (Ya se usa en obtenerMisNotas, este es un wrapper más directo)
   */
  async obtenerCalificacionesEstudiante(codigoEstudiante: string): Promise<any[]> {
    try {
      const dtoCalificaciones = await firstValueFrom(
        this.api.get<any[]>(`/evaluaciones/estudiante/${codigoEstudiante}`)
      );
      
      console.log(`${dtoCalificaciones.length} calificaciones obtenidas para estudiante ${codigoEstudiante}`);
      return dtoCalificaciones;
    } catch (error) {
      console.error(`Error al obtener calificaciones del estudiante ${codigoEstudiante}:`, error);
      return [];
    }
  }
}
