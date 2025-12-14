package com.example.Server.controladores;

import com.example.Server.dtos.*;
import com.example.Server.servicios.ServicioAutenticacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para autenticación simple
 * Endpoints: login, registro estudiante, registro docente
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAutenticacion {

    @Autowired
    private ServicioAutenticacion servicioAutenticacion;

    /**
     * POST /api/auth/login
     * Login simple con email y password
     */
    @PostMapping("/login")
    public ResponseEntity<DtoRespuestaLogin> login(@RequestBody DtoCredenciales credenciales) {
        DtoRespuestaLogin respuesta = servicioAutenticacion.login(credenciales);
        
        if (respuesta.isExito()) {
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.status(401).body(respuesta);
        }
    }

    /**
     * POST /api/auth/registro/estudiante
     * Registro de nuevo estudiante
     */
    @PostMapping("/registro/estudiante")
    public ResponseEntity<DtoRespuestaRegistro> registrarEstudiante(
            @RequestBody DtoRegistroEstudiante dto) {
        
        DtoRespuestaRegistro respuesta = servicioAutenticacion.registrarEstudiante(dto);
        
        if (respuesta.isExito()) {
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.badRequest().body(respuesta);
        }
    }

    /**
     * POST /api/auth/registro/docente
     * Registro de nuevo docente
     */
    @PostMapping("/registro/docente")
    public ResponseEntity<DtoRespuestaRegistro> registrarDocente(
            @RequestBody DtoRegistroDocente dto) {
        
        DtoRespuestaRegistro respuesta = servicioAutenticacion.registrarDocente(dto);
        
        if (respuesta.isExito()) {
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.badRequest().body(respuesta);
        }
    }
}
