package com.example.Server.servicios.implementaciones;

import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IMatricula;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.modelos.implementaciones.Matricula;
import com.example.Server.repositorios.abstracciones.IRepositorioMatricula;
import com.example.Server.repositorios.abstracciones.IRepositorioEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioParaleloMateria;
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
    private final IRepositorioEstudiante repositorioEstudiante;
    private final IRepositorioParaleloMateria repositorioParalelo;
    private final IValidarMatricula validador;

    @Override
    public IMatricula crear(IEstudiante estudiante, IParaleloMateria paralelo) {
        IEstudiante estudianteReal = repositorioEstudiante.buscarPorCodigo(estudiante.getCodigo());
        IParaleloMateria paraleloReal = repositorioParalelo.buscar(paralelo.getCodigo());

        IEstudiante estudianteAValidar = estudianteReal != null ? estudianteReal : estudiante;
        IParaleloMateria paraleloAValidar = paraleloReal != null ? paraleloReal : paralelo;

        for (IMatricula matriculaExistente : repositorio.getMatriculas()) {
            if (matriculaExistente.getEstudiante().getCodigo().equals(estudianteAValidar.getCodigo()) &&
                matriculaExistente.getParaleloMateria().getCodigo().equals(paraleloAValidar.getCodigo())) {
                System.out.println("INSCRIPCIÓN YA EXISTE: " + estudianteAValidar.getCodigo() + " en " + paraleloAValidar.getCodigo());
                return matriculaExistente; 
            }
        }

        String error = validador.validar(estudianteAValidar, paraleloAValidar);

        if (error != null) {
            System.out.println("ERROR DE VALIDACION: " + error);
            return null;
        }

        Matricula matricula = new Matricula();
        matricula.setEstudiante(estudianteAValidar);
        matricula.setParaleloMateria(paraleloAValidar);
        matricula.setEstado("ACEPTADA");

        IMatricula matriculaGuardada = repositorio.guardar(matricula);

        paraleloAValidar.getEstudiantes().add(estudianteAValidar);
        estudianteAValidar.getMateriasInscritas().add(paraleloAValidar.getMateria());

        return matriculaGuardada;
    }

    @Override
    public void aceptar(IMatricula matricula) {
        if (matricula == null)
            return;

        IEstudiante estudiante = matricula.getEstudiante();
        IParaleloMateria paralelo = matricula.getParaleloMateria();
        
        if (!paralelo.getEstudiantes().contains(estudiante)) {
            paralelo.getEstudiantes().add(estudiante);
        }
        if (!estudiante.getMateriasInscritas().contains(paralelo.getMateria())) {
            estudiante.getMateriasInscritas().add(paralelo.getMateria());
        }

        matricula.setEstado("ACEPTADA");
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
        return repositorio.buscar(estudianteCodigo);
    }

    @Override
    public List<IMatricula> getMatriculasPorParalelo(String paraleloCodigo) {
        List<IMatricula> resultado = new ArrayList<>();

        for (IMatricula matricula : repositorio.getMatriculas())
            if (matricula.getParaleloMateria() != null
                    && matricula.getParaleloMateria().getCodigo().equals(paraleloCodigo))
                resultado.add(matricula);

        return resultado;
    }

    @Override
    public void cancelar(String estudianteCodigo, String paraleloCodigo) {
        List<IMatricula> matriculasEstudiante = repositorio.buscar(estudianteCodigo);
        IMatricula matriculaAEliminar = null;

        for (IMatricula matricula : matriculasEstudiante)
            if (matricula.getParaleloMateria() != null
                    && matricula.getParaleloMateria().getCodigo().equals(paraleloCodigo)) {
                matriculaAEliminar = matricula;
                break;
            }

        if (matriculaAEliminar != null)
            repositorio.eliminar(matriculaAEliminar);
    }
}
