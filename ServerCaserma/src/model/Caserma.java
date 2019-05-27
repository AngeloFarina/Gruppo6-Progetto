package model;

import java.util.ArrayList;
import java.util.List;

public class Caserma {
	private String id;
	private String citta;
	private int cisterna;
	private DistaccamentoProvinciale provincia;
	private List<Mezzo> mezzi;
	
	public Caserma(String id,String citta, int cisterna, List<Mezzo> mezzi) {
		this.setId(new String(id));
		this.setCitta(new String(citta));
		this.setCisterna(cisterna);
		this.setMezzi(new ArrayList<Mezzo>(mezzi));
	}

	public final int getCisterna() {
		return cisterna;
	}

	public final void setCisterna(int cisterna) {
		this.cisterna = cisterna;
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final DistaccamentoProvinciale getProvincia() {
		return provincia;
	}

	public final void setProvincia(DistaccamentoProvinciale provincia) {
		this.provincia = provincia;
	}
	
	
	public final String getCitta() {
		return citta;
	}

	public final void setCitta(String citta) {
		this.citta = citta;
	}
	

	public List<Mezzo> getMezzi() {
		return new ArrayList<Mezzo>(mezzi);
	}

	public void setMezzi(List<Mezzo> mezzi) {
		this.mezzi = mezzi;
	}
	
	@Override
	public String toString() {
		String res = "ID = " + id + " citta' = " + citta + " Cisterna = " + cisterna + " Provincia: " + provincia + " Mezzi: ";
		for(Mezzo m : mezzi) {
			res += m.toString() + ", ";
		}
		return res.substring(0,res.length()-3);
	}

}
