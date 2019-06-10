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
	public List<Manutenzione> listaManutenzioni() throws SQLException {
		List<Manutenzione> result = new ArrayList<Manutenzione>();
		Connection db = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		//Statement temp=null;
		ResultSet mezzo = null;
		System.out.println("Eseguo caricamento storico");
		try {
			stmt = db.createStatement();
			String sql = "SELECT * " + 
					"FROM Manutenzione";
			rs = stmt.executeQuery(sql);
			//temp=db.createStatement();
			while (rs.next()) {
				mezzo = db.createStatement().executeQuery("SELECT * FROM MEZZO WHERE ID='" + rs.getString("idMezzo") + "'");
				System.out.println("Mezzo trovato in db: " + mezzo.getString(1));
				Mezzo m = new Mezzo(mezzo.getString("id"),
						mezzo.getString("tipo"),
						mezzo.getInt("anno"),
						mezzo.getString("marca"),
						mezzo.getString("modello"),
						Stato.valueOf(mezzo.getString("stato")),
						Assegnazione.valueOf(mezzo.getString("assegnazione")));
				result.add(new Manutenzione(rs.getString("id"),
            			rs.getString("dataOraInizio"),
            			rs.getString("dataOraFine"),
            			rs.getString("descrizione"),
            			m));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore generico lettura db...");
		}finally {
			try {
				mezzo.close();
				rs.close();
				stmt.close();
				//temp.close();
				db.close();
			}catch (SQLException e) {
				throw new SQLException("chiusura db fallita...");
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws SQLException {
		StoricoManutenzioniController manutenzioni = new StoricoManutenzioniController("", "", "");
		List<Manutenzione> l = manutenzioni.listaManutenzioni();
		for (Manutenzione manutenzione : l) {
			System.out.println(manutenzione.toString());
		}
	}

}
