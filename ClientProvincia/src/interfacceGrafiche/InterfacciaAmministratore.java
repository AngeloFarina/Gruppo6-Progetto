package interfacceGrafiche;




import controller.ControllerClientProvincia;
import controller.ControllerRichiesteSostituzioni;
import controller.ControllerStoricoManutenzioni;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.TabellaAmministratore;

public class InterfacciaAmministratore extends BorderPane{
	@SuppressWarnings("rawtypes")
	private TableView tableProvincia = null;
	@SuppressWarnings("rawtypes")
	private TableView tableCaserme = null;
	private ImageView auto = null;
	private ImageView posizione = null;
	private ImageView account = null;
	private ImageView tickVerde = null;
	private ImageView tickRossa = null;
	private ImageView tickBlu = null;
	//private ImageView tickGrigia = null;
	private ImageView tickGialla = null;
	private Button crearichiesteSostituzioni = null;
	private Button storicoManutenzioni = null;
	private Button modificaCaserma = null;
	private Button modificaProvincia = null;
	private ImageView richiesteSostituzioni = null;
	private ImageView manutenzioni= null;
	private ImageView impostazioni = null;
	
	private Label caserma = null;
	private Label nome = null;
	
	private ControllerClientProvincia controller = null;
	
	public InterfacciaAmministratore(ControllerClientProvincia controller) {
		this.controller=controller;
		refresh();
		initGUI();
	}

	@SuppressWarnings("unchecked")
	private void refresh() {
		  new Thread(() -> {
		    while(true) {
		       try {
		          Thread.sleep(20000); // Wait for 20 secs before updating items
		       } catch (InterruptedException e) {
		          e.printStackTrace();
		       }
		       Platform.runLater(() -> {
		    	   		controller = new ControllerClientProvincia(controller.getIdCaserma(),controller.getNome());
						initGUI();
		    	   });// Update on JavaFX Application Thread
		    	}
		  	}).start();
	}
	
	

	private void initGUI() {
		nome = new Label(controller.getNome());
		caserma = new Label(controller.getCaserma());
		
		//Imposto le dimensioni del pane
		this.setWidth(1280);
		this.setHeight(720);
		
		//Inizializzo tutte le componenti grafiche
		initComponents();
		
		//Imposto al centro, sinistra e alto del borderPane
		setCenter(center());
		setLeft(left());
		setTop(top());
	}

	@SuppressWarnings("rawtypes")
	private void initComponents() {
		tableProvincia = new TableView<>();
		tableCaserme = new TableView<>();
		
		//Inizializzazione di tutte le icone/immagini
		impostazioni = new ImageView(new Image("./icone/Impostazioni.png"));
		modificaCaserma = new Button("Modifica");
		modificaProvincia=new Button("Modifica");
		richiesteSostituzioni = new ImageView(new Image("./icone/DoppieFrecce.png"));
		manutenzioni = new ImageView(new Image("./icone/ChiaveIngleseOfficina.png"));
		storicoManutenzioni = new Button("",manutenzioni);
		storicoManutenzioni.setOnAction(this::storicoManutenzioniHandler);
		crearichiesteSostituzioni = new Button("",richiesteSostituzioni);
		crearichiesteSostituzioni.setOnAction(this::richiesteSostituzioniHandler);
		richiesteSostituzioni.setPickOnBounds(true);
		tickVerde = new ImageView(new Image("./icone/TickVerde.png"));
		//tickGrigia = new ImageView(new Image("./icone/TickGrigia.png"));
		tickRossa = new ImageView(new Image("./icone/TickRossa.png"));
		tickBlu = new ImageView(new Image("./icone/TickBlu.png"));
		tickGialla = new ImageView(new Image("./icone/TickGialla.png"));
		account = new ImageView(new Image("./icone/utente.png"));
		posizione = new ImageView(new Image("./icone/Posizione.png"));
		auto = new ImageView(new Image("./icone/Auto.png"));
	}

