package mx.edu.uacm.is.slt.as.intermediario_polizas.controladores;

import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Beneficiario;
import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.BeneficiarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

    private final BeneficiarioService service;

    public BeneficiarioController(BeneficiarioService service) {
        this.service = service;
    }

    @GetMapping("/{fecha}/{clave}/{nombres}/{apellido}")
    public ResponseEntity<String> consultar(@PathVariable String fecha,
                                             @PathVariable String clave,
                                             @PathVariable String nombres,
                                             @PathVariable String apellido) {
        try {
            return ResponseEntity.ok(
                service.consultarBeneficiario(fecha, clave, nombres, apellido)
            );
        } catch (Exception e) {
            return ResponseEntity.status(503)
                    .body("{\"error\":\"Sistema Dueno no disponible\"}");
        }
    }

    @PostMapping
    public ResponseEntity<String> alta(@RequestBody Beneficiario beneficiario) {
        return ResponseEntity.ok(
            "{\"status\":\"simulado\",\"mensaje\":\"Alta de beneficiario pendiente\"}"
        );
    }
}