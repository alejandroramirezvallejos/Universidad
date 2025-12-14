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
        return estudiantes.stream()
                .filter(e -> e.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public Estudiante buscarPorEmail(String email) {
        return estudiantes.stream()
                .filter(e -> e.getEmail() != null && e.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}

