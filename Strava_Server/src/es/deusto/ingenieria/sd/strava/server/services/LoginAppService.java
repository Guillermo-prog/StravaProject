package es.deusto.ingenieria.sd.strava.server.services;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.factory.LoginFactory;
import es.deusto.ingenieria.sd.strava.server.gateway.FacebookServiceGateway;
import es.deusto.ingenieria.sd.strava.server.gateway.*;

public class LoginAppService {

	// Instance for the Singleton Pattern
	private static LoginAppService instance;

	private LoginAppService() {
	}

	public static LoginAppService getInstance() {
		if (instance == null) {
			instance = new LoginAppService();
		}

		return instance;
	}

	public User login(String email, String password) {
		// TODO: Get User using DAO and check
		LoginFactory factory = new LoginFactory();
		boolean fbUserExists = factory.createServiceGateways("Facebook").checkUser(email, password);
		System.out.println("After checking the FB-server, is it true or false that the user exists? " + fbUserExists);
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
	}
	
	//Kommentera tillbaka detta
	
//	public User loginGoogle(String email, String password) {
//		// TODO: Get User using DAO and check
//		LoginFactory factory = new LoginFactory();
//		ILogin googleGateway = factory.getTest();
//		User user = new User();
//		return GoogleServiceGateway.getInstance().loginGoogle(email, password);
//
//	}

	// registration
	public User registration(String email, String password, String birthdate, String nickname, int weigth, int heigth,
			int maxrate, int minRate) {
		// TODO: Get User using DAO and check
		User user = new User();
		user.setEmail("thomas.e2001@gmail.com");
		user.setNickname("Thomas");
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