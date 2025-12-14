package com.example.Server.alertas;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Materia;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Component
public class ContextoNotificacion {
    private INotificar estrategia;

    public void notificar(Estudiante estudiante, Materia materia, Double notaFinal) {
        if (estrategia != null)
            estrategia.notificar(estudiante, materia, notaFinal);
    }
}

