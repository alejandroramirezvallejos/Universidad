package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.IAula;
import com.example.Server.repositorios.abstracciones.IRepositorioAula;
import com.example.Server.servicios.abstracciones.IServicioAula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioAula implements IServicioAula {
    private final IRepositorioAula repositorio;

    @Override
    public IAula crear(IAula aula) {
        return repositorio.guardar(aula);
    }

    @Override
    public List<IAula> getAulas() {
        return repositorio.getAulas();
    }

    @Override
    public void eliminar(IAula aula) {
        repositorio.eliminar(aula);
    }
}
