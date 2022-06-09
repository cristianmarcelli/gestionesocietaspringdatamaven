package it.prova.gestionesocietaspringdatamaven.service;

import java.util.List;

import it.prova.gestionesocietaspringdatamaven.model.Societa;

public interface SocietaService {

	public List<Societa> listAllSocieta();

	public Societa caricaSingolaSocieta(Long id);

	public void aggiorna(Societa societaInstance);

	public void inserisciNuovo(Societa societaInstance);

	public void rimuovi(Societa societaInstance);

	public List<Societa> findByExample(Societa example);
	
	public void removeConEccezione(Societa societaInstance);
	
}