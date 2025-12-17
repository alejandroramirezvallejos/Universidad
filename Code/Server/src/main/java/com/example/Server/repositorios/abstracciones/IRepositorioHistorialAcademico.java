package com.example.Server.repositorios.abstracciones;
import com.example.Server.modelos.abstracciones.IHistorialAcademico;
import java.util.List;

public interface IRepositorioHistorialAcademico {
    IHistorialAcademico guardar(IHistorialAcademico historial);
    List<IHistorialAcademico> getHistoriales();
    void eliminar(IHistorialAcademico historial);
    IHistorialAcademico buscar(String codigoEstudiante);
}

