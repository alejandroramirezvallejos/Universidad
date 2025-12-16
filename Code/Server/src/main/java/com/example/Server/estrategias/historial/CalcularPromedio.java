package com.example.Server.estrategias.historial;
import com.example.Server.modelos.ActaEstudiante;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CalcularPromedio implements IEstrategiaCalculoHistorial {
    @Override
    public double calcular(List<ActaEstudiante> actas) {
        if (actas.isEmpty())
            return 0.0;

        double suma = 0.0;

        for (ActaEstudiante acta : actas)
            if (acta.isAprobado()) suma += acta.getCalificacionFinal();

        return suma / actas.size();
    }
}

