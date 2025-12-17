package com.example.Server.repositorios.abstracciones;
import com.example.Server.modelos.abstracciones.IEstudiante;
import java.util.List;

public interface IRepositorioEstudiante {
    IEstudiante guardar(IEstudiante estudiante);
    List<IEstudiante> getEstudiantes();
    void eliminar(IEstudiante estudiante);
    IEstudiante buscarPorCodigo(String codigo);
    IEstudiante buscarPorEmail(String email);
}

