package mx.edu.uacm.is.slt.as.intermediario_polizas.servicios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoAdapter;
import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Beneficiario;
import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza;
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

    /**
     * Valida que la suma de porcentajes de los beneficiarios no exceda el 100% (ERS 3.2.5).
     * @param poliza La póliza a validar.
     */
    public void validarPorcentajes(Poliza poliza) {
        int suma = repositorio.findAll().stream()
            .filter(b -> b.getPoliza().getClave().equals(poliza.getClave()))
            .mapToInt(Beneficiario::getPorcentaje)
            .sum();
        if (suma > 100) {
            throw new IllegalStateException("La suma de porcentajes excede el 100%: " + suma);
        }
    }
}