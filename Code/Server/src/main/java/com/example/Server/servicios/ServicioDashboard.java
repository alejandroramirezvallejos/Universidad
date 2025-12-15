package com.example.Server.servicios;
import com.example.Server.dtos.DtoDashboardEstudiante;
import com.example.Server.dtos.DtoDashboardDocente;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioDashboard {
    @Autowired
    private RepositorioEstudiante repositorioEstudiante;
    @Autowired
    private RepositorioDocente repositorioDocente;
    @Autowired
    private RepositorioParaleloMateria repositorioParalelo;

    public DtoDashboardEstudiante generarDashboardEstudiante(String codigoEstudiante) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(codigoEstudiante);
        
        if (estudiante == null)
            return null;

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

    public DtoDashboardDocente generarDashboardDocente(String codigoDocente) {
        Docente docente = repositorioDocente.buscarPorCodigo(codigoDocente);
        
        if (docente == null)
            return null;

        DtoDashboardDocente dashboard = new DtoDashboardDocente();
        dashboard.setCodigoDocente(docente.getCodigo());
        dashboard.setNombreCompleto(docente.getNombre() + " " + docente.getApellido());
        dashboard.setDepartamento(docente.getDepartamento());
        dashboard.setEspecialidad(docente.getEspecialidad());
        int paralelosActivos = 0;

        for (ParaleloMateria paralelo : repositorioParalelo.getParalelos())
            if (paralelo.getDocente() != null && paralelo.getDocente().getCodigo().equals(codigoDocente))
                paralelosActivos++;

        dashboard.setParalelosActivos(paralelosActivos);
        int totalEstudiantes = 0;

        for (ParaleloMateria paralelo : repositorioParalelo.getParalelos())
            if (paralelo.getDocente() != null && paralelo.getDocente().getCodigo().equals(codigoDocente))
                if (paralelo.getEstudiantes() != null)
                    totalEstudiantes += paralelo.getEstudiantes().size();

        dashboard.setTotalEstudiantes(totalEstudiantes);
        List<String> materiasUnicas = getStrings(codigoDocente);
        dashboard.setMateriasImpartidas(materiasUnicas.size());
        dashboard.setParalelosActuales(new ArrayList<>());
        dashboard.setEstadisticasMaterias(new ArrayList<>());

        return dashboard;
    }

    private @NotNull List<String> getStrings(String codigoDocente) {
        List<String> materiasUnicas = new ArrayList<>();

        for (ParaleloMateria paralelo : repositorioParalelo.getParalelos())
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

    private int calcularCreditosInscritos(Estudiante estudiante) {
        if (estudiante.getMateriasInscritas() == null)
            return 0;

        int total = 0;

        for (Materia materia : estudiante.getMateriasInscritas())
            total += materia.getCreditos();

        return total;
    }

    private int calcularCreditosAprobados(Estudiante estudiante) {
        if (estudiante.getMateriasAprobadas() == null)
            return 0;

        int total = 0;

        for (Materia materia : estudiante.getMateriasAprobadas())
            total += materia.getCreditos();

        return total;
    }
}
