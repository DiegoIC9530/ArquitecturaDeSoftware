package mx.edu.uacm.is.slt.as.intermediario_polizas.servicios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoAdapter;
import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza;
import mx.edu.uacm.is.slt.as.intermediario_polizas.repositorios.PolizaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Caso de uso: gestión de Pólizas de seguro.
 */
@Service
public class PolizaService {

    private final SistemaDuenoAdapter adaptador;
    private final PolizaRepository repositorio;

    public PolizaService(SistemaDuenoAdapter adaptador, PolizaRepository repositorio) {
        this.adaptador = adaptador;
        this.repositorio = repositorio;
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

    public List<Poliza> listarLocales() {
        return repositorio.findAll();
    }

    public Optional<Poliza> buscarLocal(UUID clave) {
        return repositorio.findById(clave);
    }

    // --- MÉTODOS LOCALES (Izel) ---
    public Poliza guardarLocal(Poliza poliza) {
        return repositorio.save(poliza);
    }
    
    public Optional<Poliza> actualizarLocal(UUID clave, Poliza nuevos) {
        return repositorio.findById(clave).map(p -> {
            p.setTipo(nuevos.getTipo());
            p.setMonto(nuevos.getMonto());
            p.setDescripcion(nuevos.getDescripcion());
            return repositorio.save(p);
        });
    }
    
    public void eliminarLocal(UUID clave) {
        repositorio.deleteById(clave);
    }

    // --- MÉTODOS REMOTOS (Juan) ---
    public String registrarPoliza(Poliza poliza) {
        return adaptador.registrarPoliza(poliza);
    }
    
    public String eliminarPoliza(java.util.UUID clave) {
        return adaptador.eliminarPoliza(clave.toString());
    }

    // --- VALIDACIONES (Joel) ---
    /**
     * Valida que el tipo de póliza sea 0 (Auto), 1 (Vida) o 2 (Médico) según ERS 2.7.3.
     * @param poliza La póliza a validar.
     */
    public void validarTipo(Poliza poliza) {
        if (poliza.getTipo() == null || poliza.getTipo() < 0 || poliza.getTipo() > 2) {
            throw new IllegalArgumentException("Tipo de póliza debe ser 0 (Auto), 1 (Vida) o 2 (Médico)");
        }
    }
}