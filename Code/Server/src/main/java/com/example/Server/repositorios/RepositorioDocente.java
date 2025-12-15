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
        for (Docente docente : docentes)
            if (docente.getCodigo().equals(codigo))
                return docente;

        return null;
    }

    public Docente buscarPorEmail(String email) {
        for (Docente docente : docentes)
            if (docente.getEmail() != null && docente.getEmail().equals(email))
                return docente;

        return null;
    }
}
