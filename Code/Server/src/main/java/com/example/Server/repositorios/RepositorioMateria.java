package com.example.Server.repositorios;
import com.example.Server.modelos.Materia;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioMateria {
    private final List<Materia> materias = new ArrayList<>();

    public Materia guardar(Materia materia) {
        materias.add(materia);
        return materia;
    }

    public List<Materia> getMaterias() {
        return new ArrayList<>(materias);
    }

    public void eliminar(Materia materia) {
        materias.remove(materia);
    }

    public Materia buscarPorCodigo(String codigo) {
        for (Materia materia : materias)
            if (materia.getCodigo().equals(codigo))
                return materia;

        return null;
    }
}
