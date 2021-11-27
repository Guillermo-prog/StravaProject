package es.deusto.ingenieria.sd.strava.server.gateway;
import java.rmi.Naming;
import es.deusto.ingenieria.sd.strava.google.remote.ILoginGoogle;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

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
		return instance;
	}
	
	public static User loginGoogle(String email, String password) {
		System.out.println(" Google Login from Google Service Gateway");
		
		try {
			return this.googleLoginService.loginGoogle(email, password);
		} catch (Exception ex) {
			System.out.println("Error getting Google Login from Google Service Gateway: " + ex.getMessage());
			return null;
		}	
		
	}
}
