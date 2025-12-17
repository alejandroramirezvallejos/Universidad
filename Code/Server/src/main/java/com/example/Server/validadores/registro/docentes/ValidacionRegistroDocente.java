package com.example.Server.validadores.registro.docentes;
import com.example.Server.modelos.abstracciones.IDocente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidacionRegistroDocente {
    private final List<IValidadorRegistroDocente> validadores;

    public void validar(IDocente docente) {
        for (IValidadorRegistroDocente validador : validadores)
            validador.validar(docente);
    }
}
