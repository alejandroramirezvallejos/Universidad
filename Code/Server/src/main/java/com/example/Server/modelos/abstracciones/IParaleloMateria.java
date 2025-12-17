package com.example.Server.modelos.abstracciones;

import java.util.List;

public interface IParaleloMateria {
    String getCodigo();
    void setCodigo(String codigo);
    IMateria getMateria();
    void setMateria(IMateria materia);
    IDocente getDocente();
    void setDocente(IDocente docente);
    IAula getAula();
    void setAula(IAula aula);
    IGestion getGestion();
    void setGestion(IGestion gestion);
    Integer getCupoMaximo();
    void setCupoMaximo(Integer cupoMaximo);
    List<IEstudiante> getEstudiantes();
    void setEstudiantes(List<IEstudiante> estudiantes);
    List<IHorario> getHorarios();
    void setHorarios(List<IHorario> horarios);
}
