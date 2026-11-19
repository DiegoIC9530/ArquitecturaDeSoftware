package mx.edu.uacm.is.slt.as.intermediario_polizas.controladores;

import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Beneficiario;
import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.BeneficiarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de Beneficiarios de las pólizas.
 * Expone los endpoints HTTP para consultar y registrar beneficiarios.
 * Delega la lógica de negocio a {@link BeneficiarioService}.
 */
@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

    private final BeneficiarioService service;

    /**
     * Constructor que inyecta el servicio de beneficiarios.
     *
     * @param service El servicio que contiene la lógica de negocio de los beneficiarios.
     */
    public BeneficiarioController(BeneficiarioService service) {
        this.service = service;
    }

    /**
     * Consulta el registro de un beneficiario en el Sistema Dueño.
     *
     * @param fecha La fecha de nacimiento del beneficiario.
     * @param clave La clave de la póliza a la que pertenece.
     * @param nombres Los nombres del beneficiario.
     * @param apellido El primer apellido del beneficiario.
     * @return El JSON con los datos del beneficiario o un error 503 si falla.
     */
    @GetMapping("/{fecha}/{clave}/{nombres}/{apellido}")
    public ResponseEntity<String> consultar(@PathVariable String fecha,
                                             @PathVariable String clave,
                                             @PathVariable String nombres,
                                             @PathVariable String apellido) {
        return ResponseEntity.ok(
            service.consultarBeneficiario(fecha, clave, nombres, apellido, null)
        );
    }

    /** Consulta un beneficiario que tiene segundo apellido. */
    @GetMapping("/{fecha}/{clave}/{nombres}/{apellido}/{segundo}")
    public ResponseEntity<String> consultarConSegundo(@PathVariable String fecha,
                                                      @PathVariable String clave,
                                                      @PathVariable String nombres,
                                                      @PathVariable String apellido,
                                                      @PathVariable String segundo) {
        return ResponseEntity.ok(
            service.consultarBeneficiario(fecha, clave, nombres, apellido, segundo)
        );
    }

    /**
     * Registra un nuevo beneficiario.
     *
     * @param beneficiario El objeto Beneficiario con los datos a registrar.
     * @return Respuesta del Sistema Dueño.
     */
    @PostMapping
    public ResponseEntity<String> alta(@RequestBody Beneficiario beneficiario) {
        return ResponseEntity.ok(service.altaBeneficiario(beneficiario));
    }
}