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
import model.Assegnazione;
import model.Manutenzione;
import model.Mezzo;
import model.Stato;

public class StoricoManutenzioniController extends Controller implements IStoricoManutenzioni {

	public StoricoManutenzioniController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

	@Override
	public List<Manutenzione> listaManutenzioni() {
		List<Manutenzione> result = new ArrayList<Manutenzione>();
		Connection db = getConnection();
		Statement stmt;
		System.out.println("Eseguo caricamento storico");
		try {
			stmt = db.createStatement();
			String sql = "SELECT * " + 
					"FROM Manutenzione";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Statement temp=db.createStatement();
				ResultSet mezzo = temp.executeQuery("SELECT * FROM MEZZO WHERE ID='" + rs.getString("idMezzo") + "'");
				System.out.println("Mezzo trovato in db: " + mezzo);
				Mezzo m = new Mezzo(mezzo.getString("id"),
						mezzo.getString("tipo"),
						mezzo.getInt("anno"),
						mezzo.getString("marca"),
						mezzo.getString("modello"),
						Stato.valueOf(mezzo.getString("stato")),
						Assegnazione.valueOf(mezzo.getString("assegnazione"))
						);
				result.add(new Manutenzione(rs.getString("id"),
            			rs.getString("dataOraInizio"),
            			rs.getString("dataOraFine"),
            			rs.getString("descrizione"),
            			m));
			}
			db.close();
		} catch (SQLException e) {
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
