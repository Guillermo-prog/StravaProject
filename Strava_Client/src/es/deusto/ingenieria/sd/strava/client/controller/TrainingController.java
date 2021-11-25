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
	
	//	public boolean startActivity(String title, String sport) {
	public boolean startActivity() {
		try {
			return this.serviceLocator.getService().startActivity();
		} catch (RemoteException e) {
			return false;
		}
		//TODO	Create here the activity, taking into account the class diagram
		//return true;
	}
	
	/*//TODO delete we dont need endActivity, revise class diagram
	 * 
	 * 
	 * 	public boolean startActivity(String title, String sport) {
			//TODO	
			return true;
		}
	 * 
	 */

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

}
