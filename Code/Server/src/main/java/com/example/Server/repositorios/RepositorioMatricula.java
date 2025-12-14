package com.example.Server.repositorios;
import com.example.Server.modelos.Matricula;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioMatricula {
    private final List<Matricula> matriculas = new ArrayList<>();

    public Matricula guardar(Matricula matricula) {
        matriculas.add(matricula);
        return matricula;
    }

    public List<Matricula> getMatriculas() {
        return new ArrayList<>(matriculas);
    }

    public void eliminar(Matricula matricula) {
        matriculas.remove(matricula);
    }
}

