package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

//This class implements Controller pattern.
public class LoginController {	
	
	//Reference to the Service Locator
	private ServiceLocator serviceLocator;
	//This attribute stores the token when login success
	private long token = -1; //-1 = login has not been done or fails
	private boolean registrationDone = false;
	public LoginController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public boolean login(String email, String password, String type) {
		try {
			this.token = this.serviceLocator.getService().login(email, password, type);
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error during login: " + e);
			this.token = -1;
			return false;
		}
	}
	
	public boolean registration(String type, String email, String password,String nickname,String birthdate,int weigth,int heigth,int maxrate,int minRate) {
		try {
			this.registrationDone = this.serviceLocator.getService().registration(type, email, password,nickname,birthdate, weigth,heigth,maxrate,minRate);			
			return this.registrationDone;
		} catch (RemoteException e) {
			System.out.println("# Error during registration: " + e);
			this.registrationDone= false;
			return this.registrationDone;
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