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

    public List<Calificacion> obtenerPorEstudiante(String estudianteCodigo) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(estudianteCodigo);
        if (estudiante == null)
            return new ArrayList<>();

        List<Calificacion> resultado = new ArrayList<>();

        for (Calificacion calificacion : repositorio.getCalificaciones())
            if (calificacion.getEstudiante().getCodigo().equals(estudianteCodigo))
                resultado.add(calificacion);

        return resultado;
    }

    public void eliminar(Calificacion calificacion) {
        repositorio.eliminar(calificacion);
    }

    public Calificacion actualizar(Calificacion calificacion) {
        return repositorio.guardar(calificacion);
    }
}
