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
    
    public String verificarPeriodoInscripcion() {
        if (!"EN_CURSO".equals(estado))
            return "La gestión no está activa para inscripciones";

        Date hoy = new Date();
        if (fechaInicioMatricula != null && hoy.before(fechaInicioMatricula))
            return "El período de matrícula aún no ha iniciado";

        if (fechaFinMatricula != null && hoy.after(fechaFinMatricula))
            return "El período de matrícula ha finalizado";

        return null;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}
