package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class TabellaCapoSquadra {

	private ImageView image;
	private SimpleStringProperty tipo;
	private SimpleStringProperty targa;
	private ImageView stato;
	private ImageView assegnazione;
	private Button agg;
	private Button sost;
	private Button man;
	
	public TabellaCapoSquadra( String tipo, String targa, String stato, String assegnazione) {
		image = setImage(tipo);
		this.tipo = new SimpleStringProperty(tipo);
		this.targa =  new SimpleStringProperty(targa);
		if(stato.contentEquals("DISPONIBILE"))
			this.stato = new ImageView( new Image("icone/TickVerde.png"));
		else
			this.stato = new ImageView(new Image("icone/TickRossa.png"));
		if(assegnazione.equals("PROPRIO"))
			this.assegnazione = new ImageView(new Image("icone/TickBlu.jpg"));
		else
			this.assegnazione=  new ImageView(new Image("icone/TickGialla.png"));
		agg = new Button();
		ImageView ag = new ImageView(new Image("./icone/Aggiungi.png"));
		ag.setSmooth(true);
		ag.setFitWidth(25);
		ag.setFitHeight(25);
		this.stato.setSmooth(true);
		this.stato.setFitWidth(35);
		this.stato.setFitHeight(30);
		this.assegnazione.setSmooth(true);
		this.assegnazione.setFitWidth(25);
		this.assegnazione.setFitHeight(25);
		agg.setGraphic(ag);
		agg.setStyle("-fx-background-color: white; -fx-text-fill: white;");
		agg.setFont(Font.font(20));
		sost = new Button("Richiedi Sostituzione");
		man = new Button("Richiedi Manutenzione");
		man.setStyle("-fx-background-color: darkgrey; -fx-text-fill: white;");
	}


	private ImageView setImage(String tipo) {
		ImageView res = new ImageView(new Image("./icone/Auto.png"));
		res.setSmooth(true);
		res.setFitWidth(25);
		res.setFitHeight(25);
		return res;
	}


	private List<Button> setButtons() {
		List<Button> res = new ArrayList<Button>();
		Button aggiungi = new Button("+");
		Button sost = new Button("Richiedi Sost.");
		Button man = new Button("Richiedi Man.");
		man.setStyle("-fx-background-color: grey; -fx-font-color: white;");
		aggiungi.setStyle("-fx-background-color: grey; -fx-font-color: white;");
		res.add(aggiungi);
		res.add(sost);
		res.add(man);
		return res;
	}

	public ImageView getImage() {
		return this.image;
	}
	
	public void setImage(ImageView image) {
		this.image=image;
	}

	public String getTipo() {
		return tipo.get();
	}

	public void setTipo(String tipo) {
		this.tipo = new SimpleStringProperty(tipo);
	}

	public String getTarga() {
		return targa.get();
	}

	public void setTarga(String targa) {
		this.targa = new SimpleStringProperty(targa);
	}

	public ImageView getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		if(stato.contentEquals("DISPONIBILE"))
			this.stato = new ImageView( new Image("icone/TickVerde.png"));
		else
			this.stato = new ImageView(new Image("icone/TickRossa.png"));
	}


	public Button getAgg() {
		return agg;
	}


	public void setAgg(Button agg) {
		this.agg = agg;
	}


	public Button getSost() {
		return sost;
	}


	public void setSost(Button sost) {
		this.sost = sost;
	}


	public Button getMan() {
		return man;
	}


	public void setMan(Button man) {
		this.man = man;
	}
	
	public ImageView getAssegnazione() {
		return this.assegnazione;
	}

	public void setAssegnazione(String assegnazione) {
		if(assegnazione.equals("PROPRIO"))
			this.assegnazione = new ImageView(new Image("icone/TickBlu.jpg"));
		else
			this.assegnazione=  new ImageView(new Image("icone/TickGialla.png"));
	}
	
	
}
