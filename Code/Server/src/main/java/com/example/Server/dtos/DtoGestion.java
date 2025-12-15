package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoGestion {
    private String codigo;
    private String nombre;
    private Integer anio;
    private Integer periodo;
    private String fechaInicio;
    private String fechaFin;
    private String fechaInicioMatricula;
    private String fechaFinMatricula;
    private String estado;
}
