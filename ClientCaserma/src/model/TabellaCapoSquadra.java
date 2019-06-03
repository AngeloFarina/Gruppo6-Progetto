package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TabellaCapoSquadra {

	private ImageView image;
	private SimpleStringProperty tipo;
	private SimpleStringProperty targa;
	private ImageView stato;
	private ImageView assegnazione;
	private SimpleStringProperty anno;
	private Button sost;
	private Button man;
	private Button rest;
	
	public TabellaCapoSquadra( String tipo, String targa, String stato, String assegnazione, String anno) {
		this.image = setImage(tipo);
		this.tipo = new SimpleStringProperty(tipo);
		this.targa =  new SimpleStringProperty(targa);
		if(stato.contentEquals("DISPONIBILE"))
			this.stato = new ImageView( new Image("icone/TickVerde.png"));
		else
			this.stato = new ImageView(new Image("icone/TickRossa.png"));
		if(assegnazione.equals("PROPRIO"))
			this.assegnazione = new ImageView(new Image("icone/TickBlu.png"));
		else
			this.assegnazione=  new ImageView(new Image("icone/TickGialla.png"));
		this.anno = new SimpleStringProperty(anno);
		this.stato.setSmooth(true);
		this.stato.setFitWidth(27);
		this.stato.setFitHeight(25);
		this.assegnazione.setSmooth(true);
		this.assegnazione.setFitWidth(25);
		this.assegnazione.setFitHeight(25);
		sost = new Button("Richiedi Sostituzione");
		man = new Button("Richiedi Manutenzione");
		if(assegnazione.equals("IN SOSTITUZIONE"))
			rest = new Button("Restituisci");
		else
			rest = null;
		man.setStyle("-fx-background-color: darkgrey; -fx-text-fill: white;");
	}


	public void setRest(Button rest) {
		this.rest = rest;
	}

	public Button getRest() {
		return rest;
	}


	private ImageView setImage(String tipo) {
		ImageView res = null;
		if(tipo.equalsIgnoreCase("CAMION"))
			res = new ImageView(new Image("./icone/Camion.png"));
		else if(tipo.equalsIgnoreCase("AUTOPOMPA"))
			res = new ImageView(new Image("./icone/Autopompa.png"));
		else if (tipo.equals("JEEP"))
			res = new ImageView(new Image("./icone/Jeep.png"));
		else if (tipo.contentEquals("UTILITARIA"))
			res = new ImageView(new Image("./icone/Utilitaria.png"));
		else
			res = new ImageView(new Image("./icone/Auto.png"));
		res.setSmooth(true);
		res.setFitWidth(25);
		res.setFitHeight(25);
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


	public String getAnno() {
		return anno.get();
	}


	public void setAnno(String anno) {
		this.anno = new SimpleStringProperty(anno);
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
			this.assegnazione = new ImageView(new Image("icone/TickBlu.png"));
		else
			this.assegnazione=  new ImageView(new Image("icone/TickGialla.png"));
	}
	
	
}
