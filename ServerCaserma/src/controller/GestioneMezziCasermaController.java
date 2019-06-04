package controller;

import interfacce.IGestioneMezzi;
import model.Caserma;
import model.Manutenzione;
import model.Mezzo;

public class GestioneMezziCasermaController extends Controller implements IGestioneMezzi{

	private RichiestaManutenzioneController manutenzione;
	private RichiestaSostituzioneController sostituzione;
	
	public GestioneMezziCasermaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
		manutenzione = new RichiestaManutenzioneController(getConnection());
		sostituzione = new RichiestaSostituzioneController(getConnection());
	}
	
	public void richiesta(String tipoRichiesta, Mezzo m, Caserma c){
		switch (tipoRichiesta) {
		case "richiestaSostituzione":
			//SERVE LA RICHIESTA DI SOSTITUZIONE
			sostituzione.effettuaSostituzione(m, r);
			break;
		case "richiestaManutenzione":
			//SERVE LA RICHIESTA DI MANUTENZIONE
			Manutenzione result = manutenzione.concludiManutenzione(r);
			break;
		}
	}

}
