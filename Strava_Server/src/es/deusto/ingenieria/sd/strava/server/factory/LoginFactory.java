package es.deusto.ingenieria.sd.strava.server.factory;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.strava.server.gateway.ILogin;
import es.deusto.ingenieria.sd.strava.server.gateway.FacebookServiceGateway;

	//Kommentera tillbaka
//import es.deusto.ingenieria.sd.strava.server.gateway.GoogleServiceGateway;

//This class implements Service Locator pattern
public class LoginFactory {
	
	//ILOgin reference
	private ILogin service;
	
	public ILogin createServiceGateways(String authType) {
		if (authType == "Facebook") {
			//FacebookServiceGateway gateway = new FacebookServiceGateway("127.0.0.1", 35600);
			return FacebookServiceGateway.getInstance();
		}
		else if (authType =="Google") {
			//Create a GoogleServiceGateway here
			return null;
		}
		else {
			System.out.println("Error, wrong authorization type, not Facebook or Google");
			return null;
		}
		
	}
	
	//Kommentera tillbaka
	
//	public ILogin getTest2() {
//		GoogleServiceGateway gateway = new GoogleServiceGateway("127.0.0.1", 1099);
//		gateway.test();
//		return new GoogleServiceGateway("127.0.0.1", 1099);
//	}
//	
//	public String createGateway() {
//		return service.sendMessage("en","es","good morning");
//	}
//	public void setService(String ip, String port, String serviceName) {
//				
// 		try {		
//			this.service = createServiceGateways();
//			
//		} catch (Exception ex) {
//			System.err.println("# Error creating Service Gateways: " + ex);
//		}		
//	}
//	
//	public ILogin getService() { 
//		return this.service;
//	}
}
