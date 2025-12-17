package com.example.Server.repositorios.implementaciones;
import com.example.Server.modelos.abstracciones.IHistorialAcademico;
import com.example.Server.modelos.implementaciones.HistorialAcademico;
import com.example.Server.repositorios.abstracciones.IRepositorioHistorialAcademico;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioHistorialAcademico implements IRepositorioHistorialAcademico {
    private final Map<String, HistorialAcademico> historiales = new HashMap<>();

    @Override
    public IHistorialAcademico guardar(IHistorialAcademico historial) {
        HistorialAcademico historialImpl = (HistorialAcademico) historial;
        historiales.put(historialImpl.getEstudiante().getCodigo(), historialImpl);
        return historialImpl;
    }

    @Override
    public List<IHistorialAcademico> getHistoriales() {
        return new ArrayList<>(historiales.values());
    }

    @Override
    public void eliminar(IHistorialAcademico historial) {
        HistorialAcademico historialImpl = (HistorialAcademico) historial;
        historiales.remove(historialImpl.getEstudiante().getCodigo());
    }

    @Override
    public IHistorialAcademico buscar(String codigoEstudiante) {
        return historiales.get(codigoEstudiante);
    }
}
