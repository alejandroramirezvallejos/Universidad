# 🚨 CORRECCIONES COMPLETAS - Backend y Frontend

## 📋 PROBLEMAS IDENTIFICADOS

1. **Backend falta endpoints de actualización (PUT)** para:
   - Estudiantes
   - Docentes  
   - Aulas
   
2. **@JsonIgnoreProperties muy agresivo** - Ignora campos necesarios para edición

3. **Frontend no tiene servicios de actualización** para:
   - Estudiantes
   - Docentes
   - Aulas

4. **Campos faltantes en DTOs**:
   - codigoEstudiante no se obtiene del backend
   - codigoDocente no se obtiene del backend
   - contrasenna no debería ser obligatorio en edición

## ✅ SOLUCIONES

### PARTE 1: ARREGLAR BACKEND - Agregar endpoints faltantes

#### 1.1 ControladorEstudiante.java - Agregar PUT
```java
@PutMapping("/{codigo}")
public ResponseEntity<IEstudiante> actualizar(
    @PathVariable String codigo, 
    @RequestBody Estudiante estudiante
) {
    return ResponseEntity.ok(servicio.actualizar(codigo, estudiante));
}
```

#### 1.2 ControladorDocente.java - Agregar PUT  
```java
@PutMapping("/{codigo}")
public ResponseEntity<IDocente> actualizar(
    @PathVariable String codigo,
    @RequestBody Docente docente
) {
    return ResponseEntity.ok(servicio.actualizar(codigo, docente));
}
```

#### 1.3 ControladorAula.java - Agregar PUT
```java
@PutMapping("/{codigo}")
public ResponseEntity<IAula> actualizar(
    @PathVariable String codigo,
    @RequestBody Aula aula
) {
    return ResponseEntity.ok(servicio.actualizar(codigo, aula));
}
```

### PARTE 2: ARREGLAR SERVICIOS BACKEND

Necesitamos agregar métodos `actualizar` en los servicios si no existen.

### PARTE 3: SIMPLIFICAR @JsonIgnoreProperties

El problema es que estamos ignorando DEMASIADOS campos. Voy a usar una estrategia diferente:

**NUEVA ESTRATEGIA:** Solo ignorar en LISTAS que causan ciclos, NO en referencias simples.

#### Materia.java - CORRECCIÓN
```java
@Builder.Default
@JsonIgnoreProperties({"paraleloMaterias", "carrera"})  // Solo ignorar en las correlativas
private List<IMateria> materiasCorrelativas = new ArrayList<>();

@Builder.Default
@JsonIgnoreProperties({"materia"})  // Solo ignorar la referencia inversa
private List<IParaleloMateria> paraleloMaterias = new ArrayList<>();

// carrera SIN @JsonIgnoreProperties - se necesita completa
private ICarrera carrera;
```

#### Docente.java - CORRECCIÓN
```java
@Builder.Default
@JsonIgnoreProperties({"docente"})  // Solo ignorar la referencia inversa
private List<IParaleloMateria> paraleloMaterias = new ArrayList<>();
```

#### ParaleloMateria.java - CORRECCIÓN
```java
@JsonIgnoreProperties({"paraleloMaterias", "materiasCorrelativas"})
private IMateria materia;

@JsonIgnoreProperties({"paraleloMaterias"})
private IDocente docente;

// estudiantes y horarios SIN restricciones - se necesitan completos
private List<IEstudiante> estudiantes = new ArrayList<>();
private List<IHorario> horarios = new ArrayList<>();
```

#### Estudiante.java - CORRECCIÓN
```java
// carrera SIN @JsonIgnoreProperties pesadas - solo lo mínimo
@JsonIgnoreProperties({"estudiantes"})
private ICarrera carrera;

@Builder.Default
@JsonIgnoreProperties({"paraleloMaterias", "carrera"})
private List<IMateria> materiasInscritas = new ArrayList<>();
```

### PARTE 4: AGREGAR SERVICIOS FRONTEND

#### estudiantes.service.ts - Agregar método actualizar
```typescript
async actualizarEstudiante(codigo: string, estudiante: any): Promise<any> {
  try {
    const dtoEstudiante = {
      codigo: codigo,
      nombre: estudiante.nombre,
      apellido: estudiante.apellido,
      email: estudiante.email,
      semestre: estudiante.semestre,
      carrera: estudiante.carrera ? {
        codigo: estudiante.carrera.codigo
      } : null
    };
    
    const actualizado = await firstValueFrom(
      this.api.put<DtoEstudiante>(`/estudiantes/${codigo}`, dtoEstudiante)
    );
    
    const estudianteActualizado = this.mappers.dtoToEstudiante(actualizado);
    
    // Actualizar signal
    this._estudiantes.update(estudiantes => 
      estudiantes.map(e => e.codigoEstudiante === codigo ? estudianteActualizado : e)
    );
    
    return estudianteActualizado;
  } catch (error) {
    console.error('Error al actualizar estudiante:', error);
    throw error;
  }
}
```

#### docentes.service.ts - Agregar método actualizar
Similar al de estudiantes

#### aulas.service.ts - Agregar método actualizar  
Similar al de estudiantes

### PARTE 5: ARREGLAR COMPONENTES FRONTEND

Los componentes necesitan:
1. No enviar campos que no existen en el backend (codigoEstudiante, codigoDocente, contrasenna en UPDATE)
2. Usar los nuevos métodos de actualización

## 🎯 ESTRATEGIA DE IMPLEMENTACIÓN

### Fase 1: Backend Minimalista (30 min)
1. Agregar endpoints PUT faltantes
2. Simplificar @JsonIgnoreProperties a lo MÍNIMO necesario
3. Compilar y probar

### Fase 2: Frontend Adaptar (20 min)
1. Agregar métodos de actualización en servicios
2. Arreglar componentes para usar métodos correctos
3. Remover validaciones de campos que no vienen del backend

### Fase 3: Testing (10 min)
1. Probar crear materia
2. Probar editar materia
3. Probar crear/editar estudiante
4. Probar crear/editar docente
5. Probar crear/editar aula

## ⚡ DECISIÓN RÁPIDA

¿Quieres que implemente:
A) TODO de una vez (1 hora aprox)
B) Solo lo crítico para crear materias (15 min)
C) Paso a paso con confirmaciones
