import { Injectable, signal } from '@angular/core';
import { ApiService } from './api.service';
import { MappersService } from './mappers.service';
import { firstValueFrom } from 'rxjs';
import { DtoEstudiante } from '../../models/backend-dtos';

@Injectable({
  providedIn: 'root'
})
export class EstudiantesService {
  private _estudiantes = signal<any[]>([]);
  estudiantes = this._estudiantes.asReadonly();

  constructor(
    private api: ApiService,
    private mappers: MappersService
  ) {}

  /**
   * Obtiene todos los estudiantes del backend
   */
  async obtenerEstudiantes(): Promise<any[]> {
    try {
      const dtoEstudiantes = await firstValueFrom(
        this.api.get<DtoEstudiante[]>('/estudiantes')
      );
      const estudiantes = dtoEstudiantes.map(dto => this.mappers.dtoToEstudiante(dto));
      this._estudiantes.set(estudiantes);
      return estudiantes;
    } catch (error) {
      console.error('Error al obtener estudiantes del backend:', error);
      return [];
    }
  }

  /**
   * Crea un nuevo estudiante en el backend
   */
  async crearEstudiante(estudiante: any): Promise<any> {
    try {
      const dtoEstudiante = this.mappers.estudianteToDto(estudiante);
      console.log('🔍 Datos del estudiante a enviar:', dtoEstudiante);
      console.log('🔍 Carrera:', dtoEstudiante.carrera);
      
      const creado = await firstValueFrom(
        this.api.post<DtoEstudiante>('/estudiantes', dtoEstudiante)
      );
      const estudianteCreado = this.mappers.dtoToEstudiante(creado);
      
      // Actualizar signal
      this._estudiantes.update(estudiantes => [...estudiantes, estudianteCreado]);
      
      return estudianteCreado;
    } catch (error) {
      console.error('Error al crear estudiante:', error);
      throw error;
    }
  }

  /**
   * Elimina un estudiante del backend
   */
  async eliminarEstudiante(estudiante: any): Promise<void> {
    try {
      const dtoEstudiante = this.mappers.estudianteToDto(estudiante);
      await firstValueFrom(
        this.api.delete('/estudiantes', dtoEstudiante)
      );
      
      // Actualizar signal
      this._estudiantes.update(estudiantes => 
        estudiantes.filter(e => e.codigoEstudiante !== estudiante.codigoEstudiante)
      );
    } catch (error) {
      console.error('Error al eliminar estudiante:', error);
      throw error;
    }
  }

  /**
   * Busca un estudiante por su código
   */
  obtenerEstudiantePorCodigo(codigo: string): any | undefined {
    return this._estudiantes().find(e => e.codigoEstudiante === codigo);
  }

  /**
   * Busca estudiantes por nombre (búsqueda parcial)
   */
  buscarEstudiantesPorNombre(nombre: string): any[] {
    const nombreLower = nombre.toLowerCase();
    return this._estudiantes().filter(e => 
      e.nombre.toLowerCase().includes(nombreLower) ||
      e.apellido.toLowerCase().includes(nombreLower)
    );
  }
}
