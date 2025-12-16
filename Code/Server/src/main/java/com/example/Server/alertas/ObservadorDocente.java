package com.example.Server.alertas;
import com.example.Server.modelos.NotificacionEvento;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObservadorDocente implements IObservador {
    private final PublisherNotificacion sujeto;

    @PostConstruct
    public void suscribir() {
        sujeto.suscribir(this);
    }

    @PreDestroy
    public void desuscribir() {
        sujeto.desuscribir(this);
    }

    @Override
    public void actualizar(NotificacionEvento evento) {
        String mensaje = evento.getNotaFinal() >= 51.0 ? "APROBADO" : "REPROBADO";
        System.out.println("NOTIFICACIÓN A DOCENTE:");
        System.out.println("El estudiante " + evento.getEstudiante().getNombre() + " " + evento.getEstudiante().getApellido());
        System.out.println("Ha obtenido una nota de " + evento.getNotaFinal() + " en " + evento.getMateria().getNombre());
        System.out.println("Mensaje: " + mensaje);
        System.out.println("----------------------------------------");
    }
}
