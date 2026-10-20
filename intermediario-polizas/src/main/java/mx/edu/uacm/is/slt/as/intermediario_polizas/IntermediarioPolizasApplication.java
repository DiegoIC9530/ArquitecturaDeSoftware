package mx.edu.uacm.is.slt.as.intermediario_polizas;

import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.BeneficiarioService;
import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.ClienteService;
import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.PolizaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

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
        System.out.println("\n--- [Alta de Cliente] ---");
        try {
            System.out.print("CURP (18 caracteres): ");
            String curp = leerCurpConValidacion(scanner); // USAMOS LA VALIDACIÓN AQUÍ
            
            System.out.print("Solo Nombres (sin apellidos): ");
            String nombres = scanner.nextLine().trim();
            System.out.print("Primer apellido: ");
            
            String primerApellido = scanner.nextLine().trim();
            System.out.print("Segundo apellido (opcional, presiona Enter si no tiene): ");
            String segundoApellido = scanner.nextLine().trim();
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine().trim();
            System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
            String fechaNacimiento = scanner.nextLine().trim();

            // Aquí iría la llamada al servicio del profesor cuando esté lista
            mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Cliente nuevoCliente = 
                    new mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Cliente(curp, nombres, primerApellido, segundoApellido, direccion, LocalDate.parse(fechaNacimiento));
                
                String respuesta = clienteService.registrarCliente(nuevoCliente);
                System.out.println("Respuesta del servidor: " + respuesta);
            System.out.println("¡Datos del cliente capturados correctamente para el envío!");
        } catch (Exception e) {
            System.out.println("Error al procesar el alta del cliente: " + e.getMessage());
        }
    }

    private void altaPoliza(Scanner scanner) {
        System.out.println("\n--- [Alta de Póliza] ---");
        try {
            System.out.print("Clave de póliza (UUID, ej. 550e8400-e29b-41d4-a716-446655440000): ");
            UUID clave = UUID.fromString(scanner.nextLine().trim());
            System.out.print("Tipo de póliza (0: Auto, 1: Vida, 2: Médico): ");
            Integer tipo = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Monto (ej. 1500.00): ");
            BigDecimal monto = new BigDecimal(scanner.nextLine().trim());
            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine().trim();
            
            System.out.print("CURP del cliente asociado (18 caracteres): ");
            String curpCliente = leerCurpConValidacion(scanner); // USAMOS LA VALIDACIÓN AQUÍ

            // Aquí iría la llamada al servicio del profesor cuando esté lista
            mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Cliente clienteRef = new mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Cliente();
            clienteRef.setCurp(curpCliente);
            
            mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza nuevaPoliza = 
                new mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza(clave, tipo, monto, descripcion, clienteRef);
            
            String respuesta = polizaService.registrarPoliza(nuevaPoliza);
            System.out.println("Respuesta del servidor: " + respuesta);
        
            System.out.println("¡Datos de la póliza capturados y listos para envío!");
        } catch (Exception e) {
            System.out.println("Error al procesar el alta de la póliza: " + e.getMessage());
        }
    }
