package controller;

import interfacce.IGestioneMezzi;
import model.Caserma;
import model.Manutenzione;
import model.Mezzo;

public class GestioneMezziCasermaController extends Controller implements IGestioneMezzi{
<<<<<<< HEAD
	private RichiestaSostituzione richiestaSostituzione = new RichiestaSostituzione();
=======

	private RichiestaManutenzioneController manutenzione;
	private RichiestaSostituzioneController sostituzione;
>>>>>>> branch 'master' of https://github.com/AngeloFarina/Gruppo6-Progetto.git
	
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

	public void richiesta(String tipo,String idMezzo,String idCaserma) {
		
	}
	
}
