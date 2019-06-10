package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.RichiestaSostituzione;

public class ControllerConcludiSostituzione {
	
	private static final int BROKERPORT = 1051;
	private RichiestaSostituzione r = null;
	private String idMezzo = null;
	
	public ControllerConcludiSostituzione (RichiestaSostituzione r, String idMezzo) throws Exception  {
		this.r=r;
		this.idMezzo=idMezzo;
		init();
		
	}
	
	@SuppressWarnings("unchecked")
	public void init() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		Socket clientSocket = new Socket("localhost",BROKERPORT);
		System.out.println("ClientProvincia: creata Socket: " +clientSocket.getLocalSocketAddress());
		DataOutputStream outSock = new DataOutputStream(clientSocket.getOutputStream());
		ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
		outSock.writeUTF("localhost");
		outSock.writeUTF("localhost");
		outSock.writeUTF("gestioneSost");
		List<Object> param = new ArrayList<Object>();
		param.add(r);
		param.add(idMezzo);
		outObj.writeObject(param);
		param = new ArrayList<Object>((List<Object>)inObj.readObject());
		clientSocket.close();
		if( ((String)param.get(0)).equals("ok")) {
			Alert a = new Alert(AlertType.INFORMATION,"Sostituzione effettuata con successo");
			a.showAndWait();
		}
		else {
			Alert a = new Alert(AlertType.WARNING,"Errore nella sostituzione");
			a.showAndWait();
		}
	}


}
