# 10. Testing e Integración Continua con Gradle

Una base profesional no solo debe compilar correctamente: también debe contar con validaciones automáticas de calidad y pruebas. En este módulo te mostramos cómo integrar **testing automatizado** y **CI (Integración Continua)** usando herramientas modernas como Gradle, JUnit, JaCoCo y GitHub Actions.

---

## 🧪 Testing con JUnit y Gradle

Asegurate de incluir la dependencia en tu archivo `build.gradle.kts` común:

```kotlin
dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
```

Para ejecutar los tests:

```bash
./gradlew test
```

---

## 📈 Cobertura con JaCoCo

Agregá el plugin en el `build.gradle.kts` raíz:

```kotlin
plugins {
    jacoco
}

tasks.jacocoTestReport {
    dependsOn(test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
```

Luego ejecutá:

```bash
./gradlew jacocoTestReport
```

El reporte se genera en `build/reports/jacoco/test/html/index.html`.

---

## 🤖 Integración Continua (CI) con GitHub Actions

Creá el archivo `.github/workflows/ci.yml`:

```yaml
name: Java CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK
      uses: actions/setup-java@v3
      with:
        java-version: '23'
        distribution: 'temurin'

    - name: Build with Gradle
      run: ./gradlew build

    - name: Run tests
      run: ./gradlew test

    - name: Generate coverage report
      run: ./gradlew jacocoTestReport
```

---

## 🛠️ Tareas Gradle útiles

Podés agregar tareas personalizadas como esta en `buildSrc`:

```kotlin
tasks.register("testCoverage") {
    group = "verification"
    description = "Ejecuta tests y genera reporte de cobertura"
    dependsOn("test", "jacocoTestReport")
}
```

---

## ✅ Conclusión

La integración de testing y CI garantiza que tu código sea confiable, verificable y listo para producción desde el primer commit. Es un paso esencial para cualquier proyecto profesional.