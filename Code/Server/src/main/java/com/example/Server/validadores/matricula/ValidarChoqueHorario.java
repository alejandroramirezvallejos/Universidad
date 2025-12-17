package com.example.Server.validadores.matricula;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IHorario;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(7)
public class ValidarChoqueHorario implements IValidarMatricula {
    @Override
    public String validar(IEstudiante estudiante, IParaleloMateria paraleloMateriaNuevo) {
        if (estudiante.getMateriasInscritas() == null)
            return null;

        List<IHorario> horariosInscritos = new ArrayList<>();

        for (IMateria materia : estudiante.getMateriasInscritas())
            if (materia.getParaleloMaterias() != null)
                for (IParaleloMateria paraleloMateria : materia.getParaleloMaterias())
                    if (paraleloMateria.getEstudiantes() != null && paraleloMateria.getEstudiantes().contains(estudiante))
                        if (paraleloMateria.getHorarios() != null)
                            horariosInscritos.addAll(paraleloMateria.getHorarios());

        for (IHorario horarioInscrito : horariosInscritos)
            for (IHorario horarioNuevo : paraleloMateriaNuevo.getHorarios())
                if (horarioInscrito.getDiaSemana().equals(horarioNuevo.getDiaSemana()))
                    if (horarioInscrito.getHoraInicio().isBefore(horarioNuevo.getHoraFin()) &&
                        horarioInscrito.getHoraFin().isAfter(horarioNuevo.getHoraInicio()))
                        return "Existe choque de horarios en " + horarioInscrito.getDiaSemana();

        return null;
    }
}
