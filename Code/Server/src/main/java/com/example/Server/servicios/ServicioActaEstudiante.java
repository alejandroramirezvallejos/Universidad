package com.example.Server.servicios;
import com.example.Server.notificaciones.IPublicadorDeNotificaciones;
import com.example.Server.estrategias.calificacion.CalcularCalificacionFinal;
import com.example.Server.estrategias.calificacion.ContextoCalculoCalificacion;
import com.example.Server.estrategias.calificacion.IEstrategiaCalculoCalificacion;
import com.example.Server.modelos.ActaEstudiante;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.NotificacionEvento;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.repositorios.RepositorioActaEstudiante;
import com.example.Server.repositorios.RepositorioEvaluacion;
import com.example.Server.validadores.calificacion.IValidarCalificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioActaEstudiante {
    private final RepositorioActaEstudiante repositorio;
    private final RepositorioEvaluacion repositorioEvaluacion;
    private final IPublicadorDeNotificaciones publicadorDeNotificaciones;
    private final ContextoCalculoCalificacion contextoCalculoCalificacion;
    private final IValidarCalificacion validadorCalificacion;
    private final CalcularCalificacionFinal calcularNotaFinal;

    public ActaEstudiante crear(Estudiante estudiante, ParaleloMateria paralelo) {
        List<Evaluacion> evaluaciones = repositorioEvaluacion.getEvaluaciones();
        double nota = calcular(calcularNotaFinal, estudiante, paralelo, evaluaciones);

        ActaEstudiante acta = new ActaEstudiante();
        acta.setEstudiante(estudiante);
        acta.setParaleloMateria(paralelo);
        acta.setCalificacionFinal(nota);
        boolean aprobado = validadorCalificacion.validar(nota);
        acta.setAprobado(aprobado);

        notificar(estudiante, paralelo.getMateria(), nota);

        if (aprobado)
            estudiante.getMateriasAprobadas().add(paralelo.getMateria());

        return repositorio.guardar(acta);
    }

    private double calcular(IEstrategiaCalculoCalificacion estrategia, Estudiante estudiante,
                            ParaleloMateria paralelo, List<Evaluacion> evaluaciones) {
        contextoCalculoCalificacion.setEstrategia(estrategia);
        return contextoCalculoCalificacion.calcular(estudiante, paralelo, evaluaciones);
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
        List<ActaEstudiante> resultado = new ArrayList<>();
        for (ActaEstudiante acta : repositorio.getActas())
            if (acta.isAprobado() == aprobado)
                resultado.add(acta);
        return resultado;
    }

    public void eliminar(ActaEstudiante acta) {
        repositorio.eliminar(acta);
    }

    public void notificar(Estudiante estudiante, Materia materia, Double notaFinal) {
        NotificacionEvento evento = new NotificacionEvento(estudiante, materia, notaFinal);
        publicadorDeNotificaciones.notificar(evento);
    }
}
