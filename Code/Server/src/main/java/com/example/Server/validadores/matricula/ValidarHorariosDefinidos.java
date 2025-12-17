package com.example.Server.validadores.matricula;

import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
public class ValidarHorariosDefinidos implements IValidarMatricula {
    @Override
    public String validar(IEstudiante estudiante, IParaleloMateria paraleloMateria) {
        if (paraleloMateria.getHorarios() == null || paraleloMateria.getHorarios().isEmpty())
            return "El paralelo no tiene horarios definidos";

        return null;
    }
}
