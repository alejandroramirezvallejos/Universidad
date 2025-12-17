package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.AReporte;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.abstracciones.IEstudiante;
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
public class ReporteDeCarrera extends AReporte {
    private ICarrera carrera;
    private List<IEstudiante> estudiantes;
}
