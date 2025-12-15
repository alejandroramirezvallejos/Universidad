package com.example.Server.validadores.matricula;

import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ValidacionDeMatricula implements IValidarMatricula {
    private final List<IValidarMatricula> validadores;

    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        for (IValidarMatricula validador : validadores) {
            String error = validador.validar(estudiante, paraleloMateria);

            if (error != null)
                return error;
        }

        return null;
    }
}
