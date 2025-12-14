package com.example.Server.servicios;

import com.example.Server.modelos.Calificacion;
import com.example.Server.modelos.Estudiante;
import com.example.Server.repositorios.RepositorioCalificacion;
import com.example.Server.repositorios.RepositorioEstudiante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para gestión de Calificaciones
 */
@Service
@RequiredArgsConstructor
public class ServicioCalificacion {
    private final RepositorioCalificacion repositorio;
    private final RepositorioEstudiante repositorioEstudiante;

    /**
     * Crea una nueva calificación
     */
    public Calificacion crear(Calificacion calificacion) {
        return repositorio.guardar(calificacion);
    }

    /**
     * Obtiene todas las calificaciones
     */
    public List<Calificacion> getCalificaciones() {
        return repositorio.getCalificaciones();
    }

    /**
     * Obtiene calificaciones de un estudiante
     */
    public List<Calificacion> obtenerPorEstudiante(String estudianteCodigo) {
        Estudiante estudiante = repositorioEstudiante.buscarPorCodigo(estudianteCodigo);
        if (estudiante == null) {
            return new ArrayList<>();
        }
        
        return repositorio.getCalificaciones().stream()
                .filter(c -> c.getEstudiante().getCodigo().equals(estudianteCodigo))
                .collect(Collectors.toList());
    }

    /**
     * Elimina una calificación
     */
    public void eliminar(Calificacion calificacion) {
        repositorio.eliminar(calificacion);
    }

    /**
     * Actualiza una calificación existente
     */
    public Calificacion actualizar(Calificacion calificacion) {
        return repositorio.guardar(calificacion);
    }
}
