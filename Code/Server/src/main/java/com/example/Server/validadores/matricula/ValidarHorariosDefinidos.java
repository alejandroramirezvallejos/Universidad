package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
public class ValidarHorariosDefinidos implements IValidarMatricula {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        if (paraleloMateria.getHorarios() == null || paraleloMateria.getHorarios().isEmpty())
            return "El paralelo no tiene horarios definidos";

        return null;
    }
}

