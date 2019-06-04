package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Date;

import interfacce.ICreazioneReport;
import model.Report;

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
			String sql = "INSERT INTO report (id,kmEffettuati,carburanteConsumato,descrizione,tipologiaUscita,dataOra) "+
						"VALUES ('"+r.getId()+"',"+"'"+r.getKmEffettuati()+"',"+"'"+r.getCarburanteConsumato()+"',"+"'"+r.getDescrizione()+"','"+ r.getTipologiaUscita()+"',"+
						"'"+r.getDataOra()+"')";
			int result = stmt.executeUpdate(sql);
			System.out.println("inserimento report...\nResult: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	public static void main(String[] args) {
		CreazioneReportController report = new CreazioneReportController("", "", "");
		report.creaReport(new Report("0001", 37,58, "nel mezzo del cammin di nostra vita...", "caffettino", Date.from(Instant.now())));
		
	}

}
