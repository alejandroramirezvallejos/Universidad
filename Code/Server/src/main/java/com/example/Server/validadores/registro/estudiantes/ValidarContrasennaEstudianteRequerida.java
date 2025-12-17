package com.example.Server.validadores.registro.estudiantes;
import com.example.Server.modelos.abstracciones.IEstudiante;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ValidarContrasennaEstudianteRequerida implements IValidadorRegistroEstudiante {
    @Override
    public void validar(IEstudiante estudiante) {
        if (estudiante.getContrasenna() == null || estudiante.getContrasenna().trim().isEmpty())
            throw new RuntimeException("La contraseña es requerida");
    }
}

