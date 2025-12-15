package com.example.Server.controladores;
import com.example.Server.dtos.*;
import com.example.Server.servicios.ServicioAutenticacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAutenticacion {
    @Autowired
    private ServicioAutenticacion servicio;

    @PostMapping("/login")
    public ResponseEntity<DtoRespuestaLogin> login(@RequestBody DtoCredenciales credenciales) {
        DtoRespuestaLogin respuesta = servicio.login(credenciales);

        if (respuesta.isExito())
            return ResponseEntity.ok(respuesta);
        else
            return ResponseEntity.status(401).body(respuesta);
    }

    @PostMapping("/registro/estudiante")
    public ResponseEntity<DtoRespuestaRegistro> registrarEstudiante(
            @RequestBody DtoRegistroEstudiante dto) {
        
        DtoRespuestaRegistro respuesta = servicio.registrarEstudiante(dto);

        if (respuesta.isExito())
            return ResponseEntity.ok(respuesta);
        else
            return ResponseEntity.badRequest().body(respuesta);
    }

    @PostMapping("/registro/docente")
    public ResponseEntity<DtoRespuestaRegistro> registrarDocente(
            @RequestBody DtoRegistroDocente dto) {
        DtoRespuestaRegistro respuesta = servicio.registrarDocente(dto);

        if (respuesta.isExito())
            return ResponseEntity.ok(respuesta);
        else
            return ResponseEntity.badRequest().body(respuesta);
    }
}
