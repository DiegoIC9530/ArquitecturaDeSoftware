package mx.edu.uacm.is.slt.as.intermediario_polizas.controladores;

import mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones.OperacionNoConfirmadaException;
import mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones.RecursoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

/**
 * Traduce los errores de la aplicación a respuestas HTTP claras.
 * - Registro inexistente: 404.
 * - Escritura no confirmada por el Sistema Dueño: 502.
 * - Error devuelto por el Sistema Dueño: se reenvía su mismo código y mensaje.
 * - Sistema Dueño inalcanzable: 503.
 * - Datos inválidos en la petición: 400.
 */
@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> noEncontrado(RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"error\":\"" + mensaje(e) + "\"}");
    }

    @ExceptionHandler(OperacionNoConfirmadaException.class)
    public ResponseEntity<String> noConfirmada(OperacionNoConfirmadaException e) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("{\"error\":\"" + mensaje(e) + "\"}");
    }

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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("{\"error\":\"" + mensaje(e) + "\"}");
    }

    private static String mensaje(Exception e) {
        return String.valueOf(e.getMessage()).replace("\"", "'");
    }
}
