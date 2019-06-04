package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainLogin {
	public static final int PORT = 1052;

	public static void main(String[] args) {
		LoginController gestore = new LoginController("bho", "bhobhobhbo", "bhobhobho");
		ServerSocket serverSocket = null;
	    Socket clientSocket = null;
	    DataOutputStream outSock;
	    DataInputStream inSock;
	    ObjectOutputStream outObj;
	    ObjectInputStream inObj;
	    try {
	    	serverSocket = new ServerSocket(PORT);
	    	serverSocket.setReuseAddress(true);
	    	System.out.println("ServerLogin: avviato ");
	    	System.out.println("ServerLogin: creata la server socket: " + serverSocket);
	    }
	    catch (Exception e) {
	    	System.err.println("ServerLogin: problemi nella creazione della server socket: "+ e.getMessage());
	    	e.printStackTrace();
	    	System.exit(1);
	    }
	    try {

	    	while (true) {
	    		System.out.println("ServerLogin: in attesa di richieste...\n");

	    		try {
	    			// bloccante fino ad una pervenuta connessione
	    			clientSocket = serverSocket.accept();
	    			//clientSocket.setSoTimeout(30000);
	    			System.out.println("ServerLogin: connessione accettata: " + clientSocket);
	    		}
	    		catch (Exception e) {
	    			System.err.println("ServerLogin: problemi nella accettazione della connessione: "+ e.getMessage());
	    			e.printStackTrace();
	    			continue;
	    		}
	    		
	    		try {
	    			outObj = new ObjectOutputStream(clientSocket.getOutputStream());
	    			outSock = new DataOutputStream(clientSocket.getOutputStream());
	    			inSock = new DataInputStream(clientSocket.getInputStream());
	    			System.out.println("Mi aspetto di leggere dal FiltroRichiesta [IP="+clientSocket.getRemoteSocketAddress()+",PORT="+clientSocket.getLocalPort());
	    			System.out.println("Leggo il servizio " +System.currentTimeMillis());
	    			String servizio = inSock.readUTF();
	    			System.out.println("Leggo i parametri " +System.currentTimeMillis());
	    			inObj = new ObjectInputStream(inSock);
	    			List<String> param = (List<String>) inObj.readObject();
	    			//clientSocket.shutdownInput();
	    			//outSock.writeUTF(clientSocket.getLocalAddress().getHostAddress());
	    			//outSock.writeUTF("localhost");
	    			//outSock.writeUTF("clientLogin");
	    			//outSock.flush();
	    			System.out.println("Aggiungo parametri " + gestore.verificaCredenziali(param.get(0),param.get(1)).split(";"));
	    			param.add(gestore.verificaCredenziali(param.get(0),param.get(1)).split(";")[0]);
	    			param.add(gestore.verificaCredenziali(param.get(0),param.get(1)).split(";")[1]);
	    			param.add(gestore.verificaCredenziali(param.get(0),param.get(1)).split(";")[2]);
	    			outObj.writeObject(param);
	    			outObj.flush();
	    			System.out.println("Fatto, serverLogin");
	    		}
	    		catch (Exception e) {
	    			System.err.println("ServerLogin: problemi nel server thread: " + e.getMessage());
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
	    	System.out.println("ServerLogin: termino...");
	    	System.exit(2);
	    }
	}

}
