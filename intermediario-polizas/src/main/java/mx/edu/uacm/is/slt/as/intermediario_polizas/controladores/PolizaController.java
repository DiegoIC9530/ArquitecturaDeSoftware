package mx.edu.uacm.is.slt.as.intermediario_polizas.controladores;

import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza;
import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.PolizaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polizas")
public class PolizaController {

    private final PolizaService service;

    public PolizaController(PolizaService service) {
        this.service = service;
    }

    @GetMapping("/{clave}")
    public ResponseEntity<String> consultarPorClave(@PathVariable String clave) {
        try {
            return ResponseEntity.ok(service.consultarPoliza(clave));
        } catch (Exception e) {
            return ResponseEntity.status(503)
                    .body("{\"error\":\"Sistema Dueno no disponible\"}");
        }
    }

    @GetMapping
    public ResponseEntity<String> listarTodas() {
        try {
            return ResponseEntity.ok(service.listarTodas());
        } catch (Exception e) {
            return ResponseEntity.status(503)
                    .body("{\"error\":\"Sistema Dueno no disponible\"}");
        }
    }

    @GetMapping("/cliente/{curp}")
    public ResponseEntity<String> listarPorCliente(@PathVariable String curp) {
        try {
            return ResponseEntity.ok(service.listarPorCliente(curp));
        } catch (Exception e) {
            return ResponseEntity.status(503)
                    .body("{\"error\":\"Sistema Dueno no disponible\"}");
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<String> listarPorTipo(@PathVariable int tipo) {
        try {
            return ResponseEntity.ok(service.listarPorTipo(tipo));
        } catch (Exception e) {
            return ResponseEntity.status(503)
                    .body("{\"error\":\"Sistema Dueno no disponible\"}");
        }
    }

    @PostMapping
    public ResponseEntity<String> altaPoliza(@RequestBody Poliza poliza) {
        return ResponseEntity.ok(
            "{\"status\":\"simulado\",\"mensaje\":\"Alta de poliza pendiente\"}"
        );
    }

    @PutMapping("/{clave}")
    public ResponseEntity<String> modificarPoliza(@PathVariable String clave,
                                                   @RequestBody Poliza poliza) {
        return ResponseEntity.ok(
            "{\"status\":\"simulado\",\"mensaje\":\"Modificacion de poliza pendiente\"}"
        );
    }

    @DeleteMapping("/{clave}")
    public ResponseEntity<String> bajaPoliza(@PathVariable String clave) {
        return ResponseEntity.ok(
            "{\"status\":\"simulado\",\"mensaje\":\"Baja de poliza pendiente\"}"
        );
    }
}