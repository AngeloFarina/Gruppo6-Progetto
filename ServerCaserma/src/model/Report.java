package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Report implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int kmEffettuati;
	private int carburanteConsumato;
	private String descrizione;
	private Tipo tipo;
	private LocalDateTime dataOra;
	private String idCaserma;
	private List<Mezzo> listaMezzi;
	public Report(int kmEffettuati, int carburanteConsumato, String descrizione, Tipo tipo,
			LocalDateTime dataOra, String idCaserma, List<Mezzo> listaMezzi) {
		this.kmEffettuati = kmEffettuati;
		this.carburanteConsumato = carburanteConsumato;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.dataOra = dataOra;
		this.idCaserma=idCaserma;
		this.listaMezzi = listaMezzi;
	}
	
	public List<Mezzo> getListaMezzi() {
		return listaMezzi;
	}
	public String getIdCaserma() {
		return idCaserma;
	}
	public void setIdCaserma(String idCaserma) {
		this.idCaserma = idCaserma;
	}
	public int getKmEffettuati() {
		return kmEffettuati;
	}
	public void setKmEffettuati(int kmEffettuati) {
		this.kmEffettuati = kmEffettuati;
	}
	public int getCarburanteConsumato() {
		return carburanteConsumato;
	}
	public void setCarburanteConsumato(int carburanteConsumato) {
		this.carburanteConsumato = carburanteConsumato;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public LocalDateTime getDataOra() {
		return dataOra;
	}
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
	@Override
	public String toString() {
		return "Report [kmEffettuati=" + kmEffettuati + ", carburanteConsumato=" + carburanteConsumato
				+ ", descrizione=" + descrizione + ", tipo=" + tipo.toString() + ", dataOra=" + dataOra.toString()
				+ ", idCaserma=" + idCaserma + ", listaMezzi=" + listaMezzi + "]";
	}
	
}
