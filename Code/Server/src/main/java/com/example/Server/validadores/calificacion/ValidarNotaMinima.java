package com.example.Server.validadores.calificacion;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidarNotaMinima implements IValidarCalificacion {
    @Override
    public boolean validar(double nota) {
        return nota >= 51;
    }
}
