package com.example.Server.repositorios.implementaciones;
import com.example.Server.modelos.abstracciones.ICarrera;
import com.example.Server.modelos.implementaciones.Carrera;
import com.example.Server.repositorios.abstracciones.IRepositorioCarrera;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepositorioCarrera implements IRepositorioCarrera {
    private final Map<String, Carrera> carreras = new HashMap<>();

    /**
     * Constructor que inicializa la carrera de Ingeniería de Sistemas por defecto
     */
    public RepositorioCarrera() {
        // Crear carrera de Ingeniería de Sistemas por defecto
        Carrera ingenieriaSistemas = Carrera.builder()
            .codigo("ING-SIS")
            .nombre("Ingeniería de Sistemas")
            .build();
        carreras.put(ingenieriaSistemas.getCodigo(), ingenieriaSistemas);
        System.out.println("✅ Carrera 'Ingeniería de Sistemas' inicializada en el sistema");
    }

    @Override
    public ICarrera guardar(ICarrera carrera) {
        Carrera carreraImpl = (Carrera) carrera;
        carreras.put(carreraImpl.getCodigo(), carreraImpl);
        return carreraImpl;
    }

    @Override
    public List<ICarrera> getCarreras() {
        return new ArrayList<>(carreras.values());
    }

    @Override
    public void eliminar(ICarrera carrera) {
        carreras.remove(carrera.getCodigo());
    }

    @Override
    public ICarrera buscar(String codigo) {
        return carreras.get(codigo);
    }
}
