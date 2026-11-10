package mx.edu.uacm.is.slt.as.intermediario_polizas.controladores;

import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Cliente;
import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de clientes asegurados.
 * Expone endpoints HTTP para consultar, dar de alta y modificar clientes.
 * Delega la lógica de negocio a la capa de servicios.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    /**
     * Constructor que inyecta el servicio de clientes.
     *
     * @param service El servicio que contiene la lógica de negocio de clientes.
     */
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    /**
     * Consulta un cliente en el Sistema Dueño por su CURP.
     * Realiza una petición GET real al sistema remoto.
     *
     * @param curp El identificador único del cliente (18 caracteres).
     * @return Un ResponseEntity con el JSON del cliente o un error 503 si el sistema no responde.
     */
    @GetMapping("/{curp}")
    public ResponseEntity<String> consultar(@PathVariable String curp) {
        try {
            return ResponseEntity.ok(service.consultarCliente(curp));
        } catch (Exception e) {
            return ResponseEntity.status(503)
                    .body("{\"error\":\"Sistema Dueno no disponible\"}");
        }
    }

    /**
     * Registra un nuevo cliente.
     *
     * @param cliente El objeto Cliente con los datos a registrar.
     * @return Respuesta del Sistema Dueño.
     */
    @PostMapping
    public ResponseEntity<String> alta(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.altaCliente(cliente));
    }

    /**
     * Modifica los datos de un cliente existente.
     *
     * @param curp El identificador único del cliente a modificar.
     * @param cliente El objeto Cliente con los nuevos datos.
     * @return Respuesta del Sistema Dueño.
     */
    @PutMapping("/{curp}")
    public ResponseEntity<String> modificar(@PathVariable String curp,
                                             @RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.modificarCliente(curp, cliente));
    }
}