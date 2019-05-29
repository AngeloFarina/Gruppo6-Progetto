package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class Prova {

	private ImageView image;
	private SimpleStringProperty tipo;
	private SimpleStringProperty targa;
	private SimpleStringProperty stato;
	private Button agg;
	private Button sost;
	private Button man;
	
	public Prova( String tipo, String targa, String stato) {
		image = setImage();
		this.tipo = new SimpleStringProperty(tipo);
		this.targa =  new SimpleStringProperty(targa);
		this.stato =  new SimpleStringProperty(stato);
		agg = new Button("+");
		agg.setStyle("-fx-background-color: darkgrey; -fx-text-fill: white;");
		agg.setFont(Font.font(20));
		sost = new Button("Richiedi Sost.");
		man = new Button("Richiedi Man.");
		man.setStyle("-fx-background-color: darkgrey; -fx-text-fill: white;");
	}


	private ImageView setImage() {
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

	public String getStato() {
		return stato.get();
	}

	public void setStato(String stato) {
		this.stato = new SimpleStringProperty(stato);
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

	
	
}
