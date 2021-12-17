package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.dto.ActivityDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;


//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {

	public void login(String email, String password, String type);
	
	public void logout(long token) throws RemoteException;

	public long registration(String email, String password, String nickname, String birthdate, int weigth, int heigth,
			int maxrate, int minRate) throws RemoteException;


	public boolean createActivity(String title, String sport, Float km, String date, String startTime, int duration) throws RemoteException;

	public List<ChallengeDTO> getChallenges() throws RemoteException;

	public List<ActivityDTO> getActivities() throws RemoteException;

	public boolean createChallenge(String title, String sport, String start, String end, Float targetDistance, int targetTime ) throws RemoteException;

	public boolean acceptChallenge(long token, String title) throws RemoteException;
	


}