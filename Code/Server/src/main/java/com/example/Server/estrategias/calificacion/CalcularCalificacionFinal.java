package com.example.Server.estrategias.calificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.servicios.abstracciones.IServicioCalificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CalcularCalificacionFinal implements IEstrategiaCalculoCalificacion {
    private final IServicioCalificacion servicioCalificacion;

    @Override
    public double calcular(IEstudiante estudiante, IParaleloMateria paralelo, List<IEvaluacion> evaluaciones) {
        double notaFinal = 0.0;

        for (IEvaluacion evaluacion : evaluaciones) {
            if (!evaluacion.getParaleloMateria().getCodigo().equals(paralelo.getCodigo()))
                continue;

            double valorCalificacion = servicioCalificacion.getCalificacionesEnEvaluacion(estudiante, evaluacion);
            notaFinal += valorCalificacion * (evaluacion.getPorcentaje() / 100.0);
        }

        return notaFinal;
    }
}
