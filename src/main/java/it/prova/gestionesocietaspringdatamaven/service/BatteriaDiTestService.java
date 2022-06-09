package it.prova.gestionesocietaspringdatamaven.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

	public void testInserisciNuovoDipendente() throws Exception {
		System.out.println("----- INIZIO testInserisciNuovoDipendente -----");

		Long nowInMillisecondi = new Date().getTime();

		Societa Societa2 = new Societa("Societa2" + nowInMillisecondi, "Via Roma, 20" + nowInMillisecondi,
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000"));
		if (Societa2.getId() != null)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(Societa2);
		if (Societa2.getId() == null || Societa2.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		Dipendente dipendente = new Dipendente("SAVERIO " + nowInMillisecondi, "CARELLOTTI " + nowInMillisecondi,
				new SimpleDateFormat("dd-MM-yyyy").parse("24-05-1999"), 54000);
		dipendente.setSocieta(Societa2);
		dipendenteService.inserisciNuovo(dipendente);
		if (dipendente.getId() == null && dipendente.getId() < 1)
			throw new RuntimeException("testInserisciNuovoDipendente... failed: inserimento dipendente fallito");

		System.out.println("----- FINE testInserisciNuovoDipendente -----");
	}

	public void testFindByExampleSocieta() throws Exception {

		System.out.println("----- INIZIO testFindByExampleSocieta -----");

		Long nowInMillisecondi = new Date().getTime();

		Societa Societa3 = new Societa("SocietaDaTrovare" + nowInMillisecondi, "Via Mosca, 52" + nowInMillisecondi,
				new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000"));
		if (Societa3.getId() != null)
			throw new RuntimeException("testFindByExampleSocieta...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(Societa3);
		if (Societa3.getId() == null || Societa3.getId() < 1)
			throw new RuntimeException("testFindByExampleSocieta...failed");

		List<Societa> listaSocietaEsempio = societaService.findByExample(Societa3);

		for (Societa societaItem : listaSocietaEsempio) {
			System.out.println(societaItem);
		}

		if (listaSocietaEsempio.size() < 1)
			throw new RuntimeException("testFindByExampleSocieta failed: societa non trovata");

		System.out.println("----- FINE testFindByExampleSocieta -----");
	}

	public void testRimozioneSocietaVaiInRollback() throws Exception {
		System.out.println("----- INIZIO testRimozioneSocietaVaiInRollback -----");

		Long nowInMillisecondi = new Date().getTime();

		Societa SocietaDaRimuovere = new Societa("SocietaDaRimuovere" + nowInMillisecondi,
				"Via Firenze, 21" + nowInMillisecondi, new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2000"));
		if (SocietaDaRimuovere.getId() != null)
			throw new RuntimeException(
					"testRimozioneSocietaVaiInRollback...failed: transient object con id valorizzato");

		societaService.inserisciNuovo(SocietaDaRimuovere);
		if (SocietaDaRimuovere.getId() == null || SocietaDaRimuovere.getId() < 1)
			throw new RuntimeException("testRimozioneSocietaVaiInRollback...failed");

//		Dipendente dipendente = new Dipendente("CLAUDIO " + nowInMillisecondi, "BUZIOTTI " + nowInMillisecondi,
//				new SimpleDateFormat("dd-MM-yyyy").parse("24-05-1999"), 54000);
//		dipendente.setSocieta(SocietaDaRimuovere);
//		dipendenteService.inserisciNuovo(dipendente);
//		if (dipendente.getId() == null && dipendente.getId() < 1)
//			throw new RuntimeException("testRimozioneSocieta... failed");

		try {
			societaService.removeConEccezione(SocietaDaRimuovere);
			throw new RuntimeException("testRemoveConEccezioneVaInRollback...failed: eccezione non lanciata");
		} catch (Exception e) {
			// se passo di qui è tutto ok
		}

		if (SocietaDaRimuovere == null || SocietaDaRimuovere.getId() == null)
			throw new RuntimeException(
					"testRimozioneSocietaVaiInRollback...failed: cancellazione avvenuta senza rollback");

		societaService.rimuovi(SocietaDaRimuovere);

		if (SocietaDaRimuovere.getDipendenti().size() != 0)
			throw new RuntimeException(
					"testRimozioneSocietaVaiInRollback...failed. Impossibile rimuovere: la societa ha dipendenti assegnati");

		System.out.println("----- FINE testRimozioneSocietaVaiInRollback -----");
	}

}