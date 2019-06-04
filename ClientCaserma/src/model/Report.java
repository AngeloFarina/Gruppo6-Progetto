package model;

import java.util.Date;

public class Report {
	private String id;
	private int kmEffettuati;
	private int carburanteConsumato;
	private String descrizione;
	private String tipologiaUscita;
	private Date dataOra;
	public Report(String id, int kmEffettuati, int carburanteConsumato, String descrizione, String tipologiaUscita,
			Date dataOra) {
		this.id = id;
		this.kmEffettuati = kmEffettuati;
		this.carburanteConsumato = carburanteConsumato;
		this.descrizione = descrizione;
		this.tipologiaUscita = tipologiaUscita;
		this.dataOra = dataOra;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getKmEffettuati() {
		return kmEffettuati;
	}
	public void setKmEffettuati(int kmEffettuati) {
		this.kmEffettuati = kmEffettuati;
	}
	public int getCarburanteConsumato() {
		return carburanteConsumato;
	}
	public void setCarburanteConsumato(int carburanteConsumato) {
		this.carburanteConsumato = carburanteConsumato;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTipologiaUscita() {
		return tipologiaUscita;
	}
	public void setTipologiaUscita(String tipologiaUscita) {
		this.tipologiaUscita = tipologiaUscita;
	}
	public Date getDataOra() {
		return dataOra;
	}
	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}
	@Override
	public String toString() {
		return "Report [id=" + id + ", kmEffettuati=" + kmEffettuati + ", carburanteConsumato=" + carburanteConsumato
				+ ", descrizione=" + descrizione + ", tipologiaUscita=" + tipologiaUscita + ", dataOra=" + dataOra
				+ "]";
	}
	
	
}
