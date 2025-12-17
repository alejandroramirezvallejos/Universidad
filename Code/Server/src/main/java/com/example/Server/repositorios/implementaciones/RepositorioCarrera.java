package com.example.Server.repositorios.implementaciones;

import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.implementaciones.Carrera;
import com.example.Server.repositorios.abstracciones.IRepositorioCarrera;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioCarrera implements IRepositorioCarrera {
    private final Map<String, Carrera> carreras = new HashMap<>();

    @Override
    public ICarrera guardar(ICarrera carrera) {
        Carrera carreraImpl = (Carrera) carrera;
        carreras.put(carreraImpl.getCodigo(), carreraImpl);
        return carreraImpl;
    }

    @Override
    public List<ICarrera> getCarreras() {
        List<ICarrera> resultado = new ArrayList<>();
        for (Carrera carrera : carreras.values())
            resultado.add(carrera);
        return resultado;
    }

    @Override
    public void eliminar(ICarrera carrera) {
        carreras.remove(carrera.getCodigo());
    }

    @Override
    public ICarrera buscarPorCodigo(String codigo) {
        return carreras.get(codigo);
    }
}
