package mx.edu.uacm.is.slt.as.intermediario_polizas.servicios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoAdapter;

import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Cliente;
import mx.edu.uacm.is.slt.as.intermediario_polizas.repositorios.ClienteRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

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


//GUARDAR CLIENTE
@Transactional
public Cliente guardarLocal(Cliente cliente) {

 if (cliente == null) {
     throw new IllegalArgumentException("El cliente no puede estar vacío");
 }

 if (repositorio.existsById(cliente.getCurp())) {
     throw new IllegalArgumentException("El cliente ya existe");
 }

 return repositorio.save(cliente);
}

//ACTUALIZAR CLIENTE
@Transactional
public Cliente actualizarLocal(String curp, Cliente datos) {

 Cliente existente = repositorio.findById(curp)
         .orElseThrow(() -> new EntityNotFoundException(
                 "No existe el cliente"));

 existente.setNombres(datos.getNombres());
 existente.setPrimerApellido(datos.getPrimerApellido());
 existente.setSegundoApellido(datos.getSegundoApellido());
 existente.setDireccion(datos.getDireccion());
 existente.setFechaNacimiento(datos.getFechaNacimiento());

 return repositorio.save(existente);
}

//ELIMINAR CLIENTE
@Transactional
public void eliminarLocal(String curp) {

 Cliente existente = repositorio.findById(curp)
         .orElseThrow(() -> new EntityNotFoundException(
                 "No existe el cliente"));

 repositorio.delete(existente);
}

    // ---------- ESCRITURA EN EL SISTEMA DUEÑO ----------

    public String altaCliente(Cliente c) {
        validarCliente(c);
        return adaptador.altaCliente(c.getCurp(), c.getDireccion(), c.getFechaNacimiento(),
                c.getNombres(), c.getPrimerApellido(), c.getSegundoApellido());
    }

    public String modificarCliente(String curp, Cliente c) {
        validarCliente(c);
        return adaptador.modificarCliente(curp, c.getDireccion(), c.getFechaNacimiento(),
                c.getNombres(), c.getPrimerApellido(), c.getSegundoApellido());
    }

    private void validarCliente(Cliente c) {
        if (c == null) {
            throw new IllegalArgumentException("Los datos del cliente son obligatorios");
        }
        requerido(c.getCurp(), "curp");
        if (c.getCurp().length() != 18) {
            throw new IllegalArgumentException("La CURP debe tener 18 caracteres");
        }
        requerido(c.getNombres(), "nombres");
        requerido(c.getPrimerApellido(), "primerApellido");
        requerido(c.getDireccion(), "direccion");
        requerido(c.getFechaNacimiento(), "fechaNacimiento");
    }

    private static void requerido(Object valor, String campo) {
        if (valor == null || valor.toString().isBlank()) {
            throw new IllegalArgumentException("El campo '" + campo + "' es obligatorio");
        }
    }
}
