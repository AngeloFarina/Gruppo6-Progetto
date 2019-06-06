package model;

import java.io.Serializable;

public class RichiestaSostituzione implements Serializable{
	private static final long serialVersionUID = 16L;
	
	private String idCaserma;
	private String idMezzo;
	private String dataOra;
	private String descrizione;
	private String tipo;
	
	public RichiestaSostituzione(String idCaserma, String idMezzo,String tipo, String dataOra, String descrizione) {
		this.idCaserma = idCaserma;
		this.idMezzo = idMezzo;
		this.dataOra = dataOra;
		this.descrizione = descrizione;
		this.tipo=tipo;
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
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	@Override
	public String toString() {
		return "RichiestaSostituzione [idCaserma=" + idCaserma + ", idMezzo=" + idMezzo + ", tipoMezzo="+ tipo + ", dataOra=" + dataOra
				+ ", descrizione=" + descrizione + "]";
	}
	
	
}
