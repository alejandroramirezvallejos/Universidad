# 🚨 SOLUCIÓN COMPLETA - Errores de Parsing y Carrera del Director

## 📋 Problema Actual

Tienes DOS problemas relacionados:

1. **ERROR AL OBTENER MATERIAS** - "Error during parsing"
2. **ERROR AL CREAR MATERIA** - "El director no tiene carrera asignada"

## 🔍 Causa Raíz

El backend tiene **referencias circulares** en los modelos:
- `Materia` contiene `List<ParaleloMateria>` 
- `ParaleloMateria` contiene `Materia`
- Y así con muchos otros modelos...

Esto causa que Jackson (serializador JSON) entre en un **loop infinito** al intentar convertir los objetos a JSON.

## ✅ Solución Aplicada

He agregado anotaciones `@JsonIgnoreProperties` a **14 archivos** del backend para romper las referencias circulares. Ver detalles en `CAMBIOS_BACKEND.md`.

## 🚀 Pasos para Arreglar

### Opción 1: Script Automático (Recomendado)

```bash
cd /Users/terrazasllanosfernando/Desktop/NotasU/Semestre4/DisenoSoftware/FinalDisenoGithub/Universidad/Code
./restart-all.sh
```

Luego, en el navegador:
1. Abre la consola (F12)
2. Ejecuta: `localStorage.clear()`
3. Recarga la página (F5)
4. Vuelve a hacer login como director

### Opción 2: Manual

#### Paso 1: Detener todo
```bash
# Matar backend
lsof -ti:8080 | xargs kill -9

# Matar frontend  
lsof -ti:4200 | xargs kill -9
```

#### Paso 2: Compilar Backend
```bash
cd Server
./gradlew build -x test
```

#### Paso 3: Iniciar Backend
```bash
./gradlew bootRun
# O ejecuta: java -jar build/libs/Server-0.0.1-SNAPSHOT.jar
```

#### Paso 4: Iniciar Frontend (en otra terminal)
```bash
cd Client
ng serve
```

#### Paso 5: Limpiar localStorage
1. Abre http://localhost:4200
2. Abre la consola del navegador (F12)
3. Ejecuta: `localStorage.clear()`
4. Recarga la página (F5)

#### Paso 6: Login
- Email: `carlos.rodriguez@ucb.edu.bo`
- Password: `password123`

## 🧪 Verificación

Después de reiniciar, verifica:

1. **Login funciona correctamente**
   - No hay errores en la consola
   - El usuario tiene todos sus datos

2. **Obtener materias funciona**
   ```
   ✓ GET /api/materias devuelve 200
   ✓ No hay "Error during parsing"
   ```

3. **Crear materia funciona**
   ```
   ✓ El director tiene carrera asignada
   ✓ POST /api/materias/agregar-carrera devuelve 200
   ```

4. **Obtener docentes funciona**
   ```
   ✓ GET /api/docentes devuelve 200
   ✓ No hay "Error during parsing"
   ```

## 🐛 Debug

Si sigue sin funcionar, ejecuta en la consola del navegador:

```javascript
// Ver el usuario actual
console.log('Usuario:', JSON.parse(localStorage.getItem('usuario_actual')));

// Ver si tiene carrera
const usuario = JSON.parse(localStorage.getItem('usuario_actual'));
console.log('Carrera:', usuario.carrera);
```

Debería mostrar:
```javascript
{
  rol: 'DIRECTOR',
  carrera: {
    codigo: 'ING-SIS',
    nombre: 'Ingeniería de Sistemas',
    id: 1,
    duracionSemestres: 10,
    facultad: 'Ingeniería'
  }
}
```

## 📝 Archivos Modificados

### Backend (Java):
- `Materia.java` ✅
- `Docente.java` ✅
- `ParaleloMateria.java` ✅
- `Carrera.java` ✅
- `Estudiante.java` ✅
- `DirectorCarrera.java` ✅
- `Calificacion.java` ✅
- `Evaluacion.java` ✅
- `Matricula.java` ✅
- `HistorialAcademico.java` ✅
- `ActaEstudiante.java` ✅
- `ReporteDeCarrera.java` ✅
- `ReporteDeInscripciones.java` ✅
- `ReporteDeRendimiento.java` ✅

### Frontend (TypeScript):
- `gestion-materias.component.ts` - Agregado console.log para debug

## 🎯 Resultado Esperado

Después de aplicar estos cambios:

✅ **Backend serializa correctamente** sin loops infinitos
✅ **Frontend recibe datos válidos** del backend
✅ **Director tiene carrera asignada** al hacer login
✅ **Materias se obtienen** sin errores
✅ **Docentes se obtienen** sin errores
✅ **Se pueden crear materias** asociadas a la carrera del director

## ⚠️ IMPORTANTE

**DEBES LIMPIAR LOCALSTORAGE** porque los datos antiguos no tienen la estructura correcta. El localStorage guarda una copia vieja del usuario sin la carrera, por eso sigue dando error incluso después de hacer login.

```javascript
// EN LA CONSOLA DEL NAVEGADOR:
localStorage.clear();
// Luego recarga y vuelve a hacer login
```
