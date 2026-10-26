package mx.edu.uacm.is.slt.as.intermediario_polizas.controladores;

import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Cliente;
import mx.edu.uacm.is.slt.as.intermediario_polizas.servicios.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    // GET → consulta real al Sistema Dueño
    @GetMapping("/{curp}")
    public ResponseEntity<String> consultar(@PathVariable String curp) {
        try {
            return ResponseEntity.ok(service.consultarCliente(curp));
        } catch (Exception e) {
            return ResponseEntity.status(503)
                    .body("{\"error\":\"Sistema Dueno no disponible\"}");
        }
    }

    // POST → simulado (permitido por la rubrica)
    @PostMapping
    public ResponseEntity<String> alta(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(
            "{\"status\":\"simulado\",\"mensaje\":\"Alta de cliente pendiente\"}"
        );
    }

    // PUT → simulado
    @PutMapping("/{curp}")
    public ResponseEntity<String> modificar(@PathVariable String curp,
                                             @RequestBody Cliente cliente) {
        return ResponseEntity.ok(
            "{\"status\":\"simulado\",\"mensaje\":\"Modificacion de cliente pendiente\"}"
        );
    }
}