package model;

import java.util.List;

public class RichiestaServizio {
	private String sorgente;
	private String destinatario;
	private String servizio;
	private List<Object> parametri;
	
	public RichiestaServizio(String sorgente, String destinatario, String servizio, List<Object> parametri) {
		this.sorgente = sorgente;
		this.destinatario = destinatario;
		this.servizio = servizio;
		this.parametri = parametri;
	}
	
}
