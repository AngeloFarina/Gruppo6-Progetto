package application;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.FiltroRichieste;
import model.GestoreSessione;
import model.RichiestaServizio;
import model.Sessione;

//Accetta richieste dai client e le spartisce al server giusto
//Riceve le risposte dai server e le restituisce al client corrispondente

class Server_Thread extends Thread {
	private Socket clientSocket = null;
	private Socket server = null;
	private String ip = "localhost";
	private RichiestaServizio richiesta;
	private Map<String,String> sessioni;
	private FiltroRichieste filtroRichieste;

	public Server_Thread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		filtroRichieste = new FiltroRichieste();
	}

	public void run() {
		System.out.println("Attivazione figlio: " + Thread.currentThread().getName());

		DataInputStream inSock;
		ObjectInputStream inObj;
		ObjectOutputStream outObject;

		try {
			outObject = new ObjectOutputStream(clientSocket.getOutputStream());
			inSock = new DataInputStream(clientSocket.getInputStream());
			inObj = new ObjectInputStream(inSock);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		try {
			try {
				//Pattern di invio: ipSorgente, ipDestinarario, servizio, List<Object> parametri
				String ipSorg,ipDest,servizio;
				List<Object> params = null;
				System.out.println("Inizio ad eseguire");
				ipSorg = inSock.readUTF();
				System.out.println("Ho letto ipSorgente" + ipSorg);
					/*if (stringa.equals("login")) {
						String credenziali = inSock.readUTF();
						System.out.println("Ho letto credenziali " + credenziali);
						server = new Socket(ip, 1052);
						server.setSoTimeout(30000);
						DataInputStream inServer;
						DataOutputStream outServer;
						inServer = new DataInputStream(server.getInputStream());
						outServer = new DataOutputStream(server.getOutputStream());
						outServer.writeUTF(credenziali);
						String utente = inServer.readUTF();
						outSock.writeUTF(utente);
						System.out.println("FINITO " + utente);
					}*/
				ipDest = inSock.readUTF();
				System.out.println("Letto ipDest " + ipDest);
				servizio = inSock.readUTF();
				params = new ArrayList<Object>((List<Object>)inObj.readObject());
				richiesta = new RichiestaServizio(ipSorg,ipDest,servizio,params);
				richiesta = filtroRichieste.gestisci(richiesta);
				if(richiesta==null) {
					System.out.println("Errore gestione richiesta");
					clientSocket.close();
					System.exit(1);
				}
				System.out.println("Letti parametri: " + richiesta.getParametri());
				outObject.writeObject(richiesta.getParametri());
				outObject.flush();
			} catch (EOFException eof) {
				System.out.println("Raggiunta la fine delle ricezioni, chiudo...");
				clientSocket.close();
				System.out.println("Server_Thread: termino...");
				System.exit(0);
			} catch (SocketTimeoutException ste) {
				System.out.println("Timeout scattato: ");
				ste.printStackTrace();
				clientSocket.close();
				System.exit(1);
			} catch (Exception e) {
				System.out.println("Problemi, i seguenti : ");
				e.printStackTrace();
				System.out.println("Chiudo ed esco...");
				clientSocket.close();
				System.exit(2);
			}
		} catch (IOException ioe) {
			System.out.println("Problemi nella chiusura della socket: ");
			ioe.printStackTrace();
			System.out.println("Chiudo ed esco...");
			System.exit(3);
		}
		System.out.println("Thread: chiudo...");
	}

}// thread

public class Broker {
	private static final int PORT = 1051;
	private RichiestaServizio richiesta;

	public void ack(String a) {

	}

	public void inviaRichiesta() {

	}

	public void inviaRisposta() {

	}

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		Socket clientSocket = null;

		try {
			serverSocket = new ServerSocket(PORT);
			serverSocket.setReuseAddress(true);
			System.out.println("Broker: avviato ");
			System.out.println("Broker: creata la server socket: " + serverSocket);
		} catch (Exception e) {
			System.err.println("Broker: problemi nella creazione della server socket: " + e.getMessage());
			e.printStackTrace();
			serverSocket.close();
			System.exit(1);
		}

		try {
			while (true) {
				System.out.println("Server: in attesa di richieste...\n");

				try {
					clientSocket = serverSocket.accept(); // bloccante!!!
					System.out.println("Broker: connessione accettata: " + clientSocket);
					System.out.println("Broker: Verifico la sessione...");
				} catch (Exception e) {
					System.err.println("Broker: problemi nella accettazione della connessione: " + e.getMessage());
					e.printStackTrace();
					continue;
				}

				try {
					new Server_Thread(clientSocket).start();
				} catch (Exception e) {
					System.err.println("Broker: problemi nel server thread: " + e.getMessage());
					e.printStackTrace();
					continue;
				}
			} // while true
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Broker: termino...");
			System.exit(2);
		}
	}
}
