package mx.edu.uacm.is.slt.as.intermediario_polizas.repositorios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link Beneficiario}.
 */
@Repository

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {



}//FIN DE LA CLASE