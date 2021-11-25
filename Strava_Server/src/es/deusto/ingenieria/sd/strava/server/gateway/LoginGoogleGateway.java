package es.deusto.ingenieria.sd.strava.server.gateway;

public class LoginGoogleGateway {

	private static LoginGoogleGateway instance = new LoginGoogleGateway();
	//de aqui iria a external server
	private LoginGoogleGateway(){
		System.out.println("test");	
	}
	
	public static LoginGoogleGateway getInstance() {
		return instance;
	}

}
