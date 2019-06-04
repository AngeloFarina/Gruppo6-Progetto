package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import interfacce.IGestioneSostituzione;
import model.Mezzo;
import model.RichiestaSostituzione;

public class RichiestaSostituzioneController implements IGestioneSostituzione{

	private Connection db;
	
	public RichiestaSostituzioneController(Connection db) {
		this.db = db;
	}

	@Override
	public void effettuaSostituzione(Mezzo m, RichiestaSostituzione r) {
		Statement stmt;
		//INSERIMENTO MANUTZIONE
		try {
			stmt = db.createStatement();
			String sql = "INSERT INTO richiestaSostituzione (dataOraInizio,dataOraFine,descrizione,idMezzo) "+
						"VALUES ('"+r.getIdCaserma()+"',"+"'"+r.getIdMezzo()+"',"+"'"+r.getDescrizione()+"',"+"'"+r.getDataOra()+"')";
			int result = stmt.executeUpdate(sql);
			System.out.println("inserimento richiesta sostituzione...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
