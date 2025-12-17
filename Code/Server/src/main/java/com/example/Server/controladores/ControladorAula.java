package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.IAula;
import com.example.Server.modelos.implementaciones.Aula;
import com.example.Server.servicios.abstracciones.IServicioAula;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/aulas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorAula {
    private final IServicioAula servicio;

    @PostMapping
    public ResponseEntity<IAula> crear(@RequestBody Aula aula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(aula));
    }

    @GetMapping
    public ResponseEntity<List<IAula>> getAulas() {
        return ResponseEntity.ok(servicio.getAulas());
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Aula aula) {
        servicio.eliminar(aula);
        return ResponseEntity.ok().build();
    }
}
