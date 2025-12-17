package com.example.Server.repositorios.implementaciones;
import com.example.Server.modelos.abstracciones.IGestion;
import com.example.Server.modelos.implementaciones.Gestion;
import com.example.Server.repositorios.abstracciones.IRepositorioGestion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioGestion implements IRepositorioGestion {
    private final Map<String, Gestion> gestiones = new HashMap<>();

    @Override
    public IGestion guardar(IGestion gestion) {
        Gestion gestionImpl = (Gestion) gestion;
        gestiones.put(gestionImpl.getCodigo(), gestionImpl);
        return gestionImpl;
    }

    @Override
    public List<IGestion> getGestiones() {
        return new ArrayList<>(gestiones.values());
    }

    @Override
    public void eliminar(IGestion gestion) {
        gestiones.remove(gestion.getCodigo());
    }

    @Override
    public IGestion buscarPorCodigo(String codigo) {
        return gestiones.get(codigo);
    }

    @Override
    public IGestion buscarGestionActual() {
        for (Gestion gestion : gestiones.values())
            if ("EN_CURSO".equals(gestion.getEstado()))
                return gestion;

        return null;
    }
}
