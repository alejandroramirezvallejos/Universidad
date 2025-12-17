package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.abstracciones.INotificacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion implements INotificacion {
    private IEstudiante estudiante;
    private IMateria materia;
    private Double notaFinal;
}
