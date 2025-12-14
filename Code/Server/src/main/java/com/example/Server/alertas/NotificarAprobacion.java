package com.example.Server.alertas;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;

public class NotificarAprobacion implements INotificar {
    public void notificar(Estudiante estudiante, Materia materia, Double notaFinal) {
        System.out.println("NOTIFICACION APROBACION:");
        System.out.println("Estudiante: " + estudiante);
        System.out.println("Materia: " + materia.getNombre());
        System.out.println("Nota Final: " + notaFinal);
        System.out.println("Estado: APROBADO");
        System.out.println("----------------------------------------");
    }
}

