package interfacceGrafiche;


import controller.ControllerStoricoManutenzioni;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.TabellaStoricoManutenzioni;

public class InterfacciaStoricoManutenzioni extends VBox {
	
	private ControllerStoricoManutenzioni controllerStoricoManutenzioni = null;
	private TableView table = null;
	
	
	public InterfacciaStoricoManutenzioni(ControllerStoricoManutenzioni controllerStoricoManutenzioni) {
		this.controllerStoricoManutenzioni = controllerStoricoManutenzioni;
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
		ObservableList<TabellaStoricoManutenzioni> data = controllerStoricoManutenzioni.caricaStorico();
		
		table.setPrefWidth(900);
		table.setPrefHeight(400);
		TableColumn id = new TableColumn<>("ID");
		id.setMinWidth(40);
		id.setPrefWidth(50);
		id.setMaxWidth(80);
		TableColumn dataOraInizio = new TableColumn<>("DATA ORA INIZIO");
		dataOraInizio.setMinWidth(80);
		dataOraInizio.setPrefWidth(118);
		dataOraInizio.setMaxWidth(150);
		TableColumn dataOraFine = new TableColumn<>("DATA ORA FINE");
		dataOraFine.setPrefWidth(118);
		dataOraFine.setMinWidth(80);
		TableColumn targa = new TableColumn<>("TARGA");
		targa.setMinWidth(80);
		targa.setPrefWidth(100);
		targa.setMaxWidth(150);
		TableColumn tipologia = new TableColumn<>("TIPO");
		tipologia.setPrefWidth(100);
		tipologia.setMinWidth(80);
		tipologia.setMaxWidth(150);
		TableColumn descrizione = new TableColumn<>("DESCRIZIONE");
		descrizione.setPrefWidth(372);
		descrizione.setMinWidth(166);
		descrizione.setMaxWidth(500);
		
		
		
		dataOraInizio.setCellValueFactory(new PropertyValueFactory<TabellaStoricoManutenzioni,String>("dataOraInizio"));
		id.setCellValueFactory(new PropertyValueFactory<TabellaStoricoManutenzioni,String>("id"));
		targa.setCellValueFactory(new PropertyValueFactory<TabellaStoricoManutenzioni,String>("targa"));
		descrizione.setCellValueFactory(new PropertyValueFactory<TabellaStoricoManutenzioni,String>("descrizione"));
		tipologia.setCellValueFactory(new PropertyValueFactory<TabellaStoricoManutenzioni,String>("tipo"));
		dataOraFine.setCellValueFactory(new PropertyValueFactory<TabellaStoricoManutenzioni,String>("dataOraFine"));
		
		
		//Aggiunta colonne alla tabella
		table.getColumns().addAll(id,dataOraInizio,dataOraFine,targa,tipologia,descrizione);
		
		//Sortable e resizable delle colonne
		dataOraInizio.setSortable(false);
		tipologia.setSortable(false);
		targa.setSortable(false);
		id.setSortable(false);
		dataOraFine.setSortable(false);
		descrizione.setSortable(false);
		
		//Inserisco i dati alla tabella (riempie tutte le celle automaticamente
		//grazie al factory precedentemente associato
		table.setItems(data);
		
	}


	private Node setHbox() {
		HBox res = new HBox();
		res.setAlignment(Pos.CENTER);
		res.setPrefHeight(60);
		res.getChildren().add(new Label("STORICO MANUTENZIONI"));
		return res;
	}

	
}
