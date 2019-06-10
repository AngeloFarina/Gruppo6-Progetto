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

public class ModificaMezzi  extends Controller implements IModificaMezzi {

	
	
	
	public ModificaMezzi(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

	@Override
	public void eliminaMezzo(Mezzo m) {
		Connection db = getConnection();
		Statement stmt = null;
		try {
			stmt = db.createStatement();
			String sql = "DELETE FROM Mezzo WHERE id='"+m.getId()+"'";
			int result = stmt.executeUpdate(sql);
			System.out.println("deleting mezzo...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				db.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void aggiungiMezzo(Mezzo m, String c) {
		Connection db = getConnection();
		Statement stmt = null;
		try {
			stmt = db.createStatement();
			String sql = "INSERT INTO Mezzo (id,tipo,anno,marca,modello,idCaserma,stato,assegnazione) "+
						"VALUES ('"+m.getId()+"',"+"'"+m.getTipo()+"',"+"'"+m.getAnno()+"',"+"'"+m.getMarca()+"','"+ m.getModello()+"',"+
						"'"+c+"','"+m.getStato()+"','"+m.getAssegnazione()+"')";
			int result = stmt.executeUpdate(sql);
			System.out.println("updating mezzo...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				db.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void modificaMezzo(Mezzo m,String id) {
		Connection db = getConnection();
		Statement stmt = null;
		System.out.println("Mezzo da aggiornare: " + id + " Nuovo mezzo " + m.getId());
		try {
			stmt = db.createStatement();
			String sql = "UPDATE MEZZO SET id='"+m.getId()+"',tipo='"+m.getTipo()+"',anno='"+m.getAnno()+
					"',stato='"+m.getStato()+"',assegnazione='"+m.getAssegnazione()+"', idCaserma='"+id.split(" ")[0] +
					"'WHERE id='"+id.split(" ")[1]+"'";
			System.out.println(sql);
			int result = stmt.executeUpdate(sql);
			System.out.println("updating mezzo...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				db.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
