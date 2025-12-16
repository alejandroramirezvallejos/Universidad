# SISTEMA DE GESTIÓN UNIVERSITARIA

## INTRODUCCIÓN

El Sistema de Gestión Universitaria es una plataforma web integral diseñada para digitalizar y optimizar los procesos académicos y administrativos de una institución educativa. Este sistema centraliza la gestión de estudiantes, docentes, materias, grupos, inscripciones, calificaciones y reportes académicos en una única aplicación accesible desde cualquier navegador web.

El proyecto nace de la necesidad de modernizar los procesos académicos tradicionales que actualmente se realizan de forma manual o mediante sistemas desconectados, lo que genera ineficiencias, errores y pérdida de tiempo tanto para estudiantes como para el personal administrativo y docente.

Este sistema proporciona una solución tecnológica robusta que facilita la comunicación entre todos los actores del proceso educativo (estudiantes, docentes y directores de carrera), permitiendo realizar operaciones como inscripciones a materias, registro de calificaciones, gestión de horarios y generación de reportes de manera ágil, transparente y en tiempo real.

---

## CONTEXTO DEL PROYECTO

### Problemática Actual

Las instituciones educativas enfrentan múltiples desafíos en la gestión de sus procesos académicos:

- **Procesos Manuales:** La inscripción de materias, aprobación de matrículas y registro de calificaciones se realizan presencialmente o mediante formularios en papel
- **Falta de Centralización:** La información académica está dispersa en múltiples sistemas o documentos físicos
- **Errores Humanos:** El registro manual de información académica es propenso a errores de transcripción
- **Tiempo de Respuesta:** Los procesos de aprobación de matrículas y resolución de consultas toman días o semanas
- **Falta de Trazabilidad:** Es difícil hacer seguimiento del historial académico y rendimiento estudiantil
- **Gestión de Horarios:** La asignación de aulas, docentes y horarios se realiza sin validación automática de conflictos

---

## OBJETIVOS

### Objetivo General

Desarrollar e implementar un sistema web de gestión universitaria que digitalice y optimice los procesos académicos y administrativos de una institución educativa, proporcionando una plataforma centralizada, eficiente y accesible para estudiantes, docentes y directores de carrera.

### Objetivos Específicos

#### 1. **Automatizar el Proceso de Matrícula**
- Permitir que los estudiantes consulten la oferta académica disponible en tiempo real
- Facilitar la inscripción en materias de forma autónoma y digital
- Implementar validaciones automáticas de prerrequisitos, conflictos de horario y cupos
- Habilitar la aprobación/rechazo de matrículas por parte de directores con motivos justificados

#### 2. **Digitalizar el Registro Académico**
- Proporcionar a los docentes una interfaz para registrar y editar calificaciones
- Permitir a los estudiantes consultar sus calificaciones en tiempo real
- Generar y mantener el historial académico completo de cada estudiante
- Calcular automáticamente promedios, créditos aprobados y progreso curricular

#### 3. **Facilitar la Visualización de Información Académica**
- Generar horarios personalizados en formato calendario para estudiantes y docentes
- Mostrar dashboards personalizados según el rol del usuario
- Visualizar el historial académico completo con estadísticas de rendimiento
- Permitir la consulta de oferta académica con filtros y búsqueda

---

## ALCANCE

### Dentro del Alcance

El sistema incluye las siguientes funcionalidades:

#### **Módulo de Autenticación**
- Inicio de sesión con email y contraseña
- Registro de estudiantes y docentes
- Gestión de perfiles personales

#### **Módulo de Matrícula (Estudiante)**
- Consulta de oferta académica por gestión
- Inscripción en materias/grupos
- Cancelación de inscripciones
- Visualización de matrícula actual y su estado
- Consulta de calificaciones
- Visualización de horario personal
- Acceso al historial académico

#### **Módulo de Calificaciones (Docente)**
- Registro de calificaciones por tipo de evaluación
- Edición de calificaciones registradas
- Visualización de grupos asignados
- Consulta de horario personal de clases

#### **Módulo de Gestión de Matrículas (Director)**
- Revisión de solicitudes de matrícula pendientes
- Aprobación de inscripciones
- Rechazo de inscripciones con motivo

#### **Módulo de Períodos Académicos (Director)**
- Creación de períodos académicos (gestiones)
- Edición de fechas de períodos
- Activación de período para matrículas
- Cierre de período académico
- Eliminación de períodos sin datos

#### **Módulo de Materias (Director)**
- Creación de materias con código, créditos y nivel
- Edición de información de materias
- Definición de prerrequisitos
- Eliminación de materias sin uso
- Consulta del catálogo completo

#### **Módulo de Grupos/Paralelos (Director)**
- Creación de grupos por materia y gestión
- Edición de cupo y estado de grupos
- Asignación de docentes a grupos
- Asignación de aulas y horarios
- Eliminación de grupos sin inscripciones
- Consulta de grupos con filtros

#### **Módulo de Estudiantes (Director)**
- Registro manual de estudiantes
- Edición de información estudiantil
- Cambio de estado académico
- Eliminación de registros sin historial
- Consulta con filtros y búsqueda

#### **Módulo de Docentes (Director)**
- Registro de docentes con especialidades
- Edición de datos y especialidades
- Eliminación de docentes sin carga
- Consulta con filtros por especialidad

#### **Módulo de Aulas (Director)**
- Registro de aulas con capacidad y tipo
- Edición de información y disponibilidad
- Eliminación de aulas sin uso
- Consulta con filtros

#### **Módulo de Reportes y Consultas**
- Generación de reportes estadísticos
- Visualización de dashboards personalizados
- Consulta de historial académico completo
- Exportación de datos en múltiples formatos

### Roles y Permisos

El sistema define tres roles principales con funcionalidades específicas:

#### 1. **ESTUDIANTE**

**Descripción:** Usuario que cursa materias y consulta información académica.

**Funcionalidades:**
- ✅ Consultar oferta académica disponible
- ✅ Inscribirse en materias (solicitar matrícula)
- ✅ Cancelar inscripciones propias
- ✅ Ver su matrícula actual con estados
- ✅ Consultar sus calificaciones
- ✅ Ver su horario personal
- ✅ Consultar historial académico completo
- ✅ Editar su información personal
- ✅ Ver dashboard con resumen académico

#### 2. **DOCENTE**

**Descripción:** Usuario que imparte materias y registra calificaciones.

**Funcionalidades:**
- ✅ Ver grupos asignados en la gestión actual
- ✅ Registrar calificaciones de estudiantes
- ✅ Editar calificaciones previamente registradas
- ✅ Ver lista de estudiantes por grupo
- ✅ Consultar su horario de clases
- ✅ Editar su información personal
- ✅ Ver dashboard con grupos y clases del día

#### 3. **DIRECTOR DE CARRERA**

**Descripción:** Usuario administrador con permisos totales de gestión.

**Funcionalidades:**
- ✅ Aprobar/rechazar solicitudes de matrícula
- ✅ Crear y gestionar períodos académicos
- ✅ Administrar catálogo de materias
- ✅ Crear y gestionar grupos/paralelos
- ✅ Asignar docentes a grupos
- ✅ Asignar aulas y horarios a grupos
- ✅ Gestionar estudiantes (CRUD completo)
- ✅ Gestionar docentes (CRUD completo)
- ✅ Gestionar aulas (CRUD completo)
- ✅ Generar reportes estadísticos
- ✅ Ver dashboard con métricas institucionales
- ✅ Editar su información personal

---

## TECNOLOGÍAS UTILIZADAS

### Frontend

**Angular** 18.x  Framework principal para SPA 
**TypeScript** 5.x  Lenguaje de programación tipado 

### Backend

**Spring Boot**  3.x  Framework principal 
**Java**  17  Lenguaje de programación 

---

# JUSTIFICACIÓN DE PATRONES DE DISEÑO

## Sistema de Gestión Universitaria - Backend

---

## 1. PATRÓN PROTOTYPE

### Ubicación
`modelos/Materia.java` e `IMateria.java`

### Implementación
```java
public class Materia implements IMateria {
    private List<Materia> materiasCorrelativas;
    private List<ParaleloMateria> paraleloMaterias;
    
    public Materia clonar() {
        List<Materia> correlativas = new ArrayList<>();
        for (Materia materia : this.materiasCorrelativas)
            correlativas.add(materia.clonar());
        
        return Materia.builder()
            .codigo(this.codigo)
            .nombre(this.nombre)
            .creditos(this.creditos)
            .materiasCorrelativas(correlativas)
            .paraleloMaterias(new ArrayList<>(this.paraleloMaterias))
            .carrera(this.carrera)
            .build();
    }
}
```

### Justificación

#### Problema que Resuelve
La entidad `Materia` tiene relaciones complejas y recursivas (materias correlativas, paralelos, carrera). Crear copias manualmente es propenso a errores y genera acoplamiento.

#### Ventajas Implementadas

1. **Clonación Profunda Automática**
   - Las materias correlativas se clonan recursivamente
   - Evita efectos colaterales al modificar copias
   - Preserva la integridad referencial

2. **Reutilización en el Sistema**
   - Crear plantillas de materias para nuevas gestiones
   - Duplicar estructuras curriculares entre carreras
   - Generar copias para reportes sin afectar datos originales

3. **Encapsulación de la Lógica de Copia**
   - El cliente no necesita conocer la estructura interna
   - Una sola línea: `materiaCopia = materia.clonar()`
   - Mantenimiento centralizado

---

## 2. PATRÓN STRATEGY

### Ubicación
`estrategias/`

### Implementación - Autenticación
```java
public interface IEstrategiaLogin {
    Usuario login(String email, String contrasenna);
}

@Component
public class ContextoLogin {
    private final List<IEstrategiaLogin> estrategias;
    
    public Usuario login(String email, String contrasenna) {
        for (IEstrategiaLogin estrategia : estrategias) {
            Usuario usuario = estrategia.login(email, contrasenna);
            if (usuario != null) return usuario;
        }
        return null;
    }
}

@Component
public class LoginEstudiante implements IEstrategiaLogin {
    @Override
    public Usuario login(String email, String contrasenna) {
        Estudiante estudiante = repositorioEstudiante.buscarPorEmail(email);
        if (estudiante == null) return null;
        
        String error = validadorLogin.validar(estudiante, contrasenna);
        if (error != null) return null;
        
        estudiante.setRol("ESTUDIANTE");
        return estudiante;
    }
}
```

### Implementación - Cálculo de Calificaciones
```java
public interface IEstrategiaCalculoCalificacion {
    double calcular(Estudiante estudiante, ParaleloMateria paralelo, 
                    List<Evaluacion> evaluaciones);
}

@Component
public class CalcularCalificacionFinal implements IEstrategiaCalculoCalificacion {
    @Override
    public double calcular(Estudiante estudiante, ParaleloMateria paralelo, 
                          List<Evaluacion> evaluaciones) {
        double notaFinal = 0.0;
        for (Evaluacion evaluacion : evaluaciones) {
            double valorCalificacion = servicioCalificacion
                .getCalificacionesEnEvaluacion(estudiante, evaluacion);
            notaFinal += valorCalificacion * (evaluacion.getPorcentaje() / 100.0);
        }
        return notaFinal;
    }
}
```

### Implementación - Reportes
```java
public interface IEstrategiaReporte {
    Map<String, Object> generar(Reporte reporte);
}

@Component
public class ReporteCarrera implements IEstrategiaReporte {
    @Override
    public Map<String, Object> generar(Reporte reporte) {
        if (!(reporte instanceof ReporteDeCarrera reporteDeCarrera))
            return null;
        
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("nombreCarrera", reporteDeCarrera.getCarrera().getNombre());
        resultado.put("totalEstudiantes", reporteDeCarrera.getEstudiantes().size());
        resultado.put("estudiantes", reporteDeCarrera.getEstudiantes());
        return resultado;
    }
}
```

### Justificación

#### Problema que Resuelve
El sistema requiere múltiples algoritmos para:
- **Login**: Diferentes tipos de usuarios (Estudiante, Docente, Director) con repositorios distintos
- **Calificaciones**: Cálculo ponderado vs promedio simple
- **Reportes**: Diferentes formatos (Carrera, Rendimiento, Inscripciones)
- **Créditos**: Totales vs aprobados

Sin Strategy, tendríamos condicionales complejos (`if-else`, `switch`) difíciles de mantener y extender.

#### Ventajas Implementadas

1. **Eliminación de Condicionales Complejos**
   
   **Sin Strategy:**
   ```java
   if (tipoUsuario.equals("ESTUDIANTE")) {
       // lógica estudiante
   } else if (tipoUsuario.equals("DOCENTE")) {
       // lógica docente
   } else if (tipoUsuario.equals("DIRECTOR")) {
       // lógica director
   }
   ```
   
   **Con Strategy:**
   ```java
   for (IEstrategiaLogin estrategia : estrategias) {
       Usuario usuario = estrategia.login(email, contrasenna);
       if (usuario != null) return usuario;
   }
   ```

2. **Intercambiabilidad en Runtime**
   - Cambiar algoritmo de cálculo sin modificar el cliente
   - El contexto delega automáticamente a la estrategia correcta
   - Selección dinámica basada en tipo de reporte

3. **Extensibilidad (Open/Closed Principle)**
   ```java
   // Agregar nueva estrategia sin modificar código existente
   @Component
   public class LoginAdministrador implements IEstrategiaLogin {
       @Override
       public Usuario login(String email, String contrasenna) {
           // Nueva lógica de autenticación
       }
   }
   ```

---

## 3. PATRÓN CHAIN OF RESPONSIBILITY

### Ubicación
`validadores/` 

### Implementación - Validación de Matrícula
```java
public interface IValidarMatricula {
    String validar(Estudiante estudiante, ParaleloMateria paraleloMateria);
}

@Service
@Primary
@RequiredArgsConstructor
public class ValidacionDeMatricula implements IValidarMatricula {
    private final List<IValidarMatricula> validadores;
    
    @Override
    public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
        for (IValidarMatricula validador : validadores) {
            String error = validador.validar(estudiante, paraleloMateria);
            if (error != null) return error; // Corto-circuito
        }
        return null;
    }
}

```

#### Problema que Resuelve
La inscripción a materias requiere validar **9 condiciones complejas** antes de permitir la operación. Sin Chain of Responsibility, tendríamos un método gigante con lógica entrelazada, difícil de mantener y probar.

#### Ventajas Implementadas

1. **Desacoplamiento Total (Single Responsibility Principle)**
   - Cada validador se enfoca en UNA sola regla de negocio
   - Fácil de entender: nombre de clase = responsabilidad
   - Fácil de probar: test unitario por validador

2. **Corto-Circuito para Performance**
   ```java
   for (IValidarMatricula validador : validadores) {
       String error = validador.validar(estudiante, paraleloMateria);
       if (error != null) return error; // Se detiene al primer error
   }
   ```
   - No ejecuta validaciones innecesarias
   - Retorna mensaje de error específico
   - Optimiza tiempo de respuesta

3. **Extensibilidad Sin Modificación (Open/Closed)**
   ```java
   // Agregar nueva validación sin tocar código existente
   @Component
   @Order(10)
   public class ValidarCuotasPendientes implements IValidarMatricula {
       @Override
       public String validar(Estudiante estudiante, ParaleloMateria paraleloMateria) {
           if (estudiante.tieneCuotasPendientes())
               return "El estudiante tiene cuotas pendientes de pago";
           return null;
       }
   }
   ```
4. **Reutilización de Validadores**
   - `ValidarContrasenna` usado en login, cambio de contraseña, recuperación
   - `ValidarChoqueHorario` usado en matrícula y reasignación de aulas
   - Componentes independientes compartidos

---

## 4. PATRÓN OBSERVER

### Ubicación
`alertas/` - Sistema de notificaciones académicas

### Implementación Completa
```java
// Interfaces
public interface ISujeto {
    void suscribir(IObservador observador);
    void desuscribir(IObservador observador);
    void notificar(NotificacionEvento evento);
}

public interface IObservador {
    void actualizar(NotificacionEvento evento);
}

// Sujeto Concreto
@Component
public class ContextoNotificacion implements ISujeto {
    private final List<IObservador> observadores = new ArrayList<>();
    
    @Override
    public void notificar(NotificacionEvento evento) {
        for (IObservador observador : observadores)
            observador.actualizar(evento);
    }
    
    public void notificar(Estudiante estudiante, Materia materia, Double notaFinal) {
        NotificacionEvento evento = new NotificacionEvento(estudiante, materia, notaFinal);
        notificar(evento);
    }
}

// Observador Estudiante
@Component
@RequiredArgsConstructor
public class ObservadorEstudiante implements IObservador {
    private final ContextoNotificacion sujeto;
    
    @PostConstruct
    public void suscribir() { sujeto.suscribir(this); }
    
    @Override
    public void actualizar(NotificacionEvento evento) {
        String mensaje = evento.getNotaFinal() >= 51.0 ? "APROBADO" : "REPROBADO";
        System.out.println("NOTIFICACIÓN A ESTUDIANTE:");
        System.out.println("Hola " + evento.getEstudiante().getNombre() + ", " + mensaje);
        System.out.println("Materia: " + evento.getMateria().getNombre());
        System.out.println("Nota: " + evento.getNotaFinal());
    }
}

```

### Justificación

#### Problema que Resuelve
Cuando se registra una calificación, **múltiples actores** deben ser notificados simultáneamente:
- Estudiante: Conocer su resultado
- Docente: Confirmar el registro
- Director: Seguimiento estadístico

Sin Observer, el servicio de calificaciones estaría **acoplado** a todos los destinatarios, violando SRP y dificultando extensiones.

#### Ventajas Implementadas

1. **Comunicación Uno-a-Muchos Desacoplada**
   ```java
   // El sujeto NO conoce los tipos concretos de observadores
   for (IObservador observador : observadores)
       observador.actualizar(evento);
   ```
   - Una sola llamada notifica a todos
   - El sujeto no depende de clases concretas
   - Fácil agregar/quitar observadores

2. **Extensibilidad Total (Open/Closed)**
   ```java
   // Agregar nuevo observador sin modificar código existente
   @Component
   @RequiredArgsConstructor
   public class ObservadorEmail implements IObservador {
       @PostConstruct
       public void suscribir() { sujeto.suscribir(this); }
       
       @Override
       public void actualizar(NotificacionEvento evento) {
           emailService.enviar(evento.getEstudiante().getEmail(), 
                              "Tu nota en " + evento.getMateria().getNombre());
       }
   }
   
   @Component
   public class ObservadorSMS implements IObservador {
       @Override
       public void actualizar(NotificacionEvento evento) {
           smsService.enviar(evento.getEstudiante().getTelefono(), 
                            "Calificación registrada: " + evento.getNotaFinal());
       }
   }
   ```


---

# CASOS DE USO - SISTEMA DE GESTIÓN UNIVERSITARIA

**Proyecto:** Sistema de Gestión Universitaria
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**Versión:** 1.0 

## ÍNDICE DE CASOS DE USO

### 1. AUTENTICACIÓN Y PERFIL (4 casos)
- CU-1: Iniciar Sesión
- CU-2: Registrarse como Estudiante
- CU-3: Registrarse como Docente
- CU-4: Editar Información Personal

### 2. OFERTA ACADÉMICA Y MATRÍCULA - ESTUDIANTE (6 casos)
- CU-5: Consultar Oferta Académica
- CU-6: Inscribirse en Materia
- CU-7: Cancelar Inscripción de Materia
- CU-8: Ver Mi Matrícula
- CU-9: Ver Mis Calificaciones
- CU-10: Ver Mi Horario Personal

### 3. GESTIÓN DE CALIFICACIONES - DOCENTE (3 casos)
- CU-11: Registrar Calificación de Estudiante
- CU-12: Editar Calificación de Estudiante
- CU-13: Ver Horario Personal Docente

### 4. GESTIÓN DE MATRÍCULAS - DIRECTOR (2 casos)
- CU-14: Aceptar Solicitud de Matrícula
- CU-15: Rechazar Solicitud de Matrícula

### 5. PERÍODOS ACADÉMICOS - DIRECTOR (5 casos)
- CU-16: Crear Período Académico
- CU-17: Editar Período Académico
- CU-18: Activar Período Académico
- CU-19: Cerrar Período Académico
- CU-20: Eliminar Período Académico

### 6. MATERIAS - DIRECTOR (5 casos)
- CU-21: Crear Materia
- CU-22: Editar Materia
- CU-23: Eliminar Materia
- CU-24: Consultar Catálogo de Materias
- CU-25: Ver Detalle de Materia

### 7. GRUPOS/PARALELOS - DIRECTOR (6 casos)
- CU-26: Crear Grupo de Materia
- CU-27: Editar Grupo de Materia
- CU-28: Asignar Docente a Grupo
- CU-29: Asignar Aula y Horario a Grupo
- CU-30: Eliminar Grupo
- CU-31: Consultar Grupos

### 8. ESTUDIANTES - DIRECTOR (5 casos)
- CU-32: Crear Estudiante
- CU-33: Editar Estudiante
- CU-34: Cambiar Estado de Estudiante
- CU-35: Eliminar Estudiante
- CU-36: Consultar Estudiantes

### 9. DOCENTES - DIRECTOR (4 casos)
- CU-37: Crear Docente
- CU-38: Editar Docente
- CU-39: Eliminar Docente
- CU-40: Consultar Docentes

### 10. AULAS - DIRECTOR (4 casos)
- CU-41: Crear Aula
- CU-42: Editar Aula
- CU-43: Eliminar Aula
- CU-44: Consultar Aulas

### 11. CONSULTAS Y REPORTES (3 casos)
- CU-45: Ver Historial Académico
- CU-46: Generar Reporte Estadístico
- CU-47: Ver Dashboard Personalizado

---

# 1. AUTENTICACIÓN Y PERFIL

---

## CU-1: INICIAR SESIÓN

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-1

### Descripción
El usuario accede al sistema ingresando su email y contraseña para autenticarse y acceder a las funcionalidades según su rol.

### Actores
- Estudiante
- Docente
- Director de Carrera

### Precondiciones
- El usuario debe estar registrado en el sistema
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El usuario accede a la página web

**FP2:** El sistema muestra la página de inicio de sesión con:
- Campo "Email" (obligatorio)
- Campo "Contraseña" (obligatorio, oculto)
- Botón "Iniciar Sesión"
- Enlaces "Registrarse como Estudiante" y "Registrarse como Docente"

**FP3:** El usuario ingresa su email en el campo correspondiente

**FP4:** El usuario ingresa su contraseña en el campo correspondiente

**FP5:** El usuario hace clic en "Iniciar Sesión"

**FP6:** El sistema valida que ambos campos no estén vacíos

**FP7:** El sistema valida el formato del email

**FP8:** El sistema busca el usuario por email en la base de datos

**FP9:** El sistema verifica que el usuario exista

**FP10:** El sistema compara la contraseña ingresada con la almacenada

**FP11:** El sistema confirma que las credenciales son correctas

**FP12:** El sistema crea una sesión para el usuario

**FP13:** El sistema redirige según el rol:
- **Estudiante:** Dashboard con resumen de materias y calificaciones
- **Docente:** Dashboard con grupos asignados y horarios
- **Director:** Dashboard con estadísticas y gestión administrativa


### Subflujos

#### SF1: Email vacío (Paso 6 → Paso 3)
**SF1.1:** En FP6, el sistema detecta que el campo email está vacío  
**SF1.2:** El sistema muestra mensaje: "El email es requerido"  
**SF1.3:** El sistema marca el campo en rojo  
**SF1.4:** El flujo retorna a FP3  

#### SF2: Contraseña vacía (Paso 6 → Paso 4)
**SF2.1:** En FP6, el sistema detecta que el campo contraseña está vacío  
**SF2.2:** El sistema muestra mensaje: "La contraseña es requerida"  
**SF2.3:** El sistema marca el campo en rojo  
**SF2.4:** El flujo retorna a FP4  

#### SF3: Formato de email inválido (Paso 7 → Paso 3)
**SF3.1:** En FP7, el sistema detecta formato de email incorrecto  
**SF3.2:** El sistema muestra mensaje: "Ingrese un email válido"  
**SF3.3:** El sistema marca el campo en rojo  
**SF3.4:** El flujo retorna a FP3  

#### SF4: Usuario no encontrado (Paso 9 → Paso 3)
**SF4.1:** En FP9, el sistema no encuentra el email en la base de datos  
**SF4.2:** El sistema muestra mensaje: "Email o contraseña incorrectos"  
**SF4.3:** El flujo retorna a FP3  

#### SF5: Contraseña incorrecta (Paso 11 → Paso 3)
**SF5.1:** En FP11, la contraseña no coincide  
**SF5.2:** El sistema muestra mensaje: "Email o contraseña incorrectos"  
**SF5.3:** El sistema registra el intento fallido  
**SF5.4:** El flujo retorna a FP3  

### Extensiones

#### EX1: Ir a Registro de Estudiante
**EX1.1:** En FP2, el usuario puede hacer clic en "Registrarse como Estudiante"  
**EX1.2:** El sistema redirige a CU-2: Registrarse como Estudiante  

