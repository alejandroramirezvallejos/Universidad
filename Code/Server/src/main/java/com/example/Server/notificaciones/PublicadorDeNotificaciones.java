package com.example.Server.notificaciones;

import com.example.Server.modelos.abstracciones.INotificacion;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PublicadorDeNotificaciones implements IPublicadorDeNotificaciones {
    private final List<IObservador> observadores = new ArrayList<>();

    @Override
    public void suscribir(IObservador observador) {
        observadores.add(observador);
    }

    @Override
    public void desuscribir(IObservador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificar(INotificacion evento) {
        for (IObservador observador : observadores)
            if (observador.debeNotificar(evento))
                observador.actualizar(evento);
    }
}
