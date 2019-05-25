package application;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.*;

public class RMI_Server extends UnicastRemoteObject implements
		RMI_interfaceFile {
	private static final long serialVersionUID = 1L;
	private static FiltroRichieste f;

	// Costruttore
	public RMI_Server() throws RemoteException {
		super();
		f = new FiltroRichieste();
	}
	
	@Override
	public RichiestaServizio richiedi(RichiestaServizio r) {
		return r;
	}
	
	
	// Avvio del Server RMI
	public static void main(String[] args) {

		final int REGISTRYPORT = 1099;
		String registryHost = "localhost";
		String serviceName = "Broker";		//lookup name...

		// Registrazione del servizio RMI
		String completeName = "//" + registryHost + ":" + REGISTRYPORT + "/"
				+ serviceName;
		try{
			RMI_interfaceFile serverRMI = new RMI_Server();
			Naming.rebind(completeName, serverRMI);
			System.out.println("Broker: Servizio \"" + serviceName
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