package com.example.Server.repositorios;
import com.example.Server.modelos.Aula;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioAula {
    private final List<Aula> aulas = new ArrayList<>();

    public Aula guardar(Aula aula) {
        aulas.add(aula);
        return aula;
    }

    public List<Aula> getAulas() {
        return new ArrayList<>(aulas);
    }

    public void eliminar(Aula aula) {
        aulas.remove(aula);
    }
}