#### EX2: Ir a Registro de Docente
**EX2.1:** En FP2, el usuario puede hacer clic en "Registrarse como Docente"  
**EX2.2:** El sistema redirige a CU-3: Registrarse como Docente

### Inclusiones
Ninguna

### Flujos Alternativos

#### FA1: El sistema no está disponible
El sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y el flujo termina.

### Postcondiciones
- Usuario autenticado en el sistema
- Usuario redirigido a su dashboard correspondiente según su rol

<img width="495" height="285" alt="Image" src="https://github.com/user-attachments/assets/2a291cf8-bd99-42ac-a0ac-00c8aecfd85f" />

---

## CU-2: REGISTRARSE COMO ESTUDIANTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-2

### Descripción
Un nuevo estudiante crea su cuenta en el sistema proporcionando su información personal y eligiendo una contraseña.

### Actores
- Estudiante (nuevo usuario)

### Precondiciones
- El estudiante no debe tener cuenta registrada
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El estudiante accede a la página de registro desde el login

**FP2:** El sistema muestra el formulario de registro con campos:
- Email (obligatorio, único)
- Contraseña (obligatorio, mínimo 6 caracteres)
- Confirmar Contraseña (obligatorio)
- Nombres (obligatorio)
- Apellidos (obligatorio)
- Carrera (selector obligatorio)
- Teléfono (opcional)
- Dirección (opcional)
- Botón "Registrarse"
- Enlace "Ya tengo cuenta"

**FP3:** El estudiante ingresa su email

**FP4:** El sistema valida que el email no esté registrado

**FP5:** El estudiante ingresa una contraseña

**FP6:** El sistema valida que la contraseña tenga mínimo 6 caracteres

**FP7:** El estudiante confirma la contraseña

**FP8:** El sistema valida que ambas contraseñas coincidan

**FP9:** El estudiante ingresa sus nombres

**FP10:** El estudiante ingresa sus apellidos

**FP11:** El estudiante selecciona su carrera del selector

**FP12:** El estudiante puede ingresar teléfono y dirección (opcional)

**FP13:** El estudiante hace clic en "Registrarse"

**FP14:** El sistema valida todos los campos obligatorios

**FP15:** El sistema valida el formato del email

**FP16:** El sistema genera el código de estudiante 

**FP18:** El sistema crea el usuario con rol ESTUDIANTE

**FP19:** El sistema guarda el registro en la base de datos

**FP20:** El sistema envía email de bienvenida con el código generado

**FP21:** El sistema muestra mensaje: "Registro exitoso. Tu código es: código. Puedes iniciar sesión"

**FP22:** El sistema redirige automáticamente al login después de 3 segundos

### Subflujos

#### SF1: Email ya registrado (Paso 4 → Paso 3)
**SF1.1:** En FP4, el sistema detecta que el email ya existe  
**SF1.2:** El sistema muestra mensaje: "Este email ya está registrado"  
**SF1.3:** El sistema marca el campo en rojo  
**SF1.4:** El flujo retorna a FP3  

#### SF2: Contraseña muy corta (Paso 6 → Paso 5)
**SF2.1:** En FP6, el sistema detecta contraseña menor a 6 caracteres  
**SF2.2:** El sistema muestra mensaje: "La contraseña debe tener al menos 6 caracteres"  
**SF2.3:** El sistema marca el campo en rojo  
**SF2.4:** El flujo retorna a FP5  

#### SF3: Contraseñas no coinciden (Paso 8 → Paso 7)
**SF3.1:** En FP8, las contraseñas no coinciden  
**SF3.2:** El sistema muestra mensaje: "Las contraseñas no coinciden"  
**SF3.3:** El sistema marca ambos campos en rojo  
**SF3.4:** El flujo retorna a FP7  

#### SF4: Campos obligatorios vacíos (Paso 14 → Paso 3)
**SF4.1:** En FP14, el sistema detecta campos vacíos  
**SF4.2:** El sistema marca todos los campos faltantes en rojo  
**SF4.3:** El sistema muestra mensaje: "Complete todos los campos obligatorios"  
**SF4.4:** El flujo retorna a FP3  

#### SF5: Formato de email inválido (Paso 15 → Paso 3)
**SF5.1:** En FP15, el sistema detecta formato incorrecto  
**SF5.2:** El sistema muestra mensaje: "Ingrese un email válido"  
**SF5.3:** El sistema marca el campo en rojo  
**SF5.4:** El flujo retorna a FP3  

#### SF6: Error al enviar email (Paso 20 → Paso 21)
**SF6.1:** En FP20, el servicio de email falla  
**SF6.2:** El sistema registra al usuario de todos modos  
**SF6.3:** El sistema muestra el código en pantalla  
**SF6.4:** El sistema muestra advertencia: "Registro exitoso pero no se pudo enviar el email"  
**SF6.5:** El flujo continúa a FP21  

### Extensiones
Ninguna

### Inclusiones
Ninguna

### Flujos Alternativos

#### FA1: El sistema no está disponible
Sistema no inicializado o detenido, el flujo termina.

#### FA2: No hay carreras disponibles
En FP2, el sistema detecta que no hay carreras registradas, el flujo no puede continuar.

### Postcondiciones
- Nuevo estudiante registrado en el sistema con rol ESTUDIANTE
- Usuario creado
- Usuario puede iniciar sesión inmediatamente

<img width="408" height="178" alt="Image" src="https://github.com/user-attachments/assets/e60de8ea-dbfd-4ba2-8785-2b0eb279cb6e" />

---

## CU-3: REGISTRARSE COMO DOCENTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-3

### Descripción
Un nuevo docente crea su cuenta en el sistema proporcionando su información personal, especialidad y eligiendo una contraseña.

### Actores
- Docente (nuevo usuario)

### Precondiciones
- El docente no debe tener cuenta registrada
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El docente accede a la página de registro desde el login

**FP2:** El sistema muestra el formulario de registro con campos:
- Email (obligatorio, único)
- Contraseña (obligatorio, mínimo 6 caracteres)
- Confirmar Contraseña (obligatorio)
- Nombres (obligatorio)
- Apellidos (obligatorio)
- Especialidad (obligatorio, texto libre)
- Departamento (selector obligatorio: Matemáticas, Física, Computación, Electrónica, etc.)
- Grado Académico (selector: Licenciatura, Maestría, Doctorado)
- Teléfono (opcional)
- Oficina (opcional)
- Botón "Registrarse"
- Enlace "Ya tengo cuenta"

**FP3:** El docente ingresa su email

**FP4:** El sistema valida que el email no esté registrado

**FP5:** El docente ingresa una contraseña

**FP6:** El sistema valida que la contraseña tenga mínimo 6 caracteres

**FP7:** El docente confirma la contraseña

**FP8:** El sistema valida que ambas contraseñas coincidan

**FP9:** El docente ingresa sus nombres

**FP10:** El docente ingresa sus apellidos

**FP11:** El docente ingresa su especialidad (ejemplo: "Inteligencia Artificial", "Bases de Datos")

**FP12:** El docente selecciona su departamento académico

**FP13:** El docente selecciona su grado académico

**FP14:** El docente puede ingresar teléfono y oficina (opcional)

**FP15:** El docente hace clic en "Registrarse"

**FP16:** El sistema valida todos los campos obligatorios

**FP17:** El sistema valida el formato del email

**FP18:** El sistema genera el código de docente (formato: DOC-YYYY-NNNN)

**FP20:** El sistema crea el usuario con rol DOCENTE

**FP21:** El sistema guarda el registro en la base de datos

**FP22:** El sistema envía email de bienvenida con el código generado

**FP23:** El sistema muestra mensaje: "Registro exitoso. Tu código es: código. Puedes iniciar sesión"

**FP24:** El sistema redirige automáticamente al login después de 3 segundos

### Subflujos

#### SF1: Email ya registrado (Paso 4 → Paso 3)
**SF1.1:** En FP4, el sistema detecta que el email ya existe  
**SF1.2:** El sistema muestra mensaje: "Este email ya está registrado"  
**SF1.3:** El sistema marca el campo en rojo  
**SF1.4:** El flujo retorna a FP3  

#### SF2: Contraseña muy corta (Paso 6 → Paso 5)
**SF2.1:** En FP6, el sistema detecta contraseña menor a 6 caracteres  
**SF2.2:** El sistema muestra mensaje: "La contraseña debe tener al menos 6 caracteres"  
**SF2.3:** El sistema marca el campo en rojo  
**SF2.4:** El flujo retorna a FP5  

#### SF3: Contraseñas no coinciden (Paso 8 → Paso 7)
**SF3.1:** En FP8, las contraseñas no coinciden  
**SF3.2:** El sistema muestra mensaje: "Las contraseñas no coinciden"  
**SF3.3:** El sistema marca ambos campos en rojo  
**SF3.4:** El flujo retorna a FP7  

#### SF4: Campos obligatorios vacíos (Paso 16 → Paso 3)
**SF4.1:** En FP16, el sistema detecta campos vacíos  
**SF4.2:** El sistema marca todos los campos faltantes en rojo  
**SF4.3:** El sistema muestra mensaje: "Complete todos los campos obligatorios"  
**SF4.4:** El flujo retorna a FP3  

#### SF5: Formato de email inválido (Paso 17 → Paso 3)
**SF5.1:** En FP17, el sistema detecta formato incorrecto  
**SF5.2:** El sistema muestra mensaje: "Ingrese un email válido"  
**SF5.3:** El sistema marca el campo en rojo  
**SF5.4:** El flujo retorna a FP3  

### Extensiones
Ninguna 

### Inclusiones
Ninguna

### Flujos Alternativos

#### FA1: El sistema no está disponible
El sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y el flujo termina.

### Postcondiciones
- Nuevo docente registrado en el sistema con rol DOCENTE
- Usuario creado
- Usuario puede iniciar sesión inmediatamente

<img width="422" height="219" alt="Image" src="https://github.com/user-attachments/assets/860709b2-eb47-47b7-866d-a6089e95ecad" />

---

## CU-4: EDITAR INFORMACIÓN PERSONAL

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-4

### Descripción
El usuario (Estudiante, Docente o Director) actualiza su información personal como nombres, apellidos, teléfono y dirección.

### Actores
- Estudiante
- Docente
- Director de Carrera

### Precondiciones
- El usuario debe haber iniciado sesión con credenciales válidas
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El usuario hace clic en su nombre/avatar en la esquina superior derecha

**FP2:** El sistema muestra menú desplegable con opciones:
- "Mi Perfil"
- "Cerrar Sesión"

**FP3:** El usuario selecciona "Mi Perfil"

**FP4:** El sistema muestra la página de perfil con:
- Sección "Información Personal" con:
  * Código (no editable, solo lectura)
  * Email (no editable, solo lectura)
  * Rol (no editable, solo lectura)
  * Nombres (editable)
  * Apellidos (editable)
  * Teléfono (editable)
  * Dirección (editable)
- Botón "Guardar Cambios"
- Botón "Cancelar"
- Sección adicional según rol:
  * **Estudiante:** Carrera, Semestre, Estado
  * **Docente:** Departamento, Especialidad, Grado Académico
  * **Director:** Carrera Dirigida

**FP5:** El usuario visualiza su información actual

**FP6:** El usuario modifica sus nombres

**FP7:** El usuario modifica sus apellidos

**FP8:** El usuario modifica su teléfono

**FP9:** El usuario modifica su dirección

**FP10:** El usuario hace clic en "Guardar Cambios"

**FP11:** El sistema valida que los campos obligatorios no estén vacíos (Nombres, Apellidos)

**FP12:** El sistema valida el formato del teléfono (si se ingresó)

**FP13:** El sistema actualiza la información en la base de datos

**FP14:** El sistema muestra mensaje: "Información actualizada correctamente"

**FP15:** El sistema recarga la página mostrando los nuevos datos

### Subflujos

#### SF1: Nombres vacío (Paso 11 → Paso 6)
**SF1.1:** En FP11, el sistema detecta que Nombres está vacío  
**SF1.2:** El sistema muestra mensaje: "El nombre es obligatorio"  
**SF1.3:** El sistema marca el campo en rojo  
**SF1.4:** El flujo retorna a FP6  

#### SF2: Apellidos vacío (Paso 11 → Paso 7)
**SF2.1:** En FP11, el sistema detecta que Apellidos está vacío  
**SF2.2:** El sistema muestra mensaje: "Los apellidos son obligatorios"  
**SF2.3:** El sistema marca el campo en rojo  
**SF2.4:** El flujo retorna a FP7  

#### SF3: Formato de teléfono inválido (Paso 12 → Paso 8)
**SF3.1:** En FP12, el sistema detecta formato incorrecto (debe ser numérico)  
**SF3.2:** El sistema muestra mensaje: "Ingrese un número de teléfono válido"  
**SF3.3:** El sistema marca el campo en rojo  
**SF3.4:** El flujo retorna a FP8  

### Extensiones
Ninguna

### Inclusiones

#### IN1: Ver Perfil 
Para editar la información personal, el sistema debe primero cargar y mostrar el perfil actual del usuario (FP1-FP5). No se puede editar sin visualizar primero.

### Flujos Alternativos

#### FA1: El sistema no está disponible
Al intentar guardar, si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y no se guardan los cambios.

#### FA2: Error al guardar en base de datos
En FP13, si ocurre un error de base de datos, el sistema mantiene los datos anteriores y el flujo termina.

### Postcondiciones
- Información personal del usuario actualizada en la base de datos
- Cambios reflejados inmediatamente en todas las vistas del sistema
- El nombre actualizado aparece en el header/menú del sistema

<img width="494" height="281" alt="Image" src="https://github.com/user-attachments/assets/fda35b87-4827-4daa-a08d-de66f782bc13" />

---



# 2. OFERTA ACADÉMICA Y MATRÍCULA - ESTUDIANTE

---

## CU-5: CONSULTAR OFERTA ACADÉMICA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-5

### Descripción
El estudiante visualiza todas las materias disponibles para inscripción en la gestión académica actual, filtrando por carrera y explorando detalles de cada materia.

### Actores
- Estudiante

### Precondiciones
- El estudiante debe haber iniciado sesión con credenciales válidas
- El estudiante debe tener una sesión activa
- Debe existir un período académico activo (estado MATRICULA)
- Debe haber grupos creados para la gestión actual
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El estudiante accede a "Oferta Académica" desde el menú principal

**FP2:** El sistema detecta la carrera del estudiante

**FP3:** El sistema obtiene el período académico activo

**FP4:** El sistema obtiene todos los grupos disponibles para la carrera y gestión

**FP5:** El sistema muestra la página de oferta académica con:
- Título: "Oferta Académica - Carrera - Gestión"
- Filtros: Por nivel/semestre recomendado, por docente, por horario
- Campo de búsqueda por nombre de materia
- Lista de materias agrupadas por nivel
- Por cada materia:
  * Código y nombre de la materia
  * Créditos
  * Nivel/Semestre recomendado
  * Prerrequisitos (si tiene)
  * Paralelos disponibles (A, B, C...)
  * Por cada paralelo:
    - Docente asignado
    - Horarios (días y horas)
    - Aula
    - Cupo total y disponible (Ej: 25/30)
    - Estado (ABIERTO/LLENO)
- Botón "Inscribirme" por cada paralelo disponible

**FP6:** El estudiante visualiza la oferta completa

**FP7:** El estudiante explora las materias y paralelos

### Subflujos

#### SF1: Filtrar por Nivel (Paso 7 → Paso 6)
**SF1.1:** El estudiante selecciona un nivel del filtro (1ro, 2do, 3ro, etc.)  
**SF1.2:** El sistema filtra mostrando solo materias de ese nivel  
**SF1.3:** El estudiante visualiza las materias filtradas  
**SF1.4:** El flujo retorna a FP6 con datos filtrados

#### SF2: Buscar Materia (Paso 7 → Paso 6)
**SF2.1:** El estudiante escribe el nombre de una materia en el buscador  
**SF2.2:** El sistema filtra en tiempo real  
**SF2.3:** El sistema muestra solo las materias que coincidan  
**SF2.4:** Si no hay resultados, muestra mensaje: "No se encontraron materias"  
**SF2.5:** El flujo retorna a FP6 con resultados de búsqueda

#### SF3: Ver Detalle de Materia (Paso 7 → Paso 7)
**SF3.1:** El estudiante hace clic en el nombre de una materia  
**SF3.2:** El sistema muestra modal con información completa:
- Código y nombre
- Descripción
- Créditos
- Nivel recomendado
- Prerrequisitos con estado (Cumplido / No Cumplido)
- Contenido programático (objetivos)
- Todos los paralelos disponibles  
**SF3.3:** El estudiante revisa la información  
**SF3.4:** El estudiante cierra el modal  
**SF3.5:** El flujo retorna a FP7

#### SF4: Ver Horario de Paralelo (Paso 7 → Paso 7)
**SF4.1:** El estudiante hace clic en "Ver Horario" de un paralelo  
**SF4.2:** El sistema muestra calendario semanal con los bloques de clase  
**SF4.3:** El estudiante visualiza los horarios  
**SF4.4:** El estudiante cierra el calendario  
**SF4.5:** El flujo retorna a FP7

### Extensiones

#### EX1: Inscribirse en Materia
**EX1.1:** El estudiante puede hacer clic en "Inscribirme" de cualquier paralelo disponible  
**EX1.2:** El sistema redirige a CU-6: Inscribirse en Materia

#### EX2: Ver Mi Matrícula
**EX2.1:** El estudiante puede hacer clic en "Ver Mi Matrícula" del menú  
**EX2.2:** El sistema redirige a CU-8: Ver Mi Matrícula  

### Inclusiones

#### IN1: Obtener período académico
El sistema debe consultar y validar que existe un período académico con estado MATRICULA antes de mostrar la oferta.

#### IN2: Obtener grupos de la carrera del estudiante 
El sistema debe cargar todos los grupos disponibles para la carrera del estudiante en la gestión actual.

### Flujos Alternativos

#### FA1: El sistema no está disponible
Si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y el flujo termina.

#### FA2: No hay período activo
En FP3, el sistema no encuentra período con estado MATRICULA el flujo termina.

#### FA3: No hay oferta disponible para la carrera
En FP4, el sistema no encuentra grupos para la carrera del estudiante, muestra mensaje: "No hay materias disponibles para tu carrera en esta gestión. Contacta al director de carrera" y el flujo termina.

### Postcondiciones
- Estudiante visualiza oferta académica completa de su carrera
- Estudiante conoce horarios, docentes, aulas y disponibilidad de cupos
- Estudiante puede identificar materias con prerrequisitos cumplidos o no cumplidos

<img width="497" height="340" alt="Image" src="https://github.com/user-attachments/assets/40acc22a-b8de-4f0a-9cbb-01510642b97d" />

---

## CU-6: INSCRIBIRSE EN MATERIA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-6

### Descripción
El estudiante selecciona un paralelo específico de una materia y solicita su inscripción, la cual queda en estado PENDIENTE hasta ser revisada por el Director.

### Actores
- Estudiante

### Precondiciones
- El estudiante debe haber iniciado sesión con credenciales válidas
- El estudiante debe tener una sesión activa
- Debe estar en período de matrícula activo (estado MATRICULA)
- El paralelo debe tener cupos disponibles
- El estudiante no debe estar ya inscrito en otro paralelo de la misma materia
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El estudiante visualiza la oferta académica (desde CU-5)

**FP2:** El estudiante identifica una materia de su interés

**FP3:** El estudiante identifica un paralelo disponible (no lleno, horario compatible)

**FP4:** El estudiante hace clic en "Inscribirme" del paralelo seleccionado

**FP5:** El sistema muestra modal de confirmación con:
- Materia: Código - Nombre
- Paralelo: Letra
- Docente: Nombre
- Horarios: Lista de días y horas
- Aula: Código
- Créditos: Número
- Advertencia si hay conflicto de horario con otras inscripciones
- Botón "Confirmar Inscripción"
- Botón "Cancelar"

**FP6:** El estudiante revisa la información

**FP7:** El estudiante hace clic en "Confirmar Inscripción"

**FP8:** El sistema valida que el paralelo aún tenga cupos disponibles

**FP9:** El sistema valida que el estudiante no esté inscrito en otro paralelo de la misma materia

**FP10:** El sistema detecta conflictos de horario con otras inscripciones pendientes/aceptadas

**FP11:** El sistema crea el registro de matrícula con:
- Estudiante
- Grupo (paralelo)
- Estado: PENDIENTE
- Fecha de solicitud: fecha actual
- Gestión actual

**FP12:** El sistema guarda la inscripción en la base de datos

**FP13:** El sistema muestra mensaje: "Inscripción solicitada correctamente. Estado: PENDIENTE DE APROBACIÓN"

**FP14:** El sistema actualiza el contador de inscripciones pendientes del Director

**FP15:** El sistema cierra el modal

**FP16:** El sistema actualiza la vista mostrando el paralelo con badge "INSCRITO - PENDIENTE"

### Subflujos

#### SF1: Conflicto de horario (Paso 10 → Paso 7)
**SF1.1:** En FP10, el sistema detecta solapamiento de horarios con otras inscripciones  
**SF1.2:** El sistema muestra advertencia en el modal: "Conflicto de horario con Materia - Día Hora"  
**SF1.3:** El estudiante decide si continuar o cancelar  
**SF1.4:** Si elige continuar: La inscripción se crea con marca de conflicto y continúa a FP11  
**SF1.5:** Si elige cancelar: El sistema cierra el modal y retorna a FP3 en la oferta académica

#### SF2: No cumple prerrequisitos (Paso 10 → Paso 7)
**SF2.1:** El sistema detecta que el estudiante no aprobó los prerrequisitos requeridos  
**SF2.2:** El sistema muestra advertencia: "No cumples los prerrequisitos: Lista"  
**SF2.3:** El estudiante puede decidir continuar (inscripción condicional) o cancelar  
**SF2.4:** Si elige continuar: La inscripción se crea con marca "Sin Prerrequisitos" y continúa a FP11  
**SF2.5:** Si elige cancelar: El sistema cierra el modal y retorna a FP3

### Extensiones

#### EX1: Ver materia
**EX2.1:** Antes de FP4, el estudiante puede hacer clic en el nombre de la materia  
**EX2.2:** El sistema muestra el modal de detalles con información completa  
**EX2.3:** El estudiante revisa y cierra el modal  

### Inclusiones

#### IN1: Consultar Oferta Académica (Obligatorio)
Este caso de uso requiere que el estudiante primero visualice la oferta académica (CU-5) para poder seleccionar un paralelo.

#### IN2: Obtener cupos disponibles (Obligatorio)
El sistema debe verificar en tiempo real la disponibilidad de cupos del paralelo antes de permitir la inscripción.


### Flujos Alternativos

#### FA1: El sistema no está disponible
Al intentar guardar la inscripción, si el servidor no responde

#### FA2: Paralelo sin cupos
En FP8, el sistema detecta que el cupo se llenó mientras el estudiante confirmaba, muestra mensaje: "Este paralelo ya no tiene cupos disponibles", recarga la oferta académica y el flujo termina.

#### FA3: Ya inscrito en otro paralelo de la misma materia
En FP9, el sistema detecta inscripción duplicada, muestra mensaje: "Ya estás inscrito en el paralelo X de esta materia" y el flujo termina.

#### FA4: Período de matrícula cerrado
Durante el proceso, si el período cambia de estado, el sistema muestra mensaje: "El período de matrícula ha cerrado", bloquea el formulario y el flujo termina.

### Postcondiciones
- Registro de matrícula creado en la base de datos con estado PENDIENTE
- Inscripción asociada al estudiante, grupo (paralelo) y gestión actual

### Postcondiciones

**Éxito:**
- Inscripción creada con estado PENDIENTE
- Registro visible en "Mi Matrícula" del estudiante

**Fallo:**
- No se crea la inscripción
- Cupos sin cambios

<img width="499" height="253" alt="Image" src="https://github.com/user-attachments/assets/b90c2593-3597-4e26-aaf9-3aacbfd7279d" />

---

## CU-7: CANCELAR INSCRIPCIÓN DE MATERIA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-7

### Descripción
El estudiante cancela una inscripción que realizó previamente, ya sea en estado PENDIENTE o ACEPTADA, liberando el cupo del paralelo.

### Actores
- Estudiante

### Precondiciones
- El estudiante debe haber iniciado sesión con credenciales válidas
- El estudiante debe tener una sesión activa
- El estudiante debe tener al menos una inscripción registrada
- El período debe permitir modificaciones de matrícula (estado MATRICULA)
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El estudiante accede a "Mi Matrícula" desde el menú principal

**FP2:** El sistema muestra la lista de materias inscritas con:
- Código y nombre de materia
- Paralelo
- Docente
- Horarios
- Aula
- Estado: PENDIENTE (amarillo) / ACEPTADA (verde) / RECHAZADA (rojo)
- Botón "Cancelar Inscripción" (solo en PENDIENTE y ACEPTADA)

**FP3:** El estudiante identifica la materia que desea cancelar

**FP4:** El estudiante hace clic en "Cancelar Inscripción"