	@SuppressWarnings("static-access")
	private Node top() {
		//Hbox principale
		HBox main = new HBox();
		
		//Allineamento, dimensioni e stile dell'hbox principale
		main.setAlignment(Pos.TOP_LEFT);
		main.setStyle("-fx-border-color:lightgrey");
		main.setPrefWidth(700);
		main.setPrefHeight(68);
		
		//Imposto le dimensioni e lo stile delle icone e dei label
		impostazioni.setSmooth(true);
		impostazioni.setFitWidth(60);
		impostazioni.setFitHeight(76);
		posizione.setSmooth(true);
		posizione.setFitWidth(67);
		posizione.setFitHeight(64);
		posizione.setStyle("-fx-margin-left: 5; -fx-margin-bottom: 5; -fx-margin-left:10;");
		caserma.setFont(Font.font(22));
		caserma.setStyle("-fx-margin-top: 20;");
		caserma.setPrefWidth(920);
		caserma.setPrefHeight(45);
		caserma.setAlignment(Pos.CENTER_LEFT);
		caserma.setContentDisplay(ContentDisplay.LEFT);
		caserma.setGraphicTextGap(4);
		
		//Creo il vbox che conterrà il ruolo e nome dell'utente
		VBox v = new VBox();
		
		//Imposto le dimensioni del vbox
		v.setStyle("-fx-margin-top : 10");
		v.setPrefWidth(450);
		v.setPrefHeight(200);
		v.setSpacing(7);
		v.setAlignment(Pos.BASELINE_RIGHT);
		
		//Imposto font e allineamento del label del nome dell'utente
		nome.setFont(Font.font(20));
		nome.setTextAlignment(TextAlignment.RIGHT);
		
		//Creo e imposto il label del ruolo
		Label ruolo = new Label("Amministratore");
		ruolo.setAlignment(Pos.CENTER_RIGHT);
		
		//Aggiungo nome e ruolo al vbox
		v.getChildren().addAll(nome,ruolo);
		
		//Imposto le dimensioni dell'immagine dell'account
		account.setSmooth(true);
		account.setFitHeight(60);
		account.setFitWidth(65);
		
		//Aggiungo all'hbox principale tutte le icone e label
		main.getChildren().addAll(impostazioni,posizione,caserma,v,account);
		
		//Imposto i margini e ottimizzo l'impatto visivo
		main.setMargin(caserma, new Insets(15,0,0,0));
		main.setMargin(v, new Insets(10,0,0,0));
		main.setMargin(account, new Insets(6,0,0,20));
		return main;
	}

	@SuppressWarnings("static-access")
	private Node left() {
		//Creo il vbox principale
		VBox root = new VBox();
		
		//Imposto le dimensioni e stili del vbox principale
		root.setStyle("-fx-background-color: lightgrey");
		root.setPrefHeight(642);
		root.setPrefWidth(61);
		
		//Imposto il margine/spazio per le immagini di auto e richiesteSostituzioni
		root.setMargin(auto, new Insets(30,0,0,10));
		root.setMargin(richiesteSostituzioni, new Insets(30,0,0,10));
		root.setSpacing(30);
		
		//Dimensiono le icone auto e richiesteSostituzioni
		auto.setSmooth(true);
		auto.setFitHeight(40);
		auto.setFitWidth(40);
		manutenzioni.setSmooth(true);
		manutenzioni.setFitWidth(45);
		manutenzioni.setFitHeight(40);
		richiesteSostituzioni.setSmooth(true);
		richiesteSostituzioni.setFitWidth(45);
		richiesteSostituzioni.setFitHeight(40);
		crearichiesteSostituzioni.setPrefWidth(45);
		crearichiesteSostituzioni.setPrefHeight(40);
		crearichiesteSostituzioni.setTooltip(new Tooltip("Clicca per visualizzare le richieste di sostituzione"));
		storicoManutenzioni.setPrefWidth(45);
		storicoManutenzioni.setPrefHeight(40);
		storicoManutenzioni.setTooltip(new Tooltip("Clicca per visualizzare lo storico delle manutenzioni"));
		
		//Aggiungo al vbox principale le due icone
		root.getChildren().addAll(auto,crearichiesteSostituzioni,storicoManutenzioni);
		return root;
	}

	@SuppressWarnings("static-access")
	private Node center() {
		//Creo il vbox principale
		VBox root = new VBox();
		
		//hbox dove sta il label dei mezzi caserma provinciale
		HBox top = setTopLabel();
		
		HBox center = setCenterLabel();
		
		//Imposto tutta la tabella dei mezzi della caserma provinciale
		setTableViewProvincia();
		//Mezzi tutta la provincia
		setTableViewCaserme();
		
		//Creo l'hbox della legenda di stato/assegnazione
		HBox bottom = setBottom();
		
		//Imposto il margine per la tabella, per ottimizzare l'impatto visivo
		root.setMargin(tableProvincia, new Insets(0,65,0,100));
		root.setMargin(tableCaserme,new Insets(0,65,0,100));
		root.setMargin(top, new Insets(10,0,10,0));
		root.setMargin(center, new Insets(10,0,10,0));
		//Aggiungo la sezione in alto, la tabella e la legenda al vbox principale
		root.getChildren().addAll(top,tableProvincia,center,tableCaserme,bottom);
		
		//Imposto il margine per la legenda per ottimizzare l'impatto visivo
		root.setMargin(bottom, new Insets(0,0,0,100));
		return root;
	}

	

