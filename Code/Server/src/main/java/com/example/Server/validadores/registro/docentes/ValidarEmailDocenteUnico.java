package com.example.Server.validadores.registro.docentes;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioDirectorCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
@RequiredArgsConstructor
public class ValidarEmailDocenteUnico implements IValidadorRegistroDocente {
    private final IRepositorioEstudiante repositorioEstudiante;
    private final IRepositorioDocente repositorioDocente;
    private final IRepositorioDirectorCarrera repositorioDirector;

    @Override
    public void validar(IDocente docente) {
        boolean emailExiste = repositorioEstudiante.buscarPorEmail(docente.getEmail()) != null ||
                              repositorioDocente.buscarPorEmail(docente.getEmail()) != null ||
                              repositorioDirector.buscarPorEmail(docente.getEmail()) != null;

        if (emailExiste)
            throw new RuntimeException("El email ya está registrado");
    }
}

