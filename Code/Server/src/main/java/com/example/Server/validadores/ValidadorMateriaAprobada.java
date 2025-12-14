package com.example.Server.validadores;

import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Validador que verifica que el estudiante no haya aprobado ya la materia
 * Un estudiante no puede inscribirse a una materia que ya aprobó
 */
@Component
public class ValidadorMateriaAprobada implements IValidador {
    private IValidador siguiente;

    @Override
    public void setSiguiente(IValidador siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        Materia materia = paraleloMateria.getMateria();
        
        if (estudianteAproboMateria(estudiante, materia)) {
            return "El estudiante ya aprobó la materia: " + materia.getNombre();
        }

        if (siguiente != null) {
            return siguiente.validar(estudiante, paraleloMateria);
        }

        return null;
    }

    /**
     * Verifica si el estudiante ya aprobó esta materia
     */
    private boolean estudianteAproboMateria(Estudiante estudiante, Materia materia) {
        List<Materia> materiasAprobadas = estudiante.getMateriasAprobadas();
        
        if (materiasAprobadas == null || materiasAprobadas.isEmpty()) {
            return false;
        }

        return materiasAprobadas.stream()
                .anyMatch(m -> m.getCodigo().equals(materia.getCodigo()));
    }
}
