package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import com.example.Server.servicios.abstracciones.IServicioEstudiante;
import com.example.Server.validadores.registro.estudiantes.ValidacionRegistroEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioEstudiante implements IServicioEstudiante {
    private final IRepositorioEstudiante repositorio;
    private final ValidacionRegistroEstudiante validacionRegistroEstudiante;

    @Override
    public IEstudiante crear(IEstudiante estudiante) {
        validacionRegistroEstudiante.validar(estudiante);
        
        if (estudiante.getSemestre() == 0) {
            estudiante.setSemestre(1);
        }
        
        return repositorio.guardar(estudiante);
    }

    @Override
    public List<IEstudiante> getEstudiantes() {
        return repositorio.getEstudiantes();
    }

    @Override
    public void eliminar(IEstudiante estudiante) {
        repositorio.eliminar(estudiante);
    }
}
