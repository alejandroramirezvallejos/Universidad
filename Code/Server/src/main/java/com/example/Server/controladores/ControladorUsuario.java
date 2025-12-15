package com.example.Server.controladores;
import com.example.Server.dtos.DtoActualizarUsuario;
import com.example.Server.dtos.DtoUsuarioCompleto;
import com.example.Server.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorUsuario {
    @Autowired
    private ServicioUsuario servicio;

    @GetMapping("/{codigo}")
    public ResponseEntity<DtoUsuarioCompleto> obtenerUsuario(@PathVariable String codigo) {
        DtoUsuarioCompleto usuario = servicio.obtenerPorCodigo(codigo);

        if (usuario == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<DtoUsuarioCompleto> actualizarUsuario(
            @PathVariable String codigo,
            @RequestBody DtoActualizarUsuario dto) {
        
        DtoUsuarioCompleto usuario = servicio.actualizar(codigo, dto);

        if (usuario == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }
}
