package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Horario;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(7)
public class ValidarChoqueHorario implements IValidarMatricula {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateriaNuevo) {
        if (estudiante.getMateriasInscritas() == null)
            return null;

        List<Horario> horariosInscritos = new ArrayList<>();

        for (Materia materia : estudiante.getMateriasInscritas())
            if (materia.getParaleloMaterias() != null)
                for (ParaleloMateria paraleloMateria : materia.getParaleloMaterias())
                    if (paraleloMateria.getEstudiantes() != null && paraleloMateria.getEstudiantes().contains(estudiante))
                        if (paraleloMateria.getHorarios() != null)
                            horariosInscritos.addAll(paraleloMateria.getHorarios());

        for (Horario horarioInscrito : horariosInscritos)
            for (Horario horarioNuevo : paraleloMateriaNuevo.getHorarios())
                if (horarioInscrito.getDiaSemana().equals(horarioNuevo.getDiaSemana()))
                    if (horarioInscrito.getHoraInicio().isBefore(horarioNuevo.getHoraFin()) &&
                        horarioInscrito.getHoraFin().isAfter(horarioNuevo.getHoraInicio()))
                        return "Existe choque de horarios en " + horarioInscrito.getDiaSemana();

        return null;
    }
}

