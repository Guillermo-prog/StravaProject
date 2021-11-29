package es.deusto.ingenieria.sd.strava.google.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILoginGoogle extends Remote {
	public boolean loginGoogle(String email, String password) throws RemoteException;
}
