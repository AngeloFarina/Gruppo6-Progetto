package model;

import java.util.List;

public class RichiestaServizio {
	private String sorgente;
	private String destinatario;
	private String servizio;
	private List<String> parametri;
	
	public RichiestaServizio(String sorgente, String destinatario, String servizio, List<String> parametri) {
		this.sorgente = sorgente;
		this.destinatario = destinatario;
		this.servizio = servizio;
		this.parametri = parametri;
	}

	public String getSorgente() {
		return sorgente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public String getServizio() {
		return servizio;
	}

	public List<String> getParametri() {
		return parametri;
	}
	
}
