package controller;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Caserma;
import model.Manutenzione;
import model.Mezzo;
import model.RichiestaSostituzione;
import model.TabellaAmministratore;

public class MainProvincia {
	public static final int PORT = 1050;

	public static void main(String[] args) throws SQLException {
		GestioneMezziProvinciaController gestore = new GestioneMezziProvinciaController("bho", "bhobhobhbo", "bhobhobho");
		VisualizzaMezziProvinciaController visualizza = new VisualizzaMezziProvinciaController("","","");
		StoricoManutenzioniController storico = new StoricoManutenzioniController("", "", "");
		ControllerCaricamentoRichiesteSostituzione caricaSostituzioni = new ControllerCaricamentoRichiesteSostituzione("","","");
		ServerSocket serverSocket = null;
	    Socket clientSocket = null;
	    try {
	    	serverSocket = new ServerSocket(PORT);
	    	serverSocket.setReuseAddress(true);
	    	System.out.println("ServerProvincia: avviato ");
	    	System.out.println("ServerProvincia: creata la server socket: " + serverSocket);
	    }
	    catch (Exception e) {
	    	System.err.println("ServerProvincia: problemi nella creazione della server socket: "+ e.getMessage());
	    	e.printStackTrace();
	    	System.exit(1);
	    }
	    try {

	    	while (true) {
	    		System.out.println("ServerProvincia: in attesa di richieste...\n");
	    		DataInputStream inSock = null;
	    		ObjectInputStream inObj = null;
				ObjectOutputStream outSock = null;
	    		try {
	    			// bloccante fino ad una pervenuta connessione
	    			clientSocket = serverSocket.accept();
	    			//clientSocket.setSoTimeout(30000);
	    			System.out.println("ServerProvincia: connessione accettata: " + clientSocket);

	    		}
	    		catch (Exception e) {
	    			System.err.println("ServerProvincia: problemi nella accettazione della connessione: "+ e.getMessage());
	    			e.printStackTrace();
	    			continue;
	    		}
	    		//FUNZIONAMENTO DEL PROGRAMMA
				try {
					outSock = new ObjectOutputStream(clientSocket.getOutputStream());
					inSock = new DataInputStream(clientSocket.getInputStream());
					String servizio = inSock.readUTF();
					inObj = new ObjectInputStream(clientSocket.getInputStream());
					List<Object> param = (List<Object>)inObj.readObject();
					System.out.println("Guardo che richiesta di servizio ho: " + servizio);
					
					if(servizio == null)
						outSock.writeObject(null);
					
					if(servizio.equals("mezziCasermaProvincia")){
						String idCaserma = (String)param.get(0);
						List<Mezzo> result = visualizza.visualizzaMezzi(idCaserma);
						outSock.writeObject(result);
					}
					else if (servizio.equals("mezziProvincia")) {
						String idCaserma = (String)param.get(0);
						List<Caserma> result = visualizza.visualizzaMezziProvincia(idCaserma);
						outSock.writeObject(result);
					}
					else if(servizio.equals("storicoManutenzioni")) {
						List<Manutenzione> result = storico.listaManutenzioni();
						outSock.writeObject(result);
					}
					else if(servizio.equals("modificaMezzi")) {
						List<Object> result = new ArrayList<Object>();
						TabellaAmministratore tab = (TabellaAmministratore)param.get(0);
						Mezzo m = (Mezzo)param.get(1);
						if(tab.getTarga()==null && m.getMarca()!=null)
							if(!gestore.modificaMezzo("aggiungi", m, tab.getIdCaserma().split(" ")[0]))
								result.add("warning");
						if(tab.getTarga()!=null && m.getMarca()==null)
							if(!gestore.modificaMezzo("modifica", m,tab.getIdCaserma().split(" ")[0]))
								result.add("warning");
						if(tab.getTarga()==null && m.getMarca()==null)
							if(!gestore.modificaMezzo("elimina", m, ""))
								result.add("warning");
						result.add("ok");
					}
					else if(servizio.equals("caricaSost")) {
						String idProvincia = (String)param.get(0);
						List<RichiestaSostituzione> richieste = caricaSostituzioni.caricaRichieste(idProvincia);
						outSock.writeObject(richieste);
					}
					else
						outSock.writeObject(null);
		        }
	    		
	    		catch (Exception e) {
	    			System.err.println("ServerProvincia: problemi nel server thread: "
	    					+ e.getMessage());
	    			e.printStackTrace();
	    			continue;
	    		}

	    	} // while
	    }
	    // qui catturo le eccezioni non catturate all'interno del while
	    // in seguito alle quali il server termina l'esecuzione
	    catch (Exception e) {
	    	e.printStackTrace();
	    	// chiusura di stream e socket
	    	System.out.println("ServerProvincia: termino...");
	    	System.exit(2);
	    }
	}
}
