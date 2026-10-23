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
    public String registrarCliente(Cliente cliente) {
        return adaptador.registrarCliente(cliente);
    }
}