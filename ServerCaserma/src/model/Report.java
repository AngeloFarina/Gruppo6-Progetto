package model;

import java.time.LocalDate;

public class Report {
	private String id;
	private int kmEffettuati;
	private int carburanteConsumato;
	private String descrizione;
	private Tipo tipo;
	private LocalDate dataOra;
	private String idCaserma;
	public Report(String id, int kmEffettuati, int carburanteConsumato, String descrizione, Tipo tipo,
			LocalDate dataOra, String idCaserma) {
		this.id = id;
		this.kmEffettuati = kmEffettuati;
		this.carburanteConsumato = carburanteConsumato;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.dataOra = dataOra;
		this.idCaserma=idCaserma;
	}
	public String getIdCaserma() {
		return idCaserma;
	}
	public void setIdCaserma(String idCaserma) {
		this.idCaserma = idCaserma;
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
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public LocalDate getDataOra() {
		return dataOra;
	}
	public void setDataOra(LocalDate dataOra) {
		this.dataOra = dataOra;
	}
	@Override
	public String toString() {
		return "Report [id=" + id + ", kmEffettuati=" + kmEffettuati + ", carburanteConsumato=" + carburanteConsumato
				+ ", descrizione=" + descrizione + ", tipologiaUscita=" + tipologiaUscita + ", dataOra=" + dataOra
				+ ", idCaserma=" + idCaserma + "]";
	}
	
	
}
