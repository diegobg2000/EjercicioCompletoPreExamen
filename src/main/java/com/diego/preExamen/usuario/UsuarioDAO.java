package com.diego.preExamen.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;




public interface UsuarioDAO extends  CrudRepository<Usuario, String> {
	
	@Transactional
	@Query(value="select * from usuarios where edad=:edad", nativeQuery = true)
	/*Si queremos sacarlo como siso tenemos que dejarlo en void
	 * Pero si queremos pasarlo a thimeaf tenemos que retornar una lista de ese objeto*/
	List<Usuario> buscarPorEdad(@Param("edad") Integer anios);
	
	
	List<Usuario> findByEdadLessThan(Integer edad);

	
	
	
}
