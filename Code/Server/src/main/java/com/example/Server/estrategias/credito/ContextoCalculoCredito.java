package com.example.Server.estrategias.credito;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ContextoCalculoCredito {
    private IEstrategiaCalculoCredito estrategia;

    public void setEstrategia(IEstrategiaCalculoCredito estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(List<IActaEstudiante> actas) {
        if (estrategia == null)
            throw new RuntimeException("No se ha establecido una estrategia de cálculo");

        return estrategia.calcular(actas);
    }
}

