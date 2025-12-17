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
   * @param estudiante - DtoEstudiante o modelo interno
   */
  async crearEstudiante(estudiante: any): Promise<any> {
    try {
      // Si ya es un DtoEstudiante (tiene 'codigo'), enviarlo directamente
      // Si es modelo interno (tiene 'codigoEstudiante'), mapearlo primero
      const dtoEstudiante = estudiante.codigo && !estudiante.codigoEstudiante
        ? estudiante  // Ya es DTO, enviar tal cual
        : this.mappers.estudianteToDto(estudiante);  // Es modelo interno, mapear
      
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
   * @param estudiante - DtoEstudiante con todos los datos necesarios
   */
  async eliminarEstudiante(estudiante: DtoEstudiante): Promise<void> {
    try {
      console.log('🗑️ Eliminando estudiante:', estudiante);
      
      // Si el estudiante ya es un DTO (tiene 'codigo'), enviarlo directamente
      // Si no, mapearlo primero
      const dtoEstudiante = estudiante.codigo 
        ? estudiante 
        : this.mappers.estudianteToDto(estudiante);
      
      await firstValueFrom(
        this.api.delete('/estudiantes', dtoEstudiante)
      );
      
      // Actualizar signal - buscar por 'codigo' o 'codigoEstudiante'
      this._estudiantes.update(estudiantes => 
        estudiantes.filter(e => 
          e.codigoEstudiante !== dtoEstudiante.codigo && 
          e.codigo !== dtoEstudiante.codigo
        )
      );
      
      console.log('✅ Estudiante eliminado correctamente');
    } catch (error) {
      console.error('❌ Error al eliminar estudiante:', error);
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
