package com.example.Server.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoMateriaConParalelos {
    private String codigo;
    private String nombre;
    private int creditos;
    private int semestre;
    private List<DtoParaleloDetalle> paralelos;
}
