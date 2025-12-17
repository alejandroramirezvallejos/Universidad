package com.example.Server.validadores.matricula;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class ValidarMateriaAprobada implements IValidarMatricula {
    @Override
    public String validar(IEstudiante estudiante, IParaleloMateria paraleloMateria) {
        IMateria materia = paraleloMateria.getMateria();

        if (estudiante.getMateriasAprobadas() != null && estudiante.getMateriasAprobadas().contains(materia))
            return "El estudiante ya aprobó la materia: " + materia.getNombre();

        return null;
    }
}
