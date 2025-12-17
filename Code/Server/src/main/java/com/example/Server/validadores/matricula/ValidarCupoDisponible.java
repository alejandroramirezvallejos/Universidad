package com.example.Server.validadores.matricula;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ValidarCupoDisponible implements IValidarMatricula {
    @Override
    public String validar(IEstudiante estudiante, IParaleloMateria paraleloMateria) {
        if (paraleloMateria.getEstudiantes().size() >= paraleloMateria.getCupoMaximo())
            return "El paralelo no tiene cupos disponibles";

        return null;
    }
}
