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


import interfacceGrafiche.InterfacciaCapoSquadra;
import interfacceGrafiche.InterfacciaVigile;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerLoginCaserma {
	private static final int BROKERPORT = 1051;

	public ControllerLoginCaserma() {
		
	}
	
	@SuppressWarnings("unchecked")
	public void richiestaLogin(String username,String password, Window window) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		Socket clientSocket = new Socket("localhost",BROKERPORT);
		System.out.println("ClientLogin: creata Socket: " +clientSocket.getLocalSocketAddress());
		DataOutputStream outSock = new DataOutputStream(clientSocket.getOutputStream());
		ObjectOutputStream outObj = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
		outSock.writeUTF("localhost");
		outSock.writeUTF("localhost");
		outSock.writeUTF("login");
		List<String> param = new ArrayList<String>();
		param.add(username);
		param.add(password);
		outObj.writeObject(param);
		String ruolo,nome,id;
		param = new ArrayList<String>((List<String>)inObj.readObject());
		clientSocket.close();
		if(param==null || param.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING,"Utente o password errati");
			alert.showAndWait();
		}
		else {
			ruolo = param.get(2);
			nome = param.get(3);
			id = param.get(4);
			System.out.println("Parametri ricevuti " + ruolo + " " + nome + " " + id);
			if(ruolo.equals("caposquadra"))
				setNewStageCapoSquadra(window,nome,id);
			else if(ruolo.equals("vigile"))
				setNewStageVigile(window,nome,id);
			else {
				Alert alert = new Alert(AlertType.WARNING,"Utente o password errati");
				alert.showAndWait();
			}
		}
	}

	private void setNewStageVigile(Window window,String nome, String id) {
		Stage s = (Stage) window;
		s.close();
		s.setTitle("Gestione Mezzi Vigili del Fuoco");
		InterfacciaVigile root = null;
		try {
			root = new InterfacciaVigile(new ControllerClientCaserma(id,nome));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setScene(s,root);
	}

	private void setNewStageCapoSquadra(Window window, String nome,String id) {
		Stage s = (Stage) window;
		s.close();
		s.setTitle("Gestione Mezzi Vigili del Fuoco");
		InterfacciaCapoSquadra root = null;
		try {
			root = new InterfacciaCapoSquadra(new ControllerClientCaserma(id,nome));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setScene(s, root);
		
	}

	private void setScene(Stage s, Pane root) {
		root.setPrefSize(1280, 720);
		Scene scene = new Scene(root, 1280,720,Color.WHITE);
		s.setScene(scene);
		s.setResizable(false);
		s.setOnCloseRequest((e) -> {
			Platform.exit();
			System.exit(0);
		});
		s.show();
	}
	
}
