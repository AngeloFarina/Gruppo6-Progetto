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
import model.Manutenzione;
import model.RichiestaManutenzione;

public class RichiestaManutenzioneController implements IGestioneManutenzione{

	private Connection db;
	
	public RichiestaManutenzioneController(Connection db) {
		this.db = db;
	}
	
	@Override
	public Manutenzione concludiManutenzione(RichiestaManutenzione r) {
		Statement stmt;
		//INSERIMENTO MANUTZIONE
		try {
			stmt = db.createStatement();
			String sql = "INSERT INTO manutenzione (dataOraInizio,dataOraFine,descrizione,idMezzo) "+
						"VALUES ('"+r.getDataOra()+"',"+"'"+Date.from(Instant.now())+"',"+"'"+r.getDescrizione()+"',"+"'"+r.getIdMezzo()+"')";
			int result = stmt.executeUpdate(sql);
			System.out.println("inserimento manutenzione completata...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "SELECT * FROM manutenzione WHERE dataOraInizio='"+r.getDataOra()+"'";
		ResultSet rs;
		Manutenzione result = null;
		//LETTURA MANUTENZIONE PER ID E RESTITUZIONE OGGETTO MANUTENZIONE
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery(sql);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = new Manutenzione(rs.getString("id"), rs.getString("descrizione"), df.parse(rs.getString("dataOraInizio")), df.parse(rs.getString("dataOraFine")), rs.getString("idMezzo"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
