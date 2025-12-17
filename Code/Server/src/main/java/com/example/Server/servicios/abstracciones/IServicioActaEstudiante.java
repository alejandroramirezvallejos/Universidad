package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import java.util.List;

public interface IServicioActaEstudiante {
    IActaEstudiante crear(IEstudiante estudiante, IParaleloMateria paralelo);
    List<IActaEstudiante> getActas();
    List<IActaEstudiante> getActasReprobadas();
    List<IActaEstudiante> getActasAprobadas();
    void eliminar(IActaEstudiante acta);
}
