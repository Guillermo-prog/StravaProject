package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

//This class implements Controller pattern.
public class LoginController {	
	
	//Reference to the Service Locator
	private ServiceLocator serviceLocator;
	//This attribute stores the token when login success
	private long token = -1; //-1 = login has not been done or fails

	public LoginController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
<<<<<<< HEAD
	public boolean login(String email, String password) {
//		try {
			this.token = this.serviceLocator.getService().login(email, password, "normal");			
=======
	public boolean login(String email, String password, String type) {
		try {
			this.token = this.serviceLocator.getService().login(email, password, type);
>>>>>>> branch 'master' of https://github.com/Guillermo-prog/StravaProject.git
			return true;
//		} catch (RemoteException e) {
//			System.out.println("# Error during login: " + e);
//			this.token = -1;
//			return false;
//		}
	}
	
<<<<<<< HEAD
	public boolean loginFacebook(String email, String password)  {
//		try {
			this.token = this.serviceLocator.getService().login(email, password, "Facebook");			
			return true;
//		} catch (RemoteException e) {
//			System.out.println("# Error during login: " + e);
//			this.token = -1;
//			return false;
//		}
	}
	
	public boolean loginGoogle(String email, String password) {
//		try {
			this.token = this.serviceLocator.getService().login(email, password, "Google");			
			return true;
//		} catch (RemoteException e) {
//			System.out.println("# Error during login: " + e);
//			this.token = -1;
//			return false;
//		}
	}

=======
>>>>>>> branch 'master' of https://github.com/Guillermo-prog/StravaProject.git
	public boolean registration(String email, String password,String nickname,String birthdate,int weigth,int heigth,int maxrate,int minRate) {
		try {
			this.token = this.serviceLocator.getService().registration(email, password,nickname,birthdate, weigth,heigth,maxrate,minRate);			
			System.out.println("token i logincontroller" + this.token);
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error during registration: " + e);
			this.token = -1;
			return false;
		}
	}
	
	public void logout() {
		try {
			this.serviceLocator.getService().logout(this.token);
			this.token = -1;
		} catch (RemoteException e) {
			System.out.println("# Error during logout: " + e);
		}
	}

	public long getToken() {
		return token;
	}
}