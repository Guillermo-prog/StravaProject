package es.deusto.ingenieria.sd.strava.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Activity;

public class ActivityAssembler {
	private static ActivityAssembler instance = new ActivityAssembler();
	
	public ActivityAssembler() {}

	public static ActivityAssembler getInstance() {
		System.out.println("Returning the only instance that can be initializated... ");
		return instance;
	}
	
	public ActivityDTO ActivityToDTO(Activity activity) {
		ActivityDTO dto = new ActivityDTO();
		
		dto.setUser(activity.getUser());
		dto.setTitle(activity.getTitle());
		dto.setSport(activity.getSport());
		dto.setDistanceKm(activity.getDistanceKm());
		dto.setStartTime(activity.getStartTime());
		dto.setStartDate(activity.getStartDate());
		dto.setDurationMin(activity.getDurationMin());
		
		return dto;
	}
	
	public List<ActivityDTO> activityToDTO(List<Activity> activities){
		List<ActivityDTO> dtos = new ArrayList<ActivityDTO>();
		
		for (Activity activity : activities) {
			dtos.add(this.ActivityToDTO(activity));
		}
		
		return dtos;
	}
}
