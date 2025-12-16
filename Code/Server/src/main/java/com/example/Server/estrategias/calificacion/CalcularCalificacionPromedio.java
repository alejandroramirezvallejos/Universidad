package com.example.Server.estrategias.calificacion;
import com.example.Server.modelos.ActaEstudiante;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CalcularCalificacionPromedio implements IEstrategiaCalculoCalificacion {
    @Override
    public double calcular(Estudiante estudiante, ParaleloMateria paralelo, List<Evaluacion> evaluaciones) {
        double suma = 0.0;
        int contador = 0;

        for (Evaluacion evaluacion : evaluaciones) {
            if (!evaluacion.getParaleloMateria().getCodigo().equals(paralelo.getCodigo()))
                continue;

            if (evaluacion.getCalificaciones() == null)
                continue;

            for (var calificacion : evaluacion.getCalificaciones()) {
                if (calificacion.getEstudiante().getCodigo().equals(estudiante.getCodigo())) {
                    suma += calificacion.getValor();
                    ++contador;
                }
            }
        }

        return contador > 0 ? suma / contador : 0.0;
    }

    public double calcular(List<ActaEstudiante> actas) {
        if (actas.isEmpty())
            return 0.0;

        double suma = 0.0;

        for (ActaEstudiante acta : actas)
            suma += acta.getCalificacionFinal();

        return suma / actas.size();
    }
}
