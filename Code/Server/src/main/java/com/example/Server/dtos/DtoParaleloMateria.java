package com.example.Server.dtos;
import com.example.Server.modelos.Aula;
import com.example.Server.modelos.DirectorCarrera;
import com.example.Server.modelos.Docente;
import com.example.Server.modelos.Materia;
import com.example.Server.modelos.Horario;
import lombok.Data;
import java.util.List;

@Data
public class DtoParaleloMateria {
    private DirectorCarrera director;
    private String codigo;
    private Materia materia;
    private Docente docente;
    private Aula aula;
    private Integer cupoMaximo;
    private List<Horario> horarios;
}

