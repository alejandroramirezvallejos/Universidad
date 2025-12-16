package com.example.Server.modelos;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Matricula {
    private String estado;
    private ParaleloMateria paraleloMateria;
    private Estudiante estudiante;
}
