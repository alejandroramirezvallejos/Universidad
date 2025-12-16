package com.example.Server.estrategias.historial;
import com.example.Server.modelos.ActaEstudiante;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CalcularCreditosTotales implements IEstrategiaCalculoHistorial {
    @Override
    public double calcular(List<ActaEstudiante> actas) {
        int creditos = 0;

        for (ActaEstudiante acta : actas)
            creditos += acta.getParaleloMateria().getMateria().getCreditos();

        return creditos;
    }
}

