/**
 * Auth Guard
 * Protege las rutas que requieren autenticación
 * Redirige a login si el usuario no está autenticado
 */

import { inject } from '@angular/core';
import { Router, type CanActivateFn } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.estaAutenticado()) {
    return true;
  }

  // Guardar la URL que intentó acceder para redirigir después del login
  router.navigate(['/login'], {
    queryParams: { returnUrl: state.url }
  });
  
  return false;
};

/**
 * Guard para verificar roles específicos
 */
export const roleGuard: (roles: string[]) => CanActivateFn = (roles: string[]) => {
  return (route, state) => {
    const authService = inject(AuthService);
    const router = inject(Router);

    if (!authService.estaAutenticado()) {
      router.navigate(['/login']);
      return false;
    }

    const userRole = authService.rol();
    if (userRole && roles.includes(userRole)) {
      return true;
    }

    // Redirigir a dashboard si no tiene permiso
    router.navigate(['/dashboard']);
    return false;
  };
};