**FP5:** El sistema muestra modal de confirmación:
- "¿Estás seguro de cancelar esta inscripción?"
- Materia: Código - Nombre
- Paralelo: Letra
- Advertencia: "Esta acción no se puede deshacer"
- Botón "Sí, Cancelar"
- Botón "No, Mantener"

**FP6:** El estudiante hace clic en "Sí, Cancelar"

**FP7:** El sistema valida que la inscripción aún existe y está en estado PENDIENTE o ACEPTADA

**FP8:** El sistema elimina el registro de inscripción de la base de datos

**FP9:** El sistema incrementa el cupo disponible del paralelo (+1)

**FP10:** El sistema muestra mensaje: "Inscripción cancelada exitosamente"

**FP11:** El sistema actualiza la vista "Mi Matrícula" removiendo la materia

**FP12:** El sistema actualiza el horario semanal (si está visible) eliminando los bloques

### Subflujos
Ninguno

### Extensiones
Ninguno

### Inclusiones

#### IN1: Ver Mi Matrícula (Obligatorio)
Para cancelar una inscripción, el estudiante debe primero acceder y visualizar su matrícula (CU-8) donde se listan todas las inscripciones.


### Flujos Alternativos

#### FA1: El sistema no está disponible
Al intentar cancelar, si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y no se elimina la inscripción.

#### FA3: Inscripción no encontrada
En FP7, el sistema no encuentra la inscripción en la base de datos, muestra mensaje: "Error: Inscripción no encontrada", recarga "Mi Matrícula" y el flujo termina.

#### FA6: Período cerrado para modificaciones
El sistema detecta que el período cambió a EN_CURSO o FINALIZADO, muestra mensaje: "No se pueden cancelar inscripciones fuera del período de matrícula", deshabilita todos los botones "Cancelar Inscripción" y el flujo termina.

### Postcondiciones
- Inscripción eliminada completamente de la base de datos
- Cupo del paralelo liberado (incrementado en 1)

**Fallo:**
- Inscripción sin cambios
- Cupo sin modificación
- Sistema mantiene estado anterior

<img width="497" height="169" alt="Image" src="https://github.com/user-attachments/assets/a738b34f-872c-4ade-964d-29563bd212f6" />

---

## CU-8: VER MI MATRÍCULA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-8

### Descripción
El estudiante visualiza todas sus inscripciones actuales, organizadas por estado, con resumen de créditos y vista previa del horario semanal.

### Actores
- Estudiante

### Precondiciones
- El estudiante debe haber iniciado sesión con credenciales válidas
- El estudiante debe tener una sesión activa
- Debe existir un período académico activo
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El estudiante accede a "Mi Matrícula" desde el menú principal

**FP2:** El sistema obtiene todas las inscripciones del estudiante en la gestión actual

**FP3:** El sistema calcula estadísticas:
- Total de materias inscritas
- Total de créditos
- Materias por estado (Pendientes, Aceptadas, Rechazadas)

**FP4:** El sistema muestra la página con:
- Título: "Mi Matrícula - Gestión Actual"
- Panel de resumen con:
  * Total de materias: N
  * Total de créditos: N
  * Pendientes de aprobación: N
  * Aceptadas: N
  * Rechazadas: N
- Tabs para filtrar por estado:
  * Todas (N)
  * Pendientes (N)
  * Aceptadas (N)
  * Rechazadas (N)
- Lista de materias con:
  * Badge de estado con color
  * Código y nombre de materia
  * Paralelo
  * Docente
  * Créditos
  * Horarios (días y horas)
  * Aula
  * Botón "Cancelar Inscripción" (solo PENDIENTE/ACEPTADA)
  * Motivo de rechazo (solo RECHAZADA)
- Vista previa de horario semanal con materias ACEPTADAS
- Botón "Inscribir Más Materias" (redirige a Oferta Académica)
- Botón "Exportar Mi Matrícula" (PDF)

**FP5:** El estudiante visualiza su matrícula completa

### Subflujos

#### SF1: Filtrar por Estado (Paso 5 → Paso 5)
**SF1.1:** El estudiante hace clic en un tab (Todas/Pendientes/Aceptadas/Rechazadas)  
**SF1.2:** El sistema filtra mostrando solo materias de ese estado  
**SF1.3:** El sistema actualiza el contador en el tab  
**SF1.4:** El flujo retorna a FP5 con datos filtrados

#### SF2: Ver Detalle de Materia (Paso 5 → Paso 5)
**SF2.1:** El estudiante hace clic en el nombre de una materia  
**SF2.2:** El sistema muestra modal con información completa de la materia  
**SF2.3:** El estudiante revisa el contenido del modal  
**SF2.4:** El estudiante cierra el modal  
**SF2.5:** El flujo retorna a FP5

#### SF3: Exportar Matrícula (Paso 5 → Paso 5)
**SF3.1:** El estudiante hace clic en "Exportar Mi Matrícula"  
**SF3.2:** El sistema genera PDF con logo, datos del estudiante, lista de materias, horarios, total de créditos y fecha  
**SF3.3:** El sistema descarga el archivo PDF  
**SF3.4:** El flujo retorna a FP5

### Extensiones
Ninguna

### Inclusiones

#### IN1: Obtener inscripciones del estudiante (Obligatorio)
El sistema debe consultar todas las inscripciones del estudiante en la gestión actual desde la base de datos.

### Flujos Alternativos

#### FA1: El sistema no está disponible
Si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y el flujo termina.

#### FA2: Sin inscripciones
En FP2, el sistema no encuentra inscripciones, muestra mensaje: "No tienes materias inscritas en esta gestión", muestra solo el botón "Inscribir Materias" y el flujo termina.

### Postcondiciones
- Estudiante visualiza el estado completo de su matrícula actual
- Estudiante conoce qué inscripciones están pendientes, aceptadas o rechazadas

<img width="498" height="209" alt="Image" src="https://github.com/user-attachments/assets/3a231833-167f-4bc1-81eb-677776488693" />

---

## CU-9: VER MIS CALIFICACIONES

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-9

### Descripción
El estudiante visualiza sus calificaciones de todas las materias inscritas en la gestión actual, organizadas por materia.

### Actores
- Estudiante

### Precondiciones
- El estudiante debe haber iniciado sesión con credenciales válidas
- El estudiante debe tener una sesión activa
- El estudiante debe tener inscripciones con estado ACEPTADA
- El período debe estar en estado EN_CURSO o FINALIZADO
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El estudiante accede a "Mis Calificaciones" desde el menú principal

**FP2:** El sistema obtiene todas las inscripciones ACEPTADAS del estudiante en la gestión actual

**FP3:** El sistema obtiene todas las calificaciones registradas para cada inscripción

**FP4:** El sistema calcula para cada materia:
- Promedio parcial (promedio de todas las calificaciones ingresadas)
- Nota final (si todas las evaluaciones están completas)
- Estado: Aprobado (≥51), Reprobado (<51), En Curso (sin nota final)

**FP5:** El sistema muestra la página con:
- Título: "Mis Calificaciones - Gestión Actual"
- Panel de resumen:
  * Promedio general actual: N/100
  * Materias aprobadas: N
  * Materias reprobadas: N
  * Materias en curso: N
- Lista de materias con:
  * Nombre de materia y código
  * Docente
  * Paralelo
  * Lista de evaluaciones con:
    - Tipo (Parcial, Práctica, Examen Final, etc.)
    - Nota (sobre 100)
    - Fecha de registro
  * Promedio de la materia
  * Estado con badge de color
- Selector de gestión (para ver calificaciones pasadas)
- Botón "Exportar Calificaciones" (PDF)

**FP6:** El estudiante visualiza todas sus calificaciones

### Subflujos

#### SF1: Ver Detalle de Materia (Paso 6 → Paso 6)
**SF1.1:** El estudiante hace clic en una materia para expandir  
**SF1.2:** El sistema muestra todas las evaluaciones registradas con fechas  
**SF1.3:** El sistema muestra gráfico de evolución si hay múltiples notas  
**SF1.4:** El estudiante visualiza el detalle completo  
**SF1.5:** El flujo retorna a FP6

#### SF2: Filtrar Materias (Paso 6 → Paso 6)
**SF2.1:** El estudiante usa filtros (Todas/Aprobadas/Reprobadas/En Curso)  
**SF2.2:** El sistema filtra la lista según el criterio seleccionado  
**SF2.3:** El estudiante visualiza solo las materias del filtro  
**SF2.4:** El flujo retorna a FP6 con datos filtrados

#### SF3: Ver Calificaciones de Gestión Anterior (Paso 6 → Paso 2)
**SF3.1:** El estudiante selecciona una gestión anterior del selector  
**SF3.2:** El sistema carga las inscripciones y calificaciones de esa gestión  
**SF3.3:** El sistema recalcula estadísticas para esa gestión  
**SF3.4:** El flujo retorna a FP2 con datos de la gestión seleccionada

#### SF4: Exportar Calificaciones (Paso 6 → Paso 6)
**SF4.1:** El estudiante hace clic en "Exportar Calificaciones"  
**SF4.2:** El sistema genera PDF con logo, datos del estudiante, tabla con todas las materias y notas, promedio general, y nota "Documento informativo sin validez oficial"  
**SF4.3:** El sistema descarga el archivo PDF  
**SF4.4:** El flujo retorna a FP6

### Extensiones
Ninguna

### Inclusiones

#### IN1: Obtener inscripciones (Obligatorio)
El sistema debe consultar todas las inscripciones con estado ACEPTADA del estudiante en la gestión actual.

#### IN2: Obtener calificaciones (Obligatorio)
El sistema debe consultar todas las calificaciones registradas por los docentes para cada inscripción.

### Flujos Alternativos

#### FA1: El sistema no está disponible
Si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y el flujo termina.

#### FA4: Sin inscripciones aceptadas
En FP2, el sistema no encuentra inscripciones ACEPTADAS, muestra mensaje: "No tienes materias aceptadas en esta gestión", sugiere ir a "Mi Matrícula" y el flujo termina.

#### FA5: Período en matrícula (clases no iniciadas)
El sistema detecta que el período está en estado MATRICULA, muestra mensaje informativo: "Las clases aún no han iniciado. Las calificaciones se mostrarán cuando inicien las clases", muestra las materias sin calificaciones.

### Postcondiciones
- Estudiante visualiza todas sus calificaciones actuales organizadas por materia
- Estudiante conoce su promedio general de la gestión actual

<img width="499" height="190" alt="Image" src="https://github.com/user-attachments/assets/ef003612-5579-43b4-8419-6508206c2816" />

---

## CU-10: VER MI HORARIO PERSONAL

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-10

### Descripción
El estudiante visualiza su horario semanal personalizado en formato de calendario con todas sus materias inscritas y aceptadas.

### Actores
- Estudiante

### Precondiciones
- El estudiante debe haber iniciado sesión con credenciales válidas
- El estudiante debe tener una sesión activa
- El estudiante debe tener al menos una inscripción con estado ACEPTADA
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El estudiante accede a "Mi Horario" desde el menú principal

**FP2:** El sistema obtiene todas las inscripciones ACEPTADAS del estudiante en la gestión actual

**FP3:** El sistema obtiene los horarios de cada grupo inscrito

**FP4:** El sistema organiza los horarios en una matriz semanal (Lunes a Sábado, 7:00 - 21:00)

**FP5:** El sistema detecta conflictos de horario (si existen)

**FP6:** El sistema muestra el calendario semanal con:
- Vista de cuadrícula por día y hora
- Franjas horarias cada 2 horas (8-10, 10-12, 12-14, 14-16, 16-18, 18-20)
- Bloques de clase con:
  * Nombre de materia
  * Código
  * Aula
  * Color diferente por materia
- Panel lateral con leyenda de colores
- Resumen:
  * Total de horas semanales
  * Clases por día
  * Bloques libres
- Selector de vista: Semanal / Diaria
- Botones de exportación (PDF, PNG, iCalendar)

**FP7:** El estudiante visualiza su horario completo

### Subflujos

#### SF1: Ver Detalle de Clase (Paso 7 → Paso 7)
**SF1.1:** El estudiante hace clic en un bloque del horario  
**SF1.2:** El sistema muestra modal con materia, código, docente, paralelo, aula completa, horario exacto y cantidad de estudiantes inscritos  
**SF1.3:** El estudiante revisa la información  
**SF1.4:** El estudiante cierra el modal  
**SF1.5:** El flujo retorna a FP7

#### SF2: Cambiar a Vista Diaria (Paso 7 → Paso 7)
**SF2.1:** El estudiante hace clic en "Vista Diaria"  
**SF2.2:** El sistema muestra solo un día completo con botones de navegación (◀ ▶)  
**SF2.3:** El estudiante navega entre días de la semana  
**SF2.4:** El flujo retorna a FP7 en vista diaria

#### SF3: Volver a Vista Semanal (Paso 7 → Paso 7)
**SF3.1:** Desde vista diaria, el estudiante hace clic en "Vista Semanal"  
**SF3.2:** El sistema muestra toda la semana en cuadrícula  
**SF3.3:** El flujo retorna a FP7

#### SF4: Exportar a PDF (Paso 7 → Paso 7)
**SF4.1:** El estudiante hace clic en "Exportar PDF"  
**SF4.2:** El sistema genera PDF formato A4 con el calendario completo, datos del estudiante y código QR  
**SF4.3:** El sistema descarga el archivo  
**SF4.4:** El flujo retorna a FP7

#### SF5: Exportar a iCalendar (Paso 7 → Paso 7)
**SF5.1:** El estudiante hace clic en "Exportar iCalendar"  
**SF5.2:** El sistema genera archivo .ics con todos los eventos recurrentes de la gestión  
**SF5.3:** El sistema descarga el archivo .ics  
**SF5.4:** El estudiante puede importarlo en Google Calendar/Outlook/Apple Calendar  
**SF5.5:** El flujo retorna a FP7

### Extensiones

#### EX1: Imprimir Horario
**EX1.1:** El estudiante puede hacer clic en "Imprimir"  
**EX1.2:** El sistema abre vista de impresión optimizada sin botones ni menús  
**EX1.3:** El estudiante imprime desde el navegador  

### Inclusiones

#### IN1: Obtener inscripciones aceptadas (Obligatorio)
El sistema debe consultar todas las inscripciones con estado ACEPTADA del estudiante en la gestión actual.

### Flujos Alternativos

#### FA3: El sistema no está disponible
Si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y el flujo termina.

#### FA4: Sin inscripciones aceptadas
En FP2, el sistema no encuentra inscripciones ACEPTADAS, muestra mensaje: "No tienes materias aceptadas. Tu horario está vacío", muestra calendario vacío, sugiere ir a "Mi Matrícula" y el flujo termina.

### Postcondiciones
- Estudiante visualiza su horario semanal completo en formato de calendario

<img width="494" height="207" alt="Image" src="https://github.com/user-attachments/assets/66bd2b9d-a483-4c38-8da6-e3c164e84cc8" />

---

# 3. GESTIÓN DE CALIFICACIONES - DOCENTE

---

## CU-11: REGISTRAR CALIFICACIÓN DE ESTUDIANTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-11

### Descripción
El docente registra una nueva calificación para un estudiante en un tipo de evaluación específico (parcial, práctica, examen final, etc.) de un grupo que tiene asignado.

### Actores
- Docente

### Precondiciones
- El docente debe haber iniciado sesión con credenciales válidas
- El docente debe tener una sesión activa
- El docente debe tener grupos asignados en la gestión actual
- El período debe estar en estado EN_CURSO
- El estudiante debe estar inscrito en el grupo con estado ACEPTADA
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El docente accede a "Mis Grupos" desde el menú principal

**FP2:** El sistema muestra todos los grupos asignados al docente en la gestión actual

**FP3:** El docente selecciona un grupo específico

**FP4:** El sistema muestra la lista de estudiantes inscritos en ese grupo con estado ACEPTADA

**FP5:** El docente hace clic en "Registrar Calificación" de un estudiante

**FP6:** El sistema muestra formulario modal con:
- Estudiante: Código - Nombre Completo
- Materia: Código - Nombre
- Grupo: Paralelo
- Selector de tipo de evaluación:
  * Primer Parcial
  * Segundo Parcial
  * Examen Final
  * Práctica 1, 2, 3...
  * Trabajo Final
  * Otro (campo de texto libre)
- Campo de nota (número de 0 a 100, obligatorio)
- Campo de observaciones (opcional)
- Botón "Guardar Calificación"
- Botón "Cancelar"

**FP7:** El docente selecciona el tipo de evaluación

**FP8:** El docente ingresa la nota (número entre 0 y 100)

**FP9:** El docente puede agregar observaciones (opcional)

**FP10:** El docente hace clic en "Guardar Calificación"

**FP11:** El sistema valida que la nota esté entre 0 y 100

**FP12:** El sistema valida que el tipo de evaluación no esté vacío

**FP13:** El sistema crea el registro de calificación con:
- Inscripción (estudiante + grupo)
- Tipo de evaluación
- Nota
- Observaciones
- Fecha de registro: fecha actual
- Docente que registró

**FP14:** El sistema guarda la calificación en la base de datos

**FP15:** El sistema muestra mensaje: "Calificación registrada exitosamente"

**FP16:** El sistema cierra el modal

**FP17:** El sistema actualiza la vista mostrando la nueva calificación en la lista del estudiante

**FP18:** El sistema envía notificación al estudiante

### Subflujos

#### SF1: Nota fuera de rango (Paso 11 → Paso 8)
**SF1.1:** En FP11, el sistema detecta nota menor a 0 o mayor a 100  
**SF1.2:** El sistema muestra mensaje: "La nota debe estar entre 0 y 100"  
**SF1.3:** El sistema marca el campo en rojo  
**SF1.4:** El flujo retorna a FP8  

#### SF2: Nota no numérica (Paso 8 → Paso 8)
**SF2.1:** En FP8, el docente ingresa texto o caracteres no numéricos  
**SF2.2:** El sistema muestra mensaje: "Ingrese un número válido"  
**SF2.3:** El sistema marca el campo en rojo  
**SF2.4:** El flujo retorna a FP8  

#### SF3: Tipo de evaluación vacío (Paso 12 → Paso 7)
**SF3.1:** En FP12, el sistema detecta que el tipo está vacío  
**SF3.2:** El sistema muestra mensaje: "Seleccione el tipo de evaluación"  
**SF3.3:** El flujo retorna a FP7  

#### SF4: Calificación duplicada del mismo tipo (Paso 13 → Paso 10)
**SF4.1:** El sistema detecta que ya existe una calificación del mismo tipo para este estudiante  
**SF4.2:** El sistema muestra advertencia: "Ya existe una calificación de 'Tipo' para este estudiante"  
**SF4.3:** El sistema pregunta: "¿Desea crear otra calificación del mismo tipo?"  
**SF4.4:** Si el docente confirma "Sí": El flujo continúa a FP13  
**SF4.5:** Si el docente selecciona "No": El flujo retorna a FP7 para cambiar el tipo

### Extensiones
Ninguna

### Inclusiones

#### IN1: Obtener grupos asignados al docente (Obligatorio)
El sistema debe consultar todos los grupos en los que el docente está asignado en la gestión actual.

#### IN2: Obtener estudiantes inscrito (Obligatorio)
El sistema debe listar solo los estudiantes con inscripción ACEPTADA en el grupo seleccionado.

### Flujos Alternativos

#### FA1: El sistema no está disponible
Al intentar guardar, si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y no se crea la calificación.

#### FA2: Período no en curso
El sistema detecta que el período no está en estado EN_CURSO, muestra mensaje: "No se pueden registrar calificaciones fuera del período de clases", deshabilita el botón "Registrar Calificación" y el flujo termina.

#### FA3: Sin grupos asignados
En FP2, el docente no tiene grupos asignados en la gestión actual, el sistema muestra mensaje: "No tienes grupos asignados en esta gestión" y el flujo termina.

### Postcondiciones
- Nueva calificación registrada en la base de datos
- Calificación inmediatamente visible para el estudiante en su vista de calificaciones (CU-9)
- Promedio de la materia del estudiante recalculado automáticamente

<img width="500" height="221" alt="Image" src="https://github.com/user-attachments/assets/2b64426f-9095-4c2b-bd6f-cbd7cb3a6d69" />

---

## CU-12: EDITAR CALIFICACIÓN DE ESTUDIANTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-12

### Descripción
El docente modifica una calificación previamente registrada, cambiando la nota, el tipo de evaluación o las observaciones.

### Actores
- Docente

### Precondiciones
- El docente debe haber iniciado sesión con credenciales válidas
- El docente debe tener una sesión activa
- Debe existir al menos una calificación registrada por el docente
- El período debe estar en estado EN_CURSO
- El docente debe ser quien registró la calificación originalmente
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El docente accede a "Mis Grupos" desde el menú principal

**FP2:** El sistema muestra todos los grupos asignados al docente

**FP3:** El docente selecciona un grupo específico

**FP4:** El sistema muestra la lista de estudiantes con sus calificaciones

**FP5:** El docente hace clic en una calificación específica para editarla

**FP6:** El sistema muestra formulario modal con los datos actuales:
- Estudiante: Código - Nombre (no editable)
- Materia y Grupo (no editable)
- Tipo de evaluación: Valor actual (editable)
- Nota: Valor actual (editable)
- Observaciones: Valor actual (editable)
- Fecha de registro original (no editable)
- Botón "Guardar Cambios"
- Botón "Cancelar"

**FP7:** El docente modifica el tipo de evaluación (si desea)

**FP8:** El docente modifica la nota

**FP9:** El docente modifica las observaciones (si desea)

**FP10:** El docente hace clic en "Guardar Cambios"

**FP11:** El sistema valida que la nota esté entre 0 y 100

**FP12:** El sistema valida que el tipo de evaluación no esté vacío

**FP13:** El sistema actualiza el registro de calificación en la base de datos

**FP14:** El sistema registra el cambio con:
- Fecha de modificación: fecha actual
- Nota anterior
- Nota nueva

**FP15:** El sistema muestra mensaje: "Calificación actualizada exitosamente"

**FP16:** El sistema cierra el modal

**FP17:** El sistema actualiza la vista mostrando la calificación modificada

**FP18:** El sistema envía notificación al estudiante informando el cambio

### Subflujos

#### SF1: Nota fuera de rango (Paso 11 → Paso 8)
**SF1.1:** En FP11, el sistema detecta nota menor a 0 o mayor a 100  
**SF1.2:** El sistema muestra mensaje: "La nota debe estar entre 0 y 100"  
**SF1.3:** El sistema marca el campo en rojo  
**SF1.4:** El flujo retorna a FP8  

#### SF2: Sin cambios realizados (Paso 10 → Modal se cierra)
**SF2.1:** En FP10, el sistema detecta que no hubo modificaciones  
**SF2.2:** El sistema muestra mensaje: "No se realizaron cambios"  
**SF2.3:** El sistema cierra el modal sin actualizar la base de datos  
**SF2.4:** El flujo termina  

### Extensiones
Ninguna

### Inclusiones

#### IN3: Obtener calificacion del estudiante (Obligatorio)
El sistema debe ver el promedio de la materia del estudiante al modificar la calificación.

### Flujos Alternativos

#### FA3: El sistema no está disponible
Al intentar guardar, si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y no se actualiza la calificación.

#### FA4: Calificación no encontrada
En FP6, el sistema no encuentra la calificación en la base de datos, muestra mensaje: "Error: Calificación no encontrada", recarga la lista y el flujo termina.

#### FA5: Período no en curso
El sistema detecta que el período no está en estado EN_CURSO, muestra mensaje: "No se pueden editar calificaciones fuera del período de clases" y el flujo termina.

### Postcondiciones
- Calificación actualizada en la base de datos con los nuevos valores
- Cambio visible inmediatamente para el estudiante en CU-9
- Promedio de la materia del estudiante recalculado automáticamente

<img width="500" height="198" alt="Image" src="https://github.com/user-attachments/assets/13c756ff-7eae-4455-853d-e7db2e9d298c" />

---

## CU-13: VER HORARIO PERSONAL DOCENTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-13

### Descripción
El docente visualiza su horario semanal personalizado con todos los grupos que tiene asignados en la gestión actual.

### Actores
- Docente

### Precondiciones
- El docente debe haber iniciado sesión con credenciales válidas
- El docente debe tener una sesión activa
- El docente debe tener al menos un grupo asignado en la gestión actual
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El docente accede a "Mi Horario" desde el menú principal

**FP2:** El sistema obtiene todos los grupos asignados al docente en la gestión actual

**FP3:** El sistema obtiene los horarios de cada grupo

**FP4:** El sistema organiza los horarios en una matriz semanal (Lunes a Sábado, 7:00 - 21:00)

**FP5:** El sistema calcula:
- Total de horas semanales
- Clases por día
- Carga horaria total

**FP6:** El sistema muestra el calendario semanal con:
- Vista de cuadrícula por día y hora
- Franjas horarias cada 2 horas
- Bloques de clase con:
  * Materia y código
  * Paralelo
  * Cantidad de estudiantes inscritos
  * Aula
  * Color diferente por materia
