package interfacceGrafiche;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controller.ControllerRichiestaSostituzione;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class InterfacciaRichiestaSostituzione extends VBox {
	
	private ControllerRichiestaSostituzione controllerSostituzione = null;
	private Button invia = null;
	private TextField desc = null;
	
	
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
		res.setPrefHeight(40);
		res.getChildren().add(l);
		return res;
	}


	@SuppressWarnings("static-access")
	private Node setMainVbox() {
		VBox main = new VBox();
		main.setAlignment(Pos.TOP_LEFT);
		main.setPrefWidth(400);
		main.setPrefHeight(360);
		this.setMargin(main, new Insets(20));
		Label dataOra = new Label("Data e ora: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Label targa = new Label("Targa veicolo da sostituire: " + controllerSostituzione.getMezzo());
		Label desc = new Label("Descrizione:");
		this.desc = new TextField();
		this.desc.setPromptText("Scrivi qui un'eventuale descrizione...");
		HBox invia = new HBox();
		this.invia = new Button("  Invia richiesta  ");
		invia.setAlignment(Pos.CENTER);
		invia.getChildren().add(this.invia);
		main.getChildren().addAll(dataOra,targa,desc,this.desc,invia);
		return main;
	}
	
	

	private void alert(String p) {
		Alert alert = new Alert(AlertType.ERROR,p);
		alert.showAndWait();
	}

	
}
