package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfacce.IVisualizzaMezzi;
import model.Assegnazione;
import model.Caserma;
import model.Mezzo;
import model.Stato;

public class VisualizzaMezziProvinciaController extends Controller implements IVisualizzaMezzi{

	private VisualizzaMezziCasermaController visualizzaMezziCaserma;
	
	public VisualizzaMezziProvinciaController(String connString, String pathFileOp, String pathFileMsg) throws SQLException {
		super(connString, pathFileOp, pathFileMsg);
		this.visualizzaMezziCaserma = new VisualizzaMezziCasermaController(connString,pathFileOp,pathFileMsg);
	}
	
	public List<Caserma> visualizzaMezziProvincia(String idProvincia) throws SQLException{
		List<Caserma> result = new ArrayList<Caserma>();
		
		ResultSet rs = null;
		Connection db = null;
		Statement stmt = null;
		String sql = "SELECT * " + 
				"FROM Caserma C " + 
				"WHERE C.idProvincia='"+idProvincia+"'";
		try {
			db = getConnection();
			stmt  = db.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				result.add(new Caserma(rs.getString("id"), rs.getString("luogo"), rs.getInt("cisterna"),
						visualizzaMezziCaserma.visualizzaMezzi(rs.getString("id"))));
			} 
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			try {
				rs.close();
				stmt.close();
				db.close();
			}catch (Exception e) {
				throw new SQLException("chiusura db fallita...");
			}
		}
		return result;
	}
	
	@Override
	public List<Mezzo> visualizzaMezzi(String idProvincia) throws SQLException{
		List<Mezzo> result = new ArrayList<Mezzo>();
		Connection db = null;
		Statement stmt  = null;
		ResultSet rs = null;
		String sql = "SELECT M.* " + 
				"FROM Caserma C, Mezzo M " + 
				"WHERE M.idCaserma=C.id and C.id='"+idProvincia+ 
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
			throw new SQLException();
		} finally {
			try {
				rs.close();
				stmt.close();
				db.close();
			}catch (Exception e) {
				throw new SQLException("chiusura db fallita...");
			}
		}
		return result;
	}
	
	public static void main(String args[]) throws SQLException {
		VisualizzaMezziProvinciaController c = new VisualizzaMezziProvinciaController("","","");
		System.out.println("*****************VISUALIZZA MEZZI PROVINCIA*****************");
		List<Caserma> temp = c.visualizzaMezziProvincia("BO001");
		for (Caserma mezzo : temp) {
			System.out.println(mezzo.toString());
		}
		System.out.println("*****************VISUALIZZA MEZZI CASERMA*****************");
		List<Mezzo> temp2 = c.visualizzaMezzi("BO001");
		for (Mezzo mezzo : temp2) {
			System.out.println(mezzo.toString());
		}
		
	}

}
