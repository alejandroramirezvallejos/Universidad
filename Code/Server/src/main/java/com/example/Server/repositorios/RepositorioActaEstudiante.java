package com.example.Server.repositorios;
import com.example.Server.modelos.ActaEstudiante;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioActaEstudiante {
    private final List<ActaEstudiante> actas = new ArrayList<>();

    public ActaEstudiante guardar(ActaEstudiante acta) {
        actas.add(acta);
        return acta;
    }

    public List<ActaEstudiante> getActas() {
        return new ArrayList<>(actas);
    }

    public void eliminar(ActaEstudiante acta) {
        actas.remove(acta);
    }
}

