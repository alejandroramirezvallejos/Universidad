package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.abstracciones.IMateria;
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
    public IMateria agregar(IMateria materia, ICarrera carrera) {
        for (ICarrera carreraExistente : repositorioCarrera.getCarreras())
            if (carreraExistente.getCodigo().equals(carrera.getCodigo())) {
                carreraExistente.getMaterias().add(materia);
                repositorioCarrera.guardar(carreraExistente);
                break;
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
