package mx.edu.uacm.is.slt.as.intermediario_polizas.servicios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoAdapter;
import mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones.OperacionNoConfirmadaException;
import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza;
import org.springframework.stereotype.Service;

/**
 * Caso de uso: gestión de Pólizas de seguro.
 * La información se consulta y se modifica en el Sistema Dueño.
 */
@Service
public class PolizaService {

    private final SistemaDuenoAdapter adaptador;

    public PolizaService(SistemaDuenoAdapter adaptador) {
        this.adaptador = adaptador;
    }

    public String consultarPoliza(String clave) {
        return adaptador.obtenerPoliza(clave);
    }

    public String listarTodas() {
        return adaptador.obtenerTodasLasPolizas();
    }

    public String listarPorCliente(String curp) {
        return adaptador.obtenerPolizasPorCliente(curp);
    }

    public String listarPorTipo(int tipo) {
        return adaptador.obtenerPolizasPorTipo(tipo);
    }

    // ---------- ESCRITURA EN EL SISTEMA DUEÑO ----------

    public String altaPoliza(Poliza p) {
        validarPoliza(p);
        requerido(p.getClave(), "clave");
        adaptador.altaPoliza(p.getClave().toString(), p.getTipo(), p.getMonto().toPlainString(),
                p.getDescripcion(), p.getCliente().getCurp());
        return confirmar(p.getClave().toString(), "El alta");
    }

    public String modificarPoliza(String clave, Poliza p) {
        validarPoliza(p);
        adaptador.modificarPoliza(clave, p.getTipo(), p.getMonto().toPlainString(),
                p.getDescripcion(), p.getCliente().getCurp());
        return confirmar(clave, "La modificación");
    }

    public String bajaPoliza(String clave) {
        if (clave == null || clave.isBlank()) {
            throw new IllegalArgumentException("La clave de la póliza es obligatoria");
        }
        adaptador.bajaPoliza(clave);
        if (adaptador.existePoliza(clave)) {
            throw new OperacionNoConfirmadaException("La baja de la póliza " + clave
                    + " no se confirmó: el registro sigue existiendo");
        }
        return "{\"resultado\":\"Baja confirmada\",\"clave\":\"" + clave.replace("\"", "") + "\"}";
    }

    /** Vuelve a consultar la póliza: si no existe, la escritura no se confirma. */
    private String confirmar(String clave, String operacion) {
        if (!adaptador.existePoliza(clave)) {
            throw new OperacionNoConfirmadaException(operacion + " de la póliza " + clave
                    + " no se confirmó: el Sistema Dueño no devuelve el registro");
        }
        return adaptador.obtenerPoliza(clave);
    }

    private void validarPoliza(Poliza p) {
        if (p == null) {
            throw new IllegalArgumentException("Los datos de la póliza son obligatorios");
        }
        requerido(p.getTipo(), "tipo");
        if (p.getTipo() < 0 || p.getTipo() > 2) {
            throw new IllegalArgumentException("El tipo debe ser 0 (auto), 1 (vida) o 2 (médico)");
        }
        requerido(p.getMonto(), "monto");
        if (p.getMonto().signum() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        requerido(p.getDescripcion(), "descripcion");
        if (p.getCliente() == null) {
            throw new IllegalArgumentException("La póliza debe indicar el cliente (curp)");
        }
        requerido(p.getCliente().getCurp(), "cliente.curp");
    }

    private static void requerido(Object valor, String campo) {
        if (valor == null || valor.toString().isBlank()) {
            throw new IllegalArgumentException("El campo '" + campo + "' es obligatorio");
        }
    }
}
