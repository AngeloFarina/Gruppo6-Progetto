package interfacciaCaserma;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

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
	
	public InterfacciaCapoSquadra() {
		initGUI();
	}

	private void initGUI() {
		initComponents();
		setCenter(center());
		setLeft(left());
		setTop(top());
	}

	private void initComponents() {
		table = new TableView();
		impostazioni = new ImageView(new Image("./icone/Impostazioni.png"));
		report = new ImageView(new Image("./icone/Report.png"));
		rifornimento = new ImageView(new Image("./icone/Rifornimento.png"));
		tickVerde = new ImageView(new Image("./icone/TickVerde.png"));
		tickGrigia = new ImageView(new Image("./icone/TickGrigia.png"));
		tickRossa = new ImageView(new Image("./icone/TickRossa.png"));
		tickBlu = new ImageView(new Image("./icone/TickBlu.png"));
		tickGialla = new ImageView(new Image("./icone/TickGialla.png"));
		account = new ImageView(new Image("./icone/Utente.png"));
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
		return main;
	}

	private Node left() {
		return null;
	}

	private Node center() {
		return null;
	}
	
}
