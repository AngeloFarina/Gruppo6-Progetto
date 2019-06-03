package controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TabellaCapoSquadra;
import model.TabellaVigile;

public class ControllerClientCaserma {
	private ObservableList<TabellaCapoSquadra> mezziCapo = null;
	private ObservableList<TabellaVigile> mezziVigile = null;
	private String idCaserma = null;
	private String nomeCaserma = null;
	private String luogoCaserma = null;
	private String nome = null;
	private int totMezzi=0;
	private int man=0;
	private int carburante=0;
	
	public ControllerClientCaserma(String idCaserma,String nome) throws SQLException {
		this.idCaserma=idCaserma;
		this.nome=nome;
		mezziCapo = FXCollections.observableArrayList();
		mezziVigile = FXCollections.observableArrayList();
		init();
	}
	
	private void init() throws SQLException {
		ObservableList<TabellaCapoSquadra> mezziCapo = FXCollections.observableArrayList();
		ObservableList<TabellaVigile> mezziVigile = FXCollections.observableArrayList();
		File f = new File("vigilidb");
		String dbUri = "jdbc:sqlite:"+f.getAbsolutePath();
		Connection db = null;
		db = DriverManager.getConnection(dbUri);
		Statement stmt  = db.createStatement();
		String sql = "SELECT M.* " + 
				"FROM Caserma C, Mezzo M " + 
				"WHERE M.idCaserma=C.id and C.id='"+idCaserma+ 
				"' group by M.id ";
		ResultSet rs = stmt.executeQuery(sql);
		int totMezzi = 0,man=0;
		while (rs.next()) {
			totMezzi++;
        	mezziCapo.addAll(new TabellaCapoSquadra(
        			rs.getString("tipo"),
        			rs.getString("id"),
        			rs.getString("stato"),
        			rs.getString("assegnazione"),
        			rs.getInt("anno")+""));
        	mezziVigile.addAll(new TabellaVigile(rs.getString("tipo"),
        			rs.getString("id"),
        			rs.getString("stato"),
        			rs.getString("assegnazione"),
        			rs.getInt("anno")+""));
        	if(rs.getString("stato").contentEquals("IN MANUTENZIONE"))
        		man++;
        }
		this.man=man;
		this.totMezzi=totMezzi;
		rs = stmt.executeQuery("SELECT * FROM CASERMA WHERE ID='" + idCaserma +"'");
		if(rs.next()) {
			this.nomeCaserma=rs.getString("nome");
			this.luogoCaserma = rs.getString("luogo");
			this.carburante= rs.getInt("cisterna");
		}
		this.mezziCapo.addAll(mezziCapo);
		this.mezziVigile.addAll(mezziVigile);
	}
	
	public ObservableList<TabellaCapoSquadra> caricaMezziCapo() {
		return FXCollections.observableArrayList(mezziCapo);
	}
	
	public ObservableList<TabellaVigile> caricaMezziVigile(){
		return FXCollections.observableArrayList(mezziVigile);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCaserma() {
		if(nomeCaserma==null)
			return "CASERMA";
		return luogoCaserma + " " + nomeCaserma;
	}
	
	public int getTotMezzi() {
		return totMezzi;
	}
	
	public int getMan() {
		return man;
	}
	
	public int getLitri () {
		return carburante;
	}
	
}
