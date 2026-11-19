package mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones;

/** El Sistema Dueño no devolvió el recurso solicitado (respuesta vacía o nula). */
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
