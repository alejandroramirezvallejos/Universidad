package com.example.Server.repositorios.implementaciones;
import com.example.Server.modelos.abstracciones.IMatricula;
import com.example.Server.modelos.implementaciones.Matricula;
import com.example.Server.repositorios.abstracciones.IRepositorioMatricula;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioMatricula implements IRepositorioMatricula {
    private final Map<String, List<Matricula>> matriculas = new HashMap<>();

    @Override
    public IMatricula guardar(IMatricula matricula) {
        Matricula matriculaImpl = (Matricula) matricula;
        String codigo = matriculaImpl.getEstudiante().getCodigo();

        if (!matriculas.containsKey(codigo))
            matriculas.put(codigo, new ArrayList<>());

        matriculas.get(codigo).add(matriculaImpl);

        return matriculaImpl;
    }

    @Override
    public List<IMatricula> getMatriculas() {
        List<IMatricula> matriculasLista = new ArrayList<>();

        for (List<Matricula> lista : matriculas.values())
            matriculasLista.addAll(lista);

        return matriculasLista;
    }

    @Override
    public void eliminar(IMatricula matricula) {
        Matricula matriculaImpl = (Matricula) matricula;
        List<Matricula> matriculasLista = matriculas.get(matriculaImpl.getEstudiante().getCodigo());

        if (matriculasLista != null)
            matriculasLista.remove(matriculaImpl);
    }

    @Override
    public List<IMatricula> buscar(String codigoEstudiante) {
        List<IMatricula> resultado = new ArrayList<>();
        List<Matricula> lista = matriculas.get(codigoEstudiante);

        if (lista != null)
            resultado.addAll(lista);

        return resultado;
    }
}
