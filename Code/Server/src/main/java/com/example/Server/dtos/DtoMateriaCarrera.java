package com.example.Server.dtos;
import com.example.Server.modelos.Carrera;
import com.example.Server.modelos.DirectorCarrera;
import com.example.Server.modelos.Materia;
import lombok.Data;

@Data
public class DtoMateriaCarrera {
    private DirectorCarrera director;
    private Materia materia;
    private Carrera carrera;
}

