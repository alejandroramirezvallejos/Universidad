package com.example.Server.validadores.calificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ValidacionCalificacion implements IValidarCalificacion {
    private final List<IValidarCalificacion> validadores;

    @Override
    public boolean validar(double nota) {
        for (IValidarCalificacion validador : validadores)
            if (validador != this)
                if (!validador.validar(nota))
                    return false;

        return true;
    }
}
