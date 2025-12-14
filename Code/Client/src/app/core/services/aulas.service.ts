import { Injectable, signal } from '@angular/core';
import { ApiService } from './api.service';
import { MappersService } from './mappers.service';
import { DatosMockService } from './datos-mock.service';
import { firstValueFrom } from 'rxjs';
import { DtoAula } from '../../models/backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class AulasService {
  private _aulas = signal<any[]>([]);
  aulas = this._aulas.asReadonly();

  constructor(
    private api: ApiService,
    private mappers: MappersService,
    private datosMock: DatosMockService
  ) {}

  /**
   * Obtiene todas las aulas del backend
   */
  async obtenerAulas(): Promise<any[]> {
    try {
      const dtoAulas = await firstValueFrom(
        this.api.get<DtoAula[]>('/aulas')
      );
      const aulas = dtoAulas.map(dto => this.mappers.dtoToAula(dto));
      this._aulas.set(aulas);
      return aulas;
    } catch (error) {
      console.error('❌ Error al obtener aulas del backend:', error);
      // Fallback a datos mock
      const aulasMock = this.datosMock.aulas;
      this._aulas.set(aulasMock);
      return aulasMock;
    }
  }

  /**
   * Crea una nueva aula en el backend
   */
  async crearAula(aula: any): Promise<any> {
    try {
      const dtoAula = this.mappers.aulaToDto(aula);
      const creada = await firstValueFrom(
        this.api.post<DtoAula>('/aulas', dtoAula)
      );
      const aulaCreada = this.mappers.dtoToAula(creada);
      
      // Actualizar signal
      this._aulas.update(aulas => [...aulas, aulaCreada]);
      
      return aulaCreada;
    } catch (error) {
      console.error('❌ Error al crear aula:', error);
      throw error;
    }
  }

  /**
   * Elimina un aula del backend
   */
  async eliminarAula(aula: any): Promise<void> {
    try {
      const dtoAula = this.mappers.aulaToDto(aula);
      await firstValueFrom(
        this.api.delete('/aulas', dtoAula)
      );
      
      // Actualizar signal
      this._aulas.update(aulas => 
        aulas.filter(a => a.codigo !== aula.codigo)
      );
    } catch (error) {
      console.error('❌ Error al eliminar aula:', error);
      throw error;
    }
  }

  /**
   * Busca un aula por su código
   */
  obtenerAulaPorCodigo(codigo: string): any | undefined {
    return this._aulas().find(a => a.codigo === codigo);
  }

  /**
   * Obtiene aulas disponibles
   */
  obtenerAulasDisponibles(): any[] {
    return this._aulas().filter(a => a.disponible !== false);
  }

  /**
   * Obtiene aulas por edificio
   */
  obtenerAulasPorEdificio(edificio: string): any[] {
    return this._aulas().filter(a => 
      a.edificio?.toLowerCase() === edificio.toLowerCase()
    );
  }

  /**
   * Obtiene aulas con capacidad mínima
   */
  obtenerAulasConCapacidad(capacidadMinima: number): any[] {
    return this._aulas().filter(a => a.capacidad >= capacidadMinima);
  }
}
