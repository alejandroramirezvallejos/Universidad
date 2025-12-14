package com.example.Server.repositorios;

import com.example.Server.modelos.Calificacion;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio in-memory para la gestión de Calificaciones
 */
@Repository
public class RepositorioCalificacion {
    private final List<Calificacion> calificaciones = new ArrayList<>();

    /**
     * Guarda una calificación (crear o actualizar)
     */
    public Calificacion guardar(Calificacion calificacion) {
        calificaciones.add(calificacion);
        return calificacion;
    }

    /**
     * Obtiene todas las calificaciones
     */
    public List<Calificacion> getCalificaciones() {
        return new ArrayList<>(calificaciones);
    }

    /**
     * Elimina una calificación
     */
    public void eliminar(Calificacion calificacion) {
        calificaciones.remove(calificacion);
    }
}
