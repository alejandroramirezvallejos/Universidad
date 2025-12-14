package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorMateriaNoInscrita implements IValidador {
    private final ValidadorEstudiante validadorEstudiante;
    private IValidador siguiente;

    @Override
    public void setSiguiente(IValidador siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateriaNuevo) {
        Materia materiaNueva = paraleloMateriaNuevo.getMateria();

        if (tieneMateriaInscrita(estudiante, materiaNueva))
            return "El estudiante ya esta inscrito en un paralelo de esta materia";

        if (siguiente != null)
            return siguiente.validar(estudiante, paraleloMateriaNuevo);

        return null;
    }

    private boolean tieneMateriaInscrita(Estudiante estudiante, Materia materiaNueva) {
        return validadorEstudiante.tieneInscritaMateria(estudiante, materiaNueva.getCodigo());
    }
}
