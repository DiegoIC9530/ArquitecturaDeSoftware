package mx.edu.uacm.is.slt.as.intermediario_polizas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * Entidad JPA que representa la tabla 'cliente' en la base de datos PostgreSQL.
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    /**
     * Llave primaria (PK), CURP del cliente (longitud 18).
     */
    @Id
    @Column(name = "curp", length = 18, nullable = false, unique = true)
    private String curp;

    /**
     * Nombres del cliente.
     */
    @Column(name = "nombres", length = 128, nullable = false)
    private String nombres;

    /**
     * Primer apellido del cliente.
     */
    @Column(name = "primer_apellido", length = 128, nullable = false)
    private String primerApellido;

    /**
     * Segundo apellido del cliente (opcional).
     */
    @Column(name = "segundo_apellido", length = 128, nullable = true)
    private String segundoApellido;

    /**
     * Dirección física del cliente.
     */
    @Column(name = "direccion", length = 512, nullable = false)
    private String direccion;

    /**
     * Fecha de nacimiento del cliente.
     */
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    /**
     * Constructor por defecto exigido por JPA.
     */
    public Cliente() {
    }

    /**
     * Constructor con todos los campos parametrizados.
     *
     * @param curp CURP del cliente.
     * @param nombres Nombres del cliente.
     * @param primerApellido Primer apellido del cliente.
     * @param segundoApellido Segundo apellido del cliente.
     * @param direccion Dirección del cliente.
     * @param fechaNacimiento Fecha de nacimiento del cliente.
     */
    public Cliente(String curp, String nombres, String primerApellido, String segundoApellido, String direccion, LocalDate fechaNacimiento) {
        this.curp = curp;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene la CURP del cliente.
     *
     * @return la CURP del cliente.
     */
    public String getCurp() {
        return curp;
    }

    /**
     * Establece la CURP del cliente.
     *
     * @param curp la CURP a asignar.
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * Obtiene los nombres del cliente.
     *
     * @return los nombres.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del cliente.
     *
     * @param nombres los nombres a asignar.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el primer apellido del cliente.
     *
     * @return el primer apellido.
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * Establece el primer apellido del cliente.
     *
     * @param primerApellido el primer apellido a asignar.
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * Obtiene el segundo apellido del cliente.
     *
     * @return el segundo apellido.
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * Establece el segundo apellido del cliente.
     *
     * @param segundoApellido el segundo apellido a asignar.
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * Obtiene la dirección del cliente.
     *
     * @return la dirección.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del cliente.
     *
     * @param direccion la dirección a asignar.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la fecha de nacimiento del cliente.
     *
     * @return la fecha de nacimiento.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del cliente.
     *
     * @param fechaNacimiento la fecha de nacimiento a asignar.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
