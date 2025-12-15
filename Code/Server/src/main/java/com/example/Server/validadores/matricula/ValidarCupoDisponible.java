package com.example.Server.validadores.matricula;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ValidarCupoDisponible implements IValidarMatricula {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        if (!paraleloMateria.tieneCupo())
            return "El paralelo no tiene cupos disponibles";

        return null;
    }
}
