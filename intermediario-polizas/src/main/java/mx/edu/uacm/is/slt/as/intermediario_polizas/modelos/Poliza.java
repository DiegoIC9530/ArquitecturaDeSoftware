package mx.edu.uacm.is.slt.as.intermediario_polizas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Entidad JPA que representa la tabla 'poliza' en la base de datos PostgreSQL.
 */
@Entity
@Table(name = "poliza")
public class Poliza {

    /**
     * Llave primaria (PK), identificador único UUID de la póliza.
     */
    @Id
    @Column(name = "clave", nullable = false, updatable = false)
    private UUID clave;

    /**
     * Tipo de póliza: 0 (Auto), 1 (Vida), 2 (Médico).
     */
    @Column(name = "tipo", nullable = false)
    private Integer tipo;

    /**
     * Monto de la póliza con precisión NUMERIC(9,2).
     */
    @Column(name = "monto", precision = 9, scale = 2, nullable = false)
    private BigDecimal monto;

    /**
     * Descripción o detalle de la póliza.
     */
    @Column(name = "descripcion", columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    /**
     * Relación Muchos a Uno con la entidad Cliente (Llave Foránea 'curp_cliente').
     */
    @ManyToOne
    @JoinColumn(name = "curp_cliente", referencedColumnName = "curp", nullable = false)
    private Cliente cliente;

    /**
     * Constructor vacío exigido por JPA.
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
