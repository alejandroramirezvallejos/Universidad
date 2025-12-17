package com.example.Server.notificaciones;

import com.example.Server.modelos.abstracciones.INotificacion;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObservadorDocente implements IObservador {
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
    public boolean debeNotificar(INotificacion evento) {
        return evento.getEstudiante() != null && evento.getMateria() != null;
    }

    @Override
    public void actualizar(INotificacion evento) {
        String estado = evento.getNotaFinal() >= 51.0 ? "APROBADO" : "REPROBADO";
        System.out.println("NOTIFICACIÓN A DOCENTE:");
        System.out.println("El estudiante " + evento.getEstudiante().getNombre() + " " + evento.getEstudiante().getApellido());
        System.out.println("Ha obtenido una nota de " + evento.getNotaFinal() + " en " + evento.getMateria().getNombre());
        System.out.println("Estado: " + estado);
        System.out.println("----------------------------------------");
    }
}
