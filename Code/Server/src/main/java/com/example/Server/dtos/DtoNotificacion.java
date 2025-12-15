package com.example.Server.dtos;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoNotificacion {
    private Estudiante estudiante;
    private Materia materia;
    private Double notaFinal;
}
