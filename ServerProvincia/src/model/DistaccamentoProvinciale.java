package model;

import java.util.ArrayList;
import java.util.List;

public class DistaccamentoProvinciale extends Caserma{
	private List<Caserma> caserme;
	
	public DistaccamentoProvinciale(String id, String citta,int cisterna,List<Mezzo> mezzi, List<Vigile> vigili) {
		super(id,citta,cisterna,mezzi,vigili);
		this.setId(new String(id));
		this.setCitta(new String(citta));
		caserme = new ArrayList<Caserma>();
	}


	public final List<Caserma> getCaserme() {
		return new ArrayList<Caserma>(caserme);
	}

	public final void setCaserme(List<Caserma> caserme) {
		this.caserme = caserme;
	}
	
	public final boolean addCaserma(Caserma caserma) {
		return caserme.add(caserma);
	}
	
	
	@Override
	public String toString() {
		String result = "";
		result += "ID = " + getId() + " citta' = " + getCitta() + " cisterna = " + getCisterna();
		for(Caserma c : caserme) {
			result += c.toString() + "\n";
		}
		return result;
	}
	

}
