package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfacce.IVisualizzaMezzi;
import model.Assegnazione;
import model.Mezzo;
import model.Stato;

public class VisualizzaMezziCasermaController extends Controller implements IVisualizzaMezzi {

	public VisualizzaMezziCasermaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}
	
	public List<Mezzo> visualizzaMezzi(String idCaserma) throws SQLException{
		List<Mezzo> result = new ArrayList<Mezzo>();
		Connection db = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT M.* " + 
				"FROM Caserma C, Mezzo M " + 
				"WHERE M.idCaserma=C.id and C.id='"+idCaserma+ 
				"' group by M.id " + 
				"order by C.luogo";
		try {
			db = getConnection();
			stmt  = db.createStatement();
			rs = stmt.executeQuery(sql);
			/*
			 * creazione della lista dei mezzi
			 */
			while (rs.next()) {
				result.add(new Mezzo(rs.getString("id"), rs.getString("tipo"), rs.getInt("anno"), rs.getString("marca"),
						rs.getString("modello"), Stato.valueOf(rs.getString("stato")),
						Assegnazione.valueOf(rs.getString("assegnazione"))));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				db.close();
			}catch (SQLException e) {
				throw new SQLException("Chiusura db fallita");
			}
		}
		return result;
	}
	
	public static void main(String args[]) throws SQLException {
		VisualizzaMezziCasermaController c = new VisualizzaMezziCasermaController("","","");
		List<Mezzo> temp = c.visualizzaMezzi("BO003");
		for (Mezzo mezzo : temp) {
			System.out.println(mezzo.toString());
		}
		
	}
}
