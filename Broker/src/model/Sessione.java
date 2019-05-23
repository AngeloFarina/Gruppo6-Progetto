package model;

import java.time.LocalDate;

public class Sessione {
	private String ruolo;
	private LocalDate dataCreazione;
	private LocalDate dataScadenza;
	private String ipClient;
	
	public Sessione(String ruolo, LocalDate dataCreazione, LocalDate dataScadenza, String ipClient) {
		this.ruolo = ruolo;
		this.dataCreazione = dataCreazione;
		this.dataScadenza = dataScadenza;
		this.ipClient = ipClient;
	}
	
	
}
