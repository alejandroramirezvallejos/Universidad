package com.example.Server.controladores;

import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestión de usuarios (perfil)
 * Endpoints: obtener perfil, actualizar perfil
 */
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorUsuario {

    @Autowired
    private ServicioUsuario servicioUsuario;

    /**
     * GET /api/usuarios/{codigo}
     * Obtiene los datos completos de un usuario
     */
    @GetMapping("/{codigo}")
    public ResponseEntity<DtoUsuarioCompleto> obtenerUsuario(@PathVariable String codigo) {
        DtoUsuarioCompleto usuario = servicioUsuario.obtenerPorCodigo(codigo);
        
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(usuario);
    }

    /**
     * PUT /api/usuarios/{codigo}
     * Actualiza los datos de un usuario
     */
    @PutMapping("/{codigo}")
    public ResponseEntity<DtoUsuarioCompleto> actualizarUsuario(
            @PathVariable String codigo,
            @RequestBody DtoActualizarUsuario dto) {
        
        DtoUsuarioCompleto usuario = servicioUsuario.actualizar(codigo, dto);
        
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(usuario);
    }
}
