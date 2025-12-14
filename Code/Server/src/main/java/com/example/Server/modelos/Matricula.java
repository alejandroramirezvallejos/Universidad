package com.example.Server.modelos;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Matricula {
    private String estado;
    private ParaleloMateria paraleloMateria;
    private Estudiante estudiante;
}


