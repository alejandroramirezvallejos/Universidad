package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.IGestion;
import com.example.Server.servicios.abstracciones.IServicioOfertaAcademica;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oferta-academica")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorOfertaAcademica {
    private final IServicioOfertaAcademica servicio;

    @GetMapping("/gestion/{codigo}")
    public ResponseEntity<IGestion> getOfertaAcademica(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.getOfertaPorGestion(codigo));
    }
}
