package mx.edu.uacm.is.slt.as.intermediario_polizas.repositorios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repositorio JPA para la entidad {@link Poliza}.
 * La PK de Poliza es de tipo UUID.
 */
@Repository


public interface PolizaRepository extends JpaRepository<Poliza, UUID> {

}//fin de la interface
