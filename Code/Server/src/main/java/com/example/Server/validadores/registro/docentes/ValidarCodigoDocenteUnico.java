package com.example.Server.validadores.registro.docentes;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@RequiredArgsConstructor
public class ValidarCodigoDocenteUnico implements IValidadorRegistroDocente {
    private final IRepositorioDocente repositorioDocente;

    @Override
    public void validar(IDocente docente) {
        if (repositorioDocente.buscarPorCodigo(docente.getCodigo()) != null)
            throw new RuntimeException("El código de docente ya existe");
    }
}

