package com.example.Server.validadores.registro.estudiantes;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioCarrera;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
@RequiredArgsConstructor
public class ValidarCarreraEstudiante implements IValidadorRegistroEstudiante {
    private final IRepositorioCarrera repositorioCarrera;

    @Override
    public void validar(IEstudiante estudiante) {
        if (estudiante.getCarrera() == null || estudiante.getCarrera().getCodigo() == null)
            throw new RuntimeException("La carrera es requerida");

        ICarrera carrera = repositorioCarrera.buscar(estudiante.getCarrera().getCodigo());

        if (carrera == null)
            throw new RuntimeException("La carrera no existe");

        estudiante.setCarrera(carrera);
    }
}

