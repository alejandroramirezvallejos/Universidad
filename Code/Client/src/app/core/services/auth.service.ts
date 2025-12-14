/**
 * Servicio de Autenticación
 * ✅ CONECTADO A BACKEND - Usa endpoints existentes de estudiantes/docentes
 * 🔓 SIN JWT - Autenticación simple para desarrollo
 * 
 * LOGIN: GET /api/estudiantes o /api/docentes → buscar por email
 * REGISTER: POST /api/estudiantes → crear nuevo usuario
 * 
 * ⚠️ NOTA: Backend NO tiene campo password, por lo que:
 *    - Login: Cualquier email que exista puede loguearse
 *    - Register: Password no se guarda (solo en localStorage)
 * 
 * Heurística Nielsen #1: Visibilidad del estado del sistema
 * - Mantiene el estado de autenticación visible en toda la app
 */

import { Injectable, signal, computed } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario, CredencialesLogin, Estudiante, Docente, Director } from '../../models';
import { ApiService } from './api.service';
import { MappersService } from './mappers.service';
import { DtoEstudiante, DtoDocente, DtoDirectorCarrera } from '../../models/backend-dtos';
import { firstValueFrom } from 'rxjs';

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
    private api: ApiService,
    private mappers: MappersService
  ) {
    console.log('🔐 AuthService inicializado');
  }

  /**
   * LOGIN - Busca el usuario en el backend (estudiantes o docentes)
   * 🔓 SIN VALIDACIÓN DE PASSWORD - Cualquier email que exista puede loguearse
   * ⚠️ TEMPORAL: Para desarrollo sin preocuparse por seguridad
   */
  async login(credenciales: CredencialesLogin): Promise<{ exito: boolean; mensaje: string }> {
    try {
      console.log('🔐 Intentando login con:', credenciales.email);

      // 1. Buscar en ESTUDIANTES
      try {
        const dtoEstudiantes = await firstValueFrom(
          this.api.get<DtoEstudiante[]>('/estudiantes', false)
        );
        
        const estudianteDto = dtoEstudiantes.find(e => e.email === credenciales.email);
        
        if (estudianteDto) {
          console.log('✅ Estudiante encontrado:', estudianteDto.nombre);
          
          const estudiante = this.mappers.dtoToEstudiante(estudianteDto);
          this.usuarioActual.set(estudiante);
          this.guardarUsuario(estudiante);
          
          this.router.navigate(['/dashboard']);
          
          return { 
            exito: true, 
            mensaje: `¡Bienvenido/a ${estudiante.nombre}! Conectado al backend.` 
          };
        }
      } catch (errorEstudiantes) {
        console.warn('⚠️ No se pudo buscar en estudiantes:', errorEstudiantes);
      }

      // 2. Buscar en DOCENTES (por email o código)
      try {
        const dtoDocentes = await firstValueFrom(
          this.api.get<DtoDocente[]>('/docentes', false)
        );
        
        // Buscar por código o por email generado
        const docenteDto = dtoDocentes.find(d => 
          d.codigo === credenciales.email || 
          `${d.codigo}@universidad.edu` === credenciales.email
        );
        
        if (docenteDto) {
          console.log('✅ Docente encontrado:', docenteDto.nombre);
          
          const docente: Docente = {
            id: parseInt(docenteDto.codigo),
            nombre: docenteDto.nombre.split(' ')[0],
            apellido: docenteDto.nombre.split(' ').slice(1).join(' ') || '',
            email: `${docenteDto.codigo}@universidad.edu`,
            rol: 'DOCENTE',
            activo: true,
            codigoDocente: docenteDto.codigo,
            departamento: 'General',
            especialidad: docenteDto.especialidad
          };
          
          this.usuarioActual.set(docente);
          this.guardarUsuario(docente);
          
          this.router.navigate(['/dashboard']);
          
          return { 
            exito: true, 
            mensaje: `¡Bienvenido/a ${docente.nombre}! Conectado al backend.` 
          };
        }
      } catch (errorDocentes) {
        console.warn('⚠️ No se pudo buscar en docentes:', errorDocentes);
      }

      // 3. Buscar en DIRECTORES (por email o código)
      try {
        const dtoDirectores = await firstValueFrom(
          this.api.get<DtoDirectorCarrera[]>('/directores', false)
        );
        
        // Buscar por código o por email
        const directorDto = dtoDirectores.find(d => 
          d.codigo === credenciales.email || 
          d.email === credenciales.email
        );
        
        if (directorDto) {
          console.log('✅ Director encontrado:', directorDto.nombre);
          
          const director: Director = {
            id: parseInt(directorDto.codigo),
            nombre: directorDto.nombre.split(' ')[0],
            apellido: directorDto.nombre.split(' ').slice(1).join(' ') || '',
            email: directorDto.email,
            rol: 'DIRECTOR',
            activo: true,
            codigoDirector: directorDto.codigo,
            departamento: 'Dirección Académica'
          };
          
          this.usuarioActual.set(director);
          this.guardarUsuario(director);
          
          this.router.navigate(['/dashboard']);
          
          return { 
            exito: true, 
            mensaje: `¡Bienvenido/a ${director.nombre}! Conectado al backend.` 
          };
        }
      } catch (errorDirectores) {
        console.warn('⚠️ No se pudo buscar en directores:', errorDirectores);
      }

      // 4. Usuario no encontrado en backend
      console.warn('⚠️ Usuario no encontrado en backend');
      return { 
        exito: false, 
        mensaje: 'Usuario no encontrado. Verifica tu email o regístrate.' 
      };

    } catch (error) {
      console.error('❌ Error en login:', error);
      return { 
        exito: false, 
        mensaje: 'Error al conectar con el servidor. Verifica que el backend esté corriendo en puerto 8080.' 
      };
    }
  }

  /**
   * REGISTER - Crea un nuevo estudiante en el backend
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

      // 1. Verificar que el email no exista
      const dtoEstudiantes = await firstValueFrom(
        this.api.get<DtoEstudiante[]>('/estudiantes', false)
      );
      
      if (dtoEstudiantes.some(e => e.email === datos.email)) {
        return { exito: false, mensaje: 'El email ya está registrado. Intenta con otro.' };
      }

      // 2. Obtener la carrera (necesitamos el DTO completo)
      const dtoCarreras = await firstValueFrom(
        this.api.get<any[]>('/carreras', false)
      );
      
      // No necesitamos buscar carrera porque el backend no la acepta en DtoEstudiante
      
      // 3. Crear el DTO del estudiante
      const nuevoEstudianteDto: DtoEstudiante = {
        codigo: datos.codigoEstudiante,
        nombre: `${datos.nombre} ${datos.apellido}`,
        email: datos.email
        // ⚠️ Backend no incluye carrera en DtoEstudiante
      };

      // 4. Enviar al backend
      const estudianteCreado = await firstValueFrom(
        this.api.post<DtoEstudiante>('/estudiantes', nuevoEstudianteDto)
      );

      console.log('✅ Estudiante creado en backend:', estudianteCreado.nombre);

      // 5. Convertir y guardar
      const estudiante = this.mappers.dtoToEstudiante(estudianteCreado);
      this.usuarioActual.set(estudiante);
      this.guardarUsuario(estudiante);

      // 6. Redirigir al dashboard
      this.router.navigate(['/dashboard']);

      return { 
        exito: true, 
        mensaje: `¡Registro exitoso! Bienvenido/a ${estudiante.nombre}. Tu cuenta fue creada en el backend.` 
      };

    } catch (error: any) {
      console.error('❌ Error en register:', error);
      
      // Manejar error de código duplicado
      if (error.message && (error.message.includes('409') || error.message.includes('duplicado'))) {
        return { 
          exito: false, 
          mensaje: 'El código de estudiante ya está registrado. Usa otro código.' 
        };
      }
      
      return { 
        exito: false, 
        mensaje: 'Error al registrar en el backend. Verifica que el backend esté corriendo.' 
      };
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
   * Guarda el usuario en localStorage (persistencia)
   */
  private guardarUsuario(usuario: Usuario): void {
    // ⚠️ NO guardar password en localStorage
    const usuarioSinPassword = { ...usuario };
    delete (usuarioSinPassword as any).password;
    
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(usuarioSinPassword));
    console.log('💾 Usuario guardado en localStorage');
  }

  /**
   * Carga el usuario desde localStorage al iniciar
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
   * Verifica si el backend está disponible
   */
  async verificarBackend(): Promise<boolean> {
    return await this.api.verificarConexion();
  }
}
