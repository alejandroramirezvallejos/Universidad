package com.example.Server.repositorios.implementaciones;

import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.modelos.implementaciones.ParaleloMateria;
import com.example.Server.repositorios.abstracciones.IRepositorioParaleloMateria;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioParaleloMateria implements IRepositorioParaleloMateria {
    private final Map<String, ParaleloMateria> paraleloMaterias = new HashMap<>();

    @Override
    public IParaleloMateria guardar(IParaleloMateria paralelo) {
        ParaleloMateria paraleloImpl = (ParaleloMateria) paralelo;
        paraleloMaterias.put(paraleloImpl.getCodigo(), paraleloImpl);
        return paraleloImpl;
    }

    @Override
    public List<IParaleloMateria> getParalelos() {
        List<IParaleloMateria> resultado = new ArrayList<>();
        for (ParaleloMateria paralelo : paraleloMaterias.values())
            resultado.add(paralelo);
        return resultado;
    }

    @Override
    public void eliminar(IParaleloMateria paralelo) {
        paraleloMaterias.remove(paralelo.getCodigo());
    }

    @Override
    public IParaleloMateria buscarPorCodigo(String codigo) {
        return paraleloMaterias.get(codigo);
    }
}
