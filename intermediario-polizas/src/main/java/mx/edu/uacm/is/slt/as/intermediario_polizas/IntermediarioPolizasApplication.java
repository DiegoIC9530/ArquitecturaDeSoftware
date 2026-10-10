package mx.edu.uacm.is.slt.as.intermediario_polizas;

import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.BeneficiarioService;
import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.ClienteService;
import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.PolizaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * Clase principal de arranque de la aplicacion Spring Boot.
 * Implementa CommandLineRunner para exponer un menu de consola con los 8 casos de uso.
 */
@SpringBootApplication
public class IntermediarioPolizasApplication implements CommandLineRunner {

    private final ClienteService clienteService;
    private final PolizaService polizaService;
    private final BeneficiarioService beneficiarioService;

    public IntermediarioPolizasApplication(ClienteService clienteService,
                                            PolizaService polizaService,
                                            BeneficiarioService beneficiarioService) {
        this.clienteService = clienteService;
        this.polizaService = polizaService;
        this.beneficiarioService = beneficiarioService;
    }

    public static void main(String[] args) {
        SpringApplication.run(IntermediarioPolizasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n==================================================");
            System.out.println("   Sistema Intermediario de Polizas - Menu");
            System.out.println("==================================================");
            System.out.println("1. Alta de cliente");
            System.out.println("2. Alta de poliza");
            System.out.println("3. Alta de beneficiario");
            System.out.println("4. Consulta de poliza");
            System.out.println("5. Consulta de beneficiario");
            System.out.println("6. Modificacion de cliente");
            System.out.println("7. Modificacion de poliza");
            System.out.println("8. Baja de poliza");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1" -> altaCliente(scanner);
                case "2" -> altaPoliza(scanner);
                case "3" -> altaBeneficiario(scanner);
                case "4" -> consultaPoliza(scanner);
                case "5" -> consultaBeneficiario(scanner);
                case "6" -> modificarCliente(scanner);
                case "7" -> modificarPoliza(scanner);
                case "8" -> bajaPoliza(scanner);
                case "0" -> {
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                }
                default -> System.out.println("Opcion no valida.");
            }
        }
        scanner.close();
    }

    private void altaCliente(Scanner scanner) {
        System.out.println("\n[Alta de cliente]");
        System.out.print("CURP: ");
        scanner.nextLine().trim();
        System.out.println("Operacion simulada. Alta pendiente de implementar.");
    }

    private void altaPoliza(Scanner scanner) {
        System.out.println("\n[Alta de poliza]");
        System.out.print("Clave de poliza: ");
        scanner.nextLine().trim();
        System.out.println("Operacion simulada. Alta pendiente de implementar.");
    }

    private void altaBeneficiario(Scanner scanner) {
        System.out.println("\n[Alta de beneficiario]");
        System.out.print("Clave de poliza: ");
        scanner.nextLine().trim();
        System.out.println("Operacion simulada. Alta pendiente de implementar.");
    }

    private void consultaPoliza(Scanner scanner) {
        System.out.println("\n[Consulta de poliza]");
        System.out.print("Clave de poliza: ");
        String clave = scanner.nextLine().trim();
        try {
            String respuesta = polizaService.consultarPoliza(clave);
            System.out.println("Respuesta del Sistema Dueno:");
            System.out.println(respuesta);
        } catch (Exception e) {
            System.out.println("Error: Sistema Dueno no disponible - " + e.getMessage());
        }
    }

    private void consultaBeneficiario(Scanner scanner) {
        System.out.println("\n[Consulta de beneficiario]");
        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        String fecha = scanner.nextLine().trim();
        System.out.print("Clave de poliza: ");
        String clave = scanner.nextLine().trim();
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine().trim();
        System.out.print("Primer apellido: ");
        String apellido = scanner.nextLine().trim();
        try {
            String respuesta = beneficiarioService.consultarBeneficiario(fecha, clave, nombres, apellido);
            System.out.println("Respuesta del Sistema Dueno:");
            System.out.println(respuesta);
        } catch (Exception e) {
            System.out.println("Error: Sistema Dueno no disponible - " + e.getMessage());
        }
    }

    private void modificarCliente(Scanner scanner) {
        System.out.println("\n[Modificacion de cliente]");
        System.out.print("CURP: ");
        scanner.nextLine().trim();
        System.out.println("Operacion simulada. Modificacion pendiente de implementar.");
    }

    private void modificarPoliza(Scanner scanner) {
        System.out.println("\n[Modificacion de poliza]");
        System.out.print("Clave de poliza: ");
        scanner.nextLine().trim();
        System.out.println("Operacion simulada. Modificacion pendiente de implementar.");
    }

    private void bajaPoliza(Scanner scanner) {
        System.out.println("\n[Baja de poliza]");
        System.out.print("Clave de poliza: ");
        scanner.nextLine().trim();
        System.out.println("Operacion simulada. Baja pendiente de implementar.");
    }
}