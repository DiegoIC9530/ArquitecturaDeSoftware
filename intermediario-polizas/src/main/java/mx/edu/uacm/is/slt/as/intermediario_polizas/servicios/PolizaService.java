package mx.edu.uacm.is.slt.as.intermediario_polizas.servicios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.adaptadores.SistemaDuenoAdapter;
import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza;
import mx.edu.uacm.is.slt.as.intermediario_polizas.repositorios.PolizaRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

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


//GUARDAR POLIZA
@Transactional
public Poliza guardarLocal(Poliza poliza) {

 if (poliza == null) {
     throw new IllegalArgumentException("La póliza no puede estar vacía");
 }

 if (poliza.getClave() != null &&
         repositorio.existsById(poliza.getClave())) {
     throw new IllegalArgumentException("La póliza ya existe");
 }

 return repositorio.save(poliza);
}

//ACTUALIZAR POLIZA
@Transactional
public Poliza actualizarLocal(UUID clave, Poliza datos) {

 if (datos == null) {
     throw new IllegalArgumentException("Los datos no pueden estar vacíos");
 }

 Poliza existente = repositorio.findById(clave)
         .orElseThrow(() -> new EntityNotFoundException(
                 "No existe la póliza"));

 existente.setTipo(datos.getTipo());
 existente.setMonto(datos.getMonto());
 existente.setDescripcion(datos.getDescripcion());
 existente.setCliente(datos.getCliente());

 return repositorio.save(existente);
}

    // ---------- ESCRITURA EN EL SISTEMA DUEÑO ----------

    public String altaPoliza(Poliza p) {
        validarPoliza(p);
        requerido(p.getClave(), "clave");
        return adaptador.altaPoliza(p.getClave().toString(), p.getTipo(), p.getMonto().toPlainString(),
                p.getDescripcion(), p.getCliente().getCurp());
    }

    public String modificarPoliza(String clave, Poliza p) {
        validarPoliza(p);
        return adaptador.modificarPoliza(clave, p.getTipo(), p.getMonto().toPlainString(),
                p.getDescripcion(), p.getCliente().getCurp());
    }

    public String bajaPoliza(String clave) {
        if (clave == null || clave.isBlank()) {
            throw new IllegalArgumentException("La clave de la póliza es obligatoria");
        }
        return adaptador.bajaPoliza(clave);
    }

    private void validarPoliza(Poliza p) {
        if (p == null) {
            throw new IllegalArgumentException("Los datos de la póliza son obligatorios");
        }
        requerido(p.getTipo(), "tipo");
        if (p.getTipo() < 0 || p.getTipo() > 2) {
            throw new IllegalArgumentException("El tipo debe ser 0 (auto), 1 (vida) o 2 (médico)");
        }
        requerido(p.getMonto(), "monto");
        if (p.getMonto().signum() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        requerido(p.getDescripcion(), "descripcion");
        if (p.getCliente() == null) {
            throw new IllegalArgumentException("La póliza debe indicar el cliente (curp)");
        }
        requerido(p.getCliente().getCurp(), "cliente.curp");
    }

    private static void requerido(Object valor, String campo) {
        if (valor == null || valor.toString().isBlank()) {
            throw new IllegalArgumentException("El campo '" + campo + "' es obligatorio");
        }
    }
}
