package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.IGestion;
import com.example.Server.repositorios.abstracciones.IRepositorioGestion;
import com.example.Server.servicios.abstracciones.IServicioGestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioGestion implements IServicioGestion {
    private final IRepositorioGestion repositorio;

    @Override
    public IGestion crear(IGestion gestion) {
        return repositorio.guardar(gestion);
    }

    @Override
    public List<IGestion> getGestiones() {
        return repositorio.getGestiones();
    }

    @Override
    public IGestion getGestionPorCodigo(String codigo) {
        IGestion gestion = repositorio.buscarPorCodigo(codigo);

        if (gestion == null)
            throw new RuntimeException("Gestión no encontrada");

        return gestion;
    }

    @Override
    public IGestion getGestion() {
        IGestion gestion = repositorio.buscarGestionActual();

        if (gestion == null)
            throw new RuntimeException("No hay gestión actual");

        return gestion;
    }

    @Override
    public void eliminar(IGestion gestion) {
        repositorio.eliminar(gestion);
    }
}
