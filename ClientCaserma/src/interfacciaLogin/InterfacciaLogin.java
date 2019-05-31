package interfacciaLogin;



import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import application.RMI_interfaceFile;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.RichiestaServizio;

public final class InterfacciaLogin extends BorderPane{
	private TextField username;
	private PasswordField password;
	private Button loginButton;
	private static Insets PADDING = new Insets(10);
	private static int REGISTRYPORT = 1099;
	private static String registryHost = "localhost";
	private static String serviceName = "Broker";
	
	
	public InterfacciaLogin() {
		username = new TextField();
		password = new PasswordField();
		loginButton = new Button("ENTRA");
		initComponenti();
		initGUI();
	}

	private void initComponenti() {
		password.setFont(new Font("System Italic",15));
		password.setPromptText("**********");
		username.setFont(new Font("System Italic",15));
		username.setPromptText("Inserisci username...");
		//Ascoltatore evento sul bottone
		loginButton.setOnMousePressed(this::buttonHandler);
	}

	private void initGUI() {
		//Dimensioni BorderPane principale
		setMargin(this,new Insets(0,0,40,0));
		setPrefWidth(601);
		setPrefHeight(330);
		setPadding(PADDING);
		//Inizializza le 5 parti del BorderPane con i componenti adatti
		setCenter(setCenter());
		setBottom(setBottom());
		setRight(setRight());
		setTop(setTop());
		setLeft(setLeft());
	}

	//Inizializzo un Vertical Box vuoto nella sezione sinistra del BorderPane, per ottimizzare la visione dei componenti
	private Node setLeft() {
		VBox vb = new VBox();
		vb.setPrefWidth(100);
		vb.setPrefHeight(200);
		return vb;
	}

	//Creo un Horizontal Box che conterrà un'etichetta con scritto "LOGIN" e metto il colore di sfondo a grigio chiaro
	private Node setTop() {
		HBox hb = new HBox();
		hb.setPrefWidth(hb.getMaxWidth());
		hb.setStyle("-fx-background-color: lightgrey;");
		hb.setAlignment(Pos.CENTER);
		hb.setPrefWidth(601);
		hb.setPrefHeight(56);
		Label login = new Label("LOGIN");
		login.setPrefWidth(55);
		login.setPrefHeight(27);
		login.setStyle("-fx-font-size: 18px;");
		hb.getChildren().add(login);
		return hb;
	}

	//Inizializzo un Vertical Box vuoto nella sezione destra del BorderPane, per ottimizzare la visione dei componenti
	//Analogamente a setLeft()
	private Node setRight() {
		VBox vb = new VBox();
		vb.setPrefWidth(100);
		vb.setPrefHeight(200);
		return vb;
	}

	//Gestisco il bottone per entrare nell'applicativo e lo posiziono al centro della sezione in basso del BorderPane
	private Node setBottom() {
		loginButton.setAlignment(Pos.CENTER);
		loginButton.setPrefWidth(135);
		loginButton.setPrefHeight(31);
		setAlignment(loginButton, Pos.CENTER);
		setMargin(loginButton,new Insets(0,0,40,0));
		return loginButton;
	}

	//Gestisco la parte centrale del BorderPane
	//Il ragionamento è il seguente:
	//Ho un Vertical Box principale a cui accorpo due Horizontal Box, uno sopra e uno sotto
	//Ciascuno di questi due HBox contiene, rispettivamente, il campo di immissione dell'username
	//e quello di immissione della password (Che sarà ovviamente un PasswordField)
	//Entrambi i campi sono preceduti da un TextField che ne spiega la semantica (Username/Password)
	private Node setCenter() {
		VBox v = new VBox();
		v.setPrefWidth(401);
		v.setPrefHeight(182);
		v.setAlignment(Pos.CENTER);
		v.setPadding(new Insets(20,10,10,10));
		setMargin(v, new Insets(0,0,20,0));
		HBox hb1 = new HBox();
		hb1.setPadding(new Insets(10,10,4,10));
		TextField us = new TextField("Username");
		us.setPrefWidth(186);
		us.setPrefHeight(31);
		us.setEditable(false);
		us.setStyle("-fx-background-color: lightgrey;");
		us.setAlignment(Pos.CENTER);
		username.setPrefWidth(287);
		username.setPrefHeight(31);
		HBox hb2 = new HBox();
		hb2.setPadding(PADDING);
		TextField pw = new TextField("Password");
		pw.setPrefWidth(190);
		pw.setPrefHeight(31);
		pw.setEditable(false);
		pw.setStyle("-fx-background-color: lightgrey;");
		pw.setAlignment(Pos.CENTER);
		password.setPrefWidth(291);
		password.setPrefHeight(31);
		hb2.getChildren().addAll(pw,password);
		hb1.getChildren().addAll(us,username);
		v.getChildren().addAll(hb1,hb2);
		return v;
	}
	
	
	//Handler dell'evento di Click sul bottone
	//Questo handler deve catturare il testo presente nei due campi Username/Password 
	//Deve poi generare una richiestadi servizio login, spedendo come parametri Username e Password catturati.
	//Al momento della ricezione della risposta, dovrà chiudersi se tutto sarà andato a buon fine e lasciare spazio ad un'altra Home
	//Corrispondente all'utente che si è autenticato
	private EventHandler<Event> buttonHandler(Event e){
		if(username.getText()==null || username.getText().isEmpty()) {
			Alert a  = new Alert(AlertType.WARNING,"Inserire un username");
			a.show();
		}
		else if(password.getText()==null || password.getText().isEmpty()) {
			Alert a = new Alert(AlertType.WARNING,"Inserire password");
			a.show();
		}
		else {
			generaEInviaRichiesta();
		}
		return null;
	}

	private void generaEInviaRichiesta() {
		List<Object> parametri = new ArrayList<Object>();
		parametri.add(username.getText());
		parametri.add(password.getText());
		RichiestaServizio r = new RichiestaServizio("interfacciaLogin","serverLogin","login",parametri);
		try{
			String completeName = "//" + registryHost + ":" + REGISTRYPORT + "/"
					+ serviceName;
			RMI_interfaceFile broker = (RMI_interfaceFile) Naming.lookup(completeName);
			r = broker.richiedi(r);
			if(r!=null)
				this.setVisible(false);
		}
		catch(Exception e){
			System.err.println("ClientInterfacciaLogin: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
}
