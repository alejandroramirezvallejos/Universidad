package com.example.Server.casts;

import com.example.Server.dtos.DtoDashboardDocente;
import com.example.Server.modelos.Docente;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CastDashboardDocente {

    public DtoDashboardDocente getDto(Docente docente, List<ParaleloMateria> todosLosParalelos) {
        DtoDashboardDocente dashboard = new DtoDashboardDocente();
        dashboard.setCodigoDocente(docente.getCodigo());
        dashboard.setNombreCompleto(docente.getNombre() + " " + docente.getApellido());
        dashboard.setDepartamento(docente.getDepartamento());
        dashboard.setEspecialidad(docente.getEspecialidad());

        int paralelosActivos = 0;
        for (ParaleloMateria paralelo : todosLosParalelos)
            if (paralelo.getDocente() != null && paralelo.getDocente().getCodigo().equals(docente.getCodigo()))
                paralelosActivos++;

        dashboard.setParalelosActivos(paralelosActivos);

        int totalEstudiantes = 0;
        for (ParaleloMateria paralelo : todosLosParalelos)
            if (paralelo.getDocente() != null && paralelo.getDocente().getCodigo().equals(docente.getCodigo()))
                if (paralelo.getEstudiantes() != null)
                    totalEstudiantes += paralelo.getEstudiantes().size();

        dashboard.setTotalEstudiantes(totalEstudiantes);

        List<String> materiasUnicas = getStrings(docente.getCodigo(), todosLosParalelos);
        dashboard.setMateriasImpartidas(materiasUnicas.size());
        dashboard.setParalelosActuales(new ArrayList<>());
        dashboard.setEstadisticasMaterias(new ArrayList<>());
        return dashboard;
    }

    private List<String> getStrings(String codigoDocente, List<ParaleloMateria> todosLosParalelos) {
        List<String> materiasUnicas = new ArrayList<>();
        for (ParaleloMateria paralelo : todosLosParalelos)
            if (paralelo.getDocente() != null && paralelo.getDocente().getCodigo().equals(codigoDocente)) {
                String codigoMateria = paralelo.getMateria().getCodigo();
                boolean existe = false;
                for (String codigo : materiasUnicas)
                    if (codigo.equals(codigoMateria)) {
                        existe = true;
                        break;
                    }
                if (!existe)
                    materiasUnicas.add(codigoMateria);
            }
        return materiasUnicas;
    }
}
