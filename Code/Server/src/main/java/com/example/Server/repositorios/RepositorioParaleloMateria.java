package com.example.Server.repositorios;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioParaleloMateria {
    private final Map<String, ParaleloMateria> paraleloMaterias = new HashMap<>();

    public ParaleloMateria guardar(ParaleloMateria paraleloMateria) {
        paraleloMaterias.put(paraleloMateria.getCodigo(), paraleloMateria);
        return paraleloMateria;
    }

    public List<ParaleloMateria> getParalelos() {
        return new ArrayList<>(paraleloMaterias.values());
    }

    public void eliminar(ParaleloMateria paraleloMateria) {
        paraleloMaterias.remove(paraleloMateria.getCodigo());
    }

    public ParaleloMateria buscarPorCodigo(String codigo) {
        return paraleloMaterias.get(codigo);
    }
}
