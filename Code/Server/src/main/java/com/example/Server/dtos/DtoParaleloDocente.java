package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoParaleloDocente {
    private String codigoMateria;
    private String nombreMateria;
    private String paralelo;
    private int estudiantesInscritos;
    private int cupoMaximo;
}

