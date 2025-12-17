package com.example.Server.estrategias.calificacion;

import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CalcularCalificacionPromedio implements IEstrategiaCalculoCalificacion {
    @Override
    public double calcular(IEstudiante estudiante, IParaleloMateria paralelo, List<IEvaluacion> evaluaciones) {
        double suma = 0.0;
        int contador = 0;

        for (IEvaluacion evaluacion : evaluaciones) {
            if (!evaluacion.getParaleloMateria().getCodigo().equals(paralelo.getCodigo()))
                continue;

            if (evaluacion.getCalificaciones() == null)
                continue;

            for (ICalificacion calificacion : evaluacion.getCalificaciones()) {
                if (calificacion.getEstudiante().getCodigo().equals(estudiante.getCodigo())) {
                    suma += calificacion.getValor();
                    ++contador;
                }
            }
        }

        return contador > 0 ? suma / contador : 0.0;
    }

    public double calcular(List<IActaEstudiante> actas) {
        if (actas.isEmpty())
            return 0.0;

        double suma = 0.0;

        for (IActaEstudiante acta : actas)
            suma += acta.getCalificacionFinal();

        return suma / actas.size();
    }
}
