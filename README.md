# Gradle Profesional - Proyecto de Ejemplo

Este repositorio complementa el eBook 📘 [Gradle Profesional con Java + Spring](https://github.com/marcoslozina/gradle-profesional-ebook) y proporciona una base sólida para construir aplicaciones Java modernas usando Gradle con Kotlin DSL.

## 📦 Características del proyecto

- ✅ Java 23 + Spring Boot 3.2
- ✅ Configuración con Gradle Kotlin DSL
- ✅ Estructura **multimódulo** siguiendo principios de **arquitectura hexagonal**
- ✅ Centralización de dependencias en `buildSrc/`
- ✅ Uso del plugin `spring-dependency-management` y BOM
- ✅ Tareas personalizadas en Gradle (`printVersion`, `lintAll`, etc.)
- ✅ Compatible con CI/CD y buenas prácticas de escalabilidad

## 🚀 ¿Cómo empezar?

```bash
./gradlew build              # Compila todos los módulos
./gradlew bootRun --project-dir application  # Ejecuta la app principal
./gradlew printVersion       # Tarea personalizada para mostrar la versión
```

## 📁 Módulos del proyecto

- `application`: Punto de entrada y capa de presentación
- `domain`: Lógica del negocio (vacío por ahora)
- `infrastructure`: Adaptadores externos (controladores, repositorios, etc.)
- `config`: Configuraciones transversales del sistema

## 📚 Relación con el eBook

Este código es la implementación directa del capítulo 8: *"Repositorio base ideal para escalar"*.

## 📜 Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).
