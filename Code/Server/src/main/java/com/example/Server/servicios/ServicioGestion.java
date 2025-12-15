package com.example.Server.servicios;
import com.example.Server.modelos.Gestion;
import com.example.Server.repositorios.RepositorioGestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioGestion {
    private final RepositorioGestion repositorio;

    public Gestion crear(Gestion gestion) {
        return repositorio.guardar(gestion);
    }

    public List<Gestion> getGestiones() {
        return repositorio.getGestiones();
    }

    public Optional<Gestion> buscarPorCodigo(String codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }

    public Optional<Gestion> getGestionActual() {
        return repositorio.buscarGestionActual();
    }

    public void eliminar(Gestion gestion) {
        repositorio.eliminar(gestion);
    }
}
