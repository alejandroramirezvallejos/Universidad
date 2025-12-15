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

    @Bean
    @Primary
    public IValidador validar() {
        validadorPeriodoMatricula.setSiguiente(validadorCupo);
        validadorCupo.setSiguiente(validadorMateriaNoInscrita);
        validadorMateriaNoInscrita.setSiguiente(validadorMateriaAprobada);
        validadorMateriaAprobada.setSiguiente(validadorMateriasCorrelativas);
        validadorMateriasCorrelativas.setSiguiente(validadorChoqueHorario);
        validadorChoqueHorario.setSiguiente(validadorLimiteCreditos);
        
        return validadorPeriodoMatricula;
    }
}

