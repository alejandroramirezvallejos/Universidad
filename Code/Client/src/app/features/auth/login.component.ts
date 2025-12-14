/**
 * Página de Login
 * 
 * Heurística Nielsen #5: Prevención de errores
 * - Validación clara de campos
 * 
 * Heurística Nielsen #9: Ayudar a reconocer, diagnosticar y recuperarse de errores
 * - Mensajes de error específicos
 */

import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  template: `
    <div class="login-container">
      <div class="login-content">
        <div class="login-card">
          <div class="login-header">
            <div class="logo-container">
              <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20"></path>
                <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z"></path>
              </svg>
            </div>
            <h1>Sistema Universitario</h1>
            <p>Ingresa tus credenciales</p>
          </div>

          <form (ngSubmit)="iniciarSesion()" class="login-form">
            <!-- Campo Email -->
            <div class="form-grupo">
              <label for="email" class="form-label">Correo institucional</label>
              <input 
                type="email" 
                id="email"
                [(ngModel)]="email"
                name="email"
                class="form-input"
                [class.error]="errorEmail()"
                placeholder="nombre.apellido@ucb.edu.bo"
                required
                (focus)="limpiarError()"
              >
              <span class="form-error" *ngIf="errorEmail()">{{ errorEmail() }}</span>
            </div>

            <!-- Campo Contraseña -->
            <div class="form-grupo">
              <label for="password" class="form-label">Contraseña</label>
              <div class="input-password">
                <input 
                  [type]="mostrarPassword ? 'text' : 'password'"
                  id="password"
                  [(ngModel)]="password"
                  name="password"
                  class="form-input"
                  placeholder="••••••••"
                  required
                  (focus)="limpiarError()"
                >
                <button 
                  type="button" 
                  class="btn-ver-password"
                  (click)="mostrarPassword = !mostrarPassword"
                  [attr.aria-label]="mostrarPassword ? 'Ocultar contraseña' : 'Mostrar contraseña'"
                >
                  <svg *ngIf="!mostrarPassword" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path>
                    <circle cx="12" cy="12" r="3"></circle>
                  </svg>
                  <svg *ngIf="mostrarPassword" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"></path>
                    <line x1="1" y1="1" x2="23" y2="23"></line>
                  </svg>
                </button>
              </div>
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

            <!-- Botón de login -->
            <button 
              type="submit" 
              class="btn btn-primario btn-lg w-full"
              [disabled]="cargando()"
            >
              <span class="spinner spinner-sm" *ngIf="cargando()"></span>
              {{ cargando() ? 'Ingresando...' : 'Iniciar Sesión' }}
            </button>
          </form>

          <!-- Usuarios de prueba -->
          <div class="usuarios-prueba">
            <p class="usuarios-prueba-titulo">Acceso rápido</p>
            <div class="usuarios-lista">
              <button 
                *ngFor="let usuario of usuariosPrueba" 
                class="usuario-btn"
                (click)="usarUsuarioPrueba(usuario.email)"
                [title]="'Ingresar como ' + usuario.nombre"
              >
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                  <circle cx="12" cy="7" r="4"></circle>
                </svg>
                <span>{{ usuario.nombre }}</span>
              </button>
            </div>
          </div>

          <!-- Link a registro -->
          <div class="registro-link">
            <p>¿No tienes cuenta? <a routerLink="/registro">Regístrate aquí</a></p>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .login-container {
      display: flex;
      align-items: center;
      justify-content: center;
      min-height: 100vh;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      padding: var(--espaciado-lg);
    }

    .login-content {
      width: 100%;
      max-width: 420px;
    }

    .login-card {
      background-color: var(--color-fondo-card);
      border-radius: var(--radio-xl);
      box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
      padding: var(--espaciado-2xl);
      backdrop-filter: blur(10px);
    }

    .login-header {
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

    .login-header h1 {
      font-size: var(--texto-2xl);
      font-weight: 700;
      margin-bottom: var(--espaciado-xs);
      color: var(--color-texto);
    }

    .login-header p {
      color: var(--color-texto-secundario);
      margin: 0;
      font-size: var(--texto-sm);
    }

    .login-form {
      margin-bottom: var(--espaciado-lg);
    }

    .input-password {
      position: relative;
    }

    .btn-ver-password {
      position: absolute;
      right: var(--espaciado-sm);
      top: 50%;
      transform: translateY(-50%);
      background: transparent;
      border: none;
      color: var(--color-texto-secundario);
      cursor: pointer;
      padding: var(--espaciado-xs);
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: var(--radio-sm);
      transition: all var(--transicion-rapida);
    }

    .btn-ver-password:hover {
      color: var(--color-primario);
      background-color: var(--color-primario-light);
    }

    .usuarios-prueba {
      padding-top: var(--espaciado-lg);
      border-top: 1px solid var(--color-borde);
    }

    .usuarios-prueba-titulo {
      font-size: var(--texto-sm);
      font-weight: 600;
      color: var(--color-texto-secundario);
      margin-bottom: var(--espaciado-md);
      text-align: center;
    }

    .usuarios-lista {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(120px, 1fr));
      gap: var(--espaciado-sm);
    }

    .usuario-btn {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: var(--espaciado-xs);
      padding: var(--espaciado-md);
      background-color: var(--color-fondo);
      border: 2px solid var(--color-borde);
      border-radius: var(--radio-md);
      cursor: pointer;
      font-size: var(--texto-sm);
      font-weight: 500;
      transition: all var(--transicion-rapida);
      color: var(--color-texto);
    }

    .usuario-btn:hover {
      background-color: var(--color-primario-light);
      border-color: var(--color-primario);
      color: var(--color-primario);
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(102, 126, 234, 0.2);
    }

    .usuario-btn svg {
      flex-shrink: 0;
    }

    .usuario-btn span {
      font-size: var(--texto-xs);
    }

    .registro-link {
      text-align: center;
      margin-top: var(--espaciado-lg);
      padding-top: var(--espaciado-lg);
      border-top: 1px solid var(--color-borde);
    }

    .registro-link p {
      color: var(--color-texto-secundario);
      font-size: var(--texto-sm);
      margin: 0;
    }

    .registro-link a {
      color: var(--color-primario);
      font-weight: 600;
      text-decoration: none;
      transition: all var(--transicion-rapida);
    }

    .registro-link a:hover {
      text-decoration: underline;
    }

    @media (max-width: 480px) {
      .login-card {
        padding: var(--espaciado-xl);
      }

      .logo-container {
        width: 64px;
        height: 64px;
      }

      .logo-container svg {
        width: 32px;
        height: 32px;
      }

      .usuarios-lista {
        grid-template-columns: 1fr;
      }
    }
  `]
})
export class LoginComponent {
  email = '';
  password = '';
  mostrarPassword = false;

