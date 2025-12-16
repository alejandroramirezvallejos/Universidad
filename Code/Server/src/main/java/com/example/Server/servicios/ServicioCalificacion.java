package com.example.Server.servicios;
import com.example.Server.modelos.Calificacion;
import com.example.Server.modelos.Estudiante;
import com.example.Server.repositorios.RepositorioCalificacion;
import com.example.Server.repositorios.RepositorioEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioCalificacion {
    private final RepositorioCalificacion repositorio;
    private final RepositorioEstudiante repositorioEstudiante;

    public Calificacion crear(Calificacion calificacion) {
        return repositorio.guardar(calificacion);
    }

    public List<Calificacion> getCalificaciones() {
        return repositorio.getCalificaciones();
    }

    public List<Calificacion> getCalificacionesPorEstudiante(String estudianteCodigo) {

        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(estudianteCodigo);
        if (estudiante == null)
            return new ArrayList<>();

        return repositorio.buscarPorEstudiante(estudianteCodigo);
    }

    public void eliminar(Calificacion calificacion) {
        repositorio.eliminar(calificacion);
    }
}
