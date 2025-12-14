package com.example.Server.servicios;
import com.example.Server.modelos.Carrera;
import com.example.Server.repositorios.RepositorioCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioCarrera {
    private final RepositorioCarrera repositorio;

    public Carrera crear(Carrera carrera) {
        return repositorio.guardar(carrera);
    }

    public List<Carrera> getCarreras() {
        return repositorio.getCarreras();
    }

    public void eliminar(Carrera carrera) {
        repositorio.eliminar(carrera);
    }
}

