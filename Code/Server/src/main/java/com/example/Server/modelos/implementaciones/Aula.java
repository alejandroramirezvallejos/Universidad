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
    private Boolean disponible = true;  // Cambiado de boolean a Boolean para permitir null
    private Integer capacidad;
    private String edificio;
    private String codigo;

    // Sobrescribir métodos para Boolean en lugar de boolean
    public Boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
