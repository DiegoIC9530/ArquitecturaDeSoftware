package mx.edu.uacm.is.slt.as.intermediario_polizas.servicios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoAdapter;
import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Cliente;
import mx.edu.uacm.is.slt.as.intermediario_polizas.repositorios.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Caso de uso: gestión de Clientes asegurados.
 */
@Service
public class ClienteService {

    private final SistemaDuenoAdapter adaptador;
    private final ClienteRepository repositorio;

    public ClienteService(SistemaDuenoAdapter adaptador, ClienteRepository repositorio) {
        this.adaptador = adaptador;
        this.repositorio = repositorio;
    }

    public String consultarCliente(String curp) {
        return adaptador.obtenerCliente(curp);
    }

    public List<Cliente> listarClientesLocales() {
        return repositorio.findAll();
    }

    public Optional<Cliente> buscarLocal(String curp) {
        return repositorio.findById(curp);
    }

    // --- MÉTODOS LOCALES (Izel) ---
    public Cliente guardarLocal(Cliente cliente) {
        return repositorio.save(cliente);
    }
    
    public Optional<Cliente> actualizarLocal(String curp, Cliente nuevos) {
        return repositorio.findById(curp).map(c -> {
            c.setNombres(nuevos.getNombres());
            c.setPrimerApellido(nuevos.getPrimerApellido());
            c.setSegundoApellido(nuevos.getSegundoApellido());
            c.setDireccion(nuevos.getDireccion());
            c.setFechaNacimiento(nuevos.getFechaNacimiento());
            return repositorio.save(c);
        });
    }
    
    public void eliminarLocal(String curp) {
        repositorio.deleteById(curp);
    }

    // --- MÉTODOS REMOTOS (Juan) ---
    public String registrarCliente(Cliente cliente) {
        return adaptador.registrarCliente(cliente);
    }

    // --- VALIDACIONES (Joel) ---
    /**
     * Valida el formato de la CURP (18 caracteres alfanuméricos).
     * @param curp La CURP a validar.
     */
    public void validarCurp(String curp) {
        if (curp == null || !curp.matches("[A-Z]{4}\\d{6}[A-Z0-9]{8}")) {
            throw new IllegalArgumentException("CURP inválida: " + curp);
        }
    }
}