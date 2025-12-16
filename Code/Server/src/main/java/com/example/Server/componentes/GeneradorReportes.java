package com.example.Server.componentes;

import com.example.Server.modelos.Carrera;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Gestion;
import com.example.Server.modelos.Matricula;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GeneradorReportes {
    public Map<String, Object> generarReporteCarrera(Carrera carrera, List<Estudiante> estudiantes) {
        List<Estudiante> estudiantesCarrera = new ArrayList<>();
        Map<Integer, Integer> porSemestre = new HashMap<>();
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCarrera() != null && estudiante.getCarrera().getCodigo().equals(carrera.getCodigo())) {
                estudiantesCarrera.add(estudiante);
                porSemestre.put(estudiante.getSemestre(), porSemestre.getOrDefault(estudiante.getSemestre(), 0) + 1);
            }
        }
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("codigoCarrera", carrera.getCodigo());
        reporte.put("nombreCarrera", carrera.getNombre());
        reporte.put("totalEstudiantes", estudiantesCarrera.size());
        reporte.put("estudiantesPorSemestre", porSemestre);
        reporte.put("estudiantes", estudiantesCarrera);
        return reporte;
    }

    public Map<String, Object> generarReporteInscripciones(Gestion gestion, List<Matricula> matriculas) {
        List<Matricula> matriculasGestion = new ArrayList<>();
        int pendientes = 0, aceptadas = 0, rechazadas = 0;
        for (Matricula matricula : matriculas) {
            if (matricula.getParaleloMateria() != null && matricula.getParaleloMateria().getGestion() != null &&
                matricula.getParaleloMateria().getGestion().getCodigo().equals(gestion.getCodigo())) {
                matriculasGestion.add(matricula);
                if ("PENDIENTE".equals(matricula.getEstado())) pendientes++;
                else if ("ACEPTADA".equals(matricula.getEstado())) aceptadas++;
                else if ("RECHAZADA".equals(matricula.getEstado())) rechazadas++;
            }
        }
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("codigoGestion", gestion.getCodigo());
        reporte.put("nombreGestion", gestion.getNombre());
        reporte.put("totalInscripciones", matriculasGestion.size());
        reporte.put("inscripcionesPendientes", pendientes);
        reporte.put("inscripcionesAceptadas", aceptadas);
        reporte.put("inscripcionesRechazadas", rechazadas);
        reporte.put("inscripciones", matriculasGestion);
        return reporte;
    }

    public Map<String, Object> generarReporteRendimiento(ParaleloMateria paralelo) {
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("codigoMateria", paralelo.getMateria().getCodigo());
        reporte.put("nombreMateria", paralelo.getMateria().getNombre());
        reporte.put("codigoParalelo", paralelo.getCodigo());
        reporte.put("nombreDocente", paralelo.getDocente().getNombre() + " " + paralelo.getDocente().getApellido());
        reporte.put("totalEstudiantes", paralelo.getEstudiantes().size());
        reporte.put("paralelo", paralelo);
        return reporte;
    }
}

