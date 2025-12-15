package com.example.Server.alertas;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObservadorEstudiante implements IObservador {
    private final ContextoNotificacion sujeto;

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
        System.out.println("NOTIFICACIÓN A ESTUDIANTE:");
        System.out.println("Hola " + evento.getEstudiante().getNombre() + ", " + mensaje);
        System.out.println("Materia: " + evento.getMateria().getNombre());
        System.out.println("Nota: " + evento.getNotaFinal());
        System.out.println("----------------------------------------");
    }
}
