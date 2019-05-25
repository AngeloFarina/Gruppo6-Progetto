package application;
import java.rmi.Remote;
import java.rmi.RemoteException;

import model.RichiestaServizio;

public interface RMI_interfaceFile extends Remote {

	public RichiestaServizio richiedi(RichiestaServizio r);

}