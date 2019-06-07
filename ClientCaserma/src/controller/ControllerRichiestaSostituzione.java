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

public class ControllerRichiestaSostituzione {
	private String idCaserma;
	private String idMezzo;
	private static final int BROKERPORT = 1051;
	
	public ControllerRichiestaSostituzione(String idCaserma,String idMezzo) {
		this.idCaserma=idCaserma;
		this.idMezzo=idMezzo;
	}
	
	public String creaReport(Report r) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		r.setIdCaserma(idCaserma);
		Socket clientSocket = new Socket("localhost",BROKERPORT);
		System.out.println("ClientReport: creata Socket: " +clientSocket.getLocalSocketAddress());
		DataOutputStream outSock = new DataOutputStream(clientSocket.getOutputStream());
		ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
		outSock.writeUTF("localhost");
		outSock.writeUTF("localhost");
		outSock.writeUTF("report");
		List<Object> param = new ArrayList<Object>();
		param.add(r);
		System.out.println("Report: " + r + "\nParam: " + param);
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
}
