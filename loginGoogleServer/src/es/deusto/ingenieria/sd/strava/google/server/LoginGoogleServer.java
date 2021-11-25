package es.deusto.ingenieria.sd.strava.google.server;

import java.rmi.Naming;


import es.deusto.ingenieria.sd.strava.google.remote.LoginGoogleService;
import es.deusto.ingenieria.sd.strava.google.remote.ILoginGoogle;

public class LoginGoogleServer {

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
		
		try {
			ILoginGoogle remoteObject = LoginGoogleService.getInstance();			
			Naming.rebind(name, remoteObject);
			System.out.println(" * Google login server'" + name + "' started!!");
		} catch (Exception ex) {
			System.out.println(" #  Google login server: " + ex.getMessage());
			ex.printStackTrace();
		}

	}
}