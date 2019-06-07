package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import interfacce.IGestioneManutenzione;
import interfacce.IRichiestaManutenzione;
import model.Manutenzione;
import model.RichiestaManutenzione;

public class RichiestaManutenzioneController implements IRichiestaManutenzione{

	private Connection db;
	
	public RichiestaManutenzioneController(Connection db) {
		this.db = db;
	}
	
	@Override
	public void richiesta(RichiestaManutenzione r) {
		Statement stmt;
		//INSERIMENTO MANUTZIONE
		try {
			stmt = db.createStatement();
			String sql = "INSERT INTO richiestaManutenzione (idCaserma,idMezzo,dataOra,descrizione) "+
						"VALUES ('"+r.getIdCaserma()+"',"+"'"+r.getIdMezzo()+"',"+"'"+r.getDataOra()+"',"+"'"+r.getDescrizione()+"')";
			int result = stmt.executeUpdate(sql);
			System.out.println("inserimento richiesta manutenzione...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
