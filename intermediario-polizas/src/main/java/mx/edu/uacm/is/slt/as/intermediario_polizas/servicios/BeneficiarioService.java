package mx.edu.uacm.is.slt.as.intermediario_polizas.servicios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoAdapter;
import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Beneficiario;
import mx.edu.uacm.is.slt.as.intermediario_polizas.repositorios.BeneficiarioRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Caso de uso: gestión de Beneficiarios.
 */
@Service
public class BeneficiarioService {

    private final SistemaDuenoAdapter adaptador;
    private final BeneficiarioRepository repositorio;

    public BeneficiarioService(SistemaDuenoAdapter adaptador, BeneficiarioRepository repositorio) {
        this.adaptador = adaptador;
        this.repositorio = repositorio;
    }

    public String consultarBeneficiario(String fecha, String clavePoliza,
                                         String nombres, String apellido) {
        return adaptador.obtenerBeneficiario(fecha, clavePoliza, nombres, apellido);
    }

    public List<Beneficiario> listarLocales() {
        return repositorio.findAll();
    }


//GUARDAR BENEFICIARIO
@Transactional
public Beneficiario guardarLocal(Beneficiario beneficiario) {

 if (beneficiario == null) {
     throw new IllegalArgumentException(
             "El beneficiario no puede estar vacío");
 }

 if (beneficiario.getId() != null &&
         repositorio.existsById(beneficiario.getId())) {
     throw new IllegalArgumentException(
             "El beneficiario ya existe");
 }

 return repositorio.save(beneficiario);
}

//ACTUALIZAR BENEFICIARIO
@Transactional
public Beneficiario actualizarLocal(Long id, Beneficiario datos) {

 if (datos == null) {
     throw new IllegalArgumentException(
             "Los datos no pueden estar vacíos");
 }

 Beneficiario existente = repositorio.findById(id)
         .orElseThrow(() -> new EntityNotFoundException(
                 "No existe el beneficiario"));

 existente.setNombres(datos.getNombres());
 existente.setPrimerApellido(datos.getPrimerApellido());
 existente.setSegundoApellido(datos.getSegundoApellido());
 existente.setFechaNacimiento(datos.getFechaNacimiento());
 existente.setPorcentaje(datos.getPorcentaje());
 existente.setPoliza(datos.getPoliza());

 return repositorio.save(existente);
}

//ELIMINAR BENEFICIARIO
@Transactional
public void eliminarLocal(Long id) {

 Beneficiario existente = repositorio.findById(id)
         .orElseThrow(() -> new EntityNotFoundException(
                 "No existe el beneficiario"));

 repositorio.delete(existente);
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
        return adaptador.altaBeneficiario(b.getFechaNacimiento(), b.getPoliza().getClave().toString(),
                b.getPorcentaje(), b.getNombres(), b.getPrimerApellido(), b.getSegundoApellido());
    }

    private static void requerido(Object valor, String campo) {
        if (valor == null || valor.toString().isBlank()) {
            throw new IllegalArgumentException("El campo '" + campo + "' es obligatorio");
        }
    }
}
