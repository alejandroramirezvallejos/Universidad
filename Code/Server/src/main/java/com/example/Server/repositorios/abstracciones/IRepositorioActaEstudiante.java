package com.example.Server.repositorios.abstracciones;

import com.example.Server.modelos.abstracciones.IActaEstudiante;
import java.util.List;

public interface IRepositorioActaEstudiante {
    IActaEstudiante guardar(IActaEstudiante acta);
    List<IActaEstudiante> getActas();
    void eliminar(IActaEstudiante acta);
    List<IActaEstudiante> buscarPorEstudiante(String codigoEstudiante);
}
