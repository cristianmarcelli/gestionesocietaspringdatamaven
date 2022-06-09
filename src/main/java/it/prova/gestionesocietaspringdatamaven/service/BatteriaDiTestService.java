package it.prova.gestionesocietaspringdatamaven.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocietaspringdatamaven.model.Dipendente;
import it.prova.gestionesocietaspringdatamaven.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private SocietaService societaService;

	@Autowired
	private DipendenteService dipendenteService;

	public void testInserisciNuovaSocieta() throws ParseException {
		System.out.println("----- INIZIO testInserisciNuovaSocieta -----");

		Long nowInMillisecondi = new Date().getTime();

		Societa nuovaSocieta = new Societa("Societa1" + nowInMillisecondi, "Via Italia, 52" + nowInMillisecondi,
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000"));
		if (nuovaSocieta.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		System.out.println("----- FINE testInserisciNuovaSocieta -----");
	}

}