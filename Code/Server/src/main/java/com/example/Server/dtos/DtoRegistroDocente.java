package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoRegistroDocente {
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenna;
    private String codigoDocente;
    private String departamento;
    private String especialidad;
}
