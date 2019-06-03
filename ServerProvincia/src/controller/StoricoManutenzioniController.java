package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import interfacce.IStoricoManutenzioni;
import model.Manutenzione;

public class StoricoManutenzioniController extends Controller implements IStoricoManutenzioni {

	public StoricoManutenzioniController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

	@Override
	public List<Manutenzione> listaManutenzioni() {
		List<Manutenzione> result = new ArrayList<Manutenzione>();
		Connection db = getConnection();
		Statement stmt;
		try {
			stmt = db.createStatement();
			String sql = "SELECT * " + 
					"FROM Manutenzione";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	result.add(new Manutenzione(rs.getString("id"), 
            			df.parse(rs.getString("dataOraInizio")),
            			df.parse(rs.getString("dataOraFine")),
            			rs.getString("descrizione"))); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		StoricoManutenzioniController manutenzioni = new StoricoManutenzioniController("", "", "");
		List<Manutenzione> l = manutenzioni.listaManutenzioni();
		for (Manutenzione manutenzione : l) {
			System.out.println(manutenzione.toString());
		}
	}

}
