package es.deusto.ingenieria.sd.strava.google.server;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class LoginGoogleService extends UnicastRemoteObject implements ILoginGoogle {
	private static final long serialVersionUID = 1L;
	public static long token = -1;

	private HashMap<String, String> hashGoogle = new HashMap<>();

	// Attribute for the Singleton pattern
	public static LoginGoogleService instance;

	private LoginGoogleService() throws RemoteException {
		super();
	}

	public static LoginGoogleService getInstance() {

		if (instance == null) {
			try {
				instance = new LoginGoogleService();
			} catch (Exception ex) {
				System.err.println("  # Error initializing service(): " + ex.getMessage());
			}
		}

		return instance;
	}

	@Override
	public boolean login(String email, String password) throws RemoteException {
		boolean loginTrue = false;
		try {
			LoginGoogleService.getInstance();
			//String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");
			hashGoogle.put("test@gmail.google.com", "$!9PhNz,");

			if (hashGoogle.containsKey(email)) {
				if (hashGoogle.get(email).equals(password)) {
					loginTrue = true;
					System.out.println("User " + email + " exists in the Google server");
				}
				else {
					System.out.println("Email and password does not match");
				}
			}
			else {
				System.out.println("User " + email + " does not exist in the Google server");
			}

		} catch (Exception ex) {
			System.out.println("  # Error trying to log on Google " + ex.getMessage());
		}
		return loginTrue;

	}

}