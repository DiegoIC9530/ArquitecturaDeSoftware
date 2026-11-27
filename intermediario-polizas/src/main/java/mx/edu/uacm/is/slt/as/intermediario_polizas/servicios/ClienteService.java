package mx.edu.uacm.is.slt.as.intermediario_polizas.servicios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoAdapter;
import mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones.OperacionNoConfirmadaException;
import mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones.RegistroDuplicadoException;
import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Cliente;
import org.springframework.stereotype.Service;

/**
 * Caso de uso: gestión de Clientes asegurados.
 * La información se consulta y se modifica en el Sistema Dueño.
 */
@Service
public class ClienteService {

    private final SistemaDuenoAdapter adaptador;

    public ClienteService(SistemaDuenoAdapter adaptador) {
        this.adaptador = adaptador;
    }

    public String consultarCliente(String curp) {
        return adaptador.obtenerCliente(curp);
    }

    // ---------- ESCRITURA EN EL SISTEMA DUEÑO ----------

    public String altaCliente(Cliente c) {
        validarCliente(c);
        if (adaptador.existeCliente(c.getCurp())) {
            throw new RegistroDuplicadoException("El cliente con CURP " + c.getCurp() + " ya está registrado");
        }
        adaptador.altaCliente(c.getCurp(), c.getDireccion(), c.getFechaNacimiento(),
                c.getNombres(), c.getPrimerApellido(), c.getSegundoApellido());
        return confirmar(c.getCurp(), "El alta");
    }

    public String modificarCliente(String curp, Cliente c) {
        validarCliente(c);
        if (!curp.equals(c.getCurp())) {
            throw new IllegalArgumentException("La CURP del cuerpo (" + c.getCurp() + ") no coincide con la de la dirección (" + curp + ")");
        }
        adaptador.modificarCliente(curp, c.getDireccion(), c.getFechaNacimiento(),
                c.getNombres(), c.getPrimerApellido(), c.getSegundoApellido());
        return confirmar(curp, "La modificación");
    }

    /** Vuelve a consultar el cliente: si no existe, la escritura no se confirma. */
    private String confirmar(String curp, String operacion) {
        if (!adaptador.existeCliente(curp)) {
            throw new OperacionNoConfirmadaException(operacion + " del cliente " + curp
                    + " no se confirmó: el Sistema Dueño no devuelve el registro");
        }
        return adaptador.obtenerCliente(curp);
    }

    private void validarCliente(Cliente c) {
        if (c == null) {
            throw new IllegalArgumentException("Los datos del cliente son obligatorios");
        }
        requerido(c.getCurp(), "curp");
        if (c.getCurp().length() != 18) {
            throw new IllegalArgumentException("La CURP debe tener 18 caracteres");
        }
        requerido(c.getNombres(), "nombres");
        requerido(c.getPrimerApellido(), "primerApellido");
        requerido(c.getDireccion(), "direccion");
        requerido(c.getFechaNacimiento(), "fechaNacimiento");
    }

    private static void requerido(Object valor, String campo) {
        if (valor == null || valor.toString().isBlank()) {
            throw new IllegalArgumentException("El campo '" + campo + "' es obligatorio");
        }
    }
}
