package interfacceGrafiche;




import java.util.ArrayList;
import java.util.List;

import controller.ControllerConcludiSostituzione;
import controller.ControllerModificaMezzi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Assegnazione;
import model.Caserma;
import model.Mezzo;
import model.Modifica;
import model.RichiestaSostituzione;
import model.Stato;


public class InterfacciaConcludiSostituzione extends VBox {
	private Button salva = null;
	private TextArea desc= null;
	private ComboBox<String> idMezzo = null;
	private RichiestaSostituzione r = null;
	private List<String> mezzi = null;
	
	public InterfacciaConcludiSostituzione(RichiestaSostituzione r,List<String> mezzi) {
		this.r=r;
		this.idMezzo = new ComboBox<String>();
		this.mezzi=new ArrayList<String>(mezzi);
		salva = new Button("  Invia mezzo sostitutivo e\ncompleta la richiesta  ");
		salva.setOnAction(this::salva);
		initGUI();
	}


	private void initGUI() {
		this.setWidth(400);
		this.setHeight(300);
		this.getChildren().add(setTopHbox());
		this.getChildren().add(setMainVbox());
	}
	
	private Node setTopHbox() {
		HBox res = new HBox();
		Label l = new Label("GESTIONE RICHIESTA SOSTITUZIONE MEZZO " +r.getIdMezzo() + " CASERMA " + r.getIdCaserma());
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
		main.setPadding(new Insets(10));
		main.setAlignment(Pos.TOP_LEFT);
		main.setPrefWidth(400);
		main.setPrefHeight(265);
		HBox sel = new HBox();
		sel.setPrefHeight(50);
		sel.getChildren().addAll(new Label("Seleziona mezzo da inviare in sostituzione:  "),idMezzo);
		Label l = new Label("Descrizione:");
		desc = new TextArea();
		desc.setWrapText(true);
		desc.setPromptText("Inserisci una descrizione...");
		HBox salva = new HBox();
		salva.setAlignment(Pos.CENTER);
		salva.getChildren().add(this.salva);
		setItems();
		main.getChildren().addAll(sel,l,desc,salva);
		return main;
	}
	
	private void setItems() {
		this.idMezzo.setItems(FXCollections.observableArrayList(mezzi));
		this.idMezzo.getSelectionModel().selectFirst();
	}

	
	private void salva(Event e) {
		try{
			new ControllerConcludiSostituzione(r,idMezzo.getValue());
		}catch(Exception e1) {
			e1.printStackTrace();
			Alert a = new Alert(AlertType.WARNING,"Errore gestione sostituzione");
			a.showAndWait();
		}
		Stage s = (Stage)getScene().getWindow();
		s.close();
	}
	
}
