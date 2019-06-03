package controller;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import model.Mezzo;

public class MainCaserma {
	public static final int PORT = 1053;

	public static void main(String[] args) throws SQLException {
		GestioneMezziCasermaController gestore = new GestioneMezziCasermaController("bho", "bhobhobhbo", "bhobhobho");
		VisualizzaMezziCasermaController visualizza = new VisualizzaMezziCasermaController("","","");
		ServerSocket serverSocket = null;
	    Socket clientSocket = null;
		String idProvincia="";
	    try {
	    	serverSocket = new ServerSocket(PORT);
	    	serverSocket.setReuseAddress(true);
	    	System.out.println("PutFileServerCon: avviato ");
	    	System.out.println("Server: creata la server socket: " + serverSocket);
	    }
	    catch (Exception e) {
	    	System.err.println("Server: problemi nella creazione della server socket: "+ e.getMessage());
	    	e.printStackTrace();
	    	System.exit(1);
	    }
	    try {

	    	while (true) {
	    		System.out.println("Server: in attesa di richieste...\n");
	    		DataInputStream inSock = null;
				ObjectOutputStream outSock = null;
	    		try {
	    			// bloccante fino ad una pervenuta connessione
	    			clientSocket = serverSocket.accept();
	    			clientSocket.setSoTimeout(30000);
	    			System.out.println("Server: connessione accettata: " + clientSocket);

	    		}
	    		catch (Exception e) {
	    			System.err.println("Server: problemi nella accettazione della connessione: "+ e.getMessage());
	    			e.printStackTrace();
	    			continue;
	    		}
	    		//FUNZIONAMENTO DEL PROGRAMMA
				try {
					inSock = new DataInputStream(clientSocket.getInputStream());
					outSock = new ObjectOutputStream(clientSocket.getOutputStream());
					idProvincia = inSock.readUTF();
					
					if(idProvincia == null)
						outSock.writeObject(null);
				
					List<Mezzo> result = visualizza.visualizzaMezzi(idProvincia);
					outSock.writeObject(result);
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
	    	System.out.println("PutFileServerCon: termino...");
	    	System.exit(2);
	    }
	}

}
