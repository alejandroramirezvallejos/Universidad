package com.example.Server.modelos.abstracciones;

public interface INotificacion {
    IEstudiante getEstudiante();
    void setEstudiante(IEstudiante estudiante);
    IMateria getMateria();
    void setMateria(IMateria materia);
    Double getNotaFinal();
    void setNotaFinal(Double notaFinal);
}
