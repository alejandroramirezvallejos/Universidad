package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;

@Component
public class ValidarLimiteDeCreditos extends Validar {
    private static final int CREDITOS_MAXIMOS = 24;

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        int creditosActuales = estudiante.calcularCreditosInscritos();
        int creditosNuevos = paraleloMateria.getMateria().getCreditos();
        int creditosTotales = creditosActuales + creditosNuevos;

        if (creditosTotales > CREDITOS_MAXIMOS)
            return String.format(
                "Límite de créditos excedido. Actual: %d, Nuevo: %d, Total: %d (Máximo: %d)",
                creditosActuales, creditosNuevos, creditosTotales, CREDITOS_MAXIMOS
            );

        return super.validar(estudiante, paraleloMateria);
    }
}
