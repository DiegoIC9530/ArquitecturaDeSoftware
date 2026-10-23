package mx.edu.uacm.is.slt.as.intermediario_polizas.servicios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoAdapter;
import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Beneficiario;
import mx.edu.uacm.is.slt.as.intermediario_polizas.repositorios.BeneficiarioRepository;
import org.springframework.stereotype.Service;

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

    // --- MÉTODOS LOCALES (Izel) ---
    public Beneficiario guardarLocal(Beneficiario beneficiario) {
        return repositorio.save(beneficiario);
    }
    
    public void eliminarLocal(Long id) {
        repositorio.deleteById(id);
    }

    // --- MÉTODOS REMOTOS (Juan) ---
    public String registrarBeneficiario(Beneficiario beneficiario) {
        return adaptador.registrarBeneficiario(beneficiario);
    }
}