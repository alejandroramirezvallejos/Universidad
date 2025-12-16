package com.example.Server.estrategias.calificacion;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.modelos.ParaleloMateria;
import java.util.List;

public interface IEstrategiaCalculoCalificacion {
    double calcular(Estudiante estudiante, ParaleloMateria paralelo, List<Evaluacion> evaluaciones);
}
