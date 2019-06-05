package controller;

import interfacce.IGestioneMezzi;
import model.Caserma;
import model.Manutenzione;
import model.Mezzo;
import model.RichiestaSostituzione;

public class GestioneMezziCasermaController extends Controller implements IGestioneMezzi{

	private RichiestaManutenzioneController manutenzione;
	private RichiestaSostituzioneController sostituzione;
	
	public GestioneMezziCasermaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
		manutenzione = new RichiestaManutenzioneController(getConnection());
		sostituzione = new RichiestaSostituzioneController(getConnection());
	}
	
	public void richiesta(String tipoRichiesta, Mezzo m, Caserma c,String descrizione){
		switch (tipoRichiesta) {
		case "richiestaSostituzione":
			//SERVE LA RICHIESTA DI SOSTITUZIONE
			RichiestaSostituzione richiesta = new RichiestaSostituzione()
			sostituzione.effettuaSostituzione(m, richiestaSostituzione);
			break;
		case "richiestaManutenzione":
			//SERVE LA RICHIESTA DI MANUTENZIONE
			Manutenzione result = manutenzione.concludiManutenzione(r);
			break;
		}
	}

	public void richiesta(String tipo,String idMezzo,String idCaserma) {
		
	}
	
}
