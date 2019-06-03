package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		SessioniAttive sa = new SessioniAttive(generaChiave(),s);
		this.sessioniAttive.add(sa);
		return sa.getChiave();
	}
	
	private String generaChiave() {
		Random rand = new Random();
		StringBuffer tempStr = new StringBuffer();
		tempStr.append("");
		for (int i = 0; i < 20; i++) {
		int c = rand.nextInt(122 - 48) + 48;
		if((c >= 58 && c <= 64) || (c >= 91 && c <= 96)){
		i--;
		continue;
		}
		tempStr.append((char)c);
		}
		for(SessioniAttive sa : sessioniAttive) {
			if(sa.getChiave().equals(tempStr.toString()))
				return generaChiave();
		}
		return tempStr.toString();
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
