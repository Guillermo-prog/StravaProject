package es.deusto.ingenieria.sd.strava.server.gateway;
import java.rmi.Naming;
import es.deusto.ingenieria.sd.strava.google.remote.ILoginGoogle;

public class GoogleServiceGateway {

	private static GoogleServiceGateway instance = new GoogleServiceGateway();
	private ILogin googleLoginService;

	
	private GoogleServiceGateway() {
		try {		
			String URL = "//127.0.0.1:1099/loginGoogle";
			this.googleLoginService = (ILogin) Naming.lookup(URL);
		} catch (Exception ex) {
			System.err.println("# Error locating remote facade: " + ex);
		}
	}
	
	public static GoogleServiceGateway getInstance() {
		if(instance == null) {
			instance = new GoogleServiceGateway();
		}
		
		return instance;
	}
	
	public static boolean loginGoogle(String email, String password) {
		System.out.println(" Google Login from Google Service Gateway");
		
		try {
			return this.googleLoginService.loginGoogle(email, password);
		} catch (Exception ex) {
			System.out.println("Error getting Google Login from Google Service Gateway: " + ex.getMessage());
			return -1f;
		}	
		
	}
}