	private HBox setTopLabel() {
		HBox res = new HBox();
		res.setPrefWidth(1219);
		res.setPrefHeight(44);
		HBox first = new HBox();
		Label l1 = new Label("LISTA MEZZI DISTACCAMENTO PROVINCIALE");
		l1.setPrefWidth(290);
		l1.setPrefHeight(17);
		first.setPrefWidth(389);
		first.setPrefHeight(44);
		first.getChildren().add(l1);
		HBox sec = new HBox();
		sec.setPrefWidth(764);
		sec.setPrefHeight(44);
		sec.setAlignment(Pos.CENTER_RIGHT);
		first.setAlignment(Pos.CENTER_RIGHT);
		sec.getChildren().add(modificaCaserma);
		res.getChildren().addAll(first,sec);
		return res;
	}
	
	private HBox setCenterLabel() {
		HBox res = new HBox();
		HBox first = new HBox();
		res.setPrefWidth(1219);
		res.setPrefHeight(44);
		Label l1 = new Label("LISTA MEZZI CASERME IN PROVINCIA");
		l1.setPrefWidth(290);
		l1.setPrefHeight(17);
		first.setPrefWidth(389);
		first.setPrefHeight(44);
		first.getChildren().add(l1);
		HBox sec = new HBox();
		sec.setPrefWidth(764);
		sec.setPrefHeight(44);
		sec.setAlignment(Pos.CENTER_RIGHT);
		first.setAlignment(Pos.CENTER_RIGHT);
		sec.getChildren().add(modificaProvincia);
		res.getChildren().addAll(first,sec);
		return res;
	}

