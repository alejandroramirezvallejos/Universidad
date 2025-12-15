package com.example.Server.repositorios;
import com.example.Server.modelos.Aula;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioAula {
    private final Map<String, Aula> aulas = new HashMap<>();

    public Aula guardar(Aula aula) {
        aulas.put(aula.getCodigo(), aula);
        return aula;
    }

    public List<Aula> getAulas() {
        return new ArrayList<>(aulas.values());
    }

    public void eliminar(Aula aula) {
        aulas.remove(aula.getCodigo());
    }

    public Aula buscarPorCodigo(String codigo) {
        return aulas.get(codigo);
    }
}

