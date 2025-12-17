package com.example.Server.notificaciones;

import com.example.Server.modelos.abstracciones.INotificacion;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObservadorReprobacion implements IObservador {
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
        return evento.getEstudiante() != null && evento.getNotaFinal() < 51.0;
    }

    @Override
    public void actualizar(INotificacion evento) {
        System.out.println("ALERTA DE REPROBACIÓN:");
        System.out.println("Estudiante: " + evento.getEstudiante().getNombre() + " " + evento.getEstudiante().getApellido());
        System.out.println("Materia: " + evento.getMateria().getNombre());
        System.out.println("Nota: " + evento.getNotaFinal());
        System.out.println("Estado: REPROBADO - Requiere atención");
        System.out.println("----------------------------------------");
    }
}
