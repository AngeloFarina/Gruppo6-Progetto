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

public class VisualizzaMezziProvinciaController extends Controller implements IVisualizzaMezzi{

	private String idProvincia;
	
	public VisualizzaMezziProvinciaController(String connString, String pathFileOp, String pathFileMsg, String idProvincia) throws SQLException {
		super(connString, pathFileOp, pathFileMsg);
		this.idProvincia = idProvincia;
		
	}
	
	public List<Mezzo> visualizzaMezziProvincia() throws SQLException{
		List<Mezzo> result = new ArrayList<Mezzo>();
		
		Connection db = getConnection();
		Statement stmt  = db.createStatement();
		String sql = "SELECT M.* " + 
				"FROM Caserma C, Mezzo M " + 
				"WHERE M.idCaserma=C.id and C.idProvincia='"+idProvincia+ 
				"' group by M.id " + 
				"order by C.luogo";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
            	result.add(new Mezzo(rs.getString("id"), 
            			rs.getString("tipo"),
            			rs.getInt("anno"),
            			rs.getString("marca"),
            			rs.getString("modello"),
            			Stato.valueOf(rs.getString("stato")),
            			Assegnazione.valueOf(rs.getString("assegnazione")))); 
        }
		return result;
	}
	
	public static void main(String args[]) throws SQLException {
		VisualizzaMezziProvinciaController c = new VisualizzaMezziProvinciaController("","","","BO001");
		List<Mezzo> temp = c.visualizzaMezziProvincia();
		for (Mezzo mezzo : temp) {
			System.out.println(mezzo.toString());
		}
		
	}

}
