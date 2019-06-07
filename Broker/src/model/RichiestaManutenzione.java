package model;

import java.io.Serializable;

public class RichiestaManutenzione extends Richiesta implements Serializable{
	private static final long serialVersionUID = 19L;

	public RichiestaManutenzione(String idCaserma, String idMezzo, String dataOra, String descrizione) {
		super(idCaserma, idMezzo, dataOra, descrizione);
	}

	@Override
	public String toString() {
		return "RichiestaManutenzione [getIdCaserma()=" + getIdCaserma() + ", getIdMezzo()=" + getIdMezzo()
				+ ", getDataOra()=" + getDataOra() + ", getDescrizione()=" + getDescrizione() +"]";
	}
	
}
