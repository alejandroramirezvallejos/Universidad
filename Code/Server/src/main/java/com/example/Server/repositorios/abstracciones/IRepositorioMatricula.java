package com.example.Server.repositorios.abstracciones;

import com.example.Server.modelos.abstracciones.IMatricula;
import java.util.List;

public interface IRepositorioMatricula {
    IMatricula guardar(IMatricula matricula);
    List<IMatricula> getMatriculas();
    void eliminar(IMatricula matricula);
    List<IMatricula> buscarPorEstudiante(String codigoEstudiante);
}

