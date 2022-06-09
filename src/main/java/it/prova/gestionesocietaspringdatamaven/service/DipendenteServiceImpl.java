package it.prova.gestionesocietaspringdatamaven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocietaspringdatamaven.model.Dipendente;
import it.prova.gestionesocietaspringdatamaven.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository dipendenteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Dipendente> listAllDipendenti() {
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendente(Long id) {
		return dipendenteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);

	}

	@Override
	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Dipendente dipendenteInstance) {
		dipendenteRepository.delete(dipendenteInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dipendente> findByExample(Dipendente example) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		return (List<Dipendente>) dipendenteRepository.findAll(Example.of(example, matcher));
	}

}