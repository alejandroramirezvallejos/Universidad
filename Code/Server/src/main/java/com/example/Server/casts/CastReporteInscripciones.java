package com.example.Server.casts;

import com.example.Server.dtos.DtoReporteInscripciones;
import com.example.Server.modelos.Gestion;
import com.example.Server.modelos.Matricula;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CastReporteInscripciones {

    public DtoReporteInscripciones getDto(Gestion gestion, List<Matricula> matriculasGestion) {
        DtoReporteInscripciones reporte = new DtoReporteInscripciones();
        reporte.setCodigoGestion(gestion.getCodigo());
        reporte.setNombreGestion(gestion.getNombre());
        reporte.setTotalInscripciones(matriculasGestion.size());

        int pendientes = 0;
        int aceptadas = 0;
        int rechazadas = 0;

        for (Matricula matricula : matriculasGestion) {
            if ("PENDIENTE".equals(matricula.getEstado()))
                ++pendientes;
            else if ("ACEPTADA".equals(matricula.getEstado()))
                ++aceptadas;
            else if ("RECHAZADA".equals(matricula.getEstado()))
                ++rechazadas;
        }

        reporte.setInscripcionesPendientes(pendientes);
        reporte.setInscripcionesAceptadas(aceptadas);
        reporte.setInscripcionesRechazadas(rechazadas);
        reporte.setInscripciones(new ArrayList<>());
        return reporte;
    }
}
