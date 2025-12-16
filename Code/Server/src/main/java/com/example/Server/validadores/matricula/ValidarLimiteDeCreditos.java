package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(9)
public class ValidarLimiteDeCreditos implements IValidarMatricula {
    private static final int CREDITOS_MAXIMOS = 24;

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        int creditosActuales = 0;

        if (estudiante.getMateriasInscritas() != null)
            for (Materia materia : estudiante.getMateriasInscritas())
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
