package com.example.Server.repositorios;
import com.example.Server.modelos.Carrera;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioCarrera {
    private final Map<String, Carrera> carreras = new HashMap<>();

    public Carrera guardar(Carrera carrera) {
        carreras.put(carrera.getCodigo(), carrera);
        return carrera;
    }

    public List<Carrera> getCarreras() {
        return new ArrayList<>(carreras.values());
    }

    public void eliminar(Carrera carrera) {
        carreras.remove(carrera.getCodigo());
    }

    public Carrera buscarPorCodigo(String codigo) {
        return carreras.get(codigo);
    }
}
