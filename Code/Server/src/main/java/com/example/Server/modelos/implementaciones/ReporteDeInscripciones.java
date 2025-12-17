package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.AReporte;
import com.example.Server.modelos.abstracciones.IGestion;
import com.example.Server.modelos.abstracciones.IMatricula;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReporteDeInscripciones extends AReporte {
    private IGestion gestion;
    @JsonIgnoreProperties({"paraleloMateria"})
    private List<IMatricula> matriculas;
}
