package com.example.Server.dtos;
import lombok.Data;

@Data
public class DtoPromedio {
    private String estudianteCodigo;
    private Double promedio;
    private Integer totalMaterias;
}
