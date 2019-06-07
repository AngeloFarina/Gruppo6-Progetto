package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class TabellaStoricoManutenzioni {

	private SimpleStringProperty dataOraInizio;
	private SimpleStringProperty dataOraFine;
	private SimpleStringProperty targa;
	private SimpleStringProperty id;
	private SimpleStringProperty descrizione;
	private SimpleStringProperty tipo;
	
	public TabellaStoricoManutenzioni( String tipo, String targa, String id,String dataOraInizio,String dataOraFine,String descrizione) {
		this.id = new SimpleStringProperty(id);
		this.tipo = new SimpleStringProperty(tipo);
		this.targa =  new SimpleStringProperty(targa);
		this.dataOraInizio = new SimpleStringProperty(dataOraInizio);
		this.dataOraFine = new SimpleStringProperty(dataOraFine);
		this.descrizione = new SimpleStringProperty(descrizione);
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

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id = new SimpleStringProperty(id);
	}

	public String getDataOraInizio() {
		return dataOraInizio.get();
	}

	public void setDataOra(String dataOraInizio) {
		this.dataOraInizio= new SimpleStringProperty(dataOraInizio);
	}

	public String getDataOraFine() {
		return this.dataOraFine.get();
	}
	
	public void setDataOraFine(String dataOraFine) {
		this.dataOraFine = new SimpleStringProperty(dataOraFine);
	}
	
	public String getDescrizione() {
		return descrizione.get();
	}

	public void setDescrizione(String  descrizione) {
		this.descrizione = new SimpleStringProperty(descrizione);
	}
	
}
