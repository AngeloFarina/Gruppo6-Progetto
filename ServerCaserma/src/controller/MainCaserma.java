package controller;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Mezzo;
import model.Report;

public class MainCaserma {
	public static final int PORT = 1053;

	public static void main(String[] args) throws SQLException {
		GestioneMezziCasermaController gestore = new GestioneMezziCasermaController("bho", "bhobhobhbo", "bhobhobho");
		VisualizzaMezziCasermaController visualizza = new VisualizzaMezziCasermaController("","","");
		CreazioneReportController report = new CreazioneReportController("","","");
		ServerSocket serverSocket = null;
	    Socket clientSocket = null;
	    try {
	    	serverSocket = new ServerSocket(PORT);
	    	serverSocket.setReuseAddress(true);
	    	System.out.println("ServerCaserma: avviato ");
	    	System.out.println("ServerCaserma: creata la server socket: " + serverSocket);
	    }
	    catch (Exception e) {
	    	System.err.println("ServerCaserma: problemi nella creazione della server socket: "+ e.getMessage());
	    	e.printStackTrace();
	    	System.exit(1);
	    }
	    try {

	    	while (true) {
	    		System.out.println("ServerCaserma: in attesa di richieste...\n");
	    		DataInputStream inSock = null;
	    		ObjectInputStream inObj = null;
				ObjectOutputStream outSock = null;
	    		try {
	    			// bloccante fino ad una pervenuta connessione
	    			clientSocket = serverSocket.accept();
	    			//clientSocket.setSoTimeout(30000);
	    			System.out.println("ServerCaserma: connessione accettata: " + clientSocket);

	    		}
	    		catch (Exception e) {
	    			System.err.println("ServerCaserma: problemi nella accettazione della connessione: "+ e.getMessage());
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
					if(servizio.equals("mezziCasermaCaserma")) {
						System.out.println("Eseguo visualizzaMezziCaserma");
						String idCaserma = (String)param.get(0);
						List<Object> dati = new ArrayList<Object>();
						dati.add(visualizza.visualizzaMezzi(idCaserma));
						dati.add(getInformazioniCaserma(idCaserma));
						outSock.writeObject(dati);
					}
					else if(servizio.equals("richiestaSost"))
						outSock.writeObject(new ArrayList<Object>());
					else if(servizio.equals("report")) {
						report.creaReport((Report)param.get(0));
						outSock.writeObject("ok");
					}
					System.out.println("Mandate le informazioni");
		        }
	    		catch (Exception e) {
	    			System.err.println("ServerCaserma: problemi nel server thread: "
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
	    	System.out.println("ServerCaserma: termino...");
	    	System.exit(2);
	    }
	}

	private static List<String> getInformazioniCaserma(String idCaserma) {
		InformazioniCasermaController info = new InformazioniCasermaController("","","");
		return info.getInformazioniCaserma(idCaserma);
	}

}
