package mx.edu.uacm.is.slt.as.intermediario_polizas.repositorios;

import mx.edu.uacm.is.slt.as.intermediario_polizas.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link Cliente}.
 * Hereda las operaciones CRUD de {@link JpaRepository}.
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

	
	
}// fin de la clase
