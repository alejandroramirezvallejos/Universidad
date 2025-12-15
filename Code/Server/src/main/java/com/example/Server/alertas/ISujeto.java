package com.example.Server.alertas;

public interface ISujeto {
    void suscribir(IObservador observador);
    void desuscribir(IObservador observador);
    void notificar(NotificacionEvento evento);
}

