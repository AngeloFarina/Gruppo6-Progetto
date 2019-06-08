package controller;

import java.sql.Connection;

import interfacce.IGestioneSostituzione;
import model.Mezzo;
import model.RichiestaSostituzione;

public class GestioneSostituzione extends Controller implements IGestioneSostituzione {

	
	
	
	public GestioneSostituzione(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

	@Override
	public void effettuaSostituzione(Mezzo m, RichiestaSostituzione s) {
		
	}

}
