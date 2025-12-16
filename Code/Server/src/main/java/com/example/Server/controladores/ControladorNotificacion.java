package com.example.Server.controladores;
import com.example.Server.modelos.NotificacionEvento;
import com.example.Server.servicios.ServicioActaEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorNotificacion {
    private final ServicioActaEstudiante servicioActa;

    @PostMapping
    public ResponseEntity<Void> notificar(@RequestBody NotificacionEvento evento) {
        servicioActa.notificar(evento.getEstudiante(), evento.getMateria(), evento.getNotaFinal());
        return ResponseEntity.ok().build();
    }
}

