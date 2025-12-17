# Cambios en el Backend - Solución de Referencias Circulares

## Problema
Los modelos del backend tenían referencias circulares que causaban errores de parsing JSON:
- Materia ↔ ParaleloMateria
- Docente ↔ ParaleloMateria  
- Carrera ↔ Estudiante/Materia/Director
- Estudiante ↔ Materia
- Y más...

## Solución
Se agregaron anotaciones `@JsonIgnoreProperties` a todos los campos que crean referencias circulares.

## Archivos Modificados

### 1. Materia.java
```java
@JsonIgnoreProperties({"materiasCorrelativas", "paraleloMaterias", "carrera"})
private List<IMateria> materiasCorrelativas = new ArrayList<>();

@JsonIgnoreProperties({"materia", "docente", "estudiantes", "horarios"})
private List<IParaleloMateria> paraleloMaterias = new ArrayList<>();

@JsonIgnoreProperties({"estudiantes", "materias", "director"})
private ICarrera carrera;
```

### 2. Docente.java
```java
@JsonIgnoreProperties({"docente", "materia", "estudiantes", "horarios"})
private List<IParaleloMateria> paraleloMaterias = new ArrayList<>();
```

### 3. ParaleloMateria.java
```java
@JsonIgnoreProperties({"paraleloMaterias", "materiasCorrelativas"})
private IMateria materia;

@JsonIgnoreProperties({"paraleloMaterias"})
private IDocente docente;

@JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
private List<IEstudiante> estudiantes = new ArrayList<>();
```

### 4. Carrera.java
```java
@JsonIgnoreProperties({"carrera", "materiasInscritas", "materiasAprobadas"})
private List<IEstudiante> estudiantes = new ArrayList<>();

@JsonIgnoreProperties({"carrera"})
private IDirectorCarrera director;

@JsonIgnoreProperties({"carrera", "paraleloMaterias", "materiasCorrelativas"})
private List<IMateria> materias = new ArrayList<>();
```

### 5. Estudiante.java
```java
@JsonIgnoreProperties({"estudiantes", "materias", "director"})
private ICarrera carrera;

@JsonIgnoreProperties({"paraleloMaterias", "materiasCorrelativas", "carrera"})
private List<IMateria> materiasInscritas = new ArrayList<>();

@JsonIgnoreProperties({"paraleloMaterias", "materiasCorrelativas", "carrera"})
private List<IMateria> materiasAprobadas = new ArrayList<>();
```

### 6. DirectorCarrera.java
```java
@JsonIgnoreProperties({"director", "estudiantes", "materias"})
private ICarrera carrera;
```
**IMPORTANTE:** La carrera del director SÍ se serializa, solo ignora los campos internos.

### 7. Calificacion.java
```java
@JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
private IEstudiante estudiante;

@JsonIgnoreProperties({"calificaciones", "paraleloMateria"})
private IEvaluacion evaluacion;
```

### 8. Evaluacion.java
```java
@JsonIgnoreProperties({"materia", "docente", "estudiantes", "horarios"})
private IParaleloMateria paraleloMateria;

@JsonIgnoreProperties({"evaluacion"})
private List<ICalificacion> calificaciones = new ArrayList<>();
```

### 9. Matricula.java
```java
@JsonIgnoreProperties({"estudiantes", "horarios"})
private IParaleloMateria paraleloMateria;

@JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
private IEstudiante estudiante;
```

### 10. HistorialAcademico.java
```java
@JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
private IEstudiante estudiante;

@JsonIgnoreProperties({"estudiante"})
private List<IActaEstudiante> actas = new ArrayList<>();
```

### 11. ActaEstudiante.java
```java
@JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
private IEstudiante estudiante;

@JsonIgnoreProperties({"estudiantes", "horarios", "docente"})
private IParaleloMateria paraleloMateria;

@JsonIgnoreProperties({"estudiante", "evaluacion"})
private List<ICalificacion> calificaciones = new ArrayList<>();
```

### 12. ReporteDeCarrera.java
```java
@JsonIgnoreProperties({"estudiantes", "materias", "director"})
private ICarrera carrera;

@JsonIgnoreProperties({"materiasInscritas", "materiasAprobadas", "carrera"})
private List<IEstudiante> estudiantes;
```

### 13. ReporteDeInscripciones.java
```java
@JsonIgnoreProperties({"paraleloMateria"})
private List<IMatricula> matriculas;
```

### 14. ReporteDeRendimiento.java
```java
@JsonIgnoreProperties({"estudiantes", "horarios"})
private ParaleloMateria paralelo;
```

## Pasos para Aplicar los Cambios

1. **Compilar el backend:**
   ```bash
   cd Server
   ./gradlew build -x test
   ```

2. **Reiniciar el servidor:**
   ```bash
   ./gradlew bootRun
   ```

3. **Limpiar localStorage del navegador** (en la consola del navegador):
   ```javascript
   localStorage.clear();
   ```

4. **Volver a hacer login** para que el frontend obtenga los datos correctos del backend.

## Resultado Esperado
- ✅ No más errores "Error during parsing"
- ✅ Los datos se serializan correctamente sin loops infinitos
- ✅ El Director tiene su carrera asignada
- ✅ Los docentes se obtienen correctamente
- ✅ Las materias se obtienen correctamente
