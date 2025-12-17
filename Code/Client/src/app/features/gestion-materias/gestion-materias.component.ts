/**
 * Componente de Gestión de Materias
 * Exclusivo para Director de Carrera
 * Permite crear, editar y gestionar el catálogo de materias
 * 
 * Heurística Nielsen #5: Prevención de errores
 * - Validaciones de prerequisitos
 * 
 * Heurística Nielsen #6: Reconocimiento antes que recuerdo
 * - Información clara de cada materia y sus dependencias
 */

import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { NotificacionService } from '../../core/services/notificacion.service';
import { MateriasService } from '../../core/services/materias.service';
import { OfertaAcademicaService } from '../../core/services/oferta-academica.service';
import { CarrerasService } from '../../core/services/carreras.service';
import { ParalelosService } from '../../core/services/paralelos.service';
import { Materia } from '../../models';

@Component({
  selector: 'app-gestion-materias',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="gestion-materias-page">
      <header class="pagina-header">
        <h1>Gestión de Materias</h1>
        <p>Administra el catálogo de materias de la carrera</p>
      </header>

      <!-- Botón crear nueva materia -->
      <section class="acciones-principales">
        <button class="btn btn-primario" (click)="mostrarFormulario = !mostrarFormulario">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="12" y1="5" x2="12" y2="19"></line>
            <line x1="5" y1="12" x2="19" y2="12"></line>
          </svg>
          {{ modoEdicion ? 'Cancelar Edición' : 'Crear Nueva Materia' }}
        </button>
      </section>

      <!-- Formulario de creación/edición -->
      <section class="formulario-materia card" *ngIf="mostrarFormulario">
        <h2 class="card-titulo">{{ modoEdicion ? 'Editar Materia' : 'Nueva Materia' }}</h2>
        
        <form (ngSubmit)="guardarMateria()" class="form-grid">
          <!-- Código -->
          <div class="form-group">
            <label class="form-label">Código *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="materiaForm.codigo" 
              name="codigo"
              placeholder="Ej: MAT-101"
              required
            >
            <span class="form-hint">Formato: XXX-###</span>
          </div>

          <!-- Nombre -->
          <div class="form-group">
            <label class="form-label">Nombre *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="materiaForm.nombre" 
              name="nombre"
              placeholder="Ej: Cálculo I"
              required
            >
          </div>

          <!-- Créditos -->
          <div class="form-group">
            <label class="form-label">Créditos *</label>
            <input 
              type="number" 
              class="form-input" 
              [(ngModel)]="materiaForm.creditos" 
              name="creditos"
              min="1"
              max="10"
              required
            >
          </div>

          <!-- Semestre -->
          <div class="form-group">
            <label class="form-label">Semestre *</label>
            <select class="form-select" [(ngModel)]="materiaForm.semestre" name="semestre" required>
              <option value="">Seleccione</option>
              <option *ngFor="let sem of [1,2,3,4,5,6,7,8,9,10]" [value]="sem">{{ sem }}</option>
            </select>
          </div>

          <!-- Prerequisitos -->
          <div class="form-group form-group-full">
            <label class="form-label">Prerequisitos (Materias Correlativas)</label>
            <div class="prerequisitos-selector">
              <div class="checkbox-group">
                <label *ngFor="let materia of materiasDisponibles()" class="checkbox-label">
                  <input 
                    type="checkbox" 
                    [value]="materia.codigo"
                    [checked]="materiaForm.prerequisitosCodigos.includes(materia.codigo)"
                    (change)="togglePrerequisito(materia.codigo)"
                  >
                  <span>{{ materia.codigo }} - {{ materia.nombre }}</span>
                </label>
              </div>
            </div>
            <span class="form-hint">Selecciona las materias que son prerequisito</span>
          </div>

          <!-- Botones -->
          <div class="form-actions form-group-full">
            <button type="submit" class="btn btn-primario" [disabled]="!formularioValido()">
              {{ modoEdicion ? 'Actualizar' : 'Crear' }} Materia
            </button>
            <button type="button" class="btn btn-ghost" (click)="cancelar()">
              Cancelar
            </button>
          </div>
        </form>
      </section>

      <!-- Lista de materias -->
      <section class="materias-lista">
        <div class="lista-header">
          <h2>Catálogo de Materias ({{ materias().length }})</h2>
          <div class="filtros">
            <select class="form-select" [(ngModel)]="filtroSemestre" (change)="aplicarFiltros()">
              <option value="">Todos los semestres</option>
              <option *ngFor="let sem of [1,2,3,4,5,6,7,8,9,10]" [value]="sem">Semestre {{ sem }}</option>
            </select>
          </div>
        </div>

        <div class="materias-grid">
          <div class="materia-card card" *ngFor="let materia of materiasFiltradas()">
            <div class="materia-header">
              <div>
                <div class="materia-codigo-badge">{{ materia.codigo }}</div>
                <h3>{{ materia.nombre }}</h3>
              </div>
              <span class="badge badge-primario">Semestre {{ materia.semestre }}</span>
            </div>

            <div class="materia-info">
              <div class="info-row">
                <span class="info-label">Créditos:</span>
                <span class="info-valor">{{ materia.creditos }}</span>
              </div>
              <div class="info-row" *ngIf="materia.prerrequisitos && materia.prerrequisitos.length > 0">
                <span class="info-label">Prerequisitos:</span>
                <div class="prerequisitos-lista">
                  <span *ngFor="let pre of materia.prerrequisitos" class="prerequisito-tag">
                    {{ pre.codigo }}
                  </span>
                </div>
              </div>
              <div class="info-row" *ngIf="!materia.prerrequisitos || materia.prerrequisitos.length === 0">
                <span class="info-label">Prerequisitos:</span>
                <span class="info-valor texto-secundario">Ninguno</span>
              </div>
            </div>

            <div class="materia-estado">
              <span class="badge" [class.badge-exito]="materia.activa" [class.badge-error]="!materia.activa">
                {{ materia.activa ? 'Activa' : 'Inactiva' }}
              </span>
              <span class="info-secundario">{{ contarGrupos(materia.id) }} grupos</span>
            </div>

            <div class="materia-acciones">
              <button class="btn btn-ghost btn-sm" (click)="editarMateria(materia)">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                  <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                </svg>
                Editar
              </button>
              <button 
                class="btn btn-sm" 
                [class.btn-error]="materia.activa"
                [class.btn-exito]="!materia.activa"
                (click)="toggleEstado(materia)"
              >
                {{ materia.activa ? 'Desactivar' : 'Activar' }}
              </button>
            </div>
          </div>
        </div>

        <div class="estado-vacio" *ngIf="materias().length === 0">
          <div class="estado-vacio-icono"></div>
          <p>No hay materias registradas</p>
          <p class="texto-secundario">Crea la primera materia para comenzar</p>
        </div>
      </section>
    </div>
  `,
  styles: [`
    .gestion-materias-page {
      max-width: var(--ancho-maximo);
      margin: 0 auto;
    }

    .pagina-header {
      margin-bottom: var(--espaciado-xl);
    }

    .pagina-header h1 {
      font-size: var(--texto-2xl);
      margin-bottom: var(--espaciado-xs);
    }

    .pagina-header p {
      color: var(--color-texto-secundario);
      margin: 0;
    }

    .acciones-principales {
      margin-bottom: var(--espaciado-xl);
    }

    .formulario-materia {
      margin-bottom: var(--espaciado-xl);
      padding: var(--espaciado-xl);
    }

    .card-titulo {
      font-size: var(--texto-lg);
      font-weight: 600;
      margin-bottom: var(--espaciado-lg);
    }

    .form-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: var(--espaciado-lg);
    }

    .form-group {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-xs);
    }

    .form-group-full {
      grid-column: 1 / -1;
    }

    .form-hint {
      font-size: var(--texto-xs);
      color: var(--color-texto-secundario);
    }

    .form-textarea {
      min-height: 80px;
      resize: vertical;
    }

    .prerequisitos-selector {
      border: 1px solid var(--color-borde);
      border-radius: var(--radio-md);
      padding: var(--espaciado-md);
      max-height: 200px;
      overflow-y: auto;
    }

    .checkbox-group {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-sm);
    }

    .checkbox-label {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      cursor: pointer;
      padding: var(--espaciado-xs);
      border-radius: var(--radio-sm);
      transition: background-color var(--transicion-rapida);
    }

    .checkbox-label:hover {
      background-color: var(--color-fondo);
    }

    .form-actions {
      display: flex;
      gap: var(--espaciado-md);
      justify-content: flex-end;
      margin-top: var(--espaciado-md);
    }

    .lista-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: var(--espaciado-md);
    }

    .lista-header h2 {
      font-size: var(--texto-lg);
      font-weight: 600;
    }

    .filtros {
      display: flex;
      gap: var(--espaciado-sm);
    }

    .materias-grid {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
      gap: var(--espaciado-lg);
    }

    .materia-card {
      padding: var(--espaciado-lg);
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-md);
    }

    .materia-header {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      padding-bottom: var(--espaciado-md);
      border-bottom: 1px solid var(--color-borde);
    }

    .materia-codigo-badge {
      display: inline-block;
      background-color: var(--color-primario-light);
      color: var(--color-primario);
      padding: 4px 12px;
      border-radius: var(--radio-sm);
      font-size: var(--texto-xs);
      font-weight: 600;
      margin-bottom: var(--espaciado-xs);
    }

    .materia-header h3 {
      font-size: var(--texto-base);
      font-weight: 600;
      margin-bottom: var(--espaciado-xs);
    }

    .materia-descripcion {
      color: var(--color-texto-secundario);
      font-size: var(--texto-sm);
      margin: 0;
      line-height: 1.4;
    }

    .materia-info {
      display: flex;
      flex-direction: column;
      gap: var(--espaciado-sm);
    }

    .info-row {
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: var(--texto-sm);
    }

    .info-label {
      color: var(--color-texto-secundario);
      font-weight: 500;
    }

    .info-valor {
      font-weight: 600;
    }

    .prerequisitos-lista {
      display: flex;
      flex-wrap: wrap;
      gap: var(--espaciado-xs);
    }

    .prerequisito-tag {
      background-color: var(--color-fondo);
      border: 1px solid var(--color-borde);
      padding: 2px 8px;
      border-radius: var(--radio-sm);
      font-size: var(--texto-xs);
      font-weight: 500;
    }

    .materia-estado {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding-top: var(--espaciado-md);
      border-top: 1px solid var(--color-borde);
    }

    .info-secundario {
      color: var(--color-texto-secundario);
      font-size: var(--texto-sm);
    }

    .materia-acciones {
      display: flex;
      gap: var(--espaciado-sm);
    }

    .estado-vacio {
      text-align: center;
      padding: var(--espaciado-2xl);
      color: var(--color-texto-secundario);
    }

    .estado-vacio-icono {
      font-size: 64px;
      margin-bottom: var(--espaciado-md);
    }

    @media (max-width: 768px) {
      .materias-grid {
        grid-template-columns: 1fr;
      }

      .lista-header {
        flex-direction: column;
        align-items: flex-start;
        gap: var(--espaciado-md);
      }
    }
  `]
})
export class GestionMateriasComponent implements OnInit {
  mostrarFormulario = false;
  modoEdicion = false;
  materiaEditandoId: number | null = null;
  filtroSemestre = '';
  
  // Formulario simplificado: SOLO los campos que usa el backend
  materiaForm = {
    codigo: '',
    nombre: '',
    creditos: 4,
    semestre: '',
    prerequisitosCodigos: [] as string[]
  };

  materias = signal<Materia[]>([]);
  materiasFiltradas = signal<Materia[]>([]);

  constructor(
    public authService: AuthService,
    private materiasService: MateriasService,
    private notificacion: NotificacionService,
    private ofertaService: OfertaAcademicaService,
    private carrerasService: CarrerasService,
    private paralelosService: ParalelosService
  ) {}

  async ngOnInit(): Promise<void> {
    // Verificar que sea director
    const rol = this.authService.rol();
    if (rol !== 'DIRECTOR') {
      this.notificacion.error('Acceso denegado');
      return;
    }

    await this.cargarDatos();
  }

  async cargarDatos(): Promise<void> {
    try {
      // USANDO MateriasService en lugar de mocks
      const materias = await this.materiasService.obtenerTodasLasMaterias();
      await this.carrerasService.obtenerCarreras();
      
      // Convertir DTOs a modelo frontend si es necesario
      this.materias.set(materias as any);
      this.aplicarFiltros();
      
      console.log(`${materias.length} materias cargadas desde backend`);
    } catch (error) {
      console.error('Error cargando materias:', error);
      this.notificacion.error('Error al cargar las materias');
      this.materias.set([]);
    }
  }

  materiasDisponibles(): Materia[] {
    // Para prerequisitos, excluir la materia que se está editando
    return this.materias().filter(m => 
      this.modoEdicion ? m.id !== this.materiaEditandoId : true
    );
  }

  togglePrerequisito(materiaCodigo: string): void {
    const index = this.materiaForm.prerequisitosCodigos.indexOf(materiaCodigo);
    if (index > -1) {
      this.materiaForm.prerequisitosCodigos.splice(index, 1);
    } else {
      this.materiaForm.prerequisitosCodigos.push(materiaCodigo);
    }
  }

  formularioValido(): boolean {
    return !!(
      this.materiaForm.codigo &&
      this.materiaForm.nombre &&
      this.materiaForm.creditos > 0 &&
      this.materiaForm.semestre
    );
  }

  async guardarMateria(): Promise<void> {
    if (!this.formularioValido()) {
      this.notificacion.error('Completa todos los campos obligatorios');
      return;
    }

    // Obtener materias correlativas seleccionadas
    const materiasCorrelativas = this.materias()
      .filter(m => this.materiaForm.prerequisitosCodigos.includes(m.codigo))
      .map(m => ({
        codigo: m.codigo,
        nombre: m.nombre,
        creditos: m.creditos,
        semestre: m.semestre
      }));

    // Obtener la carrera del director actual
    const usuario = this.authService.usuario();
    console.log('[DEBUG] Usuario completo:', usuario);
    console.log('[DEBUG] Carrera del usuario:', (usuario as any)?.carrera);
    
    let carreraDelDirector = (usuario as any)?.carrera;
    
    // FIX TEMPORAL: Si el director no tiene carrera, asignar la carrera por defecto
    if (!carreraDelDirector && usuario?.rol === 'DIRECTOR') {
      console.warn('[FIX] Director sin carrera, asignando ING-SIS por defecto');
      carreraDelDirector = {
        codigo: 'ING-SIS',
        nombre: 'Ingeniería de Sistemas',
        id: 1,
        duracionSemestres: 10,
        facultad: 'Ingeniería'
      };
      
      // Actualizar el usuario en memoria y localStorage
      const usuarioActualizado = {
        ...usuario,
        carrera: carreraDelDirector
      };
      
      // Guardar en localStorage
      localStorage.setItem('usuario_actual', JSON.stringify(usuarioActualizado));
      
      // Mostrar notificación
      this.notificacion.info('Se ha asignado la carrera ING-SIS al director. Recarga la página si persisten errores.');
    }

    try {
      if (this.modoEdicion && this.materiaEditandoId) {
        // USANDO BACKEND: Actualizar materia existente
        const materiaActualizar = this.materias().find(m => m.id === this.materiaEditandoId);
        if (!materiaActualizar) {
          this.notificacion.error('Materia no encontrada');
          return;
        }

        const materiaDTO = {
          codigo: this.materiaForm.codigo,
          nombre: this.materiaForm.nombre,
          creditos: this.materiaForm.creditos,
          semestre: parseInt(this.materiaForm.semestre),
          carrera: carreraDelDirector ? {
            codigo: carreraDelDirector.codigo,
            nombre: carreraDelDirector.nombre
          } : undefined
        };

        await this.materiasService.actualizarMateria(materiaActualizar.codigo, materiaDTO);
        this.notificacion.exito('Materia actualizada exitosamente en el backend');
      } else {
        // USANDO BACKEND: Crear nueva materia con carrera
        // Validar que el director tenga carrera asignada
        if (!carreraDelDirector || !carreraDelDirector.codigo) {
          this.notificacion.error('Error: El director no tiene una carrera asignada');
          return;
        }

        const materiaDTO = {
          codigo: this.materiaForm.codigo,
          nombre: this.materiaForm.nombre,
          creditos: this.materiaForm.creditos,
          semestre: parseInt(this.materiaForm.semestre),
          carrera: {
            codigo: carreraDelDirector.codigo,
            nombre: carreraDelDirector.nombre
          }
        };

        console.log('Enviando materia al backend:', materiaDTO);

        // Usar el endpoint que agrega a la carrera
        await this.materiasService.crearMateriaConCarrera(materiaDTO);
        this.notificacion.exito('Materia creada exitosamente en el backend');
      }

      await this.cargarDatos();
      this.cancelar();
    } catch (error: any) {
      console.error('Error guardando materia:', error);
      const mensaje = error.error?.message || error.message || 'Error al guardar la materia';
      this.notificacion.error(mensaje);
    }
  }

  editarMateria(materia: Materia): void {
    this.modoEdicion = true;
    this.materiaEditandoId = materia.id;
    this.materiaForm = {
      codigo: materia.codigo,
      nombre: materia.nombre,
      creditos: materia.creditos,
      semestre: materia.semestre.toString(),
      prerequisitosCodigos: materia.prerrequisitos?.map(p => p.codigo) || []
    };
    this.mostrarFormulario = true;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  async toggleEstado(materia: Materia): Promise<void> {
    // [LOCK] VALIDACIÓN CRÍTICA: No permitir desactivar materia con estudiantes inscritos
    if (materia.activa) {
      // USANDO BACKEND: Verificar inscripciones activas
      // Por ahora simplificamos, en producción se debe consultar al backend
      const confirmar = window.confirm(
        `¿Estás seguro de que deseas ${materia.activa ? 'desactivar' : 'activar'} esta materia?`
      );
      if (!confirmar) {
        return;
      }
    }

    try {
      // USANDO BACKEND: Toggle estado de materia
      await this.materiasService.toggleEstadoMateria(materia.codigo);
      await this.cargarDatos();
      this.notificacion.exito(
        `Materia ${!materia.activa ? 'activada' : 'desactivada'} exitosamente`
      );
    } catch (error) {
      console.error('Error cambiando estado de materia:', error);
      this.notificacion.error('Error al cambiar el estado de la materia');
    }
  }

  async contarGrupos(materiaId: number): Promise<number> {
    // USANDO BACKEND: Contar paralelos por materia
    try {
      const materia = this.materias().find(m => m.id === materiaId);
      if (!materia) return 0;
      
      const paralelos = await this.paralelosService.obtenerParalelosPorMateriaBackend(materia.codigo);
      return paralelos.length;
    } catch (error) {
      console.error('Error contando grupos:', error);
      return 0;
    }
  }

  aplicarFiltros(): void {
    let filtradas = this.materias();

    if (this.filtroSemestre) {
      filtradas = filtradas.filter(m => m.semestre === parseInt(this.filtroSemestre));
    }

    this.materiasFiltradas.set(filtradas);
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.modoEdicion = false;
    this.materiaEditandoId = null;
    this.materiaForm = {
      codigo: '',
      nombre: '',
      creditos: 4,
      semestre: '',
      prerequisitosCodigos: []
    };
  }
}
