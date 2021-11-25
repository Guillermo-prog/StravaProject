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
	
	public void checkUser(String email, String password); 
	public void checkUserGoogle(String email, String password); 

	//public long login(String email, String password) throws RemoteException; //update
	public void test();


}