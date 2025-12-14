package com.example.Server.modelos;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Aula {
    private boolean disponible = true;
    private Integer capacidad;
    private String edificio;
    private String codigo;
}



