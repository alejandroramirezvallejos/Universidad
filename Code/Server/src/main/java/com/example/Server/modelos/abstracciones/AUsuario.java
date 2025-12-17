package com.example.Server.modelos.abstracciones;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AUsuario implements IUsuario {
    private String codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenna;
    private String rol;
}
