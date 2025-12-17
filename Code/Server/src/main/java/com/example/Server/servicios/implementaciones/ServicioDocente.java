package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import com.example.Server.servicios.abstracciones.IServicioDocente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioDocente implements IServicioDocente {
    private final IRepositorioDocente repositorio;

    @Override
    public IDocente crear(IDocente docente) {
        return repositorio.guardar(docente);
    }

    @Override
    public List<IDocente> getDocentes() {
        return repositorio.getDocentes();
    }

    @Override
    public void eliminar(IDocente docente) {
        repositorio.eliminar(docente);
    }

    @Override
    public List<IDocente> getDocentesActivos() {
        List<IDocente> activos = new ArrayList<>();

        for (IDocente docente : repositorio.getDocentes())
            if (docente.isActivo())
                activos.add(docente);

        return activos;
    }

    @Override
    public IDocente buscar(String codigo) {
        IDocente docente = repositorio.buscarPorCodigo(codigo);

        if (docente == null)
            throw new RuntimeException("Docente no encontrado");

        return docente;
    }
}
