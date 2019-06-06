package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import interfacce.ICreazioneReport;
import model.Report;
import model.Tipo;

public class CreazioneReportController extends Controller implements ICreazioneReport{

	public CreazioneReportController(String connString, String pathFileOp, String pathFileMsg) {
		super(connString, pathFileOp, pathFileMsg);
	}

	@Override
	public void creaReport(Report r) {
		Statement stmt;
		try {
			Connection db = getConnection();
			stmt = db.createStatement();
			String sql = "INSERT INTO report (kmEffettuati,carburanteConsumato,descrizione,tipologiaUscita,dataOra,idCaserma) "+
						"VALUES ('"+r.getKmEffettuati()+"',"+"'"+r.getCarburanteConsumato()+"',"+"'"+r.getDescrizione()+"','"+ r.getTipo().toString() + "',"+
						"'"+r.getDataOra().toString()+ "'," + "'" + r.getIdCaserma() + "')";
			int result = stmt.executeUpdate(sql);
			System.out.println("inserimento report...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	

}