  cargando = signal(false);
  errorEmail = signal<string | null>(null);
  errorGeneral = signal<string | null>(null);

  usuariosPrueba = [
    { email: 'juan.perez@ucb.edu.bo', nombre: 'Estudiante' },
    { email: 'maria.gonzalez@ucb.edu.bo', nombre: 'Docente' },
    { email: 'carlos.rodriguez@ucb.edu.bo', nombre: 'Director' }
  ];

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
    // 🔒 MEJORA: Si ya está autenticado, redirigir al dashboard
    if (this.authService.estaAutenticado()) {
      this.router.navigate(['/dashboard']);
    }
  }

  async iniciarSesion(): Promise<void> {
    // Validaciones
    if (!this.email) {
      this.errorEmail.set('El correo es requerido');
      return;
    }

    if (!this.email.includes('@')) {
      this.errorEmail.set('Ingresa un correo válido');
      return;
    }

    // Validar dominio institucional UCB (excepto para usuarios de prueba)
    const esUsuarioPrueba = this.usuariosPrueba.some(u => u.email === this.email);
    if (!esUsuarioPrueba && !this.email.endsWith('@ucb.edu.bo')) {
      this.errorEmail.set('Debe usar un correo institucional @ucb.edu.bo');
      return;
    }

    if (!this.password) {
      this.errorGeneral.set('La contraseña es requerida');
      return;
    }

    this.cargando.set(true);
    this.limpiarError();

    try {
      const resultado = await this.authService.login({
        email: this.email,
        password: this.password
      });

      if (resultado.exito) {
        this.router.navigate(['/dashboard']);
      } else {
        this.errorGeneral.set(resultado.mensaje);
      }
    } catch (error) {
      this.errorGeneral.set('Error al conectar con el servidor');
    } finally {
      this.cargando.set(false);
    }
  }

  usarUsuarioPrueba(email: string): void {
    this.email = email;
    this.password = 'password123';
    this.limpiarError();
  }

  limpiarError(): void {
    this.errorEmail.set(null);
    this.errorGeneral.set(null);
  }
}
