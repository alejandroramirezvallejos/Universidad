package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenna;
    private String rol;
}