- Panel de resumen:
  * Carga horaria semanal: N horas
  * Total de grupos: N
  * Total de estudiantes: N
  * Clases por día
- Selector de vista: Semanal / Diaria
- Botones de exportación (PDF, iCalendar)

**FP7:** El docente visualiza su horario completo

### Subflujos

#### SF1: Ver Detalle de Clase (Paso 7 → Paso 7)
**SF1.1:** El docente hace clic en un bloque del horario  
**SF1.2:** El sistema muestra modal con materia, código, paralelo, cantidad de estudiantes inscritos (con nombres), aula completa, horario exacto, botón "Ir a Calificaciones" y botón "Ver Lista de Estudiantes"  
**SF1.3:** El docente revisa la información  
**SF1.4:** El docente cierra el modal  
**SF1.5:** El flujo retorna a FP7

#### SF2: Cambiar a Vista Diaria (Paso 7 → Paso 7)
**SF2.1:** El docente hace clic en "Vista Diaria"  
**SF2.2:** El sistema muestra solo un día completo con botones de navegación  
**SF2.3:** El docente navega entre días de la semana  
**SF2.4:** El flujo retorna a FP7

#### SF3: Exportar a PDF (Paso 7 → Paso 7)
**SF3.1:** El docente hace clic en "Exportar PDF"  
**SF3.2:** El sistema genera PDF formato A4 con el calendario, datos del docente y estadísticas  
**SF3.3:** El sistema descarga el archivo  
**SF3.4:** El flujo retorna a FP7

#### SF4: Ir a Calificaciones desde Bloque (Paso 7 → CU-11)
**SF4.1:** Desde SF1, el docente hace clic en "Ir a Calificaciones"  
**SF4.2:** El sistema redirige a la página de calificaciones del grupo seleccionado  

### Extensiones
Ninguna

### Inclusiones
Ninguna

### Flujos Alternativos

#### FA1: El sistema no está disponible
Si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y el flujo termina.

#### FA2: Sin grupos asignados
En FP2, el sistema no encuentra grupos asignados al docente en la gestión actual, muestra mensaje: "No tienes grupos asignados en esta gestión. Contacta al Director de Carrera", muestra calendario vacío y el flujo termina.

### Postcondiciones
- Docente visualiza su horario semanal completo con todos sus grupos asignados
- Docente conoce su carga horaria total en horas semanales

<img width="499" height="215" alt="Image" src="https://github.com/user-attachments/assets/e44cb516-8338-4d97-8d4d-800306498f01" />

---

# 4. GESTIÓN DE MATRÍCULAS - DIRECTOR

---

## CU-14: ACEPTAR SOLICITUD DE MATRÍCULA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-14

### Descripción
El Director revisa una solicitud de inscripción de un estudiante y la aprueba, cambiando su estado de PENDIENTE a ACEPTADA.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión con credenciales válidas
- El Director debe tener una sesión activa
- Debe existir al menos una inscripción con estado PENDIENTE
- El período debe estar en estado MATRICULA
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El Director accede a "Gestión de Matrículas" desde el menú principal

**FP2:** El sistema muestra la página con:
- Tabs por estado:
  * Pendientes (N) - tab activo por defecto
  * Aceptadas (N)
  * Rechazadas (N)
- Lista de inscripciones pendientes con:
  * Estudiante (código y nombre)
  * Materia y paralelo
  * Docente
  * Horarios
  * Créditos
  * Fecha de solicitud
  * Alertas (conflictos de horario, sin prerrequisitos)
  * Botón "Aceptar"
  * Botón "Rechazar"
- Filtros por carrera, materia, estudiante

**FP3:** El Director visualiza las solicitudes pendientes

**FP4:** El Director selecciona una solicitud específica

**FP5:** El Director revisa la información del estudiante y la materia

**FP6:** El Director hace clic en "Aceptar"

**FP7:** El sistema muestra modal de confirmación:
- Estudiante: Código - Nombre
- Materia: Código - Nombre - Paralelo
- Si hay alertas, las muestra destacadas
- Campo de observaciones (opcional)
- Botón "Confirmar Aceptación"
- Botón "Cancelar"

**FP8:** El Director puede agregar observaciones (opcional)

**FP9:** El Director hace clic en "Confirmar Aceptación"

**FP10:** El sistema actualiza el estado de la inscripción a ACEPTADA

**FP11:** El sistema registra:
- Fecha de aprobación
- Director que aprobó
- Observaciones (si hay)

**FP12:** El sistema confirma el cupo ocupado en el paralelo

**FP13:** El sistema guarda los cambios en la base de datos

**FP14:** El sistema envía notificación al estudiante: "Tu inscripción en Materia ha sido ACEPTADA"

**FP15:** El sistema muestra mensaje: "Inscripción aceptada exitosamente"

**FP16:** El sistema remueve la solicitud de la lista de pendientes

**FP17:** El sistema actualiza el contador de pendientes (-1)

### Subflujos

#### SF1: Ver Perfil del Estudiante (Paso 5 → Paso 5)
**SF1.1:** Antes de FP6, el Director hace clic en el nombre del estudiante  
**SF1.2:** El sistema muestra modal con datos personales, carrera, semestre, promedio acumulado, créditos aprobados, historial de gestiones y materias inscritas en gestión actual  
**SF1.3:** El Director revisa la información  
**SF1.4:** El Director cierra el modal  
**SF1.5:** El flujo retorna a FP5

#### SF2: Ver Detalle de la Materia (Paso 5 → Paso 5)
**SF2.1:** Antes de FP6, el Director hace clic en la materia  
**SF2.2:** El sistema muestra información completa de la materia, prerrequisitos y datos del grupo  
**SF2.3:** El Director revisa el contenido  
**SF2.4:** El Director cierra el modal  
**SF2.5:** El flujo retorna a FP5

#### SF3: Paralelo sin cupos (Paso 12 → Paso 9)
**SF3.1:** En FP12, el sistema detecta que el cupo máximo ya se alcanzó  
**SF3.2:** El sistema muestra advertencia: "Este paralelo ya no tiene cupos disponibles"  
**SF3.3:** El sistema pregunta: "¿Desea aceptar con sobrecupo?"  
**SF3.4:** Si el Director confirma: La inscripción se acepta con marca de "SOBRECUPO" y continúa a FP13  
**SF3.5:** Si el Director cancela: El sistema cierra el modal y retorna a FP3 sin cambios

#### SF4: Conflicto de horario crítico (Paso 10 → Paso 9)
**SF4.1:** El sistema detecta conflicto grave (3 o más materias simultáneas)  
**SF4.2:** El sistema muestra advertencia destacada en rojo  
**SF4.3:** El Director debe confirmar explícitamente para continuar  
**SF4.4:** Si confirma: El flujo continúa a FP10  
**SF4.5:** Si cancela: El flujo retorna a FP3

### Extensiones
Ninguno 

### Inclusiones

#### IN1: Obtener solicitudes pendientes (Obligatorio)
El sistema debe consultar todas las inscripciones con estado PENDIENTE de la carrera del Director.

#### IN2: Obtener cupos disponibles (Obligatorio)
El sistema debe verificar en tiempo real si el paralelo aún tiene cupos disponibles antes de confirmar.

### Flujos Alternativos

#### FA1: El sistema no está disponible
Al intentar guardar, si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y no se actualiza la inscripción.

#### FA2: Inscripción ya procesada
En FP10, el sistema detecta que ya fue aceptada o rechazada por otro Director simultáneamente, muestra mensaje: "Esta inscripción ya fue procesada", recarga la lista y el flujo termina.

### Postcondiciones
- Inscripción actualizada a estado ACEPTADA en la base de datos
- Cupo del paralelo decrementado (o marcado como sobrecupo si aplica)

<img width="502" height="277" alt="Image" src="https://github.com/user-attachments/assets/f0faacc3-7ed7-43f2-84d4-387ec72bc594" />

---

## CU-15: RECHAZAR SOLICITUD DE MATRÍCULA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-15

### Descripción
El Director revisa una solicitud de inscripción de un estudiante y la rechaza, proporcionando un motivo que será visible para el estudiante.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión con credenciales válidas
- El Director debe tener una sesión activa
- Debe existir al menos una inscripción con estado PENDIENTE
- El período debe estar en estado MATRICULA
- El sistema debe estar disponible

### Flujo Principal

**FP1:** El Director accede a "Gestión de Matrículas" desde el menú principal

**FP2:** El sistema muestra la lista de inscripciones pendientes

**FP3:** El Director visualiza las solicitudes pendientes

**FP4:** El Director selecciona una solicitud específica

**FP5:** El Director revisa la información y decide rechazarla

**FP6:** El Director hace clic en "Rechazar"

**FP7:** El sistema muestra modal de rechazo:
- Estudiante: Código - Nombre
- Materia: Código - Nombre - Paralelo
- Campo "Motivo del rechazo" (obligatorio, textarea)
- Motivos sugeridos (botones rápidos):
  * "No cumple prerrequisitos"
  * "Conflicto de horario crítico"
  * "Sobrecupo del paralelo"
  * "Carga académica excesiva"
  * "Materia no corresponde al nivel del estudiante"
- Botón "Confirmar Rechazo"
- Botón "Cancelar"

**FP8:** El Director selecciona un motivo sugerido O escribe uno personalizado

**FP9:** El Director hace clic en "Confirmar Rechazo"

**FP10:** El sistema valida que el motivo no esté vacío

**FP11:** El sistema actualiza el estado de la inscripción a RECHAZADA

**FP12:** El sistema registra:
- Fecha de rechazo
- Director que rechazó
- Motivo del rechazo

**FP13:** El sistema libera el cupo del paralelo (+1 disponible)

**FP14:** El sistema guarda los cambios en la base de datos

**FP15:** El sistema envía notificación al estudiante: "Tu inscripción en Materia ha sido RECHAZADA. Motivo: Motivo"

**FP16:** El sistema muestra mensaje: "Inscripción rechazada"

**FP17:** El sistema remueve la solicitud de la lista de pendientes

**FP18:** El sistema actualiza el contador de pendientes (-1)

### Subflujos

#### SF1: Usar Motivo Sugerido (Paso 8 → Paso 9)
**SF1.1:** En FP8, el Director hace clic en un botón de motivo sugerido  
**SF1.2:** El sistema carga ese texto automáticamente en el campo de motivo  
**SF1.3:** El Director puede editarlo o dejarlo como está  
**SF1.4:** El flujo continúa a FP9

#### SF2: Motivo vacío (Paso 10 → Paso 8)
**SF2.1:** En FP10, el sistema detecta que el campo motivo está vacío  
**SF2.2:** El sistema muestra mensaje: "El motivo del rechazo es obligatorio"  
**SF2.3:** El sistema marca el campo en rojo  
**SF2.4:** El flujo retorna a FP8  

#### SF3: Ver Perfil del Estudiante (Paso 5 → Paso 5)
**SF3.1:** Antes de FP6, el Director hace clic en el nombre del estudiante  
**SF3.2:** El sistema muestra el perfil completo con historial académico  
**SF3.3:** El Director revisa la información  
**SF3.4:** El Director cierra el modal  
**SF3.5:** El flujo retorna a FP5

### Extensiones

#### EX1: Cancelar Rechazo
**EX1.1:** En FP9, el Director puede hacer clic en "Cancelar"  
**EX1.2:** El sistema cierra el modal sin realizar cambios  
**EX1.3:** La solicitud permanece con estado PENDIENTE  

### Inclusiones

#### IN1: Obtener solicitudes pendientes (Obligatorio)
El sistema debe consultar todas las inscripciones con estado PENDIENTE de la carrera del Director.

### Flujos Alternativos

#### FA3: El sistema no está disponible
Al intentar guardar, si el servidor no responde, el sistema muestra mensaje: "El servidor no está disponible. Intenta más tarde" y no se actualiza la inscripción.

#### FA4: Inscripción ya procesada
En FP11, el sistema detecta que ya fue aceptada o rechazada por otro Director simultáneamente, muestra mensaje: "Esta inscripción ya fue procesada", recarga la lista y el flujo termina.

#### FA5: Período ya no está en matrícula
El sistema detecta que el período cambió de estado, muestra mensaje: "El período de matrícula ha cerrado. No se pueden procesar más solicitudes" y el flujo termina.

### Postcondiciones
- Inscripción actualizada a estado RECHAZADA en la base de datos
- Cupo del paralelo liberado (incrementado en 1) 

<img width="506" height="254" alt="Image" src="https://github.com/user-attachments/assets/78cc5787-1027-452f-b73a-b1f378afbda4" />

---

# 5. PERÍODOS ACADÉMICOS - DIRECTOR

---

## CU-16: CREAR PERÍODO ACADÉMICO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-16

### Descripción
El Director crea un nuevo período académico (gestión) estableciendo el año, semestre y fechas de inicio y fin.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El Director debe tener permisos de administración de períodos académicos
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Períodos Académicos" desde el menú principal

**FP2:** El sistema muestra la lista de períodos existentes con:
- Código (formato: YYYY-S)
- Año
- Semestre (1 o 2)
- Fechas (inicio - fin)
- Estado (MATRICULA, EN_CURSO, FINALIZADA)
- Período activo resaltado con badge verde
- Botón "Crear Nuevo Período"

**FP3:** El Director hace clic en "Crear Nuevo Período"

**FP4:** El sistema muestra formulario modal con:
- Campo "Año" (número, obligatorio, formato YYYY)
- Selector "Semestre" (obligatorio):
  * 1 - Primer Semestre
  * 2 - Segundo Semestre
- Campo "Fecha de Inicio" (date picker, obligatorio)
- Campo "Fecha de Fin" (date picker, obligatorio)
- Estado inicial (por defecto: MATRICULA)
- Botón "Crear Período"
- Botón "Cancelar"

**FP5:** El Director ingresa el año (ejemplo: 2025)

**FP6:** El Director selecciona el semestre (1 o 2)

**FP7:** El sistema genera el código: Año-Semestre (ejemplo: "2025-1")

**FP8:** El sistema muestra el código generado (no editable)

**FP9:** El Director selecciona la fecha de inicio

**FP10:** El Director selecciona la fecha de fin

**FP11:** El Director hace clic en "Crear Período"

**FP12:** El sistema valida que todos los campos estén completos

**FP13:** El sistema valida que el año sea válido (≥ año actual)

**FP14:** El sistema valida que el semestre sea 1 o 2

**FP15:** El sistema valida que fecha_fin > fecha_inicio

**FP16:** El sistema valida que no existe otro período con el mismo código

**FP17:** El sistema valida que no haya otro período activo (solo puede haber uno)

**FP18:** El sistema crea el registro del período con:
- Código generado
- Año
- Semestre
- Fecha inicio
- Fecha fin
- Estado: MATRICULA

**FP19:** El sistema guarda el período en la base de datos

**FP20:** El sistema muestra mensaje: "Período código creado exitosamente"

**FP21:** El sistema cierra el modal

**FP22:** El sistema actualiza la lista mostrando el nuevo período

### Subflujos

#### SF1: Campos obligatorios vacíos (Paso 12 → Paso 5)
En FP12, el sistema detecta campos vacíos, marca los campos en rojo, muestra mensaje: "Complete todos los campos" y retorna al Paso 5.

#### SF2: Año inválido (Paso 13 → Paso 5)
En FP13, el año es anterior al actual, el sistema muestra mensaje: "El año debe ser mayor o igual a año actual" y retorna al Paso 5.

#### SF3: Fechas incoherentes (Paso 15 → Paso 10)
En FP15, fecha_fin ≤ fecha_inicio, el sistema muestra mensaje: "La fecha de fin debe ser posterior a la fecha de inicio", marca ambos campos en rojo y retorna al Paso 10.

#### SF4: Período duplicado (Paso 16 → Paso 5)
En FP16, el código ya existe, el sistema muestra mensaje: "Ya existe un período código", muestra el período existente y retorna al Paso 5.

### Extensiones
Ninguno

### Inclusiones
#### FA3: Obtener periodos academicos
Obtener periodos academicos del cubo comercial

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al guardar período
En FP19, si ocurre un error al guardar el período en la base de datos, el sistema muestra mensaje: "Error al crear el período. Intente nuevamente", registra el error en logs y el flujo termina.


### Postcondiciones
- Nuevo período académico creado en la base de datos con estado MATRICULA o INACTIVO
- Período disponible para crear grupos, paralelos y oferta académica

<img width="486" height="109" alt="Image" src="https://github.com/user-attachments/assets/7b5518b8-0bbf-4190-9b64-9fdb2919a62d" />

---

## CU-17: EDITAR PERÍODO ACADÉMICO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-17

### Descripción
El Director modifica las fechas de inicio y fin de un período académico existente.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir al menos un período académico creado
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Períodos Académicos"

**FP2:** El sistema muestra la lista de períodos existentes

**FP3:** El Director identifica el período que desea editar

**FP4:** El Director hace clic en "Editar" del período seleccionado

**FP5:** El sistema muestra formulario modal con los datos actuales:
- Código: YYYY-S (no editable, solo lectura)
- Año: valor (no editable)
- Semestre: valor (no editable)
- Fecha de Inicio: valor actual (editable)
- Fecha de Fin: valor actual (editable)
- Botón "Guardar Cambios"
- Botón "Cancelar"

**FP6:** El Director modifica la fecha de inicio

**FP7:** El Director modifica la fecha de fin

**FP8:** El Director hace clic en "Guardar Cambios"

**FP9:** El sistema valida que fecha_fin > fecha_inicio

**FP10:** El sistema valida que las nuevas fechas no causen conflictos con inscripciones existentes

**FP11:** El sistema actualiza las fechas del período en la base de datos

**FP12:** El sistema muestra mensaje: "Período actualizado exitosamente"

**FP13:** El sistema cierra el modal

**FP14:** El sistema actualiza la lista mostrando las nuevas fechas

### Subflujos

#### SF1: Fechas incoherentes (Paso 9 → Paso 6)
En FP9, fecha_fin ≤ fecha_inicio, el sistema muestra mensaje: "La fecha de fin debe ser posterior a la fecha de inicio", marca ambos campos en rojo y retorna al Paso 6.

#### SF2: Sin cambios detectados (Paso 8 → FIN)
En FP8, el sistema detecta que no hubo cambios en las fechas, muestra mensaje: "No se realizaron cambios", cierra el modal y el flujo termina.

### Extensiones
Ninguno

### Inclusiones

#### IN1: Obtener datos del período a editar
En FP5, el sistema ejecuta consulta para obtener todos los datos del período seleccionado (código, año, semestre, fechas actuales, estado).

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al actualizar período
En FP11, si ocurre un error al actualizar el período en la base de datos, el sistema muestra mensaje: "Error al actualizar el período. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Período eliminado por otro usuario
Si entre FP4 y FP11 otro Director eliminó el período, el sistema muestra mensaje: "El período ya no existe. Fue eliminado por otro usuario", recarga la lista y el flujo termina.

### Postcondiciones
- Fechas del período actualizadas en la base de datos (fecha_inicio y/o fecha_fin)
- Cambios reflejados inmediatamente en toda la gestión académica

<img width="500" height="120" alt="Image" src="https://github.com/user-attachments/assets/cb233084-3d94-4bf8-b6f4-5211919ffc3f" />

---

## CU-18: ACTIVAR PERÍODO ACADÉMICO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-18

### Descripción
El Director cambia el estado de un período académico a MATRICULA (activo), permitiendo que los estudiantes realicen inscripciones. Solo puede haber un período activo a la vez.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir un período en estado diferente a MATRICULA (EN_CURSO o FINALIZADA)
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Períodos Académicos"

**FP2:** El sistema muestra la lista de períodos con su estado

**FP3:** El Director identifica un período que desea activar (estado: EN_CURSO o FINALIZADA)

**FP4:** El Director hace clic en "Activar" del período seleccionado

**FP5:** El sistema verifica si existe otro período activo (estado MATRICULA)

**FP6:** Si NO hay otro activo, el sistema muestra modal de confirmación:
- Período a activar: Código - Año-Semestre
- Fechas: Inicio - Fin
- Mensaje: "Al activar este período, los estudiantes podrán realizar inscripciones"
- Botón "Confirmar Activación"
- Botón "Cancelar"

**FP7:** El Director hace clic en "Confirmar Activación"

**FP8:** El sistema actualiza el estado del período a MATRICULA

**FP9:** El sistema registra:
- Fecha de activación
- Director que activó

**FP10:** El sistema guarda los cambios en la base de datos

**FP11:** El sistema muestra mensaje: "Período código activado. Ya está disponible para matrículas"

**FP12:** El sistema actualiza la lista mostrando el período con badge verde "ACTIVO"

**FP13:** El sistema habilita el módulo de inscripciones para estudiantes

### Subflujos

#### SF1: Período ya activo (Paso 5 → FIN)
En FP5, el sistema detecta que el período ya está en estado MATRICULA, muestra mensaje: "Este período ya está activo", el botón "Activar" está deshabilitado y el flujo termina.

### Extensiones
Ninguno

### Inclusiones

#### IN1: Obtener datos del período a activar
En FP5, el sistema ejecuta consulta para obtener todos los datos del período seleccionado (código, año, semestre, fechas actuales, estado).

### Flujos Alternativos

#### FA1: Sin conexión a internet
Si en cualquier momento del flujo se pierde la conexión a internet, el sistema muestra mensaje: "Sin conexión. Verifique su conexión a internet" y el flujo termina.

#### FA2: Sesión expirada
Si en cualquier momento la sesión del Director expira, el sistema muestra mensaje: "Su sesión ha expirado. Por favor, inicie sesión nuevamente", redirige al login y el flujo termina.

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al activar período
En FP10, si ocurre un error al actualizar el estado del período en la base de datos, el sistema muestra mensaje: "Error al activar el período. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Período modificado o eliminado por otro usuario
Si entre FP4 y FP10 otro Director modificó o eliminó el período, el sistema muestra mensaje: "El período ha sido modificado o eliminado por otro usuario", recarga la lista y el flujo termina.

### Postcondiciones
- Período cambia a estado MATRICULA (activo) en la base de datos

<img width="506" height="99" alt="Image" src="https://github.com/user-attachments/assets/c6d9fa32-04db-4713-8f50-8cb9b925e7ff" />

---

## CU-19: CERRAR PERÍODO ACADÉMICO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-19

### Descripción
El Director finaliza un período académico cambiando su estado a FINALIZADA, lo que bloquea cualquier modificación de inscripciones y calificaciones.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El período debe estar en estado MATRICULA o EN_CURSO
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Períodos Académicos"

**FP2:** El sistema muestra la lista de períodos

**FP3:** El Director identifica el período que desea cerrar

**FP4:** El Director hace clic en "Cerrar" del período seleccionado

**FP5:** El sistema muestra modal de confirmación:
- Período a cerrar: Código - Año-Semestre
- Estado actual: MATRICULA o EN_CURSO
- Advertencia: "Esta acción bloqueará inscripciones y calificaciones"
- Estadísticas:
  * Total de inscripciones: N
  * Inscripciones pendientes: N (si hay, muestra alerta)
  * Estudiantes sin calificaciones completas: N (si hay, muestra alerta)
- Checkbox "Confirmo que deseo cerrar este período"
- Botón "Cerrar Período" (deshabilitado hasta marcar checkbox)
- Botón "Cancelar"

**FP6:** El Director revisa las estadísticas

**FP7:** El Director marca el checkbox de confirmación

**FP8:** El botón "Cerrar Período" se habilita

**FP9:** El Director hace clic en "Cerrar Período"

**FP10:** El sistema actualiza el estado del período a FINALIZADA

**FP11:** El sistema bloquea:
- Nuevas inscripciones de estudiantes
- Aceptación/rechazo de matrículas
- Registro/edición de calificaciones
- Creación/edición de grupos

**FP12:** El sistema procesa inscripciones pendientes:
- Si quedan PENDIENTES, las cambia automáticamente a RECHAZADA con motivo: "Período cerrado"

**FP13:** El sistema registra:
- Fecha de cierre
- Director que cerró
- Estadísticas finales

**FP14:** El sistema guarda los cambios en la base de datos

**FP15:** El sistema muestra mensaje: "Período código cerrado exitosamente"

**FP16:** El sistema actualiza la lista mostrando el período con badge gris "FINALIZADA"

### Subflujos

#### SF1: Ver Detalle de Inscripciones Pendientes (Paso 6 → Paso 6)
En FP6, el Director hace clic en "Ver inscripciones pendientes", el sistema muestra lista de estudiantes con inscripciones PENDIENTES, el Director revisa y cierra, retornando al Paso 6.

#### SF2: Ver Calificaciones Pendientes (Paso 6 → Paso 6)
En FP6, el Director hace clic en "Ver estudiantes sin calificaciones", el sistema muestra lista de estudiantes por materia sin calificaciones completas, el Director revisa y cierra, retornando al Paso 6.

#### SF3: Período ya cerrado (Paso 4 → FIN)
En FP4, el sistema detecta que el período ya está en estado FINALIZADA, muestra mensaje: "Este período ya está cerrado", el botón "Cerrar" está deshabilitado y el flujo termina.

