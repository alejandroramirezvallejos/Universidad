package com.example.Server.casts;

import com.example.Server.dtos.DtoReporteEstudiantesPorCarrera;
import com.example.Server.modelos.Carrera;
import com.example.Server.modelos.Estudiante;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CastReporteEstudiantes {

    public DtoReporteEstudiantesPorCarrera getDto(Carrera carrera, List<Estudiante> estudiantesCarrera) {
        DtoReporteEstudiantesPorCarrera reporte = new DtoReporteEstudiantesPorCarrera();
        reporte.setCodigoCarrera(carrera.getCodigo());
        reporte.setNombreCarrera(carrera.getNombre());
        reporte.setTotalEstudiantes(estudiantesCarrera.size());
        reporte.setEstudiantesPorSemestre1(contarPorSemestre(estudiantesCarrera, 1));
        reporte.setEstudiantesPorSemestre2(contarPorSemestre(estudiantesCarrera, 2));
        reporte.setEstudiantesPorSemestre3(contarPorSemestre(estudiantesCarrera, 3));
        reporte.setEstudiantesPorSemestre4(contarPorSemestre(estudiantesCarrera, 4));
        reporte.setEstudiantesPorSemestre5(contarPorSemestre(estudiantesCarrera, 5));
        reporte.setEstudiantesPorSemestre6(contarPorSemestre(estudiantesCarrera, 6));
        reporte.setEstudiantesPorSemestre7(contarPorSemestre(estudiantesCarrera, 7));
        reporte.setEstudiantesPorSemestre8(contarPorSemestre(estudiantesCarrera, 8));
        reporte.setEstudiantes(new ArrayList<>());
        return reporte;
    }

    private int contarPorSemestre(List<Estudiante> estudiantes, int semestre) {
        int contador = 0;
        for (Estudiante estudiante : estudiantes)
            if (estudiante.getSemestre() == semestre)
                contador++;
        return contador;
    }
}
