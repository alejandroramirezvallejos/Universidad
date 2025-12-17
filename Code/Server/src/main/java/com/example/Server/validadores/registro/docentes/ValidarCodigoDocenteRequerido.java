package com.example.Server.validadores.registro.docentes;
import com.example.Server.modelos.abstracciones.IDocente;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ValidarCodigoDocenteRequerido implements IValidadorRegistroDocente {
    @Override
    public void validar(IDocente docente) {
        if (docente.getCodigo() == null || docente.getCodigo().trim().isEmpty())
            throw new RuntimeException("El código de docente es requerido");
    }
}

