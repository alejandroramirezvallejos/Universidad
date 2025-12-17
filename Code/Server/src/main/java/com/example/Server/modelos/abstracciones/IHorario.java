package com.example.Server.modelos.abstracciones;
import java.time.LocalTime;

public interface IHorario {
    String getDiaSemana();
    void setDiaSemana(String diaSemana);
    LocalTime getHoraInicio();
    void setHoraInicio(LocalTime horaInicio);
    LocalTime getHoraFin();
    void setHoraFin(LocalTime horaFin);
}

