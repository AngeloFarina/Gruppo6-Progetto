package interfacce;

import model.Mezzo;
import model.RichiestaSostituzione;

public interface IGestioneSostituzione {

	public boolean effettuaSostituzione(Mezzo m, RichiestaSostituzione s);
}
