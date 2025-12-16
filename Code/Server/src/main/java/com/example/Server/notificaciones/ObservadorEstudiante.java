package com.example.Server.notificaciones;
import com.example.Server.modelos.NotificacionEvento;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObservadorEstudiante implements IObservador {
    private final IPublicadorDeNotificaciones publicadorDeNotificaciones;

    @PostConstruct
    public void suscribir() {
        publicadorDeNotificaciones.suscribir(this);
    }

    @PreDestroy
    public void desuscribir() {
        publicadorDeNotificaciones.desuscribir(this);
    }

    @Override
    public boolean debeNotificar(NotificacionEvento evento) {
        return evento.getEstudiante() != null;
    }

    @Override
    public void actualizar(NotificacionEvento evento) {
        String estado = evento.getNotaFinal() >= 51.0 ? "APROBADO" : "REPROBADO";
        System.out.println("NOTIFICACIÓN A ESTUDIANTE:");
        System.out.println("Hola " + evento.getEstudiante().getNombre() + ", " + estado);
        System.out.println("Materia: " + evento.getMateria().getNombre());
        System.out.println("Nota: " + evento.getNotaFinal());
        System.out.println("----------------------------------------");
    }
}
