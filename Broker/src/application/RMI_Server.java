package application;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMI_Server extends UnicastRemoteObject implements
		RMI_interfaceFile {

	private static final long serialVersionUID = 1L;

	// Costruttore
	public RMI_Server() throws RemoteException {
		super();
	}

	// 	Richiede una prenotazione
	public int intero(int num) throws RemoteException {
		return num;
	}

	// Ritorno il campo
	public String str(String stringa) throws RemoteException {
		return stringa;
	}

	// Avvio del Server RMI
	public static void main(String[] args) {

		final int REGISTRYPORT = 1099;
		String registryHost = "localhost";
		String serviceName = "Server";		//lookup name...

		// Registrazione del servizio RMI
		String completeName = "//" + registryHost + ":" + REGISTRYPORT + "/"
				+ serviceName;
		try{
			RMI_interfaceFile serverRMI = new RMI_Server();
			
			Naming.rebind(completeName, serverRMI);
			System.out.println("Server RMI: Servizio \"" + serviceName
					+ "\" registrato");
		}
		catch(Exception e){
			System.err.println("Server RMI \"" + serviceName + "\": "
					+ e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}