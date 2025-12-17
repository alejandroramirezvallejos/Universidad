package com.example.Server.controladores;
import com.example.Server.modelos.Usuario;
import com.example.Server.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorUsuario {
    @Autowired
    private ServicioUsuario servicio;

    @GetMapping("/{codigo}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String codigo) {
        return ResponseEntity.ok(servicio.getUsuarioPorCodigo(codigo));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Usuario> actualizar(@PathVariable String codigo, @RequestBody Map<String, Object> datos) {
        return ResponseEntity.ok(servicio.actualizar(codigo, datos));
    }
}
