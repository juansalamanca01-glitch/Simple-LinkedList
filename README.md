# Lista Enlazada Simple - Juan Camilo Salamanca Mosquera ID:409457

## Descripción
Implementación de una lista enlazada simple para gestionar estudiantes en una clase.
- No se usan colecciones de alto nivel (ArrayList, etc.)
- Todas las operaciones se realizan recorriendo y manipulando manualmente la lista.

## Archivos principales
- `Student.java` – Nodo de la lista (nombre, ID, programa, etc.)
- `Class.java` – Clase que mantiene la referencia a `head` y opera sobre la lista
- `Main.java` – Prueba de funcionalidad (inscripción, actualización, eliminación)

## Operaciones soportadas
- Inscribir estudiante (con validación de duplicados)
- Actualizar datos de un estudiante
- Eliminar estudiante (casos borde: head, intermedio, último)
- Listar todos los inscritos
- Validar créditos positivos al crear la clase

## Uso
Compila y ejecuta:
```bash
javac *.java
java Main