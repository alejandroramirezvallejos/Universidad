package com.example.Server.modelos.abstracciones;
import com.example.Server.modelos.implementaciones.Carrera;
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
    Boolean isActiva(); 
    void setActiva(Boolean activa); 
    List<IMateria> getMateriasCorrelativas();
    void setMateriasCorrelativas(List<IMateria> materiasCorrelativas);
    List<IParaleloMateria> getParaleloMaterias();
    void setParaleloMaterias(List<IParaleloMateria> paraleloMaterias);
    Carrera getCarrera();
    void setCarrera(Carrera carrera);
    IMateria clonar();
}
