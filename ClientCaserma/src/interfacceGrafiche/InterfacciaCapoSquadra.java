package interfacceGrafiche;



import java.sql.SQLException;

import controller.ControllerClientCaserma;
import controller.ControllerReport;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.TabellaCapoSquadra;

public class InterfacciaCapoSquadra extends BorderPane{
	@SuppressWarnings("rawtypes")
	private TableView table = null;
	private ImageView auto = null;
	private ImageView posizione = null;
	private ImageView account = null;
	private ImageView rifornimento = null;
	private ImageView tickVerde = null;
	private ImageView tickRossa = null;
	private ImageView tickBlu = null;
	//private ImageView tickGrigia = null;
	private ImageView tickGialla = null;
	private ImageView report = null;
	private ImageView impostazioni = null;
	
	private VBox v3 = null;
	private HBox h3 = null;
	
	private Label totMezzi = null;
	private Label inManutenzione = null;
	private Label livelloCarb = null;
	private Label caserma = null;
	private Label nome = null;
	private int litri;
	
	private ControllerClientCaserma controller = null;
	
	public InterfacciaCapoSquadra(ControllerClientCaserma controller) {
		this.controller=controller;
		refresh();
		initGUI();
	}

	@SuppressWarnings("unchecked")
	private void refresh() {
		  new Thread(() -> {
		    while(true) {
		       try {
		          Thread.sleep(10000); // Wait for 10 secs before updating items
		       } catch (InterruptedException e) {
		          e.printStackTrace();
		       }
		       Platform.runLater(() -> {
		    	   try {
				    	controller = new ControllerClientCaserma(controller.getIdCaserma(),controller.getNome());
						initGUI();
		    	   } catch (SQLException e) {
		    		   	e.printStackTrace();
		    	   }});// Update on JavaFX Application Thread
		    	}
		  	}).start();
	}
	
	

	private void initGUI() {
		nome = new Label(controller.getNome());
		caserma = new Label(controller.getCaserma());
		totMezzi = new Label("" + controller.getTotMezzi());
		litri = Integer.parseInt(controller.getLitri());
		livelloCarb = new Label("" + litri);
		inManutenzione = new Label("" + controller.getMan());
		
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
		table = new TableView();
		
		//Inizializzazione di tutte le icone/immagini
		impostazioni = new ImageView(new Image("./icone/Impostazioni.png"));
		report = new ImageView(new Image("./icone/Documenti.png"));
		report.setOnMouseClicked(this::reportHandler);
		report.setPickOnBounds(true);
		rifornimento = new ImageView(new Image("./icone/Rifornimento.png"));
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
		Label ruolo = new Label("Capo Squadra");
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
		
		//Imposto il margine/spazio per le immagini di auto e report
		root.setMargin(auto, new Insets(30,0,0,10));
		root.setMargin(report, new Insets(30,0,0,10));
		root.setSpacing(30);
		
		//Dimensiono le icone auto e report
		auto.setSmooth(true);
		auto.setFitHeight(40);
		auto.setFitWidth(40);
		report.setSmooth(true);
		report.setFitWidth(45);
		report.setFitHeight(40);
		
		//Aggiungo al vbox principale le due icone
		root.getChildren().addAll(auto,report);
		return root;
	}

