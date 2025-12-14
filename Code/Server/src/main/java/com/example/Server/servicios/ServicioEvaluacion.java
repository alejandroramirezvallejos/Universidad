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

    public Evaluacion crear(ParaleloMateria paralelo, String nombre, Double porcentaje) {
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

    public void agregar(Evaluacion evaluacion, Calificacion calificacion) {
        evaluacion.getCalificaciones().add(calificacion);
        repositorio.guardar(evaluacion);
    }

    public List<Evaluacion> getEvaluaciones() {
        return repositorio.getEvaluaciones();
    }

    public void eliminar(Evaluacion evaluacion) {
        repositorio.eliminar(evaluacion);
    }

    public List<Evaluacion> obtenerPorParalelo(String paraleloCodigo) {
        List<Evaluacion> todasEvaluaciones = repositorio.getEvaluaciones();
        return todasEvaluaciones.stream()
                .filter(e -> e.getParaleloMateria() != null &&
                        e.getParaleloMateria().getCodigo().equals(paraleloCodigo))
                .toList();
    }

    public List<Calificacion> obtenerCalificacionesEstudiante(String estudianteCodigo) {
        List<Evaluacion> todasEvaluaciones = repositorio.getEvaluaciones();
        List<Calificacion> calificaciones = new ArrayList<>();
        
        for (Evaluacion evaluacion : todasEvaluaciones) {
            for (Calificacion calificacion : evaluacion.getCalificaciones()) {
                if (calificacion.getEstudiante() != null &&
                        calificacion.getEstudiante().getCodigo().equals(estudianteCodigo)) {
                    // Establecer la evaluación en la calificación para tener contexto
                    calificacion.setEvaluacion(evaluacion);
                    calificaciones.add(calificacion);
                }
            }
        }
        
        return calificaciones;
    }
}



