# 7. Buenas prácticas y errores comunes en Gradle

A continuación se presentan las recomendaciones clave para mantener proyectos Gradle bien estructurados, escalables y fáciles de mantener, junto con errores frecuentes que es importante evitar.

---

### ✅ Buenas prácticas recomendadas

1. **Centralizar configuración en `buildSrc/`**  
   Organiza las dependencias y convenciones en clases como `Dependencies.kt` o `Plugins.kt`. Esto reduce la duplicación y mejora la mantenibilidad del proyecto.

2. **Reutilizar configuración en bloques `subprojects {}` o `allprojects {}`**  
   Estos bloques permiten aplicar reglas comunes (plugins, repositorios, compiladores, encoding, etc.) a todos los módulos del proyecto, manteniendo scripts individuales más limpios.

3. **Utilizar Kotlin DSL (`.kts`) para todos los scripts de build**  
   El DSL tipado permite autocompletado, validación en tiempo de compilación y menor propensión a errores. Ideal para equipos que usan IntelliJ IDEA o Android Studio.

4. **Definir versiones y propiedades en `gradle.properties`**  
   Evitá hardcodear versiones en los scripts. Usar `gradle.properties` permite centralizar valores y sobrescribirlos según el entorno (CI/CD, local, etc.).

5. **Automatizar la detección de actualizaciones**  
   Incorporá el plugin `com.github.ben-manes.versions` y tareas personalizadas para mantener dependencias actualizadas de forma segura.

---

### ❌ Errores comunes a evitar

1. **Usar SDKMAN con versiones inestables como Java 23**  
   Algunas herramientas del ecosistema Gradle no ofrecen soporte inmediato a versiones recientes. Usar versiones estables minimiza errores con plugins o builds rotos.

2. **No fijar versiones de plugins o dependencias**  
   Dejar versiones flotantes (`+`) puede generar builds no reproducibles o fallos inesperados si cambian las versiones en los repositorios.

3. **Hardcodear strings de dependencias en cada módulo**  
   Esto aumenta el esfuerzo de mantenimiento y genera inconsistencias. En su lugar, usá constantes centralizadas (por ejemplo, en `Deps.kt`).

4. **No crear tareas personalizadas reutilizables**  
   Repetir pasos manuales en scripts o pipelines puede evitarse con tareas Gradle bien definidas y parametrizadas (`register("lintAll") { ... }`).

5. **No limpiar y estructurar los scripts**  
   Un `build.gradle.kts` desordenado o con responsabilidades mezcladas (lógica, configuración, variables) dificulta su lectura, testing y evolución.

---

Aplicar estas prácticas no solo mejora la salud del proyecto, sino que también facilita el trabajo colaborativo y la automatización del ciclo de vida de construcción.