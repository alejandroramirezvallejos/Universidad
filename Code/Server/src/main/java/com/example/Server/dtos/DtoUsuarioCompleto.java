package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoUsuarioCompleto {
    private String codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private String codigoEstudiante;
    private DtoCarrera carrera;
    private Integer semestre;
    private String codigoDocente;
    private String departamento;
    private String especialidad;
    private String codigoDirector;
}
