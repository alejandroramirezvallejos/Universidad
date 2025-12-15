package com.example.Server.repositorios;
import com.example.Server.modelos.Materia;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioMateria {
    private final Map<String, Materia> materias = new HashMap<>();

    public Materia guardar(Materia materia) {
        materias.put(materia.getCodigo(), materia);
        return materia;
    }

    public List<Materia> getMaterias() {
        return new ArrayList<>(materias.values());
    }

    public void eliminar(Materia materia) {
        materias.remove(materia.getCodigo());
    }

    public Materia buscarPorCodigo(String codigo) {
        return materias.get(codigo);
    }
}
