package com.example.Server.servicios;
import com.example.Server.modelos.Estudiante;
import com.example.Server.modelos.Matricula;
import com.example.Server.modelos.ParaleloMateria;
import com.example.Server.repositorios.RepositorioMatricula;
import com.example.Server.validadores.matricula.IValidarMatricula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioInscripcion {
    private final RepositorioMatricula repositorio;
    private final IValidarMatricula validador;

    public Matricula crear(Estudiante estudiante, ParaleloMateria paralelo) {
        String error = validador.validar(estudiante, paralelo);

        if (error != null) {
            System.out.println("ERROR DE VALIDACION: " + error);
            return null;
        }

        Matricula matricula = construir(estudiante, paralelo);

        return repositorio.guardar(matricula);
    }

    private Matricula construir(Estudiante estudiante, ParaleloMateria paralelo) {
        Matricula matricula = new Matricula();
        matricula.setEstudiante(estudiante);
        matricula.setParaleloMateria(paralelo);
        matricula.setEstado("PENDIENTE");
        return matricula;
    }

    public void aceptar(Matricula matricula) {
        if (matricula == null)
            return;

        agregarEstudiante(matricula);
        actualizar(matricula);
    }

    private void agregarEstudiante(Matricula matricula) {
        Estudiante estudiante = matricula.getEstudiante();
        ParaleloMateria paralelo = matricula.getParaleloMateria();
        paralelo.getEstudiantes().add(estudiante);
        estudiante.getMateriasInscritas().add(paralelo.getMateria());
    }

    private void actualizar(Matricula matricula) {
        matricula.setEstado("ACEPTADA");
        repositorio.guardar(matricula);
    }

    public List<Matricula> getMatriculas() {
        return repositorio.getMatriculas();
    }

    public List<Matricula> getMatriculasPorEstudiante(String estudianteCodigo) {
        return repositorio.buscarPorEstudiante(estudianteCodigo);
    }

    public List<Matricula> getMatriculasPorParalelo(String paraleloCodigo) {
        List<Matricula> resultado = new ArrayList<>();

        for (Matricula matricula : repositorio.getMatriculas())
            if (matricula.getParaleloMateria() != null && matricula.getParaleloMateria().getCodigo().equals(paraleloCodigo))
                resultado.add(matricula);

        return resultado;
    }

    public void cancelar(String estudianteCodigo, String paraleloCodigo) {
        List<Matricula> matriculasEstudiante = repositorio.buscarPorEstudiante(estudianteCodigo);
        Matricula matriculaAEliminar = null;

        for (Matricula matricula : matriculasEstudiante)
            if (matricula.getParaleloMateria() != null && matricula.getParaleloMateria().getCodigo().equals(paraleloCodigo)) {
                matriculaAEliminar = matricula;
                break;
            }

        if (matriculaAEliminar != null)
            repositorio.eliminar(matriculaAEliminar);
    }
}
