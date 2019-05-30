package controller;

import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
	private Connection dbConnection;
	private Writer printerOperazioni;
	private Writer printerMessaggi;
	
	public Controller(String connString, String pathFileOp, String pathFileMsg) {
		try {
			dbConnection = openConnection(connString);
			printerOperazioni = openLogOperation(pathFileOp);
			printerMessaggi = openLogMessaggi(pathFileMsg);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private Connection openConnection(String connString) throws PersistenceException {
		String dbUri;

		dbUri = "jdbc:sqlite:C:\\Users\\Paolo\\git\\Gruppo6-Progetto\\ServerProvincia\\src\\controller\\vigilidb";
		
		try {
		    dbConnection = DriverManager.getConnection(dbUri);
		    System.out.println("Connessione avvenuta!!!!!");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		}
		return dbConnection;
	}
	
	private Writer openLogOperation(String pathFile) {
		return null; //////MOOOOOCCKKKKK
	}
	
	private Writer openLogMessaggi(String pathFile) {
		return null; //////MOOOOOCCKKKKK
	}
	
	protected void printMessaggio(String m) {
		
	}
	
	protected void printOperazione(String m) {
		
	}
	
	protected Connection getConnection() {
		return dbConnection;
	}
	
	public static void main(String args[]) throws SQLException {
		Controller c = new Controller("","","");
		Connection db = c.getConnection();
		Statement stmt  = db.createStatement();
		String sql = "SELECT * FROM Mezzo";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
            System.out.println(rs.getString("id") +  "\t" + 
                               rs.getString("tipo") + "\t" +
                               rs.getInt("anno") + "\t" +
                               rs.getString("marca") + "\t" +
                               rs.getString("modello") +"\t" +
                               rs.getString("idCaserma"));
        }
		
	}
}
