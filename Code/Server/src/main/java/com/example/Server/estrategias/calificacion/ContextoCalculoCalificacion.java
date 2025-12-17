package com.example.Server.estrategias.calificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.List;

@Setter
@Component
public class ContextoCalculoCalificacion {
    private IEstrategiaCalculoCalificacion estrategia;

    public double calcular(IEstudiante estudiante, IParaleloMateria paralelo, List<IEvaluacion> evaluaciones) {
        return estrategia.calcular(estudiante, paralelo, evaluaciones);
    }
}
