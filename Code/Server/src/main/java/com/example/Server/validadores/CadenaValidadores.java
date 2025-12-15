package com.example.Server.validadores;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class CadenaValidadores {
    private final ValidarCupoDisponible validarCupoDisponible;
    private final ValidarMateriaNoInscrita validarMateriaNoInscrita;
    private final ValidarMateriasCorrelativas validarMateriasCorrelativas;
    private final ValidarChoqueHorario validarChoqueHorario;
    private final ValidarMateriaAprobada validarMateriaAprobada;
    private final ValidarLimiteDeCreditos validarLimiteCreditos;
    private final ValidarPeriodoMatricula validarPeriodoMatricula;

    @Bean
    @Primary
    public IValidar validar() {
        validarPeriodoMatricula
            .setSiguiente(validarCupoDisponible)
            .setSiguiente(validarMateriaNoInscrita)
            .setSiguiente(validarMateriaAprobada)
            .setSiguiente(validarMateriasCorrelativas)
            .setSiguiente(validarChoqueHorario)
            .setSiguiente(validarLimiteCreditos);

        return validarPeriodoMatricula;
    }
}
