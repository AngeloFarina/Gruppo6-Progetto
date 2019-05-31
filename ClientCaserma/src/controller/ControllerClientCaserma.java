package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Prova;

public class ControllerClientCaserma {
	private String idCaserma;
	private String nomeCaserma = null;
	private String luogoCaserma = null;
	
	public ControllerClientCaserma(String idCaserma) {
		this.idCaserma=idCaserma;
	}
	
	public ObservableList<Prova> caricaMezziCaserma() throws SQLException {
		ObservableList<Prova> result = FXCollections.observableArrayList();
		String dbUri = "jdbc:sqlite:C:\\Users\\Paolo\\git\\Gruppo6-Progetto\\ClientCaserma\\vigilidb";
		Connection db = null;
		db = DriverManager.getConnection(dbUri);
		Statement stmt  = db.createStatement();
		String sql = "SELECT M.* " + 
				"FROM Caserma C, Mezzo M " + 
				"WHERE M.idCaserma=C.id and C.id='"+idCaserma+ 
				"' group by M.id ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
        	result.addAll(new Prova(
        			rs.getString("tipo"),
        			rs.getString("id"),
        			rs.getString("stato")));
		}
		rs = stmt.executeQuery("SELECT NOME,LUOGO FROM CASERMA WHERE ID='" + idCaserma +"'");
		if(rs.next()) {
			nomeCaserma=rs.getString(1);
			luogoCaserma = rs.getString(2);
		}
		return result;
	}
	
	public String getCaserma() {
		if(nomeCaserma==null)
			return "CASERMA";
		return luogoCaserma + " " + nomeCaserma;
	}
	
}
