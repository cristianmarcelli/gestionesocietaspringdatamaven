package it.prova.gestionesocietaspringdatamaven.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocietaspringdatamaven.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long>, QueryByExampleExecutor<Dipendente> {

	// dipendente più anziano - lavorativamente parlando – delle società fondate
	// prima del 1990

	@EntityGraph(attributePaths = { "societa" })
	@Query("from Dipendente d join d.societa s where s.dataFondazione < '1990-01-01' and d.dataAssunzione in (select min(d.dataAssunzione) from Dipendente d)")
	Dipendente findDipendentePiuAnzianoDiUnaSocietaWithDateFoundedBefore();

}