package com.example.Server.validadores;

import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class CadenaValidadores implements IValidar {
    private final List<IValidar> validadores;

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        for (IValidar validador : validadores) {
            String error = validador.validar(estudiante, paraleloMateria);

            if (error != null)
                return error;
        }

        return null;
    }
}
