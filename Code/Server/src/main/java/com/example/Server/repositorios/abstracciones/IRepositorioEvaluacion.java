package com.example.Server.repositorios.abstracciones;

import com.example.Server.modelos.abstracciones.IEvaluacion;
import java.util.List;

public interface IRepositorioEvaluacion {
    IEvaluacion guardar(IEvaluacion evaluacion);
    List<IEvaluacion> getEvaluaciones();
    void eliminar(IEvaluacion evaluacion);
    List<IEvaluacion> buscarPorParalelo(String paraleloCodigo);
    IEvaluacion buscarPorCodigo(String codigo);
}

