package interfacceGrafiche;

import java.time.LocalDate;

import controller.ControllerReport;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class InterfacciaReport extends VBox {
	
	private ControllerReport controllerReport = null;
	private Button salva = null;
	
	public InterfacciaReport(ControllerReport controllerReport) {
		this.controllerReport=controllerReport;
		salva = new Button("Salva report");
		initGUI();
	}


	private void initGUI() {
		this.setWidth(400);
		this.setHeight(500);
		this.getChildren().add(setTopHbox());
		this.getChildren().add(setMainVbox());
	}
	
	private Node setTopHbox() {
		HBox res = new HBox();
		Label l = new Label("CREAZIONE REPORT");
		l.setTextFill(Color.valueOf("#101010"));
		res.setStyle("-fx-background-color: lightgrey; -fx-border-color: darkgrey;");
		res.setAlignment(Pos.CENTER);
		res.setPrefWidth(300);
		res.setPrefHeight(35);
		res.getChildren().add(l);
		return res;
	}


	private Node setMainVbox() {
		VBox main = new VBox();
		main.setAlignment(Pos.TOP_LEFT);
		main.setPrefWidth(300);
		main.setPrefHeight(465);
		this.setMargin(main, new Insets(0,30,0,30));
		main.getChildren().addAll(firstHbox(),secondHbox(),thirdHbox(),bottomVbox());
		return main;
	}


	private Node firstHbox() {
		HBox res = new HBox();
		res.setPrefWidth(240);
		res.setPrefHeight(46);
		res.setAlignment(Pos.CENTER_LEFT);
		Label l1 = new Label("Data:  ");
		DatePicker data = new DatePicker();
		data.setPrefWidth(120);
		data.setPrefHeight(25);
		data.setValue(LocalDate.now());
		Label l2 = new Label("Ora:  ");
		l2.setPadding(new Insets(0,0,0,10));
		TextField ora = new TextField();
		ora.setPrefWidth(50);
		ora.setPrefHeight(25);
		ora.setPromptText("12:00");
		res.getChildren().addAll(l1,data,l2,ora);
		return res;
	}


	private Node secondHbox() {
		HBox res=  new HBox();
		res.setPrefWidth(240);
		res.setPrefHeight(38);
		res.setAlignment(Pos.CENTER_LEFT);
		Label l = new Label("Litri carburante consumati:  ");
		TextField litri = new TextField();
		litri.setPromptText("Litri");
		litri.setPrefWidth(50);
		litri.setPrefHeight(25);
		res.getChildren().addAll(l,litri);
		return res;
	}


	private Node thirdHbox() {
		HBox res = new HBox();
		res.setPrefWidth(240);
		res.setPrefHeight(38);
		res.setAlignment(Pos.CENTER_LEFT);
		Label l = new Label("Totale km effettuati:  ");
		TextField km = new TextField();
		km.setPromptText("Km");
		km.setPrefWidth(50);
		km.setPrefHeight(25);
		res.getChildren().addAll(l,km);
		return res;
	}


	private Node bottomVbox() {
		VBox res = new VBox();
		res.setAlignment(Pos.TOP_LEFT);
		res.setPrefWidth(240);
		res.setPrefHeight(300);
		Label l1 = new Label("Mezzi utilizzati  ");
		ListView mezzi = new ListView();
		mezzi.setPrefWidth(240);
		mezzi.setPrefHeight(150);
		Label l2 = new Label("Descrizione:  ");
		TextArea desc= new TextArea();
		desc.setPrefWidth(240);
		desc.setPrefHeight(150);
		desc.setPromptText("Scrivi qua una descrizione...");
		res.getChildren().addAll(l1,mezzi,l2,desc,setBottom());
		return res;
	}


	private Node setBottom() {
		HBox res = new HBox();
		res.setPadding(new Insets(20,0,0,0));
		res.setAlignment(Pos.BOTTOM_CENTER);
		res.setPrefWidth(240);
		res.setPrefHeight(40);
		res.getChildren().add(salva);
		return res;
	}


	
	
}
