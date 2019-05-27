package controller;

import java.io.Writer;
import java.sql.Connection;

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
			// TODO: handle exception
		}
	}
	
	private Connection openConnection(String connString) {
		return null; //////MOOOOOCCKKKKK
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
		return null;
	}
}
