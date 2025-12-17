package com.example.Server.validadores.matricula;

import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Order(8)
public class ValidarMateriasCorrelativas implements IValidarMatricula {
    @Override
    public String validar(IEstudiante estudiante, IParaleloMateria paraleloMateria) {
        IMateria materia = paraleloMateria.getMateria();
        List<IMateria> materiasCorrelativas = materia.getMateriasCorrelativas();

        if (materiasCorrelativas != null)
            for (IMateria materiaCorrelativa : materiasCorrelativas)
                if (estudiante.getMateriasAprobadas() == null
                        || !estudiante.getMateriasAprobadas().contains(materiaCorrelativa))
                    return "El estudiante no ha aprobado el prerequisito: " + materiaCorrelativa.getNombre();

        return null;
    }
}
