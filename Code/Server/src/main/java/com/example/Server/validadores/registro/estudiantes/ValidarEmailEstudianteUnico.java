package com.example.Server.validadores.registro.estudiantes;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioDirectorCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
@RequiredArgsConstructor
public class ValidarEmailEstudianteUnico implements IValidadorRegistroEstudiante {
    private final IRepositorioEstudiante repositorioEstudiante;
    private final IRepositorioDocente repositorioDocente;
    private final IRepositorioDirectorCarrera repositorioDirector;

    @Override
    public void validar(IEstudiante estudiante) {
        boolean emailExiste = repositorioEstudiante.buscarPorEmail(estudiante.getEmail()) != null ||
                              repositorioDocente.buscarPorEmail(estudiante.getEmail()) != null ||
                              repositorioDirector.buscarPorEmail(estudiante.getEmail()) != null;
        if (emailExiste)
            throw new RuntimeException("El email ya está registrado");
    }
}

