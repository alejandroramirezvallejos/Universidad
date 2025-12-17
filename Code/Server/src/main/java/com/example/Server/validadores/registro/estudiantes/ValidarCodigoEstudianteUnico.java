package com.example.Server.validadores.registro.estudiantes;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@RequiredArgsConstructor
public class ValidarCodigoEstudianteUnico implements IValidadorRegistroEstudiante {
    private final IRepositorioEstudiante repositorioEstudiante;

    @Override
    public void validar(IEstudiante estudiante) {
        if (repositorioEstudiante.buscarPorCodigo(estudiante.getCodigo()) != null)
            throw new RuntimeException("El código de estudiante ya existe");
    }
}

