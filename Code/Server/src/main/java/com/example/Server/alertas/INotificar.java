package com.example.Server.alertas;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;

public interface INotificar {
    void notificar(Estudiante estudiante, Materia materia, Double notaFinal);
}

