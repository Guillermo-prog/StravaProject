package es.deusto.ingenieria.sd.strava.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.strava.server.data.domain.Activity;
import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.data.dao.ActivityDAO;
import es.deusto.ingenieria.sd.strava.server.data.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.strava.server.data.dao.IDataAccessObject;
import es.deusto.ingenieria.sd.strava.server.data.dao.UserDAO;

public class TrainingAppService {
	private static TrainingAppService instance = new TrainingAppService();
	private PersistenceManagerFactory pmf;
	//TODO: remove when DAO Pattern is implemented
	private List<Challenge> challenges = new ArrayList<>();
	private List<Activity> activities = new ArrayList<>();
	//TODO: remove when DAO Pattern is implemented
	
	private ChallengeDAO challangeDAO;
	private ActivityDAO activityDAO;
	private UserDAO userDAO;
	
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
		this.challenges = challangeDAO.getInstance().getAll();
		System.out.println("Returning Challenges to client ....");
		return this.challenges;
	}
	
//	public List<Activity> getActivities() {
//		this.activities = activityDAO.getInstance().getAll();
//		System.out.println("Returning Activities to client ....");
//		return this.activities;
//	}
	public List<Activity> getActivities(User user) {
		this.activities = userDAO.getInstance().find(user.getEmail()).getActivities();
		for (Activity activity : activities) {
			activity.setUser(null);
		}
		System.out.println("Returning Activities to client ....");
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
		Boolean challengeCanBeAdded = true;
		
		List<Challenge> challengeList = ChallengeDAO.getInstance().getAll();
		
		for (Challenge challenge : challengeList) {
			if (title.equals(challenge.getTitle())) {
				System.out.println("Challenge with the same title already exists!");
				challengeCanBeAdded = false;
				return challengeCanBeAdded;
			}
		}	
		
		if (challengeCanBeAdded) {
			ChallengeDAO.getInstance().save(newChallenge);
			return challengeCanBeAdded;
		}
		
		return challengeCanBeAdded;
	}
	
	public void sendChallengeEmail (User user, Challenge challenge) {
		String email = user.getEmail();
		String message = "Hello " + email + "! You have just accepted the challenge " + challenge.getTitle() + 
				", of the type " + challenge.getSport() + ". The challenge starts: " + challenge.getStart() +
				", and ends at: " + challenge.getEnd() + ". Good luck!" ;
		
		new MailSender(email).sendMessage(message);

	};
	
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
			System.out.println("USER in trainingap serv" + user + user.getEmail());
			sendChallengeEmail(user, challengeObject);			
			return true;
		}
		else {
			return false;
		}		
	}
	
	public boolean createActivity(User user, String title, String sport, Float km, String date, String startTime, int duration) {
		User userInDb = UserDAO.getInstance().find(String.valueOf(user.getEmail()));
		Activity newActivity = new Activity();
		newActivity.setTitle(title);
		newActivity.setSport(sport);
		newActivity.setStartDate(date);
		newActivity.setStartTime(startTime);
		newActivity.setDistanceKm(km);
		newActivity.setDurationMin(duration);
		newActivity.setUser(userInDb);
		
		List<Activity> activityList = userInDb.getActivities();

		Boolean activityCanBeAdded = true;
		
		for (Activity activity : activityList) {
			if (title.equals(activity.getTitle())) { 
				activityCanBeAdded = false;
				break;
			}
		}
		
		if(activityCanBeAdded) {
			userInDb.createActivity(newActivity);
			ActivityDAO.getInstance().save(newActivity);			
			System.out.println("Activity has been added.");	
		}
		
		else {
			System.out.println("Activity already exist or the values are null. ");
		}
		
		return activityCanBeAdded;	

	}
}
