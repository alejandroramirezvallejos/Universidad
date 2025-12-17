/**
 * Componente de Gestión de Aulas
 * Exclusivo para Director de Carrera
 * Permite crear, editar y gestionar aulas
 */

import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { NotificacionService } from '../../core/services/notificacion.service';
import { AulasService } from '../../core/services/aulas.service';
import { DtoAula } from '../../models/backend-dtos';

@Component({
  selector: 'app-gestion-aulas',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="gestion-aulas-page">
      <header class="pagina-header">
        <h1>Gestión de Aulas</h1>
        <p>Administra las aulas disponibles para la gestión académica</p>
      </header>

      <!-- Botón crear nueva aula -->
      <section class="acciones-principales">
        <button class="btn btn-primario" (click)="mostrarFormulario = !mostrarFormulario">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <line x1="12" y1="5" x2="12" y2="19"></line>
            <line x1="5" y1="12" x2="19" y2="12"></line>
          </svg>
          {{ modoEdicion ? 'Cancelar Edición' : 'Crear Nueva Aula' }}
        </button>
        <select class="form-select" [(ngModel)]="filtroEdificio" (change)="aplicarFiltros()">
          <option value="">Todos los edificios</option>
          <option *ngFor="let edificio of edificiosUnicos()" [value]="edificio">{{ edificio }}</option>
        </select>
        <select class="form-select" [(ngModel)]="filtroDisponible" (change)="aplicarFiltros()">
          <option value="">Todas</option>
          <option value="true">Disponibles</option>
          <option value="false">No disponibles</option>
        </select>
      </section>

      <!-- Formulario de creación/edición -->
      <section class="formulario-aula card" *ngIf="mostrarFormulario">
        <h2 class="card-titulo">{{ modoEdicion ? 'Editar Aula' : 'Nueva Aula' }}</h2>
        
        <form (ngSubmit)="guardarAula()" class="form-grid">
          <!-- Código -->
          <div class="form-group">
            <label class="form-label">Código de Aula *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="aulaForm.codigo" 
              name="codigo"
              placeholder="Ej: A-101"
              required
              [disabled]="modoEdicion"
            >
          </div>

          <!-- Edificio -->
          <div class="form-group">
            <label class="form-label">Edificio *</label>
            <input 
              type="text" 
              class="form-input" 
              [(ngModel)]="aulaForm.edificio" 
              name="edificio"
              placeholder="Ej: Edificio A, B, C"
              required
            >
          </div>

          <!-- Capacidad -->
          <div class="form-group">
            <label class="form-label">Capacidad *</label>
            <input 
              type="number" 
              class="form-input" 
              [(ngModel)]="aulaForm.capacidad" 
              name="capacidad"
              placeholder="Número de estudiantes"
              min="10"
              max="200"
              required
            >
          </div>

          <!-- Disponible -->
          <div class="form-group">
            <label class="form-label">Estado</label>
            <div class="checkbox-container">
              <input 
                type="checkbox" 
                id="disponible"
                [(ngModel)]="aulaForm.disponible" 
                name="disponible"
              >
              <label for="disponible">Aula disponible</label>
            </div>
          </div>

          <!-- Acciones -->
          <div class="form-actions form-group-full">
            <button type="button" class="btn btn-secundario" (click)="cancelar()">
              Cancelar
            </button>
            <button type="submit" class="btn btn-primario" [disabled]="!formularioValido()">
              {{ modoEdicion ? 'Actualizar' : 'Crear' }} Aula
            </button>
          </div>
        </form>
      </section>

      <!-- Estadísticas -->
      <section class="estadisticas-grid">
        <div class="card estadistica">
          <div class="estadistica-icono"></div>
          <div class="estadistica-info">
            <span class="estadistica-valor">{{ aulas().length }}</span>
            <span class="estadistica-label">Total Aulas</span>
          </div>
        </div>
        <div class="card estadistica">
          <div class="estadistica-icono"></div>
          <div class="estadistica-info">
            <span class="estadistica-valor">{{ aulasDisponibles() }}</span>
            <span class="estadistica-label">Disponibles</span>
          </div>
        </div>
        <div class="card estadistica">
          <div class="estadistica-icono"></div>
          <div class="estadistica-info">
            <span class="estadistica-valor">{{ edificiosUnicos().length }}</span>
            <span class="estadistica-label">Edificios</span>
          </div>
        </div>
        <div class="card estadistica">
          <div class="estadistica-icono"></div>
          <div class="estadistica-info">
            <span class="estadistica-valor">{{ capacidadTotal() }}</span>
            <span class="estadistica-label">Capacidad Total</span>
          </div>
        </div>
      </section>

      <!-- Lista de aulas -->
      <section class="lista-aulas card">
        <h2 class="card-titulo">Aulas Registradas</h2>
        
        <div class="tabla-contenedor">
          <table class="tabla">
            <thead>
              <tr>
                <th>Código</th>
                <th>Edificio</th>
                <th>Capacidad</th>
                <th>Estado</th>
                <th class="acciones-col">Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let aula of aulasFiltradas()">
                <td><strong>{{ aula.codigo }}</strong></td>
                <td>{{ aula.edificio }}</td>
                <td>{{ aula.capacidad }} estudiantes</td>
                <td>
                  <span class="badge" [class.badge-exito]="aula.disponible" [class.badge-error]="!aula.disponible">
                    {{ aula.disponible ? 'Disponible' : 'No disponible' }}
                  </span>
                </td>
                <td class="acciones-col">
                  <!-- ⚠️ EDICIÓN DESHABILITADA: El backend no tiene endpoint PUT /api/aulas/{codigo} -->
                  <!-- Para modificar un aula: eliminarla y crear una nueva -->
                  <!--
                  <button 
                    class="btn btn-ghost btn-sm" 
                    (click)="editarAula(aula)"
                    title="Editar"
                  >
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                      <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                    </svg>
                  </button>
                  -->
                  <button 
                    class="btn btn-error btn-sm" 
                    (click)="eliminarAula(aula)"
                    title="Eliminar"
                  >
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <polyline points="3 6 5 6 21 6"></polyline>
                      <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                    </svg>
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <div class="estado-vacio" *ngIf="aulasFiltradas().length === 0">
          <div class="estado-vacio-icono"></div>
          <p>No se encontraron aulas</p>
        </div>
      </section>
    </div>
  `,
  styles: [`
    .gestion-aulas-page {
      max-width: var(--ancho-maximo);
      margin: 0 auto;
    }

    .pagina-header {
      margin-bottom: var(--espaciado-xl);
    }

    .acciones-principales {
      display: flex;
      gap: var(--espaciado-md);
      margin-bottom: var(--espaciado-xl);
      align-items: center;
      flex-wrap: wrap;
    }

    .formulario-aula {
      margin-bottom: var(--espaciado-xl);
    }

    .form-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: var(--espaciado-lg);
    }

    .form-group-full {
      grid-column: 1 / -1;
    }

    .checkbox-container {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      padding: var(--espaciado-sm) 0;
    }

    .checkbox-container input[type="checkbox"] {
      width: 20px;
      height: 20px;
      cursor: pointer;
    }

    .form-actions {
      display: flex;
      gap: var(--espaciado-md);
      justify-content: flex-end;
    }

    .estadisticas-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
      gap: var(--espaciado-md);
      margin-bottom: var(--espaciado-xl);
    }

    .estadistica {
      display: flex;
      align-items: center;
      gap: var(--espaciado-md);
    }

    .estadistica-icono {
      font-size: 2rem;
    }

    .estadistica-info {
      display: flex;
      flex-direction: column;
    }

    .estadistica-valor {
      font-size: var(--texto-xl);
      font-weight: 700;
    }

    .estadistica-label {
      font-size: var(--texto-sm);
      color: var(--color-texto-secundario);
    }

    .tabla-contenedor {
      overflow-x: auto;
    }

    .tabla {
      width: 100%;
      border-collapse: collapse;
    }

    .tabla th {
      text-align: left;
      padding: var(--espaciado-md);
      border-bottom: 2px solid var(--color-borde);
      font-weight: 600;
    }

    .tabla td {
      padding: var(--espaciado-md);
      border-bottom: 1px solid var(--color-borde);
    }

    .tabla tbody tr:hover {
      background-color: var(--color-fondo);
    }

    .acciones-col {
      width: 120px;
      text-align: right;
    }

    .acciones-col button {
      margin-left: var(--espaciado-xs);
    }

    .estado-vacio {
      text-align: center;
      padding: var(--espaciado-2xl);
      color: var(--color-texto-secundario);
    }

    .estado-vacio-icono {
      font-size: 3rem;
      margin-bottom: var(--espaciado-md);
    }
  `]
})
export class GestionAulasComponent implements OnInit {
  mostrarFormulario = false;
  modoEdicion = false;
  aulaEditando: DtoAula | null = null;
  filtroEdificio = '';
  filtroDisponible = '';

  aulaForm = {
    codigo: '',
    edificio: '',
    capacidad: 30,
    disponible: true
  };

  constructor(
    public authService: AuthService,
    private notificacion: NotificacionService,
    private aulasService: AulasService
  ) {}
  
  get aulas() { return this.aulasService.aulas; }
  aulasFiltradas = signal<DtoAula[]>([]);

  async ngOnInit(): Promise<void> {
    if (this.authService.rol() !== 'DIRECTOR') {
      this.notificacion.error('Acceso denegado');
      return;
    }

    await this.cargarDatos();
  }

  async cargarDatos(): Promise<void> {
    try {
      await this.aulasService.obtenerAulas();
      this.aplicarFiltros();
    } catch (error) {
      console.error('Error cargando datos:', error);
      this.notificacion.error('Error al cargar aulas');
    }
  }

  aplicarFiltros(): void {
    let filtradas = this.aulas();

    if (this.filtroEdificio) {
      filtradas = filtradas.filter(a => a.edificio === this.filtroEdificio);
    }

    if (this.filtroDisponible) {
      const disponible = this.filtroDisponible === 'true';
      filtradas = filtradas.filter(a => a.disponible === disponible);
    }

    this.aulasFiltradas.set(filtradas);
  }

  edificiosUnicos(): string[] {
    const edificios = new Set(this.aulas().map(a => a.edificio));
    return Array.from(edificios).sort();
  }

  aulasDisponibles(): number {
    return this.aulas().filter(a => a.disponible).length;
  }

  capacidadTotal(): number {
    return this.aulas().reduce((sum, a) => sum + a.capacidad, 0);
  }

  formularioValido(): boolean {
    return !!(
      this.aulaForm.codigo &&
      this.aulaForm.edificio &&
      this.aulaForm.capacidad > 0
    );
  }

  async guardarAula(): Promise<void> {
    if (!this.formularioValido()) {
      this.notificacion.error('Completa todos los campos obligatorios');
      return;
    }

    try {
      if (this.modoEdicion && this.aulaEditando) {
        this.notificacion.advertencia('Actualización no implementada en backend');
      } else {
        const nuevaAula: DtoAula = {
          codigo: this.aulaForm.codigo,
          edificio: this.aulaForm.edificio,
          capacidad: this.aulaForm.capacidad,
          disponible: this.aulaForm.disponible
        };

        await this.aulasService.crearAula(nuevaAula);
        this.notificacion.exito('Aula creada exitosamente');
      }

      await this.cargarDatos();
      this.cancelar();
    } catch (error) {
      console.error('Error guardando aula:', error);
      this.notificacion.error('Error al guardar el aula');
    }
  }

  /*
   * ⚠️ MÉTODO DESHABILITADO
   * El backend no tiene endpoint PUT /api/aulas/{codigo}
   * Para modificar un aula: eliminarla y crear una nueva
   */
  /*
  editarAula(aula: DtoAula): void {
    this.modoEdicion = true;
    this.aulaEditando = aula;
    this.aulaForm = {
      codigo: aula.codigo,
      edificio: aula.edificio,
      capacidad: aula.capacidad,
      disponible: aula.disponible
    };
    this.mostrarFormulario = true;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
  */

  async eliminarAula(aula: DtoAula): Promise<void> {
    const confirmar = window.confirm(
      `¿Estás seguro de eliminar el aula ${aula.codigo}?`
    );

    if (!confirmar) return;

    try {
      // Pasar el objeto completo según lo que espera el backend
      await this.aulasService.eliminarAula({
        codigo: aula.codigo,
        edificio: aula.edificio,
        capacidad: aula.capacidad,
        disponible: aula.disponible
      });
      this.notificacion.exito('Aula eliminada exitosamente');
      await this.cargarDatos();
    } catch (error) {
      console.error('Error eliminando aula:', error);
      this.notificacion.error('Error al eliminar el aula');
    }
  }

  cancelar(): void {
    this.mostrarFormulario = false;
    this.modoEdicion = false;
    this.aulaEditando = null;
    this.aulaForm = {
      codigo: '',
      edificio: '',
      capacidad: 30,
      disponible: true
    };
  }
}
