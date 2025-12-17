package com.example.Server.controladores;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMatricula;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.modelos.implementaciones.Matricula;
import com.example.Server.servicios.abstracciones.IServicioEstudiante;
import com.example.Server.servicios.abstracciones.IServicioInscripcion;
import com.example.Server.servicios.abstracciones.IServicioParaleloMateria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ControladorInscripcion {
    private final IServicioInscripcion servicio;
    private final IServicioParaleloMateria servicioParalelo;
    private final IServicioEstudiante servicioEstudiante;

    @PostMapping
    public ResponseEntity<?> inscribir(@RequestBody Matricula matricula) {
        System.out.println("=== INICIO INSCRIPCIÓN ===");
        System.out.println("Código estudiante recibido: " + matricula.getEstudiante().getCodigo());
        System.out.println("Código paralelo recibido: " + matricula.getParaleloMateria().getCodigo());
        
        // Buscar el paralelo completo por su código
        IParaleloMateria paraleloCompleto = servicioParalelo.getParaleloPorCodigo(
            matricula.getParaleloMateria().getCodigo()
        );
        
        System.out.println("Paralelo encontrado: " + (paraleloCompleto != null ? paraleloCompleto.getCodigo() : "NULL"));
        
        if (paraleloCompleto == null) {
            System.out.println("ERROR: Paralelo no encontrado");
            return ResponseEntity.badRequest().body("Paralelo no encontrado");
        }
        
        // Buscar el estudiante completo por su código
        IEstudiante estudianteCompleto = servicioEstudiante.getEstudiantes().stream()
            .filter(e -> e.getCodigo().equals(matricula.getEstudiante().getCodigo()))
            .findFirst()
            .orElse(null);
        
        System.out.println("Estudiante encontrado: " + (estudianteCompleto != null ? estudianteCompleto.getCodigo() : "NULL"));
        
        if (estudianteCompleto == null) {
            System.out.println("ERROR: Estudiante no encontrado");
            return ResponseEntity.badRequest().body("Estudiante no encontrado");
        }
        
        IMatricula resultado = servicio.crear(estudianteCompleto, paraleloCompleto);
        
        System.out.println("Resultado de crear: " + (resultado != null ? "CREADO" : "NULL"));
        
        if (resultado == null) {
            System.out.println("ERROR: No se pudo crear la inscripción (validación falló)");
            return ResponseEntity.badRequest().body("No se pudo crear la inscripción - verifica los logs");
        }
        
        System.out.println("=== INSCRIPCIÓN EXITOSA ===");
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @PutMapping("/aceptar")
    public ResponseEntity<Void> aceptar(@RequestBody Matricula matricula) {
        servicio.aceptar(matricula);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<IMatricula>> getMatriculas() {
        return ResponseEntity.ok(servicio.getMatriculas());
    }

    @PostMapping("/batch")
    public ResponseEntity<List<IMatricula>> inscribirBatch(@RequestBody List<Matricula> inscripciones) {
        List<IMatricula> matriculasCompletas = new ArrayList<>();
        
        for (Matricula inscripcion : inscripciones) {
            // Buscar el paralelo completo por su código
            IParaleloMateria paraleloCompleto = servicioParalelo.getParaleloPorCodigo(
                inscripcion.getParaleloMateria().getCodigo()
            );
            
            if (paraleloCompleto == null) {
                continue; // Saltar esta inscripción si no se encuentra el paralelo
            }
            
            // Buscar el estudiante completo por su código
            IEstudiante estudianteCompleto = servicioEstudiante.getEstudiantes().stream()
                .filter(e -> e.getCodigo().equals(inscripcion.getEstudiante().getCodigo()))
                .findFirst()
                .orElse(null);
            
            if (estudianteCompleto == null) {
                continue; // Saltar esta inscripción si no se encuentra el estudiante
            }
            
            // Crear la matrícula con los objetos completos
            IMatricula resultado = servicio.crear(estudianteCompleto, paraleloCompleto);
            
            if (resultado != null) {
                matriculasCompletas.add(resultado);
            }
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculasCompletas);
    }

    @GetMapping("/estudiante/{estudianteCodigo}")
    public ResponseEntity<List<IMatricula>> getInscripcionesPorEstudiante(@PathVariable String estudianteCodigo) {
        return ResponseEntity.ok(servicio.getMatriculasPorEstudiante(estudianteCodigo));
    }

    @GetMapping("/paralelo/{paraleloCodigo}")
    public ResponseEntity<List<IMatricula>> getInscripcionesPorParalelo(@PathVariable String paraleloCodigo) {
        return ResponseEntity.ok(servicio.getMatriculasPorParalelo(paraleloCodigo));
    }

    @DeleteMapping("/{estudianteCodigo}/{paraleloCodigo}")
    public ResponseEntity<Void> cancelar(@PathVariable String estudianteCodigo, @PathVariable String paraleloCodigo) {
        servicio.cancelar(estudianteCodigo, paraleloCodigo);
        return ResponseEntity.ok().build();
    }
}
