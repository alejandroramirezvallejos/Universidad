package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.AReporte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReporteDeRendimiento extends AReporte {
    private ParaleloMateria paralelo;
}
