package com.example.Server.alertas;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.NotificacionEvento;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PublisherNotificacion implements IPublisherNotificacion {
    private final List<IObservador> observadores = new ArrayList<>();

    public void suscribir(IObservador observador) {
        observadores.add(observador);
    }

    public void desuscribir(IObservador observador) {
        observadores.remove(observador);
    }

    public void notificar(NotificacionEvento notificacion) {
        for (IObservador observador : observadores)
            observador.actualizar(notificacion);
    }

    public void notificar(Estudiante estudiante, Materia materia, Double notaFinal) {
        NotificacionEvento evento = new NotificacionEvento(estudiante, materia, notaFinal);
        notificar(evento);
    }
}
