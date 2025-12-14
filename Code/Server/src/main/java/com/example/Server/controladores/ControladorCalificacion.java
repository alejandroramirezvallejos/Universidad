package com.example.Server.controladores;

import com.example.Server.dtos.DtoCalificacion;
import com.example.Server.modelos.Calificacion;
import com.example.Server.servicios.ServicioCalificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador REST para gestión de Calificaciones
 * Endpoint: /api/calificaciones
 */
@RestController
@RequestMapping("/api/calificaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorCalificacion {
    private final ServicioCalificacion servicio;

    /**
     * Crea una nueva calificación
     * POST /api/calificaciones
     */
    @PostMapping
    public ResponseEntity<DtoCalificacion> crear(@RequestBody DtoCalificacion dto) {
        Calificacion calificacion = castModelo(dto);
        Calificacion creada = servicio.crear(calificacion);
        
        DtoCalificacion respuesta = castDto(creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * Obtiene todas las calificaciones
     * GET /api/calificaciones
     */
    @GetMapping
    public ResponseEntity<List<DtoCalificacion>> getCalificaciones() {
        List<Calificacion> calificaciones = servicio.getCalificaciones();
        List<DtoCalificacion> dtos = new ArrayList<>();

        for (Calificacion calificacion : calificaciones)
            dtos.add(castDto(calificacion));

        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtiene calificaciones de un estudiante
     * GET /api/calificaciones/estudiante/{codigo}
     */
    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<DtoCalificacion>> obtenerPorEstudiante(@PathVariable String estudianteCodigo) {
        List<Calificacion> calificaciones = servicio.obtenerPorEstudiante(estudianteCodigo);
        List<DtoCalificacion> dtos = new ArrayList<>();

        for (Calificacion calificacion : calificaciones)
            dtos.add(castDto(calificacion));

        return ResponseEntity.ok(dtos);
    }

    /**
     * Elimina una calificación
     * DELETE /api/calificaciones
     */
    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody DtoCalificacion dto) {
        Calificacion calificacion = castModelo(dto);
        servicio.eliminar(calificacion);
        return ResponseEntity.ok().build();
    }

    private Calificacion castModelo(DtoCalificacion dto) {
        Calificacion calificacion = new Calificacion();
        calificacion.setEstudiante(dto.getEstudiante());
        calificacion.setValor(dto.getValor());
        calificacion.setObservaciones(dto.getObservaciones());
        
        if (dto.getEvaluacion() != null) {
            calificacion.setEvaluacion(dto.getEvaluacion());
        }
        
        return calificacion;
    }

    private DtoCalificacion castDto(Calificacion calificacion) {
        DtoCalificacion dto = new DtoCalificacion();
        dto.setEstudiante(calificacion.getEstudiante());
        dto.setValor(calificacion.getValor());
        dto.setObservaciones(calificacion.getObservaciones());
        
        if (calificacion.getEvaluacion() != null) {
            dto.setEvaluacion(calificacion.getEvaluacion());
        }
        
        return dto;
    }
}
