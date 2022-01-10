package es.deusto.ingenieria.sd.strava.server.data.domain;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class Challenge {
	@PrimaryKey
	private String title;
	private String sport;
	private String start;
	private String end;
	private float targetDistance;
	private int targetTime; 
	
	@Persistent(defaultFetchGroup="true")
	private User creator;
	
	public Challenge() {
		
	}
	
	public Challenge(String title, String sport, String start, String end, float targetDistance, int targetTime) {
		this.title = title;
		this.sport = sport;
		this.start = start;
		this.end = end;
		this.targetDistance = targetDistance;
		this.targetTime = targetTime;
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public float getTargetDistance() {
		return targetDistance;
	}

	public void setTargetDistance(float targetDistance) {
		this.targetDistance = targetDistance;
	}

	public int getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(int targetTime) {
		this.targetTime = targetTime;
	}


	@Override
	public String toString() {
		return "Challenge [title=" + title + ", sport=" + sport + ", targetDistance=" + targetDistance + ", targetTime=" + targetTime
				+ "]";
	};
	
	
}
