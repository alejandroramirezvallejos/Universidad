package com.example.Server.repositorios;
import com.example.Server.modelos.HistorialAcademico;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioHistorialAcademico {
    private final Map<String, HistorialAcademico> historiales = new HashMap<>();

    public HistorialAcademico guardar(HistorialAcademico historial) {
        historiales.put(historial.getEstudiante().getCodigo(), historial);
        return historial;
    }

    public List<HistorialAcademico> getHistoriales() {
        return new ArrayList<>(historiales.values());
    }

    public void eliminar(HistorialAcademico historial) {
        historiales.remove(historial.getEstudiante().getCodigo());
    }

    public HistorialAcademico buscarPorEstudiante(String codigoEstudiante) {
        return historiales.get(codigoEstudiante);
    }
}

