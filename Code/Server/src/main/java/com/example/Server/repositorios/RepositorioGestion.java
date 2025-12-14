package com.example.Server.repositorios;
import com.example.Server.modelos.Gestion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar Gestiones (periodos académicos) en memoria
 */
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
        return gestiones.stream()
                .filter(g -> g.getCodigo().equals(codigo))
                .findFirst();
    }

    public Optional<Gestion> buscarGestionActual() {
        return gestiones.stream()
                .filter(g -> "EN_CURSO".equals(g.getEstado()))
                .findFirst();
    }

    public void eliminar(Gestion gestion) {
        gestiones.remove(gestion);
    }
}
