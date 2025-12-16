package com.example.Server.componentes;

import com.example.Server.modelos.Calificacion;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CalculadoraPromedio {
    public double calcular(Estudiante estudiante, ParaleloMateria paralelo, List<Evaluacion> evaluaciones) {
        double suma = 0.0;
        int cantidad = 0;
        for (Evaluacion evaluacion : evaluaciones) {
            if (evaluacion.getParaleloMateria().getCodigo().equals(paralelo.getCodigo())) {
                for (Calificacion calificacion : evaluacion.getCalificaciones()) {
                    if (calificacion.getEstudiante().getCodigo().equals(estudiante.getCodigo())) {
                        suma += calificacion.getValor();
                        cantidad++;
                    }
                }
            }
        }
        return cantidad > 0 ? suma / cantidad : 0.0;
    }
}

