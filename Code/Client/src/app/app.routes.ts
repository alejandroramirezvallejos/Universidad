/**
 * Configuración de Rutas de la Aplicación
 *
 * Heurística Nielsen #2: Correspondencia sistema-mundo real
 * - URLs claras y descriptivas
 *
 * Implementa:
 * - Lazy loading para mejor rendimiento
 * - Guards para protección de rutas
 * - Redirecciones inteligentes
 */

import { Routes } from '@angular/router';
import { authGuard, roleGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  // Ruta por defecto - redirige al login (el authGuard se encarga del resto)
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

  // Login (pública)
  {
    path: 'login',
    loadComponent: () => import('./features/auth/login.component')
      .then(m => m.LoginComponent),
    title: 'Iniciar Sesión - Universidad'
  },

  // Registro (pública)
  {
    path: 'registro',
    loadComponent: () => import('./features/auth/registro.component')
      .then(m => m.RegistroComponent),
    title: 'Registrarse - Universidad'
  },

  // Rutas protegidas con layout principal
  {
    path: '',
    loadComponent: () => import('./shared/components/layout/main-layout.component')
      .then(m => m.MainLayoutComponent),
    canActivate: [authGuard], // Proteger todas las rutas hijas
    children: [
      // Dashboard (todos los roles)
      {
        path: 'dashboard',
        loadComponent: () => import('./features/dashboard/dashboard.component')
          .then(m => m.DashboardComponent),
        title: 'Inicio - Universidad'
      },

      // Perfil (todos los roles)
      {
        path: 'perfil',
        loadComponent: () => import('./features/perfil/perfil.component')
          .then(m => m.PerfilComponent),
        title: 'Mi Perfil - Universidad'
      },

      // Oferta Académica (Estudiantes)
      {
        path: 'oferta-academica',
        loadComponent: () => import('./features/oferta-academica/oferta-academica.component')
          .then(m => m.OfertaAcademicaComponent),
        canActivate: [roleGuard(['ESTUDIANTE'])],
        title: 'Oferta Académica - Universidad'
      },

      // Matrícula (Estudiantes)
      {
        path: 'matricula',
        loadComponent: () => import('./features/matricula/matricula.component')
          .then(m => m.MatriculaComponent),
        canActivate: [roleGuard(['ESTUDIANTE'])],
        title: 'Mi Matrícula - Universidad'
      },

      // Calificaciones (Estudiantes y Docentes)
      {
        path: 'calificaciones',
        loadComponent: () => import('./features/calificaciones/calificaciones.component')
          .then(m => m.CalificacionesComponent),
        canActivate: [roleGuard(['ESTUDIANTE', 'DOCENTE'])],
        title: 'Calificaciones - Universidad'
      },

      // Historial Académico (Estudiantes)
      {
        path: 'historial',
        loadComponent: () => import('./features/historial/historial.component')
          .then(m => m.HistorialComponent),
        canActivate: [roleGuard(['ESTUDIANTE'])],
        title: 'Historial Académico - Universidad'
      },

      // Horario (Estudiantes y Docentes)
      {
        path: 'horario',
        loadComponent: () => import('./features/horario/horario.component')
          .then(m => m.HorarioComponent),
        canActivate: [roleGuard(['ESTUDIANTE', 'DOCENTE'])],
        title: 'Mi Horario - Universidad'
      },

      // Gestión de Períodos (Director)
      {
        path: 'gestiones',
        loadComponent: () => import('./features/gestiones/gestiones.component')
          .then(m => m.GestionesComponent),
        canActivate: [roleGuard(['DIRECTOR'])],
        title: 'Gestión de Períodos - Universidad'
      },

      // Gestión de Grupos (Director)
      {
        path: 'gestion-grupos',
        loadComponent: () => import('./features/gestion-grupos/gestion-grupos.component')
          .then(m => m.GestionGruposComponent),
        canActivate: [roleGuard(['DIRECTOR'])],
        title: 'Gestión de Grupos - Universidad'
      },

      // Gestión de Materias (Director)
      {
        path: 'gestion-materias',
        loadComponent: () => import('./features/gestion-materias/gestion-materias.component')
          .then(m => m.GestionMateriasComponent),
        canActivate: [roleGuard(['DIRECTOR'])],
        title: 'Gestión de Materias - Universidad'
      },

      // Gestión de Estudiantes (Director)
      {
        path: 'gestion-estudiantes',
        loadComponent: () => import('./features/gestion-estudiantes/gestion-estudiantes.component')
          .then(m => m.GestionEstudiantesComponent),
        canActivate: [roleGuard(['DIRECTOR'])],
        title: 'Gestión de Estudiantes - Universidad'
      },

      // Gestión de Docentes (Director)
      {
        path: 'gestion-docentes',
        loadComponent: () => import('./features/gestion-docentes/gestion-docentes.component')
          .then(m => m.GestionDocentesComponent),
        canActivate: [roleGuard(['DIRECTOR'])],
        title: 'Gestión de Docentes - Universidad'
      },

      // Gestión de Aulas (Director)
      {
        path: 'gestion-aulas',
        loadComponent: () => import('./features/gestion-aulas/gestion-aulas.component')
          .then(m => m.GestionAulasComponent),
        canActivate: [roleGuard(['DIRECTOR'])],
        title: 'Gestión de Aulas - Universidad'
      },

      // Reportes (Director)
      {
        path: 'reportes',
        loadComponent: () => import('./features/reportes/reportes.component')
          .then(m => m.ReportesComponent),
        canActivate: [roleGuard(['DIRECTOR'])],
        title: 'Reportes - Universidad'
      },

      // Test Observer
      {
        path: 'test-observer',
        loadComponent: () => import('./features/test-observer/test-observer.component')
          .then(m => m.TestObserverComponent),
        title: 'Prueba Observer - Universidad'
      },
    ]
  },

  // Ruta 404 - página no encontrada
  {
    path: '**',
    redirectTo: 'dashboard'
  }
];
