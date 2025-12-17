package com.example.Server.validadores.registro.estudiantes;
import com.example.Server.modelos.abstracciones.IEstudiante;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidarEmailEstudianteRequerido implements IValidadorRegistroEstudiante {
    @Override
    public void validar(IEstudiante estudiante) {
        if (estudiante.getEmail() == null || estudiante.getEmail().trim().isEmpty())
            throw new RuntimeException("El email es requerido");
    }
}

