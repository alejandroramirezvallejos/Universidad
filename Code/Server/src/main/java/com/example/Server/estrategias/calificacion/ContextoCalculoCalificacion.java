package com.example.Server.estrategias.calificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ContextoCalculoCalificacion {
    private IEstrategiaCalculoCalificacion estrategia;

    public void setEstrategia(IEstrategiaCalculoCalificacion estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(IEstudiante estudiante, IParaleloMateria paralelo, List<IEvaluacion> evaluaciones) {
        if (estrategia == null)
            throw new RuntimeException("No se ha establecido una estrategia de cálculo");

        return estrategia.calcular(estudiante, paralelo, evaluaciones);
    }
}

