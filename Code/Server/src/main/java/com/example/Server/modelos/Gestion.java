package com.example.Server.modelos;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gestion {
    private String codigo;
    private String nombre;
    private Integer anio;
    private Integer periodo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicioMatricula;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaFinMatricula;
    private String estado;
    @Builder.Default
    private List<Materia> materias = new ArrayList<>();

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}
