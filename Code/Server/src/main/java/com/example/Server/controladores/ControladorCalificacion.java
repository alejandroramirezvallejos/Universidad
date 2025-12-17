package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.ICalificacion;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.modelos.implementaciones.Calificacion;
import com.example.Server.modelos.implementaciones.Estudiante;
import com.example.Server.modelos.implementaciones.Evaluacion;
import com.example.Server.modelos.implementaciones.ParaleloMateria;
import com.example.Server.servicios.abstracciones.IServicioCalificacion;
import com.example.Server.servicios.abstracciones.IServicioEstudiante;
import com.example.Server.servicios.abstracciones.IServicioParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorCalificacion {
    private final IServicioCalificacion servicio;
    private final IServicioEstudiante servicioEstudiante;
    private final IServicioParaleloMateria servicioParalelo;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Calificacion calificacion) {
        System.out.println("=== CREAR CALIFICACION ===");
        System.out.println("Calificacion recibida: " + calificacion);
        System.out.println("Evaluacion: " + calificacion.getEvaluacion());
        if (calificacion.getEvaluacion() != null) {
            System.out.println("Evaluacion.paraleloMateria: " + calificacion.getEvaluacion().getParaleloMateria());
            if (calificacion.getEvaluacion().getParaleloMateria() != null) {
                System.out.println("ParaleloMateria.codigo: " + calificacion.getEvaluacion().getParaleloMateria().getCodigo());
            }
        }
        
        // Buscar estudiante completo por código
        if (calificacion.getEstudiante() != null && calificacion.getEstudiante().getCodigo() != null) {
            IEstudiante estudianteCompleto = servicioEstudiante.getEstudiantes().stream()
                .filter(e -> e.getCodigo().equals(calificacion.getEstudiante().getCodigo()))
                .findFirst()
                .orElse(null);
            
            if (estudianteCompleto != null) {
                calificacion.setEstudiante((Estudiante) estudianteCompleto);
                System.out.println("Estudiante encontrado: " + estudianteCompleto.getCodigo());
            } else {
                System.out.println("ERROR: Estudiante no encontrado: " + calificacion.getEstudiante().getCodigo());
                return ResponseEntity.badRequest().body("Estudiante no encontrado");
            }
        }
        
        // Buscar paralelo completo por código en la evaluación
        if (calificacion.getEvaluacion() != null && 
            calificacion.getEvaluacion().getParaleloMateria() != null &&
            calificacion.getEvaluacion().getParaleloMateria().getCodigo() != null) {
            
            String codigoParalelo = calificacion.getEvaluacion().getParaleloMateria().getCodigo();
            System.out.println("Buscando paralelo con codigo: " + codigoParalelo);
            
            IParaleloMateria paraleloCompleto = servicioParalelo.getParaleloPorCodigo(codigoParalelo);
            
            if (paraleloCompleto != null) {
                Evaluacion evaluacion = (Evaluacion) calificacion.getEvaluacion();
                evaluacion.setParaleloMateria((ParaleloMateria) paraleloCompleto);
                calificacion.setEvaluacion(evaluacion);
                System.out.println("Paralelo encontrado y asignado: " + paraleloCompleto.getCodigo());
                System.out.println("Materia del paralelo: " + paraleloCompleto.getMateria().getNombre());
            } else {
                System.out.println("ERROR: Paralelo no encontrado: " + codigoParalelo);
                return ResponseEntity.badRequest().body("Paralelo no encontrado");
            }
        } else {
            System.out.println("ADVERTENCIA: No se pudo extraer el código del paralelo de la evaluación");
        }
        
        ICalificacion resultado = servicio.crear(calificacion);
        System.out.println("Calificacion creada exitosamente");
        System.out.println("Paralelo en resultado: " + (resultado.getEvaluacion() != null ? resultado.getEvaluacion().getParaleloMateria() : "null"));
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<ICalificacion>> getCalificaciones() {
        return ResponseEntity.ok(servicio.getCalificaciones());
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<ICalificacion>> getCalificacionesPorEstudiante(@PathVariable String estudianteCodigo) {
        return ResponseEntity.ok(servicio.getCalificacionesPorEstudiante(estudianteCodigo));
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody Calificacion calificacion) {
        servicio.eliminar(calificacion);
        return ResponseEntity.ok().build();
    }
}
