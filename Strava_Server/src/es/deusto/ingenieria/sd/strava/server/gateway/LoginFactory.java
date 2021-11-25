package es.deusto.ingenieria.sd.strava.server.gateway;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.strava.server.gateway.ILogin;
import es.deusto.ingenieria.sd.strava.server.gateway.FacebookServiceGateway;

//This class implements Service Locator pattern
public class LoginFactory {
	
	//ILOgin reference
	private ILogin service;
	
	public ILogin getTest() {
		FacebookServiceGateway gateway = new FacebookServiceGateway("127.0.0.1", 35600);
		gateway.test();
		return new FacebookServiceGateway("127.0.0.1", 35600);
	}
	
	public ILogin getTest2() {
		GoogleServiceGateway gateway = new GoogleServiceGateway("127.0.0.1", 1099);
		gateway.test();
		return new GoogleServiceGateway("127.0.0.1", 35600);
	}
	
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
