package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import model.Mezzo;
import model.Report;

public class ControllerReport {
	private String idCaserma;
	private static final int BROKERPORT = 1051;
	private List<Mezzo> mezzi = null;
	
	public ControllerReport(String idCaserma,List<Mezzo> mezzi) {
		this.idCaserma=idCaserma;
		this.mezzi = new ArrayList<Mezzo>(mezzi);
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

	public List<Mezzo> getMezzi(){
		return this.mezzi;
	}
}
