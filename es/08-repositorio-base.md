## 8. Repositorio base del proyecto

Podés consultar el código completo y funcional de este proyecto en el siguiente repositorio:

👉 **[github.com/marcoslozina/gradle-profesional-ejemplo](https://github.com/marcoslozina/gradle-profesional-ejemplo)**

Este repositorio incluye:

- Un proyecto **Java 23 + Spring Boot**
- Estructura **multimódulo** con arquitectura hexagonal
- Configuración avanzada con **Gradle Kotlin DSL**
- Uso de `buildSrc`, `gradle.properties` y convenciones recomendadas
- Tareas personalizadas (`printVersion`, `lintAll`, etc.)
- Archivos profesionales: `README.md`, `LICENSE`, `CHANGELOG.md`, `.gitignore`

---

## 🛠️ ¿Cómo se construyó este proyecto?

1. Proyecto generado en [start.spring.io](https://start.spring.io) con:
  - Java 23, Spring Boot 3.2.5, Gradle con Kotlin DSL
  - Dependencias: Spring Web, Actuator y DevTools

2. Se definió una estructura **multimódulo** con:
  - `application/`, `domain/`, `infrastructure/`, `config/`

3. Se creó `buildSrc/` con:
  - `Dependencies.kt` para centralizar versiones
  - `CustomTasks.kt` para tareas personalizadas reutilizables

4. Se configuraron:
  - `gradle.properties`, `settings.gradle.kts`, `build.gradle.kts` con convenciones empresariales
  - `buildSrc/build.gradle.kts` para usar JDK 23 y Kotlin DSL

5. Se añadieron archivos esenciales:
  - `.gitignore`, `README.md`, `LICENSE` (MIT), `CHANGELOG.md`

---

## 📘 Repositorio del eBook

👉 [github.com/marcoslozina/gradle-profesional-ebook](https://github.com/marcoslozina/gradle-profesional-ebook)  
Contiene el contenido completo del libro, ejemplos, assets y material complementario.

---

## Recursos adicionales

- 📘 [Documentación oficial de Gradle](https://docs.gradle.org/current/userguide/)
- 🚀 [Spring Initializr - start.spring.io](https://start.spring.io/)
- 📦 [Guía oficial del plugin de Spring Boot](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/)
- 🧩 [Guía oficial de Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- 🌱 [Guía de Spring Boot con Kotlin (opcional)](https://spring.io/guides/tutorials/spring-boot-kotlin/)  
  *(Aporta ejemplos útiles de DSL aunque el proyecto esté en Java)*