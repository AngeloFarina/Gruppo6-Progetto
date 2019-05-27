package controller;

import interfacce.IGestioneMezzi;

public class GestioneMezziCasermaController extends Controller implements IGestioneMezzi{

	public GestioneMezziCasermaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

}
