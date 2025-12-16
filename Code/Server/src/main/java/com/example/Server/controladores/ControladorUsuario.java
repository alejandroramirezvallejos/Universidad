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
        Usuario usuario = servicio.obtenerPorCodigo(codigo);

        if (usuario == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Usuario> actualizar(
            @PathVariable String codigo,
            @RequestBody Map<String, Object> datos) {

        Usuario usuario = servicio.actualizar(codigo, datos);

        if (usuario == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }
}
