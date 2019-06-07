package model;

import java.io.Serializable;

public class RichiestaSostituzione extends Richiesta implements Serializable{
	private static final long serialVersionUID = 16L;
	
	private String tipo;
	
	public RichiestaSostituzione(String idCaserma, String idMezzo,String tipo, String dataOra, String descrizione) {
		super(idCaserma, idMezzo, dataOra, descrizione);
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}

	@Override
	public String toString() {
		return "RichiestaSostituzione [tipo=" + tipo + ", getIdCaserma()=" + getIdCaserma() + ", getIdMezzo()="
				+ getIdMezzo() + ", getDataOra()=" + getDataOra() + ", getDescrizione()=" + getDescrizione() + "]";
	}
	
}
