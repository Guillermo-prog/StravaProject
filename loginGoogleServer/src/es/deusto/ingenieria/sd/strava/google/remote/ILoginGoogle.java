package es.deusto.ingenieria.sd.strava.google.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILoginGoogle extends Remote {
	public User loginGoogle(String email, String password) throws RemoteException;
}
