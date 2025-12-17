package com.example.Server.modelos.abstracciones;
import com.example.Server.modelos.implementaciones.*;
import java.util.List;

public interface IParaleloMateria {
    String getCodigo();
    void setCodigo(String codigo);
    Materia getMateria();  // Cambiado de IMateria a Materia
    void setMateria(Materia materia);  // Cambiado de IMateria a Materia
    Docente getDocente();  // Cambiado de IDocente a Docente
    void setDocente(Docente docente);  // Cambiado de IDocente a Docente
    Aula getAula();  // Cambiado de IAula a Aula
    void setAula(Aula aula);  // Cambiado de IAula a Aula
    Gestion getGestion();  // Cambiado de IGestion a Gestion
    void setGestion(Gestion gestion);  // Cambiado de IGestion a Gestion
    Integer getCupoMaximo();
    void setCupoMaximo(Integer cupoMaximo);
    List<IEstudiante> getEstudiantes();
    void setEstudiantes(List<IEstudiante> estudiantes);
    List<Horario> getHorarios();  // Cambiado de IHorario a Horario
    void setHorarios(List<Horario> horarios);  // Cambiado de IHorario a Horario
}
