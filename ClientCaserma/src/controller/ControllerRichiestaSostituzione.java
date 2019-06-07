package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import model.Report;
import model.RichiestaSostituzione;
import model.Tipo;

public class ControllerRichiestaSostituzione {
	private String idCaserma;
	private String idMezzo;
	private Tipo tipo;
	private static final int BROKERPORT = 1051;
	
	public ControllerRichiestaSostituzione(String idCaserma,String idMezzo, Tipo tipo) {
		this.idCaserma=idCaserma;
		this.idMezzo=idMezzo;
		this.tipo=tipo;
	}
	
	
	
	public String richiedi(String dataOra,String desc) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		RichiestaSostituzione r = new RichiestaSostituzione(this.idCaserma,this.idMezzo,this.tipo.name(),dataOra,desc);
		Socket clientSocket = new Socket("localhost",BROKERPORT);
		System.out.println("ClientReport: creata Socket: " +clientSocket.getLocalSocketAddress());
		DataOutputStream outSock = new DataOutputStream(clientSocket.getOutputStream());
		ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
		outSock.writeUTF("localhost");
		outSock.writeUTF("localhost");
		outSock.writeUTF("richiestaSost");
		List<Object> param = new ArrayList<Object>();
		param.add(r);
		System.out.println("Richiesta: " + r + "\nParam: " + param);
		outObj.writeObject(param);
		String ruolo,nome,id;
		param = new ArrayList<Object>((List<Object>)inObj.readObject());
		System.out.println("Ritorno: " + param.get(0));
		return (String)param.get(0);
	}

	public String getCaserma() {
		return this.idCaserma;
	}
	
	public String getMezzo(){
		return this.idMezzo;
	}
	
	public Tipo getTipo() {
		return this.tipo;
	}
}
