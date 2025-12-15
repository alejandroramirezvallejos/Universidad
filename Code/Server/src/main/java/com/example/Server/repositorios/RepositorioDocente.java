package com.example.Server.repositorios;
import com.example.Server.modelos.Docente;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioDocente {
    private final Map<String, Docente> docentes = new HashMap<>();

    public Docente guardar(Docente docente) {
        docentes.put(docente.getCodigo(), docente);
        return docente;
    }

    public List<Docente> getDocentes() {
        return new ArrayList<>(docentes.values());
    }

    public void eliminar(Docente docente) {
        docentes.remove(docente.getCodigo());
    }

    public Docente buscarPorCodigo(String codigo) {
        return docentes.get(codigo);
    }

    public Docente buscarPorEmail(String email) {
        for (Docente docente : docentes.values())
            if (docente.getEmail() != null && docente.getEmail().equals(email))
                return docente;

        return null;
    }
}
