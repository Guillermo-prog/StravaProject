package es.deusto.ingenieria.sd.strava.google.server;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
		System.out.println("ENTRA SERVER EXTERNO");
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
	public boolean loginGoogle(String email, String password) throws RemoteException {
		try {
			LoginGoogleService.getInstance();
			hashGoogle.put("test@gmail.google.com", "$!9PhNz,");

			if (hashGoogle.containsKey(email)) {
				if (hashGoogle.get(email).matches(password)) {
					return true;
				}
			}

		} catch (Exception ex) {
			System.out.println("  # Error trying to log on Google " + ex.getMessage());
		}
		return false;

	}

}