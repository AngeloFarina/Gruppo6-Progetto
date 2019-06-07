package controller;

import interfacce.IGestioneMezzi;
import model.Richiesta;
import model.RichiestaManutenzione;
import model.RichiestaSostituzione;

public class GestioneMezziCasermaController extends Controller implements IGestioneMezzi{

	private RichiestaManutenzioneController manutenzione;
	private RichiestaSostituzioneController sostituzione;
	
	public GestioneMezziCasermaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
		manutenzione = new RichiestaManutenzioneController(getConnection());
		sostituzione = new RichiestaSostituzioneController(getConnection());
	}
	
	public void richiesta(String tipoRichiesta, Richiesta r){
		switch (tipoRichiesta) {
		case "richiestaSost":
			//SERVE LA RICHIESTA DI SOSTITUZIONE
			sostituzione.richiesta((RichiestaSostituzione)r);
			break;
		case "richiestaMan":
			//SERVE LA RICHIESTA DI MANUTENZIONE
			manutenzione.richiesta((RichiestaManutenzione)r);
			break;
		}
	}

	public void richiesta(String tipo,String idMezzo,String idCaserma) {
		
	}
	
}
