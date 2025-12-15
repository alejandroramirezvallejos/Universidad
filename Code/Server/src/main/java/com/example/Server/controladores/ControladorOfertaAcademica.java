package com.example.Server.controladores;
import com.example.Server.dtos.DtoOfertaAcademica;
import com.example.Server.servicios.ServicioOfertaAcademica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oferta-academica")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorOfertaAcademica {
    @Autowired
    private ServicioOfertaAcademica servicio;

    @GetMapping("/gestion/{codigo}")
    public ResponseEntity<DtoOfertaAcademica> obtenerOfertaPorGestion(@PathVariable String codigo) {
        DtoOfertaAcademica oferta = servicio.obtenerOfertaPorGestion(codigo);

        if (oferta == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(oferta);
    }
}
