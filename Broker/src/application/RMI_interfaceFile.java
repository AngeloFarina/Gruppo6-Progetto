package application;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_interfaceFile extends Remote {

	int intero(int num) throws RemoteException;

	String str(String stringa) throws RemoteException;

}