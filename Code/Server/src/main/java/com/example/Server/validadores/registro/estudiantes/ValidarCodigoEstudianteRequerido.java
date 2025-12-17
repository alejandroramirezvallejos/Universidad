package com.example.Server.validadores.registro.estudiantes;
import com.example.Server.modelos.abstracciones.IEstudiante;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ValidarCodigoEstudianteRequerido implements IValidadorRegistroEstudiante {
    @Override
    public void validar(IEstudiante estudiante) {
        if (estudiante.getCodigo() == null || estudiante.getCodigo().trim().isEmpty())
            throw new RuntimeException("El código de estudiante es requerido");
    }
}

