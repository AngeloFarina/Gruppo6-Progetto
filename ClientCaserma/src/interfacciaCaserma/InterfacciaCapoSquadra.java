package interfacciaCaserma;

import java.sql.SQLException;
import java.util.List;

import controller.ControllerClientCaserma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Prova;

public class InterfacciaCapoSquadra extends BorderPane{
	private TableView table = null;
	private ImageView auto = null;
	private ImageView posizione = null;
	private ImageView account = null;
	private ImageView rifornimento = null;
	private ImageView tickVerde = null;
	private ImageView tickRossa = null;
	private ImageView tickBlu = null;
	private ImageView tickGrigia = null;
	private ImageView tickGialla = null;
	private ImageView report = null;
	private ImageView impostazioni = null;
	
	private Label totMezzi = new Label("5");
	private Label inManutenzione = new Label("1");
	private Label livelloCarb = new Label("3000");
	private Label caserma = new Label("CASERMA");
	
	private ControllerClientCaserma controller = null;
	
	public InterfacciaCapoSquadra(ControllerClientCaserma controller) {
		this.controller=controller;
		initGUI();
	}

	private void initGUI() {
		this.setWidth(1280);
		this.setHeight(720);
		initComponents();
		setCenter(center());
		setLeft(left());
		setTop(top());
	}

	private void initComponents() {
		table = new TableView();
		impostazioni = new ImageView(new Image("./icone/Impostazioni.png"));
		report = new ImageView(new Image("./icone/Documenti.png"));
		rifornimento = new ImageView(new Image("./icone/Rifornimento.png"));
		tickVerde = new ImageView(new Image("./icone/TickVerde.png"));
		tickGrigia = new ImageView(new Image("./icone/TickGrigia.png"));
		tickRossa = new ImageView(new Image("./icone/TickRossa.png"));
		tickBlu = new ImageView(new Image("./icone/TickBlu.jpg"));
		tickGialla = new ImageView(new Image("./icone/TickGialla.png"));
		account = new ImageView(new Image("./icone/utente.png"));
		posizione = new ImageView(new Image("./icone/Posizione.png"));
		auto = new ImageView(new Image("./icone/Auto.png"));
	}

	private Node top() {
		HBox main = new HBox();
		main.setAlignment(Pos.TOP_LEFT);
		main.setStyle("-fx-border-color:lightgrey");
		main.setPrefWidth(1080);
		main.setPrefHeight(68);
		impostazioni.setSmooth(true);
		impostazioni.setFitWidth(60);
		impostazioni.setFitHeight(76);
		posizione.setSmooth(true);
		posizione.setFitWidth(67);
		posizione.setFitHeight(64);
		posizione.setStyle("-fx-margin-left: 5; -fx-margin-bottom: 5; -fx-margin-left:10;");
		caserma.setStyle("-fx-font: Arial");
		caserma.setFont(Font.font(22));
		caserma.setStyle("-fx-margin-top: 20;");
		caserma.setPrefWidth(920);
		caserma.setPrefHeight(45);
		caserma.setAlignment(Pos.CENTER_LEFT);
		caserma.setContentDisplay(ContentDisplay.LEFT);
		caserma.setGraphicTextGap(4);
		VBox v = new VBox();
		v.setStyle("-fx-margin-top : 10");
		v.setPrefWidth(150);
		v.setPrefHeight(200);
		v.setSpacing(7);
		v.setAlignment(Pos.TOP_LEFT);
		Label nome = new Label("MARIO ROSSI");
		nome.setFont(Font.font(20));
		Label ruolo = new Label("Capo Squadra");
		ruolo.setAlignment(Pos.CENTER_RIGHT);
		v.getChildren().addAll(nome,ruolo);
		account.setSmooth(true);
		account.setFitHeight(60);
		account.setFitWidth(65);
		main.getChildren().addAll(impostazioni,posizione,caserma,v,account);
		main.setMargin(caserma, new Insets(15,0,0,0));
		main.setMargin(v, new Insets(10,0,0,0));
		main.setMargin(account, new Insets(6,0,0,20));
		return main;
	}

