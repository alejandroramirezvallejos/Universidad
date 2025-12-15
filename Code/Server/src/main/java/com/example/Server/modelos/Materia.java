package com.example.Server.modelos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materia {
    private String codigo;
    private String nombre;
    private Integer semestre;
    private Integer creditos;
    private boolean activa = true;
    private List<Materia> materiasCorrelativas = new ArrayList<>();
    private List<ParaleloMateria> paraleloMaterias = new ArrayList<>();

    public ParaleloMateria getParaleloInscrito(Estudiante estudiante) {
        if (paraleloMaterias == null)
            return null;

        for (ParaleloMateria p : paraleloMaterias)
            if (p.estaInscrito(estudiante))
                return p;

        return null;
    }
}
