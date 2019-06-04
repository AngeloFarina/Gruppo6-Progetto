package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TabellaVigile {

	private ImageView image;
	private SimpleStringProperty tipo;
	private SimpleStringProperty targa;
	private SimpleStringProperty anno;
	private ImageView stato;
	private ImageView assegnazione;
	
	public TabellaVigile(String tipo, String targa, Stato stato, Assegnazione assegnazione,String anno) {
		this.image = setImage(tipo);
		this.anno= new SimpleStringProperty(anno);
		this.tipo = new SimpleStringProperty(tipo);
		this.targa =  new SimpleStringProperty(targa);
		if(stato.equals(Stato.DISPONIBILE))
			this.stato = new ImageView( new Image("icone/TickVerde.png"));
		else
			this.stato = new ImageView(new Image("icone/TickRossa.png"));
		if(assegnazione.equals(Assegnazione.PROPRIO))
			this.assegnazione = new ImageView(new Image("icone/TickBlu.png"));
		else
			this.assegnazione=  new ImageView(new Image("icone/TickGialla.png"));
		this.stato.setSmooth(true);
		this.stato.setFitWidth(27);
		this.stato.setFitHeight(25);
		this.assegnazione.setSmooth(true);
		this.assegnazione.setFitWidth(25);
		this.assegnazione.setFitHeight(25);
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
	
	public String getAnno() {
		return this.anno.get();
	}
	
	public void setAnno(String anno) {
		this.anno=new SimpleStringProperty(anno);
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
		if(stato.equals("DISPONIBILE"))
			this.stato = new ImageView( new Image("icone/TickVerde.png"));
		else
			this.stato = new ImageView(new Image("icone/TickRossa.png"));
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
