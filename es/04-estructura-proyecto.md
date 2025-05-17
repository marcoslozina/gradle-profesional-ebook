# 4. Estructura del proyecto

Gradle organiza su configuración principal mediante archivos Kotlin Script (`.kts`) o Groovy (`.gradle`). En proyectos modernos con Java y Spring Boot, se recomienda usar **Kotlin DSL (`.kts`)**, incluso si todo el código fuente está escrito en `.java`. Esto permite aprovechar el tipado, la integración con el IDE y la validación en tiempo de compilación.

---

### 📁 Archivos clave en un proyecto profesional

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

### 📄 `build.gradle.kts` (raíz)

Archivo principal de configuración. Define:

- Plugins del proyecto (Spring Boot, Java, etc.)
- Repositorios y versiones comunes
- Configuración de compilación
- Dependencias globales o compartidas

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

Define el nombre del proyecto raíz y los submódulos:

```kotlin
rootProject.name = "java-hexagonal-architecture"

include("app", "domain", "application", "infrastructure", "config")
```

Este archivo es crítico para proyectos multimódulo, ya que Gradle lo ejecuta primero para identificar qué módulos compilar.

---

### 📄 `gradle.properties`

Centraliza propiedades reutilizables del proyecto, como versiones, configuración de JVM o flags:

```properties
# Información del proyecto
projectVersion=1.0.0
group=com.miempresa

# JVM
org.gradle.jvmargs=-Xmx2g -Dfile.encoding=UTF-8

# Versiones
springBootVersion=3.2.0
junitVersion=5.10.0
javaVersion=23
```

Se accede desde `.kts` así:

```kotlin
val springBootVersion: String by project
```

---

### 📁 `buildSrc/` y `Dependencies.kt`

El directorio `buildSrc/` permite centralizar la lógica del build (como dependencias) de forma organizada.

**Ejemplo de `Dependencies.kt`:**

```kotlin
object Deps {
    val junit get() = "org.junit.jupiter:junit-jupiter:${project.property("junitVersion")}"
    val springBoot get() = "org.springframework.boot:spring-boot-starter:${project.property("springBootVersion")}"
}
```

Luego se usa así:

```kotlin
dependencies {
    implementation(Deps.springBoot)
    testImplementation(Deps.junit)
}
```

> ✅ También se puede usar Java para `Dependencies.java` si todo el proyecto está en Java.

---

### 🎯 `.kt` vs `.kts` — ¿cuál es cuál?

| Extensión | Uso                                 | Contexto común         |
|-----------|--------------------------------------|------------------------|
| `.kt`     | Código fuente Kotlin                 | Lógica de negocio      |
| `.kts`    | Kotlin Script para configuración     | Archivos de Gradle DSL |

> Usar `.kts` en proyectos Java no obliga a usar Kotlin como lenguaje de aplicación.

---

### ✅ Buenas prácticas

- Declarar versiones en `gradle.properties`
- Centralizar dependencias en `buildSrc/Dependencies.kt`
- Evitar `Versions.kt` con versiones hardcodeadas
- No incluir datos sensibles en `gradle.properties`
- Usar `.kts` incluso en proyectos 100% Java para mejor mantenimiento

---

### 🧩 Resumen de responsabilidades

| Archivo                    | Propósito principal                                           |
|----------------------------|---------------------------------------------------------------|
| `build.gradle.kts`         | Configuración de compilación, dependencias y plugins         |
| `settings.gradle.kts`      | Organización y módulos del proyecto                          |
| `gradle.properties`        | Propiedades globales, versiones, flags JVM                   |
| `buildSrc/Dependencies.kt` | Declaración centralizada y reutilizable de dependencias      |

---

Aunque el código fuente esté completamente en Java, usar Kotlin DSL (`.kts`) en la configuración ofrece una experiencia de desarrollo más robusta, clara y moderna.