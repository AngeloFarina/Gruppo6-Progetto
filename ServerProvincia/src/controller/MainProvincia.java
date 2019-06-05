package controller;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import model.Caserma;
import model.Manutenzione;
import model.Mezzo;

public class MainProvincia {
	public static final int PORT = 1050;

	public static void main(String[] args) throws SQLException {
		GestioneMezziProvinciaController gestore = new GestioneMezziProvinciaController("bho", "bhobhobhbo", "bhobhobho");
		VisualizzaMezziProvinciaController visualizza = new VisualizzaMezziProvinciaController("","","");
		StoricoManutenzioniController storico = new StoricoManutenzioniController("", "", "");
		ServerSocket serverSocket = null;
	    Socket clientSocket = null;
	    String nomeServzio="";
		String idProvincia="";
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
				ObjectOutputStream outSock = null;
	    		try {
	    			// bloccante fino ad una pervenuta connessione
	    			clientSocket = serverSocket.accept();
	    			clientSocket.setSoTimeout(30000);
	    			System.out.println("ServerProvincia: connessione accettata: " + clientSocket);

	    		}
	    		catch (Exception e) {
	    			System.err.println("ServerProvincia: problemi nella accettazione della connessione: "+ e.getMessage());
	    			e.printStackTrace();
	    			continue;
	    		}
	    		//FUNZIONAMENTO DEL PROGRAMMA
				try {
					inSock = new DataInputStream(clientSocket.getInputStream());
					outSock = new ObjectOutputStream(clientSocket.getOutputStream());
					nomeServzio = inSock.readUTF();
					idProvincia = inSock.readUTF();
					
					if(nomeServzio == null || idProvincia == null)
						outSock.writeObject(null);
					
					if(nomeServzio.equals("visualizzaMezziCaserma")){
						List<Mezzo> result = visualizza.visualizzaMezzi(idProvincia);
						outSock.writeObject(result);
					}
					else if (nomeServzio.equals("visualizzaMezziProvincia")) {
						List<Caserma> result = visualizza.visualizzaMezziProvincia(idProvincia);
						outSock.writeObject(result);
					}
					else if(nomeServzio.equals("listaManutezioni")) {
						List<Manutenzione> result = storico.listaManutenzioni();
						outSock.writeObject(result);
					}
					else if(nomeServzio.equals("modificaMezzi")) {
						// LEGGERE TIPO RICHIESTA UN MEZZO E UNA CASERMA (SERVE SOLO ID)
						//gestore.modificaMezzo("tipoRichiesta", "m", "c");
						//outSock.writeObject(result);
					}
					else
						outSock.writeObject(null);
		        }
	    		
	    		catch (Exception e) {
	    			System.err.println("Server: problemi nel server thread: "
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
