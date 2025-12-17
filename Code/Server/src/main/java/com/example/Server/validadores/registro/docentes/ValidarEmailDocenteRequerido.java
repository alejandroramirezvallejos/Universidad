package com.example.Server.validadores.registro.docentes;
import com.example.Server.modelos.abstracciones.IDocente;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidarEmailDocenteRequerido implements IValidadorRegistroDocente {
    @Override
    public void validar(IDocente docente) {
        if (docente.getEmail() == null || docente.getEmail().trim().isEmpty())
            throw new RuntimeException("El email es requerido");
    }
}

