package model;

public class SessioniAttive {
	private String chiave;
	private Sessione sessione;
	
	public SessioniAttive(String chiave, Sessione sessione) {
		this.sessione=sessione;
		this.chiave=chiave;
	}
	
	public String getChiave() {
		return this.chiave;
	}
	
	public Sessione getSessione() {
		return this.sessione;
	}
	
}
