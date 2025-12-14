package com.example.Server.servicios;
import com.example.Server.modelos.Carrera;
import com.example.Server.modelos.Materia;
import com.example.Server.repositorios.RepositorioCarrera;
import com.example.Server.repositorios.RepositorioMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioMateria {
    private final RepositorioMateria repositorio;
    private final RepositorioCarrera repositorioCarrera;

    public Materia agregarCarrera(Materia materia, Carrera carrera) {
        asociarCarrera(materia, carrera);
        return repositorio.guardar(materia);
    }

    private void asociarCarrera(Materia materia, Carrera carrera) {
        List<Carrera> carreras = repositorioCarrera.getCarreras();

        for (Carrera carreraExistente : carreras)
            if (carreraExistente.getCodigo().equals(carrera.getCodigo())) {
                carreraExistente.getMaterias().add(materia);
                repositorioCarrera.guardar(carreraExistente);
                break;
            }
    }

    public List<Materia> getMaterias() {
        return repositorio.getMaterias();
    }

    public Materia crear(Materia materia) {
        return repositorio.guardar(materia);
    }

    public void eliminar(Materia materia) {
        repositorio.eliminar(materia);
    }

    public Materia actualizar(Materia materia) {
        return repositorio.guardar(materia);
    }

    public Materia buscarPorCodigo(String codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }
}



