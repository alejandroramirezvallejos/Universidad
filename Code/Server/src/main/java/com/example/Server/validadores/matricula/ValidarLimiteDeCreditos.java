package com.example.Server.validadores.matricula;

import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(9)
public class ValidarLimiteDeCreditos implements IValidarMatricula {
    private static final int CREDITOS_MAXIMOS = 24;

    @Override
    public String validar(IEstudiante estudiante, IParaleloMateria paraleloMateria) {
        int creditosActuales = 0;

        if (estudiante.getMateriasInscritas() != null)
            for (IMateria materia : estudiante.getMateriasInscritas())
                creditosActuales += materia.getCreditos();

        int creditosNuevos = paraleloMateria.getMateria().getCreditos();
        int creditosTotales = creditosActuales + creditosNuevos;

        if (creditosTotales > CREDITOS_MAXIMOS)
            return "Límite de créditos excedido. Actual: "
                    + creditosActuales + ", Nuevo: "
                    + creditosNuevos + ", Total: "
                    + creditosTotales + " (Máximo: "
                    + CREDITOS_MAXIMOS + ")";

        return null;
    }
}
