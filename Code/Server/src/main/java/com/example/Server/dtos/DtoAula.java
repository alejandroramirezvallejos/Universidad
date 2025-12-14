package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoAula {
    private String codigo;
    private String edificio;
    private Integer capacidad;
    private boolean disponible;
}

