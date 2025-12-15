package com.example.Server.repositorios;
import com.example.Server.modelos.Gestion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class RepositorioGestion {
    private final Map<String, Gestion> gestiones = new HashMap<>();

    public Gestion guardar(Gestion gestion) {
        gestiones.put(gestion.getCodigo(), gestion);
        return gestion;
    }

    public List<Gestion> getGestiones() {
        return new ArrayList<>(gestiones.values());
    }

    public Optional<Gestion> buscarPorCodigo(String codigo) {
        return Optional.ofNullable(gestiones.get(codigo));
    }

    public Optional<Gestion> buscarGestionActual() {
        for (Gestion gestion : gestiones.values())
            if ("EN_CURSO".equals(gestion.getEstado()))
                return Optional.of(gestion);

        return Optional.empty();
    }

    public void eliminar(Gestion gestion) {
        gestiones.remove(gestion.getCodigo());
    }
}
