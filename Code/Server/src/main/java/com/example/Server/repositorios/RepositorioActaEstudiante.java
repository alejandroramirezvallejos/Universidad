package com.example.Server.repositorios;
import com.example.Server.modelos.ActaEstudiante;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioActaEstudiante {
    private final Map<String, List<ActaEstudiante>> actas = new HashMap<>();

    public ActaEstudiante guardar(ActaEstudiante acta) {
        String codigo = acta.getEstudiante().getCodigo();

        if (!actas.containsKey(codigo))
            actas.put(codigo, new ArrayList<>());

        actas.get(codigo).add(acta);

        return acta;
    }

    public List<ActaEstudiante> getActas() {
        List<ActaEstudiante> actaLista = new ArrayList<>();

        for (List<ActaEstudiante> lista : actas.values())
            actaLista.addAll(lista);

        return actaLista;
    }

    public void eliminar(ActaEstudiante acta) {
        List<ActaEstudiante> actaLista = actas.get(acta.getEstudiante().getCodigo());

        if (actaLista != null)
            actaLista.remove(acta);
    }

    public List<ActaEstudiante> buscarPorEstudiante(String codigoEstudiante) {
        return actas.getOrDefault(codigoEstudiante, new ArrayList<>());
    }
}

