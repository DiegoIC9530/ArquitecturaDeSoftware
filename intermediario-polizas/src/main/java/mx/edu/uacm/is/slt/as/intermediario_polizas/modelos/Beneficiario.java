package mx.edu.uacm.is.slt.as.intermediario_polizas.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * Entidad JPA que representa la tabla 'beneficiario_poliza' en la base de datos PostgreSQL.
 */
@Entity
@Table(name = "beneficiario_poliza")
public class Beneficiario {

    /**
     * Identificador autogenerado (PK subrogada exigida por JPA).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombres del beneficiario.
     */
    @Column(name = "nombres", length = 128, nullable = false)
    private String nombres;

    /**
     * Primer apellido del beneficiario.
     */
    @Column(name = "primer_apellido", length = 128, nullable = false)
    private String primerApellido;

    /**
     * Segundo apellido del beneficiario (opcional).
     */
    @Column(name = "segundo_apellido", length = 128, nullable = true)
    private String segundoApellido;

    /**
     * Fecha de nacimiento del beneficiario.
     */
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    /**
     * Porcentaje asignado de la póliza.
     */
    @Column(name = "porcentaje", nullable = false)
    private Integer porcentaje;

    /**
     * Relación Muchos a Uno con la entidad Póliza (Llave Foránea 'clave_poliza').
     */
    @ManyToOne
    @JoinColumn(name = "clave_poliza", referencedColumnName = "clave", nullable = false)
    private Poliza poliza;

    /**
     * Constructor vacío exigido por JPA.
     */
    public Beneficiario() {
    }

    /**
     * Constructor parametrizado.
     *
     * @param nombres Nombres del beneficiario.
     * @param primerApellido Primer apellido.
     * @param segundoApellido Segundo apellido.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param porcentaje Porcentaje de póliza.
     * @param poliza Póliza asociada.
     */
    public Beneficiario(String nombres, String primerApellido, String segundoApellido,
                        LocalDate fechaNacimiento, Integer porcentaje, Poliza poliza) {
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.porcentaje = porcentaje;
        this.poliza = poliza;
    }

    /**
     * Obtiene el ID del beneficiario.
     *
     * @return el ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del beneficiario.
     *
     * @param id el ID a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene los nombres del beneficiario.
     *
     * @return los nombres.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del beneficiario.
     *
     * @param nombres los nombres a asignar.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el primer apellido del beneficiario.
     *
     * @return el primer apellido.
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * Establece el primer apellido del beneficiario.
     *
     * @param primerApellido el primer apellido a asignar.
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * Obtiene el segundo apellido del beneficiario.
     *
     * @return el segundo apellido.
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * Establece el segundo apellido del beneficiario.
     *
     * @param segundoApellido el segundo apellido a asignar.
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * Obtiene la fecha de nacimiento del beneficiario.
     *
     * @return la fecha de nacimiento.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del beneficiario.
     *
     * @param fechaNacimiento la fecha a asignar.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el porcentaje asignado de la póliza.
     *
     * @return el porcentaje.
     */
    public Integer getPorcentaje() {
        return porcentaje;
    }

    /**
     * Establece el porcentaje asignado de la póliza.
     *
     * @param porcentaje el porcentaje a asignar.
     */
    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    /**
     * Obtiene la póliza asociada.
     *
     * @return la póliza.
     */
    public Poliza getPoliza() {
        return poliza;
    }

    /**
     * Establece la póliza asociada.
     *
     * @param poliza la póliza a asignar.
     */
    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }
}
