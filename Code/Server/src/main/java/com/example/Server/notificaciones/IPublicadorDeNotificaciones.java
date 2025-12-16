package com.example.Server.notificaciones;
import com.example.Server.modelos.NotificacionEvento;

public interface IPublicadorDeNotificaciones {
    void suscribir(IObservador observador);
    void desuscribir(IObservador observador);
    void notificar(NotificacionEvento evento);
}

