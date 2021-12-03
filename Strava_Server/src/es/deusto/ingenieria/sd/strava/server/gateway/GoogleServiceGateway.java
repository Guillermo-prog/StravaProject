package es.deusto.ingenieria.sd.strava.server.gateway;

import java.rmi.Naming;
import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.strava.google.server.ILoginGoogle;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;

public class GoogleServiceGateway implements ILogin {

	private static GoogleServiceGateway instance = new GoogleServiceGateway();
	private ILoginGoogle googleLoginService;

	private GoogleServiceGateway() {
		try {
			String URL = "//127.0.0.1:1099/loginGoogle";
			this.googleLoginService = (ILoginGoogle) Naming.lookup(URL);
		} catch (Exception ex) {
			System.err.println("# Error locating remote facade: " + ex);
		}
	}

	public static GoogleServiceGateway getInstance() {
		return instance;

	}

//	@Override
//	public boolean loginGoogle(String email, String password) throws RemoteException{
//		System.out.println(" Google Login from Google Service Gateway");
//		
//		try {
//			return this.googleLoginService.loginGoogle(email, password);
//		} catch (Exception ex) {
//			System.out.println("Error getting Google Login from Google Service Gateway: " + ex.getMessage());
//			return false;
//		}	
//		
//	}

//	@Override
//	public boolean checkUser(String email, String password){
//		return false;
//	}
	@Override
	public boolean login(String email, String password) throws RemoteException {
		System.out.println(" Google Login from Google Service Gateway");

		try {
			System.out.println("print login de gateway");
			boolean temp;
			temp = googleLoginService.loginGoogle(email, password);
			System.out.println("GLORIAAA");
			return temp;
		} catch (Exception ex) {
			System.out.println("Error getting Google Login from Google Service Gateway: " + ex.getMessage());
			return false;
		}

	}
}
