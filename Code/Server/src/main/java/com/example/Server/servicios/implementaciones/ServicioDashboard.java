package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import com.example.Server.servicios.abstracciones.IServicioDashboard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServicioDashboard implements IServicioDashboard {
    private final IRepositorioEstudiante repositorioEstudiante;
    private final IRepositorioDocente repositorioDocente;

    @Override
    public Map<String, Object> generarDashboardEstudiante(String codigoEstudiante) {
        IEstudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigoEstudiante);

        if (estudiante == null)
            throw new RuntimeException("Estudiante no encontrado");

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("estudiante", estudiante);

        return dashboard;
    }

    @Override
    public Map<String, Object> generarDashboardDocente(String codigoDocente) {
        IDocente docente = repositorioDocente.buscarPorCodigo(codigoDocente);

        if (docente == null)
            throw new RuntimeException("Docente no encontrado");

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("docente", docente);

        return dashboard;
    }
}
