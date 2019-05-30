package controller;

import java.sql.Connection;

import interfacce.IGestioneSostituzione;
import model.Mezzo;
import model.RichiestaSostituzione;

public class GestioneSostituzione implements IGestioneSostituzione {

	private Connection db;
	
	public GestioneSostituzione(Connection db) {
		this.db = db;
	}
	
	@Override
	public void effettuaSostituzione(Mezzo m, RichiestaSostituzione s) {
		
	}

}
