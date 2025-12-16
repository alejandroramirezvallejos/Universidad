package com.example.Server.servicios;
import com.example.Server.alertas.ContextoNotificacion;
import com.example.Server.componentes.CalculadoraPromedio;
import com.example.Server.modelos.ActaEstudiante;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.repositorios.RepositorioActaEstudiante;
import com.example.Server.repositorios.RepositorioEvaluacion;
import com.example.Server.validadores.calificacion.IValidarCalificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicioActaEstudiante {
    private final RepositorioActaEstudiante repositorio;
    private final RepositorioEvaluacion repositorioEvaluacion;
    private final ContextoNotificacion contexto;
    private final CalculadoraPromedio calculadora;
    private final IValidarCalificacion validadorCalificacion;

    public ActaEstudiante setActa(Estudiante estudiante, ParaleloMateria paralelo) {
        List<Evaluacion> evaluaciones = repositorioEvaluacion.getEvaluaciones();
        double nota = calculadora.calcular(estudiante, paralelo, evaluaciones);
        ActaEstudiante acta = new ActaEstudiante();
        acta.setEstudiante(estudiante);
        acta.setParaleloMateria(paralelo);
        acta.setCalificacionFinal(nota);
        boolean aprobado = validadorCalificacion.validar(nota);
        acta.setAprobado(aprobado);
        contexto.notificar(estudiante, paralelo.getMateria(), nota);
        if (aprobado) estudiante.getMateriasAprobadas().add(paralelo.getMateria());
        return repositorio.guardar(acta);
    }

    public List<ActaEstudiante> getActas() {
        return repositorio.getActas();
    }

    public List<ActaEstudiante> getActasReprobadas() {
        return getActasPorEstado(false);
    }

    public List<ActaEstudiante> getActasAprobadas() {
        return getActasPorEstado(true);
    }

    private List<ActaEstudiante> getActasPorEstado(boolean aprobado) {
        return repositorio.getActas().stream()
                .filter(acta -> acta.isAprobado() == aprobado)
                .collect(Collectors.toList());
    }

    public void eliminarActa(ActaEstudiante acta) {
        repositorio.eliminar(acta);
    }

    public void notificar(Estudiante estudiante, Materia materia, Double notaFinal) {
        contexto.notificar(estudiante, materia, notaFinal);
    }
}
