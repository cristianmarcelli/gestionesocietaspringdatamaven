package it.prova.gestionesocietaspringdatamaven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocietaspringdatamaven.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, QueryByExampleExecutor<Dipendente> {

	List<Dipendente> findByNome(String name);

	List<Dipendente> findByRedditoAnnuoGreaterThan(int etaInput);

	List<Dipendente> findByNomeAndCognome(String nome, String cognome);

	List<Dipendente> findByRedditoAnnuoOrderByNomeDesc(int redditoAnnuo);

	@Query("from Dipendente d where d.nome like ?1%")
	List<Dipendente> findByNomeStartsWith(String token);

	@EntityGraph(attributePaths = { "societa" })
	List<Dipendente> findByCognome(String cognome);

}