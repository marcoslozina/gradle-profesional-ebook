## 5. Archivos clave de configuración

Gradle organiza su configuración principal a través de archivos Kotlin Script (`.kts`) o Groovy (`.gradle`). En proyectos modernos con Java y Spring Boot, se recomienda usar **Kotlin DSL (`.kts`)** por su tipado, integración con el IDE y validación en tiempo de compilación, incluso si todo el código fuente de la aplicación está en `.java`.

Este enfoque combina lo mejor de ambos mundos: un backend robusto en Java y una configuración declarativa moderna, tipada y reutilizable con Kotlin DSL.

---

### 🗂️ Estructura base con archivos clave

```
java-hexagonal-architecture/
├── build.gradle.kts           # Configuración principal del proyecto
├── settings.gradle.kts        # Declaración de módulos
├── gradle.properties          # Propiedades globales reutilizables
├── buildSrc/                  # Lógica común para el build (opcional)
│   └── src/main/kotlin/
│       └── Dependencies.kt    # Centralización de dependencias
└── ...
```

---

### 📄 `build.gradle.kts`

Archivo principal de configuración. Define:

- Plugins a aplicar (Spring Boot, Kotlin, Java, etc.)
- Configuración de compilación
- Dependencias necesarias por módulo
- Acceso a propiedades globales

**Ejemplo:**

```kotlin
plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.3"
    java
}

allprojects {
    group = project.findProperty("group") as String
    version = project.findProperty("projectVersion") as String

    repositories {
        mavenCentral()
    }
}
```

---

### 📄 `settings.gradle.kts`

Define el nombre del proyecto raíz y la lista de submódulos.

**Ejemplo:**

```kotlin
rootProject.name = "java-hexagonal-architecture"

include("app")
include("domain")
include("application")
include("infrastructure")
include("config")
```

Gradle lo ejecuta primero para determinar qué módulos compilar.

---

### 📄 `gradle.properties`

Este archivo permite centralizar propiedades reutilizables para todas las configuraciones Gradle, como versiones de librerías, ajustes de JVM o parámetros de entorno.

**Ejemplo:**

```properties
# Información general del proyecto
projectName=java-hexagonal-architecture
projectVersion=1.0.0
group=com.miempresa

# JVM y rendimiento
org.gradle.jvmargs=-Xmx2g -Dfile.encoding=UTF-8

# Versiones centralizadas
javaVersion=23
springBootVersion=3.2.0
junitVersion=5.10.0
```

Se puede acceder a estas propiedades en los scripts `.kts` usando:

```kotlin
val springBootVersion: String by project
```

---

### 📁 `buildSrc/src/main/kotlin/Dependencies.kt`

Permite centralizar las dependencias utilizadas en los distintos módulos del proyecto. Lee las versiones directamente desde `gradle.properties`.

**Ejemplo:**

```kotlin
object Deps {
    val junit get() = "org.junit.jupiter:junit-jupiter:${project.property("junitVersion")}"
    val springBootStarter get() = "org.springframework.boot:spring-boot-starter:${project.property("springBootVersion")}"
}
```

Luego se usa en los módulos:

```kotlin
dependencies {
    implementation(Deps.springBootStarter)
    testImplementation(Deps.junit)
}
```

---

### ✅ Buenas prácticas

- Definir todas las versiones en `gradle.properties` para permitir sobrescribirlas por entorno.
- Usar `buildSrc/Dependencies.kt` para evitar repetir strings en los `build.gradle.kts`.
- No usar `Versions.kt`, ya que las versiones deben poder definirse fuera del classpath.
- Evitar datos sensibles en `gradle.properties` (usar variables de entorno o `.env`).
- No hacer commit del archivo si contiene configuraciones locales.

---

### 🧩 Resumen de responsabilidades

| Archivo                    | Rol principal                                               |
|----------------------------|-------------------------------------------------------------|
| `build.gradle.kts`         | Lógica de compilación y dependencias del proyecto           |
| `settings.gradle.kts`      | Organización del proyecto y definición de módulos           |
| `gradle.properties`        | Centralización de propiedades y configuración global        |
| `buildSrc/Dependencies.kt` | Mapeo reutilizable de dependencias accediendo a versiones   |

---

Aunque el código fuente esté completamente en Java, el uso de `.kts` en la configuración permite un entorno más robusto, coherente y fácil de mantener.