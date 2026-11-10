package mx.edu.uacm.is.slt.as.intermediario_polizas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de arranque de la aplicacion Spring Boot.
 * La interaccion con el sistema se realiza mediante la aplicacion web (API REST y pagina en /).
 */
@SpringBootApplication
public class IntermediarioPolizasApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntermediarioPolizasApplication.class, args);
    }
}
