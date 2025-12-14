package com.example.Server.servicios;
import com.example.Server.modelos.ActaEstudiante;
import com.example.Server.modelos.Calificacion;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.alertas.ContextoNotificacion;
import com.example.Server.alertas.NotificarAprobacion;
import com.example.Server.alertas.NotificarReprobacion;
import com.example.Server.repositorios.RepositorioActaEstudiante;
import com.example.Server.repositorios.RepositorioEvaluacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioActaEstudiante {
    private static final double NOTA_MINIMA_APROBACION = 51.0;
    private final RepositorioActaEstudiante repositorio;
    private final RepositorioEvaluacion repositorioEvaluacion;
    private final ContextoNotificacion contexto;

    public ActaEstudiante crear(Estudiante estudiante, ParaleloMateria paralelo) {
        List<Evaluacion> evaluaciones = repositorioEvaluacion.getEvaluaciones();
        double nota = calcularNota(estudiante, paralelo, evaluaciones);

        ActaEstudiante acta = construir(estudiante, paralelo, nota);
        procesarResultado(estudiante, paralelo, nota);

        return repositorio.guardar(acta);
    }

    private ActaEstudiante construir(Estudiante estudiante, ParaleloMateria paralelo, double nota) {
        ActaEstudiante acta = new ActaEstudiante();
        acta.setEstudiante(estudiante);
        acta.setParaleloMateria(paralelo);
        acta.setCalificacionFinal(nota);
        acta.setAprobado(esAprobado(nota));
        return acta;
    }

    private void procesarResultado(Estudiante estudiante, ParaleloMateria paralelo, double nota) {
        if (esAprobado(nota)) {
            contexto.setEstrategia(new NotificarAprobacion());
            contexto.notificar(estudiante, paralelo.getMateria(), nota);
            estudiante.getMateriasAprobadas().add(paralelo.getMateria());
        }
        else {
            contexto.setEstrategia(new NotificarReprobacion());
            contexto.notificar(estudiante, paralelo.getMateria(), nota);
        }
    }

    private boolean esAprobado(double nota) {
        return nota >= NOTA_MINIMA_APROBACION;
    }

    private double calcularNota(Estudiante estudiante, ParaleloMateria paralelo, List<Evaluacion> evaluaciones) {
        List<Evaluacion> evaluacionesParalelo = filtrarPorParalelo(paralelo, evaluaciones);
        return calcularPromedio(estudiante, evaluacionesParalelo);
    }

    private List<Evaluacion> filtrarPorParalelo(ParaleloMateria paralelo, List<Evaluacion> evaluaciones) {
        List<Evaluacion> resultado = new ArrayList<>();

        for (Evaluacion evaluacion : evaluaciones)
            if (evaluacion.getParaleloMateria().getCodigo().equals(paralelo.getCodigo()))
                resultado.add(evaluacion);

        return resultado;
    }

    private double calcularPromedio(Estudiante estudiante, List<Evaluacion> evaluaciones) {
        double suma = 0.0;
        int cantidad = 0;

        for (Evaluacion evaluacion : evaluaciones)
            for (Calificacion calificacion : evaluacion.getCalificaciones()) {
                if (calificacion.getEstudiante().getCodigo().equals(estudiante.getCodigo()))
                    suma += calificacion.getValor();
                    ++cantidad;
                }

        return cantidad > 0 ? suma / cantidad : 0.0;
    }

    public List<ActaEstudiante> getActas() {
        return repositorio.getActas();
    }

    public List<ActaEstudiante> getActasReprobadas() {
        return filtrarPorEstado(false);
    }

    public List<ActaEstudiante> getActasAprobadas() {
        return filtrarPorEstado(true);
    }

    private List<ActaEstudiante> filtrarPorEstado(boolean aprobado) {
        List<ActaEstudiante> actas = repositorio.getActas();
        List<ActaEstudiante> filtradas = new ArrayList<>();

        for (ActaEstudiante acta : actas)
            if (acta.isAprobado() == aprobado)
                filtradas.add(acta);

        return filtradas;
    }

    public void eliminar(ActaEstudiante acta) {
        repositorio.eliminar(acta);
    }
}



