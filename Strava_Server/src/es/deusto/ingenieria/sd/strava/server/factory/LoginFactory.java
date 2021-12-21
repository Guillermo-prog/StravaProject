package es.deusto.ingenieria.sd.strava.server.factory;

import es.deusto.ingenieria.sd.strava.server.gateway.ILogin;
import es.deusto.ingenieria.sd.strava.server.gateway.FacebookServiceGateway;
import es.deusto.ingenieria.sd.strava.server.gateway.GoogleServiceGateway;

//import es.deusto.ingenieria.sd.strava.server.gateway.GoogleServiceGateway;

//This class implements Service Locator pattern
public class LoginFactory {
	
	//ILOgin reference
	
	public ILogin createServiceGateways(String authType) {
		if (authType.equals("Facebook")) {
			System.out.println("in factory");
			return FacebookServiceGateway.getInstance();
		}
		else if (authType.equals("Google")) {
			return GoogleServiceGateway.getInstance();
		}
		else {
			System.out.println("Error, wrong authorization type, not Facebook or Google");
			return null;
		}
		
	}
	
	
}
