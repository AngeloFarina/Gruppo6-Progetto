package model;

import java.io.Serializable;

public class Manutenzione implements Serializable{
	private static final long serialVersionUID = 20L;
	
	private String id;
	private String dataOraInizio;
	private String dataOraFine;
	private String descrizione;
	private Mezzo mezzo;

	public Manutenzione(String id, String dataOraInizio,String dataOraFine,String descrizione, Mezzo mezzo) {
		this.id=id;
		this.dataOraInizio=dataOraInizio;
		this.dataOraFine=dataOraFine;
		this.descrizione=descrizione;
		this.mezzo=mezzo;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataOraInizio() {
		return dataOraInizio;
	}

	public void setDataOraInizio(String dataOraInizio) {
		this.dataOraInizio = dataOraInizio;
	}

	public String getDataOraFine() {
		return dataOraFine;
	}

	public void setDataOraFine(String dataOraFine) {
		this.dataOraFine = dataOraFine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

}
