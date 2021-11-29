package es.deusto.ingenieria.sd.strava.server.gateway;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.gateway.FacebookServiceGateway;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.data.dto.ActivityDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;


public interface ILogin {
	
//	public boolean checkUser(String email, String password); 
//	public boolean loginGoogle(String email, String password) throws RemoteException;
	public boolean login(String email, String password) throws RemoteException;
	
	
	
}
