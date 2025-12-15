package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoRegistroEstudiante {
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenna;
    private String codigoEstudiante;
    private String codigoCarrera;
    private Integer semestre;
}
