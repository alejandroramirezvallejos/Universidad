package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.AUsuario;
import com.example.Server.servicios.abstracciones.IServicioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorUsuario {
    private final IServicioUsuario servicio;

    @GetMapping("/{codigo}")
    public ResponseEntity<AUsuario> getUsuario(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.getUsuario(codigo));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<AUsuario> actualizar(@PathVariable String codigo, @RequestBody Map<String, Object> datos) {
        return ResponseEntity.ok(servicio.actualizar(codigo, datos));
    }
}
