package com.example.Server.servicios.implementaciones;

import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMatricula;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.modelos.implementaciones.Matricula;
import com.example.Server.repositorios.abstracciones.IRepositorioMatricula;
import com.example.Server.validadores.matricula.IValidarMatricula;
import com.example.Server.servicios.abstracciones.IServicioInscripcion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioInscripcion implements IServicioInscripcion {
    private final IRepositorioMatricula repositorio;
    private final IValidarMatricula validador;

    @Override
    public IMatricula crear(IEstudiante estudiante, IParaleloMateria paralelo) {
        String error = validador.validar(estudiante, paralelo);

        if (error != null) {
            System.out.println("ERROR DE VALIDACION: " + error);
            return null;
        }

        Matricula matricula = new Matricula();
        matricula.setEstudiante(estudiante);
        matricula.setParaleloMateria(paralelo);
        matricula.setEstado("PENDIENTE");

        return repositorio.guardar(matricula);
    }

    @Override
    public void aceptar(IMatricula matricula) {
        if (matricula == null)
            return;

        IEstudiante estudiante = matricula.getEstudiante();
        IParaleloMateria paralelo = matricula.getParaleloMateria();
        paralelo.getEstudiantes().add(estudiante);
        estudiante.getMateriasInscritas().add(paralelo.getMateria());

        matricula.setEstado("ACEPTADA");
        repositorio.guardar(matricula);
    }

    @Override
    public List<IMatricula> getMatriculas() {
        return repositorio.getMatriculas();
    }

    @Override
    public List<IMatricula> inscribirBatch(List<IMatricula> inscripciones) {
        List<IMatricula> matriculasCreadas = new ArrayList<>();
        for (IMatricula inscripcion : inscripciones) {
            IMatricula matricula = crear(inscripcion.getEstudiante(), inscripcion.getParaleloMateria());
            if (matricula != null)
                matriculasCreadas.add(matricula);
        }
        return matriculasCreadas;
    }

    @Override
    public List<IMatricula> getMatriculasPorEstudiante(String estudianteCodigo) {
        return repositorio.buscarPorEstudiante(estudianteCodigo);
    }

    @Override
    public List<IMatricula> getMatriculasPorParalelo(String paraleloCodigo) {
        List<IMatricula> resultado = new ArrayList<>();

        for (IMatricula matricula : repositorio.getMatriculas())
            if (matricula.getParaleloMateria() != null && matricula.getParaleloMateria().getCodigo().equals(paraleloCodigo))
                resultado.add(matricula);

        return resultado;
    }

    @Override
    public void cancelar(String estudianteCodigo, String paraleloCodigo) {
        List<IMatricula> matriculasEstudiante = repositorio.buscarPorEstudiante(estudianteCodigo);
        IMatricula matriculaAEliminar = null;

        for (IMatricula matricula : matriculasEstudiante)
            if (matricula.getParaleloMateria() != null && matricula.getParaleloMateria().getCodigo().equals(paraleloCodigo)) {
                matriculaAEliminar = matricula;
                break;
            }

        if (matriculaAEliminar != null)
            repositorio.eliminar(matriculaAEliminar);
    }
}
