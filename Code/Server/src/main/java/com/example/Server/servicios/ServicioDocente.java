package com.example.Server.servicios;
import com.example.Server.modelos.Docente;
import com.example.Server.repositorios.RepositorioDocente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioDocente {
    private final RepositorioDocente repositorio;

    public Docente crear(Docente docente) {
        return repositorio.guardar(docente);
    }

    public List<Docente> getDocentes() {
        return repositorio.getDocentes();
    }

    public void eliminar(Docente docente) {
        repositorio.eliminar(docente);
    }

    public List<Docente> getDocentesActivos() {
        List<Docente> activos = new ArrayList<>();

        for (Docente docente : repositorio.getDocentes())
            if (docente.isActivo())
                activos.add(docente);

        return activos;
    }

    public Docente buscarPorCodigo(String codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }
}

