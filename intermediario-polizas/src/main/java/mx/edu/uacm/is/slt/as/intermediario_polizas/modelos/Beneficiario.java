package mx.edu.uacm.is.slt.as.intermediario_polizas.modelos;

import java.time.LocalDate;

/**
 * Datos del beneficiario de una póliza, tal como los maneja el Sistema Dueño.
 */
public class Beneficiario {

    /**
     * Nombres del beneficiario.
     */
    private String nombres;

    /**
     * Primer apellido del beneficiario.
     */
    private String primerApellido;

    /**
     * Segundo apellido del beneficiario (opcional).
     */
    private String segundoApellido;

    /**
     * Fecha de nacimiento del beneficiario.
     */
    private LocalDate fechaNacimiento;

    /**
     * Porcentaje asignado de la póliza.
     */
    private Integer porcentaje;

    /**
     * Póliza a la que pertenece el beneficiario.
     */
    private Poliza poliza;

    /**
     * Constructor por defecto, necesario para convertir los datos JSON.
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
