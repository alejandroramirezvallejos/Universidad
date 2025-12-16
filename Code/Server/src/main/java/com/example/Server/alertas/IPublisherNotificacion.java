package com.example.Server.alertas;
import com.example.Server.modelos.NotificacionEvento;

public interface IPublisherNotificacion {
    void suscribir(IObservador observador);
    void desuscribir(IObservador observador);
    void notificar(NotificacionEvento evento);
}

