package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Modifica implements Serializable{

	private static final long serialVersionUID = 21L;
	
	private String tipo;
	private String targa;
	private String anno;
	private String idCaserma;
	
	private Stato statoMezzo;
	private Assegnazione assegnazioneMezzo;
	private List<Caserma> caserme = null;
	
	public Modifica( String tipo, String targa, Stato stato, Assegnazione assegnazione, String anno,String idCaserma,List<Caserma> caserme) {
		this.statoMezzo=stato;
		this.assegnazioneMezzo=assegnazione;
		this.caserme = caserme;
		this.idCaserma = idCaserma;
		this.tipo = tipo;
		this.targa =  targa;
		this.caserme = new ArrayList<Caserma>(caserme);
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getTarga() {
		return targa;
	}


	public void setTarga(String targa) {
		this.targa = targa;
	}


	public String getAnno() {
		return anno;
	}


	public void setAnno(String anno) {
		this.anno = anno;
	}


	public String getIdCaserma() {
		return idCaserma;
	}


	public void setIdCaserma(String idCaserma) {
		this.idCaserma = idCaserma;
	}


	public Stato getStatoMezzo() {
		return statoMezzo;
	}


	public void setStatoMezzo(Stato statoMezzo) {
		this.statoMezzo = statoMezzo;
	}


	public Assegnazione getAssegnazioneMezzo() {
		return assegnazioneMezzo;
	}


	public void setAssegnazioneMezzo(Assegnazione assegnazioneMezzo) {
		this.assegnazioneMezzo = assegnazioneMezzo;
	}


	public List<Caserma> getCaserme() {
		return caserme;
	}


	public void setCaserme(List<Caserma> caserme) {
		this.caserme = caserme;
	}
	
}
