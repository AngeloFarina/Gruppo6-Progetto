package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainLogin {
	public static final int PORT = 1052;

	public static void main(String[] args) {
		LoginController gestore = new LoginController("bho", "bhobhobhbo", "bhobhobho");
		ServerSocket serverSocket = null;
	    Socket clientSocket = null;
	    DataOutputStream outSock;
	    DataInputStream inSock;
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
	    		
	    		try {
	    			inSock = new DataInputStream(clientSocket.getInputStream());
	    			outSock = new DataOutputStream(clientSocket.getOutputStream());
	    			String credenziali = inSock.readUTF();
	    			outSock.writeUTF(gestore.verificaCredenziali(credenziali.split(";")[0],credenziali.split(";")[1]));
	    			System.out.println("BANANE A VOLONTA'");
	    		}
	    		catch (Exception e) {
	    			System.err.println("Server: problemi nel server thread: " + e.getMessage());
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
