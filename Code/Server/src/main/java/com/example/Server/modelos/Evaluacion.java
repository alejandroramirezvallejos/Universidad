package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion {
    @Builder.Default
    private String codigo = UUID.randomUUID().toString();
    private String nombre;
    private Double porcentaje;
    private ParaleloMateria paraleloMateria;
    private List<Calificacion> calificaciones;
}
