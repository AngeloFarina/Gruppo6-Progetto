package model;

import java.util.Date;

public class Manutenzione {
	
	private String id;
	private String descrizione;
	private Date dataOraInizio;
	private Date dataOraFine;
	private String idMezzo;
	public Manutenzione(String id, String descrizione, Date dataOraInizio, Date dataOraFine, String idMezzo) {
		this.id = id;
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.idMezzo = idMezzo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Date getDataOraInizio() {
		return dataOraInizio;
	}
	public void setDataOraInizio(Date dataOraInizio) {
		this.dataOraInizio = dataOraInizio;
	}
	public Date getDataOraFine() {
		return dataOraFine;
	}
	public void setDataOraFine(Date dataOraFine) {
		this.dataOraFine = dataOraFine;
	}
	public String getIdMezzo() {
		return this.idMezzo;
	}
	public void setIdMezzo(String idMezzo) {
		this.idMezzo = idMezzo;
	}
	@Override
	public String toString() {
		return "Manutenzione [id=" + id + ", descrizione=" + descrizione + ", dataOraInizio=" + dataOraInizio
				+ ", dataOraFine=" + dataOraFine + ", idMezzo=" + idMezzo +"]";
	}
	
	
}