	@SuppressWarnings("static-access")
	private HBox setBottom() {
		//Creo hox principale per la legenda
		HBox res = new HBox();
		
		//Creo i label relativi alla legenda
		Label disp = new Label("DISPONIBILE");
		Label man = new Label("IN MANUTENZIONE");
		Label proprio = new Label("MEZZO PROPRIO");
		Label sost = new Label("MEZZO SOSTITUTIVO");
		
		//Dimensiono le icone dei tick
		tickVerde.setSmooth(true);
		tickVerde.setFitWidth(27);
		tickVerde.setFitHeight(25);
		tickRossa.setSmooth(true);
		tickRossa.setFitWidth(27);
		tickRossa.setFitHeight(25);
		tickBlu.setSmooth(true);
		tickBlu.setFitWidth(27);
		tickBlu.setFitHeight(25);
		tickGialla.setSmooth(true);
		tickGialla.setFitWidth(27);
		tickGialla.setFitHeight(25);
		
		//Imposto il padding equivalente a paddingTop=10 e paddingLeft=5
		disp.setPadding(new Insets(10,0,0,0));
		proprio.setPadding(new Insets(0,0,0,5));
		
		//Imposto il margine per ottimizzare l'impatto visivo della legenda
		res.setMargin(man, new Insets(10,0,0,0));
		res.setMargin(proprio, new Insets(10,0,0,0));
		res.setMargin(sost, new Insets(10,0,0,5));
		res.setMargin(tickRossa, new Insets(5,0,0,0));
		res.setMargin(tickVerde, new Insets(5,0,0,0));
		res.setMargin(tickBlu, new Insets(5,0,0,0));
		res.setMargin(tickGialla, new Insets(5,0,0,5));
		
		//Aggiungo gli elementi della legenda all'hbox principale
		res.getChildren().addAll(tickVerde,disp,tickRossa,man,tickBlu,proprio,tickGialla,sost);
		return res;
	}

	
	private void setTableViewCaserme() {
		//Dimensioni tabella
		tableCaserme.setPrefWidth(1054);
		tableCaserme.setPrefHeight(300);
		
		//Creazione lista osservabile associata ai mezzi della caserma in cui mi sono autenticato
		ObservableList<TabellaAmministratore> data = controller.caricaMezziCaserme();
		
		//Metto il nome giusto nel Label della caserma
		caserma.setText(controller.getCaserma());
		
		//Creazione e dimensionamento colonne
		TableColumn icona = new TableColumn<>();
		icona.setMinWidth(10);
		icona.setPrefWidth(39);
		TableColumn tipologia = new TableColumn<>("TIPOLOGIA");
		tipologia.setMinWidth(130);
		tipologia.setPrefWidth(230);
		tipologia.setMaxWidth(400);
		TableColumn targa = new TableColumn<>("TARGA");
		targa.setMinWidth(100);
		targa.setPrefWidth(150);
		targa.setMaxWidth(150);
		TableColumn stato = new TableColumn<>("STATO");
		stato.setPrefWidth(100);
		stato.setMinWidth(40);
		TableColumn assegnazione = new TableColumn<>("ASSEGNAZIONE");
		assegnazione.setPrefWidth(100);
		assegnazione.setMinWidth(40);
		TableColumn anno = new TableColumn<>("ANNO");
		anno.setPrefWidth(100);
		TableColumn caserma = new TableColumn<>("CASERMA");
		caserma.setPrefWidth(330);
		caserma.setMinWidth(10);
		
		//Assegnazione del factory per il valore delle celle di ogni riga
		targa.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("targa"));
		tipologia.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("tipo"));
		stato.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("stato"));
		assegnazione.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("assegnazione"));
		icona.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,ImageView>("image"));
		anno.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("anno"));
		caserma.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("idCaserma"));
		
		//Aggiunta colonne alla tabella
		tableCaserme.getColumns().addAll(icona,tipologia,targa,stato,assegnazione,anno,caserma);
		
		//Sortable e resizable delle colonne
		icona.setResizable(false);
		icona.setSortable(false);
		tipologia.setSortable(false);
		targa.setSortable(false);
		stato.setResizable(false);
		stato.setSortable(false);
		assegnazione.setResizable(false);
		assegnazione.setSortable(false);
		anno.setResizable(false);
		anno.setSortable(false);
		caserma.setSortable(false);
		caserma.setResizable(false);
		
		//Inserisco i dati alla tabella (riempie tutte le celle automaticamente
		//grazie al factory precedentemente associato
		tableCaserme.setItems(data);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setTableViewProvincia() {
		//Dimensioni tabella
		tableProvincia.setPrefWidth(1054);
		tableProvincia.setPrefHeight(250);
		
		//Creazione lista osservabile associata ai mezzi della caserma in cui mi sono autenticato
		ObservableList<TabellaAmministratore> data = controller.caricaMezziProvincia();
		
		//Metto il nome giusto nel Label della caserma
		caserma.setText(controller.getCaserma());
		
		//Creazione e dimensionamento colonne
		TableColumn icona = new TableColumn<>();
		icona.setMinWidth(10);
		icona.setPrefWidth(39);
		TableColumn tipologia = new TableColumn<>("TIPOLOGIA");
		tipologia.setMinWidth(130);
		tipologia.setPrefWidth(230);
		tipologia.setMaxWidth(400);
		TableColumn targa = new TableColumn<>("TARGA");
		targa.setMinWidth(100);
		targa.setPrefWidth(150);
		targa.setMaxWidth(150);
		TableColumn stato = new TableColumn<>("STATO");
		stato.setPrefWidth(100);
		stato.setMinWidth(40);
		TableColumn assegnazione = new TableColumn<>("ASSEGNAZIONE");
		assegnazione.setPrefWidth(100);
		assegnazione.setMinWidth(40);
		TableColumn anno = new TableColumn<>("ANNO");
		anno.setPrefWidth(100);
		TableColumn caserma = new TableColumn<>("CASERMA");
		caserma.setPrefWidth(330);
		caserma.setMinWidth(10);
		
		//Assegnazione del factory per il valore delle celle di ogni riga
		targa.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("targa"));
		tipologia.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("tipo"));
		stato.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("stato"));
		assegnazione.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("assegnazione"));
		icona.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,ImageView>("image"));
		anno.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("anno"));
		caserma.setCellValueFactory(new PropertyValueFactory<TabellaAmministratore,String>("idCaserma"));
		
		//Aggiunta colonne alla tabella
		tableProvincia.getColumns().addAll(icona,tipologia,targa,stato,assegnazione,anno,caserma);
		
		//Sortable e resizable delle colonne
		icona.setResizable(false);
		icona.setSortable(false);
		tipologia.setSortable(false);
		targa.setSortable(false);
		stato.setResizable(false);
		stato.setSortable(false);
		assegnazione.setResizable(false);
		assegnazione.setSortable(false);
		anno.setResizable(false);
		anno.setSortable(false);
		caserma.setSortable(false);
		caserma.setResizable(false);
		
		//Inserisco i dati alla tabella (riempie tutte le celle automaticamente
		//grazie al factory precedentemente associato
		tableProvincia.setItems(data);
	}
	
	
	//richiesteSostituzioni handler
	public void richiesteSostituzioniHandler(Event e) {
		InterfacciaRichiesteSostituzioni richiesteSostituzioni = new InterfacciaRichiesteSostituzioni(new ControllerRichiesteSostituzioni(controller.getIdCaserma()));
		Scene scene = new Scene(richiesteSostituzioni,900,600);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.showAndWait();
	}

	//storicoManutenzioni handler
	public void storicoManutenzioniHandler(Event e) {
		InterfacciaStoricoManutenzioni storicoManutenzioni = new InterfacciaStoricoManutenzioni(new ControllerStoricoManutenzioni(controller.getIdCaserma()));
		Scene scene = new Scene(storicoManutenzioni,900,600);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.showAndWait();
	}
	
	
}
