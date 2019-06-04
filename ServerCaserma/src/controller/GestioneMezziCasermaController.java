package controller;

import interfacce.IGestioneMezzi;

public class GestioneMezziCasermaController extends Controller implements IGestioneMezzi{
	private RichiestaSostituzione richiestaSostituzione = new RichiestaSostituzione();
	
	public GestioneMezziCasermaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

	public void richiesta(String tipo,String idMezzo,String idCaserma) {
		
	}
	
}
