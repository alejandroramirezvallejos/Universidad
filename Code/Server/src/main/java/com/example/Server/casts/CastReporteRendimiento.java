package com.example.Server.casts;
import com.example.Server.dtos.DtoReporteRendimiento;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CastReporteRendimiento {

    public DtoReporteRendimiento getDto(ParaleloMateria paralelo) {
        DtoReporteRendimiento reporte = new DtoReporteRendimiento();
        reporte.setCodigoParalelo(paralelo.getCodigo());

        if (paralelo.getMateria() != null) {
            reporte.setCodigoMateria(paralelo.getMateria().getCodigo());
            reporte.setNombreMateria(paralelo.getMateria().getNombre());
        }

        if (paralelo.getDocente() != null)
            reporte.setNombreDocente(paralelo.getDocente().getNombre() + " " +
                    paralelo.getDocente().getApellido());

        List<Estudiante> estudiantes = paralelo.getEstudiantes();
        int total = estudiantes != null ? estudiantes.size() : 0;

        reporte.setTotalEstudiantes(total);
        reporte.setPromedioGeneral(0.0);
        reporte.setAprobados(0);
        reporte.setReprobados(0);
        reporte.setSinCalificar(total);
        reporte.setEstudiantes(new ArrayList<>());
        return reporte;
    }
}
