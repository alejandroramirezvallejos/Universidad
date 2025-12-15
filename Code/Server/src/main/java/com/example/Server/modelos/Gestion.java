package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gestion {
    private String codigo;
    private String nombre;
    private Integer anio;
    private Integer periodo;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaInicioMatricula;
    private Date fechaFinMatricula;
    private String estado;
    
    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}
