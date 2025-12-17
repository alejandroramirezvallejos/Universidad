package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import com.example.Server.repositorios.abstracciones.IRepositorioParaleloMateria;
import com.example.Server.servicios.abstracciones.IServicioParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioParaleloMateria implements IServicioParaleloMateria {
    private final IRepositorioParaleloMateria repositorio;
    private final IRepositorioDocente repositorioDocente;

    @Override
    public IParaleloMateria crear(IParaleloMateria paralelo) {
        asociarDocente(paralelo);
        asociarMateria(paralelo);
        return repositorio.guardar(paralelo);
    }

    private void asociarDocente(IParaleloMateria paralelo) {
        IDocente docenteExistente = repositorioDocente.buscarPorCodigo(paralelo.getDocente().getCodigo());

        if (docenteExistente != null) {
            docenteExistente.getParaleloMaterias().add(paralelo);
            repositorioDocente.guardar(docenteExistente);
        }
    }

    private void asociarMateria(IParaleloMateria paralelo) {
        paralelo.getMateria().getParaleloMaterias().add(paralelo);
    }

    @Override
    public List<IParaleloMateria> getParalelos() {
        return repositorio.getParalelos();
    }

    @Override
    public void eliminar(IParaleloMateria paralelo) {
        repositorio.eliminar(paralelo);
    }

    @Override
    public IParaleloMateria getParaleloPorCodigo(String codigo) {
        return repositorio.buscar(codigo);
    }

    @Override
    public List<IParaleloMateria> getParalelosPorDocente(String docenteCodigo) {
        List<IParaleloMateria> resultado = new ArrayList<>();

        for (IParaleloMateria paralelo : repositorio.getParalelos())
            if (paralelo.getDocente() != null && paralelo.getDocente().getCodigo().equals(docenteCodigo))
                resultado.add(paralelo);

        return resultado;
    }

    @Override
    public List<IParaleloMateria> getParalelosPorMateria(String materiaCodigo) {
        List<IParaleloMateria> resultado = new ArrayList<>();

        for (IParaleloMateria paralelo : repositorio.getParalelos())
            if (paralelo.getMateria() != null && paralelo.getMateria().getCodigo().equals(materiaCodigo))
                resultado.add(paralelo);

        return resultado;
    }

    @Override
    public IParaleloMateria actualizar(String codigo, IParaleloMateria paraleloDto) {
        IParaleloMateria paralelo = repositorio.buscar(codigo);
        if (paralelo == null)
            throw new RuntimeException("Paralelo no encontrado");

        paralelo.setMateria(paraleloDto.getMateria());
        paralelo.setDocente(paraleloDto.getDocente());
        paralelo.setAula(paraleloDto.getAula());
        paralelo.setCupoMaximo(paraleloDto.getCupoMaximo());

        if (paraleloDto.getHorarios() != null)
            paralelo.setHorarios(paraleloDto.getHorarios());

        return crear(paralelo);
    }
}
