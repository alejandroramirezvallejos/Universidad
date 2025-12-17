package com.example.Server.repositorios.implementaciones;

import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.implementaciones.Calificacion;
import com.example.Server.repositorios.abstracciones.IRepositorioCalificacion;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioCalificacion implements IRepositorioCalificacion {
    private final Map<String, List<Calificacion>> calificaciones = new HashMap<>();

    @Override
    public ICalificacion guardar(ICalificacion calificacion) {
        Calificacion calificacionImpl = (Calificacion) calificacion;
        String codigo = calificacionImpl.getEstudiante().getCodigo();

        if (!calificaciones.containsKey(codigo))
            calificaciones.put(codigo, new ArrayList<>());

        calificaciones.get(codigo).add(calificacionImpl);

        return calificacionImpl;
    }

    @Override
    public List<ICalificacion> getCalificaciones() {
        List<ICalificacion> calificacionLista = new ArrayList<>();

        for (List<Calificacion> lista : calificaciones.values())
            for (Calificacion calificacion : lista)
                calificacionLista.add(calificacion);

        return calificacionLista;
    }

    @Override
    public void eliminar(ICalificacion calificacion) {
        Calificacion calificacionImpl = (Calificacion) calificacion;
        List<Calificacion> calificacionLista = calificaciones.get(calificacionImpl.getEstudiante().getCodigo());

        if (calificacionLista != null)
            calificacionLista.remove(calificacionImpl);
    }

    @Override
    public List<ICalificacion> buscarPorEstudiante(String codigoEstudiante) {
        List<ICalificacion> resultado = new ArrayList<>();
        List<Calificacion> lista = calificaciones.get(codigoEstudiante);
        if (lista != null)
            for (Calificacion calificacion : lista)
                resultado.add(calificacion);
        return resultado;
    }
}
