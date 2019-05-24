package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
//import java.rmi.NotBoundException;


class RMI_Client {

	public static void main(String[] args) {
		final int REGISTRYPORT = 1099;
	    String registryHost = null;					//host remoto con registry
	    String serviceName = "Server";	
	    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	
		// Controllo dei parametri della riga di comando
		if(args.length != 1){
			System.out.println("Sintassi: RMI_Registry_IP");
			System.exit(1);
		}
		registryHost = args[0];
		
		System.out.println("Invio richieste a "+registryHost+" per il servizio di nome "+serviceName);
		
		// Connessione al servizio RMI remoto
		try{
			String completeName = "//" + registryHost + ":" + REGISTRYPORT + "/"
					+ serviceName;
			RMI_interfaceFile serverRMI = (RMI_interfaceFile) Naming.lookup(completeName);
			System.out.println("ClientRMI: Servizio \"" + serviceName + "\" connesso");

			System.out.println("\nRichieste di servizio fino a fine file");

			String service;
			System.out.print("Servizio (I=invia intero, S=invia stringa): ");
  
			/*ciclo accettazione richieste utente*/
			while ((service = stdIn.readLine()) != null){
		
				if(service.equalsIgnoreCase("I")){
					int num;
					
					System.out.print("Inserisci intero: ");
					num = Integer.parseInt(stdIn.readLine());

					System.out.println("Invio "+num+" il Server");
					System.out.println("Ricevuto: "+serverRMI.intero(num));
					
				} // I=intero
				
				else if(service.equalsIgnoreCase("S")){
					String stringa;
					
					System.out.print("Inserisci stringa: ");
					stringa=stdIn.readLine();
					
					System.out.println("Invio "+stringa+" il Server");
					System.out.println("Ricevuto: "+serverRMI.str(stringa));
					
				} // S=stringa
				
				else System.out.println("Servizio non disponibile");
				
				System.out.print("Servizio (I=invia intero, S=invia stringa): ");
			} // while (!EOF), fine richieste utente
			
		}
		/*catch(NotBoundException nbe){
			System.err.println("ClientRMI: il nome fornito non risulta registrato; " + nbe.getMessage());
			//nbe.printStackTrace();
			System.exit(1);
		}//da uutilizzare se ho una "struttura dati"*/ 
		catch(Exception e){
			System.err.println("ClientRMI: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}