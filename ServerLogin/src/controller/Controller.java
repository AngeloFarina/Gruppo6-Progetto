package controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {
	private Connection dbConnection;
	private Writer printerOperazioni;
	private Writer printerMessaggi;
	
	// nome del database
	private String dbName = "sit_stud";
	
	public Controller(String connString, String pathFileOp, String pathFileMsg) {
		try {
			dbConnection = openConnection(connString);
			printerOperazioni = openLogOperation(pathFileOp);
			printerMessaggi = openLogMessaggi(pathFileMsg);
		} catch (Exception e) {
			
		}
	}
	
	private Connection openConnection(String connString) throws PersistenceException {
		String driver;
		String dbUri;
		String userName = "";
		String password = "";
		userName = "username";
		password = "password";
		
		driver = "com.ibm.db2.jcc.DB2Driver";
		dbUri = "jdbc:db2://diva.deis.unibo.it:50000/"+dbName;
		
		try {
			System.out.println("DataSource.getConnection() driver = "+driver);
		    Class.forName(driver);
			System.out.println("DataSource.getConnection() dbUri = "+dbUri);
		    dbConnection = DriverManager.getConnection(dbUri, userName, password);
		}
		catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		}
		catch(SQLException e) {
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
		try {
			this.printerMessaggi.write(m);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	protected void printOperazione(String m) {
		try {
			this.printerOperazioni.write(m);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() {
		return dbConnection;
	}
}
