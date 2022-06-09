package it.prova.gestionesocietaspringdatamaven.service;

import java.util.List;

import it.prova.gestionesocietaspringdatamaven.model.Dipendente;

public interface DipendenteService {

	public List<Dipendente> listAllDipendenti();

	public Dipendente caricaSingoloDipendente(Long id);

	public void aggiorna(Dipendente dipendenteInstance);

	public void inserisciNuovo(Dipendente dipendenteInstance);

	public void rimuovi(Dipendente dipendenteInstance);

	public List<Dipendente> findByExample(Dipendente example);
	
	public Dipendente cercaDipendentePiuAnzianoInUnaSocietaFondataPrimaDelNovanta();

}