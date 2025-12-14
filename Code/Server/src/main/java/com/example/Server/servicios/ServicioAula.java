package com.example.Server.servicios;
import com.example.Server.modelos.Aula;
import com.example.Server.repositorios.RepositorioAula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioAula {
    private final RepositorioAula repositorio;

    public Aula crear(Aula aula) {
        return repositorio.guardar(aula);
    }

    public List<Aula> getAulas() {
        return repositorio.getAulas();
    }

    public void eliminar(Aula aula) {
        repositorio.eliminar(aula);
    }
}
