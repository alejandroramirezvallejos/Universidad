import { Injectable, signal } from '@angular/core';
import { ApiService } from './api.service';
import { MappersService } from './mappers.service';
import { firstValueFrom } from 'rxjs';
import { DtoCarrera } from '../../models/backend-dtos';
import { Carrera } from '../../models';

@Injectable({
  providedIn: 'root'
})
export class CarrerasService {
  private _carreras = signal<Carrera[]>([]);
  carreras = this._carreras.asReadonly();

  constructor(
    private api: ApiService,
    private mappers: MappersService
  ) {}

  /**
   * Obtiene todas las carreras del backend
   */
  async obtenerCarreras(): Promise<Carrera[]> {
    try {
      const dtoCarreras = await firstValueFrom(
        this.api.get<DtoCarrera[]>('/carreras')
      );
      const carreras = dtoCarreras.map(dto => this.mappers.dtoToCarrera(dto));
      this._carreras.set(carreras);
      return carreras;
    } catch (error) {
      console.error('❌ Error al obtener carreras del backend:', error);
      // Fallback: devolver lista vacía
      return [];
    }
  }

  /**
   * Crea una nueva carrera en el backend
   */
  async crearCarrera(carrera: Carrera): Promise<Carrera> {
    try {
      const dtoCarrera = this.mappers.carreraToDto(carrera);
      const creada = await firstValueFrom(
        this.api.post<DtoCarrera>('/carreras', dtoCarrera)
      );
      const carreraCreada = this.mappers.dtoToCarrera(creada);
      
      // Actualizar signal
      this._carreras.update(carreras => [...carreras, carreraCreada]);
      
      return carreraCreada;
    } catch (error) {
      console.error('❌ Error al crear carrera:', error);
      throw error;
    }
  }

  /**
   * Elimina una carrera del backend
   */
  async eliminarCarrera(codigoCarrera: string): Promise<void> {
    try {
      await firstValueFrom(
        this.api.delete('/carreras', { codigo: codigoCarrera })
      );
      
      // Actualizar signal
      this._carreras.update(carreras => 
        carreras.filter(c => c.codigo !== codigoCarrera)
      );
    } catch (error) {
      console.error('❌ Error al eliminar carrera:', error);
      throw error;
    }
  }

  /**
   * Busca una carrera por su código
   */
  obtenerCarreraPorCodigo(codigo: string): Carrera | undefined {
    return this._carreras().find(c => c.codigo === codigo);
  }

  /**
   * Busca una carrera por su ID
   */
  obtenerCarreraPorId(id: number): Carrera | undefined {
    return this._carreras().find(c => c.id === id);
  }
}
