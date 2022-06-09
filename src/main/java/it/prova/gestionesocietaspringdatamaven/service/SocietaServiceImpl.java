package it.prova.gestionesocietaspringdatamaven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocietaspringdatamaven.model.Societa;
import it.prova.gestionesocietaspringdatamaven.repository.SocietaRepository;

@Service
public class SocietaServiceImpl implements SocietaService {

	@Autowired
	private SocietaRepository societaRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Societa> listAllSocieta() {
		return (List<Societa>) societaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Societa caricaSingolaSocieta(Long id) {
		return societaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Societa societaInstance) {
		societaRepository.save(societaInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Societa societaInstance) {
		societaRepository.save(societaInstance);

	}

	@Override
	@Transactional
	public void rimuovi(Societa societaInstance) {
		societaRepository.delete(societaInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Societa> findByExample(Societa example) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		return (List<Societa>) societaRepository.findAll(Example.of(example, matcher));
	}

}