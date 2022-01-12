package es.deusto.ingenieria.sd.strava.server.data.domain;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class User {
	//@PrimaryKey
	private String email;
	private String password;
	private String nickname;
	private String birthdate;
	private int weight;
	private int height;
	private int maxRate;
	private int minRate;
	
	@Join
	@Persistent(mappedBy="user", dependentElement="true", defaultFetchGroup="true")
	private List<Activity> activities = new ArrayList<>();
	@Join
	@Persistent(mappedBy="creator", dependentElement="true", defaultFetchGroup="true")
	private List<Challenge> challenges = new ArrayList<>();
	

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Challenge> getChallenges() {
		return challenges;
	}

	public void setChallenges(List<Challenge> challenges) {
		this.challenges = challenges;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(int maxRate) {
		this.maxRate = maxRate;
	}

	public int getMinRate() {
		return minRate;
	}

	public void setMinRate(int minRate) {
		this.minRate = minRate;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean createChallenge() {
		// TODO Create a challenge here, return true if the challenge is created
		// correctly

		return true;
	}

	public void acceptChallenge(Challenge challenge) {

		// if (challenge!=null && !this.challenges.contains(challenge)) { THIS LINE WILL
		// BE USED !
		if (challenge != null) {

			this.challenges.add(challenge);
			System.out.println("All challenges in user, serverside:" + this.challenges);
		}
	}


	public void createActivity(Activity activity) {
		if (activity != null && !this.activities.contains(activity)) {
			this.activities.add(activity);
		}
	}

	

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append(this.nickname);
		result.append(" - ");
		result.append(this.email);
		result.append(" - (");


		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.email.equals(((User) obj).email);
		}

		return false;
	}
}