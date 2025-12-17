package com.example.Server.servicios.implementaciones;

import com.example.Server.modelos.abstracciones.IDirectorCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioDirectorCarrera;
import com.example.Server.servicios.abstracciones.IServicioDirectorCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioDirectorCarrera implements IServicioDirectorCarrera {
    private final IRepositorioDirectorCarrera repositorio;

    @Override
    public IDirectorCarrera crear(IDirectorCarrera director) {
        return repositorio.guardar(director);
    }

    @Override
    public List<IDirectorCarrera> getDirectores() {
        return repositorio.getDirectores();
    }

    @Override
    public void eliminar(IDirectorCarrera director) {
        repositorio.eliminar(director);
    }
}
