package mx.edu.uacm.is.slt.as.intermediario_polizas.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

/**
 * Traduce los errores de las operaciones de escritura a respuestas HTTP claras.
 * - Error devuelto por el Sistema Dueño: se reenvía su mismo código y mensaje.
 * - Sistema Dueño inalcanzable: 503.
 * - Datos inválidos en la petición: 400.
 */
@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<String> errorDelSistemaDueno(RestClientResponseException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<String> sistemaDuenoNoDisponible(RestClientException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("{\"error\":\"Sistema Dueno no disponible\"}");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> datosInvalidos(IllegalArgumentException e) {
        String mensaje = String.valueOf(e.getMessage()).replace("\"", "'");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("{\"error\":\"" + mensaje + "\"}");
    }
}
