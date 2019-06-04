package controller;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
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
					List<String> param = (List<String>)inObj.readObject();
					String idCaserma = param.get(0);
					if(servizio.equals("mezziCasermaCaserma"))
						outSock.writeObject(visualizza.visualizzaMezzi(idCaserma));
					else if(servizio.equals("richiestaSost"))
						outSock.writeObject(new ArrayList<Object>());
					else if(servizio.equals("report"))
						outSock.writeObject("litri cisterna");
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

}
