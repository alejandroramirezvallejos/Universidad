package com.example.Server.controladores;
import com.example.Server.modelos.implementaciones.Notificacion;
import com.example.Server.notificaciones.IPublicadorDeNotificaciones;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorNotificacion {
    private final IPublicadorDeNotificaciones publicador;

    @PostMapping
    public ResponseEntity<Void> notificar(@RequestBody Notificacion evento) {
        publicador.notificar(evento);
        return ResponseEntity.ok().build();
    }
}
