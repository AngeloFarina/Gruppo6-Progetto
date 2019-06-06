package model;

import java.util.Date;

public class RichiestaSostituzione {
	private String idCaserma;
	private String idMezzo;
	private Date dataOra;
	private String descrizione;
	public RichiestaSostituzione(String idCaserma, String idMezzo, Date dataOra, String descrizione) {
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
	public Date getDataOra() {
		return dataOra;
	}
	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return "RichiestaSostituzione [idCaserma=" + idCaserma + ", idMezzo=" + idMezzo + ", dataOra=" + dataOra
				+ ", descrizione=" + descrizione + "]";
	}
	
	
}
