package com.example.Server.alertas;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;

public class NotificarReprobacion implements INotificar {
    public void notificar(Estudiante estudiante, Materia materia, Double notaFinal) {
        System.out.println("ALERTA REPROBACION:");
        System.out.println("Estudiante: " + estudiante);
        System.out.println("Materia: " + materia.getNombre());
        System.out.println("Nota Final: " + notaFinal);
        System.out.println("Estado: REPROBADO");
        System.out.println("----------------------------------------");
    }
}
