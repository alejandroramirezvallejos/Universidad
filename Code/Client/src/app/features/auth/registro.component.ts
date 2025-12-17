/**
 * Componente de Registro
 * Permite crear cuentas de ESTUDIANTE o DOCENTE
 * La cuenta de DIRECTOR es única y no se puede crear
 */

import { Component, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { CarrerasService } from '../../core/services/carreras.service';
import { RegistroEstudiante, RegistroDocente } from '../../models';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="registro-container">
      <div class="registro-content">
        <div class="registro-card">
          <!-- Header -->
          <div class="registro-header">
            <div class="logo-container">
              <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                <circle cx="12" cy="7" r="4"></circle>
              </svg>
            </div>
            <h1>Crear Cuenta</h1>
            <p>Regístrate como estudiante o docente</p>
          </div>

          <!-- Selector de Rol -->
          <div class="rol-selector">
            <button 
              type="button"
              class="rol-btn"
              [class.active]="tipoUsuario() === 'ESTUDIANTE'"
              (click)="tipoUsuario.set('ESTUDIANTE')"
            >
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M22 10v6M2 10l10-5 10 5-10 5z"></path>
                <path d="M6 12v5c3 3 9 3 12 0v-5"></path>
              </svg>
              <span>Estudiante</span>
            </button>
            <button 
              type="button"
              class="rol-btn"
              [class.active]="tipoUsuario() === 'DOCENTE'"
              (click)="tipoUsuario.set('DOCENTE')"
            >
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                <line x1="16" y1="2" x2="16" y2="6"></line>
                <line x1="8" y1="2" x2="8" y2="6"></line>
                <line x1="3" y1="10" x2="21" y2="10"></line>
              </svg>
              <span>Docente</span>
            </button>
          </div>

          <!-- Formulario -->
          <form (ngSubmit)="registrar()" class="registro-form">
            <!-- Datos Básicos -->
            <div class="form-row">
              <div class="form-grupo">
                <label for="nombre" class="form-label">Nombre</label>
                <input 
                  type="text" 
                  id="nombre"
                  [(ngModel)]="nombre"
                  name="nombre"
                  class="form-input"
                  placeholder="Juan"
                  required
                >
              </div>
              <div class="form-grupo">
                <label for="apellido" class="form-label">Apellido</label>
                <input 
                  type="text" 
                  id="apellido"
                  [(ngModel)]="apellido"
                  name="apellido"
                  class="form-input"
                  placeholder="Pérez"
                  required
                >
              </div>
            </div>

            <!-- Email -->
            <div class="form-grupo">
              <label for="email" class="form-label">Correo electrónico institucional</label>
              <input 
                type="email" 
                id="email"
                [(ngModel)]="email"
                name="email"
                class="form-input"
                [class.error]="errorEmail()"
                placeholder="nombre.apellido@ucb.edu.bo"
                required
              >
              <span class="form-error" *ngIf="errorEmail()">{{ errorEmail() }}</span>
              <p class="form-hint">Debe ser un correo institucional &#64;ucb.edu.bo</p>
            </div>

            <!-- Contraseña -->
            <div class="form-row">
              <div class="form-grupo">
                <label for="password" class="form-label">Contraseña</label>
                <input 
                  type="password"
                  id="password"
                  [(ngModel)]="password"
                  name="password"
                  class="form-input"
                  placeholder="••••••••"
                  minlength="6"
                  required
                >
              </div>
              <div class="form-grupo">
                <label for="confirmPassword" class="form-label">Confirmar</label>
                <input 
                  type="password"
                  id="confirmPassword"
                  [(ngModel)]="confirmPassword"
                  name="confirmPassword"
                  class="form-input"
                  placeholder="••••••••"
                  required
                >
              </div>
            </div>

            <!-- Campos específicos ESTUDIANTE -->
            <div *ngIf="tipoUsuario() === 'ESTUDIANTE'" class="campos-especificos">
              <div class="form-grupo">
                <label for="carrera" class="form-label">Carrera</label>
                <select 
                  id="carrera"
                  [(ngModel)]="carreraId"
                  name="carrera"
                  class="form-input"
                  required
                >
                  <option [value]="carrera.id" *ngFor="let carrera of carreras">
                    {{ carrera.nombre }}
                  </option>
                </select>
              </div>
              <p class="info-text">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="12" cy="12" r="10"></circle>
                  <line x1="12" y1="16" x2="12" y2="12"></line>
                  <line x1="12" y1="8" x2="12.01" y2="8"></line>
                </svg>
                Comenzarás en el 1er semestre. Tu código de estudiante se generará automáticamente.
              </p>
            </div>

            <!-- Campos específicos DOCENTE -->
            <div *ngIf="tipoUsuario() === 'DOCENTE'" class="campos-especificos">
              <div class="form-row">
                <div class="form-grupo">
                  <label for="departamento" class="form-label">Departamento</label>
                  <input 
                    type="text" 
                    id="departamento"
                    [(ngModel)]="departamento"
                    name="departamento"
                    class="form-input"
                    placeholder="Ciencias Exactas"
                    required
                  >
                </div>
                <div class="form-grupo">
                  <label for="especialidad" class="form-label">Especialidad</label>
                  <input 
                    type="text" 
                    id="especialidad"
                    [(ngModel)]="especialidad"
                    name="especialidad"
                    class="form-input"
                    placeholder="Matemáticas"
                    required
                  >
                </div>
              </div>
              <p class="info-text">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="12" cy="12" r="10"></circle>
                  <line x1="12" y1="16" x2="12" y2="12"></line>
                  <line x1="12" y1="8" x2="12.01" y2="8"></line>
                </svg>
                Tu código de docente se generará automáticamente.
              </p>
            </div>

            <!-- Error general -->
            <div class="alerta alerta-error" *ngIf="errorGeneral()">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"></circle>
                <line x1="12" y1="8" x2="12" y2="12"></line>
                <line x1="12" y1="16" x2="12.01" y2="16"></line>
              </svg>
              {{ errorGeneral() }}
            </div>

            <!-- Botón de registro -->
            <button 
              type="submit" 
              class="btn btn-primario btn-lg w-full"
              [disabled]="cargando()"
            >
              <span class="spinner spinner-sm" *ngIf="cargando()"></span>
              {{ cargando() ? 'Creando cuenta...' : 'Crear Cuenta' }}
            </button>
          </form>

          <!-- Link a login -->
          <div class="login-link">
            <p>¿Ya tienes cuenta? <a routerLink="/login">Iniciar sesión</a></p>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .registro-container {
      display: flex;
      align-items: center;
      justify-content: center;
      min-height: 100vh;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      padding: var(--espaciado-lg);
    }

    .registro-content {
      width: 100%;
      max-width: 600px;
    }

    .registro-card {
      background-color: var(--color-fondo-card);
      border-radius: var(--radio-xl);
      box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
      padding: var(--espaciado-2xl);
      backdrop-filter: blur(10px);
    }

    .registro-header {
      text-align: center;
      margin-bottom: var(--espaciado-xl);
    }

    .logo-container {
      display: inline-flex;
      align-items: center;
      justify-content: center;
      width: 80px;
      height: 80px;
      background: linear-gradient(135deg, var(--color-primario), #7c3aed);
      border-radius: var(--radio-lg);
      margin-bottom: var(--espaciado-md);
      box-shadow: 0 8px 16px rgba(102, 126, 234, 0.3);
    }

    .logo-container svg {
      color: white;
    }

    .registro-header h1 {
      font-size: var(--texto-2xl);
      font-weight: 700;
      margin-bottom: var(--espaciado-xs);
      color: var(--color-texto);
    }

    .registro-header p {
      color: var(--color-texto-secundario);
      margin: 0;
      font-size: var(--texto-sm);
    }

    .rol-selector {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: var(--espaciado-md);
      margin-bottom: var(--espaciado-xl);
    }

    .rol-btn {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: var(--espaciado-sm);
      padding: var(--espaciado-lg);
      background-color: var(--color-fondo);
      border: 2px solid var(--color-borde);
      border-radius: var(--radio-md);
      cursor: pointer;
      font-size: var(--texto-base);
      font-weight: 500;
      transition: all var(--transicion-rapida);
      color: var(--color-texto-secundario);
    }

    .rol-btn:hover {
      border-color: var(--color-primario);
      background-color: var(--color-primario-light);
    }

    .rol-btn.active {
      background: linear-gradient(135deg, var(--color-primario), #7c3aed);
      border-color: transparent;
      color: white;
      box-shadow: 0 4px 12px rgba(102, 126, 234, 0.3);
    }

    .rol-btn svg {
      flex-shrink: 0;
    }

    .form-row {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: var(--espaciado-md);
    }

    .campos-especificos {
      padding: var(--espaciado-lg);
      background-color: var(--color-fondo);
      border-radius: var(--radio-md);
      margin-bottom: var(--espaciado-lg);
    }

    .info-text {
      display: flex;
      align-items: center;
      gap: var(--espaciado-sm);
      margin-top: var(--espaciado-md);
      padding: var(--espaciado-md);
      background-color: var(--color-primario-light);
      border-left: 3px solid var(--color-primario);
      border-radius: var(--radio-sm);
      color: var(--color-texto-secundario);
      font-size: var(--texto-sm);
      line-height: 1.5;
    }

    .info-text svg {
      flex-shrink: 0;
      color: var(--color-primario);
    }

    .login-link {
      text-align: center;
      margin-top: var(--espaciado-lg);
      padding-top: var(--espaciado-lg);
      border-top: 1px solid var(--color-borde);
    }

    .login-link p {
      color: var(--color-texto-secundario);
      font-size: var(--texto-sm);
      margin: 0;
    }

    .login-link a {
      color: var(--color-primario);
      font-weight: 600;
      text-decoration: none;
      transition: all var(--transicion-rapida);
    }

    .login-link a:hover {
      text-decoration: underline;
    }

    @media (max-width: 640px) {
      .form-row {
        grid-template-columns: 1fr;
      }

      .rol-selector {
        grid-template-columns: 1fr;
      }
    }
  `]
})
export class RegistroComponent {
  // Estado del formulario
  tipoUsuario = signal<'ESTUDIANTE' | 'DOCENTE'>('ESTUDIANTE');
  cargando = signal(false);
  errorEmail = signal<string | null>(null);
  errorGeneral = signal<string | null>(null);

  // Campos comunes
  nombre = '';
  apellido = '';
  email = '';
  password = '';
  confirmPassword = '';

  // Campos de estudiante
  carreraId = 1;

  // Campos de docente
  departamento = '';
  especialidad = '';

  // Datos
  carreras: any[] = [];

  constructor(
    private authService: AuthService,
    private carrerasService: CarrerasService,
    private router: Router
  ) {
    this.cargarCarreras();
  }

  async cargarCarreras(): Promise<void> {
    // USANDO BACKEND: Obtener carreras desde el backend
    const carrerasBackend = await this.carrerasService.obtenerCarreras();
    this.carreras = carrerasBackend;
  }

  async registrar(): Promise<void> {
    if (!this.validarFormulario()) {
      return;
    }

    this.cargando.set(true);
    this.limpiarErrores();

    try {
      if (this.tipoUsuario() === 'ESTUDIANTE') {
        const resultado = await this.authService.register({
          nombre: this.nombre,
          apellido: this.apellido,
          email: this.email,
          password: this.password,
          codigoEstudiante: this.generarCodigoEstudiante(),
          carreraId: this.carreraId!
        });

        if (resultado.exito) {
          this.router.navigate(['/dashboard']);
        } else {
          this.errorGeneral.set(resultado.mensaje);
        }
      } else {
        this.errorGeneral.set('Registro de docentes pendiente');
      }
    } catch (error) {
      this.errorGeneral.set('Error al crear la cuenta');
    } finally {
      this.cargando.set(false);
    }
  }

  private validarFormulario(): boolean {
    if (!this.nombre || !this.apellido) {
      this.errorGeneral.set('El nombre y apellido son requeridos');
      return false;
    }

    if (!this.email || !this.email.includes('@')) {
      this.errorEmail.set('Ingresa un correo válido');
      return false;
    }

    // Validar dominio institucional UCB
    if (!this.email.endsWith('@ucb.edu.bo')) {
      this.errorEmail.set('Debe usar un correo institucional @ucb.edu.bo');
      return false;
    }

    // Verificar email único
    const usuariosGuardados = JSON.parse(localStorage.getItem('usuarios_registrados') || '[]');
    if (usuariosGuardados.find((u: any) => u.email === this.email)) {
      this.errorEmail.set('Este correo ya está registrado');
      return false;
    }

    if (!this.password || this.password.length < 6) {
      this.errorGeneral.set('La contraseña debe tener al menos 6 caracteres');
      return false;
    }

    if (this.password !== this.confirmPassword) {
      this.errorGeneral.set('Las contraseñas no coinciden');
      return false;
    }

    if (this.tipoUsuario() === 'DOCENTE' && (!this.departamento || !this.especialidad)) {
      this.errorGeneral.set('Completa todos los campos de docente');
      return false;
    }

    return true;
  }

  private generarCodigoEstudiante(): string {
    // Generar código con formato: AÑO-XXX (ej: 2025-001)
    const año = new Date().getFullYear();
    const usuariosGuardados = JSON.parse(localStorage.getItem('usuarios_registrados') || '[]');
    const estudiantesDelAño = usuariosGuardados.filter((u: any) => 
      u.rol === 'ESTUDIANTE' && u.codigoEstudiante?.startsWith(año.toString())
    );
    const numeroConsecutivo = (estudiantesDelAño.length + 1).toString().padStart(3, '0');
    return `${año}-${numeroConsecutivo}`;
  }

  private generarCodigoDocente(): string {
    // Generar código con formato: DOC-XXX (ej: DOC-001)
    const usuariosGuardados = JSON.parse(localStorage.getItem('usuarios_registrados') || '[]');
    const docentes = usuariosGuardados.filter((u: any) => u.rol === 'DOCENTE');
    const numeroConsecutivo = (docentes.length + 1).toString().padStart(3, '0');
    return `DOC-${numeroConsecutivo}`;
  }

  private crearEstudiante(): any {
    const carrera = this.carreras.find(c => c.id === this.carreraId)!;
    return {
      id: Date.now(),
      nombre: this.nombre,
      apellido: this.apellido,
      email: this.email,
      password: this.password,
      rol: 'ESTUDIANTE',
      activo: true,
      codigoEstudiante: this.generarCodigoEstudiante(),
      carrera: carrera,
      semestre: 1, // Siempre comienza en 1er semestre
      fechaIngreso: new Date(),
      fechaCreacion: new Date()
    };
  }

  private crearDocente(): any {
    return {
      id: Date.now(),
      nombre: this.nombre,
      apellido: this.apellido,
      email: this.email,
      password: this.password,
      rol: 'DOCENTE',
      activo: true,
      codigoDocente: this.generarCodigoDocente(),
      departamento: this.departamento,
      especialidad: this.especialidad,
      fechaCreacion: new Date()
    };
  }

  private limpiarErrores(): void {
    this.errorEmail.set(null);
    this.errorGeneral.set(null);
  }
}
