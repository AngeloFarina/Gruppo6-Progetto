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
import model.Mezzo;
import model.Report;
import model.RichiestaSostituzione;
import model.TabellaSostituzioni;

public class ControllerRichiesteSostituzioni {
	private String idProvincia;
	private static final int BROKERPORT = 1051;
	private ObservableList<TabellaSostituzioni> richieste = null;
	public ControllerRichiesteSostituzioni(String idProvincia) {
		this.idProvincia = idProvincia;
		richieste = FXCollections.observableArrayList();
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR,"Errore controllo richieste di sostituzione");
			a.showAndWait();
		}
	}
	
	public void init() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		Socket clientSocket = new Socket("localhost",BROKERPORT);
		System.out.println("ClientReport: creata Socket: " +clientSocket.getLocalSocketAddress());
		DataOutputStream outSock = new DataOutputStream(clientSocket.getOutputStream());
		ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
		outSock.writeUTF("localhost");
		outSock.writeUTF("localhost");
		outSock.writeUTF("caricaSost");
		List<Object> param = new ArrayList<Object>();
		param.add(idProvincia);
		outObj.writeObject(param);
		String ruolo,nome,id;
		List<RichiestaSostituzione> richieste = new ArrayList<RichiestaSostituzione>((List<RichiestaSostituzione>)inObj.readObject());
		for(RichiestaSostituzione r : richieste) {
			this.richieste.add(new TabellaSostituzioni(r.getTipo(),r.getIdMezzo(),r.getIdCaserma(),r.getDataOra(),r.getDescrizione()));
		}
	}
	
	public ObservableList<TabellaSostituzioni> caricaRichieste(){
		return FXCollections.observableArrayList();
	}

}
