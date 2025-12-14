package com.example.Server.validadores;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class CadenaValidadores {
    private final ValidadorCupo validadorCupo;
    private final ValidadorMateriaNoInscrita validadorMateriaNoInscrita;
    private final ValidadorMateriasCorrelativas validadorMateriasCorrelativas;
    private final ValidadorChoqueHorario validadorChoqueHorario;
    private final ValidadorMateriaAprobada validadorMateriaAprobada;
    private final ValidadorLimiteCreditos validadorLimiteCreditos;
    private final ValidadorPeriodoMatricula validadorPeriodoMatricula;

    /**
     * Configura la cadena completa de validadores en orden de prioridad:
     * 1. ValidadorPeriodoMatricula - Verifica que la matrícula esté abierta
     * 2. ValidadorCupo - Verifica cupos disponibles
     * 3. ValidadorMateriaNoInscrita - Evita inscripciones duplicadas
     * 4. ValidadorMateriaAprobada - Verifica que no esté ya aprobada
     * 5. ValidadorMateriasCorrelativas - Verifica prerrequisitos
     * 6. ValidadorChoqueHorario - Evita conflictos de horario
     * 7. ValidadorLimiteCreditos - Verifica límite de créditos (max 24)
     */
    @Bean
    @Primary
    public IValidador validar() {
        // Construir la cadena en orden lógico
        validadorPeriodoMatricula.setSiguiente(validadorCupo);
        validadorCupo.setSiguiente(validadorMateriaNoInscrita);
        validadorMateriaNoInscrita.setSiguiente(validadorMateriaAprobada);
        validadorMateriaAprobada.setSiguiente(validadorMateriasCorrelativas);
        validadorMateriasCorrelativas.setSiguiente(validadorChoqueHorario);
        validadorChoqueHorario.setSiguiente(validadorLimiteCreditos);
        
        // Retornar el primer validador de la cadena
        return validadorPeriodoMatricula;
    }
}

