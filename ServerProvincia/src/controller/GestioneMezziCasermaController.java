package controller;

import interfacce.IGestioneMezzi;
import model.Caserma;
import model.Mezzo;

public class GestioneMezziCasermaController extends Controller implements IGestioneMezzi {

	public GestioneMezziCasermaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
		// TODO Auto-generated constructor stub
	}
	
	public boolean richiesta(String tipoRichiesta, Mezzo m, Caserma c) {
		return true;
	}
}
