package mx.edu.uacm.is.slt.as.intermediario_polizas.controladores;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoNoDisponible;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ManejadorExcepcionesGlobal {
	@ExceptionHandler(SistemaDuenoNoDisponible.class)
    public ResponseEntity<String> manejarSistemaDuenoNoDisponible(
            SistemaDuenoNoDisponible e) {
        return ResponseEntity.status(503)
                .body("{\"error\":\"Sistema Dueno no disponible\"}");
    }
	
	@ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> manejarClienteNoRegistrado(NullPointerException e) {
        return ResponseEntity.status(404)
                .body("{\"error\":\"El cliente o registro solicitado no existe.\"}");
    }
	
}
