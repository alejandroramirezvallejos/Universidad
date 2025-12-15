package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoActualizarUsuario {
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenna;
    private Integer semestre;
    private String departamento;
    private String especialidad;
}