	private Node left() {
		VBox root = new VBox();
		root.setStyle("-fx-background-color: lightgrey");
		root.setPrefHeight(642);
		root.setPrefWidth(61);
		root.setMargin(auto, new Insets(30,0,0,10));
		root.setMargin(report, new Insets(30,0,0,10));
		root.setSpacing(30);
		auto.setSmooth(true);
		auto.setFitHeight(40);
		auto.setFitWidth(40);
		root.getChildren().add(auto);
		report.setSmooth(true);
		report.setFitWidth(45);
		report.setFitHeight(40);
		root.getChildren().add(report);
		return root;
	}

	private Node center() {
		VBox root = new VBox();
		VBox top = new VBox();
		HBox topCenter = setTopCenter();
		HBox bottomCenter = setBottomCenter();
		top.getChildren().addAll(topCenter,bottomCenter);
		top.setMargin(top.getChildren().get(0), new Insets(0,0,0,100));
		setTableView();
		HBox bottom = setBottom();
		root.setMargin(table, new Insets(0,65,0,100));
		root.getChildren().addAll(top,table,bottom);
		root.setMargin(bottom, new Insets(0,0,0,100));
		return root;
	}

	private HBox setBottom() {
		HBox res = new HBox();
		Label disp = new Label("DISPONIBILE");
		Label man = new Label("IN MANUTENZIONE");
		Label proprio = new Label("MEZZO PROPRIO");
		Label sost = new Label("MEZZO SOSTITUTIVO");
		tickVerde.setSmooth(true);
		tickVerde.setFitWidth(46);
		tickVerde.setFitHeight(38);
		tickRossa.setSmooth(true);
		tickRossa.setFitWidth(50);
		tickRossa.setFitHeight(36);
		tickBlu.setSmooth(true);
		tickBlu.setFitWidth(34);
		tickBlu.setFitHeight(28);
		tickGialla.setSmooth(true);
		tickGialla.setFitWidth(34);
		tickGialla.setFitHeight(32);
		disp.setPadding(new Insets(10,0,0,0));
		proprio.setPadding(new Insets(0,0,0,5));
		res.setMargin(man, new Insets(10,0,0,0));
		res.setMargin(proprio, new Insets(10,0,0,0));
		res.setMargin(sost, new Insets(10,0,0,5));
		res.setMargin(tickRossa, new Insets(3,0,0,0));
		res.setMargin(tickBlu, new Insets(5,0,0,0));
		res.setMargin(tickGialla, new Insets(4,0,0,5));
		res.getChildren().addAll(tickVerde,disp,tickRossa,man,tickBlu,proprio,tickGialla,sost);
		return res;
	}

	private HBox setBottomCenter() {
		HBox res = new HBox();
		res.setPrefWidth(1019);
		res.setPrefHeight(65);
		res.setAlignment(Pos.CENTER);
		Label l = new Label("LISTA MEZZI");
		l.setFont(Font.font(20));
		res.getChildren().add(l);
		return res;
	}

