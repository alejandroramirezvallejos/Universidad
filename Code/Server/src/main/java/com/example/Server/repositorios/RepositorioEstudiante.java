package com.example.Server.repositorios;
import com.example.Server.modelos.Estudiante;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioEstudiante {
    private final Map<String, Estudiante> estudiantes = new HashMap<>();

    public Estudiante guardar(Estudiante estudiante) {
        estudiantes.put(estudiante.getCodigo(), estudiante);
        return estudiante;
    }

    public List<Estudiante> getEstudiantes() {
        return new ArrayList<>(estudiantes.values());
    }

    public void eliminar(Estudiante estudiante) {
        estudiantes.remove(estudiante.getCodigo());
    }

    public Estudiante buscarPorCodigo(String codigo) {
        return estudiantes.get(codigo);
    }

    public Estudiante buscarPorEmail(String email) {
        for (Estudiante estudiante : estudiantes.values())
            if (estudiante.getEmail() != null && estudiante.getEmail().equals(email))
                return estudiante;

        return null;
    }
}
