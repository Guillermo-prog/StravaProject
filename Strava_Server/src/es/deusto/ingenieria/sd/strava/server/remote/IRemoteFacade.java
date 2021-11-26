package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.data.dto.ActivityDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;


//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {

	public long login(String email, String password) throws RemoteException;
	
	//Kommentera tillbaka
	
	//public long loginGoogle(String email, String password) throws RemoteException;


	public void logout(long token) throws RemoteException;

	public long registration(String email, String password, String nickname, String birthdate, int weigth, int heigth,
			int maxrate, int minRate) throws RemoteException;


	public boolean startActivity() throws RemoteException;

	// public boolean endActivity(String title, String sport, float km, Date
	// startDate, Time startTime, float duration ) throws RemoteException;

	public List<ChallengeDTO> getChallenges() throws RemoteException;

	public List<ActivityDTO> getActivities() throws RemoteException;

	public boolean createChallenge(String title, String sport, String start, String end, Float targetDistance, int targetTime ) throws RemoteException;

	public boolean acceptChallenge(long token, String title) throws RemoteException;
	


}