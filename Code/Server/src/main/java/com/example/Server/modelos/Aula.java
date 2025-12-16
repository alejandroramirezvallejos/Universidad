package com.example.Server.modelos;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Aula {
    @Builder.Default
    private boolean disponible = true;
    private Integer capacidad;
    private String edificio;
    private String codigo;
}
