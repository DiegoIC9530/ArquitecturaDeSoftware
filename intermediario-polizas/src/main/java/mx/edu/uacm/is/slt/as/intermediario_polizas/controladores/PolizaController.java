package mx.edu.uacm.is.slt.as.intermediario_polizas.controladores;

import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza;
import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.PolizaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de Pólizas de seguro.
 * Expone los endpoints HTTP para consultar, registrar, modificar y dar de baja pólizas.
 * Delega la lógica de negocio a {@link PolizaService}.
 */
@RestController
@RequestMapping("/api/polizas")
public class PolizaController {

    private final PolizaService service;

    /**
     * Constructor que inyecta el servicio de pólizas.
     *
     * @param service El servicio que contiene la lógica de negocio de las pólizas.
     */
    public PolizaController(PolizaService service) {
        this.service = service;
    }

    /**
     * Consulta una póliza específica en el Sistema Dueño por su clave.
     *
     * @param clave El identificador único (UUID) de la póliza.
     * @return El JSON de la póliza o un error si no se encuentra o el sistema no responde.
     */
    @GetMapping("/{clave}")
    public ResponseEntity<String> consultarPorClave(@PathVariable String clave) {
        return ResponseEntity.ok(service.consultarPoliza(clave));
    }

    /**
     * Lista todas las pólizas registradas en el Sistema Dueño.
     *
     * @return El JSON con la lista de pólizas o un error 503.
     */
    @GetMapping
    public ResponseEntity<String> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    /**
     * Lista las pólizas asociadas a un cliente específico.
     *
     * @param curp La CURP del cliente asegurado.
     * @return El JSON con las pólizas del cliente o un error 503.
     */
    @GetMapping("/cliente/{curp}")
    public ResponseEntity<String> listarPorCliente(@PathVariable String curp) {
        return ResponseEntity.ok(service.listarPorCliente(curp));
    }

    /**
     * Lista las pólizas filtradas por su tipo.
     *
     * @param tipo El tipo de póliza (0=Auto, 1=Vida, 2=Médico).
     * @return El JSON con las pólizas del tipo especificado o un error 503.
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<String> listarPorTipo(@PathVariable int tipo) {
        return ResponseEntity.ok(service.listarPorTipo(tipo));
    }

    /**
     * Registra una nueva póliza.
     *
     * @param poliza El objeto Poliza con los datos a registrar.
     * @return Respuesta del Sistema Dueño.
     */
    @PostMapping
    public ResponseEntity<String> altaPoliza(@RequestBody Poliza poliza) {
        return ResponseEntity.ok(service.altaPoliza(poliza));
    }

    /**
     * Modifica una póliza existente.
     *
     * @param clave La clave única de la póliza a modificar.
     * @param poliza El objeto Poliza con los nuevos datos.
     * @return Respuesta del Sistema Dueño.
     */
    @PutMapping("/{clave}")
    public ResponseEntity<String> modificarPoliza(@PathVariable String clave,
                                                   @RequestBody Poliza poliza) {
        return ResponseEntity.ok(service.modificarPoliza(clave, poliza));
    }

    /**
     * Da de baja una póliza.
     *
     * @param clave La clave única de la póliza a eliminar.
     * @return Respuesta del Sistema Dueño.
     */
    @DeleteMapping("/{clave}")
    public ResponseEntity<String> bajaPoliza(@PathVariable String clave) {
        return ResponseEntity.ok(service.bajaPoliza(clave));
    }
}