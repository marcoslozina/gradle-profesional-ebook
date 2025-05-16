## 5. Archivos clave de configuración

Gradle organiza su configuración principal a través de archivos Kotlin Script (`.kts`) o Groovy (`.gradle`). En proyectos modernos, se recomienda usar Kotlin DSL (`.kts`) por su tipado, integración con el IDE y validación en tiempo de compilación, incluso si el proyecto está escrito completamente en **Java**.

En este contexto, es importante conocer las diferencias entre los archivos `.kt`, `.kts` y su uso dentro de una estructura de proyecto profesional.

---

### ¿Qué son `.kt` y `.kts`?

- **`.kt`** → Archivos de código fuente de Kotlin. Se usan en la lógica de aplicación cuando el proyecto usa Kotlin como lenguaje.
- **`.kts`** → Archivos Kotlin Script, utilizados exclusivamente en la configuración del build de Gradle (por ejemplo: `build.gradle.kts`, `settings.gradle.kts`).

> En proyectos Java modernos se recomienda **usar `.kts` para la configuración**, aunque todo el código de aplicación esté escrito en Java (`.java`). Esto permite aprovechar la potencia del DSL tipado de Gradle sin tener que adoptar Kotlin en el código fuente.

---

### Archivos principales

#### 📄 `settings.gradle.kts`

Define el nombre del proyecto raíz y los módulos que lo componen:

```kotlin
rootProject.name = "java-hexagonal-architecture"

include("app")
include("domain")
include("application")
include("infrastructure")
include("config")
```

Este archivo es esencial en proyectos **multimódulo**, ya que Gradle necesita saber qué subproyectos gestionar y compilar.

---

#### 📄 `build.gradle.kts` (raíz)

Define la configuración global del proyecto, plugins compartidos y propiedades comunes como `group`, `version`, repositorios, y configuración de herramientas:

```java
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
    id("org.springframework.boot") version "3.2.0" apply false
    id("io.spring.dependency-management") version "1.1.3" apply false
}

allprojects {
    group = "com.miempresa"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}
```

Este archivo puede incluir configuraciones para pruebas, formato de código, cobertura, tareas personalizadas y configuración del compilador.

---

#### 📁 `buildSrc/src/main/java/Dependencies.java` *(o `Dependencies.kt` si usás Kotlin)*

En proyectos grandes, es una buena práctica centralizar las versiones y declaraciones de dependencias. Esto se logra con una clase como esta:

```java
public class Dependencies {
    public static final String SPRING_BOOT = "org.springframework.boot:spring-boot-starter";
    public static final String JUNIT = "org.junit.jupiter:junit-jupiter-api:5.10.0";
}
```

Aunque muchos proyectos usan Kotlin (`Dependencies.kt`) en `buildSrc`, es totalmente válido y funcional usar Java si todo tu proyecto está escrito en Java.

> `buildSrc` es una convención especial en Gradle que permite declarar lógica reutilizable y mantener el código de build organizado.

---

### Comparación entre DSLs

| Aspecto              | Kotlin DSL (`.kts`)                      | Groovy DSL (`.gradle`)               |
|----------------------|------------------------------------------|--------------------------------------|
| Tipado               | Sí (verificación en tiempo de compilación)| No                                   |
| Autocompletado IDE   | Completo (IntelliJ, Android Studio)       | Parcial                              |
| Integración moderna  | Alta (recomendado en Spring Initializr)   | Menor prioridad en nuevos proyectos  |
| Popularidad histórica| Más reciente                              | Muy usado antes de Gradle 5          |

---

### Recomendación general

Aunque el código fuente de la aplicación esté en **Java**, usar **Kotlin DSL (`.kts`) para los archivos de configuración** aporta claridad, seguridad y mejor integración con herramientas modernas. No se requiere que el equipo conozca Kotlin profundamente, ya que estos scripts son declarativos y bien soportados por el ecosistema Gradle.

---

¿Querés avanzar con una sección para definir `gradle.properties`, tareas personalizadas, o configuración de test y coverage como siguiente paso?