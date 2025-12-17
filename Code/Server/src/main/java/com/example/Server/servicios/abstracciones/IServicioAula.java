package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.IAula;
import java.util.List;

public interface IServicioAula {
    IAula crear(IAula aula);
    List<IAula> getAulas();
    void eliminar(IAula aula);
}

