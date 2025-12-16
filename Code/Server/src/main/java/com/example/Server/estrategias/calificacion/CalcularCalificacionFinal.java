package com.example.Server.estrategias.calificacion;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.servicios.ServicioCalificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CalcularCalificacionFinal implements IEstrategiaCalculoCalificacion {
    private final ServicioCalificacion servicioCalificacion;

    @Override
    public double calcular(Estudiante estudiante, ParaleloMateria paralelo, List<Evaluacion> evaluaciones) {
        double notaFinal = 0.0;

        for (Evaluacion evaluacion : evaluaciones) {
            if (!evaluacion.getParaleloMateria().getCodigo().equals(paralelo.getCodigo()))
                continue;

            double valorCalificacion = servicioCalificacion.getCalificacionesEnEvaluacion(estudiante, evaluacion);
            notaFinal += valorCalificacion * (evaluacion.getPorcentaje() / 100.0);
        }

        return notaFinal;
    }
}
