package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.IAula;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Aula implements IAula {
    @Builder.Default
    private boolean disponible = true;
    private Integer capacidad;
    private String edificio;
    private String codigo;
}
