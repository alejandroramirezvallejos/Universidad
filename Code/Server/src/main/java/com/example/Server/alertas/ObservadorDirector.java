package com.example.Server.alertas;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObservadorDirector implements IObservador {
    private final ContextoNotificacion sujeto;

    @PostConstruct
    public void suscribir() {
        sujeto.suscribir(this);
    }

    @PreDestroy
    public void desuscribir() {
        sujeto.desuscribir(this);
    }

    @Override
    public void actualizar(NotificacionEvento evento) {
        String mensaje = evento.getNotaFinal() >= 51.0 ? "APROBADO" : "REPROBADO";
        System.out.println("NOTIFICACIÓN A DIRECTOR DE CARRERA:");
        System.out.println("Reporte de calificación:");
        System.out.println("Estudiante: " + evento.getEstudiante().getNombre() + " " + evento.getEstudiante().getApellido());
        System.out.println("Carrera: " + (evento.getEstudiante().getCarrera() != null ? evento.getEstudiante().getCarrera().getNombre() : "N/A"));
        System.out.println("Materia: " + evento.getMateria().getNombre());
        System.out.println("Nota Final: " + evento.getNotaFinal());
        System.out.println("Estado: " + mensaje);
        System.out.println("----------------------------------------");
    }
}