### Extensiones
Ninguna

### Inclusiones
#### EX: Obtener periodo academico
Se obtien el periodo academico del cubo

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al cerrar período
En FP14, si ocurre un error al actualizar el estado del período en la base de datos, el sistema muestra mensaje: "Error al cerrar el período. Intente nuevamente", registra el error en logs y el flujo termina.


### Postcondiciones
- Período cambia a estado FINALIZADA en la base de datos

<img width="492" height="152" alt="Image" src="https://github.com/user-attachments/assets/526e40c7-6ea9-44c9-b735-159949982cd6" />

---

## CU-20: ELIMINAR PERÍODO ACADÉMICO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-20

### Descripción
El Director elimina completamente un período académico que no tiene inscripciones ni grupos asociados.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El período NO debe tener inscripciones, grupos/paralelos ni calificaciones registradas
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Períodos Académicos"

**FP2:** El sistema muestra la lista de períodos

**FP3:** El Director identifica un período que desea eliminar (típicamente uno recién creado por error)

**FP4:** El Director hace clic en "Eliminar" del período seleccionado

**FP5:** El sistema verifica que el período no tenga:
- Inscripciones (PENDIENTE, ACEPTADA o RECHAZADA)
- Grupos/paralelos creados
- Calificaciones registradas

**FP6:** Si NO tiene registros asociados, el sistema muestra modal de confirmación:
- Período a eliminar: Código - Año-Semestre
- Fechas: Inicio - Fin
- Advertencia: "Esta acción es permanente y no se puede deshacer"
- Mensaje: "Este período no tiene inscripciones ni grupos"
- Campo de texto: "Escribe ELIMINAR para confirmar"
- Botón "Eliminar Definitivamente" (deshabilitado)
- Botón "Cancelar"

**FP7:** El Director escribe "ELIMINAR" en el campo de confirmación

**FP8:** El botón "Eliminar Definitivamente" se habilita

**FP9:** El Director hace clic en "Eliminar Definitivamente"

**FP10:** El sistema elimina el registro del período de la base de datos

**FP11:** El sistema muestra mensaje: "Período código eliminado exitosamente"

**FP12:** El sistema actualiza la lista removiendo el período

### Subflujos

#### SF1: Confirmación incorrecta (Paso 7 → Paso 7)
En FP7, si el texto ingresado no es exactamente "ELIMINAR", el botón permanece deshabilitado, el sistema muestra hint: "Escribe ELIMINAR (en mayúsculas)" y retorna al Paso 7 esperando entrada correcta.

### Extensiones
Ningno

### Inclusiones
#### IN1: Obtener datos del período
En FP5, el sistema ejecuta consulta para obtener todos los datos del período seleccionado (código, año, semestre, fechas actuales, estado).


### Flujos Alternativos

#### FA1: Sin conexión a internet
Si en cualquier momento del flujo se pierde la conexión a internet, el sistema muestra mensaje: "Sin conexión. Verifique su conexión a internet" y el flujo termina.

#### FA2: Sesión expirada
Si en cualquier momento la sesión del Director expira, el sistema muestra mensaje: "Su sesión ha expirado. Por favor, inicie sesión nuevamente", redirige al login y el flujo termina.

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al eliminar período
En FP10, si ocurre un error al eliminar el período de la base de datos, el sistema muestra mensaje: "Error al eliminar el período. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Período modificado o eliminado por otro usuario
Si entre FP4 y FP10 otro Director modificó o eliminó el período, el sistema muestra mensaje: "El período ha sido modificado o eliminado por otro usuario", recarga la lista y el flujo termina.

### Postcondiciones
- Período eliminado permanentemente 

<img width="498" height="180" alt="Image" src="https://github.com/user-attachments/assets/139c85aa-56fe-4653-8cb0-c4c66501d1cf" />

---

# 6. MATERIAS - DIRECTOR

---

## CU-21: CREAR MATERIA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-21

### Descripción
El Director registra una nueva materia en el catálogo de materias de la carrera, definiendo código, nombre, créditos, nivel y opcionalmente prerrequisitos.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El Director debe tener permisos de administración de materias
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Materias" desde el menú principal

**FP2:** El sistema muestra la lista de materias existentes con:
- Código
- Nombre
- Créditos
- Nivel/Semestre recomendado
- Prerrequisitos
- Estado (Activa/Inactiva)
- Botón "Crear Nueva Materia"

**FP3:** El Director hace clic en "Crear Nueva Materia"

**FP4:** El sistema muestra formulario modal con:
- Campo "Código" (obligatorio, formato: MAT-XXX-YY)
- Campo "Nombre" (obligatorio)
- Campo "Descripción" (opcional, textarea)
- Campo "Créditos" (obligatorio, número 1-10)
- Selector "Nivel/Semestre" (obligatorio, 1-10)
- Selector múltiple "Prerrequisitos" (opcional, lista de materias existentes)
- Selector "Carrera" (obligatorio)
- Botón "Guardar Materia"
- Botón "Cancelar"

**FP5:** El Director ingresa el código de la materia (formato: MAT-XXX-YY)

**FP6:** El sistema valida que el código no exista

**FP7:** El Director ingresa el nombre de la materia

**FP8:** El Director puede ingresar una descripción

**FP9:** El Director ingresa la cantidad de créditos (1-10)

**FP10:** El Director selecciona el nivel/semestre recomendado

**FP11:** El Director puede seleccionar materias prerrequisito (opcional)

**FP12:** Si selecciona prerrequisitos, el sistema valida que no haya dependencia circular

**FP13:** El Director selecciona la carrera

**FP14:** El Director hace clic en "Guardar Materia"

**FP15:** El sistema valida todos los campos obligatorios

**FP16:** El sistema valida el formato del código (MAT-XXX-YY)

**FP17:** El sistema valida que los créditos estén entre 1 y 10

**FP18:** El sistema crea el registro de la materia con estado ACTIVA

**FP19:** El sistema guarda la materia en la base de datos

**FP20:** El sistema muestra mensaje: "Materia código creada exitosamente"

**FP21:** El sistema cierra el modal

**FP22:** El sistema actualiza la lista mostrando la nueva materia

### Subflujos

#### SF1: Código duplicado (Paso 6 → Paso 5)
En FP6, el sistema detecta código existente, muestra mensaje: "El código código ya existe", marca el campo en rojo y retorna al Paso 5.

#### SF2: Formato de código inválido (Paso 16 → Paso 5)
En FP16, el formato no es MAT-XXX-YY, el sistema muestra mensaje: "Use el formato MAT-XXX-YY (Ejemplo: MAT-101-01)", marca el campo en rojo y retorna al Paso 5.

#### SF3: Créditos fuera de rango (Paso 17 → Paso 9)
En FP17, los créditos son <1 o >10, el sistema muestra mensaje: "Los créditos deben estar entre 1 y 10", marca el campo en rojo y retorna al Paso 9.

#### SF4: Campos obligatorios vacíos (Paso 15 → Paso 5)
En FP15, el sistema detecta campos vacíos, marca todos los campos faltantes en rojo, muestra mensaje: "Complete todos los campos obligatorios" y retorna al Paso 5.

#### SF5: Validación de dependencia circular (Paso 12 → Paso 11)
En FP12, al seleccionar prerrequisitos, el sistema valida en tiempo real que no se cree dependencia circular. Si detecta ciclo, muestra alerta: "Dependencia circular detectada: Materia A → Materia B → Materia A", deshabilita esa opción en el selector y retorna al Paso 11.

### Extensiones
Ninguna

### Inclusiones

#### IN1: Obtener lista de materias existentes
En FP2, el sistema ejecuta consulta para obtener todas las materias registradas con sus códigos, nombres, créditos y niveles.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al guardar materia
En FP19, si ocurre un error al guardar la materia en la base de datos, el sistema muestra mensaje: "Error al crear la materia. Intente nuevamente", registra el error en logs y el flujo termina.

### Postcondiciones
- Nueva materia registrada en el catálogo con estado ACTIVA
- Código único asignado en formato MAT-XXX-YY

<img width="499" height="153" alt="Image" src="https://github.com/user-attachments/assets/78c54f65-4388-4f66-bc23-bdfba72a578b" />

---

## CU-22: EDITAR MATERIA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-22

### Descripción
El Director modifica la información de una materia existente, incluyendo nombre, descripción, créditos, nivel y prerrequisitos.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir al menos una materia registrada en el catálogo
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Materias"

**FP2:** El sistema muestra la lista de materias existentes

**FP3:** El Director identifica la materia que desea editar

**FP4:** El Director hace clic en "Editar" de la materia seleccionada

**FP5:** El sistema muestra formulario modal con los datos actuales:
- Código: valor (no editable, solo lectura)
- Nombre: valor (editable)
- Descripción: valor (editable)
- Créditos: valor (editable)
- Nivel/Semestre: valor (editable)
- Prerrequisitos: lista actual (editable)
- Carrera: valor (no editable)
- Botón "Guardar Cambios"
- Botón "Cancelar"

**FP6:** El Director modifica el nombre (si desea)

**FP7:** El Director modifica la descripción (si desea)

**FP8:** El Director modifica los créditos (si desea)

**FP9:** El Director modifica el nivel/semestre (si desea)

**FP10:** El Director modifica los prerrequisitos (agregar o quitar)

**FP11:** Si modifica prerrequisitos, el sistema valida que no cree dependencia circular

**FP12:** El Director hace clic en "Guardar Cambios"

**FP13:** El sistema valida que los campos obligatorios no estén vacíos

**FP14:** El sistema valida que los créditos estén entre 1 y 10

**FP15:** El sistema actualiza el registro de la materia en la base de datos

**FP16:** El sistema muestra mensaje: "Materia actualizada exitosamente"

**FP17:** El sistema cierra el modal

**FP18:** El sistema actualiza la lista mostrando los cambios

### Subflujos

#### SF1: Nombre vacío (Paso 13 → Paso 6)
En FP13, si el nombre está vacío, el sistema muestra mensaje: "El nombre es obligatorio", marca el campo en rojo y retorna al Paso 6.

#### SF2: Créditos fuera de rango (Paso 14 → Paso 8)
En FP14, si los créditos son <1 o >10, el sistema muestra mensaje: "Los créditos deben estar entre 1 y 10", marca el campo en rojo y retorna al Paso 8.

#### SF3: Dependencia circular detectada (Paso 11 → Paso 10)
En FP11, si agregar un prerrequisito crearía ciclo, el sistema muestra alerta: "No se puede agregar Materia como prerrequisito porque crearía dependencia circular", impide la selección y retorna al Paso 10.

#### SF4: Sin cambios detectados (Paso 12 → FIN)
En FP12, si el sistema detecta que no hubo modificaciones, muestra mensaje: "No se realizaron cambios", cierra el modal y el flujo termina.

### Extensiones
Ninguno

### Inclusiones

#### IN2: Obtener datos de la materia
En FP5, el sistema ejecuta consulta para obtener todos los datos de la materia seleccionada (código, nombre, descripción, créditos, nivel, prerrequisitos actuales, carrera).

#### IN4: Obtener grupos de la materia
Al cargar FP5, el sistema verifica si existen grupos/paralelos activos que utilicen esta materia.


### Flujos Alternativos

#### FA1: Sin conexión a internet
Si en cualquier momento del flujo se pierde la conexión a internet, el sistema muestra mensaje: "Sin conexión. Verifique su conexión a internet" y el flujo termina.

#### FA2: Sesión expirada
Si en cualquier momento la sesión del Director expira, el sistema muestra mensaje: "Su sesión ha expirado. Por favor, inicie sesión nuevamente", redirige al login y el flujo termina.

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al actualizar materia
En FP15, si ocurre un error al actualizar la materia en la base de datos, el sistema muestra mensaje: "Error al actualizar la materia. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Materia eliminada por otro usuario
Si entre FP4 y FP15 otro Director eliminó la materia, el sistema muestra mensaje: "La materia ya no existe. Fue eliminada por otro usuario", recarga la lista y el flujo termina.

### Postcondiciones
- Materia actualizada en el sistema con los nuevos datos
- Cambios reflejados inmediatamente en el catálogo de materias

<img width="502" height="192" alt="Image" src="https://github.com/user-attachments/assets/3188c73a-e604-4c9d-82fd-2de1f8d483d6" />

---

## CU-23: ELIMINAR MATERIA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-23

### Descripción
El Director elimina una materia del catálogo que no tiene grupos asociados ni inscripciones históricas.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- La materia NO debe tener grupos, inscripciones, calificaciones ni ser prerrequisito de otras materias
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Materias"

**FP2:** El sistema muestra la lista de materias

**FP3:** El Director identifica la materia que desea eliminar

**FP4:** El Director hace clic en "Eliminar" de la materia seleccionada

**FP5:** El sistema verifica que la materia no tenga:
- Grupos asociados (actuales o históricos)
- Inscripciones
- Calificaciones
- No sea prerrequisito de otras materias

**FP6:** Si NO tiene registros asociados, el sistema muestra modal de confirmación:
- Materia a eliminar: Código - Nombre
- Créditos: N
- Nivel: N
- Advertencia: "Esta acción es permanente y no se puede deshacer"
- Campo de texto: "Escribe el código de la materia para confirmar"
- Botón "Eliminar Definitivamente" (deshabilitado)
- Botón "Cancelar"

**FP7:** El Director escribe el código de la materia

**FP8:** El sistema valida que el código coincida

**FP9:** El botón "Eliminar Definitivamente" se habilita

**FP10:** El Director hace clic en "Eliminar Definitivamente"

**FP11:** El sistema elimina el registro de la materia de la base de datos

**FP12:** El sistema muestra mensaje: "Materia código eliminada exitosamente"

**FP13:** El sistema actualiza la lista removiendo la materia

### Subflujos

#### SF1: Código incorrecto (Paso 8 → Paso 7)
En FP8, si el código no coincide, el botón permanece deshabilitado, el sistema muestra mensaje: "El código no coincide" y retorna al Paso 7 esperando entrada correcta.

### Extensiones
Ninguno

### Inclusiones
#### IN: Obtener materias
Obtener las materias del cubo comercial

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al eliminar materia
En FP11, si ocurre un error al eliminar la materia de la base de datos, el sistema muestra mensaje: "Error al eliminar la materia. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Materia modificada o eliminada por otro usuario
Si entre FP4 y FP11 otro Director modificó o eliminó la materia, el sistema muestra mensaje: "La materia ha sido modificada o eliminada por otro usuario", recarga la lista y el flujo termina.

### Postcondiciones
- Materia eliminada de los repositorios
- Materia removida del catálogo de materias

<img width="474" height="163" alt="Image" src="https://github.com/user-attachments/assets/128b51ba-4f56-4136-9ae3-6d56d9334ad9" />

---

## CU-24: CONSULTAR CATÁLOGO DE MATERIAS

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-24

### Descripción
El Director visualiza el catálogo completo de materias de la carrera, con opciones de filtrado y búsqueda.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Materias" desde el menú principal

**FP2:** El sistema obtiene todas las materias de la carrera

**FP3:** El sistema muestra la página con:
- Título: "Catálogo de Materias"
- Filtros:
  * Por nivel/semestre (1-10)
  * Por estado (Activa/Inactiva)
  * Por cantidad de créditos
- Campo de búsqueda por código o nombre
- Tabla de materias con columnas:
  * Código
  * Nombre
  * Créditos
  * Nivel recomendado
  * Prerrequisitos (cantidad)
  * Estado (badge verde/gris)
  * Acciones (Ver, Editar, Eliminar)
- Estadísticas:
  * Total de materias: N
  * Créditos totales de la carrera: N
  * Materias por nivel
- Botón "Crear Nueva Materia"
- Botón "Exportar Catálogo" (Excel)
- Vista agrupada por nivel (opcional)

**FP4:** El Director visualiza el catálogo completo

### Subflujos

#### SF1: Filtrar por Nivel (Paso 3 → Paso 4)
El Director selecciona un nivel del filtro, el sistema filtra mostrando solo materias de ese nivel, actualiza las estadísticas y retorna al Paso 4.

#### SF2: Buscar Materia por código o nombre (Paso 3 → Paso 4)
El Director escribe en el campo de búsqueda, el sistema filtra en tiempo real por código o nombre, muestra coincidencias y retorna al Paso 4.

#### SF3: Ordenar Tabla (Paso 3 → Paso 4)
El Director hace clic en una columna (Código, Nombre, Créditos, Nivel), el sistema ordena ascendente/descendente y retorna al Paso 4.

#### SF4: Agrupar por Nivel (Paso 3 → Paso 4)
El Director activa "Vista por Nivel", el sistema reorganiza agrupando por nivel/semestre, muestra subtotales de créditos por nivel y retorna al Paso 4.

#### SF5: Sin resultados de búsqueda (Paso 3 → Paso 4)
Si la búsqueda no arroja resultados, el sistema muestra mensaje: "No se encontraron materias", sugiere ajustar filtros y retorna al Paso 4.

### Extensiones

#### EX1: Ver Detalle de Materia
En cualquier momento, el Director puede hacer clic en "Ver" de una materia, el sistema ejecuta CU-25: Ver Detalle de Materia.

### Inclusiones

#### IN2: Obtener todas las materias de la carrera
En FP2, el sistema ejecuta consulta para obtener todas las materias con sus códigos, nombres, créditos, niveles, prerrequisitos y estados.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

### Postcondiciones
- Director visualiza catálogo completo de materias
- Información organizada y filtrable por nivel, estado y créditos
- Estadísticas visibles (total materias, créditos totales, materias por nivel)

<img width="499" height="292" alt="Image" src="https://github.com/user-attachments/assets/15640857-a370-425d-96fd-a44c3308bd59" />

---

## CU-25: VER DETALLE DE MATERIA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-25

### Descripción
El Director visualiza toda la información detallada de una materia específica, incluyendo prerrequisitos, grupos históricos y estadísticas.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir la materia a consultar en el catálogo
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director selecciona "Ver" de una materia desde el catálogo (CU-24)

**FP2:** El sistema obtiene toda la información de la materia

**FP3:** El sistema obtiene estadísticas históricas

**FP4:** El sistema muestra página/modal con:

**Información Básica:**
- Código
- Nombre completo
- Descripción
- Créditos
- Nivel/Semestre recomendado
- Carrera
- Estado (Activa/Inactiva)

**Prerrequisitos:**
- Lista de materias prerrequisito (código y nombre)
- Si no tiene: "Sin prerrequisitos"

**Materias que la requieren:**
- Lista de materias que tienen esta como prerrequisito
- Si ninguna: "Ninguna materia la requiere"

**Grupos Históricos:**
- Tabla con grupos creados en gestiones anteriores
- Gestión, Paralelo, Docente, Estudiantes inscritos
- Total de grupos: N

**Estadísticas:**
- Total de estudiantes que la cursaron: N
- Promedio de aprobación: N%
- Promedio de calificación: N/100
- Gestión con más inscritos
- Docentes que la han impartido

**Acciones:**
- Botón "Editar Materia"
- Botón "Crear Grupo" (si hay período activo)
- Botón "Cerrar"

**FP5:** El Director revisa toda la información

### Subflujos

#### SF1: Ver Grupos de una Gestión Específica (Paso 5 → Paso 5)
El Director hace clic en una gestión de la tabla de grupos históricos, el sistema filtra mostrando solo grupos de esa gestión, el Director visualiza los grupos y retorna al Paso 5.

#### SF2: Materia sin grupos históricos (Paso 3 → Paso 5)
Si el sistema no encuentra grupos creados, muestra mensaje: "Esta materia aún no tiene grupos creados", destaca el botón "Crear Grupo" y retorna al Paso 5.

#### SF3: Sin estadísticas disponibles (Paso 3 → Paso 5)
Si no hay inscripciones completadas, el sistema muestra mensaje: "Sin estadísticas disponibles", las métricas muestran "N/A" y retorna al Paso 5.

### Extensiones
Ninguno

### Inclusiones
Ninguno

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Materia eliminada
Si entre FP1 y FP2 otro Director eliminó la materia, el sistema muestra mensaje: "La materia ya no existe", retorna al catálogo y el flujo termina.

### Postcondiciones
- Director visualiza información completa de la materia seleccionada
- Datos básicos visibles (código, nombre, descripción, créditos, nivel, carrera, estado)
- Prerrequisitos listados con código y nombre (o "Sin prerrequisitos")

<img width="358" height="199" alt="Image" src="https://github.com/user-attachments/assets/402710ed-daf6-4d60-b702-23c46507e764" />

---

# 7. GRUPOS/PARALELOS - DIRECTOR

---

## CU-26: CREAR GRUPO DE MATERIA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-26

### Descripción
El Director crea un nuevo grupo/paralelo de una materia para el período académico activo.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir al menos un período académico activo (estado MATRICULA)
- Deben existir materias registradas en el catálogo
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Grupos" desde el menú

**FP2:** El Director hace clic en "Crear Nuevo Grupo"

**FP3:** El sistema muestra formulario modal con:
- Selector de Materia (obligatorio)
- Período Académico: Gestión actual - auto-asignado, no editable
- Paralelo: Auto-calculado (placeholder)
- Cupo máximo: 30 (editable 5-50)
- Estado: ABIERTO (default)
- Botón "Crear Grupo"
- Botón "Cancelar"

**FP4:** El Director selecciona la materia del dropdown

**FP5:** El sistema auto-calcula el paralelo (letra: A, B, C...)

**FP6:** El sistema muestra:
- Materia: Código - Nombre
- Créditos: N
- Nivel: N
- Paralelo: Letra calculada

**FP7:** El Director ajusta el cupo máximo (si desea)

**FP8:** El sistema valida que el cupo esté entre 5 y 50

**FP9:** El Director hace clic en "Crear Grupo"

**FP10:** El sistema valida:
- Materia seleccionada
- Cupo dentro del rango

**FP11:** El sistema crea el registro del grupo con:
- Materia
- Gestión (actual)
- Paralelo (letra calculada)
- Cupo máximo
- Cupo disponible = Cupo máximo
- Estado: ABIERTO
- Docente: NULL (pendiente asignación)
- Horarios: [] (pendiente asignación)

**FP12:** El sistema muestra mensaje: "Grupo Materia - Paralelo creado exitosamente"

**FP13:** El sistema cierra el modal

**FP14:** El sistema actualiza la lista de grupos

**FP15:** El sistema muestra alerta informativa: "Recuerda asignar docente y horario al grupo"

### Subflujos

#### SF1: Cupo fuera de rango (Paso 10 → Paso 7)
En FP10, si el cupo es <5 o >50, el sistema muestra mensaje: "El cupo debe estar entre 5 y 50 estudiantes", marca el campo en rojo y retorna al Paso 7.

#### SF2: Materia no seleccionada (Paso 10 → Paso 4)
En FP10, si no se seleccionó materia, el sistema muestra mensaje: "Debes seleccionar una materia" y retorna al Paso 4.

#### SF3: Sin período activo (Paso 3 → FIN)
En FP3, si no hay período en estado MATRICULA, el sistema muestra mensaje: "No hay período académico activo. Activa un período primero", deshabilita el formulario y el flujo termina.

#### SF4: Sin materias registradas (Paso 3 → FIN)
En FP3, si no hay materias en el sistema, el sistema muestra mensaje: "No hay materias registradas. Crea materias primero" y el flujo termina.

### Extensiones
Ninguna

### Inclusiones
#### EX1: Obtener materias
Se obtiene las materias de cubo comercial.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al crear grupo
En FP11, si ocurre un error al guardar el grupo en la base de datos, el sistema muestra mensaje: "Error al crear el grupo. Intente nuevamente", registra el error en logs y el flujo termina.

### Postcondiciones
- Nuevo grupo registrado en el sistema vinculado al período activo
- Cupo máximo configurado

<img width="498" height="192" alt="Image" src="https://github.com/user-attachments/assets/98562dda-4757-43ac-9ea1-d09a821ea66a" />

---

## CU-27: EDITAR GRUPO DE MATERIA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-27

### Descripción
El Director modifica el cupo y estado de un grupo existente.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir al menos un grupo registrado
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Grupos"

**FP2:** El sistema muestra la lista de grupos

**FP3:** El Director identifica el grupo que desea editar

**FP4:** El Director hace clic en "Editar" del grupo seleccionado

**FP5:** El sistema muestra formulario modal con datos actuales:
- Materia: Código - Nombre (no editable, solo lectura)
- Paralelo: Letra (no editable)
- Gestión: Código (no editable)
- Cupo máximo: valor actual (editable)
- Inscripciones aceptadas: N (informativo)
- Estado: dropdown ABIERTO/CERRADO (editable)
- Docente asignado: Nombre (informativo, link a CU-28)
- Horarios: Lista (informativo, link a CU-29)
- Botón "Guardar Cambios"
- Botón "Cancelar"

**FP6:** El Director modifica el cupo máximo (si desea)

**FP7:** El sistema valida que el nuevo cupo sea ≥ inscripciones aceptadas

**FP8:** El Director cambia el estado (si desea)

**FP9:** Si cambia a CERRADO, el sistema muestra advertencia: "Los estudiantes no podrán inscribirse en este grupo"