	@SuppressWarnings("static-access")
	private Node center() {
		//Creo il vbox principale
		VBox root = new VBox();
		
		//Creo il vbox in alto contenente le sezioni dei mezzi totali,
		//in manutenzione e il livello del carburante e la sezione con il label LISTA MEZZI
		VBox top = new VBox();
		
		//Imposto la sezione in alto, contentente le tre sezioni (mezzi tot, man, carburante)
		HBox topCenter = setTopCenter();
		
		//Imposto la sezione del label lista mezzi
		HBox bottomCenter = setBottomCenter();
		
		//Agigungo al vbox i due hbox appena impostati
		top.getChildren().addAll(topCenter,bottomCenter);
		
		//Imposto il margine per ottimizzare l'impatto visivo
		top.setMargin(top.getChildren().get(0), new Insets(0,0,0,100));
		
		//Imposto tutta la tabella dei mezzi
		setTableView();
		
		//Creo l'hbox della legenda di stato/assegnazione
		HBox bottom = setBottom();
		
		//Imposto il margine per la tabella, per ottimizzare l'impatto visivo
		root.setMargin(table, new Insets(0,65,0,100));
		
		//Aggiungo la sezione in alto, la tabella e la legenda al vbox principale
		root.getChildren().addAll(top,table,bottom);
		
		//Imposto il margine per la legenda per ottimizzare l'impatto visivo
		root.setMargin(bottom, new Insets(0,0,0,100));
		return root;
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

	private HBox setBottomCenter() {
		//HBox principale per il label lista mezzi
		HBox res = new HBox();
		
		//Dimensiono l'hbox e lo allineo al centro
		res.setPrefWidth(1019);
		res.setPrefHeight(65);
		res.setAlignment(Pos.CENTER);
		
		//Creo il label lista mezzi
		Label l = new Label("LISTA MEZZI");
		
		//Imposto il font size a 20
		l.setFont(Font.font(20));
		
		//Aggiungo il label all'hbox principale
		res.getChildren().add(l);
		return res;
	}

	@SuppressWarnings("static-access")
	private HBox setTopCenter() {
		//HBox principale
		HBox res = new HBox();
		//Creo le tre vbox principali dentro all'hbox principale
		VBox v1 = new VBox();
		VBox v2 = new VBox();
		v3 = new VBox();
		//Creo un padding equivalente a paddingTop=10, paddingLeft=10
		v1.setPadding(new Insets(10,0,0,10));
		v2.setPadding(new Insets(10,0,0,10));
		v3.setPadding(new Insets(10,0,0,10));
		
		//Creo le icone da mettere nelle sezioni principali
		ImageView autoTot = new ImageView(new Image("./icone/Auto.png"));
		ImageView autoMan = new ImageView(new Image("./icone/Auto.png"));
		
		//Dimensiono le immagini
		autoTot.setSmooth(true);
		autoTot.setFitWidth(35);
		autoTot.setFitHeight(35);
		autoMan.setSmooth(true);
		autoMan.setFitWidth(35);
		autoMan.setFitHeight(35);
		
		//Dimensiono le sezioni dei mezzi tot, in man e carburante
		v1.setPrefWidth(156);
		v1.setPrefHeight(76);
		v1.setStyle("-fx-border-color: lightgrey");
		v2.setPrefWidth(170);
		v2.setPrefHeight(92);
		v2.setStyle("-fx-border-color: lightgrey");
		v3.setPrefWidth(170);
		v3.setPrefHeight(92);
		v3.setStyle("-fx-border-color: lightgrey");
		
		//Spazio tra i tre principali vbox
		res.setSpacing(280);
		
		//Creo i label
		Label l1 = new Label("TOTALE MEZZI");
		Label l2 = new Label("IN MANUTENZIONE");
		Label l3 = new Label("LIVELLO CARBURANTE");
		
		//Creazione e dimensionamento Hbox in cui mettere il tot mezzi
		HBox h1 = new HBox();
		h1.getChildren().addAll(autoTot,totMezzi);
		h1.setMargin(totMezzi,new Insets(10,0,0,10));
		
		//Creazione e dimensionamento hbox in cui mettere i mezzi in manutenzione
		HBox h2 = new HBox();
		h2.getChildren().addAll(autoMan,inManutenzione);
		
		//Creo l'hbox della sezione livello di carburante
		h3 = new HBox();
		
		//Imposto le dimensioni dell'immagine della pompa del carburante
		rifornimento.setSmooth(true);
		rifornimento.setFitWidth(35);
		rifornimento.setFitHeight(31);
		if(this.litri<=300) {
			livelloCarb.setTextFill(Color.DARKRED);
			ImageView attenzione = new ImageView(new Image("./icone/RifornimentoScarso.png"));
			attenzione.setSmooth(true);
			attenzione.setFitWidth(35);
			attenzione.setFitHeight(31);
			h3.getChildren().add(attenzione);
		}

		//Aggiungo all'hbox della sezione del rifornimento l'immagine
		//della pompa del carburante e il livello attuale
		h3.getChildren().addAll(rifornimento,livelloCarb);
		
		//Aggiungo ai tre vbox principali le rispettive sezioni dei mezzi totali
		//, in manutenzione e livello carburante
		v1.getChildren().addAll(l1,h1);
		v2.getChildren().addAll(l2,h2);
		v3.getChildren().addAll(l3,h3);
		if(this.litri<=300) {
			Label alert = new Label("RICHIESTA RIFORNIMENTO\nAUTOMATICA EFFETTUATA");
			alert.setTextFill(Color.DARKRED);
			v3.getChildren().add(alert);
			v3.setStyle("-fx-border-color: red");
		}
		
		//Imposto i margini dei label come marginTop=10 e marginLeft=10
		h2.setMargin(inManutenzione, new Insets(10,0,0,10));
		h3.setMargin(livelloCarb, new Insets(10,0,0,10));
		
		//Inserisco i tre vbox principali all'hbox padre
		res.getChildren().addAll(v1,v2,v3);
		
		//Imposto i margini dei tre vbox principali come 
		// marginTop=10
		res.setMargin(v1, new Insets(10,0,0,0));
		res.setMargin(v2, new Insets(10,0,0,0));
		res.setMargin(v3, new Insets(10,0,0,0));
		return res;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setTableView() {
		//Dimensioni tabella
		table.setPrefWidth(915);
		table.setPrefHeight(368);
		
		//Creazione lista osservabile associata ai mezzi della caserma in cui mi sono autenticato
		ObservableList<TabellaCapoSquadra> data = controller.caricaMezziCapo();
		
		//Metto il nome giusto nel Label della caserma
		caserma.setText(controller.getCaserma());
		
		//Creazione e dimensionamento colonne
		TableColumn icona = new TableColumn<>();
		icona.setMinWidth(10);
		icona.setPrefWidth(39);
		TableColumn tipologia = new TableColumn<>("TIPOLOGIA");
		tipologia.setMinWidth(100);
		tipologia.setPrefWidth(148);
		tipologia.setMaxWidth(200);
		TableColumn targa = new TableColumn<>("TARGA");
		targa.setMinWidth(70);
		targa.setPrefWidth(115);
		targa.setMaxWidth(150);
		TableColumn stato = new TableColumn<>("STATO");
		stato.setPrefWidth(60);
		stato.setMinWidth(10);
		TableColumn assegnazione = new TableColumn<>("ASSEGNAZIONE");
		assegnazione.setPrefWidth(100);
		assegnazione.setMinWidth(10);
		TableColumn azioni = new TableColumn<>("AZIONI");
		azioni.setPrefWidth(200);
		TableColumn anno = new TableColumn<>("ANNO");
		anno.setPrefWidth(60);
		TableColumn man = new TableColumn<>();
		man.setPrefWidth(150);
		TableColumn rest = new TableColumn<>();
		rest.setPrefWidth(150);
		
		//Assegnazione del factory per il valore delle celle di ogni riga
		targa.setCellValueFactory(new PropertyValueFactory<TabellaCapoSquadra,String>("targa"));
		tipologia.setCellValueFactory(new PropertyValueFactory<TabellaCapoSquadra,String>("tipo"));
		stato.setCellValueFactory(new PropertyValueFactory<TabellaCapoSquadra,String>("stato"));
		assegnazione.setCellValueFactory(new PropertyValueFactory<TabellaCapoSquadra,String>("assegnazione"));
		anno.setCellValueFactory(new PropertyValueFactory<TabellaCapoSquadra,String>("anno"));
		azioni.setCellValueFactory(new PropertyValueFactory<TabellaCapoSquadra,Button>("sost"));
		man.setCellValueFactory(new PropertyValueFactory<TabellaCapoSquadra,Button>("man"));
		icona.setCellValueFactory(new PropertyValueFactory<TabellaCapoSquadra,ImageView>("image"));
		rest.setCellValueFactory(new PropertyValueFactory<TabellaCapoSquadra,Button>("rest"));
		
		//Aggiunta colonne alla tabella
		table.getColumns().addAll(icona,tipologia,targa,stato,assegnazione,anno,azioni,man,rest);
		
		//Sortable e resizable delle colonne
		icona.setResizable(false);
		icona.setSortable(false);
		tipologia.setSortable(false);
		targa.setSortable(false);
		stato.setResizable(false);
		stato.setSortable(false);
		assegnazione.setResizable(false);
		assegnazione.setSortable(false);
		azioni.setResizable(false);
		azioni.setSortable(false);
		man.setSortable(false);
		man.setResizable(false);
		anno.setResizable(false);
		anno.setSortable(false);
		
		//Inserisco i dati alla tabella (riempie tutte le celle automaticamente
		//grazie al factory precedentemente associato
		table.setItems(data);
	}
	
	
	//Report handler
	public void reportHandler(Event e) {
		InterfacciaReport report = new InterfacciaReport(new ControllerReport(controller.getIdCaserma()));
		Scene scene = new Scene(report,400,500);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.showAndWait();
	}

}
