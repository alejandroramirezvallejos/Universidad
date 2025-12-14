package com.example.Server.repositorios;
import com.example.Server.modelos.Docente;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioDocente {
    private final List<Docente> docentes = new ArrayList<>();

    public Docente guardar(Docente docente) {
        docentes.add(docente);
        return docente;
    }

    public List<Docente> getDocentes() {
        return new ArrayList<>(docentes);
    }

    public void eliminar(Docente docente) {
        docentes.remove(docente);
    }

    public Docente buscarPorCodigo(String codigo) {
        return docentes.stream()
                .filter(d -> d.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public Docente buscarPorEmail(String email) {
        return docentes.stream()
                .filter(d -> d.getEmail() != null && d.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}

