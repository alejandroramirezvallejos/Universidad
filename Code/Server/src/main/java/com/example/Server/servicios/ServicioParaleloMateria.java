package com.example.Server.servicios;
import com.example.Server.modelos.Docente;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.repositorios.RepositorioDocente;
import com.example.Server.repositorios.RepositorioParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioParaleloMateria {
    private final RepositorioParaleloMateria repositorio;
    private final RepositorioDocente repositorioDocente;

    public ParaleloMateria setParalelo(ParaleloMateria paralelo) {
        asociarDocente(paralelo);
        asociarMateria(paralelo);
        return repositorio.guardar(paralelo);
    }

    private void asociarDocente(ParaleloMateria paralelo) {
        Docente docenteExistente = repositorioDocente.buscarPorCodigo(paralelo.getDocente().getCodigo());
        if (docenteExistente != null) {
            docenteExistente.getParaleloMaterias().add(paralelo);
            repositorioDocente.guardar(docenteExistente);
        }
    }

    private void asociarMateria(ParaleloMateria paralelo) {
        Materia materia = paralelo.getMateria();
        materia.getParaleloMaterias().add(paralelo);
    }

    public List<ParaleloMateria> getParalelos() {
        return repositorio.getParalelos();
    }

    public void eliminar(ParaleloMateria paralelo) {
        repositorio.eliminar(paralelo);
    }

    public ParaleloMateria setParalelo(ParaleloMateria paralelo, boolean actualizar) {
        return repositorio.guardar(paralelo);
    }

    public ParaleloMateria getParaleloPorCodigo(String codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }

    public List<ParaleloMateria> getParalelosPorDocente(String docenteCodigo) {
        List<ParaleloMateria> resultado = new ArrayList<>();
        for (ParaleloMateria paralelo : repositorio.getParalelos())
            if (paralelo.getDocente() != null && paralelo.getDocente().getCodigo().equals(docenteCodigo))
                resultado.add(paralelo);
        return resultado;
    }

    public List<ParaleloMateria> getParalelosPorMateria(String materiaCodigo) {
        List<ParaleloMateria> resultado = new ArrayList<>();
        for (ParaleloMateria paralelo : repositorio.getParalelos())
            if (paralelo.getMateria() != null && paralelo.getMateria().getCodigo().equals(materiaCodigo))
                resultado.add(paralelo);

        return resultado;
    }
}



