package com.example.Server.notificaciones;

import com.example.Server.modelos.abstracciones.INotificacion;

public interface IPublicadorDeNotificaciones {
    void suscribir(IObservador observador);
    void desuscribir(IObservador observador);
    void notificar(INotificacion evento);
}
