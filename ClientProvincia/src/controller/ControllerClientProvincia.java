package controller;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Caserma;
import model.Mezzo;
import model.TabellaAmministratore;

public class ControllerClientProvincia {
	private static final int BROKERPORT = 1051;
	
	private ObservableList<TabellaAmministratore> mezziProvincia = null;
	private ObservableList<TabellaAmministratore> mezziCaserme = null;
	private String idCaserma = null;
	private String luogoCaserma = null;
	private String nome = null;
	private int totMezzi=0;
	private int man=0;
	private String carburante="0";
	
	private List<Caserma> caserme = null;
	
	
	public ControllerClientProvincia(String idCaserma,String nome) {
		this.idCaserma=idCaserma;
		this.nome=nome;
		mezziProvincia = FXCollections.observableArrayList();
		mezziCaserme = FXCollections.observableArrayList();
		init();
	}
	
	@SuppressWarnings("unchecked")
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
		outSock.writeUTF("mezziProvincia");
		List<String> param = new ArrayList<String>();
		param.add(idCaserma);
		outObj.writeObject(param);
		caserme = new ArrayList<Caserma>((List<Caserma>)inObj.readObject());
		this.caserme=new ArrayList<Caserma>(caserme);
		for(Caserma c : caserme) {
			if(c.getId().equals(this.idCaserma)) {
				this.luogoCaserma = c.getCitta();
			}
			else {
				for(Mezzo m : c.getMezzi()){
					mezziCaserme.add(new TabellaAmministratore(m.getTipo(), m.getId(), m.getStato(), m.getAssegnazione(), m.getAnno() +"",c.getId() + " " + c.getCitta(),caserme));
				}
			}
		}
		clientSocket.close();
		clientSocket = new Socket("localhost",BROKERPORT);
		System.out.println("ClientProvincia: creata Socket: " +clientSocket.getLocalSocketAddress());
		outSock = new DataOutputStream(clientSocket.getOutputStream());
		outObj = new ObjectOutputStream(clientSocket.getOutputStream());
		inObj = new ObjectInputStream(clientSocket.getInputStream());
		outSock.writeUTF("localhost");
		outSock.writeUTF("localhost");
		outSock.writeUTF("mezziCasermaProvincia");
		param = new ArrayList<String>();
		param.add(idCaserma);
		outObj.writeObject(param);
		mezzi = new ArrayList<Mezzo>((List<Mezzo>)inObj.readObject());
		clientSocket.close();
		for(Mezzo m : mezzi) {
			mezziProvincia.add(new TabellaAmministratore(m.getTipo(), m.getId(), m.getStato(), m.getAssegnazione(), m.getAnno() +"",this.idCaserma,caserme));
		}
		}catch (Exception e) {
			e.printStackTrace();
			return;
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
		if(luogoCaserma == null)
			return "CASERMA";
		return "Distaccamento Provinciale "  + luogoCaserma;
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

	
	public List<Caserma> getCaserme(){
		return new ArrayList<Caserma>(caserme);
	}
	
}
