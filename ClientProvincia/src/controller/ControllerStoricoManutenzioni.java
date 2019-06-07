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
import model.RichiestaSostituzione;
import model.TabellaSostituzioni;
import model.TabellaStoricoManutenzioni;

public class ControllerStoricoManutenzioni {
	private String idProvincia;
	private static final int BROKERPORT = 1051;
	private ObservableList<TabellaStoricoManutenzioni> storico = null;
	
	public ControllerStoricoManutenzioni(String idProvincia) {
		this.idProvincia = idProvincia;
		storico = FXCollections.observableArrayList();
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR,"Errore caricamento storico manutenzioni");
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
		outSock.writeUTF("storicoManutenzioni");
		List<Object> param = new ArrayList<Object>();
		param.add(idProvincia);
		outObj.writeObject(param);
		String ruolo,nome,id;
		List<Manutenzione> manutenzioni= new ArrayList<Manutenzione>((List<Manutenzione>)inObj.readObject());
		for(Manutenzione m : manutenzioni) {
			this.storico.add(new TabellaStoricoManutenzioni(m.getMezzo().getTipo(),m.getMezzo().getId(),m.getId(),m.getDataOraInizio(),m.getDataOraFine(),m.getDescrizione()));
		}
		
	}
	
	public ObservableList<TabellaStoricoManutenzioni> caricaStorico(){
		return FXCollections.observableArrayList(this.storico);
	}

}
