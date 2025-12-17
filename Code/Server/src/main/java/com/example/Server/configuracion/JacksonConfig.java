package com.example.Server.configuracion;

import com.example.Server.modelos.abstracciones.*;
import com.example.Server.modelos.implementaciones.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        
        SimpleModule module = new SimpleModule();
        SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        
        // Registrar clases concretas para las interfaces - LA FORMA CORRECTA
        resolver.addMapping(ICarrera.class, Carrera.class);
        resolver.addMapping(IEstudiante.class, Estudiante.class);
        resolver.addMapping(IDocente.class, Docente.class);
        resolver.addMapping(IDirectorCarrera.class, DirectorCarrera.class);
        resolver.addMapping(IMateria.class, Materia.class);
        resolver.addMapping(IParaleloMateria.class, ParaleloMateria.class);
        resolver.addMapping(IAula.class, Aula.class);
        resolver.addMapping(IGestion.class, Gestion.class);
        resolver.addMapping(IMatricula.class, Matricula.class);
        resolver.addMapping(ICalificacion.class, Calificacion.class);
        resolver.addMapping(IEvaluacion.class, Evaluacion.class);
        
        module.setAbstractTypes(resolver);
        mapper.registerModule(module);
        
        return mapper;
    }
}
