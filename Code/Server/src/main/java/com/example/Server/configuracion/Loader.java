package com.example.Server.configuracion;

import com.example.Server.modelos.implementaciones.*;
import com.example.Server.servicios.abstracciones.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class Loader implements ApplicationRunner {
    private final IServicioCarrera carreraServicio;
    private final IServicioEstudiante estudianteServicio;
    private final IServicioDocente docenteServicio;
    private final IServicioDirectorCarrera directorCarreraServicio;
    private final IServicioGestion gestionServicio;
    private final IServicioMateria materiaServicio;
    private final IServicioAula aulaServicio;
    private final IServicioParaleloMateria paraleloMateriaServicio;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("🔧 INICIALIZANDO DATOS DE PRUEBA (Backend)");
        System.out.println("════════════════════════════════════════════════════════════\n");

        crearGestiones();
        crearCarreras();
        crearEstudiantes();
        crearDocentes();
        crearDirectores();
        crearAulas();
        crearMaterias();
        crearParalelos();
        imprimirResumen();
    }

    private void crearGestiones() {
        System.out.println("📅 Creando Gestiones...");
        crearGestion("II-2025", "Segundo Semestre 2025", 2025, 2, "2025-08-01", "2025-12-15", "2025-07-15",
                "2025-07-30", "EN_CURSO");
        crearGestion("I-2025", "Primer Semestre 2025", 2025, 1, "2025-02-01", "2025-06-30", "2025-01-15", "2025-01-30",
                "CERRADA");
    }

    private void crearGestion(String codigo, String nombre, int anio, int periodo, String inicio, String fin,
            String inicioMatricula, String finMatricula, String estado) {
        Gestion gestion = new Gestion();
        gestion.setCodigo(codigo);
        gestion.setNombre(nombre);
        gestion.setAnio(anio);
        gestion.setPeriodo(periodo);
        gestion.setFechaInicio(Date.valueOf(inicio));
        gestion.setFechaFin(Date.valueOf(fin));
        gestion.setFechaInicioMatricula(Date.valueOf(inicioMatricula));
        gestion.setFechaFinMatricula(Date.valueOf(finMatricula));
        gestion.setEstado(estado);
        gestionServicio.crear(gestion);
    }

    private void crearCarreras() {
        System.out.println("📚 Creando Carreras...");
        crearCarrera("ING-SIS", "Ingeniería de Sistemas");
        crearCarrera("ING-IND", "Ingeniería Industrial");
        crearCarrera("ADM-EMP", "Administración de Empresas");
    }

    private void crearCarrera(String codigo, String nombre) {
        Carrera carrera = new Carrera();
        carrera.setCodigo(codigo);
        carrera.setNombre(nombre);
        carreraServicio.crear(carrera);
    }

    private void crearEstudiantes() {
        System.out.println("👨‍🎓 Creando Estudiantes...");
        Carrera carrera = (Carrera) carreraServicio.getCarreras().get(0);
        crearEstudiante("EST001", "Juan", "Pérez", "juan.perez@ucb.edu.bo", "password123", carrera);

        Carrera ingInd = (Carrera) carreraServicio.getCarreras().get(1);
        crearEstudiante("EST002", "Ana", "Martínez", "ana.martinez@ucb.edu.bo", "password123", ingInd);
    }

    private void crearEstudiante(String codigo, String nombre, String apellido, String email, String contrasenna,
            Carrera carrera) {
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo(codigo);
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setEmail(email);
        estudiante.setContrasenna(contrasenna);
        estudiante.setCarrera(carrera);
        estudianteServicio.crear(estudiante);
    }

    private void crearDocentes() {
        System.out.println("👩‍🏫 Creando Docentes...");
        crearDocente("DOC001", "María", "González", "maria.gonzalez@ucb.edu.bo", "password123",
                "Ingeniería de Software");
        crearDocente("DOC002", "Pedro", "López", "pedro.lopez@ucb.edu.bo", "password123", "Base de Datos");
    }

    private void crearDocente(String codigo, String nombre, String apellido, String email, String contrasenna,
            String especialidad) {
        Docente docente = new Docente();
        docente.setCodigo(codigo);
        docente.setNombre(nombre);
        docente.setApellido(apellido);
        docente.setEmail(email);
        docente.setContrasenna(contrasenna);
        docente.setEspecialidad(especialidad);
        docenteServicio.crear(docente);
    }

    private void crearDirectores() {
        System.out.println("👔 Creando Directores...");
        Carrera carrera = (Carrera) carreraServicio.getCarreras().get(0);
        crearDirector("DIR001", "Carlos", "Rodríguez", "carlos.rodriguez@ucb.edu.bo", "password123", carrera);

        Carrera ingInd = (Carrera) carreraServicio.getCarreras().get(1);
        crearDirector("DIR002", "Laura", "Fernández", "laura.fernandez@ucb.edu.bo", "password123", ingInd);
    }

    private void crearDirector(String codigo, String nombre, String apellido, String email, String contrasenna,
            Carrera carrera) {
        DirectorCarrera director = new DirectorCarrera();
        director.setCodigo(codigo);
        director.setNombre(nombre);
        director.setApellido(apellido);
        director.setEmail(email);
        director.setContrasenna(contrasenna);
        director.setCarrera(carrera);
        directorCarreraServicio.crear(director);
    }

    private void crearAulas() {
        System.out.println("🏛️ Creando Aulas...");
        crearAula(30, "Edificio A", "A-201");
        crearAula(35, "Edificio A", "A-202");
        crearAula(40, "Edificio B", "B-101");
        crearAula(25, "Edificio B", "B-102");
        crearAula(45, "Edificio C", "C-301");
    }

    private void crearAula(int capacidad, String edificio, String codigo) {
        Aula aula = new Aula(true, capacidad, edificio, codigo);
        aulaServicio.crear(aula);
    }

    private void crearMaterias() {
        System.out.println("📖 Creando Materias...");
        Materia prog1 = crearMateria("SIS-101", "Programación I", 1, 4);
        crearMateria("MAT-101", "Matemáticas I", 1, 4);
        crearMateria("FIS-101", "Física I", 1, 3);

        Materia prog2 = crearMateria("SIS-201", "Programación II", 2, 4);
        prog2.getMateriasCorrelativas().add(prog1);
        materiaServicio.crear(prog2);

        Materia estDatos = crearMateria("SIS-202", "Estructuras de Datos", 2, 4);
        estDatos.getMateriasCorrelativas().add(prog1);
        materiaServicio.crear(estDatos);

        Materia bd1 = crearMateria("SIS-203", "Base de Datos I", 2, 3);

        Materia ingSw = crearMateria("SIS-303", "Ingeniería de Software", 3, 4);
        ingSw.getMateriasCorrelativas().add(prog2);
        materiaServicio.crear(ingSw);

        Materia devWeb = crearMateria("SIS-402", "Desarrollo Web", 4, 4);
        devWeb.getMateriasCorrelativas().add(bd1);
        materiaServicio.crear(devWeb);
    }

    private Materia crearMateria(String codigo, String nombre, int semestre, int creditos) {
        Materia materia = new Materia();
        materia.setCodigo(codigo);
        materia.setNombre(nombre);
        materia.setSemestre(semestre);
        materia.setCreditos(creditos);
        materiaServicio.crear(materia);
        return materia;
    }

    private void crearParalelos() {
        System.out.println("📅 Creando Paralelos...");

        Materia prog1 = (Materia) materiaServicio.getMateria("SIS-101");
        Materia bd1 = (Materia) materiaServicio.getMateria("SIS-203");
        Materia ingSw = (Materia) materiaServicio.getMateria("SIS-303");
        Docente doc1 = (Docente) docenteServicio.buscar("DOC001");
        Docente doc2 = (Docente) docenteServicio.buscar("DOC002");
        Aula aula201 = (Aula) aulaServicio.getAulas().get(0);
        Aula aula202 = (Aula) aulaServicio.getAulas().get(1);
        Aula aulaB101 = (Aula) aulaServicio.getAulas().get(2);

        Gestion gestionActual = (Gestion) gestionServicio.getGestion();

        crearParalelo("SIS-101-A", prog1, doc1, aula201, 30, gestionActual,
                new Horario("LUNES", LocalTime.of(8, 0), LocalTime.of(10, 0)),
                new Horario("MIERCOLES", LocalTime.of(8, 0), LocalTime.of(10, 0)));

        crearParalelo("SIS-101-B", prog1, doc2, aula202, 35, gestionActual,
                new Horario("MARTES", LocalTime.of(10, 0), LocalTime.of(12, 0)),
                new Horario("JUEVES", LocalTime.of(10, 0), LocalTime.of(12, 0)));

        crearParalelo("SIS-203-A", bd1, doc2, aulaB101, 40, gestionActual,
                new Horario("LUNES", LocalTime.of(14, 0), LocalTime.of(16, 0)),
                new Horario("MIERCOLES", LocalTime.of(14, 0), LocalTime.of(16, 0)));

        crearParalelo("SIS-303-A", ingSw, doc1, aula201, 45, gestionActual,
                new Horario("MARTES", LocalTime.of(8, 0), LocalTime.of(10, 0)),
                new Horario("JUEVES", LocalTime.of(8, 0), LocalTime.of(10, 0)));
    }

    private void crearParalelo(String codigo, Materia materia, Docente docente, Aula aula, int cupo, Gestion gestion,
            Horario... horarios) {
        ParaleloMateria paralelo = new ParaleloMateria();
        paralelo.setCodigo(codigo);
        paralelo.setMateria(materia);
        paralelo.setDocente(docente);
        paralelo.setAula(aula);
        paralelo.setCupoMaximo(cupo);
        paralelo.setGestion(gestion); 
        for (Horario horario : horarios)
            paralelo.getHorarios().add(horario);

        paraleloMateriaServicio.crear(paralelo);
    }

    private void imprimirResumen() {
        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("BACKEND INICIALIZADO");
        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println("\nUSUARIOS DE PRUEBA:");
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ Estudiante: juan.perez@ucb.edu.bo / password123         │");
        System.out.println("│ Docente:    maria.gonzalez@ucb.edu.bo / password123     │");
        System.out.println("│ Director:   carlos.rodriguez@ucb.edu.bo / password123   │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }
}
