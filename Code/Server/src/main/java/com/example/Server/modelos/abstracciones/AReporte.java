package com.example.Server.modelos.abstracciones;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AReporte implements IReporte {
    @lombok.Builder.Default
    private Date fechaGeneracion = new Date();
    private String solicitante;
}