	private HBox setTopCenter() {
		HBox res = new HBox();
		VBox v1 = new VBox();
		VBox v2 = new VBox();
		VBox v3 = new VBox();
		v1.setPadding(new Insets(10,0,0,10));
		v2.setPadding(new Insets(10,0,0,10));
		v3.setPadding(new Insets(10,0,0,10));
		ImageView rifornimento = new ImageView(new Image("./icone/Rifornimento.png"));
		ImageView autoTot = new ImageView(new Image("./icone/Auto.png"));
		ImageView autoMan = new ImageView(new Image("./icone/Auto.png"));
		autoTot.setSmooth(true);
		autoTot.setFitWidth(35);
		autoTot.setFitHeight(35);
		autoMan.setSmooth(true);
		autoMan.setFitWidth(35);
		autoMan.setFitHeight(35);
		v1.setPrefWidth(156);
		v1.setPrefHeight(76);
		v1.setStyle("-fx-border-color: lightgrey");
		v2.setPrefWidth(170);
		v2.setPrefHeight(92);
		v2.setStyle("-fx-border-color: lightgrey");
		v3.setPrefWidth(170);
		v3.setPrefHeight(92);
		v3.setStyle("-fx-border-color: lightgrey");
		res.setSpacing(280);
		Label l1 = new Label("TOTALE MEZZI");
		Label l2 = new Label("IN MANUTENZIONE");
		Label l3 = new Label("LIVELLO CARBURANTE");
		HBox h1 = new HBox();
		h1.getChildren().addAll(autoTot,totMezzi);
		h1.setMargin(totMezzi,new Insets(10,0,0,10));
		HBox h2 = new HBox();
		h2.getChildren().addAll(autoMan,inManutenzione);
		HBox h3 = new HBox();
		rifornimento.setSmooth(true);
		rifornimento.setFitWidth(35);
		rifornimento.setFitHeight(31);
		h3.getChildren().addAll(rifornimento,livelloCarb);
		v1.getChildren().addAll(l1,h1);
		v2.getChildren().addAll(l2,h2);
		v3.getChildren().addAll(l3,h3);
		h2.setMargin(inManutenzione, new Insets(10,0,0,10));
		h3.setMargin(livelloCarb, new Insets(10,0,0,10));
		res.getChildren().addAll(v1,v2,v3);
		res.setMargin(v1, new Insets(10,0,0,0));
		res.setMargin(v2, new Insets(10,0,0,0));
		res.setMargin(v3, new Insets(10,0,0,0));
		return res;
	}

	private void setTableView() {
		table.setPrefWidth(915);
		table.setPrefHeight(368);
		ObservableList<Prova> data = null;
		try {
			data = controller.caricaMezziCaserma();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		caserma.setText(controller.getCaserma());
		TableColumn vuota = new TableColumn<>();
		vuota.setMinWidth(10);
		vuota.setPrefWidth(39);
		TableColumn tipologia = new TableColumn<>("TIPOLOGIA");
		tipologia.setMinWidth(100);
		tipologia.setPrefWidth(148);
		tipologia.setMaxWidth(200);
		TableColumn targa = new TableColumn<>("TARGA");
		targa.setMinWidth(70);
		targa.setPrefWidth(115);
		targa.setMaxWidth(150);
		TableColumn stato = new TableColumn<>("STATO");
		stato.setPrefWidth(89);
		stato.setMinWidth(10);
		TableColumn azioni = new TableColumn<>("AZIONI");
		azioni.setPrefWidth(680);
		azioni.setMinWidth(10);
		targa.setCellValueFactory(new PropertyValueFactory<Prova,String>("targa"));
		tipologia.setCellValueFactory(new PropertyValueFactory<Prova,String>("tipo"));
		stato.setCellValueFactory(new PropertyValueFactory<Prova,String>("stato"));
		TableColumn agg = new TableColumn<>();
		TableColumn man = new TableColumn<>();
		table.getColumns().addAll(vuota,tipologia,targa,stato,agg,azioni,man);
		vuota.setResizable(false);
		vuota.setSortable(false);
		tipologia.setSortable(false);
		targa.setSortable(false);
		stato.setResizable(false);
		stato.setSortable(false);
		azioni.setResizable(false);
		azioni.setSortable(false);
		man.setSortable(false);
		man.setResizable(false);
		agg.setResizable(false);
		agg.setSortable(false);
		agg.setPrefWidth(60);
		azioni.setPrefWidth(120);
		man.setPrefWidth(500);
		agg.setCellValueFactory(new PropertyValueFactory<Prova,Button>("agg"));
		azioni.setCellValueFactory(new PropertyValueFactory<Prova,Button>("sost"));
		man.setCellValueFactory(new PropertyValueFactory<Prova,Button>("man"));
		vuota.setCellValueFactory(new PropertyValueFactory<Prova,ImageView>("image"));
		table.setItems(data);
	}
	
}
