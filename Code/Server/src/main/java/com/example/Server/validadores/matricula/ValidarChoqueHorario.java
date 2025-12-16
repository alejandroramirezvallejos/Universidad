package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Horario;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(7)
public class ValidarChoqueHorario implements IValidarMatricula {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateriaNuevo) {
        if (estudiante.getMateriasInscritas() == null) return null;

        for (Materia materiaInscrita : estudiante.getMateriasInscritas())
            if (materiaInscrita.getParaleloMaterias() != null)
                for (ParaleloMateria paraleloInscrito : materiaInscrita.getParaleloMaterias())
                    if (paraleloInscrito.getEstudiantes() != null && paraleloInscrito.getEstudiantes().contains(estudiante))
                        for (Horario horarioInscrito : paraleloInscrito.getHorarios())
                            for (Horario horarioNuevo : paraleloMateriaNuevo.getHorarios())
                                if (horarioInscrito.getDiaSemana().equals(horarioNuevo.getDiaSemana()))
                                    if (horarioInscrito.getHoraInicio().isBefore(horarioNuevo.getHoraFin()) &&
                                        horarioInscrito.getHoraFin().isAfter(horarioNuevo.getHoraInicio()))
                                        return "Existe choque de horarios en " + horarioInscrito.getDiaSemana();
        return null;
    }
}

