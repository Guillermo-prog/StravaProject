package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.sql.Time;
import java.util.Date;
import java.io.*;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class ActivityDTO implements java.io.Serializable {
	private User user;
	private String title;
	private String sport;
	private float distanceKm;
	private String startTime;
	private String startDate;
	private float durationMin;
	
	public User getUser() {
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
		return "ActivityDTO [title=" + title + ", sport=" + sport + ", distanceKm=" + distanceKm + ", startTime="
				+ startTime + ", startDate=" + startDate + ", durationMin=" + durationMin + "User=" + user + "]";
	}
	
	
}