//
    private void altaBeneficiario(Scanner scanner) {
        System.out.println("\n--- [Alta de Beneficiario] ---");
        try {
            System.out.print("Nombres del beneficiario: ");
            String nombres = scanner.nextLine().trim();
            System.out.print("Primer apellido: ");
            String primerApellido = scanner.nextLine().trim();
            System.out.print("Segundo apellido (opcional): ");
            String segundoApellido = scanner.nextLine().trim();
            System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
            LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Porcentaje asignado (ej. 100): ");
            Integer porcentaje = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Clave de la póliza asociada (UUID): ");
            UUID clavePoliza = UUID.fromString(scanner.nextLine().trim());

            // Aquí iría la llamada al servicio del profesor cuando esté lista
            mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza polizaRef = new mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza();
            polizaRef.setClave(clavePoliza);
//
            mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Beneficiario nuevoBeneficiario = 
                new mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Beneficiario(nombres, primerApellido, segundoApellido, fechaNacimiento, porcentaje, polizaRef);
            
            String respuesta = beneficiarioService.registrarBeneficiario(nuevoBeneficiario);
            System.out.println("Respuesta del servidor: " + respuesta);
            System.out.println("¡Datos del beneficiario capturados y listos para envío!");
        } catch (Exception e) {
            System.out.println("Error al procesar el alta del beneficiario: " + e.getMessage());
        }
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
        System.out.println("\n--- [Modificación de Cliente] ---");
        try {
            System.out.print("Ingrese la CURP del cliente a modificar (18 caracteres): ");
            String curp = leerCurpConValidacion(scanner);
            
            System.out.println("\n¿Qué dato deseas modificar?");
            System.out.println("1. Nombres");
            System.out.println("2. Primer Apellido");
            System.out.println("3. Segundo Apellido");
            System.out.println("4. Dirección");
            System.out.println("5. Fecha de nacimiento");
            System.out.println("0. Cancelar");
            System.out.print("Elige una opción: ");
            
            String opcionMod = scanner.nextLine().trim();
            String campoAModificar = "";
            String nuevoValor = "";

            switch (opcionMod) {
                case "1":
                    campoAModificar = "Nombres";
                    System.out.print("Ingresa el nuevo Nombre: ");
                    nuevoValor = scanner.nextLine().trim();
                    break;
                case "2":
                    campoAModificar = "Primer Apellido";
                    System.out.print("Ingresa el nuevo Primer Apellido: ");
                    nuevoValor = scanner.nextLine().trim();
                    break;
                case "3":
                    campoAModificar = "Segundo Apellido";
                    System.out.print("Ingresa el nuevo Segundo Apellido: ");
                    nuevoValor = scanner.nextLine().trim();
                    break;
                case "4":
                    campoAModificar = "Dirección";
                    System.out.print("Ingresa la nueva Dirección: ");
                    nuevoValor = scanner.nextLine().trim();
                    break;
                case "5":
                    campoAModificar = "Fecha de nacimiento";
                    System.out.print("Ingresa la nueva Fecha de nacimiento (YYYY-MM-DD): ");
                    nuevoValor = scanner.nextLine().trim();
                    break;
                case "0":
                    System.out.println("Modificación cancelada por el usuario.");
                    return; // Termina el método y regresa al menú principal
                default:
                    System.out.println("Opción no válida. Cancelando modificación.");
                    return;
            }
            
            // NOTA PARA LA INTEGRACIÓN:
            // Dependiendo de cómo el profesor configure el endpoint (PUT o PATCH), 
            // aquí deberás enviar la CURP, el campo que se modificó y el nuevo valor.
            // Ejemplo: clienteService.modificarDato(curp, opcionMod, nuevoValor);
            
            System.out.println("¡Modificación capturada! El campo '" + campoAModificar + "' del cliente " + curp + " cambiará a: " + nuevoValor);
        } catch (Exception e) {
            System.out.println("Error al modificar el cliente: " + e.getMessage());
        }
    }

    private void modificarPoliza(Scanner scanner) {
        System.out.println("\n--- [Modificación de Póliza] ---");
        try {
            System.out.print("Ingrese la Clave (UUID) de la póliza a modificar: ");
            UUID clave = UUID.fromString(scanner.nextLine().trim());
            System.out.print("Nuevo Monto (deja en blanco si no cambia): ");
            String nuevoMontoStr = scanner.nextLine().trim();
            System.out.print("Nueva Descripción (deja en blanco si no cambia): ");
            String nuevaDescripcion = scanner.nextLine().trim();
            
            System.out.println("¡Datos de actualización para la póliza " + clave + " capturados!");
        } catch (Exception e) {
            System.out.println("Error al modificar la póliza: " + e.getMessage());
        }
    }

    private void bajaPoliza(Scanner scanner) {
        System.out.println("\n--- [Baja de Póliza] ---");
        try {
            System.out.print("Ingrese la Clave (UUID) de la póliza a dar de baja: ");
            UUID clave = UUID.fromString(scanner.nextLine().trim());
            System.out.print("¿Estás seguro de eliminar esta póliza? (S/N): ");
            String confirmacion = scanner.nextLine().trim();
            
            if(confirmacion.equalsIgnoreCase("S")) {
                // Aquí hacemos la petición DELETE real
                String respuesta = polizaService.eliminarPoliza(clave);
                System.out.println("Respuesta del servidor: " + respuesta);
                System.out.println("¡Solicitud de baja para la póliza " + clave + " procesada!");
            } else {
                System.out.println("Baja cancelada.");
            }
        } catch (Exception e) {
            System.out.println("Error al dar de baja la póliza: " + e.getMessage());
        }
    }
    // MÉTODOS AUXILIARES DE VALIDACIÓN

    /**
     * Valida que la CURP tenga exactamente 18 caracteres.
     * Otorga un intento inicial y 2 reintentos en caso de equivocación.
     */
    private String leerCurpConValidacion(Scanner scanner) throws Exception {
        int intentosRestantes = 2; // Tiene 1 intento inicial + 2 oportunidades extra = 3 en total
        
        while (true) {
            String curp = scanner.nextLine().trim();
            
            if (curp.length() == 18) {
                return curp.toUpperCase(); // Retorna la CURP válida y en mayúsculas
            } else {
                if (intentosRestantes > 0) {
                    System.out.println(">> ERROR: La CURP ingresada tiene " + curp.length() + " caracteres. Debe tener exactamente 18.");
                    System.out.print(">> Vuelve a intentarlo (te quedan " + intentosRestantes + " intentos): ");
                    intentosRestantes--;
                } else {
                    throw new Exception("Se agotaron los intentos para capturar una CURP válida. Operación cancelada.");
                }
            }
        }
    }
}