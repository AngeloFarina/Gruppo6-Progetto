package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class TabellaSostituzioni {

	private SimpleStringProperty dataOra;
	private SimpleStringProperty targa;
	private SimpleStringProperty idCaserma;
	private SimpleStringProperty descrizione;
	private SimpleStringProperty tipo;
	private Button concludi;
	
	public TabellaSostituzioni( String tipo, String targa, String idCaserma,String dataOra,String descrizione) {
		this.idCaserma= new SimpleStringProperty(idCaserma);
		this.tipo = new SimpleStringProperty(tipo);
		this.targa =  new SimpleStringProperty(targa);
		this.dataOra = new SimpleStringProperty(dataOra);
		this.descrizione = new SimpleStringProperty(descrizione);
		this.setConcludi(new Button("Concludi"));
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

	public String getIdCaserma() {
		return idCaserma.get();
	}

	public void setIdCaserma(String idCaserma) {
		this.idCaserma = new SimpleStringProperty(idCaserma);
	}

	public String getDataOra() {
		return dataOra.get();
	}

	public void setDataOra(String dataOra) {
		this.dataOra = new SimpleStringProperty(dataOra);
	}

	public String getDescrizione() {
		return descrizione.get();
	}

	public void setDescrizione(String  descrizione) {
		this.descrizione = new SimpleStringProperty(descrizione);
	}


	public Button getConcludi() {
		return concludi;
	}


	public void setConcludi(Button concludi) {
		this.concludi = concludi;
	}
	
	
}