**FP10:** El Director hace clic en "Guardar Cambios"

**FP11:** El sistema valida:
- Cupo ≥ inscripciones aceptadas
- Cupo entre 5 y 50

**FP12:** El sistema actualiza el registro del grupo

**FP13:** El sistema recalcula cupo disponible:
- Cupo disponible = Nuevo cupo máximo - Inscripciones aceptadas

**FP14:** El sistema muestra mensaje: "Grupo actualizado exitosamente"

**FP15:** El sistema cierra el modal

**FP16:** El sistema actualiza la lista de grupos

### Subflujos

#### SF1: Cupo menor que inscripciones aceptadas (Paso 11 → Paso 6)
En FP11, si el nuevo cupo es menor que las inscripciones aceptadas, el sistema muestra mensaje: "El cupo no puede ser menor que las inscripciones aceptadas (N)", marca el campo en rojo y retorna al Paso 6.

#### SF2: Cupo fuera de rango (Paso 11 → Paso 6)
En FP11, si el cupo es <5 o >50, el sistema muestra mensaje: "El cupo debe estar entre 5 y 50", marca el campo en rojo y retorna al Paso 6.

#### SF3: Sin cambios detectados (Paso 10 → FIN)
En FP10, si no hubo modificaciones, el sistema muestra mensaje: "No se realizaron cambios", cierra el modal y el flujo termina.

#### SF4: Aumentar Cupo (Paso 6 → Paso 7)
En FP6, si el Director aumenta el cupo, el sistema calcula nuevos cupos disponibles, muestra: "Cupos disponibles aumentarán a N" y continúa al Paso 7.

#### SF5: Reducir Cupo (Paso 6 → Paso 7)
En FP6, si el Director reduce el cupo, el sistema valida que nuevo cupo ≥ inscripciones aceptadas. Si es válido, muestra: "Cupos disponibles se reducirán a N" y continúa al Paso 7.

### Extensiones
Ninguno

### Inclusiones

#### IN1: Obtener datos del grupo a editar
En FP5, el sistema ejecuta consulta para obtener todos los datos del grupo: materia, paralelo, gestión, cupo actual, inscripciones aceptadas, estado, docente asignado, horarios.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al actualizar grupo
En FP12, si ocurre un error al actualizar el grupo en la base de datos, el sistema muestra mensaje: "Error al actualizar el grupo. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Grupo eliminado por otro usuario
Si entre FP4 y FP12 otro Director eliminó el grupo, el sistema muestra mensaje: "El grupo ya no existe. Fue eliminado por otro usuario", recarga la lista y el flujo termina.

### Postcondiciones
- Grupo actualizado en el sistema con los nuevos datos
- Cupo disponible recalculado correctamente (Cupo máximo - Inscripciones aceptadas)
- Cambios visibles en oferta académica inmediatamente

<img width="498" height="149" alt="Image" src="https://github.com/user-attachments/assets/a0f33400-98cf-4963-82b3-5ec433539ddb" />

---

## CU-28: ASIGNAR DOCENTE A GRUPO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-28

### Descripción
El Director asigna un docente a un grupo/paralelo específico.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir al menos un grupo sin docente asignado (o para reasignar)
- Deben existir docentes registrados en el sistema
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Grupos"

**FP2:** El sistema muestra la lista de grupos

**FP3:** El Director identifica el grupo al que desea asignar docente

**FP4:** El Director hace clic en "Asignar Docente" del grupo seleccionado

**FP5:** El sistema muestra modal con:
- Grupo: Materia - Paralelo (informativo)
- Créditos: N (informativo)
- Horarios actuales: Lista o "Sin horarios" (informativo)
- Docente actual: Nombre o "Sin asignar" (informativo)
- Selector de nuevo docente (dropdown)
- Botón "Asignar"
- Botón "Cancelar"

**FP6:** El sistema carga lista de docentes activos con:
- Código
- Nombre completo
- Especialidades
- Carga horaria actual (horas/semana)

**FP7:** El Director selecciona un docente del dropdown

**FP8:** El sistema muestra información del docente seleccionado:
- Especialidades
- Carga horaria actual: N horas/semana
- Grupos asignados: N

**FP9:** Si el grupo tiene horarios, el sistema valida disponibilidad del docente

**FP10:** Si hay conflictos de horario, muestra advertencia: "El docente tiene conflicto en horarios"

**FP11:** El Director revisa la información

**FP12:** El Director hace clic en "Asignar"

**FP13:** El sistema valida que se seleccionó un docente

**FP14:** Si hay docente anterior, el sistema lo desvincula del grupo

**FP15:** El sistema asigna el nuevo docente al grupo

**FP16:** El sistema actualiza la carga horaria del docente

**FP17:** El sistema muestra mensaje: "Docente asignado exitosamente a Materia - Paralelo"

**FP18:** El sistema cierra el modal

**FP19:** El sistema actualiza la lista mostrando el docente asignado

### Subflujos

#### SF1: Sin docente seleccionado (Paso 13 → Paso 7)
En FP13, si no se seleccionó docente, el sistema muestra mensaje: "Debes seleccionar un docente" y retorna al Paso 7.

#### SF2: Filtrar Docentes por Especialidad (Paso 6 → Paso 7)
En FP6, el sistema resalta docentes cuya especialidad coincide con la materia, muestra badge "Especialidad" en docentes recomendados. El Director puede seleccionar cualquier docente igualmente y continúa al Paso 7.

### Extensiones

#### EX1: Ver Horario del Docente
En FP8, el Director puede opcionalmente hacer clic en "Ver Horario" del docente, el sistema muestra modal con horario semanal actual del docente, el Director visualiza disponibilidad y cierra el modal.

### Inclusiones

#### IN1: Obtener lista de docentes activos
En FP6, el sistema obtiene todos los docentes ACTIVOS con su código, nombre completo, especialidades y carga horaria actual.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al asignar docente
En FP15, si ocurre un error al asignar el docente en la base de datos, el sistema muestra mensaje: "Error al asignar el docente. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Grupo eliminado por otro usuario
Si entre FP4 y FP15 otro Director eliminó el grupo, el sistema muestra mensaje: "El grupo ya no existe. Fue eliminado por otro usuario", recarga la lista y el flujo termina.

### Postcondiciones
- Docente asignado al grupo en la base de datos
- Docente puede ver el grupo en su panel de docente

<img width="500" height="240" alt="Image" src="https://github.com/user-attachments/assets/dfc8d18b-dddf-4b64-baa9-66d828c296f6" />

---

## CU-29: ASIGNAR AULA Y HORARIO A GRUPO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-29

### Descripción
El Director asigna un aula y uno o más bloques de horario a un grupo/paralelo.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir al menos un grupo registrado
- Deben existir aulas registradas en el sistema
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Grupos"

**FP2:** El sistema muestra la lista de grupos

**FP3:** El Director identifica el grupo al que desea asignar horarios

**FP4:** El Director hace clic en "Asignar Horarios" del grupo seleccionado

**FP5:** El sistema muestra interfaz de asignación con:
- Grupo: Materia - Paralelo (informativo)
- Cupo: N estudiantes (informativo)
- Docente: Nombre o "Sin asignar" (informativo)
- Selector de Aula (dropdown)
- Horarios actuales: Lista o "Sin horarios"
- Botón "+ Agregar Bloque de Horario"
- Calendario semanal visual (Lu-Sa, 7:00-21:00)
- Botón "Guardar Horarios"
- Botón "Cancelar"

**FP6:** El Director selecciona un aula del dropdown

**FP7:** El sistema muestra información del aula:
- Capacidad: N personas
- Tipo: Aula/Laboratorio/Auditorio
- Edificio: Nombre
- Estado: Disponible/En Mantenimiento

**FP8:** El sistema valida que capacidad del aula ≥ cupo del grupo

**FP9:** El Director hace clic en "+ Agregar Bloque de Horario"

**FP10:** El sistema muestra formulario de bloque:
- Día: Lunes-Sábado (dropdown)
- Hora inicio: HH:MM (dropdown 7:00-21:00)
- Hora fin: HH:MM (dropdown 7:00-21:00)
- Botón "Agregar"

**FP11:** El Director selecciona día, hora inicio y hora fin

**FP12:** El sistema valida:
- Hora fin > Hora inicio
- Bloque no se solape con horarios existentes del grupo
- Aula disponible en ese bloque
- Docente disponible (si asignado)

**FP13:** El sistema agrega el bloque a la lista temporal

**FP14:** El sistema muestra el bloque en el calendario visual

**FP15:** El Director repite FP9-FP14 para agregar más bloques (si desea)

**FP16:** El Director revisa todos los bloques agregados

**FP17:** El Director hace clic en "Guardar Horarios"

**FP18:** El sistema valida que haya al menos un bloque

**FP19:** El sistema guarda todos los bloques en la base de datos

**FP20:** El sistema actualiza la disponibilidad del aula

**FP21:** El sistema muestra mensaje: "Horarios asignados exitosamente"

**FP22:** El sistema cierra el modal

**FP23:** El sistema actualiza la lista mostrando los horarios

### Subflujos

#### SF1: Hora fin ≤ Hora inicio (Paso 12 → Paso 11)
En FP12, si la hora fin no es posterior a hora inicio, el sistema muestra mensaje: "La hora de fin debe ser posterior a la hora de inicio" y retorna al Paso 11.

#### SF2: Conflicto de aula detectado (Paso 12 → Paso 11)
En FP12, si el aula está ocupada en ese horario, el sistema muestra detalles del conflicto: "Conflicto de aula: Materia usa esta aula en este horario". El Director debe ajustar el horario o elegir otra aula, retornando al Paso 11.

#### SF3: Conflicto de docente detectado (Paso 12 → Paso 11)
En FP12, si el docente tiene clase en ese horario, el sistema muestra advertencia con el grupo conflictivo: "Conflicto de docente: Ya tiene clase en este horario". El Director puede confirmar (permite sobrecarga) o ajustar, retornando al Paso 11.

#### SF4: Sin bloques agregados (Paso 18 → Paso 9)
En FP18, si no se agregó ningún bloque, el sistema muestra mensaje: "Debes agregar al menos un bloque de horario" y retorna al Paso 9.

#### SF5: Eliminar Bloque temporal (Paso 13-16 → Paso 16)
Después de agregar bloques, el Director puede hacer clic en "X" de un bloque agregado, el sistema remueve el bloque de la lista temporal, actualiza el calendario visual y retorna al Paso 16.

### Extensiones

#### EX1: Ver Disponibilidad del Aula
En cualquier momento, el Director puede hacer clic en "Ver Disponibilidad" del aula, el sistema muestra calendario semanal con bloques ocupados, el Director identifica espacios libres.

### Inclusiones

#### IN2: Obtener datos del grupo
En FP5, el sistema ejecuta consulta para obtener datos del grupo: materia, paralelo, cupo, docente, horarios actuales.

#### IN3: Cargar lista de aulas disponibles
En FP6, el sistema obtiene todas las aulas registradas con capacidad, tipo, edificio y estado.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al guardar horarios
En FP19, si ocurre un error al guardar los horarios en la base de datos, el sistema muestra mensaje: "Error al guardar los horarios. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Grupo eliminado por otro usuario
Si entre FP4 y FP19 otro Director eliminó el grupo, el sistema muestra mensaje: "El grupo ya no existe. Fue eliminado por otro usuario", recarga la lista y el flujo termina.

### Postcondiciones
- Aula y horarios asignados al grupo en la base de datos
- Disponibilidad del aula actualizada (bloques marcados como ocupados)
- Horarios visibles en oferta académica (CU-5) para estudiantes

<img width="497" height="273" alt="Image" src="https://github.com/user-attachments/assets/9293340c-545c-4b44-bf03-6cae8e20867c" />

---

## CU-30: ELIMINAR GRUPO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-30

### Descripción
El Director elimina un grupo/paralelo que no tiene inscripciones aceptadas.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El grupo NO debe tener inscripciones en estado ACEPTADA
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Grupos"

**FP2:** El sistema muestra la lista de grupos

**FP3:** El Director identifica el grupo que desea eliminar

**FP4:** El Director hace clic en "Eliminar" del grupo seleccionado

**FP5:** El sistema verifica inscripciones:
- Cuenta inscripciones ACEPTADAS
- Cuenta inscripciones PENDIENTES
- Cuenta inscripciones RECHAZADAS

**FP6:** Si NO hay inscripciones ACEPTADAS, el sistema muestra modal de confirmación:
- Grupo a eliminar: Materia - Paralelo
- Gestión: Código
- Docente asignado: Nombre o "Sin asignar"
- Horarios: Lista o "Sin horarios"
- Inscripciones pendientes: N (advertencia si >0)
- Advertencia: "Esta acción es permanente"
- Campo de confirmación: "Escribe ELIMINAR para confirmar"
- Botón "Eliminar Grupo" (deshabilitado)
- Botón "Cancelar"

**FP7:** Si hay inscripciones PENDIENTES, muestra advertencia adicional: "Hay N solicitudes pendientes que serán rechazadas automáticamente"

**FP8:** El Director escribe "ELIMINAR" en el campo

**FP9:** El sistema valida el texto

**FP10:** El botón "Eliminar Grupo" se habilita

**FP11:** El Director hace clic en "Eliminar Grupo"

**FP12:** Si hay inscripciones PENDIENTES, el sistema las cambia a RECHAZADAS con motivo "Grupo eliminado"

**FP13:** El sistema desvincula al docente (si estaba asignado)

**FP14:** El sistema libera los horarios del aula

**FP15:** El sistema elimina el registro del grupo de la base de datos

**FP16:** El sistema muestra mensaje: "Grupo eliminado exitosamente"

**FP17:** El sistema actualiza la lista removiendo el grupo

### Subflujos

#### SF1: Texto de confirmación incorrecto (Paso 9 → Paso 8)
En FP9, si el texto no es exactamente "ELIMINAR", el botón permanece deshabilitado, el sistema muestra mensaje: "Debes escribir ELIMINAR (mayúsculas)" y retorna al Paso 8.

#### SF2: Rechazar Inscripciones Pendientes automáticamente (Paso 12 → Paso 13)
En FP12, el sistema itera inscripciones PENDIENTES, cambia estado a RECHAZADA, asigna motivo: "El paralelo fue eliminado por la dirección", envía notificación a cada estudiante y continúa al Paso 13.

### Extensiones
Ninguno

### Inclusiones

#### IN1: Obtener grupos
Obtener grupos del cubo comercial

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al eliminar grupo
En FP15, si ocurre un error al eliminar el grupo de la base de datos, el sistema muestra mensaje: "Error al eliminar el grupo. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Grupo modificado por otro usuario
Si entre FP4 y FP15 otro Director modificó el grupo (agregó inscripciones aceptadas), el sistema muestra mensaje: "El grupo ha sido modificado. Ya tiene estudiantes inscritos", recarga la lista y el flujo termina.

### Postcondiciones
- Grupo eliminado permanentemente de la base de datos

<img width="455" height="188" alt="Image" src="https://github.com/user-attachments/assets/601ee364-217a-4a5c-8d58-90ced9681990" />

---

## CU-31: CONSULTAR GRUPOS

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-31

### Descripción
El Director visualiza y filtra todos los grupos/paralelos creados en el sistema.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Grupos" desde el menú principal

**FP2:** El sistema obtiene todos los grupos de la carrera

**FP3:** El sistema muestra la página con:
- Título: "Grupos y Paralelos"
- Filtros:
  * Por gestión/período (dropdown)
  * Por materia (selector)
  * Por docente (selector)
  * Por aula (selector)
  * Por estado (ABIERTO/CERRADO)
- Campo de búsqueda
- Tabla de grupos con columnas:
  * Gestión
  * Materia
  * Paralelo
  * Cupo (ocupado/total)
  * Docente
  * Horarios (resumen)
  * Aula
  * Estado (badge)
  * Acciones (Ver, Editar, Asignar Docente, Asignar Horarios, Eliminar)
- Estadísticas:
  * Total de grupos: N
  * Grupos de la gestión actual: N
  * % Ocupación promedio: N%
  * Grupos sin docente: N
  * Grupos sin horarios: N
- Botón "Crear Nuevo Grupo"
- Botón "Exportar Lista" (Excel)

**FP4:** El Director visualiza la lista completa

### Subflujos

#### SF1: Filtrar por Gestión (Paso 3 → Paso 4)
El Director selecciona una gestión del filtro, el sistema filtra mostrando solo grupos de esa gestión, actualiza las estadísticas y retorna al Paso 4.

#### SF2: Filtrar por Materia (Paso 3 → Paso 4)
El Director selecciona una materia, el sistema filtra mostrando todos los paralelos de esa materia, ordena por gestión y paralelo y retorna al Paso 4.

#### SF3: Buscar Grupo (Paso 3 → Paso 4)
El Director escribe en el campo de búsqueda, el sistema filtra en tiempo real por materia, docente o aula, muestra coincidencias y retorna al Paso 4.

#### SF4: Ordenar Tabla (Paso 3 → Paso 4)
El Director hace clic en una columna, el sistema ordena ascendente/descendente y retorna al Paso 4.

#### SF5: Sin resultados de búsqueda (Paso 3 → Paso 4)
Si la búsqueda no arroja resultados, el sistema muestra mensaje: "No se encontraron grupos", sugiere ajustar filtros y retorna al Paso 4.

### Extensiones

#### EX1: Ver Detalle de Grupo
En cualquier momento, el Director puede hacer clic en "Ver" de un grupo, el sistema muestra modal con información completa (información del grupo, lista de estudiantes inscritos ACEPTADOS, horarios completos en calendario semanal, docente con contacto, calificaciones registradas en resumen), el Director visualiza toda la información y cierra el modal.

### Inclusiones

#### IN2: Obtener grupos de la carrera
En FP2, el sistema ejecuta consulta para obtener todos los grupos con gestión, materia, paralelo, cupo, docente, horarios, aula y estado.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

### Postcondiciones
- Director visualiza todos los grupos organizados por gestión, materia y paralelo
- Información filtrable por gestión, materia, docente, aula y estado
- Información ordenable por cualquier columna

<img width="497" height="217" alt="Image" src="https://github.com/user-attachments/assets/e1266ef7-cc9f-411b-b033-a71bf681e88f" />

---

# 8. ESTUDIANTES - DIRECTOR

---

## CU-32: CREAR ESTUDIANTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-32

### Descripción
El Director registra un nuevo estudiante en el sistema, asignándole código y credenciales de acceso.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Deben existir carreras registradas en el sistema
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Estudiantes" desde el menú

**FP2:** El Director hace clic en "Crear Nuevo Estudiante"

**FP3:** El sistema muestra formulario con campos:
- Email: (obligatorio)
- Nombres: (obligatorio)
- Apellido Paterno: (obligatorio)
- Apellido Materno: (opcional)
- Carrera: (dropdown obligatorio)
- Teléfono: (opcional)
- Dirección: (opcional)
- Código: Auto-generado (solo lectura)
- Contraseña: Auto-generada (solo lectura)
- Botón "Registrar Estudiante"
- Botón "Cancelar"

**FP4:** El Director ingresa el email del estudiante

**FP5:** El sistema valida que el email tenga formato válido

**FP6:** El sistema valida que el email no esté registrado

**FP7:** El Director ingresa nombres y apellidos

**FP8:** El Director selecciona la carrera del dropdown

**FP9:** El Director ingresa teléfono y dirección (opcional)

**FP10:** El sistema genera automáticamente:
- Código: EST-YYYY-NNNN (año actual + consecutivo)
- Contraseña temporal: 8 caracteres aleatorios

**FP11:** El sistema muestra el código y contraseña generados

**FP12:** El Director hace clic en "Registrar Estudiante"

**FP13:** El sistema valida todos los campos obligatorios

**FP14:** El sistema crea el registro del estudiante con:
- Email
- Nombres y apellidos
- Código generado
- Contraseña encriptada
- Carrera
- Teléfono, dirección
- Rol: ESTUDIANTE
- Estado: ACTIVO
- Fecha de registro

**FP15:** El sistema envía email al estudiante con:
- Asunto: "Bienvenido a Universidad"
- Código de estudiante
- Contraseña temporal
- Link para iniciar sesión
- Instrucción: "Cambia tu contraseña al primer ingreso"

**FP16:** El sistema muestra modal de confirmación:
- "Estudiante registrado exitosamente"
- Código: EST-YYYY-NNNN
- Contraseña temporal: XXXXXXXX
- Botón "Copiar Credenciales"
- Botón "Cerrar"

**FP17:** El Director puede copiar las credenciales

**FP18:** El sistema cierra el modal

**FP19:** El sistema actualiza la lista mostrando el nuevo estudiante

### Subflujos

#### SF1: Email inválido (Paso 5 → Paso 4)
En FP5, si el email no tiene formato válido, el sistema muestra mensaje: "El email no es válido", marca el campo en rojo y retorna al Paso 4.

#### SF2: Email ya registrado (Paso 6 → Paso 4)
En FP6, si el email ya existe en el sistema, el sistema muestra mensaje: "El email ya está registrado", sugiere usar otro email y retorna al Paso 4.

#### SF3: Campos obligatorios vacíos (Paso 13 → Paso 4)
En FP13, si faltan campos obligatorios, el sistema muestra mensaje: "Completa todos los campos obligatorios", marca los campos faltantes en rojo y retorna al Paso 4.

#### SF4: Generar Código Consecutivo (Paso 10)
El sistema obtiene el año actual (YYYY), busca el último código EST-YYYY-**** del año. Si no existe, inicia en EST-YYYY-0001. Si existe, incrementa en 1: EST-YYYY-0002, etc. El código se muestra en el formulario.

#### SF5: Generar Contraseña Temporal (Paso 10)
El sistema genera string aleatorio de 8 caracteres incluyendo mayúsculas, minúsculas y números. La contraseña se muestra en el formulario.

### Extensiones
Ninguno

### Inclusiones
#### IN1: Obtener estudiantes
Se obtienen los datos de los estudiantes del cubo comercial

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al registrar estudiante
En FP14, si ocurre un error al guardar el estudiante en la base de datos, el sistema muestra mensaje: "Error al registrar el estudiante. Intente nuevamente", registra el error en logs y el flujo termina.

### Postcondiciones
- Nuevo estudiante registrado en el sistema
- Código único asignado en formato EST-YYYY-NNNN

<img width="469" height="191" alt="Image" src="https://github.com/user-attachments/assets/296dde11-1e52-4f7c-9ee7-e1a35e45a33c" />

---

## CU-33: EDITAR ESTUDIANTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-33

### Descripción
El Director modifica la información personal y académica de un estudiante existente.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir al menos un estudiante registrado en el sistema
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Estudiantes"

**FP2:** El sistema muestra la lista de estudiantes

**FP3:** El Director identifica al estudiante que desea editar

**FP4:** El Director hace clic en "Editar" del estudiante seleccionado

**FP5:** El sistema muestra formulario modal con datos actuales:
- Código: EST-YYYY-NNNN (no editable, solo lectura)
- Email: valor (no editable, solo lectura)
- Nombres: valor (editable)
- Apellido Paterno: valor (editable)
- Apellido Materno: valor (editable)
- Carrera: valor (editable, dropdown)
- Teléfono: valor (editable)
- Dirección: valor (editable)
- Estado: badge informativo, no editable aquí
- Botón "Guardar Cambios"
- Botón "Cancelar"

**FP6:** El Director modifica nombres (si desea)

**FP7:** El Director modifica apellidos (si desea)

**FP8:** El Director cambia la carrera (si desea)

**FP9:** Si cambia carrera, el sistema muestra advertencia: "Cambiar la carrera puede afectar las inscripciones actuales"

**FP10:** El Director confirma el cambio (si aplica)

**FP11:** El Director modifica teléfono y dirección (si desea)

**FP12:** El Director hace clic en "Guardar Cambios"

**FP13:** El sistema valida que los campos obligatorios no estén vacíos

**FP14:** Si cambió la carrera, el sistema valida que exista

**FP15:** El sistema actualiza el registro del estudiante

**FP16:** El sistema muestra mensaje: "Estudiante actualizado exitosamente"

**FP17:** El sistema cierra el modal

**FP18:** El sistema actualiza la lista mostrando los cambios

### Subflujos

#### SF1: Nombres vacíos (Paso 13 → Paso 6)
En FP13, si el campo nombres está vacío, el sistema muestra mensaje: "El nombre es obligatorio", marca el campo en rojo y retorna al Paso 6.

#### SF2: Apellido paterno vacío (Paso 13 → Paso 7)
En FP13, si el apellido paterno está vacío, el sistema muestra mensaje: "El apellido paterno es obligatorio", marca el campo en rojo y retorna al Paso 7.

#### SF3: Carrera no válida (Paso 14 → Paso 8)
En FP14, si la carrera seleccionada no existe en el sistema, el sistema muestra mensaje: "Selecciona una carrera válida" y retorna al Paso 8.

#### SF4: Sin cambios detectados (Paso 12 → Paso 17)
En FP12, si no se modificó ningún campo, el sistema muestra mensaje: "No se realizaron cambios", cierra el modal y el flujo continúa en Paso 17.

