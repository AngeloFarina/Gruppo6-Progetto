package interfacce;

import model.Caserma;
import model.Mezzo;

public interface IModificaMezzi {

	public void eliminaMezzo(Mezzo m);
	public void aggiungiMezzo(Mezzo m, Caserma c);
	public void modificaMezzo(Mezzo m);
}
