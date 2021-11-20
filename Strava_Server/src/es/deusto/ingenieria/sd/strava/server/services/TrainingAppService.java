package es.deusto.ingenieria.sd.strava.server.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import es.deusto.ingenieria.sd.strava.server.data.domain.Activity;
import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class TrainingAppService {
	private static TrainingAppService instance = new TrainingAppService();

	//TODO: remove when DAO Pattern is implemented
	private List<Challenge> challenges = new ArrayList<>();
	private List<Activity> activities = new ArrayList<>();
	//TODO: remove when DAO Pattern is implemented
	private TrainingAppService() {
		this.initializeData();
	}
	//TODO: remove when DAO Pattern is implemented
	private void initializeData() {
		// TODO Auto-generated method stub
		//Create Users
		User user0 = new User();
		user0.setEmail("thomas.e2001@gmail.com");
		user0.setNickname("Thomas");
		user0.setPassword("$!9PhNz,");
		
		User user1 = new User();
		user1.setEmail("sample@gmail.com");
		user1.setNickname("buyer33");		
		user1.setPassword("hqc`}3Hb");
		
		//Create Challenges
		Challenge runAMarathon = new Challenge();
		runAMarathon.setTitle("Marathon");
		runAMarathon.setSport("running");
		runAMarathon.setStart("20211113");
		runAMarathon.setEnd("20220524");
		runAMarathon.setTargetDistance(42);
		runAMarathon.setTargetTime(4);
		
		Challenge bike10Km = new Challenge();
		bike10Km.setTitle("Bike 10 KM");
		bike10Km.setSport("biking");
		bike10Km.setStart("20211113");
		bike10Km.setEnd("20220524");
		bike10Km.setTargetDistance(10);
		bike10Km.setTargetTime(0);
	
		//Create Activities				
		Activity running5 = new Activity();
		running5.setTitle("Five KM");
		running5.setSport("running");
		running5.setDistanceKm(5);
		running5.setStartTime("10:00");
		running5.setStartDate("20201213");
		running5.setDurationMin(25);
					
		Activity running15 = new Activity();
		running15.setTitle("Fifteen KM");
		running15.setSport("running");
		running15.setDistanceKm(15);
		running15.setStartTime("12:00");
		running15.setStartDate("20201113");
		running15.setDurationMin(102);
		
		//Add the Activity to the local cache.
		this.activities.add(running5);
		this.activities.add(running15);

		this.challenges.add(runAMarathon);
		this.challenges.add(bike10Km);
	}
	
	public static TrainingAppService getInstance() {
		return instance;
	}
	public List<Challenge> getChallenges() {
		//TODO: Get all the categories using DAO Pattern	
		return this.challenges;
	}
	public List<Activity> getActivities() {
		//TODO: Get all the categories using DAO Pattern	
		return this.activities;
	}	

	public boolean createChallenge(String title, String sport, String start, String end, Float targetDistance, int targetTime ) {
		Challenge newChallenge = new Challenge();
		newChallenge.setTitle(title);
		newChallenge.setSport(sport);
		newChallenge.setStart(start);
		newChallenge.setEnd(end);
		newChallenge.setTargetDistance(targetDistance);
		newChallenge.setTargetTime(targetTime);
		Boolean challengeCanBeAdded = false;
		
		for (Challenge challenge : challenges) {
			if (!title.equals(challenge.getTitle())) { //this check doesnt work yet
				this.challenges.add(newChallenge);
				System.out.println("Challenge has been added.");	
				challengeCanBeAdded = true;
				break;
			}
			else {
				System.out.println("Challenge already exist or the values are null.");
			}
		}
//		if (challengeCanBeAdded) {
//			this.challenges.add(newChallenge);
//		}
		return challengeCanBeAdded;
	}
	
	public void addCreatedChallenge(Challenge newChallenge) { //I dont think this method will be needed when above if-statement works
		
		
	}
	
	public boolean acceptChallenge(User user, String title) {	
		Challenge challengeObject = new Challenge();
		    for(Challenge challenge : challenges) {
		    	
		    	System.out.println("ACCEPTING: " + challenge.getTitle());
		        if(challenge.getTitle().equals(title)) {
		        	challengeObject = challenge;
	        	
		        }
		    }
		if (challengeObject != null) {
			user.acceptChallenge(challengeObject);
			return true;
		}
		else {
			return false;
		}		
	}
	
	public boolean startActivity() {
		//TODO	Create here the activity, taking into account the class diagram
		return true;
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
}
