package com.example.Server.servicios.abstracciones;

import com.example.Server.modelos.abstracciones.IGestion;

public interface IServicioOfertaAcademica {
    IGestion getOfertaPorGestion(String codigoGestion);
}

