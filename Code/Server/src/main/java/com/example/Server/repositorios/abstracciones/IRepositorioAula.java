package com.example.Server.repositorios.abstracciones;
import com.example.Server.modelos.abstracciones.IAula;
import java.util.List;

public interface IRepositorioAula {
    IAula guardar(IAula aula);
    List<IAula> getAulas();
    void eliminar(IAula aula);
    IAula buscar(String codigo);
}

