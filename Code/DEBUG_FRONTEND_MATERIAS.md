# 🔍 DEBUG: Frontend - Edición de Materias

## Problema
El API funciona correctamente, pero cuando editas una materia desde el frontend, los cambios no se reflejan en la UI.

## ✅ Backend Confirmado
- ✅ PUT /api/materias/{codigo} funciona correctamente
- ✅ Los datos se actualizan en la base de datos H2
- ✅ GET /api/materias devuelve los datos actualizados

## 🔍 Pasos para Debuggear en el Frontend

### 1. Abre la Consola del Navegador
- Chrome/Edge: F12 o Cmd+Option+I (Mac)
- Busca la pestaña "Console"

### 2. Reproduce el Error
1. Ve a: http://localhost:4200/gestion-materias
2. Haz clic en "Editar" en cualquier materia
3. Cambia el nombre o créditos
4. Haz clic en "Actualizar Materia"
5. **OBSERVA LA CONSOLA** y copia todos los logs que aparezcan

### 3. Logs que Deberías Ver

#### Logs Esperados (SI FUNCIONA):
```
[DEBUG] Actualizando materia: SIS-203 con DTO: {codigo: "SIS-203", nombre: "...", ...}
[MateriasService] Actualizando materia SIS-203 con datos: {...}
[MateriasService] Materia SIS-203 actualizada exitosamente: {...}
[DEBUG] Materia actualizada desde API: {...}
[DEBUG] Cargando materias desde el backend...
[MateriasService] Obteniendo todas las materias...
[MateriasService] 9 materias obtenidas del backend
[MateriasService] Muestra de datos (primeras 2 materias): [...]
[DEBUG] Materias cargadas: 9 materias
[DEBUG] Primera materia como ejemplo: {...}
✅ 9 materias cargadas y mostradas en UI
[DEBUG] Aplicando filtros, materias filtradas: 9
```

#### Logs Problemáticos (SI NO FUNCIONA):
- ❌ Si no ves `[MateriasService] Materia ... actualizada exitosamente`
  → El servicio no está llamando al API correctamente

- ❌ Si ves el log de éxito pero no `[DEBUG] Cargando materias desde el backend...`
  → El componente no está recargando después de actualizar

- ❌ Si ves todos los logs pero la UI no se actualiza
  → Problema de detección de cambios de Angular

### 4. Verifica el Network Tab
1. Abre la pestaña "Network" en DevTools
2. Filtra por "Fetch/XHR"
3. Edita una materia
4. Deberías ver:
   - `PUT /api/materias/{codigo}` → Status 200 (con la carrera en la respuesta)
   - `GET /api/materias` → Status 200 (listado completo)

### 5. Verifica los Datos en la Respuesta
- Haz clic en la request `PUT /api/materias/{codigo}`
- Ve a la pestaña "Response"
- **VERIFICA** que la respuesta incluya el campo `carrera`:
  ```json
  {
    "codigo": "SIS-203",
    "nombre": "...",
    "creditos": 5,
    "carrera": {
      "codigo": "ING-SIS",
      "nombre": "Ingeniería de Sistemas"
    }
  }
  ```

## 🐛 Posibles Problemas

### Problema 1: Signal No Se Actualiza
**Síntoma:** Ves todos los logs pero la UI no cambia

**Solución:** Ya agregué `[...this.materias()]` para crear nueva referencia

### Problema 2: Datos Cacheados
**Síntoma:** GET /api/materias devuelve datos viejos

**Solución:** 
```typescript
// En api.service.ts, agregar headers anti-cache
headers: { 'Cache-Control': 'no-cache' }
```

### Problema 3: Mapeo Incorrecto
**Síntoma:** La respuesta del API tiene `carrera` pero el frontend no la muestra

**Solución:** Verificar método `mapearMateriaCompleta` en materias.service.ts

## 📋 Checklist de Verificación

Cuando hagas la prueba, anota:

- [ ] ¿Aparecen los logs de `[DEBUG]` en la consola?
- [ ] ¿El PUT request devuelve Status 200?
- [ ] ¿La respuesta del PUT incluye el campo `carrera`?
- [ ] ¿El GET /api/materias se ejecuta después del PUT?
- [ ] ¿El GET devuelve los datos actualizados?
- [ ] ¿Los logs muestran las materias cargadas correctamente?
- [ ] ¿La UI se actualiza visualmente?

## 🎯 Siguiente Paso

**COPIA Y PEGA** todos los logs de la consola del navegador cuando hagas la prueba de editar una materia.

Eso me permitirá identificar exactamente dónde está fallando el flujo.
