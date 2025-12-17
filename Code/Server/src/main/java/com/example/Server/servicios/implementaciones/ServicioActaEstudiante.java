package com.example.Server.servicios.implementaciones;
import com.example.Server.modelos.abstracciones.IActaEstudiante;
import com.example.Server.modelos.abstracciones.IEstudiante;
import com.example.Server.modelos.abstracciones.IEvaluacion;
import com.example.Server.modelos.abstracciones.IMateria;
import com.example.Server.modelos.abstracciones.IParaleloMateria;
import com.example.Server.modelos.implementaciones.ActaEstudiante;
import com.example.Server.modelos.implementaciones.Notificacion;
import com.example.Server.notificaciones.IPublicadorDeNotificaciones;
import com.example.Server.repositorios.abstracciones.IRepositorioActaEstudiante;
import com.example.Server.repositorios.abstracciones.IRepositorioEvaluacion;
import com.example.Server.servicios.abstracciones.IServicioActaEstudiante;
import com.example.Server.validadores.calificacion.IValidarCalificacion;
import com.example.Server.estrategias.calificacion.CalcularCalificacionFinal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioActaEstudiante implements IServicioActaEstudiante {
    private final IRepositorioActaEstudiante repositorio;
    private final IRepositorioEvaluacion repositorioEvaluacion;
    private final IPublicadorDeNotificaciones publicador;
    private final IValidarCalificacion validador;
    private final CalcularCalificacionFinal calculador;

    @Override
    public IActaEstudiante crear(IEstudiante estudiante, IParaleloMateria paralelo) {
        List<IEvaluacion> evaluaciones = repositorioEvaluacion.buscarPorParalelo(paralelo.getCodigo());
        double nota = calculador.calcular(estudiante, paralelo, evaluaciones);
        boolean aprobado = validador.validar(nota);

        ActaEstudiante acta = new ActaEstudiante();
        acta.setEstudiante(estudiante);
        acta.setParaleloMateria(paralelo);
        acta.setCalificacionFinal(nota);
        acta.setAprobado(aprobado);

        if (aprobado)
            estudiante.getMateriasAprobadas().add(paralelo.getMateria());

        notificar(estudiante, paralelo.getMateria(), nota);

        return repositorio.guardar(acta);
    }

    @Override
    public List<IActaEstudiante> getActas() {
        return repositorio.getActas();
    }

    @Override
    public List<IActaEstudiante> getActasReprobadas() {
        return getActasPorEstado(false);
    }

    @Override
    public List<IActaEstudiante> getActasAprobadas() {
        return getActasPorEstado(true);
    }

    private List<IActaEstudiante> getActasPorEstado(boolean aprobado) {
        List<IActaEstudiante> resultado = new ArrayList<>();

        for (IActaEstudiante acta : repositorio.getActas())
            if (acta.isAprobado() == aprobado)
                resultado.add(acta);

        return resultado;
    }

    @Override
    public void eliminar(IActaEstudiante acta) {
        repositorio.eliminar(acta);
    }

    private void notificar(IEstudiante estudiante, IMateria materia, Double notaFinal) {
        Notificacion notificacion = new Notificacion();
        notificacion.setEstudiante(estudiante);
        notificacion.setMateria(materia);
        notificacion.setNotaFinal(notaFinal);
        publicador.notificar(notificacion);
    }
}
