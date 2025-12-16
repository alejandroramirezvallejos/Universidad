package com.example.Server.componentes;

import com.example.Server.modelos.ActaEstudiante;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CalculadoraHistorial {
    public double calcularPromedio(List<ActaEstudiante> actas) {
        if (actas.isEmpty()) return 0.0;
        double suma = 0.0;
        for (ActaEstudiante acta : actas)
            if (acta.isAprobado()) suma += acta.getCalificacionFinal();
        return suma / actas.size();
    }

    public int calcularCreditosAprobados(List<ActaEstudiante> actas) {
        int creditos = 0;
        for (ActaEstudiante acta : actas)
            if (acta.isAprobado()) creditos += acta.getParaleloMateria().getMateria().getCreditos();
        return creditos;
    }

    public int calcularCreditosTotales(List<ActaEstudiante> actas) {
        int creditos = 0;
        for (ActaEstudiante acta : actas)
            creditos += acta.getParaleloMateria().getMateria().getCreditos();
        return creditos;
    }
}

