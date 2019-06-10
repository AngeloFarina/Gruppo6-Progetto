package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.RichiestaSostituzione;

public class ControllerCaricamentoRichiesteSostituzione extends Controller {

	public ControllerCaricamentoRichiesteSostituzione(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}
	
	public List<RichiestaSostituzione> caricaRichieste(String idProvincia) throws Exception{
		Connection db = getConnection();
		Statement stmt = null;
		Statement temp = null;
		ResultSet rs = null;
		List<RichiestaSostituzione> richieste = new ArrayList<RichiestaSostituzione>();
		String sql = "SELECT * FROM RichiestaSostituzione\n" + 
					"WHERE idCaserma IN (SELECT idCaserma FROM CASERMA WHERE idProvincia='" + idProvincia + "')";
		try {
			stmt = db.createStatement();
			temp = db.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String tipo = temp.executeQuery("SELECT TIPO FROM MEZZO WHERE ID='" + rs.getString("idMezzo") + "'")
						.getString(1);
				richieste.add(new RichiestaSostituzione(rs.getString("idCaserma"), rs.getString("idMezzo"), tipo,
						rs.getString("dataOra"), rs.getString("descrizione")));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				temp.close();
				db.close();
			}catch (Exception e) {
				throw new Exception("Errore chiusura db...");
			}
		}
		System.out.println("Richieste: "+ richieste);
		return richieste;
	}

}
