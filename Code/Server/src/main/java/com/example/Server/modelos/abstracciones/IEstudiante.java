package com.example.Server.modelos.abstracciones;
import java.util.List;

public interface IEstudiante extends IUsuario {
    int getSemestre();
    void setSemestre(int semestre);
    ICarrera getCarrera();
    void setCarrera(ICarrera carrera);
    List<IMateria> getMateriasInscritas();
    void setMateriasInscritas(List<IMateria> materiasInscritas);
    List<IMateria> getMateriasAprobadas();
    void setMateriasAprobadas(List<IMateria> materiasAprobadas);
}
