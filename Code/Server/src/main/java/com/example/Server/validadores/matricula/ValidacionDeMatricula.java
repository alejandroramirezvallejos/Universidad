package com.example.Server.validadores.matricula;

import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
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
    public String validar(IEstudiante estudiante, IParaleloMateria paraleloMateria) {
        for (IValidarMatricula validador : validadores) {
            String error = validador.validar(estudiante, paraleloMateria);

            if (error != null)
                return error;
        }

        return null;
    }
}
