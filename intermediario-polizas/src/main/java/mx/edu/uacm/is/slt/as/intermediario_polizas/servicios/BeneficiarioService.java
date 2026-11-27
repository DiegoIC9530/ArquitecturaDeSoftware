package mx.edu.uacm.is.slt.as.intermediario_polizas.servicios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoAdapter;
import mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones.OperacionNoConfirmadaException;
import mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones.RecursoNoEncontradoException;
import mx.edu.uacm.is.slt.as.intermediario_polizas.excepciones.RegistroDuplicadoException;
import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Beneficiario;
import org.springframework.stereotype.Service;

/**
 * Caso de uso: gestión de Beneficiarios.
 * La información se consulta y se modifica en el Sistema Dueño.
 */
@Service
public class BeneficiarioService {

    private final SistemaDuenoAdapter adaptador;

    public BeneficiarioService(SistemaDuenoAdapter adaptador) {
        this.adaptador = adaptador;
    }

    public String consultarBeneficiario(String fecha, String clave, String nombres,
                                        String primerApellido, String segundoApellido) {
        return adaptador.obtenerBeneficiario(fecha, clave, nombres, primerApellido, segundoApellido);
    }

    // ---------- ESCRITURA EN EL SISTEMA DUEÑO ----------

    public String altaBeneficiario(Beneficiario b) {
        if (b == null) {
            throw new IllegalArgumentException("Los datos del beneficiario son obligatorios");
        }
        requerido(b.getPoliza(), "poliza");
        requerido(b.getPoliza().getClave(), "poliza.clave");
        requerido(b.getFechaNacimiento(), "fechaNacimiento");
        requerido(b.getNombres(), "nombres");
        requerido(b.getPrimerApellido(), "primerApellido");
        requerido(b.getPorcentaje(), "porcentaje");
        if (b.getPorcentaje() < 1 || b.getPorcentaje() > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 1 y 100");
        }
        String fecha = b.getFechaNacimiento().toString();
        String clave = b.getPoliza().getClave().toString();
        if (!adaptador.existePoliza(clave)) {
            throw new RecursoNoEncontradoException("La póliza " + clave + " no existe");
        }
        if (adaptador.existeBeneficiario(fecha, clave, b.getNombres(), b.getPrimerApellido(), b.getSegundoApellido())) {
            throw new RegistroDuplicadoException("El beneficiario ya está registrado en la póliza " + clave);
        }
        adaptador.altaBeneficiario(b.getFechaNacimiento(), clave, b.getPorcentaje(),
                b.getNombres(), b.getPrimerApellido(), b.getSegundoApellido());
        if (!adaptador.existeBeneficiario(fecha, clave, b.getNombres(), b.getPrimerApellido(), b.getSegundoApellido())) {
            throw new OperacionNoConfirmadaException("El alta del beneficiario no se confirmó: "
                    + "el Sistema Dueño no devuelve el registro");
        }
        return adaptador.obtenerBeneficiario(fecha, clave, b.getNombres(), b.getPrimerApellido(), b.getSegundoApellido());
    }

    private static void requerido(Object valor, String campo) {
        if (valor == null || valor.toString().isBlank()) {
            throw new IllegalArgumentException("El campo '" + campo + "' es obligatorio");
        }
    }
}