### Extensiones
Ninguno

### Inclusiones

#### IN2: Obtener datos actuales del estudiante
En FP5, el sistema consulta la base de datos para cargar todos los datos actuales del estudiante seleccionado.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al actualizar estudiante
En FP15, si ocurre un error al guardar los cambios en la base de datos, el sistema muestra mensaje: "Error al actualizar el estudiante. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Estudiante eliminado por otro usuario
En FP15, si otro Director eliminó al estudiante mientras se editaba, el sistema muestra mensaje: "El estudiante ya no existe en el sistema", cierra el modal y actualiza la lista.

### Postcondiciones
- Estudiante actualizado en el sistema con nuevos datos
- Cambios reflejados inmediatamente en todas las vistas

<img width="467" height="189" alt="Image" src="https://github.com/user-attachments/assets/210f22b8-ca25-456d-a0b8-03888098192a" />

---

## CU-34: CAMBIAR ESTADO DE ESTUDIANTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-34

### Descripción
El Director cambia el estado académico de un estudiante (Activo, Inactivo, Egresado, Retirado).

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir el estudiante a modificar en el sistema
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Estudiantes"

**FP2:** El sistema muestra la lista de estudiantes

**FP3:** El Director identifica al estudiante que desea cambiar estado

**FP4:** El Director hace clic en "Cambiar Estado" del estudiante

**FP5:** El sistema muestra modal con:
- Estudiante: Código - Nombre completo
- Estado actual: Badge con color
- Nuevo estado: (dropdown)
  * ACTIVO
  * INACTIVO
  * EGRESADO
  * RETIRADO
- Campo "Motivo": (visible si selecciona INACTIVO/RETIRADO)
- Inscripciones activas: N (advertencia si >0)
- Botón "Cambiar Estado"
- Botón "Cancelar"

**FP6:** El Director selecciona el nuevo estado

**FP7:** Si selecciona INACTIVO o RETIRADO, el sistema muestra campo obligatorio "Motivo"

**FP8:** Si hay inscripciones activas y selecciona INACTIVO/RETIRADO, muestra advertencia: "El estudiante tiene N inscripciones activas"

**FP9:** El Director ingresa el motivo (si aplica)

**FP10:** El Director hace clic en "Cambiar Estado"

**FP11:** El sistema valida:
- Nuevo estado seleccionado
- Motivo obligatorio si es INACTIVO/RETIRADO

**FP12:** Si cambia a INACTIVO o RETIRADO con inscripciones activas:
- El sistema muestra confirmación: "Esto cancelará N inscripciones. ¿Continuar?"
- El Director confirma

**FP13:** El sistema actualiza el estado del estudiante

**FP14:** Si cambió a INACTIVO/RETIRADO, el sistema:
- Cancela inscripciones PENDIENTES (cambia a RECHAZADA)
- Mantiene inscripciones ACEPTADAS pero bloquea acceso

**FP15:** El sistema registra:
- Nuevo estado
- Motivo (si aplica)
- Fecha del cambio
- Director que realizó el cambio

**FP16:** Si cambió a INACTIVO, el sistema bloquea el inicio de sesión

**FP17:** El sistema envía notificación al estudiante informando el cambio

**FP18:** El sistema muestra mensaje: "Estado actualizado exitosamente"

**FP19:** El sistema cierra el modal

**FP20:** El sistema actualiza la lista mostrando el nuevo estado

### Subflujos

#### SF1: Mismo estado seleccionado (Paso 11 → Paso 6)
En FP11, si el nuevo estado es igual al estado actual, el sistema muestra mensaje: "El estudiante ya tiene este estado" y retorna al Paso 6.

#### SF2: Motivo vacío para INACTIVO/RETIRADO (Paso 11 → Paso 9)
En FP11, si se seleccionó INACTIVO o RETIRADO sin ingresar motivo, el sistema muestra mensaje: "El motivo es obligatorio para este estado", marca el campo en rojo y retorna al Paso 9.

#### SF3: Cancelar por inscripciones afectadas (Paso 12 → Paso 19)
En FP12, si el Director cancela al ver las N inscripciones que se cancelarán, el sistema descarta el cambio, cierra el modal y el flujo continúa en Paso 19.

### Extensiones
Ninguno

### Inclusiones

#### IN2: Obtener estado e informacion actual del estudiante
En FP5, el sistema consulta el estado actual y cuenta las inscripciones activas del estudiante.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al cambiar estado
En FP13, si ocurre un error al actualizar el estado en la base de datos, el sistema muestra mensaje: "Error al cambiar el estado. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Estudiante eliminado por otro usuario
En FP13, si otro Director eliminó al estudiante mientras se cambiaba el estado, el sistema muestra mensaje: "El estudiante ya no existe en el sistema", cierra el modal y actualiza la lista.

### Postcondiciones
- Estado del estudiante actualizado en el sistema
- Cambio reflejado inmediatamente en todas las vistas

<img width="506" height="213" alt="Image" src="https://github.com/user-attachments/assets/985525b5-d117-4a90-be46-900fc9ea9f80" />

---

## CU-35: ELIMINAR ESTUDIANTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-35

### Descripción
El Director elimina el registro de un estudiante que nunca tuvo actividad académica.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El estudiante NO debe tener inscripciones registradas (actuales o históricas)
- El estudiante NO debe tener calificaciones registradas
- El estudiante NO debe tener historial académico
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Estudiantes"

**FP2:** El sistema muestra la lista de estudiantes

**FP3:** El Director identifica al estudiante que desea eliminar

**FP4:** El Director hace clic en "Eliminar" del estudiante seleccionado

**FP5:** El sistema verifica que el estudiante NO tenga:
- Inscripciones (cualquier estado)
- Calificaciones
- Historial académico

**FP6:** Si NO tiene registros, el sistema muestra modal de confirmación:
- Estudiante a eliminar: Código - Nombre completo
- Email: valor
- Carrera: nombre
- Estado: badge
- Advertencia: "Esta acción es permanente y no se puede deshacer"
- Campo de texto: "Escribe el código del estudiante para confirmar"
- Botón "Eliminar Definitivamente" (deshabilitado)
- Botón "Cancelar"

**FP7:** El Director escribe el código del estudiante

**FP8:** El sistema valida que el código coincida

**FP9:** El botón "Eliminar Definitivamente" se habilita

**FP10:** El Director hace clic en "Eliminar Definitivamente"

**FP11:** El sistema elimina el registro del estudiante de la base de datos

**FP12:** El sistema muestra mensaje: "Estudiante código eliminado exitosamente"

**FP13:** El sistema actualiza la lista removiendo al estudiante

### Subflujos

#### SF1: Código incorrecto (Paso 8 → Paso 7)
En FP8, si el código ingresado no coincide con el código del estudiante, el botón "Eliminar Definitivamente" permanece deshabilitado, el sistema muestra mensaje: "El código no coincide" y retorna al Paso 7.

### Extensiones
Ninguno

### Inclusiones

#### IN1: Obtener informacion del estudiante
Obtener datos del estudiante del cubo comercial

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al eliminar estudiante
En FP11, si ocurre un error al eliminar el registro de la base de datos, el sistema muestra mensaje: "Error al eliminar el estudiante. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Estudiante eliminado por otro usuario
En FP11, si otro Director ya eliminó al estudiante, el sistema muestra mensaje: "El estudiante ya no existe en el sistema", cierra el modal y actualiza la lista.

### Postcondiciones
- Estudiante eliminado permanentemente del sistema
- Registro removido de la base de datos

<img width="498" height="204" alt="Image" src="https://github.com/user-attachments/assets/c636f385-3c3c-48ae-973b-8ad0673083fb" />

---

## CU-36: CONSULTAR ESTUDIANTES

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-36

### Descripción
El Director visualiza y filtra todos los estudiantes registrados en el sistema, con información académica resumida.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Estudiantes" desde el menú principal

**FP2:** El sistema obtiene todos los estudiantes de la carrera

**FP3:** El sistema muestra la página con:
- Título: "Gestión de Estudiantes"
- Filtros:
  * Por carrera (dropdown)
  * Por estado (ACTIVO/INACTIVO/EGRESADO/RETIRADO)
  * Por semestre/nivel actual
- Campo de búsqueda por código, nombre o email
- Tabla de estudiantes con columnas:
  * Código
  * Nombre completo
  * Email
  * Carrera
  * Promedio general
  * Créditos aprobados
  * Estado (badge con color)
  * Acciones (Ver, Editar, Cambiar Estado, Eliminar)
- Estadísticas:
  * Total de estudiantes: N
  * Por estado (Activos: N, Inactivos: N, etc.)
  * Promedio general de la carrera: N
  * Estudiantes por semestre
- Botón "Crear Nuevo Estudiante"
- Botón "Importar desde Excel"
- Botón "Exportar Lista" (Excel)

**FP4:** El Director visualiza la lista completa

### Subflujos

#### SF1: Filtrar por Carrera (Paso 3 → Paso 4)
En FP3, el Director selecciona una carrera del filtro, el sistema filtra mostrando solo estudiantes de esa carrera, actualiza las estadísticas y retorna al Paso 4.

#### SF2: Filtrar por Estado (Paso 3 → Paso 4)
En FP3, el Director selecciona un estado (ACTIVO/INACTIVO/EGRESADO/RETIRADO), el sistema filtra mostrando solo estudiantes con ese estado, actualiza los contadores y retorna al Paso 4.

#### SF3: Buscar Estudiante (Paso 3 → Paso 4)
En FP3, el Director escribe en el campo de búsqueda, el sistema filtra en tiempo real por código, nombre o email, muestra coincidencias y retorna al Paso 4.

#### SF4: Ordenar Tabla (Paso 3 → Paso 4)
En FP3, el Director hace clic en una columna de la tabla, el sistema ordena ascendente o descendente y retorna al Paso 4.

#### SF5: Sin resultados de búsqueda (Paso 3 → Paso 4)
En SF3, si la búsqueda no arroja resultados, el sistema muestra mensaje: "No se encontraron estudiantes", sugiere ajustar filtros y retorna al Paso 4.

### Extensiones

#### EX2: Ver Detalle de Estudiante
El Director puede opcionalmente hacer clic en "Ver" de un estudiante, el sistema muestra modal con información completa (datos personales, información académica con promedio/créditos/nivel, inscripciones actuales, historial académico resumido, calificaciones, botones de acción rápida), el Director visualiza toda la información y cierra el modal.

### Inclusiones

#### IN2: Obtener todos los estudiantes
En FP2, el sistema consulta la base de datos para obtener todos los estudiantes registrados en la carrera del Director.

#### IN4: Aplicar filtros seleccionados
Si hay filtros activos, el sistema aplica filtros de carrera, estado y búsqueda para mostrar solo estudiantes que coincidan.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

### Postcondiciones
- Director visualiza todos los estudiantes organizados en tabla
- Información de cada estudiante visible (código, nombre, email, carrera, promedio, créditos, estado)
- Filtros aplicables por carrera, estado y búsqueda

<img width="494" height="282" alt="Image" src="https://github.com/user-attachments/assets/f88e8d34-91a9-4eee-a545-f1cbba491fe5" />

---

# 9. DOCENTES - DIRECTOR

---

## CU-37: CREAR DOCENTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-37

### Descripción
El Director registra un nuevo docente en el sistema, asignándole código, credenciales y especialidades.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Deben existir departamentos registrados en el sistema
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Docentes" desde el menú

**FP2:** El Director hace clic en "Crear Nuevo Docente"

**FP3:** El sistema muestra formulario con campos:
- Email: (obligatorio)
- Nombres: (obligatorio)
- Apellido Paterno: (obligatorio)
- Apellido Materno: (opcional)
- Especialidades: (selector múltiple obligatorio)
- Departamento: (dropdown obligatorio)
- Grado Académico: (dropdown obligatorio)
  * Licenciatura
  * Maestría
  * Doctorado
- Teléfono: (opcional)
- Oficina: (opcional)
- Código: Auto-generado (solo lectura)
- Contraseña: Auto-generada (solo lectura)
- Botón "Registrar Docente"
- Botón "Cancelar"

**FP4:** El Director ingresa el email del docente

**FP5:** El sistema valida que el email tenga formato válido

**FP6:** El sistema valida que el email no esté registrado

**FP7:** El Director ingresa nombres y apellidos

**FP8:** El Director selecciona una o más especialidades

**FP9:** El sistema permite agregar múltiples especialidades:
- Puede escribir texto libre o seleccionar predefinidas
- Muestra badges por cada especialidad agregada

**FP10:** El Director selecciona el departamento del dropdown

**FP11:** El Director selecciona el grado académico

**FP12:** El Director ingresa teléfono y oficina (opcional)

**FP13:** El sistema genera automáticamente:
- Código: DOC-YYYY-NNNN (año actual + consecutivo)
- Contraseña temporal: 8 caracteres aleatorios

**FP14:** El sistema muestra el código y contraseña generados

**FP15:** El Director hace clic en "Registrar Docente"

**FP16:** El sistema valida todos los campos obligatorios

**FP17:** El sistema crea el registro del docente con:
- Email
- Nombres y apellidos
- Código generado
- Contraseña encriptada
- Especialidades (array)
- Departamento
- Grado académico
- Teléfono, oficina
- Rol: DOCENTE
- Estado: ACTIVO
- Fecha de registro

**FP18:** El sistema envía email al docente con:
- Asunto: "Bienvenido como Docente - Universidad"
- Código de docente
- Contraseña temporal
- Link para iniciar sesión
- Instrucción: "Cambia tu contraseña al primer ingreso"

**FP19:** El sistema muestra modal de confirmación:
- "Docente registrado exitosamente"
- Código: DOC-YYYY-NNNN
- Contraseña temporal: XXXXXXXX
- Botón "Copiar Credenciales"
- Botón "Cerrar"

**FP20:** El Director puede copiar las credenciales

**FP21:** El sistema cierra el modal

**FP22:** El sistema actualiza la lista mostrando el nuevo docente

### Subflujos

#### SF1: Email inválido (Paso 5 → Paso 4)
En FP5, si el email no tiene formato válido, el sistema muestra mensaje: "El email no es válido", marca el campo en rojo y retorna al Paso 4.

#### SF2: Email ya registrado (Paso 6 → Paso 4)
En FP6, si el email ya existe en el sistema, el sistema muestra mensaje: "El email ya está registrado", sugiere usar otro email y retorna al Paso 4.

#### SF3: Sin especialidades (Paso 16 → Paso 8)
En FP16, si no se agregó ninguna especialidad, el sistema muestra mensaje: "Debes agregar al menos una especialidad", marca el campo en rojo y retorna al Paso 8.

#### SF4: Campos obligatorios vacíos (Paso 16 → Paso correspondiente)
En FP16, si faltan campos obligatorios (nombres, apellido paterno, departamento, grado académico), el sistema muestra mensaje: "Completa todos los campos obligatorios", marca los campos faltantes en rojo y retorna al paso correspondiente.

#### SF5: Generar Código Consecutivo (Paso 13)
El sistema obtiene el año actual (YYYY), busca el último código DOC-YYYY-**** del año. Si no existe, inicia en DOC-YYYY-0001. Si existe, incrementa en 1: DOC-YYYY-0002, etc. El código se muestra en el formulario.

#### SF6: Agregar Especialidades (Paso 8-9)
El Director hace clic en "+ Agregar Especialidad", el sistema muestra dropdown/autocomplete con sugerencias predefinidas, el Director puede seleccionar o escribir texto libre, el sistema agrega la especialidad como badge. El Director puede repetir para agregar más.

#### SF7: Quitar Especialidad (Paso 8-9)
El Director hace clic en "X" de un badge de especialidad, el sistema remueve la especialidad de la lista.

### Extensiones
Ninguno

### Inclusiones

#### IN1: Obtener todos los docentes
Obtener los docentes del cubo comercial

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al registrar docente
En FP17, si ocurre un error al guardar el docente en la base de datos, el sistema muestra mensaje: "Error al registrar el docente. Intente nuevamente", registra el error en logs y el flujo termina.

### Postcondiciones
- Nuevo docente registrado en el sistema
- Código único asignado en formato DOC-YYYY-NNNN

<img width="494" height="212" alt="Image" src="https://github.com/user-attachments/assets/436fcd4f-155f-4708-8665-1b5539953494" />

---

## CU-38: EDITAR DOCENTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-38

### Descripción
El Director modifica la información personal, académica y especialidades de un docente existente.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir al menos un docente registrado en el sistema
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Docentes"

**FP2:** El sistema muestra la lista de docentes

**FP3:** El Director identifica al docente que desea editar

**FP4:** El Director hace clic en "Editar" del docente seleccionado

**FP5:** El sistema muestra formulario modal con datos actuales:
- Código: DOC-YYYY-NNNN (no editable, solo lectura)
- Email: valor (no editable, solo lectura)
- Nombres: valor (editable)
- Apellido Paterno: valor (editable)
- Apellido Materno: valor (editable)
- Especialidades: lista actual con badges (editable)
- Departamento: valor (editable, dropdown)
- Grado Académico: valor (editable, dropdown)
- Teléfono: valor (editable)
- Oficina: valor (editable)
- Carga horaria actual: N horas/semana (informativo)
- Grupos asignados: N (informativo)
- Botón "Guardar Cambios"
- Botón "Cancelar"

**FP6:** El Director modifica nombres (si desea)

**FP7:** El Director modifica apellidos (si desea)

**FP8:** El Director modifica las especialidades:
- Puede agregar nuevas especialidades
- Puede quitar especialidades existentes
- Debe mantener al menos una

**FP9:** El Director cambia el departamento (si desea)

**FP10:** El Director cambia el grado académico (si desea)

**FP11:** El Director modifica teléfono y oficina (si desea)

**FP12:** El Director hace clic en "Guardar Cambios"

**FP13:** El sistema valida que los campos obligatorios no estén vacíos

**FP14:** El sistema valida que haya al menos una especialidad

**FP15:** El sistema valida que el departamento exista

**FP16:** El sistema actualiza el registro del docente

**FP17:** El sistema muestra mensaje: "Docente actualizado exitosamente"

**FP18:** El sistema cierra el modal

**FP19:** El sistema actualiza la lista mostrando los cambios

### Subflujos

#### SF1: Nombres vacíos (Paso 13 → Paso 6)
En FP13, si el campo nombres está vacío, el sistema muestra mensaje: "El nombre es obligatorio", marca el campo en rojo y retorna al Paso 6.

#### SF2: Apellido paterno vacío (Paso 13 → Paso 7)
En FP13, si el apellido paterno está vacío, el sistema muestra mensaje: "El apellido paterno es obligatorio", marca el campo en rojo y retorna al Paso 7.

#### SF3: Sin especialidades (Paso 14 → Paso 8)
En FP14, si no queda ninguna especialidad, el sistema muestra mensaje: "Debe tener al menos una especialidad", marca el campo en rojo y retorna al Paso 8.

#### SF4: Departamento inválido (Paso 15 → Paso 9)
En FP15, si el departamento seleccionado no existe en el sistema, el sistema muestra mensaje: "Selecciona un departamento válido" y retorna al Paso 9.

#### SF5: Sin cambios detectados (Paso 12 → Paso 18)
En FP12, si no se modificó ningún campo, el sistema muestra mensaje: "No se realizaron cambios", cierra el modal y el flujo continúa en Paso 18.

#### SF6: Agregar Especialidad (Paso 8)
El Director hace clic en "+ Agregar", el sistema muestra dropdown/autocomplete con especialidades predefinidas, el Director selecciona o escribe texto libre, el sistema agrega el badge a la lista.

#### SF7: Quitar Especialidad (Paso 8)
El Director hace clic en "X" de un badge de especialidad. Si solo queda una, el sistema muestra advertencia: "Debe tener al menos una especialidad" y no remueve. Si hay más de una, el sistema remueve el badge.

### Extensiones
Ninguno

### Inclusiones

#### IN2: Obtener datos actuales del docente
En FP5, el sistema consulta la base de datos para cargar todos los datos actuales del docente seleccionado, incluyendo carga horaria y grupos asignados.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al actualizar docente
En FP16, si ocurre un error al guardar los cambios en la base de datos, el sistema muestra mensaje: "Error al actualizar el docente. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Docente eliminado por otro usuario
En FP16, si otro Director eliminó al docente mientras se editaba, el sistema muestra mensaje: "El docente ya no existe en el sistema", cierra el modal y actualiza la lista.

### Postcondiciones
- Docente actualizado en el sistema con nuevos datos
- Cambios reflejados inmediatamente en todas las vistas

<img width="502" height="155" alt="Image" src="https://github.com/user-attachments/assets/c5710979-4bb1-49f0-8b34-0ecef0fa2d3c" />

---

## CU-39: ELIMINAR DOCENTE

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-39

### Descripción
El Director elimina el registro de un docente que nunca tuvo grupos asignados ni calificaciones registradas.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El docente NO debe tener grupos asignados (actuales o históricos)
- El docente NO debe tener calificaciones registradas
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Docentes"

**FP2:** El sistema muestra la lista de docentes

**FP3:** El Director identifica al docente que desea eliminar

**FP4:** El Director hace clic en "Eliminar" del docente seleccionado

**FP5:** El sistema verifica que el docente NO tenga:
- Grupos asignados (actuales o históricos)
- Calificaciones registradas

**FP6:** Si NO tiene registros, el sistema muestra modal de confirmación:
- Docente a eliminar: Código - Nombre completo
- Email: valor
- Especialidades: lista
- Departamento: nombre
- Advertencia: "Esta acción es permanente y no se puede deshacer"
- Campo de texto: "Escribe el código del docente para confirmar"
- Botón "Eliminar Definitivamente" (deshabilitado)
- Botón "Cancelar"

**FP7:** El Director escribe el código del docente

**FP8:** El sistema valida que el código coincida

**FP9:** El botón "Eliminar Definitivamente" se habilita

**FP10:** El Director hace clic en "Eliminar Definitivamente"

**FP11:** El sistema elimina el registro del docente de la base de datos

**FP12:** El sistema muestra mensaje: "Docente código eliminado exitosamente"

**FP13:** El sistema actualiza la lista removiendo al docente

### Subflujos

#### SF1: Código incorrecto (Paso 8 → Paso 7)
En FP8, si el código ingresado no coincide con el código del docente, el botón "Eliminar Definitivamente" permanece deshabilitado, el sistema muestra mensaje: "El código no coincide" y retorna al Paso 7.

### Extensiones
Ninguno

### Inclusiones

#### IN1: Obtener informacion de los docentes
Obtener datos de los docentes existentes del cubo comercial

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al eliminar docente
En FP11, si ocurre un error al eliminar el registro de la base de datos, el sistema muestra mensaje: "Error al eliminar el docente. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Docente eliminado por otro usuario
En FP11, si otro Director ya eliminó al docente, el sistema muestra mensaje: "El docente ya no existe en el sistema", cierra el modal y actualiza la lista.

### Postcondiciones
- Docente eliminado permanentemente del sistema
- Registro removido 

<img width="445" height="167" alt="Image" src="https://github.com/user-attachments/assets/3384aa1b-48d5-42b0-9e2b-0b9bd5300a9a" />

---

## CU-40: CONSULTAR DOCENTES

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-40

### Descripción
El Director visualiza y filtra todos los docentes registrados en el sistema, con información de carga académica.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Docentes" desde el menú principal

**FP2:** El sistema obtiene todos los docentes

**FP3:** El sistema muestra la página con:
- Título: "Gestión de Docentes"
- Filtros:
  * Por especialidad (selector múltiple)
  * Por departamento (dropdown)
  * Por grado académico (dropdown)
  * Por estado (ACTIVO/INACTIVO)
- Campo de búsqueda por código, nombre o email
- Tabla de docentes con columnas:
  * Código
  * Nombre completo
  * Email
  * Especialidades (badges)
  * Departamento
  * Grado Académico
  * Carga horaria actual (horas/semana)
  * Grupos asignados (N)
  * Estado (badge)
  * Acciones (Ver, Editar, Eliminar)
- Estadísticas:
  * Total de docentes: N
  * Docentes activos: N
  * Promedio carga horaria: N horas/semana
  * Docentes por departamento
  * Docentes por grado académico
- Botón "Crear Nuevo Docente"
- Botón "Exportar Lista" (Excel)

**FP4:** El Director visualiza la lista completa

### Subflujos

#### SF1: Filtrar por Especialidad (Paso 3 → Paso 4)
En FP3, el Director selecciona una o más especialidades, el sistema filtra mostrando docentes con esas especialidades, actualiza las estadísticas y retorna al Paso 4.

#### SF2: Filtrar por Departamento (Paso 3 → Paso 4)
En FP3, el Director selecciona un departamento, el sistema filtra mostrando solo docentes de ese departamento, actualiza los contadores y retorna al Paso 4.

#### SF3: Buscar Docente (Paso 3 → Paso 4)
En FP3, el Director escribe en el campo de búsqueda, el sistema filtra en tiempo real por código, nombre o email, muestra coincidencias y retorna al Paso 4.

