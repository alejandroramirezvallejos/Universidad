import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { firstValueFrom } from 'rxjs';

export interface DtoMateria {
  codigo: string;
  nombre: string;
  creditos: number;
  semestre?: number;  // Backend usa 'semestre', no 'nivel'
  nivel?: number;     // Mantener por compatibilidad
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
      console.log('[MateriasService] Obteniendo todas las materias...');
      
      const materias = await firstValueFrom(
        this.api.get<DtoMateria[]>('/materias')
      );
      
      console.log(`[MateriasService] ${materias.length} materias obtenidas del backend`);
      console.log('[MateriasService] Muestra de datos (primeras 2 materias):', materias.slice(0, 2));
      
      // Mapear a estructura completa con valores por defecto
      // Evitar referencias circulares en materiasCorrelativas
      const materiasMapeadas = materias.map(m => this.mapearMateriaCompleta(m, true));
      
      console.log('[MateriasService] Materias mapeadas para frontend:', materiasMapeadas.length);
      
      return materiasMapeadas;
    } catch (error) {
      console.error('[MateriasService] Error al obtener materias:', error);
      return [];
    }
  }

  /**
   * Mapea un DTO del backend a una estructura completa con valores por defecto
   * @param dto - El DTO de materia del backend
   * @param mapearCorrelativas - Si es false, no mapea las correlativas (evita recursión infinita)
   */
  private mapearMateriaCompleta(dto: any, mapearCorrelativas: boolean = false): any {
    if (!dto) return null;
    
    return {
      id: this.codigoToId(dto.codigo),
      codigo: dto.codigo,
      nombre: dto.nombre,
      creditos: dto.creditos || 0,
      horasTeoricas: 4, // Valor por defecto
      horasPracticas: 2, // Valor por defecto
      semestre: dto.semestre || dto.nivel || 1,
      // Solo mapear correlativas en el primer nivel para evitar recursión infinita
      prerrequisitos: (mapearCorrelativas && dto.materiasCorrelativas) 
        ? dto.materiasCorrelativas.map((m: any) => this.mapearMateriaCompleta(m, false)) 
        : [],
      descripcion: dto.descripcion || '',
      activa: dto.activa !== false,
      carrera: dto.carrera
    };
  }

  /**
   * Convierte código a ID numérico
   */
  private codigoToId(codigo: string): number {
    if (!codigo) return 0;
    const num = parseInt(codigo.replace(/\D/g, ''));
    return isNaN(num) ? Math.abs(codigo.split('').reduce((a, b) => a + b.charCodeAt(0), 0)) : num;
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
   * Crea una nueva materia (sin carrera)
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
   * Crea una nueva materia y la asocia a una carrera
   * USANDO: POST /api/materias/agregar-carrera
   * Backend espera Materia con: codigo, nombre, creditos, semestre, carrera (con codigo)
   */
  async crearMateriaConCarrera(materia: DtoMateria): Promise<DtoMateria | null> {
    try {
      // Validar que tenga carrera
      if (!materia.carrera || !materia.carrera.codigo) {
        console.error('Error: La materia debe tener una carrera con código');
        throw new Error('La materia debe estar asociada a una carrera');
      }

      // Crear DTO limpio con solo los campos que el backend necesita
      const materiaDTO = {
        codigo: materia.codigo,
        nombre: materia.nombre,
        creditos: materia.creditos,
        semestre: materia.semestre,
        activa: true,
        carrera: {
          codigo: materia.carrera.codigo,
          nombre: materia.carrera.nombre || ''
        },
        materiasCorrelativas: []  // No enviar correlativas en creación
      };

      console.log('Creando materia con carrera:', materiaDTO);

      const materiaCreada = await firstValueFrom(
        this.api.post<DtoMateria>('/materias/agregar-carrera', materiaDTO)
      );
      console.log(`Materia ${materia.codigo} creada y asociada a carrera exitosamente`);
      return materiaCreada;
    } catch (error: any) {
      console.error('Error al crear materia con carrera:', error);
      console.error('Detalle del error:', error.error || error.message);
      throw error;  // Propagar el error para que el componente lo maneje
    }
  }

  /**
   * Actualiza una materia existente
   * USANDO: PUT /api/materias/{codigo}
   */
  async actualizarMateria(codigo: string, materia: DtoMateria): Promise<DtoMateria | null> {
    try {
      console.log(`[MateriasService] Actualizando materia ${codigo} con datos:`, materia);
      
      const materiaActualizada = await firstValueFrom(
        this.api.put<DtoMateria>(`/materias/${codigo}`, materia)
      );
      
      console.log(`[MateriasService] Materia ${codigo} actualizada exitosamente:`, materiaActualizada);
      return materiaActualizada;
    } catch (error) {
      console.error(`[MateriasService] Error al actualizar materia ${codigo}:`, error);
      return null;
    }
  }

  /**
   * Cambia el estado activo/inactivo de una materia
   * USANDO: PATCH /api/materias/{codigo}/estado
   */
  async toggleEstadoMateria(codigo: string): Promise<DtoMateria | null> {
    try {
      const materiaActualizada = await firstValueFrom(
        this.api.patch<DtoMateria>(`/materias/${codigo}/estado`, {})
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
   * Filtra materias por semestre
   */
  async obtenerMateriasPorSemestre(semestre: number): Promise<DtoMateria[]> {
    const todasLasMaterias = await this.obtenerTodasLasMaterias();
    return todasLasMaterias.filter(m => m.semestre === semestre);
  }

  /**
   * Filtra materias por carrera
   */
  async obtenerMateriasPorCarrera(codigoCarrera: string): Promise<DtoMateria[]> {
    const todasLasMaterias = await this.obtenerTodasLasMaterias();
    return todasLasMaterias.filter(m => m.carrera?.codigo === codigoCarrera);
  }
}
