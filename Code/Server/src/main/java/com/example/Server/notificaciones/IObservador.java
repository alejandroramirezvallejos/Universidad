package com.example.Server.notificaciones;
import com.example.Server.modelos.NotificacionEvento;

public interface IObservador {
    void actualizar(NotificacionEvento evento);
    boolean debeNotificar(NotificacionEvento evento);
}
