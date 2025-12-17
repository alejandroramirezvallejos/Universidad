package com.example.Server.estrategias.actas;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ContextoCalculoPromedioActas {
    private IEstrategiaCalculoPromedioActas estrategia;

    public void setEstrategia(IEstrategiaCalculoPromedioActas estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(List<IActaEstudiante> actas) {
        if (estrategia == null)
            throw new RuntimeException("No se ha establecido una estrategia de cálculo");

        return estrategia.calcular(actas);
    }
}

