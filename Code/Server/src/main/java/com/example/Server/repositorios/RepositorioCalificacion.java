package com.example.Server.repositorios;
import com.example.Server.modelos.Calificacion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioCalificacion {
    private final Map<String, List<Calificacion>> calificaciones = new HashMap<>();

    public Calificacion guardar(Calificacion calificacion) {
        String codigo = calificacion.getEstudiante().getCodigo();

        if (!calificaciones.containsKey(codigo))
            calificaciones.put(codigo, new ArrayList<>());

        calificaciones.get(codigo).add(calificacion);

        return calificacion;
    }

    public List<Calificacion> getCalificaciones() {
        List<Calificacion> calificacionLista = new ArrayList<>();

        for (List<Calificacion> lista : calificaciones.values())
            calificacionLista.addAll(lista);

        return calificacionLista;
    }

    public void eliminar(Calificacion calificacion) {
        List<Calificacion> CalificacionLista = calificaciones.get(calificacion.getEstudiante().getCodigo());

        if (CalificacionLista != null)
            CalificacionLista.remove(calificacion);
    }

    public List<Calificacion> buscarPorEstudiante(String codigoEstudiante) {
        return calificaciones.getOrDefault(codigoEstudiante, new ArrayList<>());
    }
}
