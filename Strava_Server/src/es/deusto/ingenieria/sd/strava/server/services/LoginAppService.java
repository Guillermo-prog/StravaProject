package es.deusto.ingenieria.sd.strava.server.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.factory.LoginFactory;
import es.deusto.ingenieria.sd.strava.server.gateway.FacebookServiceGateway;
import es.deusto.ingenieria.sd.strava.server.gateway.*;

public class LoginAppService {

	// Instance for the Singleton Pattern
	private static LoginAppService instance;
	
	private List<User> users = new ArrayList<>();

	private LoginAppService() {
		this.initializeData();
	}
	
	//Initialize data and set it into the list
	private void initializeData() {
		// TODO Auto-generated method stub
		//Create Users
		User user0 = new User();
		user0.setEmail("thomas.e2001@gmail.com");
		user0.setNickname("Thomas");
		user0.setPassword("$!9PhNz,");
		
		User user1 = new User();
		user1.setEmail("sample@gmail.com");
		user1.setNickname("buyer33");		
		user1.setPassword("hqc`}3Hb");
		
		User user2 = new User();
		user2.setEmail("guillermo@gmail.com");
		user2.setNickname("guille");		
		user2.setPassword("guillermo");
		
		this.users.add(user0);
		this.users.add(user1);
		this.users.add(user2);
		}

	public static LoginAppService getInstance() {
		if (instance == null) {
			instance = new LoginAppService();
		}

		return instance;
	}
	
	
	//Login with if for decission of what auth will be used
	public User login(String email, String hashedPassword) {
		System.out.println("in i login");
		//TODO: Get User using DAO and check
		User existingUser = new User();
		System.out.println("Vad är user? " + existingUser);
		boolean userExistsOnLocalServer = false;
		boolean fbUserExists = false;
		
		for (User user : users) {
			String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(user.getPassword());
			if (user.getEmail().equals(email) && sha1.equals(hashedPassword)) {
				existingUser = user;
				userExistsOnLocalServer = true;
			}
		}
		
		if (userExistsOnLocalServer == false) {
			LoginFactory factory = new LoginFactory();
			try {
				fbUserExists = factory.createServiceGateways("Facebook").login(email, hashedPassword);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			existingUser.setEmail(email);
			existingUser.setPassword(hashedPassword);
		}

		if (userExistsOnLocalServer == false && fbUserExists == false) {
			//RUN THE CODE FOR CHECKING IF THE USER EXISTS IN GOOGLE
			
		}
		existingUser = null;
		
		return existingUser;
	}
	
	public User loginGoogle(String email, String password) {
		System.out.println(" entra logingoogle en loginappservice y entra factory");
		LoginFactory loginfactory = new LoginFactory();
		
		boolean checkGoogle;
		try {
			System.out.println("check google in ");
			checkGoogle = loginfactory.createGoogleServiceGateway().login(email, password); //loginGoogle
			System.out.println("check google out " + checkGoogle);
			if(checkGoogle) {//esto que si existe en el servidor de google --> entonces creamos un usuario y lo devolvemos
				User user = new User();
				user.setEmail(email);
				user.setPassword(password);
				return user;
			}
		} catch (RemoteException e) {
			System.out.println("Can not authenticate user " + e.toString());
		}
		return null;
	}
	

	// registration
	public User registration(String email, String password, String birthdate, String nickname, int weigth, int heigth,
			int maxrate, int minRate) {
		// TODO: Get User using DAO and check
		User user = new User();
		user.setEmail(email);
		user.setNickname(nickname);
		// Generate the hash of the password
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");
		user.setPassword(sha1);
		// nuevos campos
		user.setNickname(nickname);
		user.setWeight(weigth);
		user.setHeight(heigth);
		user.setMaxRate(maxrate);
		user.setMinRate(minRate);
		user.setBirthdate(birthdate);
		System.out.println("el user se ha creado bien");
		if (user.getEmail().equals(email) && user.checkPassword(password)) {
			return user;
		} else {
			return null;
		}
	}
}