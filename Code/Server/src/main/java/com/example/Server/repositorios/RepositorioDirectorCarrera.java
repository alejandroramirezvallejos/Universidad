package com.example.Server.repositorios;
import com.example.Server.modelos.DirectorCarrera;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio para gestionar DirectorCarrera en memoria
 */
@Repository
public class RepositorioDirectorCarrera {
    private final List<DirectorCarrera> directores = new ArrayList<>();

    public DirectorCarrera guardar(DirectorCarrera director) {
        directores.add(director);
        return director;
    }

    public List<DirectorCarrera> getDirectores() {
        return new ArrayList<>(directores);
    }

    public void eliminar(DirectorCarrera director) {
        directores.remove(director);
    }

    public DirectorCarrera buscarPorCodigo(String codigo) {
        return directores.stream()
                .filter(d -> d.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public DirectorCarrera buscarPorEmail(String email) {
        return directores.stream()
                .filter(d -> d.getEmail() != null && d.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
