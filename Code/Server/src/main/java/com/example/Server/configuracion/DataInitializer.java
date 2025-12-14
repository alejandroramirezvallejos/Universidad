package com.example.Server.config;

import com.example.Server.modelos.*;
import com.example.Server.servicios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Inicializador de datos de prueba
 * Se ejecuta automáticamente al iniciar la aplicación
 * Crea usuarios de prueba para facilitar el testing del Login
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    
    private final ServicioCarrera servicioCarrera;
    private final ServicioEstudiante servicioEstudiante;
    private final ServicioDocente servicioDocente;
    private final ServicioDirectorCarrera servicioDirector;
    private final ServicioGestion servicioGestion;
    private final ServicioMateria servicioMateria;
    private final ServicioAula servicioAula;
    private final ServicioParaleloMateria servicioParaleloMateria;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("🔧 INICIALIZANDO DATOS DE PRUEBA");
        System.out.println("════════════════════════════════════════════════════════════\n");

        // ═══════════════════════════════════════════════════════════════
        // 1. CREAR GESTIONES (PERIODOS ACADÉMICOS)
        // ═══════════════════════════════════════════════════════════════
        System.out.println("📅 Creando Gestiones Académicas...");
        
        Gestion gestionActual = new Gestion();
        gestionActual.setCodigo("II-2025");
        gestionActual.setNombre("Segundo Semestre 2025");
        gestionActual.setAnio(2025);
        gestionActual.setPeriodo(2);
        gestionActual.setFechaInicio(java.sql.Date.valueOf("2025-08-01"));
        gestionActual.setFechaFin(java.sql.Date.valueOf("2025-12-15"));
        gestionActual.setFechaInicioMatricula(java.sql.Date.valueOf("2025-07-15"));
        gestionActual.setFechaFinMatricula(java.sql.Date.valueOf("2025-07-30"));
        gestionActual.setEstado("EN_CURSO");
        servicioGestion.crear(gestionActual);
        System.out.println("   ✓ II-2025 (EN CURSO)");

        Gestion gestionAnterior = new Gestion();
        gestionAnterior.setCodigo("I-2025");
        gestionAnterior.setNombre("Primer Semestre 2025");
        gestionAnterior.setAnio(2025);
        gestionAnterior.setPeriodo(1);
        gestionAnterior.setFechaInicio(java.sql.Date.valueOf("2025-02-01"));
        gestionAnterior.setFechaFin(java.sql.Date.valueOf("2025-06-30"));
        gestionAnterior.setFechaInicioMatricula(java.sql.Date.valueOf("2025-01-15"));
        gestionAnterior.setFechaFinMatricula(java.sql.Date.valueOf("2025-01-30"));
        gestionAnterior.setEstado("CERRADA");
        servicioGestion.crear(gestionAnterior);
        System.out.println("   ✓ I-2025 (CERRADA)");

        Gestion gestionHistorica = new Gestion();
        gestionHistorica.setCodigo("II-2024");
        gestionHistorica.setNombre("Segundo Semestre 2024");
        gestionHistorica.setAnio(2024);
        gestionHistorica.setPeriodo(2);
        gestionHistorica.setFechaInicio(java.sql.Date.valueOf("2024-08-01"));
        gestionHistorica.setFechaFin(java.sql.Date.valueOf("2024-12-15"));
        gestionHistorica.setFechaInicioMatricula(java.sql.Date.valueOf("2024-07-15"));
        gestionHistorica.setFechaFinMatricula(java.sql.Date.valueOf("2024-07-30"));
        gestionHistorica.setEstado("CERRADA");
        servicioGestion.crear(gestionHistorica);
        System.out.println("   ✓ II-2024 (CERRADA)");

        // ═══════════════════════════════════════════════════════════════
        // 2. CREAR CARRERAS
        // ═══════════════════════════════════════════════════════════════
        System.out.println("\n📚 Creando Carreras...");
        
        Carrera ingenieriaSistemas = new Carrera();
        ingenieriaSistemas.setCodigo("ING-SIS");
        ingenieriaSistemas.setNombre("Ingeniería de Sistemas");
        servicioCarrera.crear(ingenieriaSistemas);
        System.out.println("   ✓ Ingeniería de Sistemas (ING-SIS)");

        Carrera ingenieriaIndustrial = new Carrera();
        ingenieriaIndustrial.setCodigo("ING-IND");
        ingenieriaIndustrial.setNombre("Ingeniería Industrial");
        servicioCarrera.crear(ingenieriaIndustrial);
        System.out.println("   ✓ Ingeniería Industrial (ING-IND)");

        Carrera administracion = new Carrera();
        administracion.setCodigo("ADM-EMP");
        administracion.setNombre("Administración de Empresas");
        servicioCarrera.crear(administracion);
        System.out.println("   ✓ Administración de Empresas (ADM-EMP)");

        // ═══════════════════════════════════════════════════════════════
        // 3. CREAR ESTUDIANTE DE PRUEBA
        // ═══════════════════════════════════════════════════════════════
        System.out.println("\n👨‍🎓 Creando Estudiante de Prueba...");
        
        Estudiante estudiante = new Estudiante();
        estudiante.setCodigo("EST001");
        estudiante.setNombre("Juan");
        estudiante.setApellido("Pérez");
        estudiante.setEmail("juan.perez@ucb.edu.bo");
        estudiante.setCarrera(ingenieriaSistemas);
        servicioEstudiante.crear(estudiante);
        
        System.out.println("   ✓ Juan Pérez");
        System.out.println("     Email: juan.perez@ucb.edu.bo");
        System.out.println("     Código: EST001");
        System.out.println("     Carrera: Ingeniería de Sistemas");

        // ═══════════════════════════════════════════════════════════════
        // 4. CREAR DOCENTE DE PRUEBA
        // ═══════════════════════════════════════════════════════════════
        System.out.println("\n👩‍🏫 Creando Docente de Prueba...");
        
        Docente docente = new Docente();
        docente.setCodigo("DOC001");
        docente.setNombre("María");
        docente.setApellido("González");
        docente.setEmail("maria.gonzalez@ucb.edu.bo");
        docente.setEspecialidad("Ingeniería de Software");
        servicioDocente.crear(docente);
        
        System.out.println("   ✓ María González");
        System.out.println("     Email: maria.gonzalez@ucb.edu.bo");
        System.out.println("     Código: DOC001");
        System.out.println("     Especialidad: Ingeniería de Software");

        // ═══════════════════════════════════════════════════════════════
        // 5. CREAR DIRECTOR DE CARRERA DE PRUEBA
        // ═══════════════════════════════════════════════════════════════
        System.out.println("\n👔 Creando Director de Carrera de Prueba...");
        
        DirectorCarrera director = new DirectorCarrera();
        director.setCodigo("DIR001");
        director.setNombre("Carlos");
        director.setApellido("Rodríguez");
        director.setEmail("carlos.rodriguez@ucb.edu.bo");
        director.setCarrera(ingenieriaSistemas);
        servicioDirector.crear(director);
        
        System.out.println("   ✓ Carlos Rodríguez");
        System.out.println("     Email: carlos.rodriguez@ucb.edu.bo");
        System.out.println("     Código: DIR001");
        System.out.println("     Carrera: Ingeniería de Sistemas");

        // ═══════════════════════════════════════════════════════════════
        // 6. CREAR USUARIOS ADICIONALES PARA TESTING
        // ═══════════════════════════════════════════════════════════════
        System.out.println("\n🧪 Creando Usuarios Adicionales...");
        
        // Estudiante adicional
        Estudiante estudiante2 = new Estudiante();
        estudiante2.setCodigo("EST002");
        estudiante2.setNombre("Ana");
        estudiante2.setApellido("Martínez");
        estudiante2.setEmail("ana.martinez@ucb.edu.bo");
        estudiante2.setCarrera(ingenieriaIndustrial);
        servicioEstudiante.crear(estudiante2);
        System.out.println("   ✓ Ana Martínez (Estudiante - Ing. Industrial)");

        // Docente adicional
        Docente docente2 = new Docente();
        docente2.setCodigo("DOC002");
        docente2.setNombre("Pedro");
        docente2.setApellido("López");
        docente2.setEmail("pedro.lopez@ucb.edu.bo");
        docente2.setEspecialidad("Base de Datos");
        servicioDocente.crear(docente2);
        System.out.println("   ✓ Pedro López (Docente - Base de Datos)");

        // Director adicional
        DirectorCarrera director2 = new DirectorCarrera();
        director2.setCodigo("DIR002");
        director2.setNombre("Laura");
        director2.setApellido("Fernández");
        director2.setEmail("laura.fernandez@ucb.edu.bo");
        director2.setCarrera(ingenieriaIndustrial);
        servicioDirector.crear(director2);
        System.out.println("   ✓ Laura Fernández (Directora - Ing. Industrial)");

        // ═══════════════════════════════════════════════════════════════
        // 7. CREAR AULAS
        // ═══════════════════════════════════════════════════════════════
        System.out.println("\n🏛️  Creando Aulas...");
        
        Aula aula201 = new Aula(true, 30, "Edificio A", "A-201");
        servicioAula.crear(aula201);
        System.out.println("   ✓ Aula A-201 (Capacidad: 30)");
        
        Aula aula202 = new Aula(true, 35, "Edificio A", "A-202");
        servicioAula.crear(aula202);
        System.out.println("   ✓ Aula A-202 (Capacidad: 35)");
        
        Aula aulaB101 = new Aula(true, 40, "Edificio B", "B-101");
        servicioAula.crear(aulaB101);
        System.out.println("   ✓ Aula B-101 (Capacidad: 40)");
        
        Aula aulaB102 = new Aula(true, 25, "Edificio B", "B-102");
        servicioAula.crear(aulaB102);
        System.out.println("   ✓ Aula B-102 (Capacidad: 25)");
        
        Aula aulaC301 = new Aula(true, 45, "Edificio C", "C-301");
        servicioAula.crear(aulaC301);
        System.out.println("   ✓ Aula C-301 (Capacidad: 45)");

        // ═══════════════════════════════════════════════════════════════
        // 8. CREAR MATERIAS
        // ═══════════════════════════════════════════════════════════════
        System.out.println("\n📖 Creando Materias...");
        
        // Semestre 1
        Materia programacion1 = new Materia();
        programacion1.setCodigo("SIS-101");
        programacion1.setNombre("Programación I");
        programacion1.setSemestre(1);
        programacion1.setCreditos(4);
        servicioMateria.crear(programacion1);
        System.out.println("   ✓ Programación I (SIS-101) - Semestre 1");
        
        Materia matematicas1 = new Materia();
        matematicas1.setCodigo("MAT-101");
        matematicas1.setNombre("Matemáticas I");
        matematicas1.setSemestre(1);
        matematicas1.setCreditos(4);
        servicioMateria.crear(matematicas1);
        System.out.println("   ✓ Matemáticas I (MAT-101) - Semestre 1");
        
        Materia fisica1 = new Materia();
        fisica1.setCodigo("FIS-101");
        fisica1.setNombre("Física I");
        fisica1.setSemestre(1);
        fisica1.setCreditos(3);
        servicioMateria.crear(fisica1);
        System.out.println("   ✓ Física I (FIS-101) - Semestre 1");
        
        // Semestre 2
        Materia programacion2 = new Materia();
        programacion2.setCodigo("SIS-201");
        programacion2.setNombre("Programación II");
        programacion2.setSemestre(2);
        programacion2.setCreditos(4);
        programacion2.getMateriasCorrelativas().add(programacion1);
        servicioMateria.crear(programacion2);
        System.out.println("   ✓ Programación II (SIS-201) - Semestre 2");
        
        Materia estructurasDatos = new Materia();
        estructurasDatos.setCodigo("SIS-202");
        estructurasDatos.setNombre("Estructuras de Datos");
        estructurasDatos.setSemestre(2);
        estructurasDatos.setCreditos(4);
        estructurasDatos.getMateriasCorrelativas().add(programacion1);
        servicioMateria.crear(estructurasDatos);
        System.out.println("   ✓ Estructuras de Datos (SIS-202) - Semestre 2");
        
        Materia baseDatos1 = new Materia();
        baseDatos1.setCodigo("SIS-203");
        baseDatos1.setNombre("Base de Datos I");
        baseDatos1.setSemestre(2);
        baseDatos1.setCreditos(3);
        servicioMateria.crear(baseDatos1);
        System.out.println("   ✓ Base de Datos I (SIS-203) - Semestre 2");
        
        // Semestre 3
        Materia algoritmos = new Materia();
        algoritmos.setCodigo("SIS-301");
        algoritmos.setNombre("Algoritmos Avanzados");
        algoritmos.setSemestre(3);
        algoritmos.setCreditos(4);
        algoritmos.getMateriasCorrelativas().add(estructurasDatos);
        servicioMateria.crear(algoritmos);
        System.out.println("   ✓ Algoritmos Avanzados (SIS-301) - Semestre 3");
        
        Materia baseDatos2 = new Materia();
        baseDatos2.setCodigo("SIS-302");
        baseDatos2.setNombre("Base de Datos II");
        baseDatos2.setSemestre(3);
        baseDatos2.setCreditos(4);
        baseDatos2.getMateriasCorrelativas().add(baseDatos1);
        servicioMateria.crear(baseDatos2);
        System.out.println("   ✓ Base de Datos II (SIS-302) - Semestre 3");
        
        Materia ingenieriaSoftware = new Materia();
        ingenieriaSoftware.setCodigo("SIS-303");
        ingenieriaSoftware.setNombre("Ingeniería de Software");
        ingenieriaSoftware.setSemestre(3);
        ingenieriaSoftware.setCreditos(4);
        ingenieriaSoftware.getMateriasCorrelativas().add(programacion2);
        servicioMateria.crear(ingenieriaSoftware);
        System.out.println("   ✓ Ingeniería de Software (SIS-303) - Semestre 3");
        
        // Semestre 4
        Materia arquitecturaSoftware = new Materia();
        arquitecturaSoftware.setCodigo("SIS-401");
        arquitecturaSoftware.setNombre("Arquitectura de Software");
        arquitecturaSoftware.setSemestre(4);
        arquitecturaSoftware.setCreditos(4);
        arquitecturaSoftware.getMateriasCorrelativas().add(ingenieriaSoftware);
        servicioMateria.crear(arquitecturaSoftware);
        System.out.println("   ✓ Arquitectura de Software (SIS-401) - Semestre 4");
        
        Materia desarrolloWeb = new Materia();
        desarrolloWeb.setCodigo("SIS-402");
        desarrolloWeb.setNombre("Desarrollo Web");
        desarrolloWeb.setSemestre(4);
        desarrolloWeb.setCreditos(4);
        desarrolloWeb.getMateriasCorrelativas().add(baseDatos1);
        servicioMateria.crear(desarrolloWeb);
        System.out.println("   ✓ Desarrollo Web (SIS-402) - Semestre 4");

        // ═══════════════════════════════════════════════════════════════
        // 9. CREAR PARALELOS CON HORARIOS
        // ═══════════════════════════════════════════════════════════════
        System.out.println("\n📅 Creando Paralelos con Horarios...");
        
        // Paralelo A de Programación I
        ParaleloMateria prog1ParaleloA = new ParaleloMateria();
        prog1ParaleloA.setCodigo("SIS-101-A");
        prog1ParaleloA.setMateria(programacion1);
        prog1ParaleloA.setDocente(docente);
        prog1ParaleloA.setAula(aula201);
        prog1ParaleloA.setCupoMaximo(30);
        prog1ParaleloA.getHorarios().add(new Horario("LUNES", java.time.LocalTime.of(8, 0), java.time.LocalTime.of(10, 0)));
        prog1ParaleloA.getHorarios().add(new Horario("MIERCOLES", java.time.LocalTime.of(8, 0), java.time.LocalTime.of(10, 0)));
        servicioParaleloMateria.crear(prog1ParaleloA);
        System.out.println("   ✓ SIS-101-A: Programación I - Lun/Mié 08:00-10:00");
        
        // Paralelo B de Programación I
        ParaleloMateria prog1ParaleloB = new ParaleloMateria();
        prog1ParaleloB.setCodigo("SIS-101-B");
        prog1ParaleloB.setMateria(programacion1);
        prog1ParaleloB.setDocente(docente2);
        prog1ParaleloB.setAula(aula202);
        prog1ParaleloB.setCupoMaximo(35);
        prog1ParaleloB.getHorarios().add(new Horario("MARTES", java.time.LocalTime.of(10, 0), java.time.LocalTime.of(12, 0)));
        prog1ParaleloB.getHorarios().add(new Horario("JUEVES", java.time.LocalTime.of(10, 0), java.time.LocalTime.of(12, 0)));
        servicioParaleloMateria.crear(prog1ParaleloB);
        System.out.println("   ✓ SIS-101-B: Programación I - Mar/Jue 10:00-12:00");
        
        // Paralelo A de Base de Datos I
        ParaleloMateria bd1ParaleloA = new ParaleloMateria();
        bd1ParaleloA.setCodigo("SIS-203-A");
        bd1ParaleloA.setMateria(baseDatos1);
        bd1ParaleloA.setDocente(docente2);
        bd1ParaleloA.setAula(aulaB101);
        bd1ParaleloA.setCupoMaximo(40);
        bd1ParaleloA.getHorarios().add(new Horario("LUNES", java.time.LocalTime.of(14, 0), java.time.LocalTime.of(16, 0)));
        bd1ParaleloA.getHorarios().add(new Horario("MIERCOLES", java.time.LocalTime.of(14, 0), java.time.LocalTime.of(16, 0)));
        servicioParaleloMateria.crear(bd1ParaleloA);
        System.out.println("   ✓ SIS-203-A: Base de Datos I - Lun/Mié 14:00-16:00");
        
        // Paralelo A de Ingeniería de Software
        ParaleloMateria ingSwParaleloA = new ParaleloMateria();
        ingSwParaleloA.setCodigo("SIS-303-A");
        ingSwParaleloA.setMateria(ingenieriaSoftware);
        ingSwParaleloA.setDocente(docente);
        ingSwParaleloA.setAula(aulaC301);
        ingSwParaleloA.setCupoMaximo(45);
        ingSwParaleloA.getHorarios().add(new Horario("MARTES", java.time.LocalTime.of(8, 0), java.time.LocalTime.of(10, 0)));
        ingSwParaleloA.getHorarios().add(new Horario("JUEVES", java.time.LocalTime.of(8, 0), java.time.LocalTime.of(10, 0)));
        servicioParaleloMateria.crear(ingSwParaleloA);
        System.out.println("   ✓ SIS-303-A: Ingeniería de Software - Mar/Jue 08:00-10:00");
        
        // Paralelo A de Desarrollo Web
        ParaleloMateria devWebParaleloA = new ParaleloMateria();
        devWebParaleloA.setCodigo("SIS-402-A");
        devWebParaleloA.setMateria(desarrolloWeb);
        devWebParaleloA.setDocente(docente);
        devWebParaleloA.setAula(aulaB102);
        devWebParaleloA.setCupoMaximo(25);
        devWebParaleloA.getHorarios().add(new Horario("VIERNES", java.time.LocalTime.of(10, 0), java.time.LocalTime.of(13, 0)));
        servicioParaleloMateria.crear(devWebParaleloA);
        System.out.println("   ✓ SIS-402-A: Desarrollo Web - Vie 10:00-13:00");

        // ═══════════════════════════════════════════════════════════════
        // 10. RESUMEN
        // ═══════════════════════════════════════════════════════════════
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
