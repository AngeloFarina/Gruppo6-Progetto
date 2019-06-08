package controller;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import interfacce.IGestioneSostituzione;
import model.RichiestaSostituzione;

public class GestioneSostituzione extends Controller implements IGestioneSostituzione {

	
	
	
	public GestioneSostituzione(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

	@Override
	public void effettuaSostituzione(String idMezzo, RichiestaSostituzione r) throws Exception{
		Connection db = getConnection();
		Statement stmt;
		try {
			stmt = db.createStatement();
			String sql = "DELETE FROM RICHIESTASOSTITUZIONE WHERE idMezzo='" + r.getIdMezzo() +"' AND idCaserma='" + r.getIdCaserma()+"'";
			int result = stmt.executeUpdate(sql);
			System.out.println("Gestione sostituzione...\nResult: "+result);
			sql = "UPDATE MEZZO SET idCaserma='" + r.getIdCaserma() + "',assegnazione='SOSTITUTIVO' WHERE id='" + idMezzo + "'";
			result = stmt.executeUpdate(sql);
			System.out.println("Aggiornamento posizione mezzo...\nResult: "+result);
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
