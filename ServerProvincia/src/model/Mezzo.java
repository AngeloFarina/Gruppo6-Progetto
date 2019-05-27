package model;

public class Mezzo {
	private String id;
	private String tipo;
	private int anno;
	private String marca;
	private String modello;
	
	public Mezzo(String id, String tipo, int anno, String marca, String modello) {
		this.id = id;
		this.tipo = tipo;
		this.anno = anno;
		this.marca = marca;
		this.modello = modello;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}
	
	
}
