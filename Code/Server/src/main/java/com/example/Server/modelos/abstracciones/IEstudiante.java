package com.example.Server.modelos.abstracciones;
import com.example.Server.modelos.implementaciones.Carrera;
import com.example.Server.modelos.implementaciones.Estudiante;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(as = Estudiante.class)
public interface IEstudiante extends IUsuario {
    int getSemestre();
    void setSemestre(int semestre);
    Carrera getCarrera();
    void setCarrera(Carrera carrera);
    List<IMateria> getMateriasInscritas();
    void setMateriasInscritas(List<IMateria> materiasInscritas);
    List<IMateria> getMateriasAprobadas();
    void setMateriasAprobadas(List<IMateria> materiasAprobadas);
}
