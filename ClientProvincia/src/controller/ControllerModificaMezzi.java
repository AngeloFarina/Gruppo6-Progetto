package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Manutenzione;
import model.Mezzo;
import model.Modifica;
import model.TabellaAmministratore;
import model.TabellaStoricoManutenzioni;

public class ControllerModificaMezzi {
	private Mezzo m;
	private Modifica tabella;
	private static final int BROKERPORT = 1051;
	
	public ControllerModificaMezzi(Modifica tabella,Mezzo m) {
		this.tabella = tabella;
		this.m=m;
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR,"Errore modifica mezzo");
			a.showAndWait();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void init() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		Socket clientSocket = new Socket("localhost",BROKERPORT);
		System.out.println("ClientReport: creata Socket: " +clientSocket.getLocalSocketAddress());
		DataOutputStream outSock = new DataOutputStream(clientSocket.getOutputStream());
		ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
		outSock.writeUTF("localhost");
		outSock.writeUTF("localhost");
		outSock.writeUTF("modificaMezzo");
		List<Object> param = new ArrayList<Object>();
		param.add(tabella);
		param.add(m);
		outObj.writeObject(param);
		param = new ArrayList<Object>((List<Object>)inObj.readObject());
		clientSocket.close();
		String res = (String)param.get(0);
		String stringa = ( res.equals("ok") ? "Modifica effettuata" : "Errore effettuazione modifica");
		alert(res,stringa);
	}

	
	private void alert(String tipo,String stringa) {
		Alert a = null;
		if(tipo.equals("warning"))
			a = new Alert(AlertType.WARNING,stringa);
		else
			a = new Alert(AlertType.INFORMATION,stringa);
		a.showAndWait();
		
	}
}
