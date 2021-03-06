package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Mezzo;
import model.Stato;
import model.TabellaCapoSquadra;
import model.TabellaVigile;

public class ControllerClientCaserma {
	private static final int BROKERPORT = 1051;
	private ObservableList<TabellaCapoSquadra> mezziCapo = null;
	private ObservableList<TabellaVigile> mezziVigile = null;
	private String idCaserma = null;
	private String nomeCaserma = null;
	private String luogoCaserma = null;
	private String nome = null;
	private int totMezzi=0;
	private int man=0;
	private String carburante="0";
	
	private List<Mezzo> mezzi = null;
	
	public ControllerClientCaserma(String idCaserma,String nome) throws SQLException {
		this.idCaserma=idCaserma;
		this.nome=nome;
		mezziCapo = FXCollections.observableArrayList();
		mezziVigile = FXCollections.observableArrayList();
		try {
			init();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void init() throws UnknownHostException, IOException, ClassNotFoundException {
		ObservableList<TabellaCapoSquadra> mezziCapo = FXCollections.observableArrayList();
		ObservableList<TabellaVigile> mezziVigile = FXCollections.observableArrayList();
		Socket clientSocket = new Socket("localhost",BROKERPORT);
		System.out.println("ClientCaserma: creata Socket: " +clientSocket.getLocalSocketAddress());
		DataOutputStream outSock = new DataOutputStream(clientSocket.getOutputStream());
		ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
		outSock.writeUTF("localhost");
		outSock.writeUTF("localhost");
		outSock.writeUTF("mezziCasermaCaserma");
		List<String> param = new ArrayList<String>();
		param.add(idCaserma);
		outObj.writeObject(param);
		List<Object> dati = new ArrayList<Object>((List<Object>)inObj.readObject());
		clientSocket.close();
		List<Mezzo> mezzi = new ArrayList<Mezzo>((List<Mezzo>)dati.get(0));
		this.mezzi=new ArrayList<Mezzo>(mezzi);
		List<String> infoCaserma = new ArrayList<String>((List<String>)dati.get(1));
		for(Mezzo m : mezzi) {
			totMezzi++;
			if(m.getStato().equals(Stato.NONDISPONIBILE))
				man++;
			mezziCapo.add(new TabellaCapoSquadra(m.getTipo(), m.getId(), m.getStato(), m.getAssegnazione(), m.getAnno() +"",this.idCaserma));
			mezziVigile.add(new TabellaVigile(m.getTipo(),m.getId(),m.getStato(),m.getAssegnazione(),m.getAnno()+""));
		}
		this.mezziCapo.addAll(mezziCapo);
		this.mezziVigile.addAll(mezziVigile);
		nomeCaserma = infoCaserma.get(0);
		luogoCaserma = infoCaserma.get(1);
		carburante = infoCaserma.get(2);
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
	
	public String getLitri () {
		return carburante;
	}
	
	public String getIdCaserma() {
		return this.idCaserma;
	}
	
	public List<Mezzo> getMezzi(){
		return new ArrayList<Mezzo>(mezzi);
	}
	
}
