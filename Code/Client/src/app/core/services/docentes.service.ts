import { Injectable, signal } from '@angular/core';
import { ApiService } from './api.service';
import { MappersService } from './mappers.service';
import { firstValueFrom } from 'rxjs';
import { DtoDocente } from '../../models/backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class DocentesService {
  private _docentes = signal<any[]>([]);
  docentes = this._docentes.asReadonly();

  constructor(
    private api: ApiService,
    private mappers: MappersService
  ) {}

  /**
   * Obtiene todos los docentes del backend
   */
  async obtenerDocentes(): Promise<any[]> {
    try {
      const dtoDocentes = await firstValueFrom(
        this.api.get<DtoDocente[]>('/docentes')
      );
      const docentes = dtoDocentes.map(dto => this.mappers.dtoToDocente(dto));
      this._docentes.set(docentes);
      return docentes;
    } catch (error) {
      console.error('Error al obtener docentes del backend:', error);
      return [];
    }
  }

  /**
   * Crea un nuevo docente en el backend
   */
  async crearDocente(docente: any): Promise<any> {
    try {
      const dtoDocente = this.mappers.docenteToDto(docente);
      const creado = await firstValueFrom(
        this.api.post<DtoDocente>('/docentes', dtoDocente)
      );
      const docenteCreado = this.mappers.dtoToDocente(creado);
      
      // Actualizar signal
      this._docentes.update(docentes => [...docentes, docenteCreado]);
      
      return docenteCreado;
    } catch (error) {
      console.error('Error al crear docente:', error);
      throw error;
    }
  }

  /**
   * Elimina un docente del backend
   */
  async eliminarDocente(docente: any): Promise<void> {
    try {
      const dtoDocente = this.mappers.docenteToDto(docente);
      await firstValueFrom(
        this.api.delete('/docentes', dtoDocente)
      );
      
      // Actualizar signal
      this._docentes.update(docentes => 
        docentes.filter(d => d.codigoDocente !== docente.codigoDocente)
      );
    } catch (error) {
      console.error('Error al eliminar docente:', error);
      throw error;
    }
  }

  /**
   * Busca un docente por su código
   */
  obtenerDocentePorCodigo(codigo: string): any | undefined {
    return this._docentes().find(d => d.codigoDocente === codigo);
  }

  /**
   * Busca docentes por especialidad
   */
  obtenerDocentesPorEspecialidad(especialidad: string): any[] {
    return this._docentes().filter(d => 
      d.especialidad?.toLowerCase() === especialidad.toLowerCase()
    );
  }

  /**
   * Busca docentes por nombre (búsqueda parcial)
   */
  buscarDocentesPorNombre(nombre: string): any[] {
    const nombreLower = nombre.toLowerCase();
    return this._docentes().filter(d => 
      d.nombre.toLowerCase().includes(nombreLower) ||
      d.apellido.toLowerCase().includes(nombreLower)
    );
  }

  // ============ NUEVOS MÉTODOS CON ENDPOINTS ESPECÍFICOS ============

  /**
   * Obtiene solo los docentes activos
   * USANDO: GET /api/docentes/activos
   */
  async obtenerDocentesActivos(): Promise<any[]> {
    try {
      const dtoDocentes = await firstValueFrom(
        this.api.get<DtoDocente[]>('/docentes/activos')
      );
      const docentes = dtoDocentes.map(dto => this.mappers.dtoToDocente(dto));
      console.log(`${docentes.length} docentes activos obtenidos del backend`);
      return docentes;
    } catch (error) {
      console.error('Error al obtener docentes activos:', error);
      return [];
    }
  }

  /**
   * Obtiene un docente específico por código desde el backend
   * USANDO: GET /api/docentes/{codigo}
   */
  async obtenerDocenteBackend(codigo: string): Promise<any | null> {
    try {
      const dtoDocente = await firstValueFrom(
        this.api.get<DtoDocente>(`/docentes/${codigo}`)
      );
      const docente = this.mappers.dtoToDocente(dtoDocente);
      console.log(`Docente ${codigo} obtenido del backend`);
      return docente;
    } catch (error) {
      console.error(`Error al obtener docente ${codigo}:`, error);
      return null;
    }
  }
}
