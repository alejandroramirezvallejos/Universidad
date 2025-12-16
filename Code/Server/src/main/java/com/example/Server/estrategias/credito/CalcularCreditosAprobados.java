package com.example.Server.estrategias.credito;
import com.example.Server.modelos.ActaEstudiante;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CalcularCreditosAprobados implements IEstrategiaCalculoCredito {
    @Override
    public double calcular(List<ActaEstudiante> actas) {
        int creditos = 0;

        for (ActaEstudiante acta : actas)
            if (acta.isAprobado()) creditos += acta.getParaleloMateria().getMateria().getCreditos();

        return creditos;
    }
}

