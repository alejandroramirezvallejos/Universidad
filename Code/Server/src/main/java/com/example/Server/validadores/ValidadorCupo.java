package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ValidadorCupo implements IValidador {
    private IValidador siguiente;

    @Override
    public void setSiguiente(IValidador siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        if (paraleloLleno(paraleloMateria))
            return "El paralelo no tiene cupos disponibles";

        if (siguiente != null)
            return siguiente.validar(estudiante, paraleloMateria);

        return null;
    }

    private boolean paraleloLleno(ParaleloMateria paraleloMateria) {
        int cupoActual = cupoActual(paraleloMateria);
        int cupoMaximo = paraleloMateria.getCupoMaximo();
        return cupoActual >= cupoMaximo;
    }

    private int cupoActual(ParaleloMateria paraleloMateria) {
        List<Estudiante> estudiantesInscritos = paraleloMateria.getEstudiantes();
        return estudiantesInscritos.size();
    }
}
