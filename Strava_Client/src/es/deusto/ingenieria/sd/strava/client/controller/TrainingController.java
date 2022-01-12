package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;
import java.util.List;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.dto.ActivityDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;

public class TrainingController {
	
	private ServiceLocator serviceLocator;
	
	public TrainingController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public List<ActivityDTO> getActivities(){
		try {
			
			return this.serviceLocator.getService().getActivities();
		} catch (RemoteException e) {
			System.out.println("#Error getting all activites : " + e);
			return null;
		}
	}

	public List<ChallengeDTO> getChallenges(){
		try {
			return this.serviceLocator.getService().getChallenges();
		} catch (RemoteException e) {
			System.out.println("#Error getting all challenges : " + e);
			return null;
		}
	}
	
	public boolean createActivity(long token, String title, String sport, Float km, String date, String startTime, int duration) {
		try {
			return this.serviceLocator.getService().createActivity(token, title, sport, km, date, startTime, duration);
		} catch (RemoteException e) {
			return false;
		}
	}
	

	public boolean createChallenge(String title, String sport, String start, String end, Float targetDistance, int targetTime) {
		try {
			return this.serviceLocator.getService().createChallenge(title, sport, start, end, targetDistance, targetTime);
		} catch (RemoteException e) {
			return false;
		}		
		
	}
	
	public boolean acceptChallenge(long token, String challengeTitle) {
		try {
			return this.serviceLocator.getService().acceptChallenge(token, challengeTitle);
		} catch (RemoteException e) {
			System.out.println("# Error accepting challenge: " + e);
			return false;		
		}
	}

	public void logout(long token) {
		try {
			this.serviceLocator.getService().logout(token);
		} catch (RemoteException e) {
			System.out.println("# Error during logout: " + e);
					
		}
	}
}
