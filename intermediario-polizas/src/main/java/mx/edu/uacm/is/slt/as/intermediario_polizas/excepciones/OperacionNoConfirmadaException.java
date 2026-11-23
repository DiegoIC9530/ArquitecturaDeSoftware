package mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones;

/** La escritura fue enviada, pero al volver a consultar el registro no se confirmó el resultado. */
public class OperacionNoConfirmadaException extends RuntimeException {
    public OperacionNoConfirmadaException(String mensaje) {
        super(mensaje);
    }
}
