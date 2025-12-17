package com.example.Server.repositorios.implementaciones;

import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.implementaciones.Estudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioEstudiante implements IRepositorioEstudiante {
    private final Map<String, Estudiante> estudiantes = new HashMap<>();

    @Override
    public IEstudiante guardar(IEstudiante estudiante) {
        Estudiante estudianteImpl = (Estudiante) estudiante;
        estudiantes.put(estudianteImpl.getCodigo(), estudianteImpl);
        return estudianteImpl;
    }

    @Override
    public List<IEstudiante> getEstudiantes() {
        List<IEstudiante> resultado = new ArrayList<>();
        for (Estudiante estudiante : estudiantes.values())
            resultado.add(estudiante);
        return resultado;
    }

    @Override
    public void eliminar(IEstudiante estudiante) {
        estudiantes.remove(estudiante.getCodigo());
    }

    @Override
    public IEstudiante buscarPorCodigo(String codigo) {
        return estudiantes.get(codigo);
    }

    @Override
    public IEstudiante buscarPorEmail(String email) {
        for (Estudiante estudiante : estudiantes.values())
            if (estudiante.getEmail() != null && estudiante.getEmail().equals(email))
                return estudiante;

        return null;
    }
}
