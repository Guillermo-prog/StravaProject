
package es.deusto.ingenieria.sd.strava.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import es.deusto.ingenieria.sd.strava.server.data.domain.Article;
//import es.deusto.ingenieria.sd.strava.server.data.domain.Category;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
//import es.deusto.ingenieria.sd.strava.server.data.dto.ArticleAssembler;
//import es.deusto.ingenieria.sd.strava.server.data.dto.ArticleDTO;
//import es.deusto.ingenieria.sd.strava.server.data.dto.CategoryAssembler;
//import es.deusto.ingenieria.sd.strava.server.data.dto.CategoryDTO;
//import es.deusto.ingenieria.sd.strava.server.services.BidAppService;
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
	public synchronized long login(String email, String password, String type){
		if(type.equals("normal")) {
			try {
				return loginNormal(email, password);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}else if(type.equals("Facebook")) {
			try {
				return loginFacebook(email, password);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}else if(type.equals("Google")) {
			try {
				return loginGoogle(email, password);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	
	public synchronized long loginNormal(String email, String password)throws RemoteException{

		//Perform login() using LoginAppService
		User user = LoginAppService.getInstance().login(email, password);
		
		//If login() success user is stored in the Server State
		if (user != null) {
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();		
				this.serverState.put(token, user);		
				return(token);
			} else {
				throw new RemoteException("User is already logged in!");
			}
		} else {
			throw new RemoteException("Login fails!");
		}
	
	}
	public synchronized long loginFacebook(String email, String password)throws RemoteException{
		
		//Perform login() using LoginAppService
		User user = LoginAppService.getInstance().loginFacebook(email, password);

		//If login() success user is stored in the Server State
		if (user != null) {
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();		
				this.serverState.put(token, user);		
				return(token);
			} else {
				throw new RemoteException("User is already logged in Facebook!");
			}
		} else {
			throw new RemoteException("Login on Facebook fails!");
		}
	}
	
	
	public synchronized long loginGoogle(String email, String password) throws RemoteException{
		
		//Perform login() using LoginAppService
		User user = LoginAppService.getInstance().loginGoogle(email, password);

		//If login() success user is stored in the Server State
		if (user != null) {
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();		
				this.serverState.put(token, user);		
				return(token);
			} else {
				throw new RemoteException("User is already logged in google!");
			}
		} else {
			throw new RemoteException("Login on google fails!");
		}
	}
	
	@Override
	public synchronized void logout(long token) throws RemoteException {
		System.out.println(" * RemoteFacade logout: " + token);
		
		if (this.serverState.containsKey(token)) {
			//Logout means remove the User from Server State
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
	public List<ActivityDTO> getActivities() throws RemoteException {

		List<Activity> activities = TrainingAppService.getInstance().getActivities();
		
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
	public boolean createActivity(String title, String sport, Float km, String date, String startTime, int duration) throws RemoteException {			
		boolean activityCreatedStatus = TrainingAppService.getInstance().createActivity(title, sport, km, date, startTime, duration);

		return activityCreatedStatus;
	}
	
	
	@Override
	public boolean createChallenge(String title, String sport, String start, String end, Float targetDistance, int targetTime ) throws RemoteException {	
		boolean challengeCreatedStatus = TrainingAppService.getInstance().createChallenge(title, sport, start, end, targetDistance, targetTime);
		return challengeCreatedStatus;
		
	}
	
	@Override
	public long registration(String email, String password, String nickname,String birthdate, int weigth, int heigth, int maxrate,
			int minRate) throws RemoteException {
		User user = LoginAppService.getInstance().registration(email, password,nickname,birthdate,weigth, heigth, maxrate,
				minRate);
		if (user != null) {
			// If user is not logged in
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();
				this.serverState.put(token, user);
				return (token);
			} else {
				throw new RemoteException("User is already registered in!");
			}
		} else {
			throw new RemoteException("Register fails!");
		}

	}
	
}