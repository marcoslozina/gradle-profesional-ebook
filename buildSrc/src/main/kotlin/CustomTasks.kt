import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

tasks.register("printVersion") {
    group = "info"
    description = "Imprime la versión actual del proyecto"
    doLast {
        println("Versión del proyecto: ${project.version}")
    }
}

tasks.register("lintAll") {
    group = "verification"
    description = "Simula una verificación de estilo de código"
    doLast {
        println("Ejecutando linting en todos los módulos... (simulado)")
    }
}
