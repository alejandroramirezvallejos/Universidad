package com.example.Server.estrategias.calificacion;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.modelos.ParaleloMateria;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.List;

@Setter
@Component
public class ContextoCalculoCalificacion {
    private IEstrategiaCalculoCalificacion estrategia;

    public double calcular(Estudiante estudiante, ParaleloMateria paralelo, List<Evaluacion> evaluaciones) {
        return estrategia.calcular(estudiante, paralelo, evaluaciones);
    }
}

