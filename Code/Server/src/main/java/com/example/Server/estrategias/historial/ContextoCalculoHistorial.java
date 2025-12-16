package com.example.Server.estrategias.historial;
import com.example.Server.modelos.ActaEstudiante;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.List;

@Setter
@Component
public class ContextoCalculoHistorial {
    private IEstrategiaCalculoHistorial estrategia;

    public double ejecutar(List<ActaEstudiante> actas) {
        return estrategia.calcular(actas);
    }
}

