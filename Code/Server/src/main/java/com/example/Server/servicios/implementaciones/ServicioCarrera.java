package com.example.Server.servicios.implementaciones;

import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioCarrera;
import com.example.Server.servicios.abstracciones.IServicioCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioCarrera implements IServicioCarrera {
    private final IRepositorioCarrera repositorio;

    @Override
    public ICarrera crear(ICarrera carrera) {
        return repositorio.guardar(carrera);
    }

    @Override
    public List<ICarrera> getCarreras() {
        return repositorio.getCarreras();
    }

    @Override
    public void eliminar(ICarrera carrera) {
        repositorio.eliminar(carrera);
    }
}
