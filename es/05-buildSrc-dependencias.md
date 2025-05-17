# 5.  `buildSrc` y su rol en la arquitectura del build

El directorio `buildSrc/` en Gradle es una poderosa herramienta que permite organizar y escalar la lógica de construcción como si fuera parte de tu código productivo. Aunque muchas veces se subestima, su correcto uso marca una gran diferencia en proyectos empresariales Java con múltiples módulos, como los desarrollados con Spring Boot.

`buildSrc` es un subproyecto especial dentro de Gradle. Es compilado automáticamente antes que cualquier otro build script y forma parte del classpath del proceso de build.

Esto significa que cualquier clase o función declarada dentro de `buildSrc` está disponible globalmente para todos los `build.gradle.kts` del proyecto.

---

### 🧠 ¿Por qué usar `buildSrc`?

- ✅ Organiza el código del build de manera modular, reutilizable y testeable.
- ✅ Permite centralizar convenciones de plugins, configuraciones comunes y versiones.
- ✅ Mejora la mantenibilidad al evitar la duplicación de lógica de construcción.
- ✅ Ofrece soporte total de autocompletado y verificación estática en IDEs como IntelliJ.

> Gradle recomienda `buildSrc` como primer paso para proyectos que comienzan a escalar. Fuente: [Gradle Docs – Build Logic](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources)

---

### 🧱 ¿Qué se puede poner en `buildSrc`?

- 📦 Archivos como `Dependencies.kt`, `Plugins.kt` o `BuildConstants.kt`.
- 🧪 Código reutilizable para configurar tareas (`TestConfig.kt`, `JacocoConfig.kt`, etc.).
- 🧩 Plugins precompilados para encapsular lógica común entre múltiples módulos.

---

### 🧩 Ejemplo de estructura extendida

```
buildSrc/
└── src/
    └── main/
        └── kotlin/
            ├── Dependencies.kt        # Referencias a librerías externas
            ├── Plugins.kt            # Aliases de plugins reutilizables
            ├── ProjectConventions.kt # Convenciones comunes (naming, encoding, etc.)
            └── BuildUtils.kt         # Funciones auxiliares para el build
```

---

### 📌 Ventajas frente a `convention plugins` externos

Aunque desde Gradle 7 se promueve el uso de `build-logic` y plugins precompilados externos, `buildSrc`:

- No requiere configuración adicional
- Es ideal para equipos medianos con un único repo
- Reduce fricción de adopción por ser automático

---

### ⚠️ Consideraciones

- 🔄 Gradle recompila `buildSrc` si detecta cambios, lo cual puede impactar tiempos de build si se abusa.
- 👥 No está pensado para compartir entre múltiples proyectos. Si querés eso, mejor usar un plugin externo o `build-logic`.

---

### ✅ Buenas prácticas

- Usar nombres semánticos para los archivos: `TestDependencies.kt`, `SpringBootPlugins.kt`, etc.
- Evitar lógica de negocio o configuración compleja directamente en `build.gradle.kts`; delegarla a `buildSrc`.
- Si usás `buildSrc`, mantenelo versionado y probado como cualquier parte del código.

---

### 🧩 Conclusión

`buildSrc` es más que una carpeta auxiliar: es una **herramienta estratégica** para lograr mantenibilidad, escalabilidad y claridad en la construcción de proyectos Java modernos. Su correcto uso permite que el sistema de construcción evolucione con la misma disciplina que el código productivo.

