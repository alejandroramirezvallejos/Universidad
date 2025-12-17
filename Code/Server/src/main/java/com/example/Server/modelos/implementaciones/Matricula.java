package com.example.Server.modelos.implementaciones;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMatricula;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Matricula implements IMatricula {
    private String estado;
    private IParaleloMateria paraleloMateria;
    private IEstudiante estudiante;
}
