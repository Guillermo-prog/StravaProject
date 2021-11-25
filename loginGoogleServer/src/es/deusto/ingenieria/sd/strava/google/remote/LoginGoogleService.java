package es.deusto.ingenieria.sd.strava.google.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginGoogleService extends UnicastRemoteObject implements ILoginGoogle {
	private static final long serialVersionUID = 1L;
	public static long token = -1;

	// Attribute for the Singleton pattern
	public static LoginGoogleService instance;

	private LoginGoogleService() throws RemoteException {
		super();
		getConversionRates();
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

	private static final void getConversionRates() {
		try {

		} catch (Exception ex) {
			System.out.println("  # Error getting conversion rates(): " + ex.getMessage());
		}

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YYYY - HH:mm");

		System.out.println(
				" - Exchange rates obtained (" + dateFormatter.format(Calendar.getInstance().getTime()) + ")!!");
		System.out.println("\t- EURO to USD rate: ");
	}

	public long loginGoogle(String email, String password) throws RemoteException {
		try {
			User user = new User();
			user.setEmail("thomas.e2001@gmail.com");
			user.setNickname("Thomas");
			// Generate the hash of the password
			String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");
			user.setPassword(sha1);

			if (user.getEmail().equals(email) && user.checkPassword(password)) {
				return user;
			} else {
				return null;
			}

		} catch (Exception ex) {
			System.out.println("  # Error trying to log on Google " + ex.getMessage());
		}
		
	}

}