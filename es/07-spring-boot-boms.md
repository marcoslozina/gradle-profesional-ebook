## 8. ¿Qué es un BOM (Bill of Materials)?

Un **BOM** es un archivo que define un conjunto de versiones compatibles entre sí para un grupo de dependencias. Proviene originalmente del mundo Maven, pero Gradle lo interpreta automáticamente cuando usamos el plugin `io.spring.dependency-management` o el plugin oficial de Spring Boot.

> 📘 Referencia oficial: [Spring Boot BOM Docs](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/)

---

### 🎯 ¿Por qué usar un BOM?

- ✅ Asegura compatibilidad entre librerías del mismo ecosistema
- ✅ Evita errores comunes por versiones desalineadas
- ✅ Permite declarar dependencias **sin necesidad de especificar la versión**
- ✅ Centraliza el control de versiones: basta con actualizar la versión de Spring Boot

---

### 🧱 ¿Cómo funciona?

- Cuando aplicás el plugin de Spring Boot, automáticamente se importa su BOM
- Este BOM incluye versiones fijas para cientos de bibliotecas comunes (Spring Web, JPA, Security, Jackson, etc.)
- Tu proyecto solo declara las dependencias necesarias, y Gradle resuelve automáticamente las versiones definidas en el BOM

---

### ✅ Ejemplo correcto

```kotlin
plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
```

👉 **No se declaran versiones**: las gestiona el BOM del plugin.

---

### ❌ Ejemplo incorrecto

```kotlin
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.1") // innecesario
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.1") // puede generar conflictos
}
```

> ⚠️ Declarar versiones explícitas puede **romper la coherencia** del BOM.

---

### 📚 Tipos de dependencias en Gradle

| Tipo               | Descripción                                                    |
|--------------------|----------------------------------------------------------------|
| `implementation`   | Se usa dentro del módulo, no se expone a otros módulos         |
| `api`              | Se expone a módulos que dependan de este módulo                |
| `compileOnly`      | Solo necesaria en compilación (ej. anotaciones)                |
| `runtimeOnly`      | Solo necesaria en tiempo de ejecución                          |
| `testImplementation` | Disponible únicamente durante testing                         |

> 📌 Recomendación: Usar `implementation` por defecto para mantener bajo acoplamiento entre módulos.

---

### 🔁 Proceso profesional de actualización de dependencias

1. Consultar la versión estable de Spring Boot desde [https://start.spring.io](https://start.spring.io)
2. Leer el changelog de Spring para detectar breaking changes
3. Actualizar `org.springframework.boot` en `build.gradle.kts`
4. Usar el plugin `com.github.ben-manes.versions` para identificar librerías desactualizadas:

```kotlin
plugins {
    id("com.github.ben-manes.versions") version "0.48.0"
}
```

```bash
./gradlew dependencyUpdates
```

5. Verificar compatibilidad con tests automatizados
6. Automatizar este flujo con RenovateBot o GitHub Actions si aplica

---

### ❗ Errores comunes

- ❌ Declarar versiones explícitas que ya gestiona el BOM
- ❌ Usar versiones conflictivas de dependencias de terceros que no respetan el BOM
- ❌ Declarar `api` innecesariamente, exponiendo dependencias internas

---

### 📘 Recursos adicionales

- [Spring Boot Gradle Plugin Docs](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/)
- [Dependency Management Plugin](https://github.com/spring-gradle-plugins/dependency-management-plugin)
- [Maven BOM Concept](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)

---

¿Querés que avancemos ahora con pruebas (`JUnit`, `JaCoCo`, `SonarQube`) o con convenciones y `Plugins.kt`?