package com.example.Server.repositorios;
import com.example.Server.modelos.Estudiante;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioEstudiante {
    private final List<Estudiante> estudiantes = new ArrayList<>();

    public Estudiante guardar(Estudiante estudiante) {
        estudiantes.add(estudiante);
        return estudiante;
    }

    public List<Estudiante> getEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    public void eliminar(Estudiante estudiante) {
        estudiantes.remove(estudiante);
    }

    public Estudiante buscarPorCodigo(String codigo) {
        for (Estudiante estudiante : estudiantes)
            if (estudiante.getCodigo().equals(codigo))
                return estudiante;

        return null;
    }

    public Estudiante buscarPorEmail(String email) {
        for (Estudiante estudiante : estudiantes)
            if (estudiante.getEmail() != null && estudiante.getEmail().equals(email))
                return estudiante;

        return null;
    }
}
