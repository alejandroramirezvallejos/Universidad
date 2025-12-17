package com.example.Server.repositorios.implementaciones;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.implementaciones.ActaEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioActaEstudiante;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioActaEstudiante implements IRepositorioActaEstudiante {
    private final Map<String, List<ActaEstudiante>> actas = new HashMap<>();

    @Override
    public IActaEstudiante guardar(IActaEstudiante acta) {
        ActaEstudiante actaImpl = (ActaEstudiante) acta;
        String codigo = actaImpl.getEstudiante().getCodigo();

        if (!actas.containsKey(codigo))
            actas.put(codigo, new ArrayList<>());

        actas.get(codigo).add(actaImpl);

        return actaImpl;
    }

    @Override
    public List<IActaEstudiante> getActas() {
        List<IActaEstudiante> actaLista = new ArrayList<>();

        for (List<ActaEstudiante> lista : actas.values())
            actaLista.addAll(lista);

        return actaLista;
    }

    @Override
    public void eliminar(IActaEstudiante acta) {
        ActaEstudiante actaImpl = (ActaEstudiante) acta;
        List<ActaEstudiante> actaLista = actas.get(actaImpl.getEstudiante().getCodigo());

        if (actaLista != null)
            actaLista.remove(actaImpl);
    }

    @Override
    public List<IActaEstudiante> buscar(String codigoEstudiante) {
        List<IActaEstudiante> resultado = new ArrayList<>();
        List<ActaEstudiante> lista = actas.get(codigoEstudiante);

        if (lista != null)
            resultado.addAll(lista);

        return resultado;
    }
}
