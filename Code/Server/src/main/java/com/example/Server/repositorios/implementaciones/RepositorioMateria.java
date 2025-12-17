package com.example.Server.repositorios.implementaciones;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.implementaciones.Materia;
import com.example.Server.repositorios.abstracciones.IRepositorioMateria;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioMateria implements IRepositorioMateria {
    private final Map<String, Materia> materias = new HashMap<>();

    @Override
    public IMateria guardar(IMateria materia) {
        Materia materiaImpl = (Materia) materia;
        materias.put(materiaImpl.getCodigo(), materiaImpl);
        return materiaImpl;
    }

    @Override
    public List<IMateria> getMaterias() {
        return new ArrayList<>(materias.values());
    }

    @Override
    public void eliminar(IMateria materia) {
        materias.remove(materia.getCodigo());
    }

    @Override
    public IMateria buscar(String codigo) {
        return materias.get(codigo);
    }
}
