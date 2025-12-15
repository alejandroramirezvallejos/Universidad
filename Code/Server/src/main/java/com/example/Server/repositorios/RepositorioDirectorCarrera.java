package com.example.Server.repositorios;
import com.example.Server.modelos.DirectorCarrera;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioDirectorCarrera {
    private final Map<String, DirectorCarrera> directores = new HashMap<>();

    public DirectorCarrera guardar(DirectorCarrera director) {
        directores.put(director.getCodigo(), director);
        return director;
    }

    public List<DirectorCarrera> getDirectores() {
        return new ArrayList<>(directores.values());
    }

    public void eliminar(DirectorCarrera director) {
        directores.remove(director.getCodigo());
    }

    public DirectorCarrera buscarPorCodigo(String codigo) {
        return directores.get(codigo);
    }

    public DirectorCarrera buscarPorEmail(String email) {
        for (DirectorCarrera director : directores.values())
            if (director.getEmail() != null && director.getEmail().equals(email))
                return director;

        return null;
    }
}
