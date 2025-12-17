package com.example.Server.notificaciones;
import com.example.Server.modelos.abstracciones.INotificacion;

public interface IObservador {
    void actualizar(INotificacion evento);
    boolean debeNotificar(INotificacion evento);
}
