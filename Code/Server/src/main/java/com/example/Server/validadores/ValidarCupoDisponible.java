package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;

@Component
public class ValidarCupoDisponible extends Validar {
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        if (!paraleloMateria.tieneCupo())
            return "El paralelo no tiene cupos disponibles";

        return super.validar(estudiante, paraleloMateria);
    }
}
