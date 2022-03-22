package br.com.marcos.apartamento.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcos.apartamento.model.entity.Apartamento;

@Repository
public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {
	
	
//	Optional<Apartamento> findByNumero(Integer numero);
	
//	SELECT * FROM apartamento WHERE exists ()
 	boolean existsByNumero(Integer numero);

}
