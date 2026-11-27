package mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones;

public class RegistroDuplicadoException extends RuntimeException {

    public RegistroDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
