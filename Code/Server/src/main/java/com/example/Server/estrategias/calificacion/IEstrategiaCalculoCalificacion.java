package com.example.Server.estrategias.calificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import java.util.List;

public interface IEstrategiaCalculoCalificacion {
    double calcular(IEstudiante estudiante, IParaleloMateria paralelo, List<IEvaluacion> evaluaciones);
}
