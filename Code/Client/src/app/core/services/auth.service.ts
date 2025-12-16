/**
 * Servicio de Autenticación
 * ✅ CONECTADO A BACKEND - Usa endpoint /api/auth/login
 * ✅ FALLBACK LOCAL - Si el backend no responde, usa datos locales
 *
 * Heurística Nielsen #1: Visibilidad del estado del sistema
 * - Mantiene el estado de autenticación visible en toda la app
 */

import { Injectable, signal, computed } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario, CredencialesLogin, Estudiante, Docente, Director } from '../../models';
import { ApiService } from './api.service';
import { firstValueFrom } from 'rxjs';

// Interfaz para la respuesta del backend de login
interface LoginResponse {
  codigo: string;
  nombre: string;
  apellido: string;
  email: string;
  rol: string;
  especialidad?: string;
  departamento?: string;
  carrera?: {
    codigo: string;
    nombre: string;
  };
  semestre?: number;
}

// Datos de usuarios locales (trasladados del Loader.java del backend)
const USUARIOS_LOCALES = {
  estudiantes: [
    {
      codigo: 'EST001',
      nombre: 'Juan',
      apellido: 'Pérez',
      email: 'juan.perez@ucb.edu.bo',
      contrasenna: 'password123',
      carrera: { codigo: 'ING-SIS', nombre: 'Ingeniería de Sistemas' },
      semestre: 5
    },
    {
      codigo: 'EST002',
      nombre: 'Ana',
      apellido: 'Martínez',
      email: 'ana.martinez@ucb.edu.bo',
      contrasenna: 'password123',
      carrera: { codigo: 'ING-IND', nombre: 'Ingeniería Industrial' },
      semestre: 3
    }
  ],
  docentes: [
    {
      codigo: 'DOC001',
      nombre: 'María',
      apellido: 'González',
      email: 'maria.gonzalez@ucb.edu.bo',
      contrasenna: 'password123',
      especialidad: 'Ingeniería de Software',
      departamento: 'Sistemas'
    },
    {
      codigo: 'DOC002',
      nombre: 'Pedro',
      apellido: 'López',
      email: 'pedro.lopez@ucb.edu.bo',
      contrasenna: 'password123',
      especialidad: 'Base de Datos',
      departamento: 'Sistemas'
    }
  ],
  directores: [
    {
      codigo: 'DIR001',
      nombre: 'Carlos',
      apellido: 'Rodríguez',
      email: 'carlos.rodriguez@ucb.edu.bo',
      contrasenna: 'password123',
      carrera: { codigo: 'ING-SIS', nombre: 'Ingeniería de Sistemas' },
      departamento: 'Dirección Académica'
    },
    {
      codigo: 'DIR002',
      nombre: 'Laura',
      apellido: 'Fernández',
      email: 'laura.fernandez@ucb.edu.bo',
      contrasenna: 'password123',
      carrera: { codigo: 'ING-IND', nombre: 'Ingeniería Industrial' },
      departamento: 'Dirección Académica'
    }
  ]
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly STORAGE_KEY = 'usuario_actual';
  private usuarioActual = signal<Usuario | null>(this.cargarUsuarioGuardado());

  // Señales computadas para acceso reactivo
  readonly usuario = computed(() => this.usuarioActual());
  readonly estaAutenticado = computed(() => !!this.usuarioActual());
  readonly rol = computed(() => this.usuarioActual()?.rol ?? null);
  readonly nombreCompleto = computed(() => {
    const user = this.usuarioActual();
    return user ? `${user.nombre} ${user.apellido}` : '';
  });
  readonly usuarioId = computed(() => this.usuarioActual()?.id ?? 0);

  constructor(
    private router: Router,
    private api: ApiService
  ) {
    console.log('🔐 AuthService inicializado');
  }

  /**
   * LOGIN - Intenta autenticar con el backend, si falla usa datos locales
   */
  async login(credenciales: CredencialesLogin): Promise<{ exito: boolean; mensaje: string }> {
    console.log('🔐 Intentando login con:', credenciales.email);

    // Primero intentar con el backend
    try {
      const loginData = {
        email: credenciales.email,
        contrasenna: credenciales.password
      };

      const response = await firstValueFrom(
        this.api.post<LoginResponse>('/auth/login', loginData)
      );

      console.log('✅ Usuario autenticado via backend:', response.nombre);
      return this.procesarLoginExitoso(response, 'backend');

    } catch (error: any) {
      console.warn('⚠️ Backend no disponible, usando autenticación local');

      // Si es error 401, son credenciales incorrectas (backend respondió)
      if (error.status === 401) {
        return {
          exito: false,
          mensaje: error.error?.mensaje || 'Email o contraseña incorrectos'
        };
      }

      // Si es otro error (conexión), usar autenticación local
      return this.loginLocal(credenciales);
    }
  }

  /**
   * LOGIN LOCAL - Autentica contra los datos locales (trasladados del Loader)
   */
  private loginLocal(credenciales: CredencialesLogin): { exito: boolean; mensaje: string } {
    const { email, password } = credenciales;

    // Buscar en estudiantes
    const estudiante = USUARIOS_LOCALES.estudiantes.find(
      e => e.email === email && e.contrasenna === password
    );
    if (estudiante) {
      const usuario = this.crearEstudianteLocal(estudiante);
      this.usuarioActual.set(usuario);
      this.guardarUsuario(usuario);
      this.router.navigate(['/dashboard']);
      return { exito: true, mensaje: `¡Bienvenido/a ${usuario.nombre}! (Modo local)` };
    }

    // Buscar en docentes
    const docente = USUARIOS_LOCALES.docentes.find(
      d => d.email === email && d.contrasenna === password
    );
    if (docente) {
      const usuario = this.crearDocenteLocal(docente);
      this.usuarioActual.set(usuario);
      this.guardarUsuario(usuario);
      this.router.navigate(['/dashboard']);
      return { exito: true, mensaje: `¡Bienvenido/a ${usuario.nombre}! (Modo local)` };
    }

    // Buscar en directores
    const director = USUARIOS_LOCALES.directores.find(
      d => d.email === email && d.contrasenna === password
    );
    if (director) {
      const usuario = this.crearDirectorLocal(director);
      this.usuarioActual.set(usuario);
      this.guardarUsuario(usuario);
      this.router.navigate(['/dashboard']);
      return { exito: true, mensaje: `¡Bienvenido/a ${usuario.nombre}! (Modo local)` };
    }

    return { exito: false, mensaje: 'Email o contraseña incorrectos' };
  }

  /**
   * Procesa un login exitoso desde el backend
   */
  private procesarLoginExitoso(response: LoginResponse, origen: string): { exito: boolean; mensaje: string } {
    let usuario: Usuario;

    if (response.rol === 'ESTUDIANTE') {
      usuario = {
        id: parseInt(response.codigo.replace(/\D/g, '')) || 1,
        nombre: response.nombre,
        apellido: response.apellido,
        email: response.email,
        rol: 'ESTUDIANTE',
        activo: true,
        codigoEstudiante: response.codigo,
        carrera: {
          id: 1,
          nombre: response.carrera?.nombre || 'Ingeniería de Sistemas',
          codigo: response.carrera?.codigo || 'ING-SIS',
          duracionSemestres: 10,
          facultad: 'Ingeniería'
        },
        semestre: response.semestre || 1,
        fechaIngreso: new Date()
      } as Estudiante;
    } else if (response.rol === 'DOCENTE') {
      usuario = {
        id: parseInt(response.codigo.replace(/\D/g, '')) || 1,
        nombre: response.nombre,
        apellido: response.apellido,
        email: response.email,
        rol: 'DOCENTE',
        activo: true,
        codigoDocente: response.codigo,
        departamento: response.departamento || 'General',
        especialidad: response.especialidad || ''
      } as Docente;
    } else if (response.rol === 'DIRECTOR') {
      usuario = {
        id: parseInt(response.codigo.replace(/\D/g, '')) || 1,
        nombre: response.nombre,
        apellido: response.apellido,
        email: response.email,
        rol: 'DIRECTOR',
        activo: true,
        codigoDirector: response.codigo,
        departamento: 'Dirección Académica'
      } as Director;
    } else {
      usuario = {
        id: parseInt(response.codigo.replace(/\D/g, '')) || 1,
        nombre: response.nombre,
        apellido: response.apellido,
        email: response.email,
        rol: response.rol as any,
        activo: true
      };
    }

    this.usuarioActual.set(usuario);
    this.guardarUsuario(usuario);
    this.router.navigate(['/dashboard']);

    return {
      exito: true,
      mensaje: `¡Bienvenido/a ${usuario.nombre}! (${origen})`
    };
  }

  /**
   * Crea un objeto Estudiante desde datos locales
   */
  private crearEstudianteLocal(data: any): Estudiante {
    return {
      id: parseInt(data.codigo.replace(/\D/g, '')) || 1,
      nombre: data.nombre,
      apellido: data.apellido,
      email: data.email,
      rol: 'ESTUDIANTE',
      activo: true,
      codigoEstudiante: data.codigo,
      carrera: {
        id: 1,
        nombre: data.carrera.nombre,
        codigo: data.carrera.codigo,
        duracionSemestres: 10,
        facultad: 'Ingeniería'
      },
      semestre: data.semestre,
      fechaIngreso: new Date()
    };
  }

  /**
   * Crea un objeto Docente desde datos locales
   */
  private crearDocenteLocal(data: any): Docente {
    return {
      id: parseInt(data.codigo.replace(/\D/g, '')) || 1,
      nombre: data.nombre,
      apellido: data.apellido,
      email: data.email,
      rol: 'DOCENTE',
      activo: true,
      codigoDocente: data.codigo,
      departamento: data.departamento,
      especialidad: data.especialidad
    };
  }

  /**
   * Crea un objeto Director desde datos locales
   */
  private crearDirectorLocal(data: any): Director {
    return {
      id: parseInt(data.codigo.replace(/\D/g, '')) || 1,
      nombre: data.nombre,
      apellido: data.apellido,
      email: data.email,
      rol: 'DIRECTOR',
      activo: true,
      codigoDirector: data.codigo,
      departamento: data.departamento
    };
  }

  /**
   * REGISTER - Crea un nuevo estudiante
   */
  async register(datos: {
    nombre: string;
    apellido: string;
    email: string;
    password: string;
    codigoEstudiante: string;
    carreraId: number;
  }): Promise<{ exito: boolean; mensaje: string }> {
    try {
      console.log('📝 Registrando nuevo estudiante:', datos.email);

      // Intentar con el backend primero
      const nuevoEstudiante = {
        codigo: datos.codigoEstudiante,
        nombre: datos.nombre,
        apellido: datos.apellido,
        email: datos.email,
        contrasenna: datos.password,
        carrera: { codigo: 'ING-SIS' }
      };

      const estudianteCreado = await firstValueFrom(
        this.api.post<LoginResponse>('/auth/registro/estudiante', nuevoEstudiante)
      );

      const estudiante = this.crearEstudianteLocal({
        ...estudianteCreado,
        carrera: { codigo: 'ING-SIS', nombre: 'Ingeniería de Sistemas' },
        semestre: 1
      });

      this.usuarioActual.set(estudiante);
      this.guardarUsuario(estudiante);
      this.router.navigate(['/dashboard']);

      return { exito: true, mensaje: `¡Registro exitoso! Bienvenido/a ${estudiante.nombre}.` };

    } catch (error: any) {
      console.warn('⚠️ Backend no disponible, registrando localmente');

      // Verificar si el email ya existe localmente
      const existe = USUARIOS_LOCALES.estudiantes.some(e => e.email === datos.email);
      if (existe) {
        return { exito: false, mensaje: 'El email ya está registrado.' };
      }

      // Crear estudiante local
      const estudiante: Estudiante = {
        id: Date.now(),
        nombre: datos.nombre,
        apellido: datos.apellido,
        email: datos.email,
        rol: 'ESTUDIANTE',
        activo: true,
        codigoEstudiante: datos.codigoEstudiante,
        carrera: {
          id: datos.carreraId,
          nombre: 'Ingeniería de Sistemas',
          codigo: 'ING-SIS',
          duracionSemestres: 10,
          facultad: 'Ingeniería'
        },
        semestre: 1,
        fechaIngreso: new Date()
      };

      this.usuarioActual.set(estudiante);
      this.guardarUsuario(estudiante);
      this.router.navigate(['/dashboard']);

      return { exito: true, mensaje: `¡Registro exitoso! Bienvenido/a ${estudiante.nombre}. (Modo local)` };
    }
  }

  /**
   * Logout - Cierra la sesión del usuario
   */
  logout(): void {
    console.log('👋 Cerrando sesión');
    this.usuarioActual.set(null);
    localStorage.removeItem(this.STORAGE_KEY);
    this.router.navigate(['/login']);
  }

  /**
   * Guarda el usuario en localStorage
   */
  private guardarUsuario(usuario: Usuario): void {
    const usuarioSinPassword = { ...usuario };
    delete (usuarioSinPassword as any).password;
    delete (usuarioSinPassword as any).contrasenna;
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(usuarioSinPassword));
    console.log('💾 Usuario guardado en localStorage');
  }

  /**
   * Carga el usuario desde localStorage
   */
  private cargarUsuarioGuardado(): Usuario | null {
    try {
      const data = localStorage.getItem(this.STORAGE_KEY);
      if (data) {
        const usuario = JSON.parse(data);
        console.log('🔄 Sesión recuperada:', usuario.email);
        return usuario;
      }
    } catch (error) {
      console.error('Error cargando sesión:', error);
    }
    return null;
  }

  /**
   * Obtiene los usuarios de prueba disponibles
   */
  getUsuariosPrueba(): { email: string; nombre: string; rol: string }[] {
    return [
      { email: 'juan.perez@ucb.edu.bo', nombre: 'Juan Pérez', rol: 'Estudiante' },
      { email: 'maria.gonzalez@ucb.edu.bo', nombre: 'María González', rol: 'Docente' },
      { email: 'carlos.rodriguez@ucb.edu.bo', nombre: 'Carlos Rodríguez', rol: 'Director' }
    ];
  }
}
