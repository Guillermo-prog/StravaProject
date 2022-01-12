package es.deusto.ingenieria.sd.strava.server.data.domain;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(detachable="true")
public class Activity{
	private String title;
	private String sport;
	private float distanceKm;
	private String startTime;
	private String startDate;
	private float durationMin;	
	
	@Persistent(defaultFetchGroup="true")
	private User user;
	
//	public Activity() {
//		
//	}
//	
//	
//	public Activity(String title, String sport, float distanceKm, String startTime, String startDate, float durationMin) {
//		
//		this.title = title;
//		this.sport = sport;
//		this.distanceKm = distanceKm;
//		this.startTime = startTime;
//		this.startDate = startDate;
//		this.durationMin = durationMin;
//	}
	
	public User getUser(){
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public float getDistanceKm() {
		return distanceKm;
	}
	public void setDistanceKm(float distanceKm) {
		this.distanceKm = distanceKm;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public float getDurationMin() {
		return durationMin;
	}
	public void setDurationMin(float durationMin) {
		this.durationMin = durationMin;
	}
	@Override
	public String toString() {
		return "Activity [title=" + title + ", sport=" + sport + ", distanceKm=" + distanceKm + ", startTime="
				+ startTime + ", startDate=" + startDate + ", durationMin=" + durationMin + "User=" + user + "]";
	}
}
