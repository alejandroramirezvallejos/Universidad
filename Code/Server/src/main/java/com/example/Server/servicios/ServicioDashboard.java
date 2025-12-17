package com.example.Server.servicios;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServicioDashboard {
    @Autowired
    private RepositorioEstudiante repositorioEstudiante;
    @Autowired
    private RepositorioDocente repositorioDocente;
    @Autowired
    private RepositorioParaleloMateria repositorioParalelo;

    public Map<String, Object> generarDashboardEstudiante(String codigoEstudiante) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigoEstudiante);

        if (estudiante == null)
            throw new RuntimeException("Estudiante no encontrado");

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("estudiante", estudiante);

        return dashboard;
    }

    public Map<String, Object> generarDashboardDocente(String codigoDocente) {
        Docente docente = repositorioDocente.buscarPorCodigo(codigoDocente);

        if (docente == null)
            throw new RuntimeException("Docente no encontrado");

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("docente", docente);

        return dashboard;
    }
}
