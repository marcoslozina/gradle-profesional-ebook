# 3. Configuración del entorno

### Objetivo
Antes de comenzar con el desarrollo, es fundamental configurar correctamente el entorno para compilar, ejecutar y depurar proyectos Java modernos sin inconvenientes.

---

### Requisitos previos

* **Java Development Kit (JDK):** versión 23
* **Gradle:** versión 8.7 o superior
* **IDE recomendado:** IntelliJ IDEA (Community o Ultimate)

---

### Instalación y configuración (Linux/macOS)

```bash
# Descargar Gradle manualmente
wget https://services.gradle.org/distributions/gradle-8.7-bin.zip

# Extraer y mover a /opt
sudo unzip gradle-8.7-bin.zip -d /opt/gradle

# Configurar variables de entorno
echo 'export GRADLE_HOME=/opt/gradle/gradle-8.7' >> ~/.bashrc
echo 'export PATH=$PATH:$GRADLE_HOME/bin' >> ~/.bashrc

# Configurar JAVA_HOME si es necesario
echo 'export JAVA_HOME=/usr/lib/jvm/java-23' >> ~/.bashrc
echo 'export PATH=$PATH:$JAVA_HOME/bin' >> ~/.bashrc

# Aplicar los cambios
source ~/.bashrc
```

### Verificación

```bash
java -version        # Esperado: 23.x
gradle -v            # Esperado: 8.7 o superior
echo $JAVA_HOME      # Esperado: /usr/lib/jvm/java-23
echo $GRADLE_HOME    # Esperado: /opt/gradle/gradle-8.7
```
💡 En Windows, se recomienda usar Git Bash o WSL para una experiencia más fluida y evitar problemas de entorno.

## Configuración en IntelliJ IDEA

1. Abrí **IntelliJ IDEA** y seleccioná **"Open"** o **"Import Project"**.
2. Seleccioná la **carpeta raíz** del proyecto.
3. IntelliJ detectará `build.gradle.kts` y te pedirá importar el proyecto como **Gradle**.
4. Verificá en **Project Structure** que esté configurado lo siguiente:
    - ✅ JDK 23 como **Project SDK**
    - ✅ Gradle desde el **sistema** (no wrapper)
    - ✅ DSL de Kotlin (`.kts`), no Groovy

---

### Atajos y comandos útiles

| **Acción**                      | **Ubicación en IntelliJ**                                                   |
| ------------------------------ | ---------------------------------------------------------------------------- |
| Recargar configuración Gradle  | Botón **"Refresh"** en Gradle Tool Window                                   |
| Configurar JDK del proyecto    | `File → Project Structure → Project SDK`                                    |
| Variables de entorno           | `Run → Edit Configurations → Environment Variables`                          |
| Ejecutar tareas Gradle         | Tool Window → **Gradle** → doble clic en la tarea                            |
| Build completo del proyecto    | `Ctrl + F9` o desde menú `Build → Rebuild`                                   |

---

### Comandos útiles de Gradle

| **Comando**                    | **Descripción**                                                 |
| ----------------------------- | --------------------------------------------------------------- |
| `./gradlew build`             | Compila el proyecto y ejecuta tests                             |
| `./gradlew clean`             | Limpia artefactos de compilación                                |
| `./gradlew test`              | Ejecuta los tests del proyecto                                  |
| `./gradlew dependencies`      | Muestra el árbol de dependencias                                |
| `./gradlew tasks`             | Lista todas las tareas disponibles                              |
| `./gradlew bootRun`           | Ejecuta una app Spring Boot (si aplica)                         |
| `./gradlew dependencyUpdates` | Muestra actualizaciones de dependencias (requiere plugin)       |

---

### Problemas frecuentes

* ❌ **No configurar `JAVA_HOME` o `GRADLE_HOME`** → errores al compilar o abrir el proyecto.
* ❌ **Tener múltiples versiones** de Java o Gradle activas → puede generar conflictos.
* ❌ **Usar SDKMAN con JDK 23** → posibles incompatibilidades si no está soportado.
* ❌ **Olvidar sincronizar el proyecto en IntelliJ** → usar `"Reload Gradle Project"`.
* ❌ **No seleccionar correctamente el DSL o el SDK** del sistema en IntelliJ.