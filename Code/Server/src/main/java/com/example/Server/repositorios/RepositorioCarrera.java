package com.example.Server.repositorios;
import com.example.Server.modelos.Carrera;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioCarrera {
    private final List<Carrera> carreras = new ArrayList<>();

    public Carrera guardar(Carrera carrera) {
        carreras.add(carrera);
        return carrera;
    }

    public List<Carrera> getCarreras() {
        return new ArrayList<>(carreras);
    }

    public void eliminar(Carrera carrera) {
        carreras.remove(carrera);
    }

    public Carrera buscarPorCodigo(String codigo) {
        for (Carrera carrera : carreras)
            if (carrera.getCodigo().equals(codigo))
                return carrera;

        return null;
    }
}
