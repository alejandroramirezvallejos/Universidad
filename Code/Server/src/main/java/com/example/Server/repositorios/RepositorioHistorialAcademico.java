package com.example.Server.repositorios;
import com.example.Server.modelos.HistorialAcademico;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioHistorialAcademico {
    private final List<HistorialAcademico> historiales = new ArrayList<>();

    public HistorialAcademico guardar(HistorialAcademico historial) {
        historiales.add(historial);
        return historial;
    }

    public List<HistorialAcademico> getHistoriales() {
        return new ArrayList<>(historiales);
    }

    public void eliminar(HistorialAcademico historial) {
        historiales.remove(historial);
    }
}

