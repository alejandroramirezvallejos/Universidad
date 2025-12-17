package com.example.Server.repositorios.abstracciones;

import com.example.Server.modelos.abstracciones.ICalificacion;
import java.util.List;

public interface IRepositorioCalificacion {
    ICalificacion guardar(ICalificacion calificacion);
    List<ICalificacion> getCalificaciones();
    void eliminar(ICalificacion calificacion);
    List<ICalificacion> buscarPorEstudiante(String codigoEstudiante);
}

