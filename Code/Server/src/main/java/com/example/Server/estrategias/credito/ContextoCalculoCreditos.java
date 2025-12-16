package com.example.Server.estrategias.credito;
import com.example.Server.modelos.ActaEstudiante;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.List;

@Setter
@Component
public class ContextoCalculoCreditos {
    private IEstrategiaCalculoCredito estrategia;

    public double calcular(List<ActaEstudiante> actas) {
        return estrategia.calcular(actas);
    }
}

