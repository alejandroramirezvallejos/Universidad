package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ValidarSemestre implements IValidarMatricula {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        int semestreEstudiante = estudiante.getSemestre();
        Integer semestreMateria = paraleloMateria.getMateria().getSemestre();

        if (semestreMateria == null)
            return "Semestre de la materia no disponible";

        if (semestreEstudiante < semestreMateria)
            return "El estudiante no puede inscribirse en una materia de semestre " + semestreMateria + " siendo de semestre " + semestreEstudiante;

        return null;
    }
}
