package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Horario;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidadorChoqueHorario implements IValidador {
    private final ValidadorEstudiante validadorEstudiante;
    private IValidador siguiente;

    @Override
    public void setSiguiente(IValidador siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateriaNuevo) {
        List<Materia> materiasInscritas = estudiante.getMateriasInscritas();
        List<Horario> horariosNuevos = paraleloMateriaNuevo.getHorarios();

        for (Materia materiaInscrita : materiasInscritas) {
            String error = validarChoqueEnMateria(estudiante, materiaInscrita, horariosNuevos);

            if (error != null)
                return error;
        }

        if (siguiente != null)
            return siguiente.validar(estudiante, paraleloMateriaNuevo);

        return null;
    }

    private String validarChoqueEnMateria(Estudiante estudiante, Materia materiaInscrita, List<Horario> horariosNuevos) {
        List<ParaleloMateria> paralelosMateria = materiaInscrita.getParaleloMaterias();

        for (ParaleloMateria paraleloMateriaExistente : paralelosMateria)
            if (validadorEstudiante.estaInscritoEnParalelo(estudiante, paraleloMateriaExistente)) {
                String error = validarChoqueEnHorarios(paraleloMateriaExistente, horariosNuevos);
                if (error != null)
                    return error;
            }

        return null;
    }

    private String validarChoqueEnHorarios(ParaleloMateria paraleloMateriaExistente, List<Horario> horariosNuevos) {
        List<Horario> horariosExistentes = paraleloMateriaExistente.getHorarios();

        for (Horario horarioNuevo : horariosNuevos)
            for (Horario horarioExistente : horariosExistentes)
                if (hayChoque(horarioNuevo, horarioExistente))
                    return "Existe choque de horarios en " + horarioNuevo.getDiaSemana();

        return null;
    }

    private boolean hayChoque(Horario primerHorario, Horario segundoHorario) {
        if (!mismoDia(primerHorario, segundoHorario))
            return false;

        return seSuperponen(primerHorario, segundoHorario);
    }

    private boolean mismoDia(Horario primerHorario, Horario segundoHorario) {
        return primerHorario.getDiaSemana().equals(segundoHorario.getDiaSemana());
    }

    private boolean seSuperponen(Horario primerHorario, Horario segundoHorario) {
        return primerHorario.getHoraInicio().isBefore(segundoHorario.getHoraFin()) &&
               primerHorario.getHoraFin().isAfter(segundoHorario.getHoraInicio());
    }
}
