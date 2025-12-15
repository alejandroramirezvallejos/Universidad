package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Order(5)
public class ValidarMateriasCorrelativas implements IValidarMatricula {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        Materia materia = paraleloMateria.getMateria();
        List<Materia> materiasCorrelativas = materia.getMateriasCorrelativas();

        if (materiasCorrelativas != null)
            for (Materia materiaCorrelativa : materiasCorrelativas)
                if (!estudiante.haAprobado(materiaCorrelativa))
                    return "El estudiante no ha aprobado el prerequisito: " + materiaCorrelativa.getNombre();

        return null;
    }
}
