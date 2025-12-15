package com.example.Server.servicios;
import com.example.Server.dtos.*;
import com.example.Server.modelos.*;
import com.example.Server.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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

    public DtoOfertaAcademica obtenerOfertaPorGestion(String codigoGestion) {
        Gestion gestion = repositorioGestion.buscarPorCodigo(codigoGestion).orElse(null);

        if (gestion == null)
            return null;

        List<Materia> todasLasMaterias = repositorioMateria.getMaterias();
        List<DtoMateriaConParalelos> materiasDto = new ArrayList<>();

        for (Materia materia : todasLasMaterias)
            materiasDto.add(mapearMateriaConParalelos(materia, gestion));

        DtoOfertaAcademica oferta = new DtoOfertaAcademica();
        oferta.setCodigoGestion(gestion.getCodigo());
        oferta.setNombreGestion(gestion.getNombre());
        oferta.setMaterias(materiasDto);

        return oferta;
    }

    private DtoMateriaConParalelos mapearMateriaConParalelos(Materia materia, Gestion gestion) {
        DtoMateriaConParalelos dto = new DtoMateriaConParalelos();
        dto.setCodigo(materia.getCodigo());
        dto.setNombre(materia.getNombre());
        dto.setCreditos(materia.getCreditos());
        dto.setSemestre(materia.getSemestre());
        List<ParaleloMateria> paralelos = new ArrayList<>();

        for (ParaleloMateria p : repositorioParalelo.getParalelos())
            if (p.getMateria().getCodigo().equals(materia.getCodigo()) &&
                p.getGestion() != null &&
                p.getGestion().getCodigo().equals(gestion.getCodigo()))
                paralelos.add(p);

        List<DtoParaleloDetalle> paralelosDto = new ArrayList<>();

        for (ParaleloMateria paralelo : paralelos)
            paralelosDto.add(mapearParaleloDetalle(paralelo));

        dto.setParalelos(paralelosDto);

        return dto;
    }

    private DtoParaleloDetalle mapearParaleloDetalle(ParaleloMateria paralelo) {
        DtoParaleloDetalle dto = new DtoParaleloDetalle();
        dto.setId(Long.valueOf(paralelo.getCodigo().hashCode()));
        dto.setNumeroParalelo(paralelo.getCodigo());
        dto.setCupoTotal(paralelo.getCupoMaximo() != null ? paralelo.getCupoMaximo() : 30);

        int inscritos = paralelo.getEstudiantes() != null ? paralelo.getEstudiantes().size() : 0;
        dto.setCupoDisponible(dto.getCupoTotal() - inscritos);

        if (paralelo.getDocente() != null) {
            dto.setNombreDocente(paralelo.getDocente().getNombre() + " " + paralelo.getDocente().getApellido());
            dto.setCodigoDocente(paralelo.getDocente().getCodigo());
        }

        List<Horario> horarios = paralelo.getHorarios();
        List<DtoHorario> horariosDto = new ArrayList<>();

        for (Horario h : horarios)
            horariosDto.add(mapearHorario(h, paralelo.getAula()));

        dto.setHorarios(horariosDto);

        return dto;
    }

    private DtoHorario mapearHorario(Horario horario, Aula aula) {
        DtoHorario dto = new DtoHorario();
        dto.setDiaSemana(horario.getDiaSemana());
        dto.setHoraInicio(horario.getHoraInicio().toString());
        dto.setHoraFin(horario.getHoraFin().toString());
        return dto;
    }
}
