package com.example.Server.repositorios.implementaciones;

import com.example.Server.modelos.abstracciones.IDocente;
import com.example.Server.modelos.implementaciones.Docente;
import com.example.Server.repositorios.abstracciones.IRepositorioDocente;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioDocente implements IRepositorioDocente {
    private final Map<String, Docente> docentes = new HashMap<>();

    @Override
    public IDocente guardar(IDocente docente) {
        Docente docenteImpl = (Docente) docente;
        docentes.put(docenteImpl.getCodigo(), docenteImpl);
        return docenteImpl;
    }

    @Override
    public List<IDocente> getDocentes() {
        List<IDocente> resultado = new ArrayList<>();
        for (Docente docente : docentes.values())
            resultado.add(docente);
        return resultado;
    }

    @Override
    public void eliminar(IDocente docente) {
        docentes.remove(docente.getCodigo());
    }

    @Override
    public IDocente buscarPorCodigo(String codigo) {
        return docentes.get(codigo);
    }

    @Override
    public IDocente buscarPorEmail(String email) {
        for (Docente docente : docentes.values())
            if (docente.getEmail() != null && docente.getEmail().equals(email))
                return docente;

        return null;
    }
}