#### SF4: Ordenar Tabla (Paso 3 → Paso 4)
En FP3, el Director hace clic en una columna de la tabla, el sistema ordena ascendente o descendente y retorna al Paso 4.

#### SF5: Sin resultados de búsqueda (Paso 3 → Paso 4)
En SF3, si la búsqueda no arroja resultados, el sistema muestra mensaje: "No se encontraron docentes", sugiere ajustar filtros y retorna al Paso 4.

### Extensiones

#### EX3: Ver Detalle de Docente
El Director puede opcionalmente hacer clic en "Ver" de un docente, el sistema muestra modal con información completa (datos personales, especialidades, departamento y grado académico, grupos asignados actualmente con horarios, carga horaria semanal detallada, historial de grupos impartidos, estadísticas con total estudiantes y promedio calificaciones, botones de acción rápida), el Director visualiza toda la información y cierra el modal.

### Inclusiones

#### IN2: Obtener todos los docentes
En FP2, el sistema consulta la base de datos para obtener todos los docentes registrados.

#### IN5: Aplicar filtros seleccionados
Si hay filtros activos, el sistema aplica filtros de especialidad, departamento, grado académico, estado y búsqueda para mostrar solo docentes que coincidan.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

### Postcondiciones
- Director visualiza todos los docentes organizados en tabla
- Información de cada docente visible (código, nombre, email, especialidades, departamento, grado académico, carga horaria, grupos asignados, estado)
- Filtros aplicables por especialidad, departamento, grado académico y estado

<img width="501" height="286" alt="Image" src="https://github.com/user-attachments/assets/6960f0a9-638f-4a10-85b2-83607dea1445" />

---

# 10. AULAS - DIRECTOR

---

## CU-41: CREAR AULA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-41

### Descripción
El Director registra una nueva aula en el sistema para asignación a grupos.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Aulas" desde el menú

**FP2:** El Director hace clic en "Crear Nueva Aula"

**FP3:** El sistema muestra formulario con campos:
- Código: (obligatorio, formato AULA-XXX)
- Edificio: (obligatorio)
- Capacidad: (obligatorio, 10-100)
- Tipo: (dropdown obligatorio)
  * Aula
  * Laboratorio
  * Auditorio
- Piso: (opcional)
- Equipamiento: (opcional, texto libre)
- Disponibilidad: DISPONIBLE (default, no editable aquí)
- Botón "Registrar Aula"
- Botón "Cancelar"

**FP4:** El Director ingresa el código del aula

**FP5:** El sistema valida que el código tenga formato AULA-XXX

**FP6:** El sistema valida que el código no esté registrado

**FP7:** El Director ingresa el edificio

**FP8:** El Director ingresa la capacidad

**FP9:** El sistema valida que la capacidad esté entre 10 y 100

**FP10:** El Director selecciona el tipo de aula

**FP11:** El Director ingresa piso y equipamiento (opcional)

**FP12:** El Director hace clic en "Registrar Aula"

**FP13:** El sistema valida todos los campos obligatorios

**FP14:** El sistema valida el formato del código

**FP15:** El sistema valida el rango de capacidad

**FP16:** El sistema crea el registro del aula con:
- Código
- Edificio
- Capacidad
- Tipo
- Piso
- Equipamiento
- Disponibilidad: DISPONIBLE
- Fecha de registro

**FP17:** El sistema muestra mensaje: "Aula registrada exitosamente"

**FP18:** El sistema cierra el modal

**FP19:** El sistema actualiza la lista mostrando la nueva aula

### Subflujos

#### SF1: Código inválido (Paso 5 → Paso 4)
En FP5, si el código no tiene formato AULA-XXX, el sistema muestra mensaje: "El código debe tener formato AULA-XXX", marca el campo en rojo y retorna al Paso 4.

#### SF2: Código ya registrado (Paso 6 → Paso 4)
En FP6, si el código ya existe en el sistema, el sistema muestra mensaje: "El código de aula ya está registrado" y retorna al Paso 4.

#### SF3: Capacidad fuera de rango (Paso 9 → Paso 8)
En FP9, si la capacidad es menor a 10 o mayor a 100, el sistema muestra mensaje: "La capacidad debe estar entre 10 y 100 personas", marca el campo en rojo y retorna al Paso 8.

#### SF4: Campos obligatorios vacíos (Paso 13 → Paso correspondiente)
En FP13, si faltan campos obligatorios (código, edificio, capacidad, tipo), el sistema muestra mensaje: "Completa todos los campos obligatorios", marca los campos faltantes en rojo y retorna al paso correspondiente.

### Extensiones
Ninguno

### Inclusiones

#### IN1: Obtener datos de las aulas
Obtener informacion de las aulas

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al registrar aula
En FP16, si ocurre un error al guardar el aula en la base de datos, el sistema muestra mensaje: "Error al registrar el aula. Intente nuevamente", registra el error en logs y el flujo termina.

### Postcondiciones
- Nueva aula registrada en el sistema
- Código único asignado con formato AULA-XXX

<img width="501" height="153" alt="Image" src="https://github.com/user-attachments/assets/aef40b5a-941b-45c6-9d46-727f19a5cf86" />

---

## CU-42: EDITAR AULA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-42

### Descripción
El Director modifica la información de un aula existente, incluyendo capacidad, tipo y disponibilidad.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- Debe existir al menos un aula registrada en el sistema
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Aulas"

**FP2:** El sistema muestra la lista de aulas

**FP3:** El Director identifica el aula que desea editar

**FP4:** El Director hace clic en "Editar" del aula seleccionada

**FP5:** El sistema muestra formulario modal con datos actuales:
- Código: AULA-XXX (no editable, solo lectura)
- Edificio: valor (editable)
- Capacidad: valor (editable)
- Tipo: valor (editable, dropdown)
- Piso: valor (editable)
- Equipamiento: valor (editable)
- Disponibilidad: dropdown (editable)
  * DISPONIBLE
  * EN_MANTENIMIENTO
  * FUERA_DE_SERVICIO
- Grupos asignados actualmente: N (informativo)
- Botón "Guardar Cambios"
- Botón "Cancelar"

**FP6:** El Director modifica el edificio (si desea)

**FP7:** El Director modifica la capacidad (si desea)

**FP8:** Si modifica la capacidad, el sistema valida que sea ≥ cupo máximo de grupos asignados

**FP9:** El Director cambia el tipo (si desea)

**FP10:** El Director modifica piso y equipamiento (si desea)

**FP11:** El Director cambia la disponibilidad (si desea)

**FP12:** Si cambia a EN_MANTENIMIENTO o FUERA_DE_SERVICIO, el sistema muestra advertencia: "Esta aula tiene N grupos asignados"

**FP13:** El Director hace clic en "Guardar Cambios"

**FP14:** El sistema valida:
- Capacidad entre 10 y 100
- Capacidad ≥ cupo máximo de grupos asignados

**FP15:** El sistema actualiza el registro del aula

**FP16:** Si cambió a EN_MANTENIMIENTO/FUERA_DE_SERVICIO:
- El sistema bloquea nuevas asignaciones
- Los grupos actuales se mantienen pero con advertencia

**FP17:** El sistema muestra mensaje: "Aula actualizada exitosamente"

**FP18:** El sistema cierra el modal

**FP19:** El sistema actualiza la lista mostrando los cambios

### Subflujos

#### SF1: Capacidad menor que grupos asignados (Paso 14 → Paso 7)
En FP14, si la nueva capacidad es menor que el cupo de grupos asignados, el sistema muestra mensaje: "La capacidad no puede ser menor que el cupo de grupos asignados (N estudiantes)", marca el campo en rojo y retorna al Paso 7.

#### SF2: Capacidad fuera de rango (Paso 14 → Paso 7)
En FP14, si la capacidad es menor a 10 o mayor a 100, el sistema muestra mensaje: "La capacidad debe estar entre 10 y 100", marca el campo en rojo y retorna al Paso 7.

#### SF3: Sin cambios detectados (Paso 13 → Paso 18)
En FP13, si no se modificó ningún campo, el sistema muestra mensaje: "No se realizaron cambios", cierra el modal y el flujo continúa en Paso 18.

#### SF4: Reducir Capacidad (Paso 7-8)
El Director reduce la capacidad, el sistema verifica cupo máximo de grupos asignados. Si nueva capacidad es menor que el cupo, muestra error (ver SF1).

### Extensiones
Ninguno

### Inclusiones

#### IN2: Obtener datos actuales del aula
En FP5, el sistema consulta la base de datos para cargar todos los datos actuales del aula seleccionada y cuenta grupos asignados.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al actualizar aula
En FP15, si ocurre un error al guardar los cambios en la base de datos, el sistema muestra mensaje: "Error al actualizar el aula. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Aula eliminada por otro usuario
En FP15, si otro Director eliminó el aula mientras se editaba, el sistema muestra mensaje: "El aula ya no existe en el sistema", cierra el modal y actualiza la lista.

### Postcondiciones
- Aula actualizada en el sistema con nuevos datos
- Cambios reflejados inmediatamente en todas las vistas

<img width="498" height="185" alt="Image" src="https://github.com/user-attachments/assets/5b33bb05-d3c9-4727-984e-b1b6ad9ed3ec" />

---

## CU-43: ELIMINAR AULA

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-43

### Descripción
El Director elimina un aula que nunca tuvo grupos asignados.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El aula NO debe tener grupos asignados (actuales o históricos)
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Aulas"

**FP2:** El sistema muestra la lista de aulas

**FP3:** El Director identifica el aula que desea eliminar

**FP4:** El Director hace clic en "Eliminar" del aula seleccionada

**FP5:** El sistema verifica que el aula NO tenga:
- Grupos asignados (actuales)
- Historial de grupos (históricos)

**FP6:** Si NO tiene grupos, el sistema muestra modal de confirmación:
- Aula a eliminar: Código
- Edificio: nombre
- Capacidad: N
- Tipo: valor
- Advertencia: "Esta acción es permanente y no se puede deshacer"
- Campo de texto: "Escribe el código del aula para confirmar"
- Botón "Eliminar Definitivamente" (deshabilitado)
- Botón "Cancelar"

**FP7:** El Director escribe el código del aula

**FP8:** El sistema valida que el código coincida

**FP9:** El botón "Eliminar Definitivamente" se habilita

**FP10:** El Director hace clic en "Eliminar Definitivamente"

**FP11:** El sistema elimina el registro del aula de la base de datos

**FP12:** El sistema muestra mensaje: "Aula código eliminada exitosamente"

**FP13:** El sistema actualiza la lista removiendo el aula

### Subflujos

#### SF1: Código incorrecto (Paso 8 → Paso 7)
En FP8, si el código ingresado no coincide con el código del aula, el botón "Eliminar Definitivamente" permanece deshabilitado, el sistema muestra mensaje: "El código no coincide" y retorna al Paso 7.

### Extensiones
Ninguno

### Inclusiones

#### IN1: Obtener informacion del aula
Obtener inforamcion de todas las aulas del cubo comercial.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

#### FA4: Error al eliminar aula
En FP11, si ocurre un error al eliminar el registro de la base de datos, el sistema muestra mensaje: "Error al eliminar el aula. Intente nuevamente", registra el error en logs y el flujo termina.

#### FA5: Aula eliminada por otro usuario
En FP11, si otro Director ya eliminó el aula, el sistema muestra mensaje: "El aula ya no existe en el sistema", cierra el modal y actualiza la lista.

### Postcondiciones
- Aula eliminada permanentemente del sistema
- Registro removido 

<img width="502" height="165" alt="Image" src="https://github.com/user-attachments/assets/7721f4b3-2667-46fe-b531-f3577af6f7ed" />

---

## CU-44: CONSULTAR AULAS

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-44

### Descripción
El Director visualiza y filtra todas las aulas registradas en el sistema, con información de ocupación.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Gestión de Aulas" desde el menú principal

**FP2:** El sistema obtiene todas las aulas

**FP3:** El sistema muestra la página con:
- Título: "Gestión de Aulas"
- Filtros:
  * Por edificio (dropdown)
  * Por capacidad (rango: min-max)
  * Por tipo (Aula/Laboratorio/Auditorio)
  * Por disponibilidad (DISPONIBLE/EN_MANTENIMIENTO/FUERA_DE_SERVICIO)
- Campo de búsqueda por código
- Tabla de aulas con columnas:
  * Código
  * Edificio
  * Piso
  * Tipo
  * Capacidad
  * % Ocupación semanal
  * Grupos asignados (N)
  * Disponibilidad (badge con color)
  * Acciones (Ver, Editar, Horario, Eliminar)
- Estadísticas:
  * Total de aulas: N
  * Aulas disponibles: N
  * Aulas en mantenimiento: N
  * Promedio ocupación: N%
  * Aulas por tipo
- Botón "Crear Nueva Aula"
- Botón "Exportar Lista" (Excel)
- Vista de mapa/plano (opcional)

**FP4:** El Director visualiza la lista completa

### Subflujos

#### SF1: Filtrar por Edificio (Paso 3 → Paso 4)
En FP3, el Director selecciona un edificio, el sistema filtra mostrando solo aulas de ese edificio, actualiza las estadísticas y retorna al Paso 4.

#### SF2: Filtrar por Capacidad (Paso 3 → Paso 4)
En FP3, el Director ingresa rango mínimo-máximo, el sistema filtra aulas dentro del rango y retorna al Paso 4.

#### SF3: Buscar Aula (Paso 3 → Paso 4)
En FP3, el Director escribe en el campo de búsqueda, el sistema filtra por código, muestra coincidencias y retorna al Paso 4.

#### SF4: Ordenar Tabla (Paso 3 → Paso 4)
En FP3, el Director hace clic en una columna de la tabla, el sistema ordena ascendente o descendente y retorna al Paso 4.

#### SF5: Sin resultados de búsqueda (Paso 3 → Paso 4)
En SF3, si la búsqueda no arroja resultados, el sistema muestra mensaje: "No se encontraron aulas", sugiere ajustar filtros y retorna al Paso 4.

### Extensiones

#### EX4: Ver Detalle de Aula
El Director puede opcionalmente hacer clic en "Ver" de un aula, el sistema muestra modal con información completa (datos del aula, grupos asignados con horarios, calendario semanal de ocupación, bloques libres disponibles, historial de uso, equipamiento detallado), el Director visualiza toda la información y cierra el modal.

### Inclusiones

#### IN2: Obtener todas las aulas
En FP2, el sistema consulta la base de datos para obtener todas las aulas registradas.

#### IN5: Aplicar filtros seleccionados
Si hay filtros activos, el sistema aplica filtros de edificio, capacidad, tipo, disponibilidad y búsqueda para mostrar solo aulas que coincidan.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

### Postcondiciones
- Director visualiza todas las aulas organizadas en tabla
- Información de cada aula visible (código, edificio, piso, tipo, capacidad, % ocupación, grupos asignados, disponibilidad)

<img width="501" height="254" alt="Image" src="https://github.com/user-attachments/assets/4c0dcfb0-078a-46cb-8b4d-ccb7b55a9a12" />

---

# 11. CONSULTAS Y REPORTES

---

## CU-45: VER HISTORIAL ACADÉMICO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-45

### Descripción
El Estudiante visualiza su historial académico completo, con todas las materias cursadas, calificaciones y progreso en la carrera.

### Actores
- Estudiante

### Precondiciones
- El Estudiante debe haber iniciado sesión como Estudiante
- Debe tener al menos una inscripción completada
- El sistema debe estar disponible
- La sesión del Estudiante debe estar activa

### Flujo Principal

**FP1:** El Estudiante accede a "Mi Historial Académico" desde el menú

**FP2:** El sistema obtiene todas las inscripciones del estudiante

**FP3:** El sistema calcula estadísticas generales

**FP4:** El sistema muestra la página con:
- Título: "Historial Académico"
- Información del estudiante:
  * Código
  * Nombre completo
  * Carrera
- Estadísticas generales:
  * Promedio acumulado: N/100
  * Créditos aprobados: N
  * Créditos totales de la carrera: N
  * % Avance: N%
  * Materias aprobadas: N
  * Materias reprobadas: N
  * Materias en curso: N
- Historial por gestión (secciones expandibles):
  * Por cada gestión cursada
  * Agrupado cronológicamente (más reciente primero)
- Botón "Exportar Historial" (PDF)
- Botón "Exportar a Excel"

**FP5:** El sistema muestra cada gestión con:
- Código de gestión (YYYY-S)
- Estado (EN_CURSO/FINALIZADA)
- Tabla de materias:
  * Código materia
  * Nombre materia
  * Créditos
  * Calificación final
  * Estado (Aprobado/Reprobado/En curso)
- Promedio de la gestión
- Créditos obtenidos en la gestión

**FP6:** El Estudiante visualiza todo su historial

### Subflujos

#### SF1: Expandir/Contraer Gestión (Paso 5-6)
El Estudiante hace clic en una gestión, el sistema expande o contrae mostrando u ocultando las materias de esa gestión.

### Extensiones

#### EX1: Ver Detalle de Calificación
El Estudiante puede opcionalmente hacer clic en una materia, el sistema muestra modal con desglose completo (todas las evaluaciones: Parcial 1, Parcial 2, Examen, Prácticas, etc., nota de cada evaluación, fecha de registro, docente que registró), el Estudiante visualiza el detalle y cierra el modal.

#### EX2: Ver Gráfico de Progreso
El Estudiante puede opcionalmente hacer clic en "Ver Gráfico", el sistema muestra gráfico de línea con evolución del promedio por gestión y gráfico de barras con créditos obtenidos por gestión.

### Inclusiones

#### IN2: Obtener todas las inscripciones del estudiante
En FP2, el sistema consulta todas las inscripciones con estado ACEPTADA del estudiante ordenadas cronológicamente.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

### Postcondiciones
- Estudiante visualiza historial académico completo ordenado cronológicamente
- Estadísticas generales actualizadas y visibles

<img width="506" height="332" alt="Image" src="https://github.com/user-attachments/assets/b55351e9-8500-4c4a-ad29-741fe1cf55d9" />

---

## CU-46: GENERAR REPORTE ESTADÍSTICO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-46

### Descripción
El Director genera reportes estadísticos personalizables sobre estudiantes, inscripciones y rendimiento académico.

### Actores
- Director de Carrera

### Precondiciones
- El Director debe haber iniciado sesión como Director
- El sistema debe estar disponible
- La sesión del Director debe estar activa

### Flujo Principal

**FP1:** El Director accede a "Reportes Estadísticos" desde el menú

**FP2:** El sistema muestra la página con opciones:
- Tipo de reporte (selector):
  * Estudiantes por Carrera
  * Inscripciones por Gestión
  * Rendimiento por Materia
  * Rendimiento por Paralelo
  * Distribución de Calificaciones
  * Carga Horaria de Docentes
  * Ocupación de Aulas
  * Aprobación vs Reprobación
- Filtros disponibles (dinámicos según tipo):
  * Gestión/Período
  * Carrera
  * Materia
  * Docente
  * Rango de fechas
- Botón "Generar Reporte"

**FP3:** El Director selecciona el tipo de reporte

**FP4:** El sistema muestra filtros aplicables para ese tipo

**FP5:** El Director configura los filtros (si desea)

**FP6:** El Director hace clic en "Generar Reporte"

**FP7:** El sistema valida que el tipo esté seleccionado

**FP8:** El sistema obtiene los datos según filtros

**FP9:** El sistema procesa y calcula estadísticas

**FP10:** El sistema muestra el reporte con:
- Título del reporte
- Filtros aplicados (resumen)
- Gráficos visuales:
  * Gráfico de pastel
  * Gráfico de barras
  * Gráfico de líneas
  * Tabla de datos
- Estadísticas clave (números destacados)
- Fecha de generación
- Botón "Exportar PDF"
- Botón "Exportar Excel"
- Botón "Exportar CSV"
- Botón "Guardar Reporte"

**FP11:** El Director visualiza el reporte generado

### Subflujos

#### SF1: Sin tipo seleccionado (Paso 7 → Paso 3)
En FP7, si no se seleccionó tipo de reporte, el sistema muestra mensaje: "Selecciona un tipo de reporte" y retorna al Paso 3.

#### SF2: Sin datos para el filtro (Paso 8 → Paso 5)
En FP8, si no hay datos que cumplan los filtros, el sistema muestra mensaje: "No hay datos disponibles para los filtros seleccionados", sugiere ajustar los filtros y retorna al Paso 5.

### Extensiones

#### EX2: Reporte de Estudiantes por Carrera
Si se selecciona este tipo (FP3), el sistema cuenta estudiantes por carrera, muestra gráfico de pastel con distribución y tabla con: Carrera, Total estudiantes, Activos, Inactivos, Promedio general.

#### EX5: Exportar Reporte
El Director puede opcionalmente hacer clic en "Exportar P", el sistema genera el archivo y lo descarga en la maquina

### Inclusiones

#### IN3: Obtener datos según filtros
En FP8, el sistema consulta la base de datos aplicando los filtros configurados por el Director.

#### IN5: Generar gráficos visuales
En FP10, el sistema genera automáticamente gráficos de pastel, barras o líneas según el tipo de reporte.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, se muestra mensaje: "El sistema no está disponible temporalmente. Intente más tarde" y el flujo termina.

### Postcondiciones
- Reporte generado exitosamente con datos actuales
- Gráficos visuales generados automáticamente según tipo de reporte

<img width="499" height="308" alt="Image" src="https://github.com/user-attachments/assets/aad2c596-4d83-40a0-8375-f7add0765494" />

---

## CU-47: VER DASHBOARD PERSONALIZADO

**Versión:** 2.0  
**Fecha:** 15/12/2025  
**Autor:** Fernando Terrazas Llanos  
**ID:** CU-47

### Descripción
Cada usuario visualiza un dashboard personalizado según su rol, con resúmenes

### Actores
- Estudiante
- Docente
- Director de Carrera

### Precondiciones
- El usuario debe haber iniciado sesión en el sistema (Estudiante, Docente o Director)
- El sistema debe estar disponible
- La sesión del usuario debe estar activa

### Flujo Principal

**FP1:** El usuario inicia sesión en el sistema

**FP2:** El sistema identifica el rol del usuario

**FP3:** El sistema carga el dashboard correspondiente al rol

**FP4:** El sistema muestra el dashboard personalizado

**FP5:** El usuario visualiza su dashboard

### Subflujos

#### SF1: Dashboard de Estudiante (Paso 3-4)
Si el rol es Estudiante, el sistema muestra : Resumen de Matrícula Actual (materias inscritas, estado por materia, créditos totales), Rendimiento Académico (promedio general, créditos aprobados/total, % avance), Mi Horario Hoy (clases del día, próxima clase countdown, aula y docente), Notificaciones Recientes (inscripciones aceptadas/rechazadas, calificaciones nuevas, anuncios), Accesos Rápidos (Inscribirme, Ver Horario, Ver Calificaciones, Ver Historial). El estudiante puede hacer clic para ir a secciones completas.

#### SF2: Dashboard de Docente (Paso 3-4)
Si el rol es Docente, el sistema muestra : Mis Grupos Actuales (lista de grupos asignados, estudiantes por grupo, próxima clase), Clases de Hoy (horario del día, aulas asignadas, estudiantes esperados), Estadísticas (total estudiantes, carga horaria semanal, calificaciones pendientes), Notificaciones (nuevas inscripciones, recordatorios), Acciones Rápidas (Registrar Calificaciones, Ver Horario, Ver Grupos). El docente puede hacer clic en grupos para registrar calificaciones.

#### SF3: Dashboard de Director (Paso 3-4)
Si el rol es Director, el sistema muestra : Estadísticas Generales (total estudiantes/docentes/grupos, gestión actual), Gestión Actual (estado del período, inscripciones pendientes, grupos sin docente/horarios), Matrículas Pendientes (solicitudes por revisar, link directo), Alertas y Avisos (grupos con conflictos, aulas en mantenimiento, docentes con sobrecarga), Accesos Rápidos (Gestionar Matrículas, Crear Grupo, Ver Reportes, Gestionar Períodos). El Director puede hacer clic en alertas para resolverlas.

#### SF4: Actualizar Dashboard (Paso 5)
El sistema refresca los datos cada 5 minutos automáticamente. El usuario puede hacer clic en "Actualizar" manualmente para refrescar inmediatamente.

### Extensiones
Ninguna

### Inclusiones

#### IN1: Obtener rol del usuario
En FP2, el sistema verifica el rol del usuario autenticado (Estudiante, Docente o Director).

#### IN3: Obtener datos actualizados para secciones 
En FP4, el sistema consulta la base de datos para obtener los datos más recientes relevantes para cada seccion según el rol.

### Flujos Alternativos

#### FA3: Sistema no disponible
Si el sistema backend no está disponible, el sistema muestra mensaje de error en los botones afectados y permite reintentar la carga.

### Postcondiciones
- Usuario visualiza dashboard personalizado según su rol
- Información resumida y actualizada visible en widgets

<img width="507" height="293" alt="Image" src="https://github.com/user-attachments/assets/4102f515-d5f3-44cb-81ef-d222e1e9b939" />

---

