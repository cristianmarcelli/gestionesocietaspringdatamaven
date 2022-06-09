package it.prova.gestionesocietaspringdatamaven.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestionesocietaspringdatamaven.model.Societa;


public interface SocietaRepository extends CrudRepository<Societa, Long>, QueryByExampleExecutor<Societa> {
	
}