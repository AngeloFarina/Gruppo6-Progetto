package application;

import java.io.*;
import java.net.*;

//Accetta richieste dai client e le spartisce al server giusto
//Riceve le risposte dai server e le restituisce al client corrispondente

class Server_Thread extends Thread {
	private Socket clientSocket = null;
	private Socket server = null;
	private String ip = "localhost";

	public Server_Thread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		System.out.println("Attivazione figlio: " + Thread.currentThread().getName());

		DataInputStream inSock;
		DataOutputStream outSock;

		try {
			inSock = new DataInputStream(clientSocket.getInputStream());
			outSock = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		try {
			try {
				String stringa = "prova";
				System.out.println("Inizio ad eseguire");
				while ((stringa = inSock.readUTF()) != null) {
					System.out.println("Ho letto " + stringa);
					if (stringa.equals("login")) {
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
					}
				} // while
				System.out.println("Ecco la stringa: " + stringa);

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
	}

}// thread

public class Broker {
	private static final int PORT = 1051;

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
			System.out.println("Server_Stream: avviato ");
			System.out.println("Server: creata la server socket: " + serverSocket);
		} catch (Exception e) {
			System.err.println("Server: problemi nella creazione della server socket: " + e.getMessage());
			e.printStackTrace();
			serverSocket.close();
			System.exit(1);
		}

		try {
			while (true) {
				System.out.println("Server: in attesa di richieste...\n");

				try {
					clientSocket = serverSocket.accept(); // bloccante!!!
					// ack
					System.out.println("Server: connessione accettata: " + clientSocket);
				} catch (Exception e) {
					System.err.println("Server: problemi nella accettazione della connessione: " + e.getMessage());
					e.printStackTrace();
					continue;
				}

				try {
					new Server_Thread(clientSocket).start();
				} catch (Exception e) {
					System.err.println("Server: problemi nel server thread: " + e.getMessage());
					e.printStackTrace();
					continue;
				}
			} // while true
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Server: termino...");
			System.exit(2);
		}
	}
}
