package com.example.Server.repositorios;
import com.example.Server.modelos.Gestion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioGestion {
    private final List<Gestion> gestiones = new ArrayList<>();

    public Gestion guardar(Gestion gestion) {
        gestiones.add(gestion);
        return gestion;
    }

    public List<Gestion> getGestiones() {
        return new ArrayList<>(gestiones);
    }

    public Optional<Gestion> buscarPorCodigo(String codigo) {
        for (Gestion gestion : gestiones)
            if (gestion.getCodigo().equals(codigo))
                return Optional.of(gestion);

        return Optional.empty();
    }

    public Optional<Gestion> buscarGestionActual() {
        for (Gestion gestion : gestiones)
            if ("EN_CURSO".equals(gestion.getEstado()))
                return Optional.of(gestion);

        return Optional.empty();
    }

    public void eliminar(Gestion gestion) {
        gestiones.remove(gestion);
    }
}
