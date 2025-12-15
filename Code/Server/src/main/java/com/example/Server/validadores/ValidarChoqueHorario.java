package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Horario;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;

@Component
public class ValidarChoqueHorario extends Validar {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateriaNuevo) {
        Horario choque = estudiante.obtenerChoqueHorario(paraleloMateriaNuevo.getHorarios());

        if (choque != null)
            return "Existe choque de horarios en " + choque.getDiaSemana();

        return super.validar(estudiante, paraleloMateriaNuevo);
    }
}
