package es.deusto.ingenieria.sd.strava.client;

//import java.util.List;
import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.gui.LoginDialog;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

public class MainProgram {

	public static void main(String[] args) {
		ServiceLocator serviceLocator = new ServiceLocator();

		// args[0] = RMIRegistry IP
		// args[1] = RMIRegistry Port
		// args[2] = Service Name
		serviceLocator.setService(args[0], args[1], args[2]);

		LoginController loginController = new LoginController(serviceLocator);
		LoginDialog loginDialog = new LoginDialog(loginController, serviceLocator);
		System.out.println("Gorka");
		// Login
		
		System.out.println("entra generar ventana");
		loginDialog.LoginD();
		System.out.println("sale generar ventana");


		// Logout
		//loginDialog.logout();
	}
}