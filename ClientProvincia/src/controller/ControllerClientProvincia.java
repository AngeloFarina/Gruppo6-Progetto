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
import model.Caserma;
import model.Mezzo;
import model.Stato;
import model.TabellaAmministratore;
import model.TabellaVigile;

public class ControllerClientProvincia {
	private static final int BROKERPORT = 1051;
	
	private ObservableList<TabellaAmministratore> mezziProvincia = null;
	private ObservableList<TabellaAmministratore> mezziCaserme = null;
	private String idCaserma = null;
	private String nomeCaserma = null;
	private String luogoCaserma = null;
	private String nome = null;
	private int totMezzi=0;
	private int man=0;
	private String carburante="0";
	
	
	public ControllerClientProvincia(String idCaserma,String nome) {
		this.idCaserma=idCaserma;
		this.nome=nome;
		mezziProvincia = FXCollections.observableArrayList();
		mezziCaserme = FXCollections.observableArrayList();
		init();
	}
	
	private void init() {
		List<Mezzo> mezzi = null;
		List<Caserma> caserme = null;
		try {
		Socket clientSocket = new Socket("localhost",BROKERPORT);
		System.out.println("ClientProvincia: creata Socket: " +clientSocket.getLocalSocketAddress());
		DataOutputStream outSock = new DataOutputStream(clientSocket.getOutputStream());
		ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
		outSock.writeUTF("localhost");
		outSock.writeUTF("localhost");
		outSock.writeUTF("mezziCasermaProvincia");
		List<String> param = new ArrayList<String>();
		param.add(idCaserma);
		outObj.writeObject(param);
		mezzi = new ArrayList<Mezzo>((List<Mezzo>)inObj.readObject());
		clientSocket.close();
		System.out.println("Ricevuti i mezzi: " + mezzi);
		for(Mezzo m : mezzi) {
			mezziProvincia.add(new TabellaAmministratore(m.getTipo(), m.getId(), m.getStato(), m.getAssegnazione(), m.getAnno() +"",this.idCaserma));
		}
		clientSocket = new Socket("localhost",BROKERPORT);
		System.out.println("ClientProvincia: creata Socket: " +clientSocket.getLocalSocketAddress());
		outSock = new DataOutputStream(clientSocket.getOutputStream());
		outObj = new ObjectOutputStream(clientSocket.getOutputStream());
		inObj = new ObjectInputStream(clientSocket.getInputStream());
		outSock.writeUTF("localhost");
		outSock.writeUTF("localhost");
		outSock.writeUTF("mezziProvincia");
		param = new ArrayList<String>();
		param.add(idCaserma);
		outObj.writeObject(param);
		caserme = new ArrayList<Caserma>((List<Caserma>)inObj.readObject());
		clientSocket.close();
		}catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Ricevute le caserme: " + caserme);
		for(Caserma c : caserme) {
			for(Mezzo m : c.getMezzi()){
				mezziCaserme.add(new TabellaAmministratore(m.getTipo(), m.getId(), m.getStato(), m.getAssegnazione(), m.getAnno() +"",c.getId() + " " + c.getCitta()));
			}
		}
	}
	
	
	
	public ObservableList<TabellaAmministratore> caricaMezziCaserme() {
		return FXCollections.observableArrayList(mezziCaserme);
	}
	
	public ObservableList<TabellaAmministratore> caricaMezziProvincia(){
		return FXCollections.observableArrayList(mezziProvincia);
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

	
}
