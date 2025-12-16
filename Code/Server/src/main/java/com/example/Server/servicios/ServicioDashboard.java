package com.example.Server.servicios;
import com.example.Server.casts.CastDashboardDocente;
import com.example.Server.casts.CastDashboardEstudiante;
import com.example.Server.dtos.DtoDashboardEstudiante;
import com.example.Server.dtos.DtoDashboardDocente;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioDashboard {
    @Autowired
    private RepositorioEstudiante repositorioEstudiante;
    @Autowired
    private RepositorioDocente repositorioDocente;
    @Autowired
    private RepositorioParaleloMateria repositorioParalelo;
    @Autowired
    private CastDashboardEstudiante convertidorEstudiante;
    @Autowired
    private CastDashboardDocente convertidorDocente;

    public DtoDashboardEstudiante generarDashboardEstudiante(String codigoEstudiante) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigoEstudiante);
        
        if (estudiante == null)
            return null;

        return convertidorEstudiante.getDto(estudiante);
    }

    public DtoDashboardDocente generarDashboardDocente(String codigoDocente) {
        Docente docente = repositorioDocente.buscarPorCodigo(codigoDocente);
        
        if (docente == null)
            return null;

        return convertidorDocente.getDto(docente, repositorioParalelo.getParalelos());
    }
}
