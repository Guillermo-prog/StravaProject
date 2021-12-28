package es.deusto.ingenieria.sd.strava.server.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.factory.LoginFactory;


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
	
	
	public User login(String email, String hashedPassword, String type) throws RemoteException {
		User existingUser = new User();
		if (type.equals("normal")) {
			for (User user : users) {
				String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(user.getPassword());
				if (user.getEmail().equals(email) && sha1.equals(hashedPassword)) {
					existingUser = user;
				}
			}
		}
		if (type.equals("Google") || type.equals("Facebook")) {
			LoginFactory factory = new LoginFactory();
			try {
				boolean userExist = factory.createServiceGateways(type).login(email, hashedPassword);
				if (userExist) {
					existingUser.setEmail(email);
					existingUser.setPassword(hashedPassword);
				}
			}
			catch (RemoteException e) {
				e.printStackTrace();
			}
		}	
		return existingUser;

	};


	// registration
	public User registration(String email, String password, String birthdate, String nickname, int weigth, int heigth,
			int maxrate, int minRate) {
		// TODO: Get User using DAO and check
		User user = new User();
		user.setEmail(email);
		user.setNickname(nickname);
		// Generate the hash of the password
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
		user.setPassword(sha1);
		// nuevos campos
		user.setNickname(nickname);
		user.setWeight(weigth);
		user.setHeight(heigth);
		user.setMaxRate(maxrate);
		user.setMinRate(minRate);
		user.setBirthdate(birthdate);
		System.out.println("El user se ha creado bien");
		this.users.add(user);
		return user;
	}
}