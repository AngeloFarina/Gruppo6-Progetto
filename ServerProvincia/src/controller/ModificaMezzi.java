package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import interfacce.IModificaMezzi;
import model.Assegnazione;
import model.Caserma;
import model.Mezzo;
import model.Stato;

public class ModificaMezzi implements IModificaMezzi {

	private Connection db;
	
	public ModificaMezzi(Connection db) {
		this.db = db;
	}
	
	@Override
	public void eliminaMezzo(Mezzo m) {
		Statement stmt;
		try {
			stmt = db.createStatement();
			String sql = "DELETE FROM Mezzo WHERE id='"+m.getId()+"'";
			int result = stmt.executeUpdate(sql);
			System.out.println("deleting mezzo...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void aggiungiMezzo(Mezzo m, Caserma c) {
		Statement stmt;
		try {
			stmt = db.createStatement();
			String sql = "DELETE FROM Mezzo WHERE id='"+m.getId()+"'";
			int result = stmt.executeUpdate(sql);
			System.out.println("deleting mezzo...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//"INSERT INTO Mezzo (id,tipo,anno,marca,modello,idCaserma,stato,assegnazione) VALUES ('"+m.getId()+"' ,"+"'"+m.getTipo()+"' ,"+"'"+m.getAnno()+"',"+"'"+m.getMarca()+"',"+ 'modello',
        //        'idCaserma',
        //        'stato',
        //        'assegnazione'
        //    );""
	}

	@Override
	public void modificaMezzo(Mezzo m) {
		// TODO Auto-generated method stub

	}

}
