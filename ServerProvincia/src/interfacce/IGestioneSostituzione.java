package interfacce;

import model.RichiestaSostituzione;

public interface IGestioneSostituzione {

	public void effettuaSostituzione(String idMezzo, RichiestaSostituzione s) throws Exception;
}
