package model;

public class Richiesta {

	private String idCaserma;
	private String idMezzo;
	private String dataOra;
	private String descrizione;
	
	public Richiesta(String idCaserma, String idMezzo, String dataOra, String descrizione) {
		this.idCaserma = idCaserma;
		this.idMezzo = idMezzo;
		this.dataOra = dataOra;
		this.descrizione = descrizione;
	}
	
	public String getIdCaserma() {
		return idCaserma;
	}
	public void setIdCaserma(String idCaserma) {
		this.idCaserma = idCaserma;
	}
	public String getIdMezzo() {
		return idMezzo;
	}
	public void setIdMezzo(String idMezzo) {
		this.idMezzo = idMezzo;
	}
	public String getDataOra() {
		return dataOra;
	}
	public void setDataOra(String dataOra) {
		this.dataOra = dataOra;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
