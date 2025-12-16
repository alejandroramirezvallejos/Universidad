package com.example.Server.servicios;
import com.example.Server.modelos.Estudiante;
import com.example.Server.repositorios.RepositorioEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioEstudiante {
    private final RepositorioEstudiante repositorio;

    public Estudiante crear(Estudiante estudiante) {
        return repositorio.guardar(estudiante);
    }

    public List<Estudiante> getEstudiantes() {
        return repositorio.getEstudiantes();
    }

    public void eliminar(Estudiante estudiante) {
        repositorio.eliminar(estudiante);
    }
}
