package com.example.Server.repositorios.implementaciones;
import com.example.Server.modelos.abstracciones.IAula;
import com.example.Server.modelos.implementaciones.Aula;
import com.example.Server.repositorios.abstracciones.IRepositorioAula;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioAula implements IRepositorioAula {
    private final Map<String, Aula> aulas = new HashMap<>();

    @Override
    public IAula guardar(IAula aula) {
        Aula aulaImpl = (Aula) aula;
        aulas.put(aulaImpl.getCodigo(), aulaImpl);
        return aulaImpl;
    }

    @Override
    public List<IAula> getAulas() {
        return new ArrayList<>(aulas.values());
    }

    @Override
    public void eliminar(IAula aula) {
        aulas.remove(aula.getCodigo());
    }

    @Override
    public IAula buscar(String codigo) {
        return aulas.get(codigo);
    }
}
