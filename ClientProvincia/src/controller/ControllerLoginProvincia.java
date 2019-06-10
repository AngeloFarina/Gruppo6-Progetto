package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import interfacceGrafiche.InterfacciaAmministratore;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerLoginProvincia {
	private static final int BROKERPORT = 1051;

	public ControllerLoginProvincia() {
		
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
			Alert a  = new Alert(AlertType.WARNING,"Utente o password errati");
			a.showAndWait();
		}
		else {
			ruolo = param.get(2);
			nome = param.get(3);
			id = param.get(4);
			System.out.println("Parametri ricevuti " + ruolo + " " + nome + " " + id);
			if(ruolo.equals("amministratore"))
				setNewStageAmministratore(window,nome,id);
			else {
				Alert alert = new Alert(AlertType.WARNING,"Utente o password errati");
				alert.showAndWait();
			}
		}
	}


	private void setNewStageAmministratore(Window window, String nome,String id) {
		Stage s = (Stage) window;
		s.close();
		s.setTitle("Gestione Mezzi Vigili del Fuoco");
		InterfacciaAmministratore root = null;
		root = new InterfacciaAmministratore(new ControllerClientProvincia(id,nome));
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
