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
		this.setDataOra(new SimpleStringProperty(dataOra));
		this.setDescrizione(new SimpleStringProperty(dataOra));
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

	public SimpleStringProperty getDataOra() {
		return dataOra;
	}

	public void setDataOra(SimpleStringProperty dataOra) {
		this.dataOra = dataOra;
	}

	public SimpleStringProperty getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(SimpleStringProperty descrizione) {
		this.descrizione = descrizione;
	}


	public Button getConcludi() {
		return concludi;
	}


	public void setConcludi(Button concludi) {
		this.concludi = concludi;
	}
	
	
}
