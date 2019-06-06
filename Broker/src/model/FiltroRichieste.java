package model;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

public class FiltroRichieste {
	private static final int LOGINPORT = 1052;
	private static final int SERVERCASERMAPORT = 1053;
	private static final int SERVERPROVINCIAPORT = 1050;
	private GestoreSessione gestore;
	
	private Socket clientSocket = null;
	
	public FiltroRichieste() {
	}
	
	@SuppressWarnings("unchecked")
	public RichiestaServizio gestisci(RichiestaServizio r) {
		if(r==null)
			return null;
		gestore = GestoreSessione.getGestoreSessione();
		Sessione sessione;
		RichiestaServizio ric = r;
		String ipClient = r.getSorgente();
		if( (sessione = gestore.verificaSessione(ipClient))!=null) {
			if(sessione.getDataScadenza().isAfter(LocalDateTime.now()))
				gestore.eliminaSessione(ipClient);
			else
				return new RichiestaServizio("sessioneAttiva",null,null,null);
		}
		gestore.creaSessione(new Sessione("cliente"+r.getServizio(),LocalDateTime.now(),LocalDateTime.now().plusMinutes(30),ipClient));
		System.out.println("Sessione attivata e valida\nServizio: " + r.getServizio());
		try {
			int port;
			if( (port=getPort(r.getServizio()))>0) {
				clientSocket = new Socket("localhost",port);
				DataOutputStream outSock = new DataOutputStream(clientSocket.getOutputStream());
				outSock.writeUTF(r.getServizio());
				ObjectOutputStream outObj = new ObjectOutputStream(outSock);
				outObj.writeObject(r.getParametri());
				outSock.flush();
				outObj.flush();
				//clientSocket.shutdownOutput();
				//DataInputStream inSock = new DataInputStream(clientSocket.getInputStream());				
				//String a = inSock.readUTF();
				//String b = inSock.readUTF();
				//String c = inSock.readUTF();
				//System.out.println("Letti a b c");
				ObjectInputStream inObj = new ObjectInputStream(clientSocket.getInputStream());
				List<Object> d = (List<Object>)inObj.readObject();
				ric = new RichiestaServizio("localhost","localhost","clientLogin",d);
				//clientSocket.shutdownInput();
			}
		}catch(Exception e) {
			gestore.eliminaSessione(ipClient);
			e.printStackTrace();
			return null;
		}
		return ric;
	}

	private int getPort(String servizio) {
		if(servizio.equals("login"))
			return LOGINPORT;
		else if(servizio.equals("report") || servizio.equals("mezziCasermaCaserma") || servizio.equals("richiestaSost"))
			return SERVERCASERMAPORT;
		else if(servizio.equals("mezziCasermaProvincia") || servizio.equals("caricaSost") || servizio.equals("mezziProvincia") || servizio.equals("storicoManutenzioni") || servizio.equals("gestioneSost"))
			return SERVERPROVINCIAPORT;
		else
			return -1;
	}
	
	public GestoreSessione getGestore() {
		return this.gestore;
	}
	
}
