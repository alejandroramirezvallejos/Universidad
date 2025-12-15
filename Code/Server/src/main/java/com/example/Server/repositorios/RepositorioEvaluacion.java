package com.example.Server.repositorios;
import com.example.Server.modelos.Evaluacion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioEvaluacion {
    private final Map<String, List<Evaluacion>> evaluaciones = new HashMap<>();

    public Evaluacion guardar(Evaluacion evaluacion) {
        String codigo = evaluacion.getParaleloMateria().getCodigo();

        if (!evaluaciones.containsKey(codigo))
            evaluaciones.put(codigo, new ArrayList<>());

        List<Evaluacion> lista = evaluaciones.get(codigo);
        lista.removeIf(e -> e.getCodigo().equals(evaluacion.getCodigo()));
        lista.add(evaluacion);

        return evaluacion;
    }

    public List<Evaluacion> getEvaluaciones() {
        List<Evaluacion> evaluacionLista = new ArrayList<>();

        for (List<Evaluacion> lista : evaluaciones.values())
            evaluacionLista.addAll(lista);

        return evaluacionLista;
    }

    public void eliminar(Evaluacion evaluacion) {
        String codigo = evaluacion.getParaleloMateria().getCodigo();

        if (evaluaciones.containsKey(codigo))
            evaluaciones.get(codigo).removeIf(e -> e.getCodigo().equals(evaluacion.getCodigo()));
    }

    public List<Evaluacion> buscarPorParalelo(String paraleloCodigo) {
        return evaluaciones.getOrDefault(paraleloCodigo, new ArrayList<>());
    }

    public Evaluacion buscarPorCodigo(String codigo) {
        for (List<Evaluacion> evaluacionLista : evaluaciones.values())
            for (Evaluacion evaluacion : evaluacionLista)
                if (evaluacion.getCodigo().equals(codigo))
                    return evaluacion;

        return null;
    }
}

