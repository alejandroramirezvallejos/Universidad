package com.example.Server.repositorios.implementaciones;

import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.implementaciones.Evaluacion;
import com.example.Server.repositorios.abstracciones.IRepositorioEvaluacion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioEvaluacion implements IRepositorioEvaluacion {
    private final Map<String, List<Evaluacion>> evaluaciones = new HashMap<>();

    @Override
    public IEvaluacion guardar(IEvaluacion evaluacion) {
        Evaluacion evaluacionImpl = (Evaluacion) evaluacion;
        String codigo = evaluacionImpl.getParaleloMateria().getCodigo();

        if (!evaluaciones.containsKey(codigo))
            evaluaciones.put(codigo, new ArrayList<>());

        List<Evaluacion> lista = evaluaciones.get(codigo);
        List<Evaluacion> eliminar = new ArrayList<>();

        for (Evaluacion evaluacionExistente : lista)
            if (evaluacionExistente.getCodigo().equals(evaluacionImpl.getCodigo()))
                eliminar.add(evaluacionExistente);

        lista.removeAll(eliminar);
        lista.add(evaluacionImpl);

        return evaluacionImpl;
    }

    @Override
    public List<IEvaluacion> getEvaluaciones() {
        List<IEvaluacion> evaluacionLista = new ArrayList<>();

        for (List<Evaluacion> lista : evaluaciones.values())
            for (Evaluacion evaluacion : lista)
                evaluacionLista.add(evaluacion);

        return evaluacionLista;
    }

    @Override
    public void eliminar(IEvaluacion evaluacion) {
        Evaluacion evaluacionImpl = (Evaluacion) evaluacion;
        String codigo = evaluacionImpl.getParaleloMateria().getCodigo();

        if (evaluaciones.containsKey(codigo)) {
            List<Evaluacion> lista = evaluaciones.get(codigo);
            List<Evaluacion> eliminar = new ArrayList<>();
            for (Evaluacion e : lista)
                if (e.getCodigo().equals(evaluacionImpl.getCodigo()))
                    eliminar.add(e);
            lista.removeAll(eliminar);
        }
    }

    @Override
    public List<IEvaluacion> buscarPorParalelo(String paraleloCodigo) {
        List<IEvaluacion> resultado = new ArrayList<>();
        List<Evaluacion> lista = evaluaciones.get(paraleloCodigo);
        if (lista != null)
            for (Evaluacion evaluacion : lista)
                resultado.add(evaluacion);
        return resultado;
    }

    @Override
    public IEvaluacion buscarPorCodigo(String codigo) {
        for (List<Evaluacion> evaluacionLista : evaluaciones.values())
            for (Evaluacion evaluacion : evaluacionLista)
                if (evaluacion.getCodigo().equals(codigo))
                    return evaluacion;

        return null;
    }
}
