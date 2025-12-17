import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { firstValueFrom } from 'rxjs';

export interface DtoMateria {
  codigo: string;
  nombre: string;
  creditos: number;
  nivel: number;
  activa?: boolean;
  carrera?: any;
  materiasCorrelativas?: any[];
}

/**
 * Servicio de Materias
 * Gestiona el CRUD completo de materias usando los nuevos endpoints del backend
 */
@Injectable({
  providedIn: 'root'
})
export class MateriasService {
  constructor(private api: ApiService) {}

  /**
   * Obtiene todas las materias
   * USANDO: GET /api/materias
   */
  async obtenerTodasLasMaterias(): Promise<DtoMateria[]> {
    try {
      const materias = await firstValueFrom(
        this.api.get<DtoMateria[]>('/materias')
      );
      console.log(`${materias.length} materias obtenidas del backend`);
      return materias;
    } catch (error) {
      console.error('Error al obtener materias:', error);
      return [];
    }
  }

  /**
   * Obtiene una materia específica por código
   * USANDO: GET /api/materias/{codigo}
   */
  async obtenerMateriaPorCodigo(codigo: string): Promise<DtoMateria | null> {
    try {
      const materia = await firstValueFrom(
        this.api.get<DtoMateria>(`/materias/${codigo}`)
      );
      console.log(`Materia ${codigo} obtenida del backend`);
      return materia;
    } catch (error) {
      console.error(`Error al obtener materia ${codigo}:`, error);
      return null;
    }
  }

  /**
   * Crea una nueva materia
   * USANDO: POST /api/materias
   */
  async crearMateria(materia: DtoMateria): Promise<DtoMateria | null> {
    try {
      const materiaCreada = await firstValueFrom(
        this.api.post<DtoMateria>('/materias', materia)
      );
      console.log(`Materia ${materia.codigo} creada exitosamente`);
      return materiaCreada;
    } catch (error) {
      console.error('Error al crear materia:', error);
      return null;
    }
  }

  /**
   * Actualiza una materia existente
   * USANDO: PUT /api/materias/{codigo}
   */
  async actualizarMateria(codigo: string, materia: DtoMateria): Promise<boolean> {
    try {
      await firstValueFrom(
        this.api.put(`/materias/${codigo}`, materia)
      );
      console.log(`Materia ${codigo} actualizada exitosamente`);
      return true;
    } catch (error) {
      console.error(`Error al actualizar materia ${codigo}:`, error);
      return false;
    }
  }

  /**
   * Cambia el estado activo/inactivo de una materia
   * USANDO: PATCH /api/materias/{codigo}/estado
   */
  async toggleEstadoMateria(codigo: string): Promise<DtoMateria | null> {
    try {
      const materiaActualizada = await firstValueFrom(
        this.api.put<DtoMateria>(`/materias/${codigo}/estado`, {})
      );
      console.log(`Estado de materia ${codigo} cambiado a ${materiaActualizada.activa ? 'ACTIVA' : 'INACTIVA'}`);
      return materiaActualizada;
    } catch (error) {
      console.error(`Error al cambiar estado de materia ${codigo}:`, error);
      return null;
    }
  }

  /**
   * Filtra materias activas
   */
  async obtenerMateriasActivas(): Promise<DtoMateria[]> {
    const todasLasMaterias = await this.obtenerTodasLasMaterias();
    return todasLasMaterias.filter(m => m.activa !== false);
  }

  /**
   * Filtra materias por nivel (semestre)
   */
  async obtenerMateriasPorNivel(nivel: number): Promise<DtoMateria[]> {
    const todasLasMaterias = await this.obtenerTodasLasMaterias();
    return todasLasMaterias.filter(m => m.nivel === nivel);
  }

  /**
   * Filtra materias por carrera
   */
  async obtenerMateriasPorCarrera(codigoCarrera: string): Promise<DtoMateria[]> {
    const todasLasMaterias = await this.obtenerTodasLasMaterias();
    return todasLasMaterias.filter(m => m.carrera?.codigo === codigoCarrera);
  }
}
