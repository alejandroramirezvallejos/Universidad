package com.example.Server.repositorios;
import com.example.Server.modelos.Evaluacion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioEvaluacion {
    private final List<Evaluacion> evaluaciones = new ArrayList<>();

    public Evaluacion guardar(Evaluacion evaluacion) {
        evaluaciones.add(evaluacion);
        return evaluacion;
    }

    public List<Evaluacion> getEvaluaciones() {
        return new ArrayList<>(evaluaciones);
    }

    public void eliminar(Evaluacion evaluacion) {
        evaluaciones.remove(evaluacion);
    }
}

