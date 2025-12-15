package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.List;

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
        
        if (estudianteAproboMateria(estudiante, materia))
            return "El estudiante ya aprobó la materia: " + materia.getNombre();

        if (siguiente != null)
            return siguiente.validar(estudiante, paraleloMateria);

        return null;
    }

    private boolean estudianteAproboMateria(Estudiante estudiante, Materia materia) {
        List<Materia> materiasAprobadas = estudiante.getMateriasAprobadas();
        
        if (materiasAprobadas == null || materiasAprobadas.isEmpty())
            return false;

        for (Materia materiaAprobada : materiasAprobadas)
            if (materiaAprobada.getCodigo().equals(materia.getCodigo()))
                return true;

        return false;
    }
}
