package com.example.Server.modelos.abstracciones;
import java.util.List;

public interface ICarrera {
    String getNombre();
    void setNombre(String nombre);
    String getCodigo();
    void setCodigo(String codigo);
    List<IEstudiante> getEstudiantes();
    void setEstudiantes(List<IEstudiante> estudiantes);
    IDirectorCarrera getDirector();
    void setDirector(IDirectorCarrera director);
    List<IMateria> getMaterias();
    void setMaterias(List<IMateria> materias);
}
