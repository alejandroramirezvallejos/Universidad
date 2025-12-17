package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.IEstudiante;
import java.util.List;

public interface IServicioEstudiante {
    IEstudiante crear(IEstudiante estudiante);
    List<IEstudiante> getEstudiantes();
    void eliminar(IEstudiante estudiante);
}

