package com.example.Server.controladores;

import com.example.Server.dtos.DtoOfertaAcademica;
import com.example.Server.servicios.ServicioOfertaAcademica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para oferta académica
 * Endpoint: GET /api/oferta-academica/gestion/{codigo}
 */
@RestController
@RequestMapping("/api/oferta-academica")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorOfertaAcademica {

    @Autowired
    private ServicioOfertaAcademica servicioOfertaAcademica;

    /**
     * GET /api/oferta-academica/gestion/{codigo}
     * Obtiene la oferta académica completa de una gestión
     * Incluye: materias con paralelos, horarios, cupos y docentes
     */
    @GetMapping("/gestion/{codigo}")
    public ResponseEntity<DtoOfertaAcademica> obtenerOfertaPorGestion(@PathVariable String codigo) {
        DtoOfertaAcademica oferta = servicioOfertaAcademica.obtenerOfertaPorGestion(codigo);
        
        if (oferta == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(oferta);
    }
}
