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
		this.visualizzaMezziCaserma = new VisualizzaMezziCasermaController("", "", "");
	}
	
	public List<Caserma> visualizzaMezziProvincia(String idProvincia) throws SQLException{
		List<Caserma> result = new ArrayList<Caserma>();
		
		Connection db = getConnection();
		Statement stmt  = db.createStatement();
		String sql = "SELECT * " + 
				"FROM Caserma C " + 
				"WHERE C.idProvincia='"+idProvincia+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
            	result.add(new Caserma(rs.getString("id"), 
            			rs.getString("luogo"),
            			rs.getInt("cisterna"),
            			visualizzaMezziCaserma.visualizzaMezzi(rs.getString("id"))));
        }
		return result;
	}
	
	@Override
	public List<Mezzo> visualizzaMezzi(String idProvincia) throws SQLException{
		List<Mezzo> result = new ArrayList<Mezzo>();
		
		Connection db = getConnection();
		Statement stmt  = db.createStatement();
		String sql = "SELECT M.* " + 
				"FROM Caserma C, Mezzo M " + 
				"WHERE M.idCaserma=C.id and C.id='"+idProvincia+ 
				"' group by M.id " + 
				"order by C.luogo";
		ResultSet rs = stmt.executeQuery(sql);
		/*
		 * creazione della lista dei mezzi
		 */
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
