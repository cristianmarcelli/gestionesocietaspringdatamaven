package it.prova.gestionesocietaspringdatamaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionesocietaspringdatamaven.service.BatteriaDiTestService;

@SpringBootApplication
public class GestionesocietaspringdatamavenApplication implements CommandLineRunner {

	@Autowired
	public BatteriaDiTestService batteriaDiTestService;

	public static void main(String[] args) {
		SpringApplication.run(GestionesocietaspringdatamavenApplication.class, args);
	}

	public void run(String... args) throws Exception {
		System.out.println("################ START   #################");
		System.out.println("################ eseguo i test  #################");

		batteriaDiTestService.testInserisciNuovaSocieta();
		batteriaDiTestService.testInserisciNuovoDipendente();

		System.out.println("################ FINE   #################");
	}

}