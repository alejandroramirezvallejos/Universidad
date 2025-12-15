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

    public ParaleloMateria crear(ParaleloMateria paralelo) {
        asociarDocente(paralelo);
        asociarMateria(paralelo);
        return repositorio.guardar(paralelo);
    }

    private void asociarDocente(ParaleloMateria paralelo) {
        List<Docente> docentes = repositorioDocente.getDocentes();
        Docente docente = paralelo.getDocente();

        for (Docente docenteExistente : docentes)
            if (docenteExistente.getCodigo().equals(docente.getCodigo())) {
                docenteExistente.getParaleloMaterias().add(paralelo);
                repositorioDocente.guardar(docenteExistente);
                break;
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

    public ParaleloMateria actualizar(ParaleloMateria paralelo) {
        return repositorio.guardar(paralelo);
    }

    public ParaleloMateria buscarPorCodigo(String codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }

    public List<ParaleloMateria> obtenerPorDocente(String docenteCodigo) {
        List<ParaleloMateria> resultado = new ArrayList<>();

        for (ParaleloMateria paralelo : repositorio.getParalelos())
            if (paralelo.getDocente() != null && paralelo.getDocente().getCodigo().equals(docenteCodigo))
                resultado.add(paralelo);

        return resultado;
    }

    public List<ParaleloMateria> obtenerPorMateria(String materiaCodigo) {
        List<ParaleloMateria> resultado = new ArrayList<>();

        for (ParaleloMateria paralelo : repositorio.getParalelos())
            if (paralelo.getMateria() != null && paralelo.getMateria().getCodigo().equals(materiaCodigo))
                resultado.add(paralelo);

        return resultado;
    }
}



