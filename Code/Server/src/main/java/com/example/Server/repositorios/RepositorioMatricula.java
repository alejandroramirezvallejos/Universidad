package com.example.Server.repositorios;
import com.example.Server.modelos.Matricula;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioMatricula {
    private final Map<String, List<Matricula>> matriculas = new HashMap<>();

    public Matricula guardar(Matricula matricula) {
        String codigo = matricula.getEstudiante().getCodigo();

        if (!matriculas.containsKey(codigo))
            matriculas.put(codigo, new ArrayList<>());

        matriculas.get(codigo).add(matricula);

        return matricula;
    }

    public List<Matricula> getMatriculas() {
        List<Matricula> matriculasLista = new ArrayList<>();

        for (List<Matricula> lista : matriculas.values())
            matriculasLista.addAll(lista);

        return matriculasLista;
    }

    public void eliminar(Matricula matricula) {
        List<Matricula> matriculasLista = matriculas.get(matricula.getEstudiante().getCodigo());

        if (matriculasLista != null)
            matriculasLista.remove(matricula);
    }

    public List<Matricula> buscarPorEstudiante(String codigoEstudiante) {
        return matriculas.getOrDefault(codigoEstudiante, new ArrayList<>());
    }
}

