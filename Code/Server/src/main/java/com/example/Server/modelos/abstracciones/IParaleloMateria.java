package com.example.Server.modelos.abstracciones;
import com.example.Server.modelos.implementaciones.*;
import java.util.List;

public interface IParaleloMateria {
    String getCodigo();
    void setCodigo(String codigo);
    Materia getMateria();
    void setMateria(Materia materia);
    Docente getDocente();
    void setDocente(Docente docente);
    Aula getAula();
    void setAula(Aula aula);
    Gestion getGestion();  
    void setGestion(Gestion gestion);  
    Integer getCupoMaximo();
    void setCupoMaximo(Integer cupoMaximo);
    List<IEstudiante> getEstudiantes();
    void setEstudiantes(List<IEstudiante> estudiantes);
    List<Horario> getHorarios();  
    void setHorarios(List<Horario> horarios);  
}
