import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { firstValueFrom } from 'rxjs';

export interface DtoHistorialAcademico {
  estudiante: {
    codigo: string;
    nombre: string;
    email: string;
  };
  actas: DtoActa[];
}

export interface DtoActa {
  codigo: string;
  notaFinal: number;
  calificacion: string;
  gestion: any;
  paraleloMateria: any;
}

export interface DtoPromedio {
  estudianteCodigo: string;
  promedio: number;
  totalMaterias: number;
}

/**
 * Servicio de Historial Académico
 * Gestiona el historial y promedios de los estudiantes
 */
@Injectable({
  providedIn: 'root'
})
export class HistorialService {
  constructor(private api: ApiService) {}

  /**
   * Obtiene el historial académico completo de un estudiante
   * USANDO: GET /api/historial/estudiante/{codigo}
   */
  async obtenerHistorialEstudiante(codigoEstudiante: string): Promise<DtoHistorialAcademico | null> {
    try {
      const historial = await firstValueFrom(
        this.api.get<DtoHistorialAcademico>(`/historial/estudiante/${codigoEstudiante}`)
      );
      console.log(`Historial académico obtenido para estudiante ${codigoEstudiante}`);
      return historial;
    } catch (error) {
      console.error(`Error al obtener historial de ${codigoEstudiante}:`, error);
      return null;
    }
  }

  /**
   * Obtiene el promedio general de un estudiante
   * USANDO: GET /api/historial/estudiante/{codigo}/promedio
   */
  async obtenerPromedioGeneral(codigoEstudiante: string): Promise<DtoPromedio | null> {
    try {
      const promedio = await firstValueFrom(
        this.api.get<DtoPromedio>(`/historial/estudiante/${codigoEstudiante}/promedio`)
      );
      console.log(`Promedio general obtenido: ${promedio.promedio.toFixed(2)} (${promedio.totalMaterias} materias)`);
      return promedio;
    } catch (error) {
      console.error(`Error al obtener promedio de ${codigoEstudiante}:`, error);
      return null;
    }
  }

  /**
   * Obtiene estadísticas del historial académico
   */
  async obtenerEstadisticas(codigoEstudiante: string): Promise<{
    promedio: number;
    totalMaterias: number;
    materiasAprobadas: number;
    materiasReprobadas: number;
    creditosCumplidos: number;
  } | null> {
    try {
      const [historial, promedio] = await Promise.all([
        this.obtenerHistorialEstudiante(codigoEstudiante),
        this.obtenerPromedioGeneral(codigoEstudiante)
      ]);

      if (!historial || !promedio) {
        return null;
      }

      const materiasAprobadas = historial.actas.filter(a => a.notaFinal >= 51).length;
      const materiasReprobadas = historial.actas.filter(a => a.notaFinal < 51).length;
      
      // Calcular créditos (asumiendo que cada materia vale cierta cantidad)
      const creditosCumplidos = historial.actas
        .filter(a => a.notaFinal >= 51)
        .reduce((sum, acta) => {
          const creditos = acta.paraleloMateria?.materia?.creditos || 0;
          return sum + creditos;
        }, 0);

      return {
        promedio: promedio.promedio,
        totalMaterias: promedio.totalMaterias,
        materiasAprobadas,
        materiasReprobadas,
        creditosCumplidos
      };
    } catch (error) {
      console.error(`Error al obtener estadísticas de ${codigoEstudiante}:`, error);
      return null;
    }
  }

  /**
   * Obtiene el ranking de un estudiante (si el backend lo soporta en el futuro)
   */
  async obtenerRankingEstudiante(codigoEstudiante: string): Promise<{
    posicion: number;
    totalEstudiantes: number;
    percentil: number;
  } | null> {
    // Este endpoint aún no existe en el backend
    // Se puede implementar cuando esté disponible
    console.warn('Endpoint de ranking no implementado en backend');
    return null;
  }

  /**
   * Verifica si un estudiante ha cumplido con los requisitos de graduación
   */
  async verificarRequisitosGraduacion(codigoEstudiante: string, creditosRequeridos: number = 200): Promise<{
    cumpleRequisitos: boolean;
    creditosObtenidos: number;
    creditosFaltantes: number;
    promedioGeneral: number;
  } | null> {
    const estadisticas = await this.obtenerEstadisticas(codigoEstudiante);
    
    if (!estadisticas) {
      return null;
    }

    const creditosFaltantes = Math.max(0, creditosRequeridos - estadisticas.creditosCumplidos);
    
    return {
      cumpleRequisitos: estadisticas.creditosCumplidos >= creditosRequeridos && estadisticas.promedio >= 51,
      creditosObtenidos: estadisticas.creditosCumplidos,
      creditosFaltantes,
      promedioGeneral: estadisticas.promedio
    };
  }
}
