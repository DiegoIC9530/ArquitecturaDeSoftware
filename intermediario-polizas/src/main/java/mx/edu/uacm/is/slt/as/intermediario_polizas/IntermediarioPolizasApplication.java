package mx.edu.uacm.is.slt.as.intermediario_polizas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de arranque de la aplicación Spring Boot Intermediario de Pólizas.
 * Implementa CommandLineRunner para ejecutar la interfaz de consola de muestra.
 */
@SpringBootApplication
public class IntermediarioPolizasApplication implements CommandLineRunner {

    /**
     * Método principal de entrada para la aplicación Spring Boot.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(IntermediarioPolizasApplication.class, args);
    }

    /**
     * Método ejecutado al iniciar la aplicación Spring Boot.
     * Hola esto es una prueba
     * @param args Argumentos pasados en la ejecución.
     * @throws Exception Si ocurre algún error durante la ejecución.
     */
    @Override
    public void run(String... args) throws Exception {
        // Interfaz de usuario de muestra requerida por la Práctica 2
        System.out.println("==================================================");
        System.out.println("  Hola mundo - Interfaz de Línea de Comandos");
        System.out.println("  Prototipo: Sistema Intermediario de Pólizas");
        System.out.println("==================================================");
    }
}
