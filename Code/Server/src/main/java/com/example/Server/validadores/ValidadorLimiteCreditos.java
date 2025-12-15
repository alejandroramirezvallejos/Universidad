package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ValidadorLimiteCreditos implements IValidador {
    private static final int CREDITOS_MAXIMOS = 24;
    private IValidador siguiente;

    @Override
    public void setSiguiente(IValidador siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        int creditosActuales = calcularCreditosInscritos(estudiante);
        int creditosNuevos = paraleloMateria.getMateria().getCreditos();
        int creditosTotales = creditosActuales + creditosNuevos;

        if (creditosTotales > CREDITOS_MAXIMOS)
            return String.format(
                "Límite de créditos excedido. Actual: %d, Nuevo: %d, Total: %d (Máximo: %d)",
                creditosActuales, creditosNuevos, creditosTotales, CREDITOS_MAXIMOS
            );

        if (siguiente != null)
            return siguiente.validar(estudiante, paraleloMateria);

        return null;
    }

    private int calcularCreditosInscritos(Estudiante estudiante) {
        List<Materia> materiasInscritas = estudiante.getMateriasInscritas();
        
        if (materiasInscritas == null || materiasInscritas.isEmpty())
            return 0;

        int totalCreditos = 0;
        for (Materia materia : materiasInscritas)
            totalCreditos += materia.getCreditos();

        return totalCreditos;
    }
}
