package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class ValidarMateriaAprobada implements IValidarMatricula {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        Materia materia = paraleloMateria.getMateria();

        if (estudiante.getMateriasAprobadas() != null && estudiante.getMateriasAprobadas().contains(materia))
            return "El estudiante ya aprobó la materia: " + materia.getNombre();

        return null;
    }
}
