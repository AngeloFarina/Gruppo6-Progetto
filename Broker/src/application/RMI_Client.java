package application;

import java.rmi.Naming;


class RMI_Client {

	public static void main(String[] args) {
		final int REGISTRYPORT = 1099;
	    String registryHost = "localhost";
	    String serviceName = "Broker";
		
		// Connessione al servizio RMI remoto
		try{
			String completeName = "//" + registryHost + ":" + REGISTRYPORT + "/"
					+ serviceName;
			RMI_interfaceFile serverRMI = (RMI_interfaceFile) Naming.lookup(completeName);
  
			/*ciclo accettazione richieste utente*/
			while (true) {
				
			}
			
		}
		
		catch(Exception e){
			System.err.println("ClientRMI: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}