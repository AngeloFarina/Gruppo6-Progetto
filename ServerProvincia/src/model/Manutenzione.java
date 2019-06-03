package model;

import java.util.Date;

public class Manutenzione {
	private String id;
	private Date dataOraInizio;
	private Date dataOraFine;
	private String descrizione;
	public Manutenzione(String id, Date dataOraInizio, Date dataOraFine, String descrizione) {
		super();
		this.id = id;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.descrizione = descrizione;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return "Manutenzione [id=" + id + ", dataOraInizio=" + dataOraInizio + ", dataOraFine=" + dataOraFine
				+ ", descrizione=" + descrizione + "]";
	}
	
	
}
