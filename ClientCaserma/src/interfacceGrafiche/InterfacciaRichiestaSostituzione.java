package interfacceGrafiche;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controller.ControllerRichiestaSostituzione;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InterfacciaRichiestaSostituzione extends VBox {
	
	private ControllerRichiestaSostituzione controllerSostituzione = null;
	private Button invia = null;
	private TextArea desc = null;
	
	
	public InterfacciaRichiestaSostituzione(ControllerRichiestaSostituzione controllerSostituzione) {
		this.controllerSostituzione=controllerSostituzione;
		initGUI();
	}


	private void initGUI() {
		this.setWidth(400);
		this.setHeight(400);
		this.getChildren().add(setTopHbox());
		this.getChildren().add(setMainVbox());
	}
	
	private Node setTopHbox() {
		HBox res = new HBox();
		Label l = new Label("RICHIESTA DI SOSTITUZIONE");
		l.setTextFill(Color.valueOf("#101010"));
		res.setStyle("-fx-background-color: lightgrey; -fx-border-color: darkgrey;");
		res.setAlignment(Pos.CENTER);
		res.setPrefWidth(400);
		res.setPrefHeight(60);
		res.getChildren().add(l);
		return res;
	}


	@SuppressWarnings("static-access")
	private Node setMainVbox() {
		VBox main = new VBox();
		main.setAlignment(Pos.TOP_LEFT);
		main.setPrefWidth(400);
		main.setPrefHeight(330);
		this.setMargin(main, new Insets(20));
		Label dataOra = new Label("Data e ora: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
		Label targa = new Label("Targa veicolo da sostituire: " + controllerSostituzione.getMezzo());
		Label desc = new Label("Descrizione:");
		this.desc = new TextArea();
		this.desc.setPromptText("Scrivi qui un'eventuale descrizione...");
		HBox invia = new HBox();
		this.invia = new Button("  Invia richiesta  ");
		this.invia.setOnAction(this::handle);
		invia.setAlignment(Pos.CENTER);
		invia.getChildren().add(this.invia);
		dataOra.setPrefHeight(40);
		targa.setPrefHeight(40);
		desc.setPrefHeight(40);
		this.desc.setPrefHeight(150);
		this.desc.setWrapText(true);
		invia.setPrefHeight(70);
		main.getChildren().addAll(dataOra,targa,desc,this.desc,invia);
		return main;
	}
	
	

	private void alert(String p) {
		Alert alert = new Alert(AlertType.ERROR,p);
		alert.showAndWait();
	}
	
	//String idCaserma, String idMezzo,String tipo, String dataOra, String descrizione
	private void handle(Event e) {
		try {
			if(controllerSostituzione.richiedi(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),desc.getText()).equals("ok")) {
				Alert a = new Alert(AlertType.INFORMATION,"Richiesta effettuata con successo");
				a.showAndWait();
				Stage s = (Stage)this.getScene().getWindow();
				s.close();
			}
			else
				alert("Errore richiesta sostituzione");
		} catch (Exception e1) {
			e1.printStackTrace();
			alert("Errore richiesta sostituzione");
		}
	}

	
}
