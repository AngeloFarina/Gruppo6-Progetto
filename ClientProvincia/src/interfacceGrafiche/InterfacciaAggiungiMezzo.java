package interfacceGrafiche;




import controller.ControllerModificaMezzi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Assegnazione;
import model.Caserma;
import model.Mezzo;
import model.Modifica;
import model.Stato;


public class InterfacciaAggiungiMezzo extends VBox {
	private Button salva = null;
	private TextField targa = null;
	private TextField tipo = null;
	private TextField anno = null;
	private TextField marca = null;
	private TextField modello = null;
	private ComboBox<Stato> stato = null;
	private ComboBox<Assegnazione> assegnazione = null;
	private ComboBox<String> caserme = null;
	private Modifica tabella;
	private Mezzo m;
	
	public InterfacciaAggiungiMezzo(Modifica tabella) {
		this.tabella = tabella;
		this.targa = new TextField();
		this.tipo = new TextField();
		this.anno = new TextField();
		this.marca = new TextField();
		this.modello = new TextField();
		this.stato = new ComboBox<Stato>();
		this.assegnazione = new ComboBox<Assegnazione>();
		caserme = new ComboBox<String>();
		salva = new Button("Aggiungi veicolo");
		salva.setOnAction(this::salva);
		initGUI();
	}


	private void initGUI() {
		this.setWidth(400);
		this.setHeight(435);
		this.getChildren().add(setTopHbox());
		this.getChildren().add(setMainVbox());
	}
	
	private Node setTopHbox() {
		HBox res = new HBox();
		Label l = new Label("AGGIUNGI MEZZO");
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
		main.setPrefHeight(400);
		this.setMargin(main, new Insets(0,30,0,30));
		HBox targaBox = new HBox();
		targaBox.getChildren().addAll(new Label("Targa:  "),this.targa);
		main.setMargin(targaBox, new Insets(20,0,0,0));
		HBox tipo = new HBox();
		tipo.getChildren().addAll(new Label("Tipologia veicolo:  "),this.tipo);
		HBox anno = new HBox();
		anno.getChildren().addAll(new Label("Anno di immatricolazione:  "),this.anno);
		HBox marca = new HBox();
		marca.getChildren().addAll(new Label("Marca veicolo:  "),this.marca);
		HBox modello = new HBox();
		modello.getChildren().addAll(new Label("Modello veicolo:  "),this.modello);
		HBox stato = new HBox();
		stato.getChildren().addAll(new Label("Stato:  "),this.stato);
		HBox assegnazione  = new HBox();
		assegnazione.getChildren().addAll(new Label("Assegnazione:  "),this.assegnazione);
		HBox caserme = new HBox();
		caserme.getChildren().addAll(new Label("Caserma attuale:  "),this.caserme);
		targaBox.setPrefHeight(40);
		tipo.setPrefHeight(40);
		anno.setPrefHeight(40);
		marca.setPrefHeight(40);
		modello.setPrefHeight(40);
		stato.setPrefHeight(40);
		assegnazione.setPrefHeight(40);
		caserme.setPrefHeight(40);
		HBox salva = new HBox();
		salva.setAlignment(Pos.CENTER);
		salva.getChildren().add(this.salva);
		salva.setPrefHeight(50);
		main.getChildren().addAll(targaBox,tipo,anno,marca,modello,stato,assegnazione,caserme,salva);
		setItems();
		return main;
	}

	
	private void setItems() {
		stato.setItems(FXCollections.observableArrayList(Stato.values()));
		assegnazione.setItems(FXCollections.observableArrayList(Assegnazione.values()));
		stato.getSelectionModel().selectFirst();
		assegnazione.getSelectionModel().selectFirst();
		ObservableList<String> idCaserme = FXCollections.observableArrayList();
		for(Caserma c : tabella.getCaserme()) {
			idCaserme.add(c.getId() + " " + c.getCitta());
		}
		caserme.setItems(idCaserme);
		caserme.getSelectionModel().selectLast();
	}


	private void salva(Event e) {
		if(checkParametri()) {
			this.m = new Mezzo(targa.getText(), tipo.getText(), Integer.parseInt(anno.getText()), marca.getText(),modello.getText(), stato.getValue(),assegnazione.getValue());
			tabella.setIdCaserma(caserme.getValue().split(" ")[0]);
			ControllerModificaMezzi mod = new ControllerModificaMezzi(new Modifica(
					tabella.getTipo(),null,tabella.getStatoMezzo(),tabella.getAssegnazioneMezzo(),
					tabella.getAnno(),tabella.getIdCaserma(),tabella.getCaserme()),m);
			Stage s = (Stage)this.getScene().getWindow();
			s.close();
		}
	}
	


	private boolean checkParametri() {
		if(targa.getText()==null || targa.getText().isEmpty() || targa.getText().length()>8)
			return false;
		if(tipo.getText() == null || tipo.getText().isEmpty() || tipo.getText().length()>25)
			return false;
		if(anno.getText() == null || anno.getText().isEmpty())
			return false;
		if(marca.getText()==null || marca.getText().isEmpty() || marca.getText().length()>50)
			return false;
		if(modello.getText()==null || modello.getText().isEmpty() || modello.getText().length()>50)
			return false;
		else {
			try {
				Integer.parseInt(anno.getText());
			}catch (NumberFormatException e){
				return false;
			}
		}
		return true;
	}
	
}
