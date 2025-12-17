package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.implementaciones.Carrera;
import com.example.Server.repositorios.abstracciones.IRepositorioCarrera;
import com.example.Server.repositorios.abstracciones.IRepositorioMateria;
import com.example.Server.servicios.abstracciones.IServicioMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioMateria implements IServicioMateria {
    private final IRepositorioMateria repositorio;
    private final IRepositorioCarrera repositorioCarrera;

    @Override
    public IMateria agregar(IMateria materia, Carrera carrera) {
        Carrera carreraExistente = (Carrera) repositorioCarrera.buscar(carrera.getCodigo());
        
        if (carreraExistente != null) {
            carreraExistente.getMaterias().add(materia);
            repositorioCarrera.guardar(carreraExistente);
        }

        return repositorio.guardar(materia);
    }

    @Override
    public List<IMateria> getMaterias() {
        return repositorio.getMaterias();
    }

    @Override
    public IMateria crear(IMateria materia) {
        return repositorio.guardar(materia);
    }

    @Override
    public void eliminar(IMateria materia) {
        repositorio.eliminar(materia);
    }

    @Override
    public IMateria getMateria(String codigo) {
        return repositorio.buscar(codigo);
    }

    @Override
    public IMateria actualizar(String codigo, IMateria materiaDto) {
        IMateria materia = getMateria(codigo);

        if (materia == null)
            throw new RuntimeException("Materia no encontrada");

        materia.setNombre(materiaDto.getNombre());
        materia.setSemestre(materiaDto.getSemestre());
        materia.setCreditos(materiaDto.getCreditos());
        
        if (materiaDto.getCarrera() != null) {
            materia.setCarrera(materiaDto.getCarrera());
        }

        return crear(materia);
    }

    @Override
    public IMateria setEstado(String codigo) {
        IMateria materia = getMateria(codigo);

        if (materia == null)
            throw new RuntimeException("Materia no encontrada");

        materia.setActiva(!materia.isActiva());

        return crear(materia);
    }
}
