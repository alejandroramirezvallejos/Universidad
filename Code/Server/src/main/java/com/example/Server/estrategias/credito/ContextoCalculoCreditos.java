package com.example.Server.estrategias.credito;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.List;

@Setter
@Component
public class ContextoCalculoCreditos {
    private IEstrategiaCalculoCredito estrategia;

    public double calcular(List<IActaEstudiante> actas) {
        return estrategia.calcular(actas);
    }
}
