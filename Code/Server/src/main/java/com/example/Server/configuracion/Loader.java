package com.example.Server.configuracion;
import com.example.Server.modelos.*;
import com.example.Server.servicios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class Loader implements ApplicationRunner {
    private final ServicioCarrera servicioCarrera;
    private final ServicioEstudiante servicioEstudiante;
    private final ServicioDocente servicioDocente;
    private final ServicioDirectorCarrera servicioDirector;
    private final ServicioGestion servicioGestion;
    private final ServicioMateria servicioMateria;
    private final ServicioAula servicioAula;
    private final ServicioParaleloMateria servicioParaleloMateria;

    @Override
    public void run(ApplicationArguments args){
        imprimirEncabezado();
        crearGestiones();
        crearCarreras();
        crearEstudiantes();
        crearDocentes();
        crearDirectores();
        crearUsuariosAdicionales();
        crearAulas();
        crearMaterias();
        crearParalelos();
        imprimirResumen();
    }

    private void imprimirEncabezado() {
        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("🔧 INICIALIZANDO DATOS DE PRUEBA");
        System.out.println("════════════════════════════════════════════════════════════\n");
    }

    private void crearGestiones() {
        System.out.println("📅 Creando Gestiones Académicas...");
        crearGestion("II-2025", "Segundo Semestre 2025", 2025, 2, "2025-08-01", "2025-12-15", "2025-07-15", "2025-07-30", "EN_CURSO");
        crearGestion("I-2025", "Primer Semestre 2025", 2025, 1, "2025-02-01", "2025-06-30", "2025-01-15", "2025-01-30", "CERRADA");
        crearGestion("II-2024", "Segundo Semestre 2024", 2024, 2, "2024-08-01", "2024-12-15", "2024-07-15", "2024-07-30", "CERRADA");
    }

    private void crearGestion(String codigo, String nombre, int anio, int periodo, String inicio, String fin, String inicioMatricula, String finMatricula, String estado) {
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
        servicioGestion.crear(gestion);
        System.out.println("   ✓ " + codigo + " (" + estado + ")");
    }

    private void crearCarreras() {
        System.out.println("\n📚 Creando Carreras...");
        crearCarrera("ING-SIS", "Ingeniería de Sistemas");
        crearCarrera("ING-IND", "Ingeniería Industrial");
        crearCarrera("ADM-EMP", "Administración de Empresas");
    }

    private void crearCarrera(String codigo, String nombre) {
        Carrera carrera = new Carrera();
        carrera.setCodigo(codigo);
        carrera.setNombre(nombre);
        servicioCarrera.crear(carrera);
        System.out.println("   ✓ " + nombre + " (" + codigo + ")");
    }

    private void crearEstudiantes() {
        System.out.println("\n👨‍🎓 Creando Estudiante de Prueba...");
        Carrera carrera = servicioCarrera.getCarreras().get(0);
        crearEstudiante("EST001", "Juan", "Pérez", "juan.perez@ucb.edu.bo", carrera);

        System.out.println("   ✓ Juan Pérez");
        System.out.println("     Email: juan.perez@ucb.edu.bo");
        System.out.println("     Código: EST001");
        System.out.println("     Carrera: Ingeniería de Sistemas");
    }

    private void crearEstudiante(String codigo, String nombre, String apellido, String email, Carrera carrera) {
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo(codigo);
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setEmail(email);
        estudiante.setCarrera(carrera);
        servicioEstudiante.crear(estudiante);
    }

    private void crearDocentes() {
        System.out.println("\n👩‍🏫 Creando Docente de Prueba...");
        crearDocente("DOC001", "María", "González", "maria.gonzalez@ucb.edu.bo", "Ingeniería de Software");

        System.out.println("   ✓ María González");
        System.out.println("     Email: maria.gonzalez@ucb.edu.bo");
        System.out.println("     Código: DOC001");
        System.out.println("     Especialidad: Ingeniería de Software");
    }

    private void crearDocente(String codigo, String nombre, String apellido, String email, String especialidad) {
        Docente docente = new Docente();
        docente.setCodigo(codigo);
        docente.setNombre(nombre);
        docente.setApellido(apellido);
        docente.setEmail(email);
        docente.setEspecialidad(especialidad);
        servicioDocente.crear(docente);
    }

    private void crearDirectores() {
        System.out.println("\n👔 Creando Director de Carrera de Prueba...");
        Carrera carrera = servicioCarrera.getCarreras().get(0);
        crearDirector("DIR001", "Carlos", "Rodríguez", "carlos.rodriguez@ucb.edu.bo", carrera);

        System.out.println("   ✓ Carlos Rodríguez");
        System.out.println("     Email: carlos.rodriguez@ucb.edu.bo");
        System.out.println("     Código: DIR001");
        System.out.println("     Carrera: Ingeniería de Sistemas");
    }

    private void crearDirector(String codigo, String nombre, String apellido, String email, Carrera carrera) {
        DirectorCarrera director = new DirectorCarrera();
        director.setCodigo(codigo);
        director.setNombre(nombre);
        director.setApellido(apellido);
        director.setEmail(email);
        director.setCarrera(carrera);
        servicioDirector.crear(director);
    }

    private void crearUsuariosAdicionales() {
        System.out.println("\n🧪 Creando Usuarios Adicionales...");
        Carrera ingInd = servicioCarrera.getCarreras().get(1);
        crearEstudiante("EST002", "Ana", "Martínez", "ana.martinez@ucb.edu.bo", ingInd);
        System.out.println("   ✓ Ana Martínez (Estudiante - Ing. Industrial)");
        crearDocente("DOC002", "Pedro", "López", "pedro.lopez@ucb.edu.bo", "Base de Datos");
        System.out.println("   ✓ Pedro López (Docente - Base de Datos)");
        crearDirector("DIR002", "Laura", "Fernández", "laura.fernandez@ucb.edu.bo", ingInd);
        System.out.println("   ✓ Laura Fernández (Directora - Ing. Industrial)");
    }

    private void crearAulas() {
        System.out.println("\n🏛️  Creando Aulas...");
        crearAula(true, 30, "Edificio A", "A-201");
        crearAula(true, 35, "Edificio A", "A-202");
        crearAula(true, 40, "Edificio B", "B-101");
        crearAula(true, 25, "Edificio B", "B-102");
        crearAula(true, 45, "Edificio C", "C-301");
    }

    private void crearAula(boolean disponible, int capacidad, String edificio, String codigo) {
        Aula aula = new Aula(disponible, capacidad, edificio, codigo);
        servicioAula.crear(aula);
        System.out.println("   ✓ Aula " + codigo + " (Capacidad: " + capacidad + ")");
    }

    private void crearMaterias() {
        System.out.println("\n📖 Creando Materias...");
        Materia prog1 = crearMateria("SIS-101", "Programación I", 1, 4);
        crearMateria("MAT-101", "Matemáticas I", 1, 4);
        crearMateria("FIS-101", "Física I", 1, 3);

        Materia prog2 = crearMateria("SIS-201", "Programación II", 2, 4);
        prog2.getMateriasCorrelativas().add(prog1);
        servicioMateria.actualizar(prog2);

        Materia estDatos = crearMateria("SIS-202", "Estructuras de Datos", 2, 4);
        estDatos.getMateriasCorrelativas().add(prog1);
        servicioMateria.actualizar(estDatos);

        Materia bd1 = crearMateria("SIS-203", "Base de Datos I", 2, 3);

        Materia alg = crearMateria("SIS-301", "Algoritmos Avanzados", 3, 4);
        alg.getMateriasCorrelativas().add(estDatos);
        servicioMateria.actualizar(alg);

        Materia bd2 = crearMateria("SIS-302", "Base de Datos II", 3, 4);
        bd2.getMateriasCorrelativas().add(bd1);
        servicioMateria.actualizar(bd2);

        Materia ingSw = crearMateria("SIS-303", "Ingeniería de Software", 3, 4);
        ingSw.getMateriasCorrelativas().add(prog2);
        servicioMateria.actualizar(ingSw);

        Materia arqSw = crearMateria("SIS-401", "Arquitectura de Software", 4, 4);
        arqSw.getMateriasCorrelativas().add(ingSw);
        servicioMateria.actualizar(arqSw);

        Materia devWeb = crearMateria("SIS-402", "Desarrollo Web", 4, 4);
        devWeb.getMateriasCorrelativas().add(bd1);
        servicioMateria.actualizar(devWeb);
    }

    private Materia crearMateria(String codigo, String nombre, int semestre, int creditos) {
        Materia materia = new Materia();
        materia.setCodigo(codigo);
        materia.setNombre(nombre);
        materia.setSemestre(semestre);
        materia.setCreditos(creditos);
        servicioMateria.crear(materia);
        System.out.println("   ✓ " + nombre + " (" + codigo + ") - Semestre " + semestre);
        return materia;
    }

    private void crearParalelos() {
        System.out.println("\n📅 Creando Paralelos con Horarios...");
        
        Materia prog1 = servicioMateria.buscarPorCodigo("SIS-101");
        Materia bd1 = servicioMateria.buscarPorCodigo("SIS-203");
        Materia ingSw = servicioMateria.buscarPorCodigo("SIS-303");
        Materia devWeb = servicioMateria.buscarPorCodigo("SIS-402");
        Docente doc1 = servicioDocente.buscarPorCodigo("DOC001");
        Docente doc2 = servicioDocente.buscarPorCodigo("DOC002");
        Aula aula201 = servicioAula.getAulas().get(0);
        Aula aula202 = servicioAula.getAulas().get(1);
        Aula aulaB101 = servicioAula.getAulas().get(2);
        Aula aulaB102 = servicioAula.getAulas().get(3);
        Aula aulaC301 = servicioAula.getAulas().get(4);

        crearParalelo("SIS-101-A", prog1, doc1, aula201, 30,
            new Horario("LUNES", LocalTime.of(8, 0), LocalTime.of(10, 0)),
            new Horario("MIERCOLES", LocalTime.of(8, 0), LocalTime.of(10, 0)));

        crearParalelo("SIS-101-B", prog1, doc2, aula202, 35,
            new Horario("MARTES", LocalTime.of(10, 0), LocalTime.of(12, 0)),
            new Horario("JUEVES", LocalTime.of(10, 0), LocalTime.of(12, 0)));

        crearParalelo("SIS-203-A", bd1, doc2, aulaB101, 40,
            new Horario("LUNES", LocalTime.of(14, 0), LocalTime.of(16, 0)),
            new Horario("MIERCOLES", LocalTime.of(14, 0), LocalTime.of(16, 0)));

        crearParalelo("SIS-303-A", ingSw, doc1, aulaC301, 45,
            new Horario("MARTES", LocalTime.of(8, 0), LocalTime.of(10, 0)),
            new Horario("JUEVES", LocalTime.of(8, 0), LocalTime.of(10, 0)));

        crearParalelo("SIS-402-A", devWeb, doc1, aulaB102, 25,
            new Horario("VIERNES", LocalTime.of(10, 0), LocalTime.of(13, 0)));
    }

    private void crearParalelo(String codigo, Materia materia, Docente docente, Aula aula, int cupo, Horario... horarios) {
        ParaleloMateria paralelo = new ParaleloMateria();
        paralelo.setCodigo(codigo);
        paralelo.setMateria(materia);
        paralelo.setDocente(docente);
        paralelo.setAula(aula);
        paralelo.setCupoMaximo(cupo);

        for (Horario horario : horarios)
            paralelo.getHorarios().add(horario);

        servicioParaleloMateria.crear(paralelo);
        System.out.println("   ✓ " + codigo + ": " + materia.getNombre());
    }

    private void imprimirResumen() {
        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("✅ DATOS INICIALIZADOS CORRECTAMENTE");
        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println("\n📋 RESUMEN:");
        System.out.println("   • 3 Gestiones académicas");
        System.out.println("   • 3 Carreras");
        System.out.println("   • 2 Estudiantes");
        System.out.println("   • 2 Docentes");
        System.out.println("   • 2 Directores");
        System.out.println("   • 5 Aulas");
        System.out.println("   • 11 Materias");
        System.out.println("   • 5 Paralelos con Horarios");
        System.out.println("\n📋 USUARIOS DE PRUEBA DISPONIBLES PARA LOGIN:");
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ ESTUDIANTE                                              │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ Email    : juan.perez@ucb.edu.bo                        │");
        System.out.println("│ Password : password123 (no se valida en desarrollo)     │");
        System.out.println("│ Rol      : ESTUDIANTE                                   │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ DOCENTE                                                 │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ Email    : maria.gonzalez@ucb.edu.bo                    │");
        System.out.println("│ Password : password123 (no se valida en desarrollo)     │");
        System.out.println("│ Rol      : DOCENTE                                      │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ DIRECTOR                                                │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ Email    : carlos.rodriguez@ucb.edu.bo                  │");
        System.out.println("│ Password : password123 (no se valida en desarrollo)     │");
        System.out.println("│ Rol      : DIRECTOR                                     │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        System.out.println("\n💡 Tip: Usa el 'Acceso rápido' en la página de Login");
        System.out.println("════════════════════════════════════════════════════════════\n");
    }
}
