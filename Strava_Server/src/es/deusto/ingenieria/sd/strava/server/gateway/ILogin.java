package es.deusto.ingenieria.sd.strava.server.gateway;

import java.rmi.RemoteException;

public interface ILogin {
	

	public boolean login(String email, String password) throws RemoteException;
	
}
