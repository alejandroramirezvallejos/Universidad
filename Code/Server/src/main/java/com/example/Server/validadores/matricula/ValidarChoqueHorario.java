package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Horario;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
public class ValidarChoqueHorario implements IValidarMatricula {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateriaNuevo) {
        Horario choque = estudiante.obtenerChoqueHorario(paraleloMateriaNuevo.getHorarios());

        if (choque != null)
            return "Existe choque de horarios en " + choque.getDiaSemana();

        return null;
    }
}
