
package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeAssembler;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.domain.Activity;
import es.deusto.ingenieria.sd.strava.server.data.dto.ActivityAssembler;
import es.deusto.ingenieria.sd.strava.server.data.dto.ActivityDTO;
import es.deusto.ingenieria.sd.strava.server.services.TrainingAppService;
import es.deusto.ingenieria.sd.strava.server.services.LoginAppService;

//TODO add all the methods that we have on the class diagram

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	public Map<Long, User> serverState = new HashMap<>();

	public RemoteFacade() throws RemoteException {
		super();		
	}
	
	@Override

	public synchronized long login(String email, String password, String type) throws RemoteException {
		long token = -1;
		User user = LoginAppService.getInstance().login(email, password, type);
		if (user != null) {
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				token = Calendar.getInstance().getTimeInMillis();		
				this.serverState.put(token, user);		

			} else {
				throw new RemoteException("User is already logged in!");
			}
		} else {
			throw new RemoteException("Login fails!");
		}
		return token;
	}
	
	
	@Override
	public synchronized void logout(long token) throws RemoteException {
		System.out.println(" * RemoteFacade logout: " + token);
		
		if (this.serverState.containsKey(token)) {
			
			this.serverState.remove(token);
		} else {
			throw new RemoteException("User is not not logged in!");
		}
	}
	
	
	@Override
	public List<ChallengeDTO> getChallenges() throws RemoteException {
		
		List<Challenge> challenges = TrainingAppService.getInstance().getChallenges();
		
		if (challenges != null) {
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("getChallenges() fails!");
		}
		//return null;
	}
	
	@Override
	public List<ActivityDTO> getActivities(long token) throws RemoteException {

		List<Activity> activities = TrainingAppService.getInstance().getActivities(this.serverState.get(token));
		
		if (activities != null) {
			
			return ActivityAssembler.getInstance().activityToDTO(activities);
		} else {
			throw new RemoteException("getActivities() fails!");
		}
	//	return null;
	}
	
	@Override
	public boolean acceptChallenge(long token, String title) throws RemoteException {		
		
		if (this.serverState.containsKey(token)) {						
			//Make the bid using Bid Application Service
			if (TrainingAppService.getInstance().acceptChallenge(this.serverState.get(token), title)) {
				return true;
			} else {
				throw new RemoteException("acceptChallenge() fails!");
			}
		} else {
			throw new RemoteException("To accept a challenge you must first log in");
		}
	}
	
	@Override
	public boolean createActivity(long token, String title, String sport, Float km, String date, String startTime, int duration) throws RemoteException {	
		
		if (this.serverState.containsKey(token)) {						
			//Make the bid using Bid Application Service
			System.out.println("Usern från staten är: " + this.serverState.get(token));
			if (TrainingAppService.getInstance().createActivity(this.serverState.get(token), title, sport, km, date, startTime, duration)) {
				return true;
			} else {
				throw new RemoteException("createActivity fails!");
			}
		} else {
			throw new RemoteException("To create an activity you must first log in");
		}

	}
	
	
	@Override
	public boolean createChallenge(String title, String sport, String start, String end, Float targetDistance, int targetTime ) throws RemoteException {	
		boolean challengeCreatedStatus = TrainingAppService.getInstance().createChallenge(title, sport, start, end, targetDistance, targetTime);
		return challengeCreatedStatus;
		
	}
	
	@Override
	public boolean registration(String type, String email, String password, String nickname,String birthdate, int weigth, int heigth, int maxrate,
			int minRate) throws RemoteException {
		User user = LoginAppService.getInstance().registration(type, email, password,nickname,birthdate,weigth, heigth, maxrate,
				minRate);
		if (user != null) {
			System.out.println("Returneras en user");
			return true;
		}
		else {
			System.out.println("Eller returneras null");
			return false;
		}
	
//		if (user != null) {
//			// If user is not logged in
//			if (!this.serverState.values().contains(user)) {
//				Long token = Calendar.getInstance().getTimeInMillis();
//				this.serverState.put(token, user);
//				System.out.println("token i remote reg" + token);
//				return (token);
//			} else {
//				throw new RemoteException("User is already registered in!");
//			}
//		} else {
//			throw new RemoteException("Register fails!");
//		}

	}
	
}