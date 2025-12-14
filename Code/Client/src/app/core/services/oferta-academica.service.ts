/**
 * Servicio de Oferta Académica
 * Gestiona materias, grupos, aulas y docentes
 * 
 * ⚠️ INTEGRACIÓN CON BACKEND:
 * - Conectado a Spring Boot (http://localhost:8080/api)
 * - Usa ApiService para comunicación HTTP
 * - Usa MappersService para convertir DTOs
 * - Fallback a datos mock si backend no está disponible
 * 
 * Heurística Nielsen #2: Coincidencia entre el sistema y el mundo real
 * - Usa terminología universitaria familiar para los usuarios
 */

import { Injectable, computed, signal } from '@angular/core';
import { Materia, Grupo, Aula, Gestion, Horario, DiaSemana } from '../../models';
import { DatosMockService } from './datos-mock.service';
import { ApiService } from './api.service';
import { MappersService } from './mappers.service';
import { DtoMateria, DtoParaleloMateria, DtoAula } from '../../models/backend-dtos';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OfertaAcademicaService {
  // Signals reactivos
  private readonly _materias = signal<Materia[]>([]);
  private readonly _grupos = signal<Grupo[]>([]);
  private readonly _aulas = signal<Aula[]>([]);
  private readonly _cargando = signal<boolean>(false);
  private readonly _usandoBackend = signal<boolean>(false);

  // Signals computados públicos
  readonly materias$ = computed(() => this._materias());
  readonly grupos$ = computed(() => this._grupos());
  readonly aulas$ = computed(() => this._aulas());
  readonly cargando$ = computed(() => this._cargando());
  readonly usandoBackend$ = computed(() => this._usandoBackend());
  
  constructor(
    private datosMock: DatosMockService,
    private api: ApiService,
    private mappers: MappersService
  ) {
    this.inicializarDatos();
  }

  /**
   * Inicializa los datos intentando conectar al backend
   */
  private async inicializarDatos() {
    try {
      this._cargando.set(true);
      
      // Verificar conexión con backend
      const backendDisponible = await this.api.verificarConexion();
      
      if (backendDisponible) {
        console.log('✅ Backend disponible. Cargando datos reales...');
        await this.cargarDatosBackend();
        this._usandoBackend.set(true);
      } else {
        console.warn('⚠️ Backend no disponible. Usando datos mock...');
        this.cargarDatosMock();
        this._usandoBackend.set(false);
      }
    } catch (error) {
      console.error('❌ Error al inicializar datos:', error);
      console.warn('⚠️ Fallback a datos mock');
      this.cargarDatosMock();
      this._usandoBackend.set(false);
    } finally {
      this._cargando.set(false);
    }
  }

  /**
   * Carga datos desde el backend Spring Boot
   */
  private async cargarDatosBackend() {
    try {
      // Cargar materias
      const dtoMaterias = await firstValueFrom(
        this.api.get<DtoMateria[]>('/materias')
      );
      const materias = dtoMaterias.map(dto => this.mappers.dtoToMateria(dto));
      this._materias.set(materias);
      console.log(`✅ ${materias.length} materias cargadas desde backend`);

      // Cargar paralelos/grupos
      const dtoParalelos = await firstValueFrom(
        this.api.get<DtoParaleloMateria[]>('/paralelos')
      );
      
      const gestionActual = { id: 1, nombre: 'II-2025', anio: 2025, periodo: 2 };
      const grupos = dtoParalelos.map(dto => this.mappers.dtoToGrupo(dto, gestionActual));
      this._grupos.set(grupos);
      console.log(`✅ ${grupos.length} grupos cargados desde backend`);

      // Cargar aulas
      const dtoAulas = await firstValueFrom(
        this.api.get<DtoAula[]>('/aulas')
      );
      const aulas = dtoAulas.map(dto => this.mappers.dtoToAula(dto));
      this._aulas.set(aulas);
      console.log(`✅ ${aulas.length} aulas cargadas desde backend`);

    } catch (error) {
      console.error('❌ Error cargando datos del backend:', error);
      throw error;
    }
  }

  /**
   * Carga datos mock como fallback
   */
  private cargarDatosMock() {
    this._materias.set(this.datosMock.materias);
    this._grupos.set(this.datosMock.grupos);
    this._aulas.set(this.datosMock.aulas);
    console.log('✅ Datos mock cargados');
  }

  /**
   * Fuerza recarga de datos desde el backend
   */
  async recargarDatos() {
    await this.inicializarDatos();
  }

  /**
   * Obtiene todas las materias
   */
  obtenerMaterias(): Materia[] {
    return this._materias();
  }

  /**
   * Obtiene todos los grupos
   */
  obtenerGrupos(): Grupo[] {
    return this._grupos();
  }

  /**
   * Obtiene los grupos de una materia específica
   */
  obtenerGruposPorMateria(materiaId: number): Grupo[] {
    return this._grupos().filter(g => g.materia.id === materiaId);
  }

  /**
   * Obtiene todas las aulas
   */
  obtenerAulas(): Aula[] {
    return this._aulas();
  }

  /**
   * Obtiene todas las gestiones
   * ⚠️ LIMITACIÓN: Backend no tiene modelo Gestion, se usan datos locales
   */
  obtenerGestiones(): Gestion[] {
    return this.datosMock.gestiones;
  }

  /**
   * Obtiene la gestión actual (en curso o matrícula)
   * ⚠️ LIMITACIÓN: Backend no tiene modelo Gestion, se usa gestión mock actual
   */
  obtenerGestionActual(): Gestion | undefined {
    return this.datosMock.gestiones.find(g => g.estado !== 'CERRADA');
  }

  /**
   * Busca materias por nombre o código
   */
  buscarMaterias(termino: string): Materia[] {
    if (!termino.trim()) return this._materias();
    
    const terminoLower = termino.toLowerCase();
    return this._materias().filter(m => 
      m.nombre.toLowerCase().includes(terminoLower) ||
      m.codigo.toLowerCase().includes(terminoLower)
    );
  }

  /**
   * Filtra materias por semestre
   */
  filtrarPorSemestre(semestre: number): Materia[] {
    if (semestre <= 0) return this._materias();
    return this._materias().filter(m => m.semestre === semestre);
  }

  /**
   * Verifica disponibilidad de cupo en un grupo
   * ⚠️ LIMITACIÓN: Backend no actualiza cupos dinámicamente
   */
  verificarCupoDisponible(grupoId: number): boolean {
    const grupo = this._grupos().find(g => g.id === grupoId);
    return grupo ? grupo.cupoDisponible > 0 : false;
  }

  /**
   * Obtiene el horario formateado de un grupo
   */
  formatearHorario(horarios: Horario[]): string {
    const diasCortos: Record<DiaSemana, string> = {
      'LUNES': 'Lun',
      'MARTES': 'Mar',
      'MIERCOLES': 'Mié',
      'JUEVES': 'Jue',
      'VIERNES': 'Vie',
      'SABADO': 'Sáb'
    };

    return horarios.map(h => 
      diasCortos[h.dia] + ' ' + h.horaInicio + '-' + h.horaFin
    ).join(', ');
  }

  /**
   * Obtiene los semestres únicos disponibles
   */
  obtenerSemestresDisponibles(): number[] {
    const semestres = new Set(this._materias().map(m => m.semestre));
    return Array.from(semestres).sort((a, b) => a - b);
  }

  /**
   * Crea una nueva materia en el backend
   */
  async crearMateria(materia: Materia): Promise<Materia> {
    try {
      const dtoMateria = this.mappers.materiaToDto(materia);
      const creada = await firstValueFrom(
        this.api.post<DtoMateria>('/materias', dtoMateria)
      );
      const materiaCreada = this.mappers.dtoToMateria(creada);
      
      // Actualizar signal
      this._materias.update(materias => [...materias, materiaCreada]);
      
      console.log('✅ Materia creada:', materiaCreada.nombre);
      return materiaCreada;
    } catch (error) {
      console.error('❌ Error al crear materia:', error);
      throw error;
    }
  }

  /**
   * Elimina una materia del backend
   */
  async eliminarMateria(codigoMateria: string): Promise<void> {
    try {
      await firstValueFrom(
        this.api.delete('/materias', { codigo: codigoMateria })
      );
      
      // Actualizar signal
      this._materias.update(materias => 
        materias.filter(m => m.codigo !== codigoMateria)
      );
      
      console.log('✅ Materia eliminada:', codigoMateria);
    } catch (error) {
      console.error('❌ Error al eliminar materia:', error);
      throw error;
    }
  }

  /**
   * Obtiene materias desde el backend (forzando recarga)
   */
  async obtenerMateriasBackend(): Promise<Materia[]> {
    try {
      const dtoMaterias = await firstValueFrom(
        this.api.get<DtoMateria[]>('/materias')
      );
      const materias = dtoMaterias.map(dto => this.mappers.dtoToMateria(dto));
      this._materias.set(materias);
      return materias;
    } catch (error) {
      console.error('❌ Error al obtener materias del backend:', error);
      // Fallback a datos mock
      const materiasMock = this.datosMock.materias;
      this._materias.set(materiasMock);
      return materiasMock;
    }
  }
}
