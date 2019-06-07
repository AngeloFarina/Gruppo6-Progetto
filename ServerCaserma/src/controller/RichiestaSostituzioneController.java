package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import interfacce.IRichiestaSostituzione;
import model.RichiestaSostituzione;

public class RichiestaSostituzioneController implements IRichiestaSostituzione{

	private Connection db;
	
	public RichiestaSostituzioneController(Connection db) {
		this.db = db;
	}

	@Override
	public void richiesta(RichiestaSostituzione r) {
		Statement stmt;
		//INSERIMENTO RICHIESTA SOSTITUZIONE
		try {
			stmt = db.createStatement();
			String sql = "INSERT INTO richiestaSostituzione (idCaserma,idMezzo,dataOra,descrizione) "+
						"VALUES ('"+r.getIdCaserma()+"',"+"'"+r.getIdMezzo()+"',"+"'"+r.getDataOra()+"',"+"'"+r.getDescrizione()+"')";
			int result = stmt.executeUpdate(sql);
			System.out.println("inserimento richiesta sostituzione...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
