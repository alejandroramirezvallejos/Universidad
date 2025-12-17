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
        for (IValidarMatricula validador : validadores)
            if (validador != this) {
                String resultado = validador.validar(estudiante, paraleloMateria);

                if (resultado != null)
                    return resultado;
            }

        return null;
    }
}
