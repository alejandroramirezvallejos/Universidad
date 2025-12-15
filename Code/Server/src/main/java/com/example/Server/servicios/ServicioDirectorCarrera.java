package com.example.Server.servicios;
import com.example.Server.modelos.DirectorCarrera;
import com.example.Server.repositorios.RepositorioDirectorCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioDirectorCarrera {
    private final RepositorioDirectorCarrera repositorio;

    public DirectorCarrera crear(DirectorCarrera director) {
        return repositorio.guardar(director);
    }

    public List<DirectorCarrera> getDirectores() {
        return repositorio.getDirectores();
    }

    public void eliminar(DirectorCarrera director) {
        repositorio.eliminar(director);
    }
}
