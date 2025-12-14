package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidadorMateriasCorrelativas implements IValidador {
    private final ValidadorMateria validadorMateria;
    private IValidador siguiente;

    @Override
    public void setSiguiente(IValidador siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        Materia materia = paraleloMateria.getMateria();
        List<Materia> materiasCorrelativas = materia.getMateriasCorrelativas();

        for (Materia materiaCorrelativa : materiasCorrelativas)
            if (!aproboMateria(estudiante, materiaCorrelativa))
                return "El estudiante no ha aprobado el prerequisito: " + materiaCorrelativa.getNombre();

        if (siguiente != null)
            return siguiente.validar(estudiante, paraleloMateria);

        return null;
    }

    private boolean aproboMateria(Estudiante estudiante, Materia materiaCorrelativa) {
        List<Materia> materiasAprobadas = estudiante.getMateriasAprobadas();
        return validadorMateria.existeEnLista(materiaCorrelativa, materiasAprobadas);
    }
}
