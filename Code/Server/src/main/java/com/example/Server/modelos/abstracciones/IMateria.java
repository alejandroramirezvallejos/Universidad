package com.example.Server.modelos.abstracciones;

import java.util.List;

public interface IMateria {
    String getCodigo();
    void setCodigo(String codigo);
    String getNombre();
    void setNombre(String nombre);
    Integer getSemestre();
    void setSemestre(Integer semestre);
    Integer getCreditos();
    void setCreditos(Integer creditos);
    boolean isActiva();
    void setActiva(boolean activa);
    List<IMateria> getMateriasCorrelativas();
    void setMateriasCorrelativas(List<IMateria> materiasCorrelativas);
    List<IParaleloMateria> getParaleloMaterias();
    void setParaleloMaterias(List<IParaleloMateria> paraleloMaterias);
    ICarrera getCarrera();
    void setCarrera(ICarrera carrera);
    IMateria clonar();
}
