package mx.edu.uacm.is.slt.as.intermediario_polizas.modelos;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Datos de la póliza de seguro, tal como los maneja el Sistema Dueño.
 */
public class Poliza {

    /**
     * identificador único UUID de la póliza.
     */
    private UUID clave;

    /**
     * Tipo de póliza: 0 (Auto), 1 (Vida), 2 (Médico).
     */
    private Integer tipo;

    /**
     * Monto de la póliza con precisión NUMERIC(9,2).
     */
    private BigDecimal monto;

    /**
     * Descripción o detalle de la póliza.
     */
    private String descripcion;

    /**
     * Cliente asegurado al que pertenece la póliza.
     */
    private Cliente cliente;

    /**
     * Constructor por defecto, necesario para convertir los datos JSON.
     */
    public Poliza() {
    }

    /**
     * Constructor parametrizado.
     *
     * @param clave Identificador UUID de la póliza.
     * @param tipo Tipo de póliza (0: Auto, 1: Vida, 2: Médico).
     * @param monto Monto numérico de la póliza.
     * @param descripcion Descripción de la póliza.
     * @param cliente Objeto Cliente asociado.
     */
    public Poliza(UUID clave, Integer tipo, BigDecimal monto, String descripcion, Cliente cliente) {
        this.clave = clave;
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.cliente = cliente;
    }

    /**
     * Obtiene la clave UUID de la póliza.
     *
     * @return la clave de la póliza.
     */
    public UUID getClave() {
        return clave;
    }

    /**
     * Establece la clave UUID de la póliza.
     *
     * @param clave la clave a asignar.
     */
    public void setClave(UUID clave) {
        this.clave = clave;
    }

    /**
     * Obtiene el tipo de póliza.
     *
     * @return el tipo de póliza (0, 1 o 2).
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de póliza.
     *
     * @param tipo el tipo a asignar.
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el monto de la póliza.
     *
     * @return el monto.
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la póliza.
     *
     * @param monto el monto a asignar.
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la descripción de la póliza.
     *
     * @return la descripción.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la póliza.
     *
     * @param descripcion la descripción a asignar.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el cliente asociado a la póliza.
     *
     * @return el cliente.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente asociado a la póliza.
     *
     * @param cliente el cliente a asignar.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
