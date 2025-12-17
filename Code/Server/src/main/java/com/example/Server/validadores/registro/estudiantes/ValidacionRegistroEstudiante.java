package com.example.Server.validadores.registro.estudiantes;
import com.example.Server.modelos.abstracciones.IEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidacionRegistroEstudiante {
    private final List<IValidadorRegistroEstudiante> validadores;

    public void validar(IEstudiante estudiante) {
        for (IValidadorRegistroEstudiante validador : validadores)
            validador.validar(estudiante);
    }
}
