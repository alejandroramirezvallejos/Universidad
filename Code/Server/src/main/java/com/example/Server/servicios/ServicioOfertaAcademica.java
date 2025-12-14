package com.example.Server.servicios;

import com.example.Server.dtos.*;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para manejar la oferta académica
 * Combina materias, paralelos y horarios de una gestión
 */
@Service
public class ServicioOfertaAcademica {

    @Autowired
    private RepositorioGestion repositorioGestion;

    @Autowired
    private RepositorioMateria repositorioMateria;

    @Autowired
    private RepositorioParaleloMateria repositorioParalelo;

    @Autowired
    private RepositorioMatricula repositorioMatricula;

    /**
     * Obtiene la oferta académica completa de una gestión
     */
    public DtoOfertaAcademica obtenerOfertaPorGestion(String codigoGestion) {
        // Buscar gestión
        Gestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion).orElse(null);
        if (gestion == null) {
            return null;
        }

        // Obtener todas las materias
        List<Materia> todasLasMaterias = repositorioMateria.getMaterias();
        
        // Mapear materias con sus paralelos
        List<DtoMateriaConParalelos> materiasDto = todasLasMaterias.stream()
                .map(materia -> mapearMateriaConParalelos(materia, gestion))
                .collect(Collectors.toList());

        // Crear y retornar DTO
        DtoOfertaAcademica oferta = new DtoOfertaAcademica();
        oferta.setCodigoGestion(gestion.getCodigo());
        oferta.setNombreGestion(gestion.getNombre());
        oferta.setMaterias(materiasDto);

        return oferta;
    }

    /**
     * Mapea una materia con todos sus paralelos de la gestión
     */
    private DtoMateriaConParalelos mapearMateriaConParalelos(Materia materia, Gestion gestion) {
        DtoMateriaConParalelos dto = new DtoMateriaConParalelos();
        dto.setCodigo(materia.getCodigo());
        dto.setNombre(materia.getNombre());
        dto.setCreditos(materia.getCreditos());
        dto.setSemestre(materia.getSemestre());

        // Buscar paralelos de esta materia en esta gestión
        List<ParaleloMateria> paralelos = repositorioParalelo.getParalelos().stream()
                .filter(p -> p.getMateria().getCodigo().equals(materia.getCodigo()) &&
                             p.getGestion() != null &&
                             p.getGestion().getCodigo().equals(gestion.getCodigo()))
                .collect(Collectors.toList());

        // Mapear cada paralelo con sus detalles
        List<DtoParaleloDetalle> paralelosDto = paralelos.stream()
                .map(this::mapearParaleloDetalle)
                .collect(Collectors.toList());

        dto.setParalelos(paralelosDto);
        return dto;
    }

    /**
     * Mapea un paralelo con todos sus detalles (horarios, cupos, docente)
     */
    private DtoParaleloDetalle mapearParaleloDetalle(ParaleloMateria paralelo) {
        DtoParaleloDetalle dto = new DtoParaleloDetalle();
        dto.setId(Long.valueOf(paralelo.getCodigo().hashCode())); // Usamos hashCode como ID provisional
        dto.setNumeroParalelo(paralelo.getCodigo());
        dto.setCupoTotal(paralelo.getCupoMaximo() != null ? paralelo.getCupoMaximo() : 30);

        // Calcular cupo disponible
        int inscritos = paralelo.getEstudiantes() != null ? paralelo.getEstudiantes().size() : 0;
        dto.setCupoDisponible(dto.getCupoTotal() - inscritos);

        // Datos del docente
        if (paralelo.getDocente() != null) {
            dto.setNombreDocente(paralelo.getDocente().getNombre() + " " + paralelo.getDocente().getApellido());
            dto.setCodigoDocente(paralelo.getDocente().getCodigo());
        }

        // Obtener horarios del paralelo (ya están en el modelo)
        List<Horario> horarios = paralelo.getHorarios();

        // Mapear horarios a DTOs
        List<DtoHorario> horariosDto = horarios.stream()
                .map(h -> mapearHorario(h, paralelo.getAula()))
                .collect(Collectors.toList());

        dto.setHorarios(horariosDto);
        return dto;
    }

    /**
     * Mapea un horario a DTO
     */
    private DtoHorario mapearHorario(Horario horario, Aula aula) {
        DtoHorario dto = new DtoHorario();
        dto.setDiaSemana(horario.getDiaSemana());
        dto.setHoraInicio(horario.getHoraInicio().toString());
        dto.setHoraFin(horario.getHoraFin().toString());
        return dto;
    }
}
