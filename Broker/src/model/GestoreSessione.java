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
		SessioniAttive sa = new SessioniAttive(s.getIpClient(),s);
		this.sessioniAttive.add(sa);
		return s.getIpClient();
	}
	

	public Sessione verificaSessione(String s) {
		for(SessioniAttive sa : sessioniAttive) {
			if(sa.getChiave().equals(s))
				return sa.getSessione();
		}
		return null;
	}
	
	public void eliminaSessione(String s) {
		
	}
	
}
