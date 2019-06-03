package application;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.*;

	//Accetta richieste dai client e le spartisce al server giusto
	//Riceve le risposte dai server e le restituisce al client corrispondente
	
	
	class Server_Thread extends Thread {
	    private Socket clientSocket = null;

	    public Server_Thread(Socket clientSocket) {
	        this.clientSocket = clientSocket;
	    }

	    public void run() {
	        System.out.println("Attivazione figlio: "+ Thread.currentThread().getName());

	        DataInputStream inSock;
	        DataOutputStream outSock;

	        try {
	            inSock = new DataInputStream(clientSocket.getInputStream());
	            outSock = new DataOutputStream(clientSocket.getOutputStream());
	        } catch (IOException ioe) {
	            System.out.println("Problemi nella creazione degli stream di input/output su socket: ");
	            ioe.printStackTrace();
	            return;
	        }
	        try {
	            try {
	            	String stringa;

	                while ((stringa = inSock.readUTF()) != null) {
	                	//Il controllo sull'immissione dell'operazione lo ha già fatto il client, non ripeto qui
	                	if(stringa.equals("1")){
	                    		outSock.writeUTF(stringa);
	                    	}
	                    	else{
	                    		outSock.writeUTF(stringa);
	                    	}
	                } // while
	                
	            }catch (EOFException eof) {
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
	        }catch (IOException ioe) {
	            System.out.println("Problemi nella chiusura della socket: ");
	            ioe.printStackTrace();
	            System.out.println("Chiudo ed esco...");
	            System.exit(3);
	        }
	    }

	}//thread


	public class Broker {
		private static final int PORT=1050;
		
		private Map<String,String> servizi = new HashMap<String,String>();
		
		
		public void registerService(String ip,String servizio) {
			servizi.put(servizio,ip);
		}
		
		public void ack(String a) {
			
		}
		
		public void inviaRichiesta() {
			
		}
		
		public void inviaRisposta() {
			
		}

	    public static void main(String[] args) throws IOException {
	        int port = -1;

	        try {
	            if (args.length == 1) {
	                port = Integer.parseInt(args[0]);
	                // controllo che la porta sia nel range consentito 1024-65535
	                if (port < 1024 || port > 65535) {
	                    System.out.println("Usage: java Server_Stream [serverPort>1024]");
	                    System.exit(1);
	                }
	            } else {
	                System.out.println("Usage: java Server_Stream port");
	                System.exit(1);
	            }
	        }
	        catch (Exception e) {
	            System.out.println("Problemi, i seguenti: ");
	            e.printStackTrace();
	            System.out.println("Usage: java Server_Stream port");
	            System.exit(1);
	        }

	        ServerSocket serverSocket = null;
	        Socket clientSocket = null;

	        try {
	            serverSocket = new ServerSocket(port);
	            serverSocket.setReuseAddress(true);
	            System.out.println("Server_Stream: avviato ");
	            System.out.println("Server: creata la server socket: " + serverSocket);
	        } catch (Exception e) {
	            System.err
	                    .println("Server: problemi nella creazione della server socket: "+ e.getMessage());
	            e.printStackTrace();
	            serverSocket.close();
	            System.exit(1);
	        }

	        try {
	            while (true) {
	                System.out.println("Server: in attesa di richieste...\n");

	                try {
	                    clientSocket = serverSocket.accept(); //bloccante!!!
	                    System.out.println("Server: connessione accettata: " + clientSocket);
	                } catch (Exception e) {
	                    System.err
	                            .println("Server: problemi nella accettazione della connessione: "
	                                    + e.getMessage());
	                    e.printStackTrace();
	                    continue;
	                }

	                try {
	                    new Server_Thread(clientSocket).start();
	                } catch (Exception e) {
	                    System.err.println("Server: problemi nel server thread: "
	                            + e.getMessage());
	                    e.printStackTrace();
	                    continue;
	                }
	            }// while true
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Server: termino...");
	            System.exit(2);
	        }
	    }
	}

