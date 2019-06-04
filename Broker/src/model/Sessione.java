package model;

import java.time.LocalDateTime;

public class Sessione {
	private String ruolo;
	private LocalDateTime dataCreazione;
	private LocalDateTime dataScadenza;
	private String ipClient;
	
	public Sessione(String ruolo, LocalDateTime dataCreazione, LocalDateTime dataScadenza, String ipClient) {
		this.ruolo = ruolo;
		this.dataCreazione = dataCreazione;
		this.dataScadenza = dataScadenza;
		this.ipClient = ipClient;
	}
	
	public String getRuolo() {
		return ruolo;
	}

	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}

	public LocalDateTime getDataScadenza() {
		return dataScadenza;
	}

	public String getIpClient() {
		return ipClient;
	}

}
