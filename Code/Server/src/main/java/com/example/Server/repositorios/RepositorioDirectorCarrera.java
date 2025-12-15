package com.example.Server.repositorios;
import com.example.Server.modelos.DirectorCarrera;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

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
        for (DirectorCarrera director : directores)
            if (director.getCodigo().equals(codigo))
                return director;

        return null;
    }

    public DirectorCarrera buscarPorEmail(String email) {
        for (DirectorCarrera director : directores)
            if (director.getEmail() != null && director.getEmail().equals(email))
                return director;

        return null;
    }
}
