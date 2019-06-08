package model;


import java.util.ArrayList;
import java.util.List;

import interfacceGrafiche.InterfacciaConcludiSostituzione;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TabellaSostituzioni {

	private SimpleStringProperty dataOra;
	private SimpleStringProperty targa;
	private SimpleStringProperty idCaserma;
	private SimpleStringProperty descrizione;
	private SimpleStringProperty tipo;
	private Button concludi;
	private List<String> idMezzi;
	
	public TabellaSostituzioni( String tipo, String targa, String idCaserma,String dataOra,String descrizione, List<String> idMezzi) {
		this.idCaserma= new SimpleStringProperty(idCaserma);
		this.idMezzi = new ArrayList<String>(idMezzi);
		this.tipo = new SimpleStringProperty(tipo);
		this.targa =  new SimpleStringProperty(targa);
		this.dataOra = new SimpleStringProperty(dataOra);
		this.descrizione = new SimpleStringProperty(descrizione);
		this.setConcludi(new Button("Concludi"));
		concludi.setOnAction(this::concludi);
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

	private void concludi(Event e) {
		RichiestaSostituzione r = new RichiestaSostituzione(getIdCaserma(),getTarga(),getTipo(),getDataOra(),getDescrizione());
		InterfacciaConcludiSostituzione cs = new InterfacciaConcludiSostituzione(r,this.idMezzi);
		Stage s = new Stage();
		s.setScene(new Scene(cs));
		s.setResizable(false);
		s.show();
	}
	public List<String> getMezzi() {
		return new ArrayList<String>(idMezzi);
	}

}
