package com.example.Server.estrategias.actas;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CalcularPromedioActas implements IEstrategiaCalculoPromedioActas {
    @Override
    public double calcular(List<IActaEstudiante> actas) {
        if (actas.isEmpty())
            return 0.0;

        double suma = 0.0;

        for (IActaEstudiante acta : actas)
            suma += acta.getCalificacionFinal();

        return suma / actas.size();
    }
}

