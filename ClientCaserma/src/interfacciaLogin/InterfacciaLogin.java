package interfacciaLogin;



import java.util.ArrayList;
import java.util.List;

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

public class InterfacciaLogin extends BorderPane{
	private TextField username;
	private PasswordField password;
	private Button loginButton;
	private static final Insets PADDING = new Insets(10);
	
	
	public InterfacciaLogin() {
		username = new TextField();
		password = new PasswordField();
		loginButton = new Button("ENTRA");
		initComponenti();
		initGUI();
	}

	private void initComponenti() {
		//Ascoltatori eventi sui campi di immissione username e password
		username.setOnMousePressed(this::textUsHandler);
		password.setOnMousePressed(this::textPwHandler);
		password.setText("**********");
		username.setFont(new Font("System Italic",15));
		username.setText("Inserisci username...");
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

	private Node setLeft() {
		VBox vb = new VBox();
		vb.setPrefWidth(100);
		vb.setPrefHeight(200);
		return vb;
	}

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

	private Node setRight() {
		VBox vb = new VBox();
		vb.setPrefWidth(100);
		vb.setPrefHeight(200);
		return vb;
	}

	private Node setBottom() {
		loginButton.setAlignment(Pos.CENTER);
		loginButton.setPrefWidth(135);
		loginButton.setPrefHeight(31);
		setAlignment(loginButton, Pos.CENTER);
		setMargin(loginButton,new Insets(0,0,40,0));
		return loginButton;
	}

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
	
	//Handler dei campi username e password e del bottone
	
	private EventHandler<Event> textUsHandler(Event e){
		username.setText("");
		return null;
	}
	
	private EventHandler<Event> textPwHandler(Event e){
		password.setText("");
		return null;
	}
	
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
			List<Object> parametri = new ArrayList<Object>();
			parametri.add(username.getText());
			parametri.add(password.getText());
			RichiestaServizio r = new RichiestaServizio("sorgente","serverLogin","login",parametri);
			username.setText(r.getDestinatario());
		}
		return null;
	}
	
	
}
