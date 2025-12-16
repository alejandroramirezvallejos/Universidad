package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Reporte {
    @lombok.Builder.Default
    private Date fechaGeneracion = new Date();
    private String solicitante;
}
