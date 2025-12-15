package com.example.Server.repositorios;
import com.example.Server.modelos.Calificacion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioCalificacion {
    private final List<Calificacion> calificaciones = new ArrayList<>();

    public Calificacion guardar(Calificacion calificacion) {
        calificaciones.add(calificacion);
        return calificacion;
    }

    public List<Calificacion> getCalificaciones() {
        return new ArrayList<>(calificaciones);
    }

    public void eliminar(Calificacion calificacion) {
        calificaciones.remove(calificacion);
    }
}
