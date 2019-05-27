package model;

public class Mezzo {
	private String id;
	private String tipo;
	private int anno;
	private String marca;
	private String modello;
	private Stato stato;
	
	
	public Mezzo(String id, String tipo, int anno, String marca, String modello, Stato stato) {
		this.id = id;
		this.tipo = tipo;
		this.anno = anno;
		this.marca = marca;
		this.modello = modello;
		this.stato=stato;
	}
	
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getTipo() {
		return tipo;
	}
	public final void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public final int getAnno() {
		return anno;
	}
	public final void setAnno(int anno) {
		this.anno = anno;
	}
	public final String getMarca() {
		return marca;
	}
	public final void setMarca(String marca) {
		this.marca = marca;
	}
	public final String getModello() {
		return modello;
	}
	public final void setModello(String modello) {
		this.modello = modello;
	}
	
	public final Stato getStato() {
		return stato;
	}

	public final void setStato(Stato stato) {
		this.stato = stato;
	}
	
	@Override
	public String toString() {
		return "ID = " + id + " tipo = " + tipo + " anno = " + anno + " marca = " + marca +" modello = " + modello;
	}

	
	
	
}
