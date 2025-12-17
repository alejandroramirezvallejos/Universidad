package com.example.Server.validadores.registro.docentes;
import com.example.Server.modelos.abstracciones.IDocente;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ValidarContrasennaDocenteRequerida implements IValidadorRegistroDocente {
    @Override
    public void validar(IDocente docente) {
        if (docente.getContrasenna() == null || docente.getContrasenna().trim().isEmpty())
            throw new RuntimeException("La contraseña es requerida");
    }
}
