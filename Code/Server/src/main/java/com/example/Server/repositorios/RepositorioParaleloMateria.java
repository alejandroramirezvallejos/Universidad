package com.example.Server.repositorios;
import com.example.Server.modelos.ParaleloMateria;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioParaleloMateria {
    private final List<ParaleloMateria> paraleloMaterias = new ArrayList<>();

    public ParaleloMateria guardar(ParaleloMateria paraleloMateria) {
        paraleloMaterias.add(paraleloMateria);
        return paraleloMateria;
    }

    public List<ParaleloMateria> getParalelos() {
        return new ArrayList<>(paraleloMaterias);
    }

    public void eliminar(ParaleloMateria paraleloMateria) {
        paraleloMaterias.remove(paraleloMateria);
    }

    public ParaleloMateria buscarPorCodigo(String codigo) {
        return paraleloMaterias.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}

