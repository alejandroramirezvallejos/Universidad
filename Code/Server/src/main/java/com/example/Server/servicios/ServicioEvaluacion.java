package com.example.Server.servicios;
import com.example.Server.modelos.Calificacion;
import com.example.Server.modelos.Evaluacion;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.repositorios.RepositorioEvaluacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioEvaluacion {
    private final RepositorioEvaluacion repositorio;

    public Evaluacion setEvaluacion(ParaleloMateria paralelo, String nombre, Double porcentaje) {
        Evaluacion evaluacion = construir(paralelo, nombre, porcentaje);
        return repositorio.guardar(evaluacion);
    }

    private Evaluacion construir(ParaleloMateria paralelo, String nombre, Double porcentaje) {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setParaleloMateria(paralelo);
        evaluacion.setNombre(nombre);
        evaluacion.setPorcentaje(porcentaje);
        evaluacion.setCalificaciones(new ArrayList<>());
        return evaluacion;
    }

    public void addCalificacion(Evaluacion evaluacion, Calificacion calificacion) {
        evaluacion.getCalificaciones().add(calificacion);
        repositorio.guardar(evaluacion);
    }

    public List<Evaluacion> getEvaluaciones() {
        return repositorio.getEvaluaciones();
    }

    public void eliminarEvaluacion(Evaluacion evaluacion) {
        repositorio.eliminar(evaluacion);
    }

    public List<Evaluacion> getEvaluacionesPorParalelo(String paraleloCodigo) {
        return repositorio.buscarPorParalelo(paraleloCodigo);
    }

    public List<Calificacion> getCalificacionesEstudiante(String estudianteCodigo) {
        List<Calificacion> calificaciones = new ArrayList<>();
        for (Evaluacion evaluacion : repositorio.getEvaluaciones())
            for (Calificacion calificacion : evaluacion.getCalificaciones())
                if (calificacion.getEstudiante() != null && calificacion.getEstudiante().getCodigo().equals(estudianteCodigo)) {
                    calificacion.setEvaluacion(evaluacion);
                    calificaciones.add(calificacion);
                }
        return calificaciones;
    }

    public Evaluacion setEvaluacion(Evaluacion evaluacion) {
        return setEvaluacion(evaluacion.getParaleloMateria(), evaluacion.getNombre(), evaluacion.getPorcentaje());
    }

    public void addCalificacion(Calificacion calificacion) {
        addCalificacion(calificacion.getEvaluacion(), calificacion);
    }
}


