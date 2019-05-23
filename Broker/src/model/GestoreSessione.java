package model;

import java.util.ArrayList;
import java.util.List;

public class GestoreSessione {
	//Pattern Singleton
	private List<SessioniAttive> sessioniAttive = null;
	public static GestoreSessione istanza = null;
	private GestoreSessione() {
		sessioniAttive = new ArrayList<SessioniAttive>();
	}
	
	public static synchronized GestoreSessione getGestoreSessione() {
		if(istanza==null)
			istanza=new GestoreSessione();
		return istanza;
	}
	
	public String creaSessione(Sessione s) {
		return null;
	}
	
	public Sessione verificaSessione(String s) {
		return null;
	}
	
	public void eliminaSessione(String s) {
		
	}
	
}
