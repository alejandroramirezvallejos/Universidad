package com.example.Server.validadores;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.ParaleloMateria;

public abstract class Validar implements IValidar {
    private IValidar siguiente;

    public IValidar setSiguiente(IValidar siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        if (siguiente != null)
            return siguiente.validar(estudiante, paraleloMateria);

        return null;
    }
}
