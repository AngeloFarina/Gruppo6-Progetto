package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InformazioniCasermaController extends Controller {

	public InformazioniCasermaController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
		
	}

	//Restituisce nome, luogo e cisterna della caserma passata come parametro
	public List<String> getInformazioniCaserma(String idCaserma){
		Statement stmt;
		List<String> res = new ArrayList<String>();
		try {
			Connection db = getConnection();
			stmt = db.createStatement();
			String sql = "SELECT NOME, LUOGO, CISTERNA FROM CASERMA WHERE ID='" + idCaserma + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				res.add(rs.getString("nome"));
				res.add(rs.getString("luogo"));
				res.add(rs.getInt("cisterna")+"");
			}
			System.out.println("Query eseguita: " + res);
			db.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}
