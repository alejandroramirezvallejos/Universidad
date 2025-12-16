package com.example.Server.casts;

import com.example.Server.dtos.DtoDashboardEstudiante;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CastDashboardEstudiante {

    public DtoDashboardEstudiante getDto(Estudiante estudiante) {
        DtoDashboardEstudiante dashboard = new DtoDashboardEstudiante();
        dashboard.setCodigoEstudiante(estudiante.getCodigo());
        dashboard.setNombreCompleto(estudiante.getNombre() + " " + estudiante.getApellido());
        dashboard.setSemestre(estudiante.getSemestre());

        if (estudiante.getCarrera() != null)
            dashboard.setCarrera(estudiante.getCarrera().getNombre());

        int materiasInscritas = estudiante.getMateriasInscritas() != null ?
                estudiante.getMateriasInscritas().size() : 0;
        int materiasAprobadas = estudiante.getMateriasAprobadas() != null ?
                estudiante.getMateriasAprobadas().size() : 0;

        dashboard.setMateriasInscritas(materiasInscritas);
        dashboard.setMateriasAprobadas(materiasAprobadas);

        int creditosInscritos = calcularCreditosInscritos(estudiante);
        int creditosAprobados = calcularCreditosAprobados(estudiante);

        dashboard.setCreditosInscritos(creditosInscritos);
        dashboard.setCreditosAprobados(creditosAprobados);
        dashboard.setPromedioGeneral(0.0);
        dashboard.setMateriasActuales(new ArrayList<>());
        dashboard.setCalificacionesRecientes(new ArrayList<>());
        return dashboard;
    }

    private int calcularCreditosInscritos(Estudiante estudiante) {
        int creditos = 0;
        if (estudiante.getMateriasInscritas() != null)
            for (Materia matricula : estudiante.getMateriasInscritas())
                if (matricula.getParaleloMateria() != null && matricula.getParaleloMateria().getMateria() != null)
                    creditos += matricula.getParaleloMateria().getMateria().getCreditos();
        return creditos;
    }

    private int calcularCreditosAprobados(Estudiante estudiante) {
        int creditos = 0;
        if (estudiante.getMateriasAprobadas() != null)
            for (Materia materia : estudiante.getMateriasAprobadas())
                creditos += materia.getCreditos();
        return creditos;
    }
}
