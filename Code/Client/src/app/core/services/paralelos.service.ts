import { Injectable, signal } from '@angular/core';
import { ApiService } from './api.service';
import { MappersService } from './mappers.service';
import { firstValueFrom } from 'rxjs';
import { DtoParaleloMateria } from '../../models/backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class ParalelosService {
  private _paralelos = signal<any[]>([]);
  paralelos = this._paralelos.asReadonly();

  constructor(
    private api: ApiService,
    private mappers: MappersService
  ) {}

  async obtenerParalelos(): Promise<any[]> {
    try {
      const dtoParalelos = await firstValueFrom(
        this.api.get<DtoParaleloMateria[]>('/paralelos')
      );
      const gestionActual = { id: 1, nombre: 'II-2025', anio: 2025, periodo: 2 };
      const paralelos = dtoParalelos.map(dto => this.mappers.dtoToGrupo(dto, gestionActual));
      this._paralelos.set(paralelos);
      return paralelos;
    } catch (error) {
      console.error('Error al obtener paralelos:', error);
      return [];
    }
  }

  async crearParalelo(paralelo: any): Promise<any> {
    try {
      const dtoParalelo: DtoParaleloMateria = {
        codigo: paralelo.codigo,
        nroParalelo: parseInt(paralelo.codigo),
        turno: paralelo.turno || 'MANANA',
        materia: {
          codigo: paralelo.materia.codigo,
          nombre: paralelo.materia.nombre,
          creditos: paralelo.materia.creditos,
          semestre: paralelo.materia.semestre
        },
        docente: {
          codigo: paralelo.docente.codigoDocente || paralelo.docente.codigo,
          nombre: paralelo.docente.nombre,
          especialidad: paralelo.docente.especialidad || 'General'
        },
        aula: {
          codigo: paralelo.aula.codigo,
          edificio: paralelo.aula.edificio,
          capacidad: paralelo.aula.capacidad,
          disponible: paralelo.aula.disponible !== false
        },
        cupoMaximo: paralelo.cupoMaximo,
        horarios: paralelo.horarios || []
      };

      const creado = await firstValueFrom(
        this.api.post<DtoParaleloMateria>('/paralelos', dtoParalelo)
      );
      
      const gestionActual = { id: 1, nombre: 'II-2025', anio: 2025, periodo: 2 };
      const paraleloCreado = this.mappers.dtoToGrupo(creado, gestionActual);
      
      this._paralelos.update(paralelos => [...paralelos, paraleloCreado]);
      
      console.log('Paralelo creado:', paraleloCreado.codigo);
      return paraleloCreado;
    } catch (error) {
      console.error('Error al crear paralelo:', error);
      throw error;
    }
  }

  async eliminarParalelo(codigoParalelo: string): Promise<void> {
    try {
      await firstValueFrom(
        this.api.delete('/paralelos', { codigo: codigoParalelo })
      );
      
      this._paralelos.update(paralelos => 
        paralelos.filter(p => p.codigo !== codigoParalelo)
      );
      
      console.log('Paralelo eliminado:', codigoParalelo);
    } catch (error) {
      console.error('Error al eliminar paralelo:', error);
      throw error;
    }
  }

  obtenerParaleloPorCodigo(codigo: string): any | undefined {
    return this._paralelos().find(p => p.codigo === codigo);
  }

  obtenerParalelosPorMateria(materiaId: number): any[] {
    return this._paralelos().filter(p => p.materia.id === materiaId);
  }

  obtenerParalelosPorDocente(docenteId: number): any[] {
    return this._paralelos().filter(p => p.docente.id === docenteId);
  }

  // ============ NUEVOS MÉTODOS CON ENDPOINTS ESPECÍFICOS ============

  /**
   * Obtiene un paralelo específico desde el backend
   * USANDO: GET /api/paralelos/{codigo}
   */
  async obtenerParaleloBackend(codigo: string): Promise<any | null> {
    try {
      const dtoParalelo = await firstValueFrom(
        this.api.get<DtoParaleloMateria>(`/paralelos/${codigo}`)
      );
      const gestionActual = { id: 1, nombre: 'II-2025', anio: 2025, periodo: 2 };
      const paralelo = this.mappers.dtoToGrupo(dtoParalelo, gestionActual);
      console.log(`Paralelo ${codigo} obtenido del backend`);
      return paralelo;
    } catch (error) {
      console.error(`Error al obtener paralelo ${codigo}:`, error);
      return null;
    }
  }

  /**
   * Obtiene todos los paralelos de un docente desde el backend
   * USANDO: GET /api/paralelos/docente/{codigo}
   */
  async obtenerParalelosPorDocenteBackend(codigoDocente: string): Promise<any[]> {
    try {
      const dtoParalelos = await firstValueFrom(
        this.api.get<DtoParaleloMateria[]>(`/paralelos/docente/${codigoDocente}`)
      );
      const gestionActual = { id: 1, nombre: 'II-2025', anio: 2025, periodo: 2 };
      const paralelos = dtoParalelos.map(dto => this.mappers.dtoToGrupo(dto, gestionActual));
      console.log(`${paralelos.length} paralelos obtenidos para docente ${codigoDocente}`);
      return paralelos;
    } catch (error) {
      console.error(`Error al obtener paralelos del docente ${codigoDocente}:`, error);
      return [];
    }
  }

  /**
   * Obtiene todos los paralelos de una materia desde el backend
   * USANDO: GET /api/paralelos/materia/{codigo}
   */
  async obtenerParalelosPorMateriaBackend(codigoMateria: string): Promise<any[]> {
    try {
      const dtoParalelos = await firstValueFrom(
        this.api.get<DtoParaleloMateria[]>(`/paralelos/materia/${codigoMateria}`)
      );
      const gestionActual = { id: 1, nombre: 'II-2025', anio: 2025, periodo: 2 };
      const paralelos = dtoParalelos.map(dto => this.mappers.dtoToGrupo(dto, gestionActual));
      console.log(`${paralelos.length} paralelos obtenidos para materia ${codigoMateria}`);
      return paralelos;
    } catch (error) {
      console.error(`Error al obtener paralelos de la materia ${codigoMateria}:`, error);
      return [];
    }
  }

  /**
   * Actualiza un paralelo existente
   * USANDO: PUT /api/paralelos/{codigo}
   */
  async actualizarParalelo(codigo: string, paralelo: any): Promise<boolean> {
    try {
      const dtoParalelo: DtoParaleloMateria = {
        codigo: paralelo.codigo,
        nroParalelo: parseInt(paralelo.codigo),
        turno: paralelo.turno || 'MANANA',
        materia: {
          codigo: paralelo.materia.codigo,
          nombre: paralelo.materia.nombre,
          creditos: paralelo.materia.creditos,
          semestre: paralelo.materia.semestre
        },
        docente: {
          codigo: paralelo.docente.codigoDocente || paralelo.docente.codigo,
          nombre: paralelo.docente.nombre,
          especialidad: paralelo.docente.especialidad || 'General'
        },
        aula: {
          codigo: paralelo.aula.codigo,
          edificio: paralelo.aula.edificio,
          capacidad: paralelo.aula.capacidad,
          disponible: paralelo.aula.disponible !== false
        },
        cupoMaximo: paralelo.cupoMaximo,
        horarios: paralelo.horarios || []
      };

      await firstValueFrom(
        this.api.put(`/paralelos/${codigo}`, dtoParalelo)
      );
      
      // Actualizar en el signal local
      this._paralelos.update(paralelos => 
        paralelos.map(p => p.codigo === codigo ? paralelo : p)
      );
      
      console.log(`Paralelo ${codigo} actualizado exitosamente`);
      return true;
    } catch (error) {
      console.error(`Error al actualizar paralelo ${codigo}:`, error);
      return false;
    }
  }
}
