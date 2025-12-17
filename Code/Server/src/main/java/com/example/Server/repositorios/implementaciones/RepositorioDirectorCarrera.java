package com.example.Server.repositorios.implementaciones;
import com.example.Server.modelos.abstracciones.IDirectorCarrera;
import com.example.Server.modelos.implementaciones.DirectorCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioDirectorCarrera;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioDirectorCarrera implements IRepositorioDirectorCarrera {
    private final Map<String, DirectorCarrera> directores = new HashMap<>();

    @Override
    public IDirectorCarrera guardar(IDirectorCarrera director) {
        DirectorCarrera directorImpl = (DirectorCarrera) director;
        directores.put(directorImpl.getCodigo(), directorImpl);
        return directorImpl;
    }

    @Override
    public List<IDirectorCarrera> getDirectores() {
        return new ArrayList<>(directores.values());
    }

    @Override
    public void eliminar(IDirectorCarrera director) {
        directores.remove(director.getCodigo());
    }

    @Override
    public IDirectorCarrera buscarPorCodigo(String codigo) {
        return directores.get(codigo);
    }

    @Override
    public IDirectorCarrera buscarPorEmail(String email) {
        for (DirectorCarrera director : directores.values())
            if (director.getEmail() != null && director.getEmail().equals(email))
                return director;

        return null;
    }
}
