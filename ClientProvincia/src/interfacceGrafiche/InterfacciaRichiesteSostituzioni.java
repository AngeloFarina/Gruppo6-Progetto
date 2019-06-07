package interfacceGrafiche;


import controller.ControllerRichiesteSostituzioni;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.TabellaSostituzioni;

public class InterfacciaRichiesteSostituzioni extends VBox {
	
	private ControllerRichiesteSostituzioni controllerSostituzioni = null;
	private TableView table = null;
	
	
	public InterfacciaRichiesteSostituzioni(ControllerRichiesteSostituzioni controllerSostituzioni) {
		this.controllerSostituzioni = controllerSostituzioni;
		initGUI();
	}


	private void initGUI() {
		table = new TableView();
		setTable();
		setMargin(table,new Insets(0,20,0,20));
		this.getChildren().addAll(setHbox(),table);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setTable() {
		ObservableList<TabellaSostituzioni> data = controllerSostituzioni.caricaRichieste();
		
		table.setPrefWidth(900);
		table.setPrefHeight(400);
		TableColumn dataOra = new TableColumn<>("DATA E ORA");
		dataOra.setMinWidth(50);
		dataOra.setPrefWidth(150);
		dataOra.setMaxWidth(220);
		TableColumn caserma = new TableColumn<>("CASERMA");
		caserma.setMinWidth(50);
		caserma.setPrefWidth(118);
		caserma.setMaxWidth(150);
		TableColumn targa = new TableColumn<>("TARGA");
		targa.setMinWidth(100);
		targa.setPrefWidth(140);
		targa.setMaxWidth(200);
		TableColumn tipologia = new TableColumn<>("TIPOLOGIA");
		tipologia.setPrefWidth(140);
		tipologia.setMinWidth(100);
		tipologia.setMaxWidth(250);
		TableColumn descrizione = new TableColumn<>("DESCRIZIONE");
		descrizione.setPrefWidth(166);
		descrizione.setMinWidth(200);
		descrizione.setMaxWidth(500);
		TableColumn azioni = new TableColumn<>("AZIONI");
		azioni.setPrefWidth(110);
		
		
		dataOra.setCellValueFactory(new PropertyValueFactory<TabellaSostituzioni,String>("dataOra"));
		caserma.setCellValueFactory(new PropertyValueFactory<TabellaSostituzioni,String>("idCaserma"));
		targa.setCellValueFactory(new PropertyValueFactory<TabellaSostituzioni,String>("targa"));
		descrizione.setCellValueFactory(new PropertyValueFactory<TabellaSostituzioni,String>("descrizione"));
		tipologia.setCellValueFactory(new PropertyValueFactory<TabellaSostituzioni,String>("tipo"));
		azioni.setCellValueFactory(new PropertyValueFactory<TabellaSostituzioni,Button>("concludi"));
		
		
		//Aggiunta colonne alla tabella
		table.getColumns().addAll(dataOra,caserma,targa,tipologia,descrizione,azioni);
		
		//Sortable e resizable delle colonne
		dataOra.setSortable(false);
		tipologia.setSortable(false);
		targa.setSortable(false);
		caserma.setSortable(false);
		azioni.setResizable(false);
		azioni.setSortable(false);
		descrizione.setSortable(false);
		
		//Inserisco i dati alla tabella (riempie tutte le celle automaticamente
		//grazie al factory precedentemente associato
		table.setItems(data);
		
	}


	private Node setHbox() {
		HBox res = new HBox();
		res.setAlignment(Pos.CENTER);
		res.setPrefHeight(60);
		res.getChildren().add(new Label("LISTA RICHIESTE DI SOSTITUZIONE"));
		return res;
	}

	
}
